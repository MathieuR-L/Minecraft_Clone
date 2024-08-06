package fr.math.minecraft.server.websockets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.ServerConfiguration;
import fr.math.minecraft.shared.ChatMessage;
import org.apache.logging.log4j.Logger;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.DefaultSSLWebSocketServerFactory;
import org.java_websocket.server.WebSocketServer;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class MinecraftWebSocketServer extends WebSocketServer {

    private final Set<WebSocket> clients;
    private final static Logger logger = LoggerUtility.getServerLogger(MinecraftWebSocketServer.class, LogType.TXT);

    public MinecraftWebSocketServer(int port) {
        super(new InetSocketAddress(port));
        ServerConfiguration configuration = ServerConfiguration.getInstance();
        try {
            KeyStore ks = KeyStore.getInstance(configuration.getStoreType());
            FileInputStream fis = new FileInputStream(configuration.getKeyStore());
            ks.load(fis, configuration.getStorePassword().toCharArray());

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(ks, configuration.getKeyPassword().toCharArray());

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), null, null);

            this.setWebSocketFactory(new DefaultSSLWebSocketServerFactory(sslContext));
            logger.info("Configuration SSL effectuée avec succès.");
        } catch (Exception e) {
            logger.warn("Impossible de configurer le certificat SSL, le serveur WebSocket ne pourra commencer de communication chiffrée.");
        }
        this.clients = new CopyOnWriteArraySet<>();
        logger.info("Instance du serveur websocket initiée avec succès.");
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake clientHandshake) {
        clients.add(conn);
        logger.info("Nouvelle connexion entrante de : " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        clients.remove(conn);
        logger.info("Connexion fermée avec : " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {

    }

    @Override
    public void onError(WebSocket conn, Exception e) {
        e.printStackTrace();
    }

    @Override
    public void onStart() {
        logger.info("Serveur WebSocket démarré avec succès !");
    }

    public void broadcastStatus(ServerStatus status) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode eventNode = mapper.createObjectNode();
        String data = status.toJson();

        eventNode.put("type", "SERVER_STATUS");
        eventNode.put("data", data);

        if (data == null) {
            logger.warn("Une update n'a pas réussi à être formatée en JSON correctement.");
            return;
        }

        for (WebSocket client : clients) {
            client.send(data);
        }
        logger.info("Envoi d'une nouvelle update aux clients connectés ! (" + data + ")");
    }

    public void broadcastChatMessage(ChatMessage message) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode eventNode = mapper.createObjectNode();
        ObjectNode messageJSONObject = message.toWebJSONObject();

        eventNode.put("type", "CHAT_MESSAGE");
        eventNode.set("data", messageJSONObject);

        try {
            String data = mapper.writeValueAsString(eventNode);
            for (WebSocket client : clients) {
                client.send(data);
            }
            logger.info("Le message de " + message.getSenderName() + "(" + message.getMessage() + ") a été envoyé aux clients.");
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }

}

package fr.math.minecraft.server.websockets;

import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class MinecraftWebSocketServer extends WebSocketServer {

    private final Set<WebSocket> clients;
    private final static Logger logger = LoggerUtility.getServerLogger(MinecraftWebSocketServer.class, LogType.TXT);

    public MinecraftWebSocketServer(int port) {
        super(new InetSocketAddress(port));
        this.clients = new CopyOnWriteArraySet<>();
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
        String data = status.toJson();
        if (data == null) {
            logger.warn("Une update n'a pas réussi à être formatée en JSON correctement.");
            return;
        }
        logger.info("Envoi d'une nouvelle update aux clients connectés ! (" + data + ")");
        for (WebSocket client : clients) {
            client.send(data);
        }
    }

}

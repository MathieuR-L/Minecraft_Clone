/**
*  Minecraft Clone Math edition : Cybersecurity - A serious game to learn network and cybersecurity
*  Copyright (C) 2024 MeAndTheHomies (Math)
*
*  This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
*
*  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
*
*  You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

package fr.math.minecraft.client;

import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class MinecraftClient {

    public static final int MAX_RESPONSE_LENGTH = 65536;
    private DatagramSocket socket;
    private InetAddress address;
    private final int serverPort;
    private final static Logger logger = LoggerUtility.getClientLogger(MinecraftClient.class, LogType.TXT);
    private final byte[] responseBuffer;
    private final String serverIp;

    public MinecraftClient(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.responseBuffer = new byte[MAX_RESPONSE_LENGTH];
        this.connect();
    }

    public void connect() {
        try {
            this.socket = new DatagramSocket();
            this.socket.setSoTimeout(10000);
            this.address = InetAddress.getByName(serverIp);
        } catch (UnknownHostException | SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message) throws IOException {
        byte[] buffer = message.getBytes(StandardCharsets.UTF_8);
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, this.address, this.serverPort);
        this.getSocket().send(packet);
    }

    public synchronized String receive() {
        DatagramPacket packet = new DatagramPacket(responseBuffer, responseBuffer.length);

        try {
            synchronized (this.getSocket()) {
                this.getSocket().receive(packet);
            }
        } catch (SocketTimeoutException ignored) {

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new String(packet.getData(), 0, packet.getLength()).trim();
    }

    public synchronized String sendString(String message) throws IOException {
        return this.sendBytes(message.getBytes(StandardCharsets.UTF_8));
    }

    public synchronized String sendBytes(byte[] buffer) throws IOException {
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length, this.address, this.serverPort);
        synchronized (this.getSocket()) {
            this.getSocket().send(packet);
            packet = new DatagramPacket(responseBuffer, responseBuffer.length);

            try {
                this.getSocket().receive(packet);
            } catch (SocketTimeoutException e) {
                logger.error(e.getMessage());
                return "TIMEOUT_REACHED";
            }
        }

        return new String(packet.getData(), 0, packet.getLength()).trim();
    }

    public DatagramSocket getSocket() {
        return socket;
    }
}

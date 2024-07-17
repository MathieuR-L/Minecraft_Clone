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

package fr.math.minecraft.server.command;

import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.MinecraftServer;
import org.apache.log4j.Logger;
import org.joml.Vector3f;

import java.util.HashMap;

public class TeleportCommand extends Command{

    private final static Logger logger = LoggerUtility.getServerLogger(TeleportCommand.class, LogType.TXT);

    private float x, y, z;

    public TeleportCommand(String name, String descpription, Team team) {
        super(name, descpription, team);
    }

    @Override
    public void run(String[] message, String sender, Client client, MinecraftServer server) {

        logger.debug("Je run 1 fois la [TP]");

        String newPosition = message[2];
        logger.debug("Bloc 2 du msg:" + newPosition);
        if(server.getClientByName(newPosition) != null) {
            Client destinationPlayer = server.getClientByName(newPosition);
            x = destinationPlayer.getPosition().x;
            y = destinationPlayer.getPosition().y;
            z = destinationPlayer.getPosition().z;
        } else {
            String[] coordinates = newPosition.split(":");
            x = Float.parseFloat(coordinates[0]);
            y = Float.parseFloat(coordinates[1]);
            z = Float.parseFloat(coordinates[2]);
        }

        Client playerTeleported = server.getClientByName(message[1]);
        if(playerTeleported == null) return;

        playerTeleported.setPosition(new Vector3f(x, y, z));

        logger.trace("Le joueur " + client.getName() + " a [TP] le joueur " + message[1] + " en : [x:" + x +" y:" + y + " z:" + z +"]");
    }

    @Override
    public Node initTree(MinecraftServer server) {
        //Noeud de départ
        Node tpNode = new Node("tp");
        HashMap<String, Node> tpOptions = new HashMap<>();
        int maxOption = this.getMaxOptions();

        //Noeuds des propositions de joueur
        for (Client client : server.getClients().values()) {
            String clientName = client.getName();
            Node clientNode = new Node(clientName);
            HashMap<String, Node> clientOptions = new HashMap<>();
            for (Client subClient : server.getClients().values()) {
                String subClientName = subClient.getName();
                Node subClientNode = new Node(subClientName);
                clientOptions.put(subClientName, subClientNode);
            }
            clientNode.setOptions(clientOptions);
            tpOptions.put(clientName, clientNode);
            maxOption++;
            System.out.println("j'ajoute une option:" + maxOption);
        }
        tpNode.setOptions(tpOptions);
        this.setMaxOptions(maxOption);
        return tpNode;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}

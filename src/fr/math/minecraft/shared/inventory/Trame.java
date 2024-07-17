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

package fr.math.minecraft.shared.inventory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.client.Camera;
import fr.math.minecraft.client.Renderer;
import fr.math.minecraft.shared.entity.Entity;

import java.util.UUID;

public class Trame {

    private String type, protocole, ipSource, ipDestination, data;
    private int portSource, portDestination;
    private boolean open;
    private UUID uuid;

    public Trame(String type, String protocole, String ipSource, String ipDestination, String data, int portSource, int portDestination, boolean open) {
        this.type = type;
        this.protocole = protocole;
        this.ipSource = ipSource;
        this.ipDestination = ipDestination;
        this.data = data;
        this.portSource = portSource;
        this.portDestination = portDestination;
        this.open = open;
        this.uuid = UUID.randomUUID();
    }
    public Trame() {
        this.type = "";
        this.protocole = "";
        this.ipSource = "";
        this.ipDestination = "";
        this.data = "";
        this.portSource = -1;
        this.portDestination = -1;
        this.open = false;
        this.uuid = UUID.randomUUID();
    }

    public void setTrame(Entity serverOrigin, Entity serviceRequested, String data) {
        this.setType("IP");
        this.setProtocole("TCP");
        this.setIpSource(serverOrigin.getIP());
        this.setIpDestination(serviceRequested.getIP());
        this.setData(data);
        this.open = false;
    }

    @Override
    public String toString() {
        return "\n|Type : " + type + " | Protocole : " + protocole + "|\n-----------" + "\n|IP Source : " + ipSource + " | IP Destination : " + ipDestination + "|\n-----------" + "\n| Port Source :" + portSource + " | Port Destination : " + portDestination + "|\n-----------" +"\n| Data : " + data + "|";
    }

    public ObjectNode trameJson(ObjectNode entityNode, Trame trame) {
        entityNode.put("typeTrame", trame.getType());
        entityNode.put("protocole",  trame.getProtocole());
        entityNode.put("ipSource",  trame.getIpSource());
        entityNode.put("ipDestination",  trame.getIpDestination());
        entityNode.put("portSource",  trame.getPortSource());
        entityNode.put("portDestination",  trame.getPortDestination());
        entityNode.put("dataTrame",  trame.getData());
        entityNode.put("open", trame.isOpen());

        return entityNode;
    }

    public String stringElement(String element) {
        if(element == null) return "null";
        return element;
    }

    public String stringElement(int element) {
        if(element == -1) return "null";
        return ""+element;
    }
    public boolean isEqual(Trame trame) {
        return trame.getUuid() == this.uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProtocole() {
        return protocole;
    }

    public void setProtocole(String protocole) {
        this.protocole = protocole;
    }

    public String getIpSource() {
        return ipSource;
    }

    public void setIpSource(String ipSource) {
        this.ipSource = ipSource;
    }

    public String getIpDestination() {
        return ipDestination;
    }

    public void setIpDestination(String ipDestination) {
        this.ipDestination = ipDestination;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getPortSource() {
        return portSource;
    }

    public void setPortSource(int portSource) {
        this.portSource = portSource;
    }

    public int getPortDestination() {
        return portDestination;
    }

    public void setPortDestination(int portDestination) {
        this.portDestination = portDestination;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public UUID getUuid() {
        return uuid;
    }
}

package fr.math.minecraft.shared.inventory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.client.Camera;
import fr.math.minecraft.client.Renderer;
import fr.math.minecraft.shared.entity.Entity;

public class Trame {

    private String type, protocole, ipSource, ipDestination, data;
    private int portSource, portDestination;

    public Trame(String type, String protocole, String ipSource, String ipDestination, String data, int portSource, int portDestination) {
        this.type = type;
        this.protocole = protocole;
        this.ipSource = ipSource;
        this.ipDestination = ipDestination;
        this.data = data;
        this.portSource = portSource;
        this.portDestination = portDestination;
    }
    public Trame() {
        this.type = "";
        this.protocole = "";
        this.ipSource = "";
        this.ipDestination = "";
        this.data = "";
        this.portSource = -1;
        this.portDestination = -1;
    }

    public void setTrame(Entity serverOrigin, Entity serviceRequested, String data) {
        this.setType("IP");
        this.setProtocole("TCP");
        this.setIpSource(serverOrigin.getIP());
        this.setIpDestination(serviceRequested.getIP());
        this.setData(data);
    }

    @Override
    public String toString() {
        return "\n|Type : " + type + " | Protocole : " + protocole + "|\n-----------" + "\n|IP Source : " + ipSource + " | IP Destination : " + ipDestination + "|\n-----------" + "\n| Port Source :" + portSource + " | Port Destination : " + portDestination + "|\n-----------" +"\n| Data : " + data + "|";
    }

    public static void trameJson(ObjectNode entityNode, Trame trame) {
        entityNode.put("typeTrame", trame.getType());
        entityNode.put("protocole",  trame.getProtocole());
        entityNode.put("ipSource",  trame.getIpSource());
        entityNode.put("ipDestination",  trame.getIpDestination());
        entityNode.put("portSource",  trame.getPortSource());
        entityNode.put("portDestionation",  trame.getPortDestination());
        entityNode.put("dataTrame",  trame.getData());
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
}

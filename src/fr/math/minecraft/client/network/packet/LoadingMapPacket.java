package fr.math.minecraft.client.network.packet;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.math.minecraft.client.Camera;
import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.client.network.AuthUser;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import org.apache.log4j.Logger;

public class LoadingMapPacket extends ClientPacket implements Runnable{

    private final ObjectMapper mapper;
    private final Player player;
    private final static Logger logger = LoggerUtility.getClientLogger(ConnectionInitPacket.class, LogType.TXT);;
    private final AuthUser user;


    public LoadingMapPacket(Player player, AuthUser user) {
        this.mapper = new ObjectMapper();
        this.player = player;
        this.user = user;
    }

    @Override
    public void run() {

    }

    @Override
    public String toJSON() {
        return null;
    }

    @Override
    public String getResponse() {
        return null;
    }


}

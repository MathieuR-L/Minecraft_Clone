package test.java.factories;

import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.server.Client;

import java.util.Random;
import java.util.UUID;

public class PlayerFactory {

    private final static String[] NAMES = {
        "Dummy",
        "John Doe",
        "Notch"
    };

    public static Player createPlayer() {
        Random random = new Random();
        String name = NAMES[random.nextInt(NAMES.length)];
        return new Player(name);
    }

}

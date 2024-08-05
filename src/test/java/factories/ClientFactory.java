package test.java.factories;

import fr.math.minecraft.server.Client;

import java.util.Random;
import java.util.UUID;

public class ClientFactory {

    private final static String[] NAMES = {
        "Dummy",
        "John Doe",
        "Notch"
    };

    public static Client createClient() {
        Random random = new Random();
        String name = NAMES[random.nextInt(NAMES.length)];
        return new Client(UUID.randomUUID().toString(), name, null, 0);
    }

}

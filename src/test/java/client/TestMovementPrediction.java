package test.java.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.client.Camera;
import fr.math.minecraft.client.entity.player.Player;
import fr.math.minecraft.server.Client;
import fr.math.minecraft.server.payload.InputPayload;
import fr.math.minecraft.shared.GameConfiguration;
import fr.math.minecraft.shared.network.PlayerInputData;
import fr.math.minecraft.shared.world.Chunk;
import fr.math.minecraft.shared.world.World;
import org.joml.Vector3f;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestMovementPrediction {

    private Player player;
    private Client client;

    @Before
    public void prepareEntities() {
        this.player = new Player("Dummy");
        this.client = new Client("1", "Dummy", null, -1);
    }

    @Test
    public void testPosition() {

        World world = new World();

        float yaw = 0.0f;
        float pitch = 0.0f;
        
        player.setYaw(yaw);
        player.setPitch(pitch);

        PlayerInputData inputData = new PlayerInputData(false, false, true, false, false, false, false, yaw, pitch, false, false, false, false, 0);
        List<PlayerInputData> inputs = new ArrayList<>();
        inputs.add(inputData);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        ArrayNode inputsArray = mapper.createArrayNode();

        for (PlayerInputData input : inputs) {
            inputsArray.add(input.toJSON());
        }

        node.put("tick", 0);
        node.put("uuid", "1");
        node.set("inputs", inputsArray);

        InputPayload inputPayload = new InputPayload(node);

        player.setMovingForward(true);
        for (int i = 0; i < 10; i++) {
            client.update(world, inputPayload);
            player.updatePosition(world);
        }

        Assert.assertEquals(player.getPosition(), client.getPosition());
    }
}

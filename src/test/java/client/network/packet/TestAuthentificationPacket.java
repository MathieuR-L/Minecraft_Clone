package test.java.client.network.packet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.math.minecraft.client.gui.buttons.LoginButton;
import fr.math.minecraft.client.network.AuthUser;
import fr.math.minecraft.client.network.packet.AuthentificationPacket;
import fr.math.minecraft.shared.Utils;
import fr.math.minecraft.shared.network.HttpResponse;
import fr.math.minecraft.shared.network.HttpUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import test.java.GameTestCase;

import java.awt.image.BufferedImage;

import static org.mockito.Mockito.*;

public class TestAuthentificationPacket extends GameTestCase {

    private String email, password, username;
    private ObjectMapper mapper;

    @Before
    public void setup() {

        username = "john.doe";
        email = "john.doe@gmail.com";
        password = "password";

        this.mapper = new ObjectMapper();
        ObjectNode authData = mapper.createObjectNode();
        authData.put("email", email);
        authData.put("password", password);

    }

    @Test
    public void testSuccessfulAuthentification() throws JsonProcessingException {

        ArgumentCaptor<AuthUser> userArgumentCaptor = ArgumentCaptor.forClass(AuthUser.class);

        ObjectNode responseData = mapper.createObjectNode();
        ObjectNode userData = mapper.createObjectNode();
        ObjectNode skinData = mapper.createObjectNode();

        skinData.put("link", "https://example.com/skin/");

        userData.put("name", username);
        userData.put("email", email);
        userData.put("token", "token");
        userData.set("skin", skinData);

        responseData.set("user", userData);

        HttpResponse response = new HttpResponse(new StringBuilder(mapper.writeValueAsString(responseData)), 200);
        LoginButton button = new LoginButton();
        AuthentificationPacket packet = new AuthentificationPacket(button, email, password);

        try (MockedStatic<HttpUtils> httpMock = Mockito.mockStatic(HttpUtils.class)) {
            httpMock.when(() -> HttpUtils.POST(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(response);

            try (MockedStatic<Utils> utilsMock = Mockito.mockStatic(Utils.class)) {
                utilsMock.when(() -> Utils.loadBase64Skin(ArgumentMatchers.any())).thenReturn(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB));
                packet.send();

                verify(game).setUser(userArgumentCaptor.capture());
                AuthUser capturedUser = userArgumentCaptor.getValue();

                Assert.assertEquals(capturedUser.getEmail(), email);
                Assert.assertEquals(capturedUser.getName(), username);
                Assert.assertEquals(capturedUser.getToken(), "token");
                Assert.assertEquals(capturedUser.getSkinUrl(), "https://example.com/skin/");
            }
        }
    }
}

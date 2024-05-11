package fr.math.minecraft.shared;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

public class Utils {

    public static BufferedImage loadSkin(String skinUrl) {
        try {
            return ImageIO.read(new URL(skinUrl).openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage loadBase64Skin(String base64Skin) throws IOException {
        byte[] skinBytes = Base64.getDecoder().decode(base64Skin);
        return ImageIO.read(new ByteArrayInputStream(skinBytes));
    }

}

package fr.math.minecraft.noise;

import fr.math.minecraft.client.AnimationMath;
import org.joml.SimplexNoise;
import org.joml.Vector2f;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Vector2f[] points = {
            new Vector2f(0.000000f, 0.029412f),
            new Vector2f(0.178104f, 0.078432f),
            new Vector2f(0.214051f, 0.116014f),
            new Vector2f(0.323529f, 0.119281f),
            new Vector2f(0.346405f, 0.192810f),
            new Vector2f(0.464256f, 0.193628f),
            new Vector2f(0.498569f, 0.355800f),
            new Vector2f(0.536815f, 0.378880f),
            new Vector2f(0.657730f, 0.385519f),
            new Vector2f(0.681588f, 0.500766f),
            new Vector2f(0.722438f, 0.511438f),
            new Vector2f(0.737144f, 0.637254f),
            new Vector2f(0.777422f, 0.648692f),
            new Vector2f(0.784135f, 0.737745f),
            new Vector2f(0.807918f, 0.759395f),
            new Vector2f(0.876545f, 0.763684f),
            new Vector2f(0.918665f, 0.964359f),
            new Vector2f(1.000000f, 1.000000f)
        };

        BufferedImage image = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < 512; x++) {
            for (int y = 0; y < 512; y++) {
                float noise = getNoise(x, y);
                noise = spline(noise, points);
                int rgb = (int) (noise * 255);
                image.setRGB(x, y, new Color(rgb, rgb, rgb).getRGB());
            }
        }

        try {
            ImageIO.write(image, "png", new File("noise/noise.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static float spline(float noise, Vector2f[] points) {
        for (int i = 1; i < points.length; i++) {
            Vector2f p1 = points[i - 1];
            Vector2f p2 = points[i];

            if (p1.x <= noise && noise <= p2.x) {
                noise = AnimationMath.lerp(p1.y, p2.y, (noise - p1.x) / (p2.x - p1.x));
            }
        }
        return noise;
    }

    public static float getNoise(int x, int y) {
        float totalAmplitude = 0.0f;
        int octaves = 8;
        float roughness = 0.52f;
        float smoothness = 100.0f;
        float noiseValue = 0.0f;
        for (int i = 0; i < octaves; i++) {
            float frequency = (float) Math.pow(2.0, i);
            float amplitude = (float) Math.pow(roughness, i);

            float noiseX = x * frequency / smoothness;
            float noiseY = y * frequency / smoothness;

            float noise = SimplexNoise.noise(noiseX, noiseY);
            noise = (noise + 1.0f) / 2.0f;
            noiseValue += noise * amplitude;
            totalAmplitude += amplitude;
        }

        return noiseValue / totalAmplitude;
    }

}

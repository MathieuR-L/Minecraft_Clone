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

package fr.math.minecraft.client.builder;

import fr.math.minecraft.shared.GameConfiguration;
import fr.math.minecraft.client.texture.Texture;

import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TextureBuilder {

    public static Texture buildDirtBackgroundTexture() {
        try {
            BufferedImage dirtImage = ImageIO.read(new File("res/textures/gui/dirt.png"));
            BufferedImage image = new BufferedImage((int) GameConfiguration.WINDOW_WIDTH, (int) GameConfiguration.WINDOW_HEIGHT, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();

            for (int x = 0; x < image.getWidth(); x += 10 * 15) {
                for (int y = 0; y < image.getHeight(); y += 10 * 15) {
                    g2d.drawImage(dirtImage, x, y, 10 * 15, 10 * 15, null);
                }
            }

            g2d.setColor(Color.BLACK);
            g2d.setComposite(AlphaComposite.SrcOver.derive(.7f));
            g2d.fillRect(0, 0, (int) GameConfiguration.WINDOW_WIDTH, (int) GameConfiguration.WINDOW_HEIGHT);


            File file = new File("res/textures/gui/dirt_background.png");
            ImageIO.write(image, "png", file);
            g2d.dispose();


            return new Texture(file.getPath(), 6);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}

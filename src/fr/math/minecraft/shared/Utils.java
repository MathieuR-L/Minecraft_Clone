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

package fr.math.minecraft.shared;

import fr.math.minecraft.server.MinecraftServer;
import fr.math.minecraft.server.command.Command;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;

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

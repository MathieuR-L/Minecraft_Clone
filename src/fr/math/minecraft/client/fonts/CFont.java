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

package fr.math.minecraft.client.fonts;

import fr.math.minecraft.client.builder.BitmapBuilder;
import fr.math.minecraft.client.texture.Texture;

import java.util.Map;

public class CFont {

    private final String filePath;
    private final int fontSize;
    private int width, height, lineHeight;
    private final Map<Integer, CharInfo> characterMap;
    private Texture texture;

    public CFont(String filePath, int fontSize) {
        this.filePath = filePath;
        this.fontSize = fontSize;
        this.width = 0;
        this.height = 0;
        this.lineHeight = 0;
        this.texture = null;
        this.characterMap = BitmapBuilder.buildBitmap(this);
    }

    public CharInfo getCharacter(int code) {
        return characterMap.getOrDefault(code, new CharInfo(0, 0, 0, 0));
    }

    public String getFilePath() {
        return filePath;
    }

    public int getFontSize() {
        return fontSize;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }

    public Map<Integer, CharInfo> getCharacterMap() {
        return characterMap;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}

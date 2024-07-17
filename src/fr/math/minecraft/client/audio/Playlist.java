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

package fr.math.minecraft.client.audio;

import org.joml.Math;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private final List<Sound> sounds;
    private boolean running;
    private int currentIndex;
    private boolean looping;

    public Playlist(boolean looping) {
        this.sounds = new ArrayList<>();
        this.currentIndex = 0;
        this.running = false;
        this.looping = looping;
    }

    public void start() {
        currentIndex = 0;
        Sound sound = sounds.get(currentIndex);
        sound.play();
        running = true;
    }

    public void next() {
        if (currentIndex + 1 >= sounds.size()) {
            this.stop();
            return;
        }
        currentIndex = Math.clamp(0, sounds.size() - 1, currentIndex + 1);
    }

    public void stop() {
        if (!running) {
            return;
        }
        running = false;
        if (currentIndex >= sounds.size()) {
            return;
        }
        Sound sound = sounds.get(currentIndex);
        sound.stop();
    }

    public void reset() {
        running = false;
        currentIndex = 0;
    }


    public void addSound(Sound sound) {
        sounds.add(sound);
    }

    public Sound getPlayedSound() {
        if (currentIndex >= sounds.size()) {
            return looping ? sounds.get(0) : null;
        }
        return sounds.get(currentIndex);
    }

    public List<Sound> getSounds() {
        return sounds;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }
}

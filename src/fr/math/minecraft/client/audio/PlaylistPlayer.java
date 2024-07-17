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

import fr.math.minecraft.client.Game;
import fr.math.minecraft.client.manager.SoundManager;
import fr.math.minecraft.logger.LogType;
import fr.math.minecraft.logger.LoggerUtility;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.glfw.GLFW.*;

public class PlaylistPlayer extends Thread {

    private final static Logger logger = LoggerUtility.getClientLogger(PlaylistPlayer.class, LogType.TXT);
    private final Playlist playlist;

    public PlaylistPlayer(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public void run() {
        Game game = Game.getInstance();
        playlist.start();
        while (playlist.isRunning()) {

            Sound sound = playlist.getPlayedSound();

            if (sound == null) {
                playlist.stop();
                break;
            }

            logger.info("Currently playing " + sound.getFilePath());
            sound.play();

            while (sound.getState() == AL_PLAYING) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            logger.info("Passage au son suivant.");
            playlist.next();

        }
    }
}

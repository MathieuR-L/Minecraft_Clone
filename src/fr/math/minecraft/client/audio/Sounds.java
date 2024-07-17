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

public enum Sounds {

    SUBWOOFER_LULLABY("res/sounds/music/subwoofer_lullaby.ogg"),
    KEY("res/sounds/music/key.ogg"),
    MINECRAFT("res/sounds/music/minecraft.ogg"),
    SWEDEN("res/sounds/music/sweden.ogg"),
    DANNY("res/sounds/music/danny.ogg"),
    FLOATING_TREES("res/sounds/music/floating_trees.ogg"),
    A_FAMILIAR_ROOM("res/sounds/music/a_familiar_room.ogg"),
    LEFT_TO_BLOOM("res/sounds/music/left_to_bloom.ogg"),
    TASWELL("res/sounds/music/taswell.ogg"),
    CLARK("res/sounds/music/clark.ogg"),
    DRY_HANDS("res/sounds/music/dry_hands.ogg"),
    HAGGSTROM("res/sounds/music/haggstrom.ogg"),
    LIVING_MICE("res/sounds/music/living_mice.ogg"),
    MICE_ON_VENUS("res/sounds/music/mice_on_venus.ogg"),
    OXYGENE("res/sounds/music/oxygene.ogg"),
    WET_HANDS("res/sounds/music/wet_hands.ogg"),
    CLICK("res/sounds/click.ogg"),
    HIT("res/sounds/hit.ogg"),
    DIG_WOOD("res/sounds/blocks/dig/wood2.ogg"),
    DIG_SNOW("res/sounds/blocks/dig/snow1.ogg"),
    GRASS_WALK("res/sounds/blocks/grass_walk.ogg", true),
    POP("res/sounds/pop.ogg");

    private final String filePath;
    private final boolean looping;

    Sounds(String filePath) {
        this(filePath, false);
    }

    Sounds(String filePath, boolean looping) {
        this.filePath = filePath;
        this.looping = looping;
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean isLooping() {
        return looping;
    }
}
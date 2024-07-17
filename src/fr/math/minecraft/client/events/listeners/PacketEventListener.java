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

package fr.math.minecraft.client.events.listeners;

import fr.math.minecraft.client.events.*;

public interface PacketEventListener {

    void onServerState(ServerStateEvent event);
    void onPlayerListPacket(PlayerListPacketEvent event);
    void onSkinPacket(SkinPacketEvent event);
    void onChunkPacket(ChunkPacketEvent event);
    void onPlayerState(PlayerStateEvent event);
    void onDroppedItemState(DroppedItemEvent event);
    void onPlacedBlockState(PlacedBlockStateEvent event);
    void onBrokenBlockState(BrokenBlockStateEvent event);
    void onChatState(ChatPayloadStateEvent event);
    void onLoadMapData(ReceiveMapEvent event);

}

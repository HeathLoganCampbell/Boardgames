package dev.cobblesword.boardgames.room.network.client;

import dev.cobblesword.boardgames.network.packets.Packet;
import lombok.Getter;

@Getter
public class CreateRoomPacket extends Packet
{
    private String username;
}

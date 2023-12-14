package dev.cobblesword.boardgames.room.network.server;

import dev.cobblesword.boardgames.network.packets.Packet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiveMessagePacket extends Packet
{
    private String message;
}

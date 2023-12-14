package dev.cobblesword.boardgames.room.network.client;

import dev.cobblesword.boardgames.network.packets.Packet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessagePacket extends Packet
{
    private String message;
}

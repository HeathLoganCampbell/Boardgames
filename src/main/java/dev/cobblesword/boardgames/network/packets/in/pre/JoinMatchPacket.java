package dev.cobblesword.boardgames.network.packets.in.pre;

import dev.cobblesword.boardgames.network.packets.Packet;
import lombok.Getter;

@Getter
public class JoinMatchPacket extends Packet
{
    private String gameId;
    private String username;
}

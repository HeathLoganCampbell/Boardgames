package dev.cobblesword.boardgames.network.packets.in.game;

import dev.cobblesword.boardgames.network.packets.Packet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeepAlivePacket extends Packet {
    public long timestamp;
}

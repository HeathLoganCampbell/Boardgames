package dev.cobblesword.boardgames.network.packets.in.pre;

import dev.cobblesword.boardgames.network.packets.Packet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUsername extends Packet {
    public String username;
}

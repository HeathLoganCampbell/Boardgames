package dev.cobblesword.boardgames.network.packets.out.pre;

import dev.cobblesword.boardgames.network.packets.Packet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcceptUsername extends Packet {
    public String username;
}

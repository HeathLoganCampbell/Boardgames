package dev.cobblesword.boardgames.room.network.server;

import dev.cobblesword.boardgames.network.packets.Packet;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JoinedRoomPacket extends Packet
{
    private String roomId;
    private String username;
    private List<String> otherPlayers;
}

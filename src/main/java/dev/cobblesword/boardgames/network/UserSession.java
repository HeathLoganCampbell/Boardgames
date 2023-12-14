package dev.cobblesword.boardgames.network;

import com.google.gson.Gson;
import dev.cobblesword.boardgames.network.handlers.PacketRegistry;
import dev.cobblesword.boardgames.network.packets.Packet;
import dev.cobblesword.boardgames.room.network.server.JoinedRoomPacket;
import dev.cobblesword.boardgames.room.network.server.ReceiveMessagePacket;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserSession
{
    private final static Gson gson = new Gson();
    private final Channel channel;

    private String roomId;

    public UserSession(Channel channel)
    {
        this.channel = channel;
    }

    public void sendPacket(Packet packet)
    {
        String packetData = gson.toJson(packet);
        String packetType = PacketRegistry.getNameByClass(packet.getClass());

        TextWebSocketFrame responseFrame = new TextWebSocketFrame("{ type: '" + packetType + "', data: " + packetData + "}");
        channel.writeAndFlush(responseFrame);
    }

    public void enterRoom(String roomId, String username)
    {
        JoinedRoomPacket joinedRoomPacket = new JoinedRoomPacket();
        joinedRoomPacket.setRoomId(roomId);
        joinedRoomPacket.setUsername(username);

        this.sendPacket(joinedRoomPacket);
    }

    public void sendMessage(String message)
    {
        ReceiveMessagePacket receiveMessagePacket = new ReceiveMessagePacket();
        receiveMessagePacket.setMessage(message);

        this.sendPacket(receiveMessagePacket);
    }
}

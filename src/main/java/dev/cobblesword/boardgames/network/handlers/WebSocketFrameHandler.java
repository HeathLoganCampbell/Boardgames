package dev.cobblesword.boardgames.network.handlers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.cobblesword.boardgames.network.UserSession;
import dev.cobblesword.boardgames.network.packets.Packet;
import dev.cobblesword.boardgames.network.packets.in.game.KeepAlivePacket;
import dev.cobblesword.boardgames.network.packets.in.pre.JoinMatchPacket;
import dev.cobblesword.boardgames.room.RoomManager;
import dev.cobblesword.boardgames.room.network.RoomPacketRegistry;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private final Gson gson = new Gson();
    private final JsonParser parser = new JsonParser();

    private static final ConcurrentMap<ChannelId, UserSession> activeUsers = new ConcurrentHashMap<>();

    private RoomManager roomManager;

    public WebSocketFrameHandler(RoomManager roomManager)
    {
        this.roomManager = roomManager;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        UserSession userSession = new UserSession(ctx.channel());
        activeUsers.put(ctx.channel().id(), userSession);

        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ChannelId channelId = ctx.channel().id();

        UserSession userSession = activeUsers.get(channelId);
        System.out.println(userSession.getUsername() + " has left");
        activeUsers.remove(channelId);

        super.channelInactive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if (frame instanceof TextWebSocketFrame textFrame) {
            String text = textFrame.text();

            JsonObject jsonObject = parser.parse(text).getAsJsonObject();
            String type = jsonObject.get("type").getAsString();

            JsonElement data = jsonObject.get("data");
            System.out.println("=> " + type);

            String area = PacketRegistry.getAreaByType(type);
            Packet packet = PacketRegistry.getPacketByType(type, data);

            ChannelId id = ctx.channel().id();
            UserSession userSession = activeUsers.get(id);

            if(area.equals(RoomPacketRegistry.AREA_ID))
            {
                roomManager.processPacket(userSession, packet);
            }


            // Now you can use packet.getGameId() and packet.getUsername()
        } else if (frame instanceof CloseWebSocketFrame) {
            ctx.close();
        }
    }
}
package dev.cobblesword.boardgames.room.network;

import dev.cobblesword.boardgames.network.handlers.PacketRegistry;
import dev.cobblesword.boardgames.network.packets.Packet;
import dev.cobblesword.boardgames.room.network.client.CreateRoomPacket;
import dev.cobblesword.boardgames.room.network.client.JoinRoomPacket;
import dev.cobblesword.boardgames.room.network.client.SelectGameTypePacket;
import dev.cobblesword.boardgames.room.network.client.SendMessagePacket;
import dev.cobblesword.boardgames.room.network.server.JoinedRoomPacket;
import dev.cobblesword.boardgames.room.network.server.ReceiveMessagePacket;
import dev.cobblesword.boardgames.room.network.server.UpdatedGameTypePacket;

public class RoomPacketRegistry
{
    public static String AREA_ID = "ROOM";

    public static void init()
    {
        PacketRegistry.registerPacket(AREA_ID, "CREATE_ROOM", CreateRoomPacket.class);
        PacketRegistry.registerPacket(AREA_ID, "JOIN_ROOM", JoinRoomPacket.class);
        PacketRegistry.registerPacket(AREA_ID, "SELECT_GAME_TYPE", SelectGameTypePacket.class);
        PacketRegistry.registerPacket(AREA_ID, "SEND_MESSAGE", SendMessagePacket.class);

        PacketRegistry.registerPacket(AREA_ID, "JOINED_ROOM", JoinedRoomPacket.class);
        PacketRegistry.registerPacket(AREA_ID, "RECEIVE_MESSAGE", ReceiveMessagePacket.class);
        PacketRegistry.registerPacket(AREA_ID, "UPDATED_GAME_TYPE", UpdatedGameTypePacket.class);
    }
}

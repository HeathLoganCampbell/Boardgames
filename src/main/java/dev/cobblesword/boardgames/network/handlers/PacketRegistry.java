package dev.cobblesword.boardgames.network.handlers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import dev.cobblesword.boardgames.network.packets.Packet;
import dev.cobblesword.boardgames.network.packets.PacketType;
import dev.cobblesword.boardgames.network.packets.in.game.KeepAlivePacket;
import dev.cobblesword.boardgames.network.packets.in.pre.JoinMatchPacket;

import java.util.HashMap;

public class PacketRegistry {
    private final static Gson gson = new Gson(); // Create a Gson instance

    private static final HashMap<String, Class<? extends Packet>> packetClasses = new HashMap<>();

    private static final HashMap<Class<? extends Packet>, String> classToName = new HashMap<>();

    public static void registerPacket(String area, String typeId, Class<? extends Packet> packetClass) {
        String name = area + "-" + typeId;
        packetClasses.put(name, packetClass);
        classToName.put(packetClass, name);
    }

    public static String getAreaByType(String type) {
        String[] split = type.split("-");
        return split[0];
    }

    public static Packet getPacketByType(String type, JsonElement data) {
        Class<? extends Packet> aClass = packetClasses.get(type);
        if(aClass != null)
        {
            return gson.fromJson(data, aClass);
        }


//        if (type.equalsIgnoreCase(PacketType.FromClient.JOIN_MATCH)) {
//            JoinMatchPacket joinMatchPacket = gson.fromJson(data, JoinMatchPacket.class);
//            return joinMatchPacket;
//        }
//
//        if (type.equalsIgnoreCase(PacketType.FromClient.KEEP_ALIVE)) {
//            KeepAlivePacket packet = gson.fromJson(data, KeepAlivePacket.class);
//            return packet;
//        }

        throw new UnsupportedOperationException("Unknown packet type " + type);
    }

    public static String getNameByClass(Class<? extends Packet> aClass) {
        return classToName.get(aClass);
    }
}

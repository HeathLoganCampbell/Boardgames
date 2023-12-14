package dev.cobblesword.boardgames.network.packets.in.game;

import dev.cobblesword.boardgames.network.packets.Packet;

public class LaunchMissilePacket extends Packet
{
    private int gameId;
    private int x, y;
}

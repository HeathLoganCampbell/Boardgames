package dev.cobblesword.boardgames.room;

import dev.cobblesword.boardgames.games.Game;
import dev.cobblesword.boardgames.games.GameType;
import dev.cobblesword.boardgames.network.UserSession;
import dev.cobblesword.boardgames.network.packets.Packet;
import dev.cobblesword.boardgames.room.helper.HashGenerator;
import dev.cobblesword.boardgames.room.network.RoomPacketRegistry;
import dev.cobblesword.boardgames.room.network.client.CreateRoomPacket;
import dev.cobblesword.boardgames.room.network.client.JoinRoomPacket;
import dev.cobblesword.boardgames.room.network.client.SelectGameTypePacket;
import dev.cobblesword.boardgames.room.network.client.SendMessagePacket;
import dev.cobblesword.boardgames.room.network.server.JoinedRoomPacket;
import dev.cobblesword.boardgames.room.network.server.UpdatedGameTypePacket;

import java.util.HashMap;

public class RoomManager
{
    private HashMap<String, GameRoom> rooms = new HashMap<>();

    public RoomManager()
    {
        RoomPacketRegistry.init();
    }

    public GameRoom createRoom()
    {
        GameRoom gameRoom = new GameRoom();

        String roomId = HashGenerator.generateShortHash();
        gameRoom.setRoomId(roomId);
        rooms.put(roomId, gameRoom);

        System.out.println("Room] Created room " + roomId);

        return gameRoom;
    }

    public GameRoom getRoom(String roomId)
    {
        return this.rooms.get(roomId);
    }

    public void processPacket(UserSession userSession, Packet packet)
    {
        if(packet instanceof CreateRoomPacket)
        {
            CreateRoomPacket createRoomPacket = (CreateRoomPacket) packet;
            String username = createRoomPacket.getUsername();

            GameRoom room = createRoom();

            Player player = new Player(userSession, username);

            room.addPlayer(player);
        }

        if(packet instanceof JoinRoomPacket)
        {
            JoinRoomPacket joinRoomPacket = (JoinRoomPacket) packet;
            String username = joinRoomPacket.getUsername();
            String roomId = joinRoomPacket.getRoomId();

            GameRoom room = getRoom(roomId);

            Player player = new Player(userSession, username);

            room.addPlayer(player);
        }

        if(packet instanceof SelectGameTypePacket)
        {
            SelectGameTypePacket selectGameTypePacket = (SelectGameTypePacket) packet;
            String gameType = selectGameTypePacket.getGameType();

            GameRoom room = getRoom(userSession.getRoomId());

            room.setGameType(GameType.TIC_TAC_TOE);

            UpdatedGameTypePacket updatedGameTypePacket = new UpdatedGameTypePacket();
            updatedGameTypePacket.setGameType(room.getGameType().toString());

            userSession.sendPacket(updatedGameTypePacket);
        }

        if(packet instanceof SendMessagePacket)
        {
            SendMessagePacket sendMessagePacket = new SendMessagePacket();
            String message = sendMessagePacket.getMessage();


        }
    }
}

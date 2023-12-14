package dev.cobblesword.boardgames.room;

import dev.cobblesword.boardgames.games.GameType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GameRoom
{
    private String roomId;

    private GameType gameType = GameType.NONE;

    private List<Player> players = new ArrayList<>();

    public void addPlayer(Player player)
    {
        this.players.add(player);
        player.setRoom(roomId);
    }

    public void broadcastMessage(String message)
    {
        for (Player player : this.players)
        {
            player.sendMessage(message);
        }
    }
}

package dev.cobblesword.boardgames.room;

import dev.cobblesword.boardgames.network.UserSession;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player
{
    private UserSession userSession;

    private String username;

    public Player(UserSession userSession, String username) {
        this.userSession = userSession;
        this.username = username;
    }

    public void setRoom(String roomId)
    {
        userSession.enterRoom(roomId, this.username);
    }

    public void sendMessage(String message)
    {
        userSession.sendMessage(message);
    }
}

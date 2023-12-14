package dev.cobblesword.boardgames.network.packets;

public class PacketType
{
    public static class FromClient
    {
        public static String REQUESTED_USERNAME = "requestUsername";

        public static String KEEP_ALIVE = "keepAlive";

        public static String CREATE_MATCH = "createMatch";
        public static String JOIN_MATCH = "joinMatch";

        public static String REQUEST_SHIP_PLACEMENT = "requestShipPlacement";

        public static String DELETE_SHIP_PLACEMENT = "deleteShipPlacement";

        public static String READY_TO_PLAY = "readyToPlay";


        public static String LAUNCH_MISSILE = "launchMissile";
    }

    public static class FromServer
    {
        public static String ACCEPTED_USERNAME = "acceptedUsername";

        public static String PLAYER_JOINED_MATCH = "playerJoinedMatch";

        public static String ACCEPT_SHIP_PLACEMENT = "acceptShipPlacement";

        public static String YOUR_TURN = "yourTurn";

        public static String MISSILE_DETECTED = "missileDetected";

        public static String SHIP_DAMAGED = "shipDamaged";

        public static String GAME_OVER = "gameOver";
    }
}

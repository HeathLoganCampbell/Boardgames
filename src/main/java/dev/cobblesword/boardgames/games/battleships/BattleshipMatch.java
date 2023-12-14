package dev.cobblesword.boardgames.games.battleships;

import dev.cobblesword.boardgames.games.battleships.helpers.Direction;
import dev.cobblesword.boardgames.games.battleships.helpers.Location;
import dev.cobblesword.boardgames.games.battleships.ships.Ship;
import dev.cobblesword.boardgames.games.battleships.ships.ShipSize;

public class BattleshipMatch
{
    private Map player1Map, player2Map;

    public void setUpGame()
    {
        int size = 20;

        this.player1Map = new Map(size, size);
        this.player2Map = new Map(size, size);

        this.player1Map.addShip(new Ship(Direction.HORIZONTAL, ShipSize.BATTLESHIP_SHIP, new Location(5, 5)));
        this.player1Map.addShip(new Ship(Direction.VERTICAL, ShipSize.DESTROYER_SHIP, new Location(5, 10)));
        this.player1Map.addShip(new Ship(Direction.VERTICAL, ShipSize.DESTROYER_SHIP, new Location(8, 11)));
    }

    public void player1Turn()
    {
    }

    public void player2Turn()
    {

    }

    public boolean hasVictor()
    {
        if (this.player1Map.isAllShipsSunk())
        {
            return true;
        }

        if (this.player2Map.isAllShipsSunk())
        {
            return true;
        }

        return false;
    }

    public void victory()
    {
        System.out.println("Victory");
    }

    public void launchMissile(int x, int y)
    {
        this.player1Map.launchMissile(x, y);
    }

    public void draw()
    {
        this.player1Map.draw();
    }

    public void run()
    {
        setUpGame();

        while(!hasVictor())
        {
            player1Turn();
            player2Turn();
        }

        victory();
    }
}

package dev.cobblesword.boardgames.games.battleships;

import dev.cobblesword.boardgames.games.battleships.helpers.Location;
import dev.cobblesword.boardgames.games.battleships.helpers.TargetedLocation;
import dev.cobblesword.boardgames.games.battleships.ships.Ship;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Getter
public class Map
{
    private int width, height;

    private List<Ship> ships;

    private List<TargetedLocation> resultTargetedLocations;

    private HashSet<Location> targetLocations;

    public Map(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.ships = new ArrayList<>();
        this.resultTargetedLocations = new ArrayList<>();
        this.targetLocations = new HashSet<>();
    }

    public boolean isAreaAvailable()
    {
        return true;
    }

    public boolean isAllShipsSunk()
    {
        for (Ship ship : this.getShips())
        {
            if (!ship.isSunk()) {
                return false;
            }
        }

        return true;
    }

    public void addShip(Ship ship)
    {
        this.ships.add(ship);
    }

    public Ship getShipAtLocation(int x, int y)
    {
        for (Ship ship : this.ships)
        {
            if (ship.intersect(x, y))
            {
                return ship;
            }
        }

        return null;
    }

    public void launchMissile(int x, int y)
    {
        if(targetLocations.contains(new Location(x, y)))
        {
            throw new RuntimeException("Shouldn't hit this again!");
        }

        Ship shipHit = getShipAtLocation(x, y);
        boolean isHit = shipHit != null;

        if(isHit)
        {
            shipHit.damageShip(x , y);
        }

        this.targetLocations.add(new Location(x, y));
        this.resultTargetedLocations.add(new TargetedLocation(new Location(x, y), isHit));
    }

    private boolean isMissedLocation(int x, int y)
    {
        for (TargetedLocation resultTargetedLocation : resultTargetedLocations)
        {
            if(resultTargetedLocation.getLocation().equals(new Location(x, y)))
            {
                return true;
            }
        }

        return false;
    }

    public void draw()
    {
        for (int x = 0; x < width + 2; x++)
        {
            System.out.print("-");
        }

        System.out.println();

        for (int y = 0; y < height; y++) {
            System.out.print("|");

            for (int x = 0; x < width; x++)
            {
                Ship shipAtLocation = this.getShipAtLocation(x, y);
                String locChar = ".";

                if (isMissedLocation(x, y)) {
                    locChar = ",";
                }


                if(shipAtLocation != null)
                {
                    if(!shipAtLocation.isDamageAtLocation(x, y))
                    {
                        locChar = "O";
                    }
                    else if(shipAtLocation.isSunk())
                    {
                        locChar = "D";
                    }
                    else
                    {
                        locChar = "X";
                    }
                }


                System.out.print(locChar);
            }

            System.out.println("|");
        }

        for (int x = 0; x < width + 2; x++)
        {
            System.out.print("-");
        }

        System.out.println();
    }
}

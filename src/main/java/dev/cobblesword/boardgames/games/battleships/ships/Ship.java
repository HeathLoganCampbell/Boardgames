package dev.cobblesword.boardgames.games.battleships.ships;

import dev.cobblesword.boardgames.games.battleships.helpers.Direction;
import dev.cobblesword.boardgames.games.battleships.helpers.Location;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Ship
{
    private Direction direction;
    private int size;
    private Location location;

    private boolean sunk;

    private HashSet<Location> partsHit;

    public Ship(Direction direction, int size, Location location)
    {
        this.direction = direction;
        this.size = size;
        this.location = location;
        this.partsHit = new HashSet<>();
        this.sunk = false;
    }

    public boolean intersect(int x, int y)
    {
        if(direction == Direction.HORIZONTAL)
        {
            boolean withinX = this.location.getX() <= x && x < this.location.getX() + this.size;
            boolean withinY = this.location.getY() <= y && y <= this.location.getY();
            return withinX && withinY;
        }

        if(direction == Direction.VERTICAL)
        {
            boolean withinX = this.location.getX() <= x && x <= this.location.getX();
            boolean withinY = this.location.getY() <= y && y < this.location.getY() + this.size;
            return withinX && withinY;
        }

        throw new UnsupportedOperationException(direction + " unknown");
    }

    public List<Location> getShipPartsLocation()
    {
        if(direction == Direction.HORIZONTAL)
        {
            List<Location> locations = new ArrayList<>();

            for (int i = 0; i < size; i++)
            {
                locations.add(new Location(this.location.getX() + i, this.location.getY()));
            }

            return locations;
        }

        if(direction == Direction.VERTICAL)
        {
            List<Location> locations = new ArrayList<>();

            for (int i = 0; i < size; i++)
            {
                locations.add(new Location(this.location.getX(), this.location.getY() + i));
            }

            return locations;
        }

        throw new UnsupportedOperationException(direction + " unknown");
    }

    public boolean isSunk()
    {
        for (Location shipPartLoc : this.getShipPartsLocation())
        {
            boolean hasBeenHit = this.partsHit.contains(shipPartLoc);
            if(!hasBeenHit)
            {
                return false;
            }
        }

        return true;
    }

    public boolean isDamageAtLocation(int x, int y)
    {
        return this.partsHit.contains(new Location(x, y));
    }

    public void damageShip(int x, int y)
    {
        Location location = new Location(x, y);
        partsHit.add(location);

        if(isSunk())
        {
            sunk = true;
        }
    }
}

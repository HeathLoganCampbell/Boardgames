package dev.cobblesword.boardgames.games.battleships.helpers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location
{
    private int x, y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return x | (y << 15);
    }

    @Override
    public boolean equals(Object obj)
    {
        // Check if the object is compared with itself
        if (this == obj) {
            return true;
        }

        // Check if the object is an instance of Location
        if (!(obj instanceof Location)) {
            return false;
        }

        // Typecast obj to Location so that we can compare data members
        Location other = (Location) obj;

        // Compare the data members and return accordingly
        return this.x == other.x && this.y == other.y;
    }
}

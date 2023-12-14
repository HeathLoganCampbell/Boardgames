package dev.cobblesword.boardgames.games.battleships.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TargetedLocation
{
    private Location location;
    private boolean hit;
}

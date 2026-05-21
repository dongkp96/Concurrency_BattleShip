package GameLogic;

public enum CellState{
    HIT,
    MISS,
    EMPTY,
    SHIP;

}

/**
 * The CellState enum is used to create the board's string representation
 * and used in a 2D array comprised of these CellStates to serve as 
 * the board itself during game play and is used to check hits, misses and place ships.
 * Used in the getBoard() toString method, getHitMapDisplay() toString method,
 * processShot(), and placeShip()
 */
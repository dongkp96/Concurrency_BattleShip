package GameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerBoardTest {

    PlayerBoard testBoard;
    @BeforeEach
    void createboard(){
        testBoard = new PlayerBoard();
        testBoard.placeShip(new int[] {0,0}, Direction.VERTICAL,ShipType.DESTROYER);
    }

    @Test
    void isReady_false() {
        assertFalse(testBoard.isReady());
    }

    @Test
    void isReady_true() {
        testBoard.placeShip(new int[]{0,1}, Direction.VERTICAL, ShipType.CRUISER);
        testBoard.placeShip(new int[]{0,2}, Direction.VERTICAL, ShipType.BATTLESHIP);
        testBoard.placeShip(new int[]{0,3}, Direction.VERTICAL, ShipType.SUBMARINE);
        testBoard.placeShip(new int[]{0,4}, Direction.VERTICAL, ShipType.CARRIER);
        assertTrue(testBoard.isReady());
    }

    @Test
    void isAllSunk_false() {
        assertFalse(testBoard.isAllSunk());
    }

    @Test
    void isAllSunk_true(){
        testBoard.processShot(new int[]{0,0});
        testBoard.processShot(new int[]{1,0});
        assertTrue(testBoard.isAllSunk());
    }

    @Test
    void processShot_hit() {
        assertEquals("HIT", testBoard.processShot(new int[]{0,0}));
    }

    @Test
    void processShot_miss() {
        assertEquals("MISS", testBoard.processShot(new int[]{8,8}));
    }

    @Test
    void processShot_sunk(){
        testBoard.processShot(new int[]{0,0});
        assertEquals("SUNK:DESTROYER", testBoard.processShot(new int[]{1,0}));

    }

    @Test
    void processShot_invalid_moveLength(){
        assertEquals("INVALID", testBoard.processShot(new int[] {0,0,0}));
    }

    @Test
    void processShot_invalid_outOfBounds(){
        assertEquals("INVALID", testBoard.processShot(new int []{10,10}));
    }

    @Test
    void processShot_invalid_repeat(){
        testBoard.processShot(new int[] {0,0});
        assertEquals("INVALID", testBoard.processShot(new int[] {0,0}));
    }

    @Test
    void placeShip_true() {
        assertTrue(testBoard.placeShip(new int[]{1,1}, Direction.HORIZONTAL, ShipType.CRUISER));
    }

    @Test
    void placeShip_false_previouslyPlaced(){
        assertFalse(testBoard.placeShip(new int[]{0,0}, Direction.HORIZONTAL, ShipType.CRUISER));
    }

    @Test
    void placeShip_false_repeatType(){
        assertFalse(testBoard.placeShip(new int[]{2,2,}, Direction.VERTICAL, ShipType.DESTROYER));
    }
}
package GameLogic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ShipTest {
    Ship testShip;

    @BeforeEach
    void createShip(){
        ArrayList<int[]> testingCoordinates = new ArrayList<>();
        int [] coordinates = {2,3};
        int [] coordinates2 = {2,4};
        testingCoordinates.add(coordinates);
        testingCoordinates.add(coordinates2);
        this.testShip = new Ship(ShipType.DESTROYER, testingCoordinates);
    }

    @Test
    void checkIsSunk_returnsFalse_allCellsNotHit() {
        assertFalse(this.testShip.checkIsSunk());
    }


    @Test
    void checkIsSunk_returnsTrue_allCellsHit(){
        this.testShip.checkIfHit(new int[] {2,3});
        this.testShip.checkIfHit(new int[] {2,4});
        assertTrue(this.testShip.checkIsSunk());

    }


    @Test
    void checkIfHit() {
        assertTrue(this.testShip.checkIfHit(new int[]{2,3}));
    }

    @Test
    void checkIfHit_missCase(){
        assertFalse(this.testShip.checkIfHit(new int[] {3,4}));
    }

    @Test
    void checkShipType_DESTROYER(){
        assertEquals(ShipType.DESTROYER, this.testShip.getType());
    }
}
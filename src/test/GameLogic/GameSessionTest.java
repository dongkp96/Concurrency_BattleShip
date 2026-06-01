package GameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSessionTest {

    private GameSession game;
    private PlayerBoard whiteBoard;
    private PlayerBoard blackBoard;

    @BeforeEach
    void createGame(){
        this.game = new GameSession();
        this.whiteBoard = new PlayerBoard();
        this.blackBoard = new PlayerBoard();
        whiteBoard.placeShip(new int[]{0,0}, Direction.VERTICAL, ShipType.DESTROYER);
        blackBoard.placeShip(new int[]{0,0}, Direction.VERTICAL, ShipType.DESTROYER);

        this.game.assignBoard(whiteBoard, Color.WHITE);
        this.game.assignBoard(blackBoard,Color.BLACK);
    }

    @Test
    void isGameOver_false(){
        assertFalse(this.game.isGameOver());
    }

    @Test
    void isGameOver_true(){
        this.game.endGame();
        assertTrue(this.game.isGameOver());
    }

    @Test
    void isTurn_black_true(){
        assertEquals(Color.BLACK, this.game.getTurn());
    }

    @Test
    void isTurn_black_false(){
        this.game.switchTurn();
        assertNotEquals(Color.BLACK, this.game.getTurn());
    }

    @Test
    void isTurn_white_true(){
        this.game.switchTurn();
        assertEquals(Color.WHITE, this.game.getTurn());
    }

    @Test
    void processShot_playerBlack(){
        int [] shot = {0,0};
        this.game.processShot(shot, Color.BLACK);
        assertEquals("INVALID",this.whiteBoard.processShot(shot));

    }

    @Test
    void processShot_playerWhite(){
        int [] shot = {0,0};
        this.game.processShot(shot, Color.WHITE);
        assertEquals("INVALID",this.blackBoard.processShot(shot));


    }

    @Test
    void getWinner_black(){
        int[] shot1 = {0,0};
        int[] shot2 = {1,0};
        this.game.processShot(shot1, Color.BLACK);
        this.game.processShot(shot2, Color.BLACK);
        assertEquals(Color.BLACK, this.game.getWinner());
    }

    @Test
    void getWinner_white(){
        int[] shot1 = {0,0};
        int[] shot2 = {1,0};
        this.game.processShot(shot1, Color.WHITE);
        this.game.processShot(shot2, Color.WHITE);
        assertEquals(Color.WHITE, this.game.getWinner());
    }

    @Test
    void getWinner_null(){
        assertNull(this.game.getWinner());
    }

    @Test
    void bothBoardsReady_false(){
        assertFalse(this.game.bothBoardsReady());
    }

    @Test
    void bothBoardsReady_true(){
        this.whiteBoard.placeShip(new int[]{0,1},Direction.VERTICAL, ShipType.CRUISER);
        this.whiteBoard.placeShip(new int[]{0,2},Direction.VERTICAL, ShipType.CARRIER);
        this.whiteBoard.placeShip(new int[]{0,3},Direction.VERTICAL, ShipType.SUBMARINE);
        this.whiteBoard.placeShip(new int[]{0,4},Direction.VERTICAL, ShipType.BATTLESHIP);
        this.blackBoard.placeShip(new int[]{0,1},Direction.VERTICAL, ShipType.CRUISER);
        this.blackBoard.placeShip(new int[]{0,2},Direction.VERTICAL, ShipType.CARRIER);
        this.blackBoard.placeShip(new int[]{0,3},Direction.VERTICAL, ShipType.SUBMARINE);
        this.blackBoard.placeShip(new int[]{0,4},Direction.VERTICAL, ShipType.BATTLESHIP);

        assertTrue(this.game.bothBoardsReady());

    }


}
package GameLogic;
import java.util.HashMap;

/**
 * GameSession class is responsible for holding the player boards in a HashMap 
 * to differentiate which player owns which board, a turn indicator using the Color Enum, and 
 * a boolean to check if the game is over.
 * It works with client handler that will pass an input in to process a shot, which will also route 
 * to the PlayerBoard class. Also has the responsibility of assigning the boards, switching turns,
 * checking the turns, and checking if both boards are ready for game play. 
 */
public class GameSession {
    
    private HashMap<Color, PlayerBoard> playerBoards;
    private Color turn;
    private boolean gameOver;

    public GameSession(){
        this.turn = Color.BLACK;
        this.gameOver = false;
        this.playerBoards = new HashMap<>();
        

    }


    /**
     * @return boolean to check if the game is over
     */
    public boolean isGameOver(){
        return this.gameOver;
    }

    /**
     * 
     * @param shot String representation of the coordinates 
     * @param playerColor Color enum denoting which player's move it is
     * @return String that indicates if it was a hit, miss, or invalid move
     */
    public String processShot(int[] move, Color playerColor){
        if(playerColor == Color.BLACK){
            return playerBoards.get(Color.WHITE).processShot(move);
        }else{
            return playerBoards.get(Color.BLACK).processShot(move);
        }

    }

    /**
     * @return boolean that indicates if both boards are ready
     */
    public boolean bothBoardsReady(){
        boolean isReady = false;
        if(playerBoards.get(Color.BLACK)!= null && playerBoards.get(Color.WHITE) != null){
            isReady= playerBoards.get(Color.BLACK).isReady() && playerBoards.get(Color.WHITE).isReady();
        }
        return isReady;
    }

    /**
     * @param board that is the respective player's board
     * @param player representing the color that indicates that player
     * Assigns boards per colors to each board
     */
    public void assignBoard(PlayerBoard board, Color player){
        playerBoards.putIfAbsent(player, board);
    }

    /**
     * 
     * @return Color enum indicating whose turn it is 
     */
    public Color checkTurn(){
        return this.turn;
    }

    /**
     * Switches the turn for the gameSession
     */
    public void switchTurn(){
        this.turn = (this.turn == Color.BLACK) ? Color.WHITE: Color.BLACK;
        //the turn equals conditional checking whose turn + ternary to switch if to White or Black
    }




}

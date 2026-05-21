package GameLogic;
import java.util.HashMap;

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
        }else if (playerBoards.get(Color.BLACK) == null || playerBoards.get(Color.WHITE) == null ){

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

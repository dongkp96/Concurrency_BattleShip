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
    public synchronized String processShot(int[] move, Color playerColor){
        String result = "";
        if(playerColor == Color.BLACK){
            result = playerBoards.get(Color.WHITE).processShot(move);
            if(playerBoards.get(Color.WHITE).isAllSunk()){
                this.gameOver = true;
                notifyAll();
            }
        }else{
            result = playerBoards.get(Color.BLACK).processShot(move);
            if(playerBoards.get(Color.BLACK).isAllSunk()){
                this.gameOver = true;
                notifyAll();
            }
        }
        return result;


    }

    /**
     * @return Color enum indicating the winner, but if game over is not had then
     * returns null
     * */
    public Color getWinner(){
        if(!this.gameOver){
            return null;
        }
        if(playerBoards.get(Color.BLACK).isAllSunk()){
            return Color.WHITE;
        }else{
            return Color.BLACK;
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

    /***
     *
     * @return Color that is the current turn
     */
    public Color getTurn(){
        return this.turn;
    }
    /**
     * 
     * Checks the turn and if it isn't the player's turn then causes them to wait()
     */
    public synchronized void checkTurn(Color playerColor){
        try{
            while(this.turn != playerColor && !this.gameOver){
                wait();
            }
        }catch(InterruptedException e){
            System.out.println(e);
        }
  
    }


    /**
     * Switches the turn for the gameSession and notifies all
     */
    public synchronized void switchTurn(){
        this.turn = (this.turn == Color.BLACK) ? Color.WHITE: Color.BLACK;
        notifyAll();
        //the turn equals conditional checking whose turn + ternary to switch if to White or Black
    }

    /**
     * Method to end the game and notifyAll monitoring the gameOver boolean
     */
    public synchronized void endGame(){
        this.gameOver = true;
        notifyAll();
    }


}

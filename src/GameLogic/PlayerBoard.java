package GameLogic;

public class PlayerBoard {
    
    private Ship[] shipList;
    private CellState[][] gameBoard;

    public PlayerBoard(){
        this.shipList = new Ship[5];
        //ship list is 5 length array to contain the 5 ships 

        this.gameBoard = new CellState[10][10];
        for(int i = 0; i< this.gameBoard.length; i++){
            for(int j = 0; j < this.gameBoard[i].length; j++){
                this.gameBoard[i][j] = CellState.EMPTY;
            }
        }
        // loops 10x10 2D array for gameboard and sets every slot to empty
    }

    /**
    *@return boolean to indicate if the playerboard is ready by checking if 
    the ships have all been placed 
    */
    public boolean isReady(){
        if(this.shipList.length == 5){
            return true;
        }
        return false;
    }

    /**
     * @param intakes an int array containing 2 integers serving as move coordinates
     * @return a boolean to indicate if the 
     */
    public boolean processShot(int[] move){
        return false;
    }

    /**
     * @param takes 3 parameters including an int array containing 2 integers, a 
     * Direction enum to indicate direction the ship is to be placed, and a Ship object
     * to be placed
     * @return boolean to indicate if placing the ship on the desired coordinates was
     * successful or not 
     */
    public boolean placeShip(int[] firstCoordinate, Direction direction, Ship ship){
        if(this.isReady()){
            return false;
        }
        return false;
    }

    /** 
     * @return String to represent the current board 
    */
    public String getBoard(){
        return " ";
    }


}

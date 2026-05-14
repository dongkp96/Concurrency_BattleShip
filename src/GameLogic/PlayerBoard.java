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
        // loops 10x10 2D array for gameboard and sets every slot to empty, gameBoard is accessed via [row][col]
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
        if(move.length > 2){
            return false;
            //checks if move coordinates entered were against the rules
        }

        if(move[0] < 0 || move[0] > (this.gameBoard.length-1)){
            return false;
            //checks if the first move coordinate is within game board bounds of the rows
        }else if(move[1] < 0 || move[1]> (this.gameBoard[0].length-1)){
            return false;
            //checks if the second move coordinate is within game board bounds of the columns
        }

        if(this.gameBoard[move[0]][move[1]] == CellState.EMPTY){
            this.gameBoard[move[0]][move[1]] = CellState.MISS;
            return true;
            //Checks if the CellState is empty, which means the player missed
        }else if(this.gameBoard[move[0]][move[1]] == CellState.SHIP){
            this.gameBoard[move[0]][move[1]] = CellState.HIT;
            for(Ship ship : this.shipList){
                if(ship.checkIfHit(move)== true){
                    break;
                }
            }
            return true;
            /*
            *Checks if the move hits a ship, if so then it will loop
            *through the shipList and call the checkIfHit() to process the logic
            *needed for a hit
            */
        }
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
            //if the array is full of the ships we need, then this stops it
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

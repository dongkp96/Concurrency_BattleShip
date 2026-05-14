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

        if(firstCoordinate.length > 2){
            return false;
            //checks if move coordinates entered were against the rules
        }

        if(firstCoordinate[0] < 0 || firstCoordinate[0] > (this.gameBoard.length-1)){
            return false;
            //checks if the first move coordinate is within game board bounds of the rows (0-9)
        }else if(firstCoordinate[1] < 0 || firstCoordinate[1]> (this.gameBoard[0].length-1)){
            return false;
            //checks if the second move coordinate is within game board bounds of the columns (0-9)
        }


        if(direction == direction.VERTICAL){
            if(((firstCoordinate[0])+(ship.getShipSize()-1))>=this.gameBoard.length){
                return false;
                //firstCoordinate is 7, ship size is 5 - 1 = 4 remaining slots = 7+4 = 11 out of bounds vertically
                //firstCoordinate is 6, ship size is 5 - 1 - 4  remaing slots = 6+4 = 10 out of bounds vertically
                //firstCoordinate is 5, ship size is 5 - 1 = 4 remaining slots = 5+4 = 9 = in bounds 
                //grid is a 10x10 and with a 2D array, its 0-9 for the 10 slots 
            }

            for(int i = firstCoordinate[0]; i < this.gameBoard.length; i++){
                if(this.gameBoard[i][firstCoordinate[1]] == CellState.SHIP){
                    return false; 
                    //loops through that column and sees if there is any ship in those desired coordinates
                }
            }
            
            int shipParts = ship.getShipSize();
            for(int i = firstCoordinate[0]; i < this.gameBoard.length; i++){
                 this.gameBoard[i][firstCoordinate[1]] = CellState.SHIP;
                 shipParts -= 1;
                 if(shipParts == 0){
                    break;
                 }
            }
            //used to place the ships since all checks have been made
            return true;


            

            
        }else{
            if((firstCoordinate[1]+(ship.getShipSize()-1))>=this.gameBoard[firstCoordinate[0]].length){
                return false;
                //firstCoordinate, 2nd part is 6, ship size = 5-1 =4, remaining slots = 6+4 = 10, out of bounds horizontally
            }
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

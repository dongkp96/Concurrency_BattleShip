package GameLogic;
import java.util.ArrayList;

/**
 * Note:
 * Responsibility of this PlayerBoard class is to process shots, check if the board is
 * ready during set up, placing the ships on the board, and returning a representation
 * of the player's board with ships and their hit map.
 * This serves as the entity for those actions with placing the ships and returning the 
 * board and hit-map likely to the client handler/client. As well as processing shots
 * that have been inputted into the Game Session.
 */
public class PlayerBoard {
    
    private ArrayList<Ship>shipList;
    private CellState[][] gameBoard;

    public PlayerBoard(){
        this.shipList = new ArrayList<>();
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
        if(this.shipList.size() == 5){
            return true;
        }
        return false;
    }

    /**
     * @return boolean that indicates if ships are all sunk
     */
    public boolean isAllSunk(){
        boolean allSunk = true;
        if(this.shipList.isEmpty()){
            return false;
        }
        for(Ship ship : shipList){
            if(!ship.checkIsSunk()){
                allSunk = false;
            }
        }
        return allSunk;
    }

    /**
     * @param intakes an int array containing 2 integers serving as move coordinates
     * @return a String to be processed by the clientHandler 
     */
    public String processShot(int[] move){
        if(move.length > 2){
            return "INVALID";
            //checks if move coordinates entered were against the rules
        }

        if(move[0] < 0 || move[0] > (this.gameBoard.length-1)){
            return "INVALID";
            //checks if the first move coordinate is within game board bounds of the rows
        }else if(move[1] < 0 || move[1]> (this.gameBoard[0].length-1)){
            return "INVALID";
            //checks if the second move coordinate is within game board bounds of the columns
        }

        if(this.gameBoard[move[0]][move[1]] == CellState.EMPTY){
            this.gameBoard[move[0]][move[1]] = CellState.MISS;
            return "MISS";
            //Checks if the CellState is empty, which means the player missed
        }else if(this.gameBoard[move[0]][move[1]] == CellState.SHIP){
            this.gameBoard[move[0]][move[1]] = CellState.HIT;
            for(Ship ship : this.shipList){
                if(ship.checkIfHit(move)==true){
                    if(ship.checkIsSunk()){
                        return "SUNK:" + ship.getType();
                    }
                    break;
                }
            }

            return "HIT";
            /*
            *Checks if the move hits a ship, if so then it will loop
            *through the shipList and call the checkIfHit() to process the logic
            *needed for a hit
            */
        }
        return "INVALID";
        /*If the coordinates chosen does fall within bounds, but is not an EMPTY or SHIP cell state 
        *then it falls into a repeat shot. 
        */
    }

    /**
     * @param takes 3 parameters including an int array containing 2 integers, a 
     * Direction enum to indicate direction the ship is to be placed, and a Ship object
     * to be placed
     * @return boolean to indicate if placing the ship on the desired coordinates was
     * successful or not 
     */
    public boolean placeShip(int[] firstCoordinate, Direction direction, ShipType type){
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


        if(direction == Direction.VERTICAL){
            if(((firstCoordinate[0])+(type.getSize()-1))>=this.gameBoard.length){
                return false;
                //firstCoordinate is 7, ship size is 5 - 1 = 4 remaining slots = 7+4 = 11 out of bounds vertically
                //firstCoordinate is 6, ship size is 5 - 1 - 4  remaing slots = 6+4 = 10 out of bounds vertically
                //firstCoordinate is 5, ship size is 5 - 1 = 4 remaining slots = 5+4 = 9 = in bounds 
                //grid is a 10x10 and with a 2D array, its 0-9 for the 10 slots 
            }

            for(int i = firstCoordinate[0]; i < (firstCoordinate[0]+(type.getSize())); i++){
                if(this.gameBoard[i][firstCoordinate[1]] == CellState.SHIP){
                    return false; 
                    //loops through that column and sees if there is any ship in those desired coordinates
                }
            }
            
            //int shipParts = type.getSize();
            //used as a counter to know when the following placement loop is done
            ArrayList<int[]> coordinates = new ArrayList<>();
            //initializes the ArrayList that will hold the ArrayList of int[] arrays
            for(int i = firstCoordinate[0]; i < (firstCoordinate[0]+type.getSize()); i++){
                 this.gameBoard[i][firstCoordinate[1]] = CellState.SHIP;
                 //accesses the gameboard 2D array and changes the block to CellState ship
                 coordinates.add(new int[]{i, firstCoordinate[1]});
                 //adds the 2 integer length int array to the coordinates ArrayList

                //shipParts -= 1;
                 //decrements the shipParts counter
                 /*if(shipParts == 0){
                    break;
                    //stops the loop once the counter is done
                 }*/
            }
            this.shipList.add(new Ship(type, coordinates));
            //intializes the ship and places it in the ship list

            
            //used to place the ships in vertical since all checks have been made
            return true;

      
        }else{
            if((firstCoordinate[1]+(type.getSize()-1))>=this.gameBoard[firstCoordinate[0]].length){
                return false;
                //firstCoordinate, 2nd part is 6, ship size = 5-1 =4, remaining slots = 6+4 = 10, out of bounds horizontally
            }

            for(int i = firstCoordinate[1]; i < (firstCoordinate[1]+(type.getSize())); i++){
                if(this.gameBoard[firstCoordinate[0]][i] == CellState.SHIP){
                    return false; 
                    //loops through that row and sees if there is any ship in those desired coordinates
                }
            }
            ArrayList<int[]> coordinates = new ArrayList<>();
            //initializes the ArrayList that will hold the ArrayList of int[] arrays

            for(int i = firstCoordinate[1]; i < (firstCoordinate[1] + type.getSize()); i++){
                this.gameBoard[firstCoordinate[0]][i] = CellState.SHIP;
                //accesses the gameboard 2D array and changes the block to CellState ship
                coordinates.add(new int[]{firstCoordinate[0], i});
                //adds the 2 integer length int array to the coordinates ArrayList
            }

            this.shipList.add(new Ship(type, coordinates));
            //intializes the ship and places it in the ship list

            
            //used to place the ships in vertical since all checks have been made
            return true;

        }

        //return false;
    }

    /** 
     * @return String to represent the current board 
    */
    public String getBoard(){
        StringBuilder sb = new StringBuilder();
        // initializes the StringBuilder that will build the board string
        
        sb.append("   ");
        // adds spacing to align the column headers with the grid
        for(int col = 0; col < this.gameBoard[0].length; col++){
            sb.append(col + " ");
            // appends each column number followed by a space
        }
        sb.append("|");
        // pipe acts as newline separator for network transmission
        
        for(int row = 0; row < this.gameBoard.length; row++){
            sb.append(row + "  ");
            // appends the row number label followed by spacing
            for(int col = 0; col < this.gameBoard[row].length; col++){
                switch(this.gameBoard[row][col]){
                    case EMPTY: sb.append("E "); break;
                    case SHIP:  sb.append("S "); break;
                    case HIT:   sb.append("H "); break;
                    case MISS:  sb.append("M "); break;
                    // appends the symbol for each CellState followed by a space
                }
            }
            sb.append("|");
            // pipe marks the end of each row, replacing the newline for network transmission
        }
        return sb.toString();
        // returns the completed board as a single string
    }

    /** 
     * @return String to represent the current hit map
    */
    public String getHitMap(){
        StringBuilder sb = new StringBuilder();
        // initializes the StringBuilder that will build the board string
        
        sb.append("   ");
        // adds spacing to align the column headers with the grid
        for(int col = 0; col < this.gameBoard[0].length; col++){
            sb.append(col + " ");
            // appends each column number followed by a space
        }
        sb.append("|");
        // pipe acts as newline separator for network transmission
        
        for(int row = 0; row < this.gameBoard.length; row++){
            sb.append(row + "  ");
            // appends the row number label followed by spacing
            for(int col = 0; col < this.gameBoard[row].length; col++){
                switch(this.gameBoard[row][col]){
                    case EMPTY: sb.append("E "); break;
                    case SHIP:  sb.append("E "); break;
                    case HIT:   sb.append("H "); break;
                    case MISS:  sb.append("M "); break;
                    // appends the symbol for each CellState followed by a space
                }
            }
            sb.append("|");
            // pipe marks the end of each row, replacing the newline for network transmission
        }
        return sb.toString();
        // returns the completed board as a single string

    }


}

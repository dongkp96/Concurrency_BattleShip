package GameLogic;

import java.util.ArrayList;

public class Ship{

    private ShipType type;
    private int hitCount;
    private ArrayList<int []> coordinates;

    public Ship(ShipType type, ArrayList<int []> shipCoordinates){
        this.type = type;
        this.hitCount = 0;
        this.coordinates = shipCoordinates;
    }

    /**
     * @return a boolean to check if a ship has been sunk
     * */
    public boolean checkIsSunk(){
        if(this.hitCount == this.type.getSize()){
            return true;
        }
        return false;
    }

    /**
     * Used to increase hit counter, will
     * change status of isSunk if hit counter exceeds ship size
     * */
    public void increaseHitCount(){
        this.hitCount ++;
    }


    /**
     * @param coordinate, an integer array containing a pair of integers
     *                    for coordinates to be checked if its inside the
     *                    ships coordinates
     * @return boolean to confirm or deny a hit
     * */
    public boolean checkIfHit(int[] coordinate){
        for(int i = 0; i<this.coordinates.size(); i++){
            if((this.coordinates.get(i)[0]==coordinate[0])&&(this.coordinates.get(i)[1]== coordinate[1])){
                this.increaseHitCount();
                return true;
            }
        }
        return false;
    }

    /**
    *@return an integer denoting the ships size based on its type enum  
    */
    public int getShipSize(){
        return this.type.size;
    }











}
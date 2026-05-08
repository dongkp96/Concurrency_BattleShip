package GameLogic;

import java.util.ArrayList;

public class Ship{

    private ShipType type;
    private int hitCount;
    private boolean isSunk = false;
    private ArrayList<int []> coordinates;

    public Ship(ShipType type){
        this.type = type;
        this.hitCount = 0;
        this.coordinates = new ArrayList<>();
    }

    /**
     * @return a boolean to check if a ship has been sunk
     * */
    public boolean checkIsSunk(){
        return this.isSunk;
    }

    /**
     * Used to increase hit counter, will
     * */
    public void increaseHitCount(){
        this.hitCount ++;
        if(this.hitCount == this.type.getSize()){
            this.isSunk = true;
        }
    }












}
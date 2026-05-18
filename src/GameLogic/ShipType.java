package GameLogic;

public enum ShipType{

    CARRIER(5),
    BATTLESHIP(4),
    CRUISER(3),
    SUBMARINE(3),
    DESTROYER(2);
    /*
    * These are enum constants that once called
    * by the enum ship type such as ShipType.CARRIER,
    * it will call the constructor and set the field
    * */

    private final int size;
    //field to hold the size, final because it won't change

    private ShipType(int size){
        this.size = size;
    }
    //constructor to set size for the enum, private because it needs to be for enums

    /**
     * @return an integer that indicates the Ship type's size
     * */
    public int getSize(){
        return this.size;
    }


}
package hw3.version_LDLinkedList;

/**
 * house class is used for house building, and it extends buildings class
 */
public class house extends buildings 
{
    /**Keeps the number of rooms of the house reference */
    private int roomNum;

    /**Keeps the color information of the house reference */
    private String color;

    /**
     * This constructor initiates position , height , length , roomNum and owner information of house reference.
     * @param pos indicates position number
     * @param hei indicates height number
     * @param len indicates length number
     * @param rooms indicates number of rooms
     * @param owner indicates owner information
     * @param color indicates color information
     */
    public house(int pos , int hei , int len , int rooms , String owner , String color)
    {
        super(pos, hei, len , owner);

        setRoomNum(rooms);
        this.color = color;
    }

    /**
     * <p>This method sets roomNum to rooms which is input
     * @param rooms indicates new room number
     */
    public void setRoomNum(int rooms)
    {
        roomNum = rooms;
    }

    /**
     * <p>This method returns the roomNum.
     * @return int - roomNum value
     */
    public int getRoomNum()
    {
        return roomNum;
    }

    /**
     * <p>This method returns the color.
     * @return String - color information
     */
    public String getColor()
    {
        return color;
    }

    /**
     * This method used for focusing mode
     * @return String - owner information of house reference
     */
    @Override
    public String focus() {
        return String.format("%s", getOwner());
    }

}

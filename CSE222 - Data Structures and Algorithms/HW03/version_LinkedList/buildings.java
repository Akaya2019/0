package hw3.version_LinkedList;

/**
 * buildings class which is superclass of house market and office classes. It extends buildTypes class
 */
public abstract class buildings extends buildTypes
{
    /**Keeps the owner information of the building reference */
    private final String owner;

    /**
     * This constructor initiates position , height , length and owner information of building reference.
     * @param pos indicates position number
     * @param hei indicates height number
     * @param len indicates length number
     * @param owner indicates owner information
     */
    public buildings(int pos , int hei , int len , String owner)
    {
        super(pos, hei, len);
        this.owner = owner;
    }

    /**
     * <p>This method returns the owner inf.
     * @return String - owner information
     */
    public String getOwner()
    {
        return owner;
    }

}

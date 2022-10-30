package hw1;

/**
 * buildTypes class which is superclass of all classes
 */
public abstract class buildTypes
{
    /**Keeps the position number of the buildType reference */
    private int position = 0;

    /**Keeps the height number of the buildType reference */
    private int height = 0;

    /**Keeps the position length of the buildType reference */
    private int length = 0;

    /**
     * This constructor initiates position , height and length of buildType reference.
     * @param pos indicates position number
     * @param hei indicates height number
     * @param len indicates length number
     */
    public buildTypes(int pos , int hei , int len)
    {
        setPosition(pos);
        setHeight(hei);
        setLength(len);
    }

    /**
     * <p>This method sets position to pos which is input
     * @param pos indicates new position number
     */
    public void setPosition(int pos)
    {
        position = pos;
    }

    /**
     * <p>This method returns the position.
     * @return int - position value
     */
    public int getPosition()
    {
        return position;
    }

    /**
     * <p>This method sets height to pos which is input
     * @param hei indicates new height number
     */
    public void setHeight(int hei)
    {
        height = hei;
    }

    /**
     * <p>This method returns the height.
     * @return int - height value
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * <p>This method sets length to pos which is input
     * @param len indicates new length number
     */
    public void setLength(int len)
    {
        length = len;
    }

    /**
     * <p>This method returns the length.
     * @return int - length value
     */
    public int getLength()
    {
        return length;
    }

    /**
     * This method used for focusing mode
     * @return String which depends on the subclass
     */
    public abstract String focus();
}

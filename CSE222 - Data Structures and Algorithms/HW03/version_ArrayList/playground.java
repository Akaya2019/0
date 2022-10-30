package hw3.version_ArrayList;
/**
 * playground class is used for playground buildType, it extends buildTypes class
 */
public class playground extends buildTypes
{
    /**
     * This constructor initiates position and length of playground reference.
     * @param pos indicates position number
     * @param len indicates length number
     */
    public playground(int pos , int len)
    {
        super(pos , 1 , len);
    }

    /**
     * This method used for focusing mode
     * @return String - length of playground reference
     */
    @Override
    public String focus()
    {
        return String.format("%s", getLength());
    }
}

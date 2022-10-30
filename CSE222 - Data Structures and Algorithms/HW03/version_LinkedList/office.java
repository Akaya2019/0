package hw3.version_LinkedList;

/**
 * office class is used for office building ,and it extends buildings
 */
public class office extends buildings
{
    /**Keeps the jobType information of the office reference */
    private String jobType;

    /**
     * This constructor initiates position , height , length , roomNum, owner information and jobType information of office reference.
     * @param pos indicates position number
     * @param hei indicates height number
     * @param len indicates length number
     * @param owner indicates owner information
     * @param jobType indicates Job Type information
     */
    public office(int pos , int hei , int len , String owner , String jobType)
    {
        super(pos, hei, len, owner);
        this.jobType = jobType;
    }

    /**
     * <p>This method returns the jobType.
     * @return String - jobType value
     */
    public String getJobType()
    {
        return jobType;
    }

    /**
     * This method used for focusing mode
     * @return String - jobType information of office reference
     */
    @Override
    public String focus()
    {
        return String.format("%s", getJobType());
    }
    
}

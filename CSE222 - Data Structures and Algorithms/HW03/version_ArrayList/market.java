package hw3.version_ArrayList;

/**
 * market class is used for market building , and it extends buildings class
 */
public class market extends buildings
{
    /**Keeps the opening Hour of the market reference */
    private int openingHour;

    /**Keeps the opening Minute of the market reference */
    private int openingMinute;

    /**Keeps the closing Hour of the market reference */
    private int closingHour;

    /**Keeps the closing Minute of the market reference */
    private int closingMinute;

    /**
     * This constructor initiates position , height , length , owner information , openingHour and closingHour of market reference.
     * @param pos indicates position number
     * @param hei indicates height number
     * @param len indicates length number
     * @param owner indicates owner information
     * @param openHour indicates opening hour inf.
     * @param closeHour indicates closing hour inf.
     */
    public market(int pos , int hei , int len , String owner , int openHour , int closeHour)
    {
        this(pos, hei, len, owner, openHour, 0, closeHour, 0);
    }

    /**
     * This constructor initiates position , height , length , owner information , openingHour , openingMinute , closingHour and closingMinute of market reference.
     * @param pos indicates position number
     * @param hei indicates height number
     * @param len indicates length number
     * @param owner indicates owner information
     * @param openHour indicates opening hour inf.
     * @param closeHour indicates closing hour inf.
     * @param openMinute indicates opening minute inf.
     * @param closeMinute indicates closing minute inf.
     */
    public market(int pos , int hei , int len , String owner , int openHour , int openMinute , int closeHour , int closeMinute)
    {
        super(pos, hei, len, owner);

        setOpenHour(openHour);
        setOpenMinute(openMinute);
        setCloseHour(closeHour);
        setCloseMinute(closeMinute);
    }

    /**
     * <p>This method sets openingHour to openHour which is input
     * @param openHour indicates new openingHour number
     */
    public void setOpenHour(int openHour)
    {
        openingHour = openHour;
    }

    /**
     * <p>This method returns the openingHour.
     * @return int - openingHour value
     */
    public int getOpenHour()
    {
        return openingHour;
    }

    /**
     * <p>This method sets openingMinute to openMinute which is input
     * @param openMinute indicates new openingMinute number
     */
    public void setOpenMinute(int openMinute)
    {
        openingMinute = openMinute;
    }

    /**
     * <p>This method returns the openingMinute.
     * @return int - openingMinute value
     */
    public int getOpenMinute()
    {
        return openingMinute;
    }

    /**
     * <p>This method sets closingHour to closeHour which is input
     * @param closeHour indicates new closingHour number
     */
    public void setCloseHour(int closeHour)
    {
        closingHour = closeHour;
    }

    /**
     * <p>This method returns the closingHour.
     * @return int - closingHour value
     */
    public int getCloseHour()
    {
        return closingHour;
    }

    /**
     * <p>This method sets closingMinute to closeMinute which is input
     * @param closeMinute indicates new closingMinute number
     */
    public void setCloseMinute(int closeMinute)
    {
        closingMinute = closeMinute;
    }

    /**
     * <p>This method returns the closingMinute.
     * @return int - closingMinute value
     */
    public int getCloseMinute()
    {
        return closingMinute;
    }

    /**
     * This method used for focusing mode
     * @return String - closing hour and closing minute (HH:MM) of market reference
     */
    @Override
    public String focus()
    {
        String resultCloseHour;
        if(getCloseHour() < 10){
            resultCloseHour = "0" + getCloseHour();
        }
        else{
            resultCloseHour = "" + getCloseHour();
        }

        String resultCloseMinute;
        if(getCloseMinute() < 10){
            resultCloseMinute = "0" + getCloseMinute();
        }
        else{
            resultCloseMinute = "" + getCloseMinute();
        }
        return String.format("%s:%s", resultCloseHour, resultCloseMinute);
    }

    /**
     * This method prints opening hour and minute inf. (hh:mm)
     * @return String - opening hour and opening minute (HH:MM) of market reference
     */
    public String printOpenHour()
    {
        String resultOpenHour;
        if(getOpenHour() < 10){
            resultOpenHour = "0" + getOpenHour();
        }
        else{
            resultOpenHour = "" + getOpenHour();
        }

        String resultOpenMinute;
        if(getOpenMinute() < 10){
            resultOpenMinute = "0" + getOpenMinute();
        }
        else{
            resultOpenMinute = "" + getOpenMinute();
        }
        return String.format("%s:%s", resultOpenHour, resultOpenMinute);
    }
    
}

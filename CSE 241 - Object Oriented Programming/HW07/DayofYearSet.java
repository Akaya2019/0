
import java.io.IOException; 
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * <h1>DayofYearSet</h1>
 * <p>The DayofYearSet program implements an application that
 * simply keeps days in it within DayofYear object references.
 * @author Ali KAYA
 * @version 1.0
 * @since 2022-01-05
 */

public class DayofYearSet {

    /**Keeps DayofYear references*/
    private DayofYear[] arr;

    /**Keeps capacity number of arr field*/
    private int capacity;

    /**Keeps used number of arr field*/
    private int used = 0;

    /**Keeps number of alive DayofYear object references.*/
    public static int aliveObj = 0;

    /**
     * <p>This class is static inner class. DayofYearSet
     * class keep datas within this class.
     */
    public static class DayofYear      //inner class------------------------------------
    {
        /**Keeps day number of DayofYear class*/
        private int day;

        /**Keeps month number of DayofYear class*/
        private int month;

        /**
        * <p>This constructor is no-parameter constructor of DayofYear class.
        * Automatically initiates date January 1.
        */
        public DayofYear()
        {
            this(1 , 1);
        }

        /**
        * <p>This constructor initiates date based on user input.
        * @param inDay This is the first parameter to this constructor
        * @param inMonth This is the second parameter to this constructor
        */
        public DayofYear(int inMonth , int inDay)
        {
            setMonth(inMonth);
            setDay(inDay);
        }

        /**
        * <p>This method set day to inDay which is input
        * @param inDay This is the only parameter to setDay method
        */
        public void setDay(int inDay){ day = inDay;}

        /**
        * <p>This method set month to inMonth which is input
        * @param inMonth This is the only parameter to setMonth method
        */
        public void setMonth(int inMonth){ month = inMonth;}

        /**
        * <p>This method set month and day to inMonth and inDay which are inputs
        * @param inDay This is the first parameter to setDate method
        * @param inMonth This is the second parameter to setDate method
        */
        public void setDate(int inMonth , int inDay) {month = inMonth; day = inDay;}
        

        /**
        * <p>This method returns the day number.
        * @return int - day number
        */
        public int getDay() {
            return day;
        }

        /**
        * <p>This method returns the month number.
        * @return int - month number
        */
        public int getMonth(){
            return month;
        }

    }   //end of DayofYear class-------------------------------------------------------------------

    /**
    * <p>This constructor is no-parameter constructor of DayofYearSet class.
    * Automatically initiates capacity to 10.
    */
    public DayofYearSet()
    {
        setCapacity(10);
        arr = new DayofYear[getCapacity()];

        for(int i = 0; i < arr.length ; ++i)
        {
            arr[i] = new DayofYear();
        }
    }

    /**
    * <p>This constructor initiates capacity to size which is specified by user.
    * @param size This is the only parameter to this constructor  
    */
    public DayofYearSet(int size)
    {
        setCapacity(size);
        arr = new DayofYear[getCapacity()];

        for(int i = 0; i < arr.length ; ++i)
        {
            arr[i] = new DayofYear();
        }
    }

    /**
    * <p>This constructor set capacity to size of list,
    * Transfers data from ArrayList to arr field 
    * @param list keeps DayofYear references in it
    */
    public DayofYearSet( ArrayList<DayofYear> list )
    {
        setCapacity( list.size() + 10);
        arr = new DayofYear[getCapacity()];

        for(int i = 0; i < arr.length ; ++i)
        {
            arr[i] = new DayofYear();
        }

        for(DayofYear temp : list)
        {
            add( temp.getMonth() , temp.getDay() );
        }
    }

    /**
    * <p>This method set capacity to newCap which is input
    * @param newCap This is the only parameter to setCapacity method
    */
    public void setCapacity(int newCap) { capacity = newCap; }

    
    /** 
     * <p>This method set used to newUsed which is input 
     * and increments the aliveObj data if newUsed is bigger than current used
     * ,else decrement the aliveObj data.
     * @param newUsed -int to set as used
     */
    public void setUsed(int newUsed){ 
        
        if( newUsed > getUsed() ){
            aliveObj++;
        }

        else if(newUsed < getUsed()){
            aliveObj--;
        }

        used = newUsed;
    }

    
    /** 
     * This method returns capacity number.
     * @return int - capacity number
     */
    public int getCapacity(){
        return capacity; 
    }

    
    /** 
     * This method returns used number.
     * @return int - used number
     */
    public int getUsed(){ 
        return used;
    }

    
    /** 
     * This method Returns a string representation of the object.
     * @return String - a string representation of the object.
     */
    public String toString()
    {
        int counter = 0;
        String[] months = {"January" , "February" , "March" , "April" , "May" , "June" ,
        "July" , "August" , "September" , "October" , "November" , "December"};

        StringBuilder result = new StringBuilder();
        // result.append("Shop [ name = "+name+" ");
    
        for(DayofYear p : arr)
        {
            result.append(" "+months[p.getMonth()-1]+" "+p.getDay()+" \n ");
            counter++;
            if(counter == this.getUsed()) {break;};
        }
    
        result.append("\n");
        return result.toString();
    }
    
    
    /**
     * this method indicates whether some other object is "equal to" this one. 
     * @param other - the reference object with which to compare.
     * @return boolean - true if this object is the same as the other argument; false otherwise.
     */
    public boolean equals(DayofYearSet other)
    {   
        boolean ans = true;
        int counter = 0;

        if (other.getUsed() != this.getUsed())
        {
            ans = false;
        }
        else
        {
            for (int i = 0; i < this.getUsed(); i++)
            {
                for (int j = 0; j < other.getUsed(); j++)
                {
                    if (this.arr[i].getDay() == other.arr[j].getDay() && this.arr[i].getMonth() == other.arr[j].getMonth())
                    {
                        counter++;
                    }
                }
            }

            if (counter != this.getUsed())
            {
                ans = false;
            }            
        }

        return ans;
    }

    
    /** 
     * This method adds a new element to the DayofYearSet
     * @param month This is the first parameter of the add method
     * @param day This is the second parameter of the add method
     */
    public void add(int month , int day)
    {
        int flag = 0;

        int[] daysNum = {31 , 28 , 31 , 30 , 31 , 30 , 31 , 31 , 30 , 31 , 30 , 31} ;

        for (int i = 0; i < getUsed(); i++)
        {
            if(arr[i].getDay() == day && arr[i].getMonth() == month)
            {
                flag = 1;
                break;
            }
        }

        if (day > daysNum[month - 1])
        {
            flag = 1;
        }        

        if (flag != 1)
        {
            if (getUsed() >= getCapacity())
            {
                setCapacity( getCapacity() + 10);

                DayofYear[] temp = new DayofYear[ getCapacity() ];
                for (int i = 0; i < getUsed() ; i++)
                {
                    temp[i] = arr[i];
                }
                arr = temp;
            }

            arr[ getUsed() ].setDate(month , day); 
            setUsed(getUsed() + 1);
        }
    
    }

    /** 
     * This method removes an element from the end of the DayofYearSet
     */
    public void remove()
    {
        DayofYear[] temp = new DayofYear[ getCapacity() ];
        for (var i = 0; i < getUsed() - 1; i++)
        {
            temp[i] = arr[i];
        }
        arr = temp;

        setUsed(getUsed() - 1);
    }

    /** 
     * This method returns size number of DayofYearSet.
     * @return int - size number
     */
    public int size() { return getUsed(); }

    /** 
     * This method returns a new set that union of this object's set and other object's set
     * @param other the reference object
     * @return DayofYearSet - union of this object's set and other object's set
     */
    public DayofYearSet unionSet( DayofYearSet other )
    {
        int i;
        int flag = 0;

        DayofYearSet New = new DayofYearSet(this.getUsed() + other.getUsed() + 10); 

        for (i = 0; i < this.getUsed(); i++)
        {
            New.arr[New.getUsed()] = this.arr[i];
            New.setUsed( New.getUsed() + 1);
        }
        for (i = 0; i < other.getUsed(); i++)
        {
            for (int j = 0; j < this.getUsed(); j++)
            {
                if (other.arr[i].getMonth() == this.arr[j].getMonth() && other.arr[i].getDay() == this.arr[j].getDay())
                {
                    flag  = 1;
                    break;
                }
            }

            if (flag != 1)
            {
                New.arr[New.getUsed()] = other.arr[i];
                New.setUsed( New.getUsed() + 1);
            }
            flag = 0;
        }
        
        return New;
    }

    /** 
     * This method returns a new set that The difference of this object's set from other object's set 
     * @param other the reference object
     * @return DayofYearSet - difference of this object's set and other object's set
     */
    public DayofYearSet differenceSet(DayofYearSet other)
    {
        if ( this.equals(other) ){
            System.out.println("These sets are equal. Try again..");
            return null;
        }
        else{
            DayofYearSet complementOther = other.complementSet(); 
            return complementOther.intersectionSet(this);           //!object2 ^ object1;
        }    
    }

    
    /** 
     * This method returns a new set that intersection of this object's set and other object's set
     * @param other the reference object
     * @return DayofYearSet - intersection of this object's set and other object's set
     */
    public DayofYearSet intersectionSet(DayofYearSet other)
    {
        int smaller = 0;

        if (this.getUsed() > other.getUsed()){
            smaller = other.getUsed();
        }
        else{
            smaller = this.getUsed();
        }

        DayofYearSet New = new DayofYearSet(smaller);

        for (var i = 0; i < this.getUsed(); i++)
        {
            for (var j = 0; j < other.getUsed(); j++)
            {
                if (this.arr[i].getMonth() == other.arr[j].getMonth() && this.arr[i].getDay() == other.arr[j].getDay())
                {
                    New.arr[New.getUsed()].setDate( this.arr[i].getMonth() , this.arr[i].getDay() );
                    New.setUsed( New.getUsed() + 1);
                    break;
                }
            }
        }
          
        return New;  
    }

    
    /** 
     * This method returns a new set that complement of this object's set
     * @return DayofYearSet - complement of this object's set
     */
    public DayofYearSet complementSet()
    {
        int flag = 0;
        int i , j ;
        int k;

        int[] daysNum = {31 , 28 , 31 , 30 , 31 , 30 , 31 , 31 , 30 , 31 , 30 , 31};

        DayofYearSet New = new DayofYearSet(365 - this.getUsed());

        for (i = 1; i <= daysNum.length; i++)
        {
            for (j = 1; j <= daysNum[i-1]; j++)
            {
                flag = 0;
                for (k = 0; k < this.getUsed(); k++)
                {
                    if (this.arr[k].getMonth() == i && this.arr[k].getDay() == j)
                    {
                        flag = 1;
                    }
                    if(flag == 1)break;
                }

                if (flag != 1)
                {
                    New.arr[New.getUsed()].setDate(i , j);
                    New.setUsed( New.getUsed() + 1); 
                }
            }
        }

        return New;
    }

    
    /** 
     * This method returns the total number of DayOfYear objects alive in all the sets
     * @return int - aliveObj that refers alive DayofYear objects in all the sets
     */
    public static int objectsAlive()
    {
        return aliveObj;
    }

    
    /** 
     * This method writes the set into a txt file which is defined by user
     * @param fileName A file name that has extension .txt
     * @throws IOException Signals that an I/O exception of some sort has occurred.
     */
    public void txtPrinter(String fileName) throws IOException
    {
        String[] months = {"January" , "February" , "March" , "April" , "May" , "June" ,
                             "July" , "August" , "September" , "October" , "November" , "December"};

        try 
        {
            PrintWriter out = new PrintWriter(fileName);

            for(var i=0;i< getUsed();i++)
            {
                out.printf("%s " , months[arr[i].getMonth()-1] );
                out.printf("%d \n" , arr[i].getDay() );
            }

            out.printf("Size of this array is: %d.\n", getUsed());
            out.close();
            
        }
        catch (IOException err)
        {
            System.out.println("Error occurred.");
            err.printStackTrace();
        }

    }

}   //end of DayOfYearSet class

package hw3.version_ArrayList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * <h1>street</h1>
 * <p>The City Plan program implements a city planning software
 * that will be used for designing a small one street town (with using ArrayList data structure).
 * @author Ali KAYA
 * @version 1.0
 * @since 2022-03-20
 */
public class streetAL {
    /**Keeps buildTypes references*/
    private ArrayList< ArrayList<buildTypes> >arr;

    /**Keeps capacity number of arr field's first row*/
    private int CapBack;

    /**Keeps capacity number of arr field's second row*/
    private int CapFront;

    /**Keeps initial capacity number and called in constructor*/
    private final int INITIAL_CAPACITY = 10;

    /**Keeps the number of buildings on the back of the street  */
    private int buildingNumsBack;       //number of buildTypes in back side of the street

    /**Keeps the number of buildings on the front of the street  */
    private int buildingNumsFront;      //number of buildTypes in front side of the street

    /**Keeps the length of the street  */
    private int streetLength;               //length of the street

    /**Keeps the empty length of back side of the street  */
    private int streetLengthEmptyBack;      //length of empty area in back side of the street

    /**Keeps the empty length of front  side of the street  */
    private int streetLengthEmptyFront;     //length of empty area in front side of the street

    /**
     * This constructor initiates street length based on user input.
     * @param newLength This is the only parameter to this constructor
     */
    public streetAL(int newLength) {
        setCapBack( INITIAL_CAPACITY );
        setCapFront( INITIAL_CAPACITY );
        setStreetLength(newLength);
        setStreetLengthEmptyBack(newLength);
        setStreetLengthEmptyFront(newLength);

        setBuildingNumsBack(0);
        setBuildingNumsFront(0);

        arr = new ArrayList<>(2);
        ArrayList< buildTypes > arr0 = new ArrayList<>(getCapBack());
        ArrayList< buildTypes > arr1 = new ArrayList<>(getCapFront());
        arr.add(arr0);
        arr.add(arr1);
    }

    /**
     * <p>This method sets CapBack to newCap which is input
     * @param newCap This is the only parameter to setCapBack method
     */
    public void setCapBack(int newCap) {
        CapBack = newCap;
    }

    /**
     * <p>This method returns the CapBack.
     * @return int - CapBack value
     */
    public int getCapBack() {
        return CapBack;
    }

    /**
     * <p>This method sets CapFront to newCap which is input
     * @param newCap This is the only parameter to setCapFront method
     */
    public void setCapFront(int newCap) {
        CapFront = newCap;
    }

    /**
     * <p>This method returns the CapFront.
     * @return int - CapFront value
     */
    public int getCapFront() {
        return CapFront;
    }

    /**
     * <p>This method sets buildingNumBack to newNum which is input
     * @param newNum This is the only parameter to setBuildingNumsBack method
     */
    public void setBuildingNumsBack(int newNum) {
        buildingNumsBack = newNum;
    }

    /**
     * <p>This method returns the buildingNumsBack.
     * @return int - buildingNumsBack value
     */
    public int getBuildingNumsBack() {
        return buildingNumsBack;
    }

    /**
     * <p>This method sets buildingNumFront to newNum which is input
     * @param newNum This is the only parameter to setBuildingNumsFront method
     */
    public void setBuildingNumsFront(int newNum) {
        buildingNumsFront = newNum;
    }

    /**
     * <p>This method returns the buildingNumsFront.
     * @return int - buildingNumsFront value
     */
    public int getBuildingNumsFront() {
        return buildingNumsFront;
    }

    /**
     * <p>This method sets streetLength to newLength which is input
     * @param newLength This is the only parameter to setStreetLength method
     */
    public void setStreetLength(int newLength) {
        streetLength = newLength;
    }

    /**
     * <p>This method returns the streetLength.
     * @return int - streetLength value
     */
    public int getStreetLength() {
        return streetLength;
    }


    /**
     * <p>This method sets streetLengthEmptyBack to newLength which is input
     * @param newLength This is the only parameter to setStreetLengthEmptyBack method
     */
    public void setStreetLengthEmptyBack(int newLength) {
        streetLengthEmptyBack = newLength;
    }

    /**
     * <p>This method returns the streetLengthEmptyBack.
     * @return int - streetLengthEmptyBack value
     */
    public int getStreetLengthEmptyBack() {
        return streetLengthEmptyBack;
    }

    /**
     * <p>This method sets streetLengthEmptyFront to newLength which is input
     * @param newLength This is the only parameter to streetLengthEmptyFront method
     */
    public void setStreetLengthEmptyFront(int newLength) {
        streetLengthEmptyFront = newLength;
    }

    /**
     * <p>This method returns the streetLengthEmptyFront.
     * @return int - streetLengthEmptyFront value
     */
    public int getStreetLengthEmptyFront() {
        return streetLengthEmptyFront;
    }

    /**
     * This method adds a new buildings to the arr field
     * @param borf indicates side of the street 0:back 1:front
     * @param newBuild indicates a reference which is buildType
     * @return int- if add operation is done properly it returns 1 if not it returns 0
     */
    public int add(int borf, buildTypes newBuild) {
        int ans = 0;

        switch (borf)
        {
            case 0:
                if(getStreetLengthEmptyBack() < newBuild.getLength()){
                    System.out.println("The empty area of the back side of the street is not enough for this action!!");
                }
                else if ( getStreetLengthEmptyBack() >= newBuild.getLength() && isEmptyArea(borf, newBuild) )            //adding a building back side of the street
                {
                    ans = 1;
                    (arr.get(borf)).add(newBuild);
                    setBuildingNumsBack(getBuildingNumsBack() + 1);
                    setStreetLengthEmptyBack(getStreetLengthEmptyBack() - newBuild.getLength());
                }
                break;

            case 1:
                if (getStreetLengthEmptyFront() < newBuild.getLength()){
                    System.out.println("The empty area of the front side of the street is not enough for this action!!");
                }
                else if ( getStreetLengthEmptyFront() >= newBuild.getLength() && isEmptyArea(borf, newBuild) )      //adding a building front side of the street
                {
                    ans = 1;
                    (arr.get(borf)).add(newBuild);
                    setBuildingNumsFront(getBuildingNumsFront() + 1);
                    setStreetLengthEmptyFront(getStreetLengthEmptyFront() - newBuild.getLength());
                }
                break;

        }
        if (ans == 1) System.out.println("Adding operation done successfully\n");
        else System.out.println("Adding operation failed !!\n");
        return ans;
    }
    /**
     * This method deletes last element of the arr field
     * @param borf indicates side of the street 0:back 1:front
     * @return if delete operation is done returns 1 if not returns 0
     */
    public int delete(int borf) {
        int ans = 1;
        if (borf == 0 && getBuildingNumsBack() != 0) {
            setStreetLengthEmptyBack(getStreetLengthEmptyBack() + (arr.get(borf)).get(getBuildingNumsBack() - 1).getLength());
            (arr.get(borf)).remove(getBuildingNumsBack() - 1);
            setBuildingNumsBack(getBuildingNumsBack() - 1);
        }
        else if (borf == 1 && getBuildingNumsFront() != 0) {
            setStreetLengthEmptyFront(getStreetLengthEmptyFront() + (arr.get(borf)).get(getBuildingNumsFront() - 1).getLength());
            (arr.get(borf)).remove(getBuildingNumsFront() - 1);
            setBuildingNumsFront(getBuildingNumsFront() - 1);
        }
        else
            ans = 0;

        if (ans == 1) System.out.println("Deleting operation done successfully\n");
        else System.out.println("Deleting operation failed there is no building on this side!!\n");
        return ans;
    }

    /**
     * This method is used for checking is area empty or not.
     * @param borf indicates side of the street 0:back 1:front
     * @param newBuild indicates a reference which is buildType
     * @return boolean -if area is empty returns false ,if not returns true
     */
    public boolean isEmptyArea(int borf, buildTypes newBuild) {
        boolean ans = true;
        int length ;

        if (borf == 0){
            length = getBuildingNumsBack();
        }else
            length = getBuildingNumsFront();

        for (int i = 0; i < length; ++i) {
            if ((newBuild.getPosition() >= arr.get(borf).get(i).getPosition()) && (newBuild.getPosition() < arr.get(borf).get(i).getPosition() + arr.get(borf).get(i).getLength())) {
                ans = false;
                break;
            }
        }
        if (!ans){
            System.out.println("The specified land is already occupied !!");
        }
        return ans;
    }

    /**
     * This method displays the total remaining length of lands on the street.
     */
    public void displayEmptyLands()
    {
        System.out.printf("\nTotal remaining length of lands: \n Back side of the street: %s \n " +
                        "Front side of the street: %s \n", getStreetLengthEmptyBack(), getStreetLengthEmptyFront());
    }

    /**
     * This method lists all properties of the buildings.
     */
    public void displayListOfBuildings()
    {
        int i;
        int breakCounter = 0 , counter;
        for (i = 0; i < 2; ++i)
        {
            if (i == 0) breakCounter = getBuildingNumsBack();
            else if (i == 1) breakCounter = getBuildingNumsFront();
            counter = 0;

            while (counter < breakCounter) {
                counter++;

                if (arr.get(i).get(counter - 1) instanceof house) {
                    System.out.printf("\nFeatures of %s's house ;\n Owner printing with focus method!\n Color: %s\n Number of rooms: %s\n " +
                                    "Position: %s\n Height of the house: %s\n Length of the house: %s\n"
                            , arr.get(i).get(counter - 1).focus()
                            , ((house) arr.get(i).get(counter - 1)).getColor()
                            , ((house) arr.get(i).get(counter - 1)).getRoomNum()
                            , arr.get(i).get(counter - 1).getPosition()
                            , arr.get(i).get(counter - 1).getHeight()
                            , arr.get(i).get(counter - 1).getLength());
                }
                else if (arr.get(i).get(counter - 1) instanceof market) {
                    System.out.printf("\nFeatures of %s's market ;\n Opening time: %s\n " +
                                    "Closing time: %s (printing with focus method)\n Position: %s\n Height of the market: %s\n Length of the market: %s\n "
                            , ((market) arr.get(i).get(counter - 1)).getOwner()
                            , ((market) arr.get(i).get(counter - 1)).printOpenHour()
                            , arr.get(i).get(counter - 1).focus()
                            , arr.get(i).get(counter - 1).getPosition()
                            , arr.get(i).get(counter - 1).getHeight()
                            , arr.get(i).get(counter - 1).getLength());
                }
                else if (arr.get(i).get(counter - 1) instanceof office) {
                    System.out.printf("\nFeatures of %s's office ;\n Job Type of the office: %s (printing with focus method)\n Position: %s\n " +
                                    "Height of the office: %s\n Length of the office: %s\n"
                            , ((office) arr.get(i).get(counter - 1)).getOwner()
                            , arr.get(i).get(counter - 1).focus()
                            , arr.get(i).get(counter - 1).getPosition()
                            , arr.get(i).get(counter - 1).getHeight()
                            , arr.get(i).get(counter - 1).getLength());

                }
                else if (arr.get(i).get(counter - 1) instanceof playground) {
                    System.out.printf("\nFeatures of this playground ;\n Position: %s\n Height of the playground: %s\n Length of the playground: %s\n"
                            , arr.get(i).get(counter - 1).getPosition()
                            , arr.get(i).get(counter - 1).getHeight()
                            , arr.get(i).get(counter - 1).getLength());
                }
            }
        }
    }

    /**
     * This method displays the total length of street occupied by playgrounds
     * and prints Ratio of playgrounds to street length.
     */
    public void displayRatioPlayground()
    {
        int i;
        int lengthCounter = 0;
        int breakCounter = 0 , counter;

        for (i = 0; i < 2; ++i) {
            if (i == 0) breakCounter = getBuildingNumsBack();
            else if (i == 1) breakCounter = getBuildingNumsFront();
            counter = 0;

            while (counter < breakCounter) {
                counter++;

                if (arr.get(i).get(counter - 1) instanceof playground) {
                    lengthCounter += arr.get(i).get(counter - 1).getLength();
                }
            }
        }
        System.out.printf("\nTotal length of the playgrounds in the street: %d\n Ratio of length of playgrounds in the street: %f\n"
                , lengthCounter , (double)lengthCounter/(2*getStreetLength()));
    }

    /**
     * This method displays the total length of street occupied by the markets, houses or offices.
     */
    public void displayTotalOccupied()
    {
        int i;
        int houseLengths = 0 , marketLengths = 0 , officeLengths = 0;
        int breakCounter = 0 , counter;

        for (i = 0; i < 2; ++i) {
            if (i == 0) breakCounter = getBuildingNumsBack();
            else if (i == 1) breakCounter = getBuildingNumsFront();
            counter = 0;

            while (counter < breakCounter) {
                counter++;

                if (arr.get(i).get(counter - 1) instanceof house)
                    houseLengths += arr.get(i).get(counter - 1).getLength();

                else if (arr.get(i).get(counter - 1) instanceof market)
                    marketLengths += arr.get(i).get(counter - 1).getLength();

                else if (arr.get(i).get(counter - 1) instanceof office)
                    officeLengths += arr.get(i).get(counter - 1).getLength();
            }
        }
        System.out.printf("\nTotal length of street occupied by ; \n Houses: %d \n Markets: %d \n Offices: %d \n"
                , houseLengths , marketLengths , officeLengths);
    }

    /**
     * This method displays the Skyline Silhouette of the street.
     */
    public void displaySilhouette()
    {
        int i , flag1 = 0 ,flag2 = 0 , tempHeight = 0;
        int breakCounter = 0 , counter;
        int startPos , endPos;
        int maxHeight = 0; //hata burada eger 0 a bina koyarsam 1i get edemez

        for (i = 0; i < 2; ++i) {
            if (i == 0) breakCounter = getBuildingNumsBack();
            else if (i == 1) breakCounter = getBuildingNumsFront();
            counter = 0;

            while (counter < breakCounter) {
                counter++;
                if (arr.get(i).get(counter - 1).getHeight() > maxHeight) {
                    maxHeight = arr.get(i).get(counter - 1).getHeight();
                }
            }
        }

        char[][] myArr = new char[maxHeight+1][getStreetLength()+1];
        for (int j = 0; j < myArr.length; j++) {
            for (int k = 0; k < myArr[j].length; k++) {
                myArr[j][k] = ' ';
            }
        }

        for (i = 0; i < 2; ++i) {
            if (i == 0) breakCounter = getBuildingNumsBack();
            else if (i == 1) breakCounter = getBuildingNumsFront();
            counter = 0;

            while (counter < breakCounter) {
                counter++;

                startPos = arr.get(i).get(counter - 1).getPosition();
                endPos = arr.get(i).get(counter - 1).getPosition() + arr.get(i).get(counter - 1).getLength();

                for (int j = startPos; j < endPos; j++) {
                    if(!overlapController(j , arr.get(i).get(counter - 1).getHeight())) myArr[maxHeight - arr.get(i).get(counter - 1).getHeight()][j] = '_';
                }
            }
        }
        for (int j = 0; j < myArr.length; j++) {
            for (int k = 0; k < myArr[j].length; k++) {
                if (myArr[j][k] == '_' && (k == 0 || k == myArr[j].length-1)){
                    for (int l = j; l < myArr.length; l++) {
                        myArr[l][k] = '|';
                    }
                    myArr[j][k] = '_';
                }
                else if (myArr[j][k] == '_' && myArr[j][k-1] == ' ') {
                    flag1 = 0;
                    for (int l = j+1; l < myArr.length; l++) {
                        if (myArr[l][k-1] == '_'){
                            flag1 = 1;
                            tempHeight = l;
                            break;
                        }
                    }
                    if (flag1 == 1){
                        for (int l = j+1; l <= tempHeight; l++) {
                            myArr[l][k] = '|';
                        }
                    }
                    else{
                        for (int l = j+1; l < myArr.length; l++) {
                            myArr[l][k] = '|';
                        }
                    }
                }
                else if (myArr[j][k] == '_' && myArr[j][k+1] == ' ') {
                    flag2 = 0;
                    for (int l = j+1; l < myArr.length; l++) {
                        if (myArr[l][k+1] == '_'){
                            flag2 = 1;
                            tempHeight = l;
                            break;
                        }
                    }
                    if (flag2 == 1){
                        for (int l = j+1; l <= tempHeight; l++) {
                            myArr[l][k] = '|';
                        }
                    }
                    else{
                        for (int l = j+1; l < myArr.length; l++) {
                            myArr[l][k] = '|';
                        }
                    }
                }
            }
        }

        for (char[] chars : myArr) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.print("\n");
        }
        for (int j = 0; j < getStreetLength(); j++) {
            System.out.print('=');
        }
        System.out.println();
    }

    /**
     * This method is used in displaySilhouette method for checks is there an overlap on street
     * @param myPos -indicates a position number
     * @param myHeight -indicates a height number
     * @return boolean -if there is an overlap returns true if not returns false
     */
    public boolean overlapController(int myPos, int myHeight) {
        boolean ans = false;
        int breakCounter = 0 ,counter , posCounter = 0;
        int startPos , endPos;
        int xIndex_1 = 0, yIndex_1 = 0, xIndex_2 = 0, yIndex_2 = 0;
        int minHeight;
        int i ;

        for (i = 0; i < 2; ++i) {
            if (i == 0) breakCounter = getBuildingNumsBack();
            else if (i == 1) breakCounter = getBuildingNumsFront();
            counter = 0;

            while (counter < breakCounter) {
                counter++;

                startPos = arr.get(i).get(counter - 1).getPosition();
                endPos = arr.get(i).get(counter - 1).getPosition() + arr.get(i).get(counter - 1).getLength();

                for (int j = startPos; j < endPos; j++) {
                    if (myPos == j) {
                        posCounter++;

                        if (posCounter == 1) {
                            xIndex_1 = i;
                            yIndex_1 = counter - 1;}
                        else if (posCounter == 2) {
                            xIndex_2 = i;
                            yIndex_2 = counter - 1;}
                        break;
                    }
                }
            }
        }
        if (posCounter == 2)
        {
            if(arr.get(xIndex_1).get(yIndex_1).getHeight() > arr.get(xIndex_2).get(yIndex_2).getHeight())
                minHeight = arr.get(xIndex_2).get(yIndex_2).getHeight();
            else
                minHeight = arr.get(xIndex_1).get(yIndex_1).getHeight();

            if (myHeight <= minHeight)
                ans = true;
        }

        return ans;
    }

    /**
     * This Method display a menu for user use
     */
    public static void menu()
    {
        int modeSelect = 0;
        int flagBreak = 0;
        int flag = 0 , flagMode = 0 , flagMode2 = 0 ,flagMode3 = 0 , flagMode4 = 0 , flagMode5 = 0;

        int positionHold , heightHold , lengthHold , roomHold , oHourHold , oMinuteHold , cHourHold , cMinuteHold , sideHold;
        String ownerHold , colorHold , jobHold;

        Scanner inp = new Scanner(System.in);
        System.out.print("\n\n");

        do {
            try {
                flag = 1;
                System.out.println("Please enter street length: ");
                modeSelect = Integer.parseInt(inp.nextLine());
                if (0 < modeSelect ) {
                    flag = 0;}
            } catch (Exception e) {
                System.out.println("Wrong Input Type\n\n");
                inp.next();
            }
        } while (flag == 1);
        streetAL myStreet = new streetAL(modeSelect);

        do {
            do {
                try {
                    flagMode = 1;
                    System.out.printf("%s \n%s \n%s \n%s \n", "1->Editing Mode", "2->Viewing Mode", "3->Exit", "Please select program mode: ");
                    modeSelect = Integer.parseInt(inp.nextLine());
                    if (modeSelect == 3) {
                        flagBreak = 1;
                        break;
                    }
                    if (0 < modeSelect && modeSelect < 4) {
                        flagMode = 0;
                    } else System.out.println("Wrong Input\n\n");
                } catch (Exception e) {
                    System.out.println("Wrong Input Type\n\n");
                    inp.next();
                }

                if (modeSelect == 1){           //editing mode
                    do {
                        try {
                            flagMode2 = 1;
                            System.out.printf("%s \n%s \n%s \n%s \n", "1->Adding new element",
                                    "2->Deleting one element from last", "3->Go previous menu", "Please select : ");
                            modeSelect = Integer.parseInt(inp.nextLine());
                            if (modeSelect == 3) {
                                flagMode = 1;
                                break;
                            }
                            if (0 < modeSelect && modeSelect < 4) {
                                flagMode2 = 0;
                            } else System.out.println("Wrong Input\n\n");
                        } catch (Exception e) {
                            System.out.println("Wrong Input Type\n\n");
                            inp.next();
                        }

                        if (modeSelect == 1)
                        {
                            do {
                                try {
                                    flagMode4 = 1;
                                    System.out.println(" 1->Adding a House \n 2->Adding a Market \n 3->Adding a Office \n " +
                                                    "4->Adding a Playground \n 5->Go Previous Menu \n Please select : \n");
                                    modeSelect = Integer.parseInt(inp.nextLine());
                                    if (modeSelect == 5) {
                                        flagMode2 = 1;
                                        break;
                                    }
                                    if (0 < modeSelect && modeSelect < 6) {
                                        flagMode4 = 0;
                                    } else System.out.println("Wrong Input\n\n");

                                    switch (modeSelect) {
                                        case 1 :
                                            System.out.println("Please enter side number 0 for back 1 for front: ");
                                            sideHold = Integer.parseInt(inp.nextLine());
                                            if (!(sideHold == 0 || sideHold == 1)) {System.out.println("INVALID"); continue;}
                                            System.out.println("Please enter position number: ");
                                            positionHold = Integer.parseInt(inp.nextLine());
                                            System.out.println("Please enter Height number: ");
                                            heightHold = Integer.parseInt(inp.nextLine());
                                            System.out.println("Please enter Length number: ");
                                            lengthHold = Integer.parseInt(inp.nextLine());
                                            System.out.println("Please enter Room number: ");
                                            roomHold = Integer.parseInt(inp.nextLine());
                                            System.out.println("Please enter owner of the house: ");
                                            ownerHold = inp.nextLine();
                                            System.out.println("Please enter color of the house: ");
                                            colorHold = inp.nextLine();

                                            buildTypes newHouse  = new house(positionHold , heightHold , lengthHold , roomHold , ownerHold , colorHold);
                                            myStreet.add(sideHold ,newHouse);
                                            break;
                                        case 2 :
                                            System.out.println("Please enter side number 0 for back 1 for front: ");
                                            sideHold = Integer.parseInt(inp.nextLine());
                                            if (!(sideHold == 0 || sideHold == 1)) {System.out.println("INVALID"); continue;}

                                            System.out.println("Please enter position number: ");
                                            positionHold = Integer.parseInt(inp.nextLine());
                                            System.out.println("Please enter Height number: ");
                                            heightHold = Integer.parseInt(inp.nextLine());
                                            System.out.println("Please enter Length number: ");
                                            lengthHold = Integer.parseInt(inp.nextLine());
                                            System.out.println("Please enter owner of the market: ");
                                            ownerHold = inp.nextLine();

                                            System.out.println("Please enter opening hour: ");
                                            oHourHold = Integer.parseInt(inp.nextLine());
                                            if (!(0 <= oHourHold && oHourHold <= 23)) {System.out.println("INVALID"); continue;}

                                            System.out.println("Please enter opening minute: ");
                                            oMinuteHold = Integer.parseInt(inp.nextLine());
                                            if (!(0 <= oMinuteHold && oMinuteHold <= 59)) {System.out.println("INVALID"); continue;}

                                            System.out.println("Please enter closing hour: ");
                                            cHourHold = Integer.parseInt(inp.nextLine());
                                            if (!(0 <= cHourHold && cHourHold <= 23)) {System.out.println("INVALID"); continue;}

                                            System.out.println("Please enter closing minute: ");
                                            cMinuteHold = Integer.parseInt(inp.nextLine());
                                            if (!(0 <= cMinuteHold && cMinuteHold <= 59)) {System.out.println("INVALID"); continue;}

                                            buildTypes newMarket = new market(positionHold , heightHold , lengthHold , ownerHold , oHourHold , oMinuteHold , cHourHold , cMinuteHold);
                                            myStreet.add(sideHold , newMarket);
                                            break;
                                        case 3 :
                                            System.out.println("Please enter side number 0 for back 1 for front: ");
                                            sideHold = Integer.parseInt(inp.nextLine());
                                            if (!(sideHold == 0 || sideHold == 1)) {System.out.println("INVALID"); continue;}
                                            System.out.println("Please enter position number: ");
                                            positionHold = Integer.parseInt(inp.nextLine());
                                            System.out.println("Please enter Height number: ");
                                            heightHold = Integer.parseInt(inp.nextLine());
                                            System.out.println("Please enter Length number: ");
                                            lengthHold = Integer.parseInt(inp.nextLine());
                                            System.out.println("Please enter owner of the office: ");
                                            ownerHold = inp.nextLine();
                                            System.out.println("Please enter job type of the office: ");
                                            jobHold = inp.nextLine();

                                            buildTypes newOffice = new office(positionHold , heightHold , lengthHold , ownerHold , jobHold);
                                            myStreet.add(sideHold , newOffice);
                                            break;
                                        case 4 :
                                            System.out.println("Please enter side number 0 for back 1 for front: ");
                                            sideHold = Integer.parseInt(inp.nextLine());
                                            if (!(sideHold == 0 || sideHold == 1)) {System.out.println("INVALID"); continue;}
                                            System.out.println("Please enter position number:");
                                            positionHold = Integer.parseInt(inp.nextLine());
                                            System.out.println("Please enter Length number:");
                                            lengthHold = Integer.parseInt(inp.nextLine());

                                            buildTypes newPlayground = new playground(positionHold , lengthHold);
                                            myStreet.add(sideHold , newPlayground);
                                            break;
                                    }

                                } catch (Exception e) {
                                    System.out.println("Wrong Input Type\n\n");
                                    inp.next();
                                }
                            } while (flagMode4 == 1);
                        }
                        else if (modeSelect == 2){
                            do {
                                try {
                                    flagMode5 = 1;
                                    System.out.println(" 0->Deleting from back side \n 1->Deleting from front side \n");
                                    modeSelect = Integer.parseInt(inp.nextLine());
                                    if (0 <= modeSelect && modeSelect < 2) {
                                        flagMode5 = 0;
                                    } else System.out.println("Wrong Input\n\n");

                                    myStreet.delete(modeSelect);
                                } catch (Exception e) {
                                    System.out.println("Wrong Input Type\n\n");
                                    inp.next();
                                }
                            }while(flagMode5 == 1);
                        }

                    } while (flagMode2 == 1);
                }
                else if (modeSelect == 2) {     //viewing mode
                    do {
                        try {
                            flagMode3 = 1;
                            System.out.println(" " +
                                            "1->Display the total remaining length of lands on the street \n " +
                                            "2->Display the List of Buildings on the street \n " +
                                            "3->Display the number and ratio of length of playgrounds in the street \n " +
                                            "4->Display the total length of street occupied by the markets, houses or offices \n " +
                                            "5->Display the Skyline Silhouette of the street \n " +
                                            "6->Go Previous Menu \n " +
                                            "Please select : \n");
                            modeSelect = Integer.parseInt(inp.nextLine());
                            if (modeSelect == 6) {
                                flagMode = 1;
                                break;
                            }
                            switch (modeSelect) {
                                case 1 :
                                    myStreet.displayEmptyLands();
                                    break;
                                case 2 :
                                    myStreet.displayListOfBuildings();
                                    break;
                                case 3 :
                                    myStreet.displayRatioPlayground();
                                    break;
                                case 4 :
                                    myStreet.displayTotalOccupied();
                                    break;
                                case 5 :
                                    myStreet.displaySilhouette();
                                    break;
                            }

                            if (0 < modeSelect && modeSelect < 7) {
                                flagMode3 = 0;
                            } else System.out.println("Wrong Input\n\n");
                        } catch (Exception e) {
                            System.out.println("Wrong Input Type\n\n");
                            inp.next();
                        }

                    } while (flagMode3 == 1);
                }

            } while (flagMode == 1);
        } while (flagBreak != 1);


    }

}

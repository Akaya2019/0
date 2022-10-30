package hw3.version_ArrayList;

/**
 * CityPlanTest class tests the City Planner program
 */
public class cityPlanTest
{
    /**
     * main method drives all methods in program
     * and call static menu class to user interface
     * @param args unused
     */
    public static void main(String[] args)
    {

        System.out.println("______________________________________________________");
        System.out.println("_____________________DRIVER CODE______________________");
        System.out.println("______________________________________________________\n");

        System.out.println("In this section Driver Code Tests all class' methods\n");

        System.out.println("Let's open a street reference with a length of 50\n");
        streetAL mainStreet = new streetAL(50);

        System.out.println("Let's open a house reference which have following properties ; ");
        System.out.println("Let its name be house1 , its Position be 0 , its Height be 10 , its Length be 10 ,\n" +
                " its Room number be 3 , its Owner be Ali and its color be Purple\n");
        buildTypes house1  = new house(0 , 10 , 10 , 3 , "Ali" , "Purple");

        System.out.println("Let's open another house reference which have following properties ; ");
        System.out.println("Let its name be house2 , its Position be 10 , its Height be 8 , its Length be 10 ,\n" +
                " its Room number be 4 , its Owner be Kaya and its color be Red\n");
        buildTypes house2  = new house(10 , 8 , 10 , 4 , "Kaya" , "Red");

        System.out.println("Let's open another house reference which have following properties ; ");
        System.out.println("Let its name be house3 , its Position be 5 , its Height be 7 , its Length be 14 ,\n" +
                " its Room number be 5 , its Owner be Michel and its color be Green\n");
        buildTypes house3  = new house(5 , 12 , 14 , 5 , "Michel" , "Green");

        System.out.println("In this time Let's open a market reference which have following properties ; ");
        System.out.println("Let its name be Migros , its Position be 20 , its Height be 15 , its Length be 6 ,\n" +
                " its Owner be Ozilhan , its opening time be 9:00 and its closing time be 23:00\n");
        buildTypes Migros = new market(20 , 15 , 6 , "Ozilhan" , 9 , 23);

        System.out.println("Let's open another market reference which have following properties ; ");
        System.out.println("Let its name be Gross , its Position be 26 , its Height be 20 , its Length be 10 ,\n" +
                " its Owner be Jack , its opening time be 8:00 and its closing time be 22:00\n");
        buildTypes Gross = new market(26 , 20 , 10 , "Jack" , 8 , 22);

        System.out.println("In this time Let's open an office reference which have following properties ; ");
        System.out.println("Let its name be Oracle , its Position be 34 , its Height be 16 , its Length be 14 ,\n" +
                " its Owner be Larry , and its job type be Software Development\n");
        buildTypes Oracle = new office(36 , 16 , 12 , "Larry" , "Software Development");

        System.out.println("In this time Let's open an playground reference which have following properties ; ");
        System.out.println("Let its name be playground1 , its Position be 48 and its Length be 2 ,\n");
        buildTypes playground1 = new playground(48 , 2);

        buildTypes test123  = new house(30 , 10 , 125 , 3 , "test" , "test");

        long startTime = System.nanoTime();

        System.out.println("________________TESTING ADD and DELETE METHODS________________\n");

        System.out.println("First ,Let's try to call delete() method to see the error message when street has no building :");
        mainStreet.delete(1);

        System.out.println("Secondly ,Let's try to add house that is longer than the length of the street \n" +
                        "with add() method to see the error message: ");
        mainStreet.add(1, test123);

        System.out.println("Let's add the house1 on front side of the street with add method :");
        mainStreet.add(1, house1);

        System.out.println("Let's add the house2 on front side of the street with add method :");
        mainStreet.add(1, house2);

        System.out.println("Let's add the house3 on back side of the street with add method :");
        mainStreet.add(0, house3);

        System.out.println("Let's add the Migros on front side of the street with add method :");
        mainStreet.add(1, Migros);

        System.out.println("Let's add the Gross on back side of the street with add method :");
        mainStreet.add(0, Gross);

        System.out.println("Let's add the Oracle on front side of the street with add method :");
        mainStreet.add(1, Oracle);

        System.out.println("Let's add the playground1 on back back of the street with add method :");
        mainStreet.add(0, playground1);

        System.out.println("Let's delete the Oracle on front side of the street with delete method :");
        mainStreet.delete(1);
        System.out.println("Let's add again the Oracle on front side of the street with add method :");
        mainStreet.add(1, Oracle);

        System.out.println("________________TESTING displayListOfBuildings() METHOD________________\n");
        System.out.println("In this section displayListOfBuildings() method lists all properties of the buildings.\n");
        mainStreet.displayListOfBuildings();
        System.out.println("\n");

        System.out.println("________________TESTING displayTotalOccupied() METHOD________________\n");
        System.out.println("In this section displayTotalOccupied() method displays\n" +
                "the total length of street occupied by the markets, houses or offices.");
        mainStreet.displayTotalOccupied();
        System.out.println("\n");

        System.out.println("________________TESTING displayRatioPlayground() METHOD________________\n");
        System.out.println("In this section displayRatioPlayground() method displays\n" +
                "the total length of street occupied by playgrounds and prints Ratio of playgrounds to street length.");
        mainStreet.displayRatioPlayground();
        System.out.println("\n");

        System.out.println("________________TESTING displayEmptyLands() METHOD________________\n");
        System.out.println("In this section displayEmptyLands() method displays\n" +
                "the total remaining length of lands on the street.");
        mainStreet.displayEmptyLands();
        System.out.println("\n");

        System.out.println("________________TESTING displaySilhouette() METHOD________________\n");
        System.out.println("In this section displaySilhouette() method displays\n" +
                "the Skyline Silhouette of the street.");
        mainStreet.displaySilhouette();
        System.out.println("\n");

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Time result: " + (double)totalTime/1000000000);

        System.out.println("______________________________________________________");
        System.out.println("_____________________MANUAL CODE______________________");
        System.out.println("______________________________________________________\n");

        System.out.println("In this section User use the program with menu\n");
        streetAL.menu();
        
    }
}

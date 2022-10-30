
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays; 

public class DayofYearSetTest {

    public static void main(String[] args) throws IOException
    {
        System.out.println("______________________________________________________");
        System.out.println("____________________PRINTING SETS_____________________");

        DayofYearSet A = new DayofYearSet();                             //program takes input : month , day format
        A.add(1,32);                                                     //program ignores unreal input ex: january 32 , february 29
        A.add(2,29);
        A.add(2,25);
        A.add(3,4);
        A.add(4,7);
        A.add(5,23);
        //cout << "Set A: " << endl << A << endl ;
        System.out.printf("\nSet A:\n %s" , A.toString());             //also TESTING overridden toString() method
    
        DayofYearSet B = new DayofYearSet(4);                          //testing second constructor
        B.add(3,4);
        B.add(4,7);
        B.add(7,15);
        B.add(8,23);
        System.out.printf("Set B:\n %s" , B.toString());
    
        DayofYearSet C = new DayofYearSet();
        C.add(9,1);
        C.add(10,23);
        C.add(11,14);
        C.add(5,2);
        System.out.printf("Set C:\n %s" , C.toString());
    
        DayofYearSet D = new DayofYearSet();
        D.add(5,3);
        D.add(6,30);
        D.add(7,31);
        D.add(8,28);
        System.out.printf("Set D:\n %s" , D.toString());
    
        DayofYearSet E = new DayofYearSet();
        E.add(12,1);
        E.add(2,12);
        E.add(8,23);
        E.add(7,15);
        System.out.printf("Set E:\n %s" , E.toString());

        System.out.printf("Alive DayofYear objects : %d \n\n" , DayofYearSet.objectsAlive());
        
        A.remove();                                                     //testing remove func
        System.out.printf("\nSet A after remove one element:\n %s" , A.toString());

        E.remove();                                                     //testing remove func
        System.out.printf("Set E after remove one element:\n %s" , E.toString());

        System.out.printf("Alive DayofYear objects after remove operations: %d \n\n" , DayofYearSet.objectsAlive());             //testing static objectsAlive() method for alive DayofYear objects

        System.out.println("______________________________________________________");
        System.out.println("__________________TESTING UNION SETS__________________\n");

        DayofYearSet unionSet_1 = A.unionSet(B);                                 //testing union sets
        System.out.println("union set: A + B");
        System.out.printf(" %s " , unionSet_1);
        System.out.printf("size of this set: %d \n\n" , unionSet_1.size());      //testing size function
    
        DayofYearSet unionSet_2 = C.unionSet(D);                                 //testing union sets
        System.out.println("union set: C + D");
        System.out.printf(" %s " , unionSet_2);
        System.out.printf("size of this set: %d \n\n" , unionSet_2.size());      //testing size function
    

        System.out.println("______________________________________________________");
        System.out.println("_______________TESTING INTERSECTION SETS______________\n");

        DayofYearSet intersectionSet_1 = A.intersectionSet(B);                         //testing intersection sets
        System.out.println("intersection set: A ^ B");
        System.out.printf(" %s " , intersectionSet_1);
        System.out.printf("size of this set: %d \n\n" , intersectionSet_1.size());     //testing size function
    
        DayofYearSet intersectionSet_2 = E.intersectionSet(B);
        System.out.println("intersection set: E ^ B");
        System.out.printf(" %s " , intersectionSet_2);
        System.out.printf("size of this set: %d \n\n" , intersectionSet_2.size());     //testing size function
    

        System.out.println("______________________________________________________");
        System.out.println("________________TESTING DIFFERENCE SETS_______________\n");

        DayofYearSet differenceSet_1 = A.differenceSet(B);                           //testing difference sets
        System.out.println("difference set: A - B");
        System.out.printf(" %s" , differenceSet_1);
    
        DayofYearSet differenceSet_2 = B.differenceSet(A);                           //testing difference sets
        System.out.println("difference set: B - A");
        System.out.printf(" %s" , differenceSet_2);
    

        System.out.println("______________________________________________________");
        System.out.println("________________TESTING DE-MORGAN RULES_______________\n");
        
        DayofYearSet unionSet_AB = A.unionSet(B);                   // union set of A and B
        DayofYearSet complement_AB = unionSet_AB.complementSet();   // !(A union B)
        DayofYearSet complement_A = A.complementSet();              // !A
        DayofYearSet complement_B = B.complementSet();              // !B
        
        System.out.print(" !(A + B) == (!A ^ !B) => ");
        if ( complement_AB.equals( complement_A.intersectionSet(complement_B)) )            //also TESTING overridden equals() method
        {
            System.out.println("True");
        }
        else System.out.println("False");
    
        DayofYearSet unionSet_BC = B.unionSet(C);                   // union set of B and C
        DayofYearSet complement_BC = unionSet_BC.complementSet();   // !(B union C)
        DayofYearSet complement_C = C.complementSet();              // !C
        
        System.out.print(" !(B + C) == (!B ^ !C) => ");
        if ( complement_BC.equals( complement_B.intersectionSet(complement_C)) )
        {
            System.out.println("True");
        }
        else System.out.println("False");

        DayofYearSet unionSet_CD = C.unionSet(D);                   // union set of C and D
        DayofYearSet complement_CD = unionSet_CD.complementSet();   // !(C union D)
        DayofYearSet complement_D = D.complementSet();              // !D
        
        System.out.print(" !(C + D) == (!C ^ !D) => ");
        if ( complement_CD.equals( complement_C.intersectionSet(complement_D)) )
        {
            System.out.println("True");
        }
        else System.out.println("False");
    
        DayofYearSet unionSet_DE = D.unionSet(E);                   // union set of D and E
        DayofYearSet complement_DE = unionSet_DE.complementSet();   // !(D union E)
        DayofYearSet complement_E = E.complementSet();              // !E
        
        System.out.print(" !(D + E) == (!D ^ !E) => ");
        if ( complement_DE.equals( complement_D.intersectionSet(complement_E)) )
        {
            System.out.println("True");
        }
        else System.out.println("False");
    
        DayofYearSet unionSet_EA = E.unionSet(A);                   // union set of E and A
        DayofYearSet complement_EA = unionSet_EA.complementSet();   // !(E union A)
        
        System.out.print(" !(E + A) == (!E ^ !A) => ");
        if ( complement_EA.equals( complement_E.intersectionSet(complement_A)) )
        {
            System.out.println("True");
        }
        else System.out.println("False");
    

        System.out.println("______________________________________________________");
        System.out.println("________________TESTING COMPLEMENT SETS_______________\n");

        System.out.println("Program prints them into txt files..\n");

        String str1 = "complementA.txt";
        A.complementSet().txtPrinter(str1);         //prints the complement set of A into txt

        String str2 = "complementE.txt";
        E.complementSet().txtPrinter(str2);         //prints the complement set of E into txt

        System.out.println("______________________________________________________________________");
        System.out.println("______TESTING DayofYearSet( ArrayList<DayofYear> ) constructor________\n");

        DayofYearSet.DayofYear data1 = new DayofYearSet.DayofYear(4,21);            //put some data into DayofYear object references
        DayofYearSet.DayofYear data2 = new DayofYearSet.DayofYear(3,30);
        DayofYearSet.DayofYear data3 = new DayofYearSet.DayofYear(12,23);
        DayofYearSet.DayofYear data4 = new DayofYearSet.DayofYear(11,29);

        ArrayList< DayofYearSet.DayofYear > elements = new ArrayList< DayofYearSet.DayofYear >( Arrays.asList( data1 , data2 , data3 , data4 ));
        DayofYearSet listCons = new DayofYearSet(elements);             //Testing DayofYearSet( ArrayList<DayofYear> ) constructor

        System.out.printf("\nSet listCons:\n %s" , listCons.toString());

        System.out.println("______________________________________________________________________");


    }
    
}

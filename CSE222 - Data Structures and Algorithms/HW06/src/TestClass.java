package hw6;
/**
 * TestClass class tests the HashTableBinaryChaining , HashTableCombine , MergeSort , QuickSort and NewSort algorithms
 */
public class TestClass {

    /**
     * main method drives all methods in program
     * @param args unused
     */
    public static void main(String[] args){

        System.out.println("______________________________________________________________");
        System.out.println("_________________________DRIVER CODE__________________________");
        System.out.println("______________________________________________________________\n");
        System.out.println("In this section Driver Code Tests all class' methods\n");

        System.out.println("_____________TESTING HashTableBinaryChaining<K , V> class____________\n");

        System.out.println("Let's create a HashTableBinaryChaining<Integer , Integer> reference");
        HashTableBinaryChaining<Integer , Integer> myHashTable1 = new HashTableBinaryChaining<Integer , Integer>();

        System.out.println("Then let's add some data in it with put(key , value) method");
        myHashTable1.put(33 , 33);
        myHashTable1.put(11 , 11);
        myHashTable1.put(55 , 55);
        myHashTable1.put(0 , 0);
        myHashTable1.put(22 , 22);
        myHashTable1.put(44 , 44);
        myHashTable1.put(66 , 66);

        myHashTable1.put(34 , 34);
        myHashTable1.put(12 , 12);
        myHashTable1.put(56 , 56);
        myHashTable1.put(1 , 1);
        myHashTable1.put(23 , 23);
        myHashTable1.put(45 , 45);
        myHashTable1.put(67 , 67);
        System.out.println("Let's print it with toString() method\n");
        System.out.println(myHashTable1.toString());

        System.out.println("Let's remove some elements with remove(key) method");
        System.out.println("Program automatically fixes the order of the tree by key value after remove method");
        myHashTable1.remove(11);
        myHashTable1.remove(56);
        System.out.println(myHashTable1.toString());

        System.out.println("Testing get(key) methods;");
        System.out.print("get(712) => ");
        System.out.println(myHashTable1.get(712));
        System.out.print("get(33) => ");
        System.out.println(myHashTable1.get(33));
        System.out.println();

        System.out.println("Testing size() method:");
        System.out.print("size() => " + myHashTable1.size() + "\n");
        System.out.println();

        System.out.println("Testing isEmpty method:");
        System.out.print("isEmpty() => " + myHashTable1.isEmpty() + "\n");
        System.out.println();

        System.out.println("________________TESTING HashTableCombine<K , V> class________________\n");

        System.out.println("Let's create a HashTableCombine<Integer , Integer> reference");
        HashTableCombine<Integer , Integer> myHashTable2;
        myHashTable2 = new HashTableCombine<>();

        System.out.println("Then let's add some data in it with put(key , value) method");
        myHashTable2.put(24 , 24);
        myHashTable2.put(52 , 52);
        for(int i = 0; i < 20; i++){
            myHashTable2.put(i, i);
        }
        myHashTable2.put(37 , 37);

        System.out.println("Let's print it with toString() method\n");
        System.out.println(myHashTable2.toString());

        System.out.println("Let's remove some elements with remove(key) method");
        System.out.println("Program automatically fixes the next current relation between nodes after remove method");
        myHashTable2.remove(5);
        myHashTable2.remove(18);
        myHashTable2.remove(0);
        System.out.println(myHashTable2.toString());

        System.out.println("Testing get(key) methods;");
        System.out.print("get(990) => ");
        System.out.println(myHashTable2.get(990));
        System.out.print("get(37) => ");
        System.out.println(myHashTable2.get(37));
        System.out.println();

        System.out.println("Testing size() method:");
        System.out.print("size() => " + myHashTable2.size() + "\n");
        System.out.println();

        System.out.println("Testing isEmpty method:");
        System.out.print("isEmpty() => " + myHashTable2.isEmpty() + "\n");
        System.out.println();


        System.out.println("________________TESTING Average Time results for both classes________________\n");
        myHashTable1 = null;
        myHashTable1 = new HashTableBinaryChaining<Integer , Integer>();
        long startTime;
        long endTime;
        long sum = 0;
        for (int j = 0; j < 100; j++) {
            startTime = System.nanoTime();
            for (int i = 0; i < 100; i++) {
                Integer nextInt = (int)(3200 *Math.random());
                myHashTable1.put(nextInt , nextInt);
            }
            endTime = System.nanoTime();
            sum += (endTime - startTime);
        }
        System.out.println("Average Time result for HashTableBinaryChaining: " + (double)(sum/100)/1000000000);

        myHashTable2 = null;
        myHashTable2 = new HashTableCombine<Integer , Integer>();
        sum = 0;
        for (int j = 0; j < 100; j++) {
            startTime = System.nanoTime();
            for (int i = 0; i < 100; i++) {
                Integer nextInt = (int)(3200 *Math.random());
                myHashTable2.put(nextInt , nextInt);
            }
            endTime = System.nanoTime();
            sum += (endTime - startTime);
        }
        System.out.println("Average Time result for HashTableCombine: " + (double)(sum/100)/1000000000);
        System.out.println();


        System.out.println("________________TESTING MergeSort class________________\n");

        int[] myArr = { 12, 11, 13, 5, 6, 7 };

        System.out.println("Initial Array");
        for (int i = 0; i < myArr.length; ++i)
            System.out.print(myArr[i] + " ");

        MergeSort testMerge = new MergeSort();
        testMerge.sort(myArr, 0, myArr.length - 1);

        System.out.println("\nSorted array");
        for (int i = 0; i < myArr.length; ++i)
            System.out.print(myArr[i] + " ");
        System.out.println();

        System.out.println("________________TESTING QuickSort class________________\n");

        int[] myArr2 = { 10, 7, 8, 9, 1, 5 };

        System.out.println("Initial Array");
        for (int i = 0; i < myArr2.length; ++i)
            System.out.print(myArr2[i] + " ");

        QuickSort testQuick = new QuickSort();
        testQuick.quickSort(myArr2, 0, myArr2.length - 1);

        System.out.println("\nSorted array: ");
        for (int i = 0; i < myArr2.length; ++i)
            System.out.print(myArr2[i] + " ");
        System.out.println();

        System.out.println("________________TESTING NewSort class________________\n");

        int[] myArr3 = new int[12];
        for (int i = 0; i < 12; i++) {
            Integer nextInt = (int)(100 *Math.random());
            myArr3[i] = nextInt;
        }

        System.out.println("Initial Array");
        for (int i = 0; i < myArr3.length; ++i)
            System.out.print(myArr3[i] + " ");

        NewSort testNew = new NewSort();
        testNew.newSort(myArr3, 0, myArr3.length - 1);

        System.out.println("\nSorted array: ");
        for (int i = 0; i < myArr3.length; ++i)
            System.out.print(myArr3[i] + " ");
        System.out.println();

        System.out.println("________________TESTING Average Time results for Sort Algorithms________________\n");

        long startTime2;
        long endTime2;
        long sumMerge = 0;
        long sumQuick = 0;
        long sumNew = 0;
        int[] testArr = new int[100];
        int[] temp;
        for (int i = 0; i < 100; i++) {
            Integer nextInt = (int)(3200 *Math.random());
            testArr[i] = nextInt;
        }

        for (int j = 0; j < 1000; j++) {
            temp = testArr;
            startTime2 = System.nanoTime();
            testMerge.sort(temp, 0, temp.length - 1);
            endTime2 = System.nanoTime();
            sumMerge += (endTime2 - startTime2);

            temp = testArr;
            startTime2 = System.nanoTime();
            testQuick.quickSort(temp, 0, temp.length - 1);
            endTime2 = System.nanoTime();
            sumQuick += (endTime2 - startTime2);

            temp = testArr;
            startTime2 = System.nanoTime();
            testNew.newSort(temp, 0, temp.length - 1);
            endTime2 = System.nanoTime();
            sumNew += (endTime2 - startTime2);
        }
        System.out.println("Average Time result for MergeSort: " + (double)(sumMerge/1000)/1000000000);
        System.out.println("Average Time result for QuickSort: " + (double)(sumQuick/1000)/1000000000);
        System.out.println("Average Time result for NewSort: " + (double)(sumNew/1000)/1000000000);

    }
}

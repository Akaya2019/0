package hw4;

/**
 * HW4_Test class tests the recursive functions
 */
public class HW4_Test {


    /**
     * This function search a given string in another given bigger string.
     * The function returns the index of the ith occurrence of the query string and
     * return -1 when the query string doesn’t occur in the big string or the number of occurences is less than i.
     * @param base is the big String we are searching in it.
     * @param target is the target string we will search inside the big string.
     * @param occurence is the number specifying the number of occurrences whose index we are looking for.
     * @param index used for recursive calls inside the function,initial value must be 0
     * @return int- indicates index
     */
    public static int innerStringFinder(String base , String target , int occurence , int index) {

        if(index + target.length() > base.length()) {
            return -1;
        }
        else {
            if (base.substring(index , index + target.length()).equals(target)) {
                occurence--;
                if(occurence == 0) return index;
                return innerStringFinder(base , target , occurence , index + target.length());
            }
            else{
                return innerStringFinder(base , target , occurence , index + 1);
            }
        }
    }

    /**
     * This function finds the number of items in the array between two given integer values.
     * @param base is the array we searched in it.
     * @param big specifies the upper limit of the numbers searched.
     * @param small specifies the lower limit of the numbers searched.
     * @param first used for recursive calls inside the binary search algorithm,initial value must be 0.
     * @param last used for recursive calls inside the binary search algorithm,initial value must be base array’s length-1.
     * @return int-number of specified values.
     */
    public static int binarySearch(int[] base , int big , int small , int first , int last) {

        if (first > last) {
            return 0;
        }
        else {
            int middle = (first+last)/2;

            if (small <= base[middle] && base[middle] <= big){
                return 1 + binarySearch(base , big , small , first , middle-1) + binarySearch(base , big , small , middle+1 , last);
            }
            else if (big < base[middle]) {
                return binarySearch(base , big , small , first , middle-1);
            }
            else {
                return binarySearch(base , big , small , middle+1 , last);
            }
        }
    }

    /**
     * This function finds contiguous subarray/s that the sum of its/theirs items is equal to a given integer value.
     * @param arr is the array we searched in it.
     * @param value the number to be compared with the numbers summed.
     * @param index used for recursive calls inside the function,initial value must be 0.
     */
    public static void findSubarray(int[] arr , int value , int index) {

        int count = 0;
        if(index > arr.length) {
            return;
        }
        else {
            for (int i = index; i < arr.length; i++) {
                count += arr[i];
                if (count == value){
                    System.out.printf("{ %d , %d }\n" , index , i);
                    findSubarray(arr , value , index+1);
                    break;
                }
                else if (count > value) {
                    findSubarray(arr , value , index+1);
                    break;
                }
            }
        }
    }

    /**
     * Just for testing
     * @param integer1 -first parameter
     * @param integer2 -second parameter
     * @return -int
     */
    public static int foo(int integer1 , int integer2) {
        if ((integer1 < 10) || (integer2 < 10))
            return integer1 * integer2;

        int length1 = String.valueOf(integer1).length();
        int length2 = String.valueOf(integer2).length();
        int n = Math.max(length1, length2);
        int half = n/2;

        // split_integer splits the integer into returns two integers
        // from the digit at position half. i.e.,
        // first integer = integer / 2^half
        // second integer = integer % 2^half

        int int1 = (int) (integer1 / Math.pow(2 , half));
        int int2 = (int) (integer1 % Math.pow(2 , half));
        int int3 = (int) (integer2 / Math.pow(2 , half));
        int int4 = (int) (integer2 % Math.pow(2 , half));

        int sub0 = foo (int2, int4);
        int sub1 = foo ((int2 + int1), (int4 + int3));
        int sub2 = foo (int1, int3);

        return (int) ((sub2 * Math.pow(10,(2*half)))+((sub1-sub2-sub0) * Math.pow(10,half))+(sub0));
    }

//    public static void arrayPainter(int[] arr , int index , int num) {
//        if(num > arr.length) {
//            return;
//        }
//        else {
//            int maxSeq = 0;
//            if (arr.length % 2 == 0){maxSeq = arr.length/(num+1);}
//            else {maxSeq = (arr.length+1)/(num+1);}
//
//            while(!(index+num > arr.length)) {
//
//
//
//                index++;
//            }
//        }
//    }

    /**
     * main method Drives all methods in program
     * @param args unused
     */
    public static void main(String[] args) {

        System.out.println("____________Testing Question1___________");

        String base = "AliabcAliAliDsasAli";
        String base2 = "asAli";
        String target = "Ali";
        int occurence = 3;
        int result = innerStringFinder(base , target , occurence, 0);
        System.out.printf("Base String : %s\n" , base);
        System.out.printf("Target String : %s\n" , target);
        System.out.println("Index of " + occurence + ". occurence: " + result + "\n");

        System.out.println("____________Testing Question2___________");
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        int small = 22;
        int big = 45;
        int number = binarySearch(arr , big , small , 0 , arr.length-1);
        System.out.println("The number of values in the array that satisfy the " + small +"<=" + "x" + "<=" + big+ " constraint : " + number + "\n");

        System.out.println("____________Testing Question3___________");
        int[] arr2 = {1 , 2 , 2 , 1 , 1 , 1 , 1 , 1 , 2};
        int value = 4;

        System.out.print("{");
        for (int i = 0; i < arr2.length; i++) {
            if(i == arr2.length-1) System.out.printf(" %d " , arr2[i]);
            else System.out.printf(" %d , " , arr2[i]);
        }
        System.out.print("}\n");

        System.out.println("sought x value : " + value + "\n");
        findSubarray(arr2 , value , 0);
        System.out.println("Subarrays are shown as {Start index , End index}");

        System.out.println("____________Testing Question4___________");
        System.out.printf("\nresult : %d" , foo(12 , 11));

    }
}

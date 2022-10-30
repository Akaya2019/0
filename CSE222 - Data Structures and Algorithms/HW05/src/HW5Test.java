package hw5;
/**
 * HW5Test class tests the BinaryHeap and BinarySearchTree programs
 */
public class HW5Test {

    /**
     * main method drives all methods in program
     * @param args unused
     */
    public static void main(String[] args){

        System.out.println("______________________________________________________");
        System.out.println("_____________________DRIVER CODE______________________");
        System.out.println("______________________________________________________\n");
        System.out.println("In this section Driver Code Tests all class' methods\n");

        System.out.println("________________TESTING BinaryHeap<E> class________________\n");

        System.out.println("Let's create a BinaryHeap<Character> reference\n");
        BinaryHeap<Character> myHeap = new BinaryHeap<>();

        System.out.println("Then let's add some data in it with offer(item) method\n");
        myHeap.offer('A');
        myHeap.offer('B');
        myHeap.offer('C');
        myHeap.offer('Z');
        myHeap.offer('J');
        System.out.println("Let's print it with toString() method\n");
        System.out.println(myHeap.toString());

        System.out.println("Let's remove the root element with poll() method");
        System.out.println("Program automatically fixes the order of the heap by key value after poll method");
        myHeap.poll();
        System.out.println(myHeap.toString());

        System.out.println("Let's try to increment priority value of an element then print heap again");
        myHeap.incrementPriority('J', 4);
        System.out.println(myHeap.toString());

        System.out.println("Let's create a BinaryHeap<Character> reference again");
        System.out.println("Then let's add some data in it with offer(item) method");
        BinaryHeap<Character> myHeap2 = new BinaryHeap<>();
        myHeap2.offer('W');
        myHeap2.offer('X');
        myHeap2.offer('Y');
        myHeap2.offer('O');
        System.out.println("Let's print it with toString() method");
        System.out.println(myHeap2.toString());

        System.out.println("Let's try to increment priority value of an element then print second heap again");
        myHeap2.incrementPriority('Y', 2);
        System.out.println(myHeap2.toString());

        System.out.println("Let's try to merge myHeap and myHeap2 with mergeOperation() method then print new heap");
        myHeap = myHeap.mergeOperation(myHeap2);
        System.out.println(myHeap.toString());

        System.out.println("________________TESTING BinarySearchTree<E> class________________\n");

        System.out.println("Let's create a BinarySearchTree<Integer> reference");
        BinarySearchTree<Integer> myBinary = new BinarySearchTree<>();

        System.out.println("Then let's add some data in it with add(item) method");
        myBinary.add(9);
        myBinary.add(12);
        myBinary.add(5);
        myBinary.add(14);
        myBinary.add(11);
        myBinary.add(3);
        myBinary.add(6);
        System.out.println("Then let's print it with toString() method\n");
        System.out.println(myBinary);

        System.out.println("\nThen let's tests contains(target) method with value 3");
        if(myBinary.contains(3)) System.out.println("Tree contains this value.");
        else System.out.println("Tree doesn't contain this value!!");

        System.out.println("\nThen let's tests find(target) method with value 999 \n(if this value in tree method returns the value; otherwise, returns null)");
        System.out.println(myBinary.find(999));

        System.out.println("\nThen let's tests remove(target) method with value 6\n(if this value in tree method returns true and removes it; otherwise, returns false)");
        myBinary.remove(6);
        System.out.println(myBinary);

        System.out.println("\nThen let's tests remove(target) method with value 12\n(if this value in tree method returns the value and removes it; otherwise, returns null)");
        System.out.println(myBinary.delete(12));
        System.out.println(myBinary);

    }
}

package hw5;

/**
 * This interface is a SearchTree interface which have basic methods of BinarySearchTree
 * @param <E> indicates generics
 */
public interface SearchTree<E> {

    boolean add(E item);
    boolean contains(E target);
    E find(E target);
    E delete(E target);
    boolean remove(E target);

}

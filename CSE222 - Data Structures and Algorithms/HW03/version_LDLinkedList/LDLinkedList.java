package hw3.version_LDLinkedList;

import java.util.AbstractList;
import java.util.List;
/**
 * LDLinkedList is a linked list structure which use lazy deletion strategy.
 * This class implements the List interface and extend the AbstractList in Java Collections Framework.
 */
public class LDLinkedList<E> extends AbstractList<E> implements List<E> {
    /**
     * Node is a static inner class of the LDLinkedList class which used for holding data and reference.
     */
    private static class Node<E> {
        /** Keeps data information of Node<E> class*/
        private E data;
        /** Keeps reference information of Node<E> class*/
        private Node<E> next;

        /**
         * Creates a new node with a null next field
         *
         * @param newData The data stored
         */
        private Node(E newData) {
            data = newData;
            next = null;
        }

        /**
         * Creates a new node that references another node
         *
         * @param newData The data stored
         * @param nodeRef The node referenced by new node
         */
        private Node(E newData, Node<E> nodeRef) {
            data = newData;
            next = nodeRef;
        }
    }

    private Node<E> head = null;
    private Node<E> headGarbage = null;
    private int size = 0;
    private int sizeGarbage = 0;

    /**
     * Adds the element parameter to head of the linked list
     * @param element -our data parameter
     */
    private void addFirst(E element) {
        Node<E> temp = sameElement(element);
        if (temp != null)
        {
            temp.next = head;
            head = temp;
        }
        else
            head = new Node<E>(element, head);

        size++;
    }

    /**
     * Adds the element data next of the node parameter on the linked list
     * @param node  -our node parameter
     * @param element -our data parameter
     */
    private void addAfter(Node<E> node, E element) {
        Node<E> temp = sameElement(element);
        if (temp != null)
        {
            temp.next = node.next;
            node.next = temp;
        }
        else
            node.next = new Node<E>(element, node.next);

        size++;
    }

    /**
     * Adds the element data to head of the garbage linked list
     * @param element -our data parameter
     */
    private void addFirstGarbage(E element) {
        headGarbage = new Node<E>(element, headGarbage);
        sizeGarbage++;
    }

    /**
     * Adds the element data next of the node parameter on the garbage linked list
     * @param node  -our node parameter
     * @param element -our data parameter
     */
    private void addAfterGarbage(Node<E> node, E element) {
        node.next = new Node<E>(element, node.next);
        sizeGarbage++;
    }

    /**
     * Removes first Node on the linked list
     * @return E- returns the removed element
     */
    private E removeFirst() {
        Node<E> temp = head;
        if (head != null) {
            head = head.next;
        }
        if (temp != null) {
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    /**
     * Removes the Node which is after node parameter on the linked list
     * @param node indicates the point node which is previous
     * @return E- returns the removed element
     */
    private E removeAfter(Node<E> node) {
        Node<E> temp = node.next;
        if (temp != null) {
            node.next = temp.next;
            size--;
            return temp.data;
        } else {
            return null;
        }
    }

    /**
     *
     * @param index indicates the order
     * @return E- the element of the list in the order specified by the index
     */
    @Override
    public E get(int index) {
        int counter = 0;
        Node<E> temp = head;
        E rtr = null;

        if (index < size()) {
            if (index == 0) {
                rtr = temp.data;
            } else {
                while (counter != index - 1) {
                    temp = temp.next;
                    counter++;
                }
                rtr = temp.next.data;
            }
        }
        return rtr;
    }

    /**
     * returns the size number of the list
     * @return int -size number
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * returns the size number of the garbage list
     * @return int -sizeGarbage number
     */
    private int sizeGarbage() {
        return sizeGarbage;
    }

    /**
     * Adds e element to end of the linked list
     * @param e element data
     * @return always true
     */
    @Override
    public boolean add(E e) {
        add(size(), e);
        return true;
    }

    /**
     * Returns the reference of the list which is specified by index
     * @param index indicates the order
     * @return reference of the list which is specified by index
     */
    private Node<E> getNode(int index) {
        Node<E> node = head;
        for (int i=0; i<index && node != null; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * Adds e element to specified index of the linked list
     * @param element data
     * @param index indicates the specified order
     */
    @Override
    public void add(int index, E element)
    {
        if (index < 0 || index > size) {
            throw new
                    IndexOutOfBoundsException(Integer.toString(index));
        }
        if (index == 0) {
            addFirst(element);
        }
        else {
            Node<E> node = getNode(index-1);
            addAfter(node, element);
        }
    }

    /**
     * Adds e element to end of the garbage list
     * @param element data
     */
    private void addGarbage(E element)
    {
        int index = sizeGarbage();

        if (index == 0) {
            addFirstGarbage(element);
        }
        else {
            Node<E> node = getNode(index-1);
            addAfterGarbage(node, element);
        }
    }

    /**
     * Removes the specified Node from the list and add it to the garbage list
     * @param index indicates the specified order
     * @return E- returns the removed element
     */
    @Override
    public E remove(int index) {
        int counter = 0;
        Node<E> temp = head;
        E rtr = null;

        if (index < size()) {
            if (index == 0) {
                addGarbage(temp.data);
                rtr = removeFirst();
            } else {
                while (counter != index - 1) {
                    temp = temp.next;
                    counter++;
                }
                addGarbage(temp.next.data);
                rtr = removeAfter(temp);
            }
        }
        return rtr;
    }

    /**
     * prints the list Nodes to the screen properly
     * @return String- for print operation
     */
    @Override
    public String toString() {
        Node<E> nodeRef = head;
        StringBuilder result = new StringBuilder();

        while (nodeRef != null) {
            result.append(nodeRef.data);
            if (nodeRef.next != null) {
                result.append(" ==> ");
            }
            nodeRef = nodeRef.next;
        }
        return result.toString();
    }

    /**
     * sameElement method controls adding element's node is already exist or not
     * @param element indicates data
     * @return Node<E> indicates the reference of already exist Node element
     */
    private Node<E> sameElement(E element) {
        int ans = 0;
        Node<E> temp = headGarbage;

        while (temp != null) {
            if (element.getClass().getSimpleName().equals(temp.data.getClass().getSimpleName())) {
                if (element instanceof house
                        && ((house) element).getOwner().equals(((house) temp.data).getOwner())
                        && ((house) element).getColor().equals(((house) temp.data).getColor())
                        && ((house) element).getRoomNum() == ((house) temp.data).getRoomNum()
                        && ((house) element).getPosition() == ((house) temp.data).getPosition()
                        && ((house) element).getHeight() == ((house) temp.data).getHeight()
                        && ((house) element).getLength() == ((house) temp.data).getLength()) {
                    ans = 1;
                    break;
                } else if (element instanceof market
                        && ((market) element).getOwner().equals(((market) temp.data).getOwner())
                        && ((market) element).getOpenHour() == ((market) temp.data).getOpenHour()
                        && ((market) element).getOpenMinute() == ((market) temp.data).getOpenMinute()
                        && ((market) element).getCloseHour() == ((market) temp.data).getCloseHour()
                        && ((market) element).getCloseMinute() == ((market) temp.data).getCloseMinute()
                        && ((market) element).getPosition() == ((market) temp.data).getPosition()
                        && ((market) element).getHeight() == ((market) temp.data).getHeight()
                        && ((market) element).getLength() == ((market) temp.data).getLength()) {
                    ans = 1;
                    break;
                } else if (element instanceof office
                        && ((office) element).getOwner().equals(((office) temp.data).getOwner())
                        && ((office) element).getJobType().equals(((office) temp.data).getJobType())
                        && ((office) element).getPosition() == ((office) temp.data).getPosition()
                        && ((office) element).getHeight() == ((office) temp.data).getHeight()
                        && ((office) element).getLength() == ((office) temp.data).getLength()) {
                    ans = 1;
                    break;
                } else if (element instanceof playground
                        && ((playground) element).getPosition() == ((playground) temp.data).getPosition()
                        && ((playground) element).getHeight() == ((playground) temp.data).getHeight()
                        && ((playground) element).getLength() == ((playground) temp.data).getLength()) {
                    ans = 1;
                    break;
                }
            }
            temp = temp.next;
        }
        if (ans == 1) return temp;
        else return null;
    }
}
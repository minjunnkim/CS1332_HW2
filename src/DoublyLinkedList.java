/**
 * Your implementation of a non-circular DoublyLinkedList with a tail pointer.
 *
 * @author Minjun Kim
 * @version 1.0
 * @userid mkim925
 * @GTID 903873051
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class DoublyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index. Don't forget to consider whether
     * traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws IndexOutOfBoundsException if index < 0 or index > size
     * @throws IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data into data structure.");
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index must be within the DoublyLinkedList's bounds.");
        }

        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data);

        if (size == 0) {
            // Empty list, add as the only node.
            head = newNode;
            tail = newNode;
        } else if (index == 0) {
            // Adding at the front.
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        } else if (index == size) {
            // Adding at the back.
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        } else {
            // Adding at the specified index.
            DoublyLinkedListNode<T> current = getNodeAtIndex(index);
            DoublyLinkedListNode<T> previous = current.getPrevious();
            previous.setNext(newNode);
            newNode.setPrevious(previous);
            newNode.setNext(current);
            current.setPrevious(newNode);
        }

        size++;
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        addAtIndex(0, data);
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        addAtIndex(size, data);
    }

    /**
     * Removes and returns the element at the specified index. Don't forget to
     * consider whether traversing the list from the head or tail is more
     * efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("List is empty.");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be within the DoublyLinkedList's bounds.");
        }

        DoublyLinkedListNode<T> current;

        if (size == 1) {
            // Removing the only element in the list.
            current = head;

            head = null;
            tail = null;
        } else if (index == 0) {
            // Removing the first element.

            current = head;

            DoublyLinkedListNode<T> newHead = head.getNext();
            newHead.setPrevious(null);
            head = newHead;

        } else if (index == size - 1) {
            // Removing the last element.

            current = tail;

            DoublyLinkedListNode<T> newTail = tail.getPrevious();
            newTail.setNext(null);
            tail = newTail;

        } else {
            // Removing an element in the middle.
            current = getNodeAtIndex(index);
            DoublyLinkedListNode<T> previous = current.getPrevious();
            DoublyLinkedListNode<T> next = current.getNext();
            previous.setNext(next);
            next.setPrevious(previous);
        }

        size--;
        return current.getData();
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        return removeAtIndex(size - 1);
    }

    /**
     * Returns the element at the specified index. Don't forget to consider
     * whether traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("List is empty.");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be within the DoublyLinkedList's bounds.");
        }

        DoublyLinkedListNode<T> current;

        if (index == 0) {
            current = head;
        } else if (index == size-1) {
            current = tail;
        } else {
            current = getNodeAtIndex(index);
        }
        return current.getData();
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(1) if data is in the tail and O(n) for all other cases.
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        if (isEmpty()) {
            throw new java.util.NoSuchElementException("List is empty");
        }

        DoublyLinkedListNode<T> current = tail;
        while (current != null) {
            if (current.getData().equals(data)) {
                return removeAtIndex(indexOfFromBack(data));
            }
            current = current.getPrevious();
        }

        throw new java.util.NoSuchElementException("Data not found");
    }

    /**
     * Returns an array representation of the linked list. If the list is
     * size 0, return an empty array.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length size holding all of the objects in the
     * list in the same order
     */
    public Object[] toArray() {
        Object[] array = new Object[size];
        DoublyLinkedListNode<T> current = head;
        int index = 0;
        while (current != null) {
            array[index] = current.getData();
            current = current.getNext();
            index++;
        }
        return array;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public DoublyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public DoublyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    private DoublyLinkedListNode<T> getNodeAtIndex(int index) {
        DoublyLinkedListNode<T> current;
        if (index < size / 2) {
            // Start from the head and move forward.
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else {
            // Start from the tail and move backward.
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.getPrevious();
            }
        }
        return current;
    }

    private int indexOfFromBack(T data) {
        DoublyLinkedListNode<T> current;
        current = tail;

        int index = 0;
        while (current != null) {
            if (current.getData().equals(data)) {
                return index;
            }

            current = current.getPrevious();
            index++;

        }
        return -1;
    }
}

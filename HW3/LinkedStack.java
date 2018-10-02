import java.util.NoSuchElementException;

/**
 * Your implementation of a linked stack. It should NOT be circular.
 *
 * @author Edward Xia
 * @userid exia3
 * @GTID 903191169
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("Can not remove"
                    + " from empty queue.");
        }
        T removed = head.getData();
        head = head.getNext();
        size--;
        return removed;
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null.");
        } else if (head == null) {
            head = new LinkedNode<>(data, null);
            size++;
        } else {
            head = new LinkedNode<>(data, head);
            size++;
        }
    }

    @Override
    public T peek() {
        if (size == 0) {
            return null;
        } else {
            return head.getData();
        }
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the head of this stack.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }
}
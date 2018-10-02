/**
 * Your implementation of a circular singly linked list.
 *
 * @author Edward Xia
 * @userid exia3
 * @GTID 903191169
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addAtIndex(T data, int index) {
        LinkedListNode<T> node = new LinkedListNode<>(data, head);
        LinkedListNode<T> head2 = head;
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index
                    + " can not be negative.");
        } else if (index > size) {
            throw new IndexOutOfBoundsException("Index: " + index
                    + " can not be greater than list size.");
        } else if (data == null) {
            throw new IllegalArgumentException("Data can not be null.");
        } else if (index == 0) {
            addToFront(data);
        } else if (index == size()) {
            addToBack(data);
        } else {
            index--;
            for (int i = 0; i <= index; i++) {
                if (index == i) {
                    LinkedListNode<T> newNode = head2.getNext();
                    node.setNext(newNode);
                    head2.setNext(node);
                }
                head2 = head2.getNext();
            }
            size++;
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null.");
        } else if (head == null) {
            LinkedListNode<T> newNode = new LinkedListNode<>(data);
            head = newNode;
            newNode.setNext(head);
        } else {
            LinkedListNode<T> newNode = new LinkedListNode<>(head.getData(),
                    head.getNext());
            head.setData(data);
            head.setNext(newNode);
        }
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null.");
        } else if (head == null) {
            addToFront(data);
        } else {
            LinkedListNode<T> newNode  = new LinkedListNode<>(head.getData(),
                    head.getNext());
            head.setData(data);
            head.setNext(newNode);
            head = newNode;
            size++;
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index
                    + "  can not be negative.");
        } else if (index > (size - 1)) {
            throw new IndexOutOfBoundsException("Index: " + index
                    + "   can not be greater than list size.");
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == size()) {
            return removeFromBack();
        } else {
            LinkedListNode<T> node = head;
            for (int i = 0; i < (index - 1); i++) {
                node = node.getNext();
            }
            LinkedListNode<T> removedNode = node.getNext();
            LinkedListNode<T> next = removedNode.getNext();
            node.setNext(next);
            size--;
            return removedNode.getData();
        }
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        T removed = head.getData();
        if (size == 1) {
            head = null;
        } else {
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
        }
        size--;
        return removed;
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        LinkedListNode<T> last = head;
        if (size == 1) {
            T removed = head.getData();
            head = null;
            size--;
            return removed;
        } else {
            for (int i = 0; i < (size - 2); i++) {
                last = last.getNext();
            }
            T removed = last.getNext().getData();
            last.setNext(head);
            size--;
            return removed;
        }
    }

    @Override
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null.");
        }
        if (size == 0) {
            return null;
        }
        T removed = null;
        LinkedListNode<T> node = head;
        LinkedListNode<T> previous = null;
        LinkedListNode<T> last = null;
        if (head.getData() == data) {
            last = head;
        }
        for (int i = 0; i < size - 1; i++) {
            if (node.getNext().getData() == data) {
                previous = node;
                last = node.getNext();
            }
            node = node.getNext();
        }
        if ((previous == null) & (last != null)) {
            return removeFromFront();
        }
        if ((previous != null) & (last != null)) {
            removed = last.getData();
            previous.setNext(last.getNext());
            size--;
        }
        return removed;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index
                    + " can not be negative.");
        } else if (index > (size - 1)) {
            throw new IndexOutOfBoundsException("Index: " + index
                    + " can not be greater than list size.");
        }
        LinkedListNode<T> ind = head;
        for (int i = 0; i < index; i++) {
            ind = ind.getNext();
        }
        return ind.getData();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        LinkedListNode<T> node = head;
        for (int i = 0; i < size; i++) {
            array[i] = node.getData();
            node = node.getNext();
        }
        return array;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}
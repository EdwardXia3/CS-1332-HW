/**
 * Your implementation of an ArrayList.
 *
 * @author Edward Xia
 * @userid exia3
 * @GTID 903191169
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size = 0;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        this.backingArray =
                (T[]) new Object[ArrayListInterface.INITIAL_CAPACITY];
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index can't be negative");
        } else if (index > size()) {
            throw new IndexOutOfBoundsException("Index is not within "
                    + "the Array Size");
        }
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data "
                    + "into data structure");
        }
        if (index == size()) {
            addToBack(data);
        } else {
            size = size + 1;
        }
        int capacity = backingArray.length;
        if (size() == capacity) {
            capacity = capacity * 2;
        }
        Object[] newarr = (T[]) new Object[capacity];
        for (int i = 0; i < size(); i++) {
            if (i < index) {
                newarr[i] = backingArray[i];
            } else if (i == index) {
                newarr[i] = data;
            } else {
                newarr[i] = backingArray[i - 1];
            }
        }
        backingArray = (T[]) newarr;

    }

    @Override
    public void addToFront(T data) {
        addAtIndex(0, data);
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data "
                    + "into data structure");
        }
        int capacity = backingArray.length;
        if (size() == capacity) {
            capacity = capacity * 2;
            Object[] newarr = (T[]) new Object[capacity];
            for (int i = 0; i < size(); i++) {
                    newarr[i] = backingArray[i];
            }
            backingArray = (T[]) newarr;
        }
        backingArray[size()] = data;
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index can't be negative");
        } else if (index > size()) {
            throw new IndexOutOfBoundsException("Index is not within "
                    + "the Array Size");
        }
        T removed = backingArray[index];
        if (index == size()) {
            removeFromBack();
        } else {
            for (int i = index; i < size(); i++) {
                backingArray[i] = backingArray[i + 1];
            }
            size--;
        }
        return removed;
    }

    @Override
    public T removeFromFront() {
        T removed = backingArray[0];
        for (int i = 0; i < size(); i++) {
            backingArray[i] = backingArray[i + 1];
        }
        size--;
        return removed;
    }

    @Override
    public T removeFromBack() {
        T removed = backingArray[size() - 1];
        backingArray[size() - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T get(int index) {
        return backingArray[index];
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Object[ArrayListInterface.INITIAL_CAPACITY ];
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}

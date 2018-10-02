import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a max heap.
 *
 * @author Edward Xia
 * @userid exia3
 * @GTID 903191169
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the Build Heap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     *
     * The initial array before the Build Heap algorithm takes place should
     * contain the data in the same order as it appears in the ArrayList.
     *
     * The {@code backingArray} should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null.");
        }
        size = data.size();
        this.backingArray = (T[]) new Comparable[2 * size + 1];
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == null) {
                throw new IllegalArgumentException("Data contains null data.");
            }
            backingArray[i + 1] = data.get(i);
        }
        for (int i = size / 2; i > 0; i--) {
            heapify(i);
        }
    }

    /**
     * Helper function for the MaxHeap constructor which swaps the child and
     * parent if the child is larger.
     *
     * @param i index of the parent to be compared with it's child
     */
    public void heapify(int i) {
        T curr = backingArray[i];
        int childI = 2 * i;
        if ((childI <= size) && (backingArray[childI + 1] != null)
            && (backingArray[childI + 1].compareTo(backingArray[childI]) > 0)) {
            childI++;
        }
        if ((childI <= size) && (curr.compareTo(backingArray[childI]) < 0)) {
            backingArray[i] = backingArray[childI];
            backingArray[childI] = curr;
            heapify(childI);
        }
    }
    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Data can not be null.");
        }
        if (backingArray.length == (size + 1)) {
            T[] arr = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 0; i < size; i++) {
                arr[i + 1] = backingArray[i + 1];
            }
            backingArray = arr;
        }
        if (size == 0) {
            backingArray[1] = item;
            size++;
        } else {
            size++;
            backingArray[size] = item;
            int parent = size / 2;
            int i = size;
            while (parent > 0 && item.compareTo(backingArray[parent]) > 0) {
                backingArray[i] = backingArray[parent];
                backingArray[parent] = item;
                parent = parent / 2;
                i = i / 2;
            }
        }
    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty.");
        }
        T root = backingArray[1];
        if (size == 1) {
            backingArray[1] = null;
        } else {
            backingArray[1] = backingArray[size];
            backingArray[size] = null;
            if (size != 2) {
                int parent = 1;
                int child = 2;
                while (child < size) {
                    if ((child + 1) < size && backingArray[child].compareTo(
                            backingArray[child + 1]) < 0) {
                        child++;
                    }
                    if (backingArray[child].compareTo(
                            backingArray[parent]) > 0) {
                        T temp = backingArray[parent];
                        backingArray[parent] = backingArray[child];
                        backingArray[child] = temp;
                        parent = child;
                        child = parent * 2;
                    } else {
                        child = size + 1;
                    }
                }
            }
        }
        size--;
        return root;
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

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

}

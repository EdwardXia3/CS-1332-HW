import java.util.Comparator;
import java.util.Random;
import java.util.ArrayList;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Edward Xia
 * @userid exia3
 * @GTID 903191169
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array can not be null");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator can not be null");
        }
        int i = 0;
        boolean swapped = true;
        while (i < (arr.length - 1) && swapped) {
            swapped = false;
            for (int j = 0; j < (arr.length - i - 1); j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            i = i + 1;
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array can not be null");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator can not be null");
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && (comparator.compare(arr[j - 1], arr[j]) > 0)) {
                T temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
                j = j - 1;
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array can not be null");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator can not be null");
        }
        for (int i = 0; i < arr.length; i++) {
            int minInd = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[minInd]) < 0) {
                    minInd = j;
                }
            }
            T temp = arr[minInd];
            arr[minInd] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("Array can not be null");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator can not be null");
        } else if (rand == null) {
            throw new IllegalArgumentException("Rand can not be null");
        }
        quickSort(arr, comparator, rand, 0, arr.length);
    }

    /**
     * The quickSort method with more inputs to allow recursive properties
     * for the main quickSort method
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param left the left array
     * @param right the right array
     * @param <T> data type to sort
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                 Random rand, int left, int right) {
        if (right - left - 2 < 0) {
            return;
        }
        T temp = arr[left];
        int pivot = rand.nextInt(right - left) + left;
        arr[left] = arr[pivot];
        arr[pivot] = temp;
        int leftIndex = left + 1;
        int rightIndex = right - 1;
        while (leftIndex <= rightIndex) {
            while (leftIndex <= rightIndex
                    && comparator.compare(arr[leftIndex], arr[left]) <= 0) {
                leftIndex++;
            }

            while (leftIndex <= rightIndex
                    && comparator.compare(arr[rightIndex], arr[left]) >= 0) {
                rightIndex--;
            }
            if (leftIndex < rightIndex) {
                temp = arr[leftIndex];
                arr[leftIndex] = arr[rightIndex];
                arr[rightIndex] = temp;
                leftIndex++;
                rightIndex--;
            }
        }
        temp = arr[left];
        arr[left] = arr[rightIndex];
        arr[rightIndex] = temp;
        quickSort(arr, comparator, rand, left, rightIndex);
        quickSort(arr, comparator, rand, rightIndex + 1, right);
    }


    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array can not be null");
        } else if (comparator == null) {
            throw new IllegalArgumentException("Comparator can not be null");
        } else if (arr.length <= 1) {
            return;
        }
        int midInd = arr.length / 2;
        T[] leftArr = (T[]) new Object[midInd];
        T[] rightArr = (T[]) new Object[arr.length - midInd];
        for (int i = 0; i < leftArr.length; i++) {
            leftArr[i] = arr[i];
        }
        for (int i = 0; i < rightArr.length; i++) {
            rightArr[i] = arr[midInd + i];
        }
        mergeSort(leftArr, comparator);
        mergeSort(rightArr, comparator);
        int leftInd = 0;
        int rightInd = 0;
        int currInd = 0;
        while (leftInd < midInd && rightInd < (arr.length - midInd)) {
            if (comparator.compare(leftArr[leftInd], rightArr[rightInd]) <= 0) {
                arr[currInd] = leftArr[leftInd];
                leftInd++;
            } else {
                arr[currInd] = rightArr[rightInd];
                rightInd++;
            }
            currInd++;
        }
        while (leftInd < midInd) {
            arr[currInd] = leftArr[leftInd];
            leftInd++;
            currInd++;
        }
        while (rightInd < (arr.length - midInd)) {
            arr[currInd] = rightArr[rightInd];
            rightInd++;
            currInd++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array can not be null");
        }
        ArrayList[] buckets = new ArrayList[19];
        for (int i = 0; i < 19; i++) {
            buckets[i] = new ArrayList();
        }
        int it = 0;
        for (int i = 0; i < arr.length; i++) {
            int dec = 10;
            int d = 2;
            int num = arr[i];
            if (num < 0) {
                num = num * -1;
            }
            while (num / dec >= 1) {
                d++;
                dec = dec * 10;
            }
            if (i == 0) {
                it = d;
            } else {
                if (d > it) {
                    it = d;
                }
            }
        }
        int bucket;
        int ten = 10;
        for (int i = 1; i <= it; i++) {
            for (int j : arr) {
                bucket = j % ten / (ten / 10) + 9;
                buckets[bucket].add(j);
            }
            int index = 0;
            for (ArrayList j : buckets) {
                while (!j.isEmpty()) {
                    arr[index] = (int) j.remove(0);
                    index++;

                }
            }
            ten = ten * 10;
        }
    }
}

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Basic tests for the array-backed stack and queue classes.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class StacksQueuesStudentTests {

    private StackInterface<Integer> stack;
    private QueueInterface<Integer> queue;

    public static final int TIMEOUT = 200;

    private void printStack() {
        LinkedNode<Integer> element = ((LinkedStack<Integer>) stack).getHead();
        System.out.print("[ ");
        if (stack.size() != 0) {
            for (int i = 0; i < stack.size(); i++) {
                System.out.print(element.getData() + " ");
                element = element.getNext();
            }
        }
        System.out.print("]");
        System.out.println();
    }

    private void printArrayStack() {
        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();
        System.out.print("[ ");
       for (int i = 0; i < backingArray.length; i++) {
           if (backingArray[i] == null)
               System.out.print("null ");
           else
               System.out.print(backingArray[i] + " ");
       }
       System.out.println("]");
    }

    private void printArrayQueue() {
        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        System.out.print("[ ");
        for (int i = 0; i < backingArray.length; i++) {
            if (backingArray[i] == null)
                System.out.print("null ");
            else
                System.out.print(backingArray[i] + " ");
        }
        System.out.println("]");
    }

    private void printQueue() {
        LinkedNode<Integer> element = ((LinkedQueue<Integer>) queue).getHead();
        System.out.print("[ ");
        if (queue.size() != 0) {
            for (int i = 0; i < queue.size(); i++) {
                System.out.print(element.getData() + " ");
                element = element.getNext();
            }
        }
        System.out.print("]");
        System.out.println();
    }


    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLinkedStackPush() {
        System.out.println("Testing push()...");
        stack = new LinkedStack<>();

        stack.push(3);
        printStack();
        assertEquals(1, stack.size());
        assertEquals(3, stack.peek().intValue());
        stack.push(2);
        assertEquals(2, stack.size());
        printStack();
        stack.push(1);
        printStack();
        assertEquals(3, stack.size());

        //checking if it throws an Illegal Argument Exception
        stack.push(null);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackPeek() {
        System.out.println("Testing peek()...");
        stack = new LinkedStack<>();
        printStack();
        assertEquals(null, stack.peek());
        stack.push(4);
        printStack();
        assertEquals(4, stack.peek().intValue());
        stack.push(3);
        printStack();
        assertEquals(3, stack.peek().intValue());
        stack.push(2);
        printStack();
        assertEquals(2, stack.peek().intValue());
        stack.push(1);
        printStack();
        assertEquals(1, stack.peek().intValue());

        stack.pop();
        printStack();
        assertEquals(2, stack.peek().intValue());
        stack.pop();
        printStack();
        assertEquals(3, stack.peek().intValue());
        stack.pop();
        printStack();
        assertEquals(4, stack.peek().intValue());
        stack.pop();
        assertEquals(0, stack.size());
        printStack();
        assertEquals(null, stack.peek());

    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedStackPop() {
        stack = new LinkedStack<>();
        System.out.println("Testing pop()...");
        stack.push(3);
        stack.push(2);
        stack.push(1);
        printStack();
        assertEquals(3, stack.size());
        assertEquals(1, stack.pop().intValue());
        assertEquals(2, stack.size());
        printStack();
        assertEquals(2, stack.pop().intValue());
        assertEquals(1, stack.size());
        printStack();
        assertEquals(3, stack.pop().intValue());
        assertEquals(0, stack.size());
        printStack();

        //checking if it throws a NoSuchElementException
        stack.pop();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLinkedQueueEnqueue() {
        System.out.println("Testing enqueue()...");
        queue = new LinkedQueue<Integer>();

        queue.enqueue(1);
        assertEquals(1, queue.size());
        printQueue();
        queue.enqueue(2);

        printQueue();
        queue.enqueue(3);
        printQueue();
        assertEquals(3, queue.size());


        //checking if it throws an Illegal Argument Exception
        queue.enqueue(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testLinkedQueueDequeue() {
        System.out.println("Testing dequeue()...");
        queue = new LinkedQueue<Integer>();
        printQueue();
        queue.enqueue(1);
        assertEquals(1, queue.size());
        printQueue();
        queue.enqueue(2);
        printQueue();
        queue.enqueue(3);
        printQueue();
        assertEquals(3, queue.size());
        assertEquals(1, queue.dequeue().intValue());
        printQueue();
        assertEquals(2, queue.size());
        assertEquals(2, queue.dequeue().intValue());
        printQueue();
        assertEquals(1, queue.size());
        assertEquals(3, queue.dequeue().intValue());
        printQueue();
        assertEquals(0, queue.size());

        //checking if it throws a NoSuchElement
        queue.dequeue();
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueuePeek() {
        System.out.println("Testing peek()...");
        queue = new LinkedQueue<Integer>();
        printQueue();
        queue.enqueue(1);
        assertEquals(1, queue.peek().intValue());
        printQueue();
        queue.enqueue(2);
        assertEquals(1, queue.peek().intValue());
        printQueue();
        queue.enqueue(3);
        assertEquals(1, queue.peek().intValue());
        printQueue();
        queue.dequeue();
        printQueue();
        assertEquals(2, queue.peek().intValue());
        queue.dequeue();
        printQueue();
        assertEquals(3, queue.peek().intValue());
        queue.dequeue();
        printQueue();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testArrayStackPush() {
        System.out.println("Testing push()...");
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());
        printArrayStack();
        stack.push(1);
        printArrayStack();
        stack.push(2);
        printArrayStack();
        stack.push(3);
        printArrayStack();
        stack.push(4);
        printArrayStack();
        stack.push(5);
        printArrayStack();
        stack.push(6);
        printArrayStack();
        stack.push(7);
        printArrayStack();
        stack.push(8);
        printArrayStack();
        stack.push(9);
        printArrayStack();
        stack.push(10);
        printArrayStack();
        stack.push(11);
        printArrayStack();
        stack.push(12);
        printArrayStack();
        stack.push(13);
        printArrayStack();
        stack.push(14);
        printArrayStack();

        assertEquals(14, stack.size());

        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY*2];
        expected[0] = 1;
        expected[1] = 2;
        expected[2] = 3;
        expected[3] = 4;
        expected[4] = 5;
        expected[5] = 6;
        expected[6] = 7;
        expected[7] = 8;
        expected[8] = 9;
        expected[9] = 10;
        expected[10] = 11;
        expected[11] = 12;
        expected[12] = 13;
        expected[13] = 14;
        expected[14] = null;
        expected[15] = null;
        expected[16] = null;
        expected[17] = null;
        expected[18] = null;
        expected[19] = null;
        expected[20] = null;
        expected[21] = null;

        assertArrayEquals(expected, backingArray);
        assertEquals(22, backingArray.length);

        //checking if you're throwing an IllegalArgumentException
        stack.push(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayStackPop() {
        System.out.println("Testing pop()...");
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        stack.push(34);
        printArrayStack();
        stack.push(29);
        printArrayStack();
        stack.push(48);
        printArrayStack();
        stack.push(59);
        printArrayStack();
        assertEquals((Integer) 59, stack.pop());
        printArrayStack();

        assertEquals(3, stack.size());

        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        //make sure you set the removed spot to null
        expected[3] = null;

        assertArrayEquals(expected, backingArray);

        assertEquals((Integer) 48, stack.pop());
        printArrayStack();
        assertEquals((Integer) 29, stack.pop());
        printArrayStack();
        assertEquals((Integer) 34, stack.pop());
        printArrayStack();

        //testing NoSuchElementException
        stack.pop();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPeek() {
        System.out.println("Testing peek()...");
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());
        printArrayStack();
        stack.push(34);
        assertEquals((Integer)34, stack.peek());
        printArrayStack();
        stack.push(29);
        assertEquals((Integer)29, stack.peek());
        printArrayStack();
        stack.push(48);
        assertEquals((Integer)48, stack.peek());
        printArrayStack();
        stack.push(59);
        assertEquals((Integer)59, stack.peek());
        printArrayStack();

        assertEquals(4, stack.size());

        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);


    }


    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testArrayQueueEnqueue() {
        System.out.println("Testing enqueue()...");
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());
        printArrayQueue();
        queue.enqueue(1);
        printArrayQueue();
        queue.enqueue(2);
        printArrayQueue();
        queue.enqueue(3);
        printArrayQueue();
        queue.enqueue(4);
        printArrayQueue();
        queue.enqueue(5);
        printArrayQueue();
        queue.enqueue(6);
        printArrayQueue();
        queue.enqueue(7);
        printArrayQueue();
        queue.enqueue(8);
        printArrayQueue();
        queue.enqueue(9);
        printArrayQueue();
        queue.enqueue(10);
        queue.enqueue(11);
        queue.enqueue(12);
        queue.enqueue(13);
        queue.enqueue(14);
        queue.enqueue(15);
        queue.enqueue(16);
        queue.enqueue(17);
        queue.enqueue(18);
        queue.enqueue(19);
        queue.enqueue(20);
        queue.enqueue(21);
        queue.enqueue(22);
        printArrayQueue();
        assertEquals(22, queue.size());

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY*2];
        expected[0] = 1;
        expected[1] = 2;
        expected[2] = 3;
        expected[3] = 4;
        expected[4] = 5;
        expected[5] = 6;
        expected[6] = 7;
        expected[7] = 8;
        expected[8] = 9;
        expected[9] = 10;
        expected[10] = 11;
        expected[11] = 12;
        expected[12] = 13;
        expected[13] = 14;
        expected[14] = 15;
        expected[15] = 16;
        expected[16] = 17;
        expected[17] = 18;
        expected[18] = 19;
        expected[19] = 20;
        expected[20] = 21;
        expected[21] = 22;

        assertArrayEquals(expected, backingArray);
        assertEquals(22, backingArray.length);

        //testing IllegalArgException
        queue.enqueue(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testArrayQueueDequeue() {
        System.out.println("Testing dequeue()...");
        System.out.println("Short test: ");
        queue = new ArrayQueue<>();
        queue.enqueue(1);
        printArrayQueue();
        queue.enqueue(2);
        printArrayQueue();
        queue.dequeue();
        printArrayQueue();
        queue.enqueue(3);
        printArrayQueue();
        queue.dequeue();
        printArrayQueue();
        queue.dequeue();
        printArrayQueue();
        queue.enqueue(1);
        printArrayQueue();
        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY];
        expected[0] = 1;
        assertArrayEquals(expected, backingArray);


        System.out.println("\nLong test:");

        queue = new ArrayQueue<>();
        queue.enqueue(1);
        printArrayQueue();
        queue.enqueue(2);
        printArrayQueue();
        queue.enqueue(3);
        printArrayQueue();
        queue.enqueue(4);
        printArrayQueue();
        queue.enqueue(5);
        printArrayQueue();
        queue.enqueue(6);
        printArrayQueue();
        queue.enqueue(7);
        printArrayQueue();
        queue.enqueue(8);
        printArrayQueue();
        queue.enqueue(9);
        printArrayQueue();
        queue.enqueue(10);
        queue.enqueue(11);
        queue.enqueue(12);
        queue.enqueue(13);
        queue.enqueue(14);
        queue.enqueue(15);
        queue.enqueue(16);
        queue.enqueue(17);
        queue.enqueue(18);
        queue.enqueue(19);
        queue.enqueue(20);
        queue.enqueue(21);
        queue.enqueue(22);
//        assertEquals(22, queue.size());

        assertEquals((Integer) 1, queue.dequeue());
        printArrayQueue();
        assertEquals(21, queue.size());
        assertEquals((Integer)2, queue.peek());
        assertEquals((Integer) 2, queue.dequeue());
        printArrayQueue();

        queue.enqueue(23);
        //assertEquals((Integer) 3, queue.peek());
        printArrayQueue();
        queue.enqueue(24);
        printArrayQueue();
        assertEquals((Integer) 3, queue.peek());
        assertEquals(22, queue.size());
        backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();
        expected = new Object[22];
        expected[0] = 23;
        expected[1] = 24;
        expected[2] = 3;
        expected[3] = 4;
        expected[4] = 5;
        expected[5] = 6;
        expected[6] = 7;
        expected[7] = 8;
        expected[8] = 9;
        expected[9] = 10;
        expected[10] = 11;
        expected[11] = 12;
        expected[12] = 13;
        expected[13] = 14;
        expected[14] = 15;
        expected[15] = 16;
        expected[16] = 17;
        expected[17] = 18;
        expected[18] = 19;
        expected[19] = 20;
        expected[20] = 21;
        expected[21] = 22;
        assertArrayEquals(expected, backingArray);

        //check if it throws nosuchelement
        queue = new ArrayQueue<>();
        queue.dequeue();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueuePeek() {
        System.out.println("Testing peek()...");
        queue = new ArrayQueue<>();
        queue.enqueue(1);
        assertEquals((Integer) 1, queue.peek());
        queue.enqueue(2);
        assertEquals((Integer) 1,  queue.peek());
        queue.dequeue();
        assertEquals((Integer) 2, queue.peek());
    }


}
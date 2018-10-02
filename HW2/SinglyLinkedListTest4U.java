import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * @version 1.0
 */
public class SinglyLinkedListTest4U {
    private LinkedListInterface<String> list;
    private String[] ideal;

    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
    }

    @Test (timeout = TIMEOUT)
    public void addAtIndex() {
        ideal = new String[]{"om", "za"};
        assertNull(list.getHead());
        list.addAtIndex("za", 0);
        assertEquals(list.getHead(), list.getHead().getNext());
        list.addAtIndex("om", 0);
        assertArrayEquals(ideal, list.toArray());
        ideal = new String[]{"om", "za", "bam"};
        list.addAtIndex("bam", 2);
        assertArrayEquals(ideal, list.toArray());
    }
    
    @Test (timeout = TIMEOUT)
    public void addAtIndex2() {
        list.addAtIndex("a", 0);
        list.addAtIndex("b", 1);
        list.addAtIndex("c", 2);
        list.addAtIndex("d", 3);
        list.addAtIndex("e", 4);
        list.addAtIndex("f", 2);
        list.addAtIndex("g", 0);
        list.addAtIndex("h", 7);
        list.addAtIndex("i", 4);
        ideal = new String[]{"g", "a", "b", "f", "i", "c", "d", "e", "h"};
        for (int i = 0; i < 9; i++) {
            assertEquals(list.get(i), ideal[i]);
        }
        assertArrayEquals(ideal, list.toArray());
        assertEquals(9, list.size());
    }

    @Test (timeout = TIMEOUT)
    public void addAtIndexLarger() {
        ideal = new String[20];
        for (int i = 19; i >= 0; i--) {
            list.addAtIndex("" + i, 19 - i);
            ideal[19 - i] = "" + i;
        }
        assertEquals(20, list.size());
        assertArrayEquals(ideal, list.toArray());
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addAtIndexNull() {
        list.addAtIndex("a", 0);
        list.addAtIndex("b", 1);
        list.addAtIndex(null, 0);
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void addAtIndexLow() {
        list.addAtIndex("a", 0);
        list.addAtIndex("b", 1);
        list.addAtIndex("c", -1);
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void addAtIndexHigh() {
        list.addAtIndex("a", 0);
        list.addAtIndex("b", 1);
        list.addAtIndex("c", 3);
    }

    @Test (timeout = TIMEOUT)
    public void addToFront() {
        assertArrayEquals(new String[0], list.toArray());
        list.addToFront("Did");
        list.addToFront("you");
        list.addToFront("ever");
        list.addToFront("hear");
        list.addToFront("the");
        list.addToFront("tragedy");
        list.addToFront("of");
        list.addToFront("Darth");
        list.addToFront("Plagueis");
        list.addToFront("the");
        list.addToFront("Wise?");
        assertArrayEquals(new String[]{"Wise?", "the", "Plagueis", "Darth",
                        "of", "tragedy", "the", "hear", "ever", "you", "Did"},
                list.toArray());
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addToFrontNull() {
        list.addToFront("a");
        list.addToFront("b");
        list.addToFront(null);
    }

    @Test (timeout = TIMEOUT)
    public void addToBack() {
        assertArrayEquals(new String[0], list.toArray());
        list.addToBack("I");
        list.addToBack("don't");
        list.addToBack("like");
        list.addToBack("sand.");
        assertArrayEquals(new String[]{"I", "don't", "like", "sand."}, list
                .toArray());
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addToBackNull() {
        list.addToBack("a");
        list.addToBack("b");
        list.addToBack(null);
    }

    @Test (timeout = TIMEOUT)
    public void removeAtIndex() {
        assertArrayEquals(new String[0], list.toArray());
        list.addToFront("Did");
        list.addToFront("you");
        list.addToFront("ever");
        list.addToFront("hear");
        list.addToFront("the");
        list.addToFront("tragedy");
        list.addToFront("of");
        list.addToFront("Darth");
        list.addToFront("Plagueis");
        list.addToFront("the");
        list.addToFront("Wise?");

        assertEquals("tragedy", list.removeAtIndex(5));
        assertArrayEquals(new String[]{"Wise?", "the", "Plagueis", "Darth",
            "of", "the", "hear", "ever", "you", "Did"}, list.toArray());
        assertEquals("Wise?", list.removeAtIndex(0));
        assertArrayEquals(new String[]{"the", "Plagueis", "Darth",
            "of", "the", "hear", "ever", "you", "Did"}, list.toArray());
        assertEquals("of", list.removeAtIndex(3));
        assertArrayEquals(new String[]{"the", "Plagueis", "Darth",
            "the", "hear", "ever", "you", "Did"}, list.toArray());
        assertEquals("Did", list.removeAtIndex(7));
        assertArrayEquals(new String[]{"the", "Plagueis", "Darth",
            "the", "hear", "ever", "you"}, list.toArray());
        assertEquals("the", list.removeAtIndex(3));
        assertArrayEquals(new String[]{"the", "Plagueis", "Darth",
            "hear", "ever", "you"}, list.toArray());
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void removeAtIndexLow() {
        list.addAtIndex("a", 0);
        list.addAtIndex("b", 1);
        list.addAtIndex("c", -1);
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void removeAtIndexHigh() {
        list.addAtIndex("a", 0);
        list.addAtIndex("b", 1);
        list.removeAtIndex(2);
    }

    @Test (timeout = TIMEOUT)
    public void removeFromFront() {
        ideal = new String[30];
        for (int i = 0; i < 45; i++) {
            list.addToFront("" + i);
        }
        for (int i = 0; i < 30; i++) {
            ideal[29 - i] = "" + i;
        }
        for (int i = 44; i >= 30; i--) {
            assertEquals("" + i, list.removeFromFront());
            assertEquals(i, list.size());
        }
        assertArrayEquals(ideal, list.toArray());
    }

    @Test (timeout = TIMEOUT)
    public void removeFromFrontNull() {
        list.addToBack("a");
        assertEquals("a", list.removeFromFront());
        assertNull(list.removeFromFront());
    }

    @Test (timeout = TIMEOUT)
    public void removeFromBack() {
        list.addAtIndex("Take", 0);
        list.addAtIndex("a", 0);
        list.addAtIndex("seat", 0);
        assertEquals(3, list.size());
        assertEquals("Take", list.removeFromBack());
        assertEquals(2, list.size());
        assertEquals("a", list.removeFromBack());
        assertEquals("seat", list.removeFromBack());
        assertEquals(0, list.size());
    }

    @Test (timeout = TIMEOUT)
    public void removeFromBackNull() {
        list.addToFront("a");
        assertEquals("a", list.removeFromBack());
        assertNull(list.removeFromBack());
    }

    @Test (timeout = TIMEOUT)
    public void removeLastOccurrence() {
        list.addToBack("a");
        list.addToBack("b");
        String c = "c";
        String c2 = "c";
        list.addToFront(c);
        list.addToBack(c2);
        list.addToBack("d");
        assertSame(c2, list.removeLastOccurrence("c"));
        assertArrayEquals(new String[]{"c", "a", "b", "d"}, list.toArray());
    }

    @Test (timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void removeLastOccurrenceNull() {
        list.addToBack("Hello");
        list.addToBack("There.");
        list.removeLastOccurrence(null);
    }

    @Test (timeout = TIMEOUT)
    public void removeLastOccurrenceNoOccurrence() {
        list.addToBack("Hello");
        list.addToBack("There.");
        assertNull(list.removeLastOccurrence("there."));
        list.clear();
        assertNull(list.removeLastOccurrence("whoa"));
    }

    @Test (timeout = TIMEOUT)
    public void get() {
        list.addToBack("It's");
        list.addToBack("coarse");
        list.addToBack("and");
        list.addToBack("rough");
        list.addToBack("and");
        String sand = "irritating.";
        list.addToBack(sand);
        assertEquals("It's", list.get(0));
        assertSame(sand, list.get(5));
        list.removeFromFront();
        list.removeFromFront();
        assertEquals("and", list.get(0));
    }

    @Test (timeout = TIMEOUT)
    public void getCircular() {
        list.addToBack("It's");
        list.addToBack("coarse");
        list.addToBack("and");
        list.addToBack("rough");
        list.addToBack("and");

        //testing circular linking
        LinkedListNode<String> travNode = list.getHead();
        for (int i = 0; i < 4; i++) {
            assertSame(travNode.getNext().getData(), list.get(i + 1));
            travNode = travNode.getNext();
        }
        assertSame(list.getHead(), travNode.getNext());
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void getAtIndexLow() {
        list.addAtIndex("a", 0);
        list.addAtIndex("b", 1);
        list.get(-1);
    }

    @Test (timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void getAtIndexHigh() {
        list.addAtIndex("a", 0);
        list.addAtIndex("b", 1);
        list.get(2);
    }

    @Test (timeout = TIMEOUT)
    public void toArray() {
        assertArrayEquals(new String[0], list.toArray());
        list.addAtIndex("Yo", 0);
        assertArrayEquals(new String[]{"Yo"}, list.toArray());
        list.removeFromBack();
        assertArrayEquals(new String[0], list.toArray());
    }

    @Test (timeout = TIMEOUT)
    public void isEmpty() {
        assertTrue(list.isEmpty());
        list.addAtIndex("", 0);
        assertFalse(list.isEmpty());
    }

    @Test (timeout = TIMEOUT)
    public void clear() {
        list.addToFront("4");
        list.addToFront("8");
        list.addToFront("15");
        list.clear();
        assertNull(list.getHead());
        assertEquals(0, list.size());
    }

    @Test (timeout = TIMEOUT)
    public void size() {
        assertEquals(0, list.size());
        list.addToFront("asdf");
        assertEquals(1, list.size());
        list.addToBack("fdas");
        assertEquals(2, list.size());
        list.removeFromFront();
        assertEquals(1, list.size());
        list.removeFromBack();
        assertEquals(0, list.size());
    }

    @Test (timeout = TIMEOUT)
    public void getHead() {
        assertNull(list.getHead());
        list.addAtIndex("boop", 0);
        assertNotNull(list.getHead());
    }
}
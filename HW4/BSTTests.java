import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class BSTTests {

    private BSTInterface<String> emptyBST;
    private BSTInterface<String> populatedBST;
    private static final int TIMEOUT = 200;

    @Before
    public void setup() {
        emptyBST = new BST<>();
        ArrayList<String> data = new ArrayList<>();
        data.add("F");
        data.add("B");
        data.add("D");
        data.add("G");
        data.add("C");
        data.add("I");
        data.add("E");
        data.add("H");
        data.add("A");
        populatedBST = new BST<>(data);
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        assertEquals(0, emptyBST.size());
        assertNull(emptyBST.getRoot());
        emptyBST.add("G");
        assertEquals(1, emptyBST.size());
        assertEquals("G", emptyBST.getRoot().getData());
        emptyBST.add("L");
        assertEquals(2, emptyBST.size());
        assertEquals("L", emptyBST.getRoot().getRight().getData());
        emptyBST.add("I");
        assertEquals(3, emptyBST.size());
        assertEquals("I", emptyBST.getRoot().getRight().getLeft().getData());
        emptyBST.add("I");
        assertEquals(3, emptyBST.size());

    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNull() {
        populatedBST.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder() {
        List<String> levelorder = populatedBST.levelorder();
        ArrayList<String> correctLevelorder = new ArrayList<>();
        correctLevelorder.add("F");
        correctLevelorder.add("B");
        correctLevelorder.add("G");
        correctLevelorder.add("A");
        correctLevelorder.add("D");
        correctLevelorder.add("I");
        correctLevelorder.add("C");
        correctLevelorder.add("E");
        correctLevelorder.add("H");
        assertEquals(correctLevelorder.size(), levelorder.size());
        for (int i = 0; i < correctLevelorder.size(); i++) {
            assertEquals(correctLevelorder.get(i), levelorder.get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testPreorder() {
        List<String> preorder = populatedBST.preorder();
        ArrayList<String> correctPreorder = new ArrayList<>();
        correctPreorder.add("F");
        correctPreorder.add("B");
        correctPreorder.add("A");
        correctPreorder.add("D");
        correctPreorder.add("C");
        correctPreorder.add("E");
        correctPreorder.add("G");
        correctPreorder.add("I");
        correctPreorder.add("H");
        assertEquals(correctPreorder.size(), preorder.size());
        for (int i = 0; i < correctPreorder.size(); i++) {
            assertEquals(correctPreorder.get(i), preorder.get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testPostorder() {
        List<String> postorder = populatedBST.postorder();
        ArrayList<String> correctPostorder = new ArrayList<>();
        correctPostorder.add("A");
        correctPostorder.add("C");
        correctPostorder.add("E");
        correctPostorder.add("D");
        correctPostorder.add("B");
        correctPostorder.add("H");
        correctPostorder.add("I");
        correctPostorder.add("G");
        correctPostorder.add("F");
        assertEquals(correctPostorder.size(), postorder.size());
        for (int i = 0; i < correctPostorder.size(); i++) {
            assertEquals(correctPostorder.get(i), postorder.get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testInorder() {
        List<String> inorder = populatedBST.inorder();
        ArrayList<String> correctInorder = new ArrayList<>();
        correctInorder.add("A");
        correctInorder.add("B");
        correctInorder.add("C");
        correctInorder.add("D");
        correctInorder.add("E");
        correctInorder.add("F");
        correctInorder.add("G");
        correctInorder.add("H");
        correctInorder.add("I");
        assertEquals(correctInorder.size(), inorder.size());
        for (int i = 0; i < correctInorder.size(); i++) {
            assertEquals(correctInorder.get(i), inorder.get(i));
        }
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        assertEquals(-1, emptyBST.height());
        assertEquals(3, populatedBST.height());
        populatedBST.add("J");
        populatedBST.add("K");
        assertEquals(4, populatedBST.height());
    }

    @Test(timeout = TIMEOUT)
    public void testDistanceBetween() {
        assertEquals(4, populatedBST.distanceBetween("E", "G"));
        assertEquals(1, populatedBST.distanceBetween("B", "F"));
        assertEquals(0, populatedBST.distanceBetween("I", "I"));
        assertEquals(6, populatedBST.distanceBetween("E", "H"));
        assertEquals(1, populatedBST.distanceBetween("I", "H"));
        assertEquals(3, populatedBST.distanceBetween("A", "C"));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testDistanceBetweenNull() {
        populatedBST.distanceBetween(null, "A");
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testDistanceBetweenNeitherFound() {
        populatedBST.distanceBetween("Y", "Z");
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testDistanceBetweenOneNotFound() {
        populatedBST.distanceBetween("B", "Z");
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        assertEquals(9, populatedBST.size());
        assertNotNull(populatedBST.getRoot());
        populatedBST.clear();
        assertEquals(0, populatedBST.size());
        assertNull(populatedBST.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveNoChildren() {
        emptyBST.add("A");
        assertEquals(1, emptyBST.size());
        assertEquals("A", emptyBST.getRoot().getData());
        assertEquals("A", emptyBST.remove("A"));
        assertEquals(0, emptyBST.size());
        assertNull(emptyBST.getRoot());
        assertEquals("A", populatedBST.remove("A"));
        assertEquals(8, populatedBST.size());
        assertNull(populatedBST.getRoot().getLeft().getLeft());
        assertEquals("E", populatedBST.remove("E"));
        assertEquals(7, populatedBST.size());
        assertNull(populatedBST.getRoot().getLeft().getRight().getRight());
        assertEquals("C", populatedBST.getRoot().getLeft().getRight()
                .getLeft().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveOneChild() {
        emptyBST.add("C");
        emptyBST.add("B");
        assertEquals("C", emptyBST.remove("C"));
        assertEquals(1, emptyBST.size());
        assertEquals("B", emptyBST.getRoot().getData());
        assertEquals("G", populatedBST.remove("G"));
        assertEquals("I", populatedBST.getRoot().getRight().getData());
        assertNotNull(populatedBST.getRoot().getRight().getLeft());
        assertEquals(8, populatedBST.size());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveTwoChild() {
        assertEquals("B", populatedBST.remove("B"));
        assertEquals("A", populatedBST.getRoot().getLeft().getData());
        assertEquals("D", populatedBST.getRoot().getLeft().getRight()
                .getData());
        assertNull(populatedBST.getRoot().getLeft().getLeft());
        assertEquals(8, populatedBST.size());
        assertEquals("F", populatedBST.remove("F"));
        assertEquals("E", populatedBST.getRoot().getData());
        assertEquals("A", populatedBST.getRoot().getLeft().getData());
        assertEquals("G", populatedBST.getRoot().getRight().getData());
        assertNull(populatedBST.getRoot().getLeft().getRight().getRight());
        assertEquals(7, populatedBST.size());
        assertEquals("E", populatedBST.remove("E"));
        assertEquals("D", populatedBST.getRoot().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveReturnsEquivalentNotSame() {
        AlwaysEquivalent instance1 = new AlwaysEquivalent();
        AlwaysEquivalent instance2 = new AlwaysEquivalent();
        BST<AlwaysEquivalent<Object>> bst = new BST<>();
        bst.add(instance1);
        AlwaysEquivalent removed = bst.remove(instance2);
        assertEquals(instance2, removed);
        assertEquals(instance1, removed);
        assertTrue(instance1 == removed);
        assertFalse(instance2 == removed);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        populatedBST.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveNotFound() {
        populatedBST.remove("Z");
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        assertTrue(populatedBST.contains("A"));
        assertTrue(populatedBST.contains("E"));
        assertTrue(populatedBST.contains("G"));
        assertFalse(populatedBST.contains("R"));
        assertFalse(populatedBST.contains("a"));
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testContainsNull() {
        populatedBST.contains(null);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        String equivalentData = "E";
        String fromTree = populatedBST.get(equivalentData);
        assertEquals(equivalentData, fromTree);
        BST<AlwaysEquivalent<Object>> bst = new BST<>();
        bst.add(new AlwaysEquivalent<>());
        AlwaysEquivalent instance = new AlwaysEquivalent();
        AlwaysEquivalent treeInstance = bst.get(instance);
        assertFalse(instance == treeInstance);
        assertEquals(instance, treeInstance);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testGetNull() {
        populatedBST.get(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetNotExists() {
        populatedBST.get("Z");
    }

    private class AlwaysEquivalent<T> implements Comparable<T> {
        @Override
        public boolean equals(Object other) {
            return true;
        }

        @Override
        public int compareTo(T other) {
            return 0;
        }
    }
}

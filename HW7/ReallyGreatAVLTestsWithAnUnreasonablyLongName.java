import org.junit.Before;
import org.junit.Test;


import java.util.Arrays;

import static org.junit.Assert.*;


/**
 * AVL? More like AV-LAME amirite?
 * @author Ya boys, Josh Dierberger and Gibran Essa
 * @version 1.0
 */
public class ReallyGreatAVLTestsWithAnUnreasonablyLongName {

    private AVLInterface<Integer> avl;
    private AVLInterface<Integer> avl2;

    private AVLInterface<String> strAvl;

    @Before
    public void setup() {
        avl = new AVL<>();
        avl2 = new AVL<>();
        strAvl = new AVL<>();
    }




    /*
    There are 3 possible scenarios for the second largest in an AVL:
         1)   1         2)  1                3)   1
             /  returns:0  / \  returns:1        / \    returns:2
            0             0   2                 0   3
                                                   /
                                                  2
     */
    @Test
    public void testSecondLargest() {
        avl.add(1);
        avl.add(0);
        assertEquals("Fails case where only a root and left node", 
                avl.getSecondLargest(), Integer.valueOf(0)); //scenario 1
        avl.clear();
        avl.add(1);
        avl.add(0);
        avl.add(2);
        assertEquals("Fails case where largest has no children",
                avl.getSecondLargest(), Integer.valueOf(1)); //scenario 2
        avl.clear();
        avl.add(1);
        avl.add(0);
        avl.add(3);
        avl.add(2);
        assertEquals("Fails case where largest has a child",
                avl.getSecondLargest(), Integer.valueOf(2)); //scenario 3
    }


    @Test
    public void testContainsGetReferenceAlias() {
        String i = "I";
        strAvl.add(i);
        strAvl.add("Hate");
        strAvl.add("Sand");
        strAvl.add(":'(");
        assertTrue("Fails when item passed into contains is not equal by reference but is by value",
                strAvl.contains("I"));
        assertTrue("Get should return the exact value in the tree, not a clone",
                strAvl.get(i) == i);
    }


    @Test
    public void testAddDuplicates() {
        avl.add(2);
        for (int i = 0; i < 200; i++) {
            avl.add(1);
        }
        avl2.add(2);
        avl2.add(1);

        assertEquals(avl, avl2);
    }

    @Test
    public void testEquals() {
        AVL<Integer> avl1 = new AVL<Integer>();
        AVL<String> avl2 = new AVL<String>();
        assertEquals("Two empty AVLs of different type should be equal",
                avl1, avl2);
        avl1.add(Integer.valueOf(0));
        avl2.add("");
        assertEquals("Failed reference equality test", avl1, avl1);
        assertNotEquals("Two non-empty AVLs of different type should not be "
                + "equal.", avl1, avl2);
        avl2 = new AVL<String>();
        assertNotEquals("Two AVLs of different size cannot be equal.",
                avl1, avl2);
        AVL<Integer> avl3 = new AVL<Integer>();
        assertNotEquals(avl1, avl3);
        avl3.add(Integer.valueOf(0));
        assertEquals("Equals should be symmetric.", avl1, avl3);
        assertEquals("Equals should be symmetric.", avl3, avl1);
        avl1.add(Integer.valueOf(1));
        avl3.add(Integer.valueOf(-1));
        assertNotEquals("Two trees of different data and structure were not "
                + "equal", avl1, avl3);
        avl1.add(Integer.valueOf(-1));
        avl3.add(Integer.valueOf(1));
        assertEquals("RIP your AVL, I added two elements in a different order"
                + " and it said they were !equal", avl1, avl3);
        avl1 = new AVL<Integer>();
        avl3 = new AVL<Integer>();
        avl1.add(Integer.valueOf(0));
        avl3.add(Integer.valueOf(1));
        avl1.add(Integer.valueOf(1));
        avl3.add(Integer.valueOf(0));
        assertNotEquals("Two AVLs with the same content but different "
                + "structure are not equal.", avl1, avl3);
        avl1.add(Integer.valueOf(2));
        avl3.add(Integer.valueOf(2));
        assertEquals("Two AVLs of different structure didn't rotate to become"
                + " equal.", avl1, avl3);
    }
    @Test
    public void testCollectionConstructor() {
        AVL<Integer> avl1 = new AVL<Integer>(Arrays.asList(Integer.valueOf(0)));
        assertEquals("Simple collection did not add to root.",
                avl1.getRoot().getData().intValue(), 0);
        try {
            avl1 = new AVL<Integer>(null);
            fail("Collection constructor should not accept null collection.");
        } catch (IllegalArgumentException iae) {
        } catch (Exception e) {
            fail("Expected IllegalArgumentException, got "
                    + e.getClass().getName() + ".");
        }
        try {
            avl1 = new AVL<Integer>(Arrays.asList(Integer.valueOf(0), null));
            fail("Collection constructor should not accept nulls in "
                    + "collection.");
        } catch (IllegalArgumentException iae) {
        } catch (Exception e) {
            fail("Expected IllegalArgumentException, got "
                    + e.getClass().getName() + ".");
        }
    }
    @Test
    public void clearTest() {
        AVL<Integer> avl1 = new AVL<Integer>();
        avl1.add(Integer.valueOf(0));
        avl1.add(Integer.valueOf(1));
        AVLNode<Integer> root = avl1.getRoot();
        avl1.clear();
        assertEquals("Size is not zero after clear", avl1.size(), 0);
        assertEquals("Root is not null", avl1.getRoot(), null);
        assertEquals("Boi, you actually went thru and set the children to null"
                        + ". Just set root to null.",
                root.getRight().getData().intValue(), 1);
    }
    @Test
    public void testFields() {
        try {
            assertEquals("You added fields; don't do that.",
                    AVL.class.getDeclaredFields().length, 2);
        } catch (Exception e) {
            System.err.println("Cannot test if fields are good.");
        }
    }
}

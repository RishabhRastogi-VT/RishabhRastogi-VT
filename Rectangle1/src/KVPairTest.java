import java.awt.Rectangle;

/**
 * @author Rishabh Rastogi
 * @version 09-18-2021
 */
public class KVPairTest extends student.TestCase {

    private KVPair<String, Rectangle> pair;

    /**
     * setup method
     */
    public void setUp() {
// no setup needed
    }


    /**
     * Test getKey method
     *
     */
    public void testGetKey() {
        Rectangle rectTest = new Rectangle(5, 1, 2, 3);
        pair = new KVPair<String, Rectangle>("rectangleTest", rectTest);
        assertEquals(pair.getKey().toString(), "rectangleTest");
    }


    /**
     * Test getValue method
     */
    public void testGetValue() {

        Rectangle rectTest = new Rectangle(5, 1, 2, 3);
        pair = new KVPair<String, Rectangle>("rectangleTest", rectTest);
        assertEquals((boolean)pair.getValue().equals(rectTest), true);
    }


    /**
     * Test CompareTo method
     */
    public void testCompareTo() {
        Rectangle rectTest = new Rectangle(5, 1, 2, 3);
        KVPair<String, Rectangle> toCheck1 = new KVPair<String, Rectangle>(
            "rectTest", rectTest);
        KVPair<String, Rectangle> toCheck2 = new KVPair<String, Rectangle>(
            "rectTests", rectTest);
        assertEquals(toCheck1.compareTo(toCheck2), -1);

    }


    /**
     * Test ToString method
     */
    public void testToString() {
        Rectangle rectTest1 = new Rectangle(5, 1, 2, 3);
        KVPair<String, Rectangle> toCheck1 = new KVPair<String, Rectangle>(
            "rectTest", rectTest1);
        assertEquals(toCheck1.toString(), toCheck1.toString());
    }
}

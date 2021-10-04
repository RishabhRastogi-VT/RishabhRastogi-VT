import java.awt.Rectangle;

/**
 * @author Rishabh Rastogi
 * @version 09-20-2021
 *
 */
public class SkipLisTest extends student.TestCase {
    private SkipList<String, Rectangle> skipList;

    /**
     * this method is used to setup the object
     */
    public void setUp() {
        skipList = new SkipList<String, Rectangle>();
    }


    /**
     * Tests functioning of test size
     */
    public void testsize() {

        assertEquals(skipList.size(), 0);
    }


    /**
     * To test the insert method.
     */
    public void testInsert() {
        Rectangle rectangleInsertionValid = new Rectangle(100, 120, 144, 78);
        Rectangle rectangleInsertionInValid = new Rectangle(100, 120, 1444, 78);
        KVPair<String, Rectangle> pairInsertionValid =
            new KVPair<String, Rectangle>("rectOne", rectangleInsertionValid);
        KVPair<String, Rectangle> pairInsertionInValid =
            new KVPair<String, Rectangle>("rectTwo", rectangleInsertionInValid);

        // First insert
        skipList.insert(pairInsertionValid);
        assertEquals(skipList.size(), 1);
        // second insert
        skipList.insert(pairInsertionInValid);
        // check unit test case
        assertEquals(skipList.size(), 2);
    }


    /**
     * To test the remove method.
     */
    public void testRemove() {
        Rectangle rectangleInsertionValid = new Rectangle(100, 120, 144, 78);
        Rectangle rectangleInsertionInValid = new Rectangle(100, 120, 1444, 78);
        KVPair<String, Rectangle> pairInsertionValid =
            new KVPair<String, Rectangle>("rectOne", rectangleInsertionValid);
        KVPair<String, Rectangle> pairInsertionInValid =
            new KVPair<String, Rectangle>("rectTwo", rectangleInsertionInValid);
        // First insert
        skipList.insert(pairInsertionValid);
        // second insert
        skipList.insert(pairInsertionInValid);
        // remove first insert
        skipList.remove("rectOne");
        // check unit test case
        assertEquals(skipList.size(), 1);
    }


    /**
     * To test the remove method.
     */
    public void testRemoveByValue() {
        Rectangle rectangleInsertionValid = new Rectangle(100, 120, 144, 78);
        Rectangle rectangleInsertionInValid = new Rectangle(100, 120, 1444, 78);
        KVPair<String, Rectangle> pairInsertionValid =
            new KVPair<String, Rectangle>("rectOne", rectangleInsertionValid);
        KVPair<String, Rectangle> pairInsertionInValid =
            new KVPair<String, Rectangle>("rectTwo", rectangleInsertionInValid);
        // First insert
        skipList.insert(pairInsertionValid);
        // second insert
        skipList.insert(pairInsertionInValid);
        // remove first insert
        skipList.removeByValue(rectangleInsertionInValid);
        // check unit test case
        assertEquals(skipList.size(), 1);
    }


    /**
     * To test the Find by Value method.
     */
    public void testFindNode() {
        Rectangle rectangleInsertionValid = new Rectangle(100, 120, 144, 78);
        Rectangle rectangleInsertionInValid = new Rectangle(100, 120, 1444, 78);
        KVPair<String, Rectangle> pairInsertionValid =
            new KVPair<String, Rectangle>("rectOne", rectangleInsertionValid);
        KVPair<String, Rectangle> pairInsertionInValid =
            new KVPair<String, Rectangle>("rectTwo", rectangleInsertionInValid);
        // First insert
        skipList.insert(pairInsertionValid);
        // second insert
        skipList.insert(pairInsertionInValid);
        SkipList<String, Rectangle>.SkipNode[] result = skipList.findbyValue(
            "rectOne");
        // check unit test case
        assertEquals(result.length, 2);
    }


    /**
     * To test the dump method.
     */
    public void testdump() {
        Rectangle rectangleInsertionValid = new Rectangle(100, 120, 144, 78);
        Rectangle rectangleInsertionInValid = new Rectangle(100, 120, 1444, 78);
        KVPair<String, Rectangle> pairInsertionValid =
            new KVPair<String, Rectangle>("rectOne", rectangleInsertionValid);
        KVPair<String, Rectangle> pairInsertionInValid =
            new KVPair<String, Rectangle>("rectTwo", rectangleInsertionInValid);
        // First insert
        skipList.insert(pairInsertionValid);
        // second insert
        skipList.insert(pairInsertionInValid);
        SkipList<String, Rectangle>.SkipNode[] result = skipList.dump();
        // check unit test case
        assertEquals(result.length, 3);
    }
}

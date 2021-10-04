import java.awt.Rectangle;

/**
 * @author Rishabh Rastogi
 * @version 09-19-2021
 *
 */
public class DatabaseTest extends student.TestCase {
    private Database databaseTests;

    /**
     * SetUp method to create object
     */
    public void setUp() {
        databaseTests = new Database();
    }


    /**
     * To test insert function
     */
    public void testInsert() {
        // To check if a rectangle is inserted successfully.
        Rectangle rectangleInsertValid = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> testInsertValid =
            new KVPair<String, Rectangle>("r1", rectangleInsertValid);
        databaseTests.insert(testInsertValid);
        assertTrue(systemOut().getHistory().contains("Rectangle inserted"));
        systemOut().clearHistory();

        // To check if a rectangle is inserted successfully.
        Rectangle rectangleInsertValid1 = new Rectangle(1, 2, 3, 5);
        KVPair<String, Rectangle> testInsertValid1 =
            new KVPair<String, Rectangle>("r2", rectangleInsertValid1);
        databaseTests.insert(testInsertValid1);
        assertTrue(systemOut().getHistory().contains("Rectangle inserted"));
        systemOut().clearHistory();

        // To check if a rectangle is rejected.
        Rectangle rectangleInsertInvalid1 = new Rectangle(1, 2, -3, 4);
        KVPair<String, Rectangle> testInsertInvalid1 =
            new KVPair<String, Rectangle>("r3", rectangleInsertInvalid1);
        databaseTests.insert(testInsertInvalid1);
        assertTrue(systemOut().getHistory().contains("Rectangle rejected"));
        systemOut().clearHistory();
    }


    /**
     * To test remove by value function
     */
    public void testremoveByValue() {
        // To check if a rectangle is inserted successfully.
        Rectangle rectangleInsertValid = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> testInsertValid =
            new KVPair<String, Rectangle>("r1", rectangleInsertValid);
        databaseTests.insert(testInsertValid);
        systemOut().clearHistory();

        // To check if a rectangle is inserted successfully.
        Rectangle rectangleInsertValid1 = new Rectangle(1, 2, 3, 5);
        KVPair<String, Rectangle> testInsertValid1 =
            new KVPair<String, Rectangle>("r2", rectangleInsertValid1);
        databaseTests.insert(testInsertValid1);
        systemOut().clearHistory();

        // To check if a rectangle to be removed is valid.
        databaseTests.remove(1, 2, 3, -5);
        assertTrue(systemOut().getHistory().contains("Rectangle rejected"));
        systemOut().clearHistory();

        // To check if a rectangle is removed by value.
        databaseTests.remove(1, 2, 3, 4);
        assertTrue(systemOut().getHistory().contains("Rectangle removed"));
        systemOut().clearHistory();

        // To check if a rectangle is valid but not removed as it is not
        // present.
        databaseTests.remove(1, 2, 3, 6);
        assertTrue(systemOut().getHistory().contains("Rectangle not removed"));
        systemOut().clearHistory();
    }


    /**
     * To test remove by key function
     */
    public void testremoveByKey() {

        // To check if a rectangle is inserted successfully.
        Rectangle rectangleInsertValid = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> testInsertValid =
            new KVPair<String, Rectangle>("r1", rectangleInsertValid);
        databaseTests.insert(testInsertValid);
        systemOut().clearHistory();

        // To check if a rectangle is inserted successfully.
        Rectangle rectangleInsertValid1 = new Rectangle(1, 2, 3, 5);
        KVPair<String, Rectangle> testInsertValid1 =
            new KVPair<String, Rectangle>("r2", rectangleInsertValid1);
        databaseTests.insert(testInsertValid1);
        systemOut().clearHistory();

        // To check if a rectangle is removed successfully by key.
        databaseTests.remove("r1");
        assertTrue(systemOut().getHistory().contains("Rectangle removed"));
        systemOut().clearHistory();

        // To check if a rectangle is not removed successfully by key.
        databaseTests.remove("r3");
        assertTrue(systemOut().getHistory().contains("Rectangle not removed"));
        systemOut().clearHistory();
    }


    /**
     * To test region search function
     */
    public void testRegionSearch() {

        // To check if a rectangle is inserted successfully.
        Rectangle rectangleInsertValid = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> testInsertValid =
            new KVPair<String, Rectangle>("r1", rectangleInsertValid);
        databaseTests.insert(testInsertValid);
        systemOut().clearHistory();

        // To check if a rectangle is inserted successfully.
        Rectangle rectangleInsertValid1 = new Rectangle(1, 2, 3, 5);
        KVPair<String, Rectangle> testInsertValid1 =
            new KVPair<String, Rectangle>("r2", rectangleInsertValid1);
        databaseTests.insert(testInsertValid1);
        systemOut().clearHistory();

        // To check the region search function for an invalid rectangle.
        databaseTests.regionsearch(1, 2, 3, -4);
        assertTrue(systemOut().getHistory().contains("Rectangle rejected"));
        systemOut().clearHistory();

        // To check region search function for an valid rectangles.
        databaseTests.regionsearch(1, 2, 3, 4000);
        assertTrue(systemOut().getHistory().contains(
            "Rectangles intersecting region"));
        systemOut().clearHistory();

        // To check region search function for an valid rectangles.
        databaseTests.regionsearch(100, 100, 100, 100);
        assertTrue(systemOut().getHistory().contains(
            "Rectangles intersecting region"));
        systemOut().clearHistory();
    }


    /**
     * To test intersection function
     */
    public void testIntersection() {
        // To check if a rectangle is inserted successfully.
        Rectangle rectangleInsertValid = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> testInsertValid =
            new KVPair<String, Rectangle>("r1", rectangleInsertValid);
        databaseTests.insert(testInsertValid);
        systemOut().clearHistory();

        // To check if a rectangle is inserted successfully.
        Rectangle rectangleInsertValid1 = new Rectangle(1, 2, 3, 5);
        KVPair<String, Rectangle> testInsertValid1 =
            new KVPair<String, Rectangle>("r2", rectangleInsertValid1);
        databaseTests.insert(testInsertValid1);
        systemOut().clearHistory();

        // To check intersections function.
        databaseTests.intersections();
        assertTrue(systemOut().getHistory().contains("|"));
        systemOut().clearHistory();

    }


    /**
     * To test Search function
     */
    public void testSearch() {
        // To check if a rectangle is inserted successfully.
        Rectangle rectangleInsertValid = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> testInsertValid =
            new KVPair<String, Rectangle>("r1", rectangleInsertValid);
        databaseTests.insert(testInsertValid);
        systemOut().clearHistory();

        // To check if a rectangle is inserted successfully.
        Rectangle rectangleInsertValid1 = new Rectangle(1, 2, 3, 5);
        KVPair<String, Rectangle> testInsertValid1 =
            new KVPair<String, Rectangle>("r2", rectangleInsertValid1);
        databaseTests.insert(testInsertValid1);
        systemOut().clearHistory();

        // To check search function
        databaseTests.search("r1");
        assertTrue(systemOut().getHistory().contains("Rectangles found:"));
        systemOut().clearHistory();

        // To check negative scenario for search function
        databaseTests.search("r3");
        assertFalse(systemOut().getHistory().contains("Rectangles found:"));
        systemOut().clearHistory();

    }


    /**
     * To test dump function
     */
    public void testDump() {
        Rectangle rectangleInsertValid = new Rectangle(1, 2, 3, 4);
        KVPair<String, Rectangle> testInsertValid =
            new KVPair<String, Rectangle>("r1", rectangleInsertValid);
        databaseTests.insert(testInsertValid);
        systemOut().clearHistory();

        // To check if a rectangle is inserted successfully.
        Rectangle rectangleInsertValid1 = new Rectangle(1, 2, 3, 5);
        KVPair<String, Rectangle> testInsertValid1 =
            new KVPair<String, Rectangle>("r2", rectangleInsertValid1);
        databaseTests.insert(testInsertValid1);

        // To check dump function
        databaseTests.dump();
        assertTrue(systemOut().getHistory().contains("Node has depth"));
        systemOut().clearHistory();

    }


    /**
     * To test dump function
     */
    public void testCheck() {
        // To check if a rectangle is rejected.
        assertFalse(databaseTests.checkRectangleValidity(1, 2, -3, 4));
        systemOut().clearHistory();

        assertFalse(databaseTests.checkRectangleValidity(1, -2, 3, 4));
        systemOut().clearHistory();

        assertFalse(databaseTests.checkRectangleValidity(-1, 2, 3, 4));
        systemOut().clearHistory();

        assertFalse(databaseTests.checkRectangleValidity(1, 2, 3, -4));
        systemOut().clearHistory();

        assertFalse(databaseTests.checkRectangleValidity(1028, 2, 3, 4));
        systemOut().clearHistory();

        assertFalse(databaseTests.checkRectangleValidity(1, 1028, 3, 4));
        systemOut().clearHistory();

        assertFalse(databaseTests.checkRectangleValidity(4, 7, 1025, 4));
        systemOut().clearHistory();

        assertFalse(databaseTests.checkRectangleValidity(4, 7, 1, 1025));
        systemOut().clearHistory();

        assertFalse(databaseTests.checkRectangleValidity(1025, 1025, 1025,
            1025));
        systemOut().clearHistory();

        // To check if a rectangle is correct.
        assertTrue(databaseTests.checkRectangleValidity(10, 10, 10, 10));
        systemOut().clearHistory();

    }

}

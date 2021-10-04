/**
 * @author Rishabh Rastogi
 * @version September 19, 2021
 */
public class RectangleTest extends student.TestCase {

    /**
     * SetUp method to create object.
     * Unused as calling method is static.
     */
    public void setUp() {
        @SuppressWarnings("unused")
        Rectangle1 rectangleOne = new Rectangle1();
    }


    /**
     * this class is used to test the main method in rectangle class
     */
    public void testMain() {

        // invalid input
        String[] argumentsToPass = { "DummyFile.txt" };
        Rectangle1.main(argumentsToPass);
        assertTrue(systemOut().getHistory().contains("Invalid file"));
        systemOut().clearHistory();

        // valid input
        String[] argsFile = { "./Data/P1test2.txt" };
        Rectangle1.main(argsFile);
        assertFalse(systemOut().getHistory().contains("Invalid file"));
        systemOut().clearHistory();

    }
}

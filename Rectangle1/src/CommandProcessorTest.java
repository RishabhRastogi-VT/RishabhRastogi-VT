/**
 * @author Rishabh Rastogi
 * @version 09-19-2021
 */
public class CommandProcessorTest extends student.TestCase {
    private CommandProcessor commandProcessor;

    /**
     * SetUp method to create object
     */
    public void setUp() {
        commandProcessor = new CommandProcessor();

    }


    /**
     * To test Processor method
     */
    public void testProcessor() {

        // insert
        commandProcessor.processor("insert R7 1 2 3 4");
        assertTrue(systemOut().getHistory().contains("Rectangle inserted"));
        systemOut().clearHistory();
        // remove
        commandProcessor.processor("remove R7 1 2 3 4");
        assertTrue(systemOut().getHistory().contains("Rectangle removed"));
        systemOut().clearHistory();

        // remove
        commandProcessor.processor("remove 1 2 3 4");
        assertTrue(systemOut().getHistory().contains("Rectangle not removed"));
        systemOut().clearHistory();

        // search
        commandProcessor.processor("search R7");
        assertTrue(systemOut().getHistory().contains("Rectangle"));
        systemOut().clearHistory();

        // intersection
        commandProcessor.processor("intersections");
        assertTrue(systemOut().getHistory().contains("Intersections pairs:"));
        systemOut().clearHistory();

        // region search
        commandProcessor.processor("regionsearch 1 2 3 4");
        assertTrue(systemOut().getHistory().contains("Rectangles"));
        systemOut().clearHistory();

        // print
        commandProcessor.processor("dump");
        assertTrue(systemOut().getHistory().contains("Node"));
        systemOut().clearHistory();

        // empty line
        commandProcessor.processor("");
        assertTrue(systemOut().getHistory().contains(""));
        systemOut().clearHistory();

        // Null pointer exception
        commandProcessor.processor(null);
        assertFalse(systemOut().getHistory().contains("NullPointerException"));
        systemOut().clearHistory();

        // check for an invalid text file
        commandProcessor.processor("anybuggyinput");
        assertTrue(systemOut().getHistory().contains("Invalid Command"));
        systemOut().clearHistory();

    }
}

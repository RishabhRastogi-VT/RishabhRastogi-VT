import java.awt.Rectangle;
import java.util.Scanner;

/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 * 
 * @author Rishabh Rastogi
 * 
 * @version 2021-09-10
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     */
    public CommandProcessor() {
        data = new Database();
    }


    /**
     * This method identifies keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions within the database required. These
     * actions are performed on specified objects and include insert, remove,
     * region search, search, intersections, and dump.
     * If the command in the file line is not one of these,
     * an appropriate message will be written in the console. This
     * processor method is called for each line in the file. Note that the
     * methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
        try {
            Scanner scan = new Scanner(line);
            String tempName = "";
            // rectangle variable
            Rectangle rectInput = new Rectangle();
            while (scan.hasNext()) {
                String scanner = scan.next();
                // switch statement that processes each line
                switch (scanner) {
                    // Attempts to insert the Rectangle in the SkipList
                    case "insert":
                        tempName = scan.next();
                        rectInput.x = scan.nextInt();
                        rectInput.y = scan.nextInt();
                        rectInput.width = scan.nextInt();
                        rectInput.height = scan.nextInt();
                        KVPair<String, Rectangle> pair =
                            new KVPair<String, Rectangle>(tempName, rectInput);
                        data.insert(pair);
                        break;
                    case "remove":
                        tempName = scan.next();
                        if (isNumber(tempName)) {
                            rectInput.x = Integer.parseInt(tempName);
                            rectInput.y = scan.nextInt();
                            rectInput.width = scan.nextInt();
                            rectInput.height = scan.nextInt();
                            data.remove(rectInput.x, rectInput.y,
                                rectInput.width, rectInput.height);
                        }
                        else {
                            data.remove(tempName);
                        }
                        break;
                    case "search":
                        tempName = scan.next();
                        data.search(tempName);
                        break;

                    case "intersections":
                        data.intersections();
                        break;

                    case "regionsearch":
                        rectInput.x = scan.nextInt();
                        rectInput.y = scan.nextInt();
                        rectInput.width = scan.nextInt();
                        rectInput.height = scan.nextInt();
                        data.regionsearch(rectInput.x, rectInput.y,
                            rectInput.width, rectInput.height);
                        break;

                    case "dump":
                        data.dump();
                        break;

                    default:
                        System.out.println("Invalid Command");
                        break;
                }

            }
            scan.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 
     * @param testing
     *            string that will be tested
     * @return true if given string is a number, false otherwise
     */
    private boolean isNumber(String testing) {

        try {
            Integer.parseInt(testing);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }
}

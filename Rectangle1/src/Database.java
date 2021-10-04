import java.awt.Rectangle;
import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 * 
 * @author Rishabh Rastogi
 * 
 * @version 2021-09-10
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Rectangle> list;

    /**
     * The constructor for this class initialises a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Rectangle>();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * insert the KVPair specified into the sorted SkipList appropriately
     * 
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(KVPair<String, Rectangle> pair) {
        Rectangle temp;
        String name = pair.getKey();
        int x = pair.getValue().x;
        int y = pair.getValue().y;
        int w = pair.getValue().width;
        int h = pair.getValue().height;
        if (!checkRectangleValidity(x, y, w, h)) {
            System.out.println("Rectangle rejected: (" + name + ", " + x + ", "
                + y + ", " + w + ", " + h + ")");
        }
        else {
            System.out.println("Rectangle inserted: (" + name + ", " + x + ", "
                + y + ", " + w + ", " + h + ")");
            temp = new Rectangle(x, y, w, h);
            KVPair<String, Rectangle> pairToInsert =
                new KVPair<String, Rectangle>(name, temp);
            list.insert(pairToInsert);
        }

    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     * 
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {
        KVPair<String, Rectangle> removeLists = list.remove(name);
        if (removeLists != null) {
            Rectangle rectInput = new Rectangle();
            rectInput.x = removeLists.getValue().x;
            rectInput.y = removeLists.getValue().y;
            rectInput.width = removeLists.getValue().width;
            rectInput.height = removeLists.getValue().height;
            System.out.println("Rectangle removed: (" + name + ", "
                + rectInput.x + ", " + rectInput.y + ", " + rectInput.width
                + ", " + rectInput.height + ")");
        }
        else {
            System.out.println("Rectangle not removed: (" + name + ")");
        }

    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     * 
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public void remove(int x, int y, int w, int h) {
        Rectangle tempRect = new Rectangle(x, y, w, h);
        if (!checkRectangleValidity(x, y, w, h)) {
            System.out.println("Rectangle rejected: (" + x + ", " + y + ", " + w
                + ", " + h + ")");
        }
        else {
            KVPair<String, Rectangle> result = list.removeByValue(tempRect);
            if (result != null) {
                System.out.println("Rectangle removed: (" + result.getKey()
                    + ", " + x + ", " + y + ", " + w + ", " + h + ")");
            }
            else {
                System.out.println("Rectangle not removed: (" + x + ", " + y
                    + ", " + w + ", " + h + ")");
            }
        }

    }


    /**
     * Test for rectangle validity
     * 
     * @param x
     *            x position of the rectangle to be zero or greater.
     * @param y
     *            y position of the rectangle to be zero or greater
     * @param w
     *            width of rectangle to be greater than zero
     * @param h
     *            height of rectangle to be greater than zero to be valid
     * @return true if rectangle has a valid position and dimensions do not
     *         make the rectangle go out of the region
     */
    boolean checkRectangleValidity(int x, int y, int w, int h) {

        return (!(x < 0 || x >= 1024 || y < 0 || y >= 1024 || w <= 0 || h <= 0
            || (x + w) > 1024 || (y + h) > 1024));
    }


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region. You will need a SkipList
     * Iterator for this
     * 
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {
        if (h <= 0 || w <= 0) {
            System.out.println("Rectangle rejected: (" + x + ", " + y + ", " + w
                + ", " + h + ")");
        }
        else {
            System.out.print("Rectangles intersecting region (");
            System.out.println(x + ", " + y + ", " + w + ", " + h + "): ");
            Rectangle searchRect = new Rectangle(x, y, w, h);
            Iterator<KVPair<String, Rectangle>> iter = list.iterator();
            while (iter.hasNext()) {
                Rectangle iteratorRect = iter.next().getValue();
                String t3 = iter.next().getKey();

                // To get coordinates
                int totalX1 = (int)searchRect.getX() + (int)searchRect
                    .getWidth();
                int totalY1 = (int)searchRect.getY() + (int)searchRect
                    .getHeight();
                int totalX2 = (int)iteratorRect.getX() + (int)iteratorRect
                    .getWidth();
                int totalY2 = (int)iteratorRect.getY() + (int)iteratorRect
                    .getHeight();

                // Prints if rectangles intersect
                if (!(totalX1 <= iteratorRect.getX() || totalX2 <= searchRect
                    .getX() || totalY1 <= iteratorRect.getY()
                    || totalY2 <= searchRect.getY())) {
                    System.out.println("(" + t3 + ", " + (int)iteratorRect
                        .getX() + ", " + (int)iteratorRect.getY() + ", "
                        + (int)iteratorRect.getWidth() + ", "
                        + (int)iteratorRect.getHeight() + ")");
                }
            }
        }

    }


    /**
     * Prints out all the rectangles that Intersect each other by calling the
     * SkipList method for intersections. You will need to use two SkipList
     * Iterators for this
     */
    public void intersections() {
        System.out.println("Intersections pairs:");
        Iterator<KVPair<String, Rectangle>> iteratorOne = list.iterator();
        while (iteratorOne.hasNext()) {
            Iterator<KVPair<String, Rectangle>> iteratorTwo = list.iterator();
            while (iteratorTwo.hasNext()) {
                /*
                 * Comparison with other rectangles except itself
                 */
                if (iteratorOne.next() != iteratorTwo.next()) {
                    // rectangle parameters
                    Rectangle term1 = iteratorOne.next().getValue();
                    Rectangle term2 = iteratorTwo.next().getValue();
                    // name of rectangle
                    String keyTerm1 = iteratorOne.next().getKey();
                    String keyTerm2 = iteratorTwo.next().getKey();
                    int sumX1 = (int)term1.getX() + (int)term1.getWidth();
                    int sumY1 = (int)term1.getY() + (int)term1.getHeight();
                    int sumX2 = (int)term2.getX() + (int)term2.getWidth();
                    int sumY2 = (int)term2.getY() + (int)term2.getHeight();

                    // Prints proper messages if rectangles intersect
                    if (!(sumX1 <= term2.getX() || sumX2 <= term1.getX()
                        || sumY1 <= term2.getY() || sumY2 <= term1.getY())) {
                        System.out.println("(" + keyTerm1 + ", " + (int)term1
                            .getX() + ", " + (int)term1.getY() + ", "
                            + (int)term1.getWidth() + ", " + (int)term1
                                .getHeight() + " | " + keyTerm2 + ", "
                            + (int)term2.getX() + ", " + (int)term2.getY()
                            + ", " + (int)term2.getWidth() + ", " + (int)term2
                                .getHeight() + ")");
                    }

                }
            }

        }

    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     * 
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
        // Rectangles list to print
        SkipList<String, Rectangle>.SkipNode[] listOfRectangles = list
            .findbyValue(name);
        if (listOfRectangles.length == 1) { // If skip list is empty
            System.out.println("Rectangle not found: " + name);
        }
        else { // Print results
            System.out.println("Rectangles found:");
            for (int i = 0; i < listOfRectangles.length; i++) {
                if (listOfRectangles[i] != null) {
                    System.out.print("(" + listOfRectangles[i].element()
                        .getKey());
                    System.out.print(", " + listOfRectangles[i].element()
                        .getValue().x);
                    System.out.print(", " + listOfRectangles[i].element()
                        .getValue().y);
                    System.out.print(", " + listOfRectangles[i].element()
                        .getValue().width);
                    System.out.println(", " + listOfRectangles[i].element()
                        .getValue().height + ")");
                }
            }
        }
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        System.out.println("SkipList dump:");
        SkipList<String, Rectangle>.SkipNode[] resultList = list.dump();

        if (resultList.length == 1) { // If skip list is empty
            System.out.println("Node has depth 1, Value (null)");
        }
        else { // Prints all necessary information for each node
            for (int i = 0; i < resultList.length; i++) {
                int depth = resultList[i].getLevel() + 1;
                System.out.print("Node has depth " + (depth + ", Value ("));
                if (resultList[i].element() != null) {
                    Rectangle rect = new Rectangle();
                    rect.x = resultList[i].element().getValue().x;
                    rect.y = resultList[i].element().getValue().y;
                    rect.width = resultList[i].element().getValue().width;
                    rect.height = resultList[i].element().getValue().height;
                    String s = resultList[i].element().getKey().toString();
                    System.out.println(s + ", " + rect.x + ", " + rect.y + ", "
                        + rect.width + ", " + rect.height + ")");
                }
                else {
                    System.out.print(resultList[i].element() + ")\n");
                }

            }
        }
        System.out.println("SkipList size is: " + (resultList.length - 1));
    }
}

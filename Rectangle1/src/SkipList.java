import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Random;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author Rishabh Rastogi
 * 
 * @version 2021-09-09
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {
    private SkipNode head; // First element of the top level
    private int size; // number of entries in the Skip List
    private int level; // Length of deepest node in skip list

    /**
     * Initialises the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        level = 0; // initial depth is zero
        size = 0; // initial size is zero
    }


    /**
     * Returns a random level number which is used as the depth of the SkipNode
     * 
     * @return a random level number
     */
    private int randomLevel() {
        int levNode;
        Random randNumber = new Random();
        for (levNode = 0; Math.abs(randNumber.nextInt()) % 2 == 0; levNode++) {
            // Do nothing
        }
        return levNode; // returns a random level
    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicographical order.
     * 
     * @param it
     *            KV pair to insert
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {
        int newLevel = randomLevel(); // New node's random level
        if (newLevel > level) { // Adjust the header if new level is deeper.
            SkipNode tempHead = head;
            head = new SkipNode(null, newLevel);
            for (int i = 0; i <= level; i++) {
                head.forward[i] = tempHead.forward[i];
            }
            level = newLevel;
        }
        // Track end of level
        SkipNode[] updateArray = (SkipNode[])Array.newInstance(SkipNode.class,
            level + 1);
        SkipNode cursorMove = head;
        for (int i = level; i >= 0; i--) { // Find insert position
            while ((cursorMove.forward[i] != null)
                && (cursorMove.forward[i].pair.getKey().compareTo(it
                    .getKey()) < 0)) {
                cursorMove = cursorMove.forward[i];
            }
            updateArray[i] = cursorMove;
        }
        cursorMove = new SkipNode(it, newLevel);
        for (int i = 0; i <= newLevel; i++) {
            cursorMove.forward[i] = updateArray[i].forward[i];
            updateArray[i].forward[i] = cursorMove;
        }
        size++; // Increment size of collection
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * @param key
     *            the KVPair to be removed
     * @return returns the removed pair by key(name) if the pair was valid and
     *         null if not
     */

    public KVPair<K, V> remove(K key) {
        SkipNode traversalNode = head;
        SkipNode findNode = findNode(key);
        if (findNode == null) { // if key is null and no node can be found
            return null;
        }
        SkipNode[] replace = findNode.forward;
        int currentLevel = traversalNode.forward.length - 1; // Get the level
        while (traversalNode != null) { // Move through the skip list

            for (int i = currentLevel; i >= 0; i--) { // traverses list
                if (traversalNode.forward[i] != null
                    && (traversalNode.forward[i] == findNode)) {
                    traversalNode.forward[i] = replace[i];
                }
            }

            traversalNode = traversalNode.forward[0]; // Move node forward by 1
            if (traversalNode != null) { // Gets new depth if traversalNode does
                                         // not equal null

                currentLevel = traversalNode.forward.length - 1;
            }
        }
        size--;
        return findNode.element(); // successful return.
    }


    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
    public KVPair<K, V> removeByValue(V val) {
        SkipNode traversalNode = head.forward[0];
        while (traversalNode != null) {
            if (traversalNode.element().getValue().equals(val)) {
                return remove(traversalNode.element().getKey());
            }
            traversalNode = traversalNode.forward[0];
        }
        return null;
    }


    /**
     * Finds first skip node that matched the given object
     * 
     * @param searchingKey
     * @return skip node for the given key, null if none exist
     */
    private SkipNode findNode(K searchingKey) {
        SkipNode traversalNode = head; // Dummy header node
        for (int i = level; i >= 0; i--) { // For each level go forward
            while ((traversalNode.forward[i] != null) && (searchingKey
                .compareTo(traversalNode.forward[i].pair.getKey()) > 0)) {
                traversalNode = traversalNode.forward[i];
            }
        }
        traversalNode = traversalNode.forward[0]; // Move to record.
        if ((traversalNode != null) && (searchingKey.compareTo(
            traversalNode.pair.getKey()) == 0)) {
            return traversalNode; // Node found
        }
        else {
            return null; // Node not found
        }
    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     * 
     * @return Prints out the SkipList in a human readable format to the
     *         console.
     * 
     */
    public SkipNode[] dump() {
        SkipNode tempHead = head;
        KVPair<K, V> dumpPairs;
        @SuppressWarnings("unchecked")
        SkipNode[] list = (SkipNode[])Array.newInstance(SkipNode.class, 1);
        int length = 0;
        while (tempHead != null) { // traverses the skip list
            if (length == 0) {
                if (tempHead.element() != null) {
                    dumpPairs = new KVPair<K, V>(tempHead.element().getKey(),
                        tempHead.element().getValue());
                }
                else {
                    dumpPairs = null;
                }
                list[0] = new SkipNode(dumpPairs, tempHead.forward.length);
                length++;
            }
            else { // Creates bigger array and adds node to list
                @SuppressWarnings("unchecked")
                SkipNode[] tempArray = (SkipNode[])Array.newInstance(
                    SkipNode.class, list.length + 1);
                System.arraycopy(list, 0, tempArray, 0, list.length);
                dumpPairs = new KVPair<K, V>(tempHead.element().getKey(),
                    tempHead.element().getValue());
                tempArray[tempArray.length - 1] = new SkipNode(dumpPairs,
                    tempHead.forward.length);
                list = tempArray;
            }
            tempHead = tempHead.forward[0];
        }
        return list;

    }


    /**
     * Finds all KVPairs given a search key
     * 
     * @param searchingKey
     *            key used to find results
     * @return list of all KVPairs found using searchKey
     */
    public SkipNode[] findbyValue(K searchingKey) {
        @SuppressWarnings("unchecked")
        SkipNode[] result = (SkipNode[])Array.newInstance(SkipNode.class, 1);
        SkipNode tempHead = head; // temporary head header node
        int startLevel = level;
        while (tempHead != null) {
            for (int i = startLevel; i >= 0; i--) {
                try {
                    while ((tempHead.forward[i] != null) && (searchingKey
                        .compareTo(tempHead.forward[i].element()
                            .getKey()) > 0)) {
                        tempHead = tempHead.forward[i];
                    }
                }
                catch (Exception e) {
                    return result;
                }
            }
            tempHead = tempHead.forward[0];

            /*
             * If the skip node contains the search, it places it in the
             * result array
             */
            if ((tempHead != null) && (searchingKey.compareTo(tempHead.element()
                .getKey()) == 0)) {
                @SuppressWarnings("unchecked")
                SkipNode[] tempArray = (SkipNode[])Array.newInstance(
                    SkipNode.class, result.length + 1);
                System.arraycopy(result, 0, tempArray, 0, result.length);
                KVPair<K, V> pairs = new KVPair<K, V>(searchingKey, tempHead
                    .element().getValue());
                tempArray[tempArray.length - 1] = new SkipNode(pairs, 0);
                result = tempArray;
                startLevel = tempHead.forward.length - 1;
            }

        }
        return result;

    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author Rishabh Rastogi
     * 
     * @version 2021-09-09
     */
    public class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        /** array of forward nodes */
        private SkipNode[] forward;
        // the number of levels
        private int level;

        /**
         * Initialises the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {

            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.setLevel(level);
        }


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }


        /**
         * @return the level
         */
        int getLevel() {
            return level;
        }


        /**
         * @param level
         *            the level to set
         */
        public void setLevel(int level) {
            this.level = level;
        }

    }


    /**
     * This class implements a SkipNode Iterator for the SkipList data
     * structure.
     * 
     * @author Rishabh Rastogi
     * 
     * @version 2021-09-09
     */

    public class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;

        /**
         * Constructor
         */

        public SkipListIterator() {
            current = head;
        }


        @Override
        public boolean hasNext() {
            if (current.forward[0] != null) {
                current = current.forward[0];
                return true;
            }
            return false;
        }


        @Override
        public KVPair<K, V> next() {

            return (KVPair<K, V>)current.element();
        }

    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new SkipListIterator();
    }

}

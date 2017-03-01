/**
 * Created by joshua on 22/02/17.
 */
public class HeapSort extends Sorter {

    private int memoryacesses;
    private int[] ints;

    public HeapSort(int[] ints) {
        super(ints);
    }


    /**
     *  Algorithm credit goes to: http://algs4.cs.princeton.edu/24pq/Heap.java.html
     *
     *  The {@code Heap} class provides a static methods for heapsorting
     *  an array.
     *  <p>
     *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
     *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
     *
     *  @author Robert Sedgewick
     *  @author Kevin Wayne
     *
     *  Copyright © 2000–2016, Robert Sedgewick and Kevin Wayne.
     *   Last updated: Mon Feb 20 17:01:50 EST 2017.
     */

    @Override
    public int[] sort() {
        setMemoryacesses(0);

        int[] sortints = createClone();
        int n = sortints.length;
        addToAccesses(2);

        for (int k = n/2; k >= 1; k--) {
            sink(sortints, k, n);

        }
        while (n > 1) {
            exch(sortints, 1, n--);
            sink(sortints, 1, n);
        }

        setInts(sortints);
        return sortints;
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private void sink(int[] sortints, int k, int n) {
        while (2*k <= n) {
            addToAccesses(2);
            int j = 2*k;
            addToAccesses(2);
            if (j < n && less(sortints, j, j+1)) j++;
            addToAccesses(2);
            if (!less(sortints, k, j)) break;
            exch(sortints, k, j);
            k = j;
            addToAccesses(2);
        }
    }

    /***************************************************************************
     * Helper functions for comparisons and swaps.
     * Indices are "off-by-one" to support 1-based indexing.
     ***************************************************************************/
    private boolean less(int[] sortints, int i, int j) {
        addToAccesses(2);
        return sortints[i-1] < sortints[j-1];
    }

    private void exch(int[] sortints, int i, int j) {
        int swap = sortints[i-1];
        addToAccesses(2);
        sortints[i-1] = sortints[j-1];
        addToAccesses(2);
        sortints[j-1] = swap;
        addToAccesses(2);
    }
    
    private int[] createClone(){
        int[] clone = new int[getInts().length];
        addToAccesses(1);
        
        for(int i=0; i<getInts().length; i++){
            clone[i] = getInts()[i];
            addToAccesses(2);
        }
        
        return clone;
    }


}

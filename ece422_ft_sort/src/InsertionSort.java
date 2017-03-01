/**
 * Created by joshua on 22/02/17.
 */
public class InsertionSort extends Sorter {

    public native int[] insertionSort(int[] ints);

    public InsertionSort(int[] ints) {
        super(ints);
    }

    //static {
      //  System.loadLibrary("");
   // }

    @Override
    public int[] sort() {
        System.out.println("Insertion Sort");
        return super.sort();
    }
}

import java.util.Arrays;

/**
 * Created by joshua on 22/02/17.
 */
public class InsertionSort extends Sorter {

    public native int[] insertionSort(int[] ints);

    public InsertionSort(int[] ints) {
        super(ints);
    }

    static {
        System.loadLibrary("InsertionSort");
    }

    @Override
    public int[] sort() {
        int[] jniReturn = new int[getInts().length + 1];
        jniReturn[0] = 0;
        for(int i=0; i<getInts().length; i++ ){
            jniReturn[i+1] = getInts()[i];
        }

        jniReturn = insertionSort(jniReturn);
        setMemoryAccesses(jniReturn[0]);
        setInts(Arrays.copyOfRange(jniReturn, 1, jniReturn.length));

        return getInts();
    }
}

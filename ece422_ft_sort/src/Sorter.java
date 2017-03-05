/**
 * Created by joshua on 22/02/17.
 */
public abstract class Sorter {

    /*
    * Memory accesses will be defined as reading or writing to a variable
    * */
    public int memoryAccesses;
    private int[] ints;

    public Sorter(int[] ints){
        setInts(ints);
        setMemoryAccesses(0);
    }

    public int[] sort() {

        return new int[0];
    }

    public int[] getInts() {
        return ints;
    }

    public void setInts(int[] ints) {
        this.ints = ints;
    }

    public int getMemoryAccesses() {
        return memoryAccesses;
    }

    public void setMemoryAccesses(int memoryacesses) {
        this.memoryAccesses = memoryacesses;
    }

    public void addToAccesses(int i){
        setMemoryAccesses(getMemoryAccesses() + i);
    }
}

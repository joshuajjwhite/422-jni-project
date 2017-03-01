/**
 * Created by joshua on 22/02/17.
 */
public abstract class Sorter {

    /*
    * Memory accesses will be defined as reading or writing to a variable
    * */
    public int memoryacesses;
    private int[] ints;


    public Sorter(int[] ints){
        setInts(ints);
        setMemoryacesses(0);
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

    public int getMemoryacesses() {
        return memoryacesses;
    }

    public void setMemoryacesses(int memoryacesses) {
        this.memoryacesses = memoryacesses;
    }

    public void addToAccesses(int i){
        setMemoryacesses(getMemoryacesses() + i);
    }
}

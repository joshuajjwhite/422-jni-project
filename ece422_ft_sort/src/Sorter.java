/**
 * Created by joshua on 22/02/17.
 */
public abstract class Sorter {

    private int[] ints;

    public Sorter(int[] ints){
        setInts(ints);
    }

    public int[] sort(){

        return new int[0];
    }

    public int[] getInts() {
        return ints;
    }

    public void setInts(int[] ints) {
        this.ints = ints;
    }
}

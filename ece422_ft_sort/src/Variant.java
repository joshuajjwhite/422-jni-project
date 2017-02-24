import static java.lang.Thread.sleep;

/**
 * Created by joshua on 22/02/17.
 */
public class Variant implements Runnable {

    private float failrate;
    private Sorter sorter;
    private SortAdjudicator at;

    public Variant(Sorter sorter, float failrate, SortAdjudicator at){
        setFailrate(failrate);
        setSorter(sorter);
        setAt(at);
    }


    @Override
    public void run() {
        try{
            sort();
            //for (;;);
            //for(int i=0; i<getSorter().getInts().length; i++){System.out.println(getSorter().getInts()[i]);}
        }
        catch (ThreadDeath td){
            System.out.println("Variant was Killed");
            throw new ThreadDeath();
        }
    }


    public void sort(){
        try {
            if (false) {
                //failure
                throw new localException();
            }
            getAt().ajudicate(getSorter().sort());
        }
        catch (localException le){
            System.out.println("Random Simulated Failure");
            getAt().variantFailure();
        }
    }

    public float getFailrate() {
        return failrate;
    }

    public void setFailrate(float failrate) {
        this.failrate = failrate;
    }

    public Sorter getSorter() {
        return sorter;
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public SortAdjudicator getAt() {
        return at;
    }

    public void setAt(SortAdjudicator at) {
        this.at = at;
    }
}

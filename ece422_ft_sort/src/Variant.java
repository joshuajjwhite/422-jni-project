import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by joshua on 22/02/17.
 */
public class Variant implements Runnable {

    private float failrate;
    private Sorter sorter;
    private SortAdjudicator at;
    private boolean success;

    public Variant(Sorter sorter, float failrate, SortAdjudicator at){
        setFailrate(failrate);
        setSorter(sorter);
        setAt(at);
    }


    @Override
    public void run() {
        try{
            sort();
        }
        catch (ThreadDeath td){
            System.out.print("Variant was Killed... ");
            variantFailure();
            throw new ThreadDeath();
        }
    }


    public void sort(){
        try {
            getAt().ajudicate(getSorter().sort());

            if (isFailure() || !getAt().isSuccess()) {
                throw new localException();
            }
            variantSuccess();
        }
        catch (localException le){
            variantFailure();
        }
    }

    public float getFailrate() {
        return failrate;
    }

    public void setFailrate(float hazard) {
        this.failrate = hazard;
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

    private boolean isFailure(){
        //Evaluates random failure of variant
        Random rand = new Random();
        float hazard = sorter.getMemoryacesses()*getFailrate();
        float chance = rand.nextFloat();

        if((0.5f <= chance) && (chance <= (0.5f + hazard))){
            System.out.println("Random Failure... chance: " + chance + " hazard: " + hazard);
            return true;
        }
        return false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private void variantFailure(){
        System.out.println("Failure of Variant");
        setSuccess(false);
    }

    private void variantSuccess(){
        System.out.println("Success of Variant");
        setSuccess(true);
    }
}

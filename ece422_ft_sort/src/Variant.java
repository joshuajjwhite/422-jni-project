import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by joshua on 22/02/17.
 */
public class Variant implements Runnable {

    private float failRate;
    private Sorter sorter;
    private SortAdjudicator at;
    private boolean success;

    public Variant(Sorter sorter, float failRate, SortAdjudicator at){
        setFailRate(failRate);
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
                throw new LocalException();
            }
            variantSuccess();
        }
        catch (LocalException le){
            variantFailure();
            getAt().setSuccess(false);
        }
    }

    public float getFailRate() {
        return failRate;
    }

    public void setFailRate(float hazard) {
        this.failRate = hazard;
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
        float hazard = sorter.getMemoryAccesses()* getFailRate();
        float chance = rand.nextFloat();

        if((0.5f <= chance) && (chance <= (0.5f + hazard))){
            System.out.println("Memory Failure... chance: " + chance + " hazard: " + hazard);
            return true;
        }

        System.out.println("No Memory Failure... chance: " + chance + " hazard: " + hazard);
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

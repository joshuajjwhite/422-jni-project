import static java.lang.Thread.sleep;

/**
 * Created by joshua on 22/02/17.
 */
public class Variant implements Runnable {

    private float failrate;
    private Sorter variant;

    public Variant(Sorter variant, float failrate){
        setFailrate(failrate);
        setVariant(variant);
    }


    @Override
    public void run() {
        try{
            sort();
            //for (;;);
            //for(int i=0; i<getVariant().getInts().length; i++){System.out.println(getVariant().getInts()[i]);}

        }
        catch (ThreadDeath td){
            System.out.println("Variant is Dead");
            throw new ThreadDeath();
        }
    }



    public void sort(){
        if(getFailrate() > 0){
            System.out.println("Variant Failed");
            throw new ThreadDeath();
        }
        getVariant().sort();
    }

    public float getFailrate() {
        return failrate;
    }

    public void setFailrate(float failrate) {
        this.failrate = failrate;
    }

    public Sorter getVariant() {
        return variant;
    }

    public void setVariant(Sorter variant) {
        this.variant = variant;
    }
}

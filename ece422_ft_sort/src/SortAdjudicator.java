/**
 * Created by joshua on 22/02/17.
 */
public class SortAdjudicator {

    private boolean status;

    public SortAdjudicator(){
        setStatus(false);
    }


    public void ajudicate(int[] ints) {
        if(isSorted(ints)){
            System.out.print("Adjudicator Success... ");
            variantSuccess();
        }
        else{
            System.out.print("Adjudicator Failure... ");
            variantFailure();
        }
    }

    public static boolean isSorted(int[] ints){
        for (int i = 0; i < ints.length-1; i++){
            if(ints[i] > ints[i+1]){
                return false;
            }
        }
        return true;
    }

    public boolean success() {
        return status;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public void variantSuccess() {
        System.out.println("Variant Success");
        setStatus(true);
    }

    public void variantFailure() {
        System.out.println("Variant Failure");
        setStatus(false);
    }
}

/**
 * Created by joshua on 22/02/17.
 */
public class SortAdjudicator {

    private boolean success;

    public SortAdjudicator(){
        setSuccess(false);
    }


    public void ajudicate(int[] ints) {
        if(isSorted(ints)){
            System.out.print("Ajudicator Success... ");
            ajudicatorSuccess();
        }
        else{
            System.out.print("Ajudicator Failure... ");
            ajudicatorFailure();
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

    public void ajudicatorSuccess() {
        setSuccess(true);
    }

    public void ajudicatorFailure() {
        setSuccess(false);
    }
}

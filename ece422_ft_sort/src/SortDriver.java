import java.io.IOException;
import java.util.Timer;

public class SortDriver {

    private FileHandler input;
    private FileHandler output;

    private Variant primary;
    private Variant secondary;

    private SortAdjudicator at;

    private int timeout;

    public SortDriver(String inputfile, String outputfile, float pfail, float sfail, int timeout){
        setInput(new FileHandler(inputfile));
        setOutput(new FileHandler(outputfile));
        setTimeout(timeout);

        setAt(new SortAdjudicator());

        //Checkpoint
        setPrimary(new Variant(new HeapSort(getInput().readFromFile()), pfail, getAt()));
        setSecondary(new Variant(new InsertionSort(getInput().readFromFile()), sfail, getAt()));

    }

    public void start(){
        //Primary Algorithm
        runVariant(getPrimary(), "Primary");

        if(getPrimary().isSuccess()){
            System.out.println("Success on Primary");
            writeVariant(getPrimary());
        }
        else{
            System.out.println("Running Secondary...");
            runVariant(getSecondary(), "Secondary");
        }

        /*
        if(getAt().isSuccess()){
            System.out.println("Success on Secondary");
            writeVariant(getSecondary());
        }
        else{
            System.out.println("Failure on Secondary");
            throw new ThreadDeath();
        }*/
    }

    private void runVariant(Variant variant, String desc) {
        Thread thread = new Thread(variant);
        WatchDog watchdog = new WatchDog(thread);

        Timer timer = new Timer();
        timer.schedule(watchdog, getTimeout());

        thread.start();
        try {
            thread.join();
            timer.cancel();
            System.out.print("End of " + desc + "... ");
            //System.out.println(Integer.toString(variant.getSorter().getMemoryacesses()) + " Memory Accesses");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(desc + " Interrupted");
        }
    }

    private void writeVariant(Variant variant){
        try {
            getOutput().writeToFile(variant.getSorter().getInts());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public FileHandler getOutput() {
        return output;
    }

    public void setOutput(FileHandler output) {
        this.output = output;
    }

    public FileHandler getInput() {
        return input;
    }

    public void setInput(FileHandler input) {
        this.input = input;
    }


    public Variant getPrimary() {
        return primary;
    }

    public void setPrimary(Variant primary) {
        this.primary = primary;
    }

    public Variant getSecondary() {
        return secondary;
    }

    public void setSecondary(Variant secondary) {
        this.secondary = secondary;
    }

    public SortAdjudicator getAt() {
        return at;
    }

    public void setAt(SortAdjudicator at) {
        this.at = at;
    }

    public static void main(String[] args){
        SortDriver driver = new SortDriver(args[0], args[1], Float.parseFloat(args[2]),
                Float.parseFloat(args[3]), Integer.parseInt(args[4]));
        driver.start();
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}


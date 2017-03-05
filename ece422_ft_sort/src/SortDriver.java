import java.io.IOException;
import java.util.Timer;

public class SortDriver {

    private FileHandler input;
    private FileHandler output;

    private Variant primary;
    private Variant secondary;

    private SortAdjudicator at;

    private int timeOut;

    public SortDriver(String inputFile, String outputFile, float primaryFail, float secondaryFail, int timeOut){
        setInput(new FileHandler(inputFile));
        setOutput(new FileHandler(outputFile));
        setTimeOut(timeOut);

        setAt(new SortAdjudicator());

        //Checkpoint
        setPrimary(new Variant(new HeapSort(getInput().readFromFile()), primaryFail, getAt()));
        setSecondary(new Variant(new InsertionSort(getInput().readFromFile()), secondaryFail, getAt()));

    }

    public void start(){
        runVariant(getPrimary(), "Primary");

        if(getPrimary().isSuccess() && getAt().isSuccess()){
            System.out.println("Success on Primary\n");
            writeVariant(getPrimary());
        }
        else{

            runVariant(getSecondary(), "Secondary");

            if(getSecondary().isSuccess() && getAt().isSuccess()){
                System.out.println("Success on Secondary\n");
                writeVariant(getSecondary());
            }
            else{
                System.out.println("Failure on Secondary");
                getOutput().deleteFile();
                throw new ThreadDeath();
            }
        }
        throw new ThreadDeath();
    }

    private void runVariant(Variant variant, String description) {
        Thread thread = new Thread(variant);
        WatchDog watchdog = new WatchDog(thread);

        //Timers run on their own thread
        Timer timer = new Timer();
        timer.schedule(watchdog, getTimeOut());

        thread.start();
        try {
            System.out.println("\nRunning " + description +"...");
            thread.join();
            timer.cancel();
            System.out.println("End of " + description + "... ");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(description + " Interrupted");
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

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }
}


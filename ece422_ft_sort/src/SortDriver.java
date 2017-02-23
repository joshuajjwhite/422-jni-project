import java.util.Timer;

public class SortDriver {

    private FileHandler input;
    private FileHandler output;

    private Variant primary;
    private Variant secondary;

    private WatchDog watchdog;

    private SortAdjudicator at;

    private Timer timer;

    private int timeout;

    public SortDriver(String inputfile, String outputfile, float primaryfr, float secondaryfr, int timeout){
        setInput(new FileHandler(inputfile));
        setOutput(new FileHandler(outputfile));
        setTimeout(timeout);

        //Checkpoint
        setPrimary(new Variant(new HeapSort(getInput().readFromFile()), primaryfr));
        setSecondary(new Variant(new InsertionSort(getInput().readFromFile()), secondaryfr));

        setTimer(new Timer());

        setAt(new SortAdjudicator());

        setWatchdog(null);
    }

    public void start(){
        //Primary Algorithm
        //runVariant(getPrimary(), "Primary");

        Thread thread = new Thread(getPrimary());
        setWatchdog(new WatchDog(thread));

        getTimer().schedule(getWatchdog(), getTimeout());
        thread.start();
        try {
            thread.join();
            getTimer().cancel();
            System.out.println("End of " + "Primary");
            thread=null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Primary" + " Interrupted");
        }

        if(getAt().test(getPrimary().getVariant().getInts())){
            System.out.println("Sucess 1");
        }

        //Secondary
        Thread secondary = new Thread(getSecondary());
        setWatchdog(new WatchDog(secondary));

        setTimer(new Timer());
        getTimer().schedule(getWatchdog(), getTimeout());
        secondary.start();
        try {
            secondary.join();
            getTimer().cancel();
            System.out.println("End of " + "Secondary");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Secondary" + " Interrupted");
        }

        if(getAt().test(getSecondary().getVariant().getInts())){
            System.out.println("Sucess 2");
        }
    }

    public void runVariant(Variant variant, String desc){

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



    public WatchDog getWatchdog() {
        return watchdog;
    }

    public void setWatchdog(WatchDog watchdog) {
        this.watchdog = watchdog;
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

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}


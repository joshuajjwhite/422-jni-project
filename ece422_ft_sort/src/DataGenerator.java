import java.util.Random;
import java.io.*;


/**
 * Created by joshua on 09/02/17.
 */
public class DataGenerator {

    private int size;
    private FileHandler output;

    private DataGenerator(String outputFile, int size) {
        this.setSize(size);
        this.setOutput(new FileHandler(outputFile));
    }

    private FileHandler getOutput() {
        return this.output;
    }

    private void setOutput(FileHandler output) {
        this.output = output;
    }

    private int getSize() {
        return size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    private int[] generate(){
        int[] array = new int[getSize()];
        Random rand = new Random();

        for(int i=0; i< getSize(); i++){
            array[i] = rand.nextInt();
        }
        return array;
    }

    private void write(int[] ints){
        try {
            getOutput().writeToFile(ints);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     *
     */
    public static void main(String[] args) {

        DataGenerator generator = new DataGenerator(args[0], Integer.parseInt(args[1]));
        generator.write(generator.generate());
    }
}

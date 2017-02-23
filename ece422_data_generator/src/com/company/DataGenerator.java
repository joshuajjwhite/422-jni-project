package com.company;

import java.util.Random;
import java.io.*;

/**
 * Created by joshua on 09/02/17.
 */
public class DataGenerator {

    private int size;
    private String outputfile;

    private DataGenerator(String outputfile, int size){
        this.setSize(size);
        this.setOutputfile(outputfile);
    }

    private String getOutputfile() {
        return outputfile;
    }

    private void setOutputfile(String outputfile) {
        this.outputfile = outputfile;
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

    //Credit to Steven
    //http://stackoverflow.com/questions/5820508/writing-an-array-to-a-file-in-java
    private void writeToFile(int[] ints) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.getOutputfile()));
            for (int i: ints){
                writer.write(Integer.toString(i) + " ");
            }
            writer.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param args
     *
     */
    public static void main(String[] args) {

        DataGenerator generator = new DataGenerator(args[0],Integer.parseInt(args[1]));
        try {
            generator.writeToFile(generator.generate());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by joshua on 21/02/17.
 */
public class FileHandler {

    private String filename;

    public FileHandler(String filename){
        setFilename(filename);
    }

    //Credit to Steven
    //http://stackoverflow.com/questions/5820508/writing-an-array-to-a-file-in-java
    protected void writeToFile(int[] ints) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFilename()));
            for (int i: ints){
                writer.write(Integer.toString(i) + " ");
            }
            writer.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    protected int[] readFromFile(){
        try {
            String str = FileUtils.readFileToString(new File(getFilename()), "utf-8");
            str = str.trim().replace("\n", "");
            String[] stringsplit = str.split(" ");
            int[] ints = new int[stringsplit.length];

            for(int i=0; i<stringsplit.length; i++) {
                ints[i] = Integer.parseInt(stringsplit[i]);
            }

            return ints;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}

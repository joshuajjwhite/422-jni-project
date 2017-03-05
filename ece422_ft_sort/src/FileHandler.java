import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by joshua on 21/02/17.
 */
public class FileHandler {

    private String fileName;

    public FileHandler(String fileName){
        setFileName(fileName);
    }

    //Credit to Steven
    //http://stackoverflow.com/questions/5820508/writing-an-array-to-a-file-in-java
    protected void writeToFile(int[] ints) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFileName()));
            for (int i: ints){
                writer.write(Integer.toString(i) + " ");
            }
            writer.write("\n");
            writer.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    protected int[] readFromFile(){
        try {
            String str = FileUtils.readFileToString(new File(getFileName()), "utf-8");
            str = str.trim().replace("\n", "");
            String[] stringSplit = str.split(" ");
            int[] ints = new int[stringSplit.length];

            for(int i=0; i<stringSplit.length; i++) {
                ints[i] = Integer.parseInt(stringSplit[i]);
            }

            return ints;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void deleteFile(){
        File file = new File(getFileName());
        file.delete();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

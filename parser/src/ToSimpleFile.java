import java.io.*;
import java.lang.*;
import java.util.*;

public class ToSimpleFile {

    private Formatter file;
    ToSimpleFile(String values[][]) {

        String fileName = "knnParsed.txt";
        try {
            PrintWriter outputStream = new PrintWriter(fileName);

            for(int i=0;i<values.length;i++) {
                outputStream.println("########### File :" + i + "###############");
                for (int j = 0; j < values[i].length; j++)
                    outputStream.println(values[i][j]);
                outputStream.println("################################");
            }
            outputStream.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

}

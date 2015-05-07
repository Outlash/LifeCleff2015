import java.io.*;
import java.lang.*;
import java.util.*;

public class ToSimpleFile {

    private Formatter file;
    private String fileName = "src\\xml\\metadatatrain.train";

    ToSimpleFile(String values[][]) {

        try {
            PrintWriter outputStream = new PrintWriter(fileName);

            outputStream.println(values.length+" "+(values[0].length-1));//scrien nr de seturi de valori + nr de valori dintr-un set

            for(int i=1;i<values.length;i++) {
                for (int j = 0; j < values[i].length; j++)
                    outputStream.print(values[i][j] + " ");
                outputStream.println();
            }
            outputStream.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

}

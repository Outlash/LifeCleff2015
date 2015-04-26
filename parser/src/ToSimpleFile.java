import java.io.*;
import java.lang.*;
import java.util.*;

public class ToSimpleFile {

    private Formatter file;
    private String fileName = "xmlTrainingValues.txt";

    ToSimpleFile(String values[][]) {

        try {
            PrintWriter outputStream = new PrintWriter(fileName);

            outputStream.println(values.length+" "+values[0].length);//scrien nr de seturi de valori + nr de valori dintr-un set

            for(int i=0;i<values.length;i++) {
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

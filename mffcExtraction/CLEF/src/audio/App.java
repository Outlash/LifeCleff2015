package audio;

import files.FileIterator;
import files.SaxParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Ionut on 5/7/2015.
 */
public class App {

    public static boolean train=true;
    public static void main(String[] args) {
        String buffer = "";
        Operations op = new Operations();
        FileIterator it=new FileIterator(train);
        File[] wavFiles;
        File wavFile=null;
        wavFiles = it.readWaveFilesList();
        for (int i = 0; i < wavFiles.length; i++) {
                    wavFile = wavFiles[i];
                    buffer = op.generateLines(wavFile);
                    save_output(buffer, wavFile);
                    System.out.println(Integer.toString(i)+"/"+Integer.toString(wavFiles.length));


            }

    }

    public static String get_id(String filename) {
        String[] ids = filename.split("_");
        String lastone = ids[ids.length - 1];
        ids = lastone.split("\\.");
        lastone = ids[0];
        return lastone;
    }

    public static String get_classid(File wavfile) {
        String filename=wavfile.getName();
        int pos = filename.lastIndexOf(".");
        if (pos > 0) {
            filename = filename.substring(0, pos);
        }
        filename+=".xml";
        filename="xml-files/xml/train/"+filename;
        SaxParser sp= new SaxParser(filename);
        return sp.get_class_id();

    }

    public static void save_output(String buffer,File wavfile) {
        try {
            File file=null;
            String classid="";
            if(train) {
                file = new File("output/train/"+ wavfile.getName()+".txt");
                classid="#"+get_classid(wavfile);
                buffer+=classid;
            }
            else
            {
                file = new File("output/test/"+wavfile.getName()+".txt");
            }
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(buffer);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

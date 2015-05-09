package audio;

import files.FileIterator;
import files.SaxParser;

import java.io.*;

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
        for (int i = 77; i < wavFiles.length; i++) {
            System.out.print(wavFiles[i].getName()+"--");
            System.out.println(Integer.toString(i) + "/" + Integer.toString(wavFiles.length));
                    wavFile = wavFiles[i];
                    //buffer = op.generateLines(wavFile);
            try {
                op.generateLines(wavFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //save_output(buffer, wavFile);

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
            //BufferedWriter bw = new BufferedWriter(fw);
            fw.write(buffer);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

}

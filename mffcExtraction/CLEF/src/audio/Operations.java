/*
  Please feel free to use/modify this class. 
  If you give me credit by keeping this information or
  by sending me an email before using it or by reporting bugs , i will be happy.
  Email : gtiwari333@gmail.com,
  Blog : http://ganeshtiwaridotcomdotnp.blogspot.com/ 
 */
package audio;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import audio.feature.FeatureVector;
/**
 * @author Ganesh Tiwari
 */
public class Operations {

    FormatControlConf fc = new FormatControlConf();
    int samplingRate = (int) fc.getRate();
    int samplePerFrame = 256;//16ms for 8 khz
    //int samplePerFrame = 512;// 23.22ms
    int FEATUREDIMENSION = 39;
    List<double[]> allFeaturesList = new ArrayList<double[]>();
    FeatureExtract fExt;
    WaveData wd;
    PreProcess prp;

    public Operations() {
        wd = new WaveData();
    }

    public void generateLines(File wavFile) throws IOException {



        String Output="";
        String classid="";
        BufferedWriter writer=null;
        if(App.train) {
            writer = new BufferedWriter(new FileWriter("output/train/" + wavFile.getName() + ".txt"));
            classid="#"+App.get_classid(wavFile);
        }
        else
        {
            writer = new BufferedWriter(new FileWriter("output/train/" + wavFile.getName() + ".txt"));
        }
        PrintWriter printWriter=new PrintWriter(writer);
        try {
            FeatureVector feature = extractFeatureFromFile(wavFile);
            for (int k = 0; k < feature.getNoOfFrames(); k++) {
                allFeaturesList.add(feature.getMfccFeature()[k]);
                double[] tmp = allFeaturesList.get(k);

                for (int j = 0; j < tmp.length; j++)
                    printWriter.print((j + 1) + ":" + tmp[j] + " ");
                //Output+=(j+1)+":"+tmp[j]+" ";
                printWriter.print(System.lineSeparator());

                //Output+="%n";

            }
        }
     catch (Exception e) {
         System.out.println("Error : " + e.getMessage());
         File dest=null;
         if(App.train) {
             dest = new File("input/error/train/"+wavFile.getName());
         }else
         {
             dest = new File("input/error/test/"+wavFile.getName());
         }
         if (!dest.exists()) {
             dest.createNewFile();
         }
         System.out.print(dest.getAbsolutePath());

         App.copyFileUsingStream(wavFile, dest);
     }

        printWriter.print(classid);
        printWriter.close();
       // return String.format(Output);
    }


    /**
     * @param byteArray
     * @return
     * @throws Exception
     */
    public FeatureVector extractFeatureFromFileByteArray(byte[] byteArray) {
        float[] arrAmp;
        arrAmp = wd.extractAmplitudeFromFileByteArray(byteArray);
        return extractFeatureFromExtractedAmplitureByteArray(arrAmp);
    }


    public FeatureVector extractFeatureFromExtractedAmplitureByteArray(float[] arrAmp) {
        prp = new PreProcess(arrAmp, samplePerFrame, samplingRate);
        fExt = new FeatureExtract(prp.framedSignal, samplingRate, samplePerFrame);
        fExt.makeMfccFeatureVector();
        return fExt.getFeatureVector();
    }

    /**
     * @param wavFile
     * @return
     * @throws Exception
     */
    private FeatureVector extractFeatureFromFile(File wavFile) {
        float[] arrAmp;
        arrAmp = wd.extractAmplitudeFromFile(wavFile);
        return extractFeatureFromExtractedAmplitureByteArray(arrAmp);
    }


    /**
     * @return
     */
    public boolean checkSelectedPath() {
        return true;
    }


}
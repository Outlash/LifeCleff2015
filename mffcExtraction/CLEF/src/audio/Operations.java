/*
  Please feel free to use/modify this class. 
  If you give me credit by keeping this information or
  by sending me an email before using it or by reporting bugs , i will be happy.
  Email : gtiwari333@gmail.com,
  Blog : http://ganeshtiwaridotcomdotnp.blogspot.com/ 
 */
package audio;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import audio.FeatureExtract;
import audio.FormatControlConf;
import audio.PreProcess;
import audio.WaveData;
import audio.feature.FeatureVector;
import util.ArrayWriter;

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

    public String generateLines(File wavFile) {
        int totalFrames = 0;



        FeatureVector feature = extractFeatureFromFile(wavFile);
        for (int k = 0; k < feature.getNoOfFrames(); k++) {
            allFeaturesList.add(feature.getMfccFeature()[k]);
            totalFrames++;//
        }
        String Output="";
        // make a single 2d array of all features
        double allFeatures[][] = new double[totalFrames][FEATUREDIMENSION];
        for (int i = 0; i < totalFrames; i++) {
            double[] tmp = allFeaturesList.get(i);
            for(int j=0;j<tmp.length;j++)
                Output+=j+":"+tmp[j]+" ";
            Output+="%n";
            allFeatures[i] = tmp;
        }
        return String.format(Output);
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
package files;

import java.io.File;

/**
 * Created by Ionut on 5/7/2015.
 */
public class FileIterator {

    protected String[] folderNames;
    protected File[][] waveFiles;
    protected File wavPath;

    /**
     *
     */
    /**
     * constructor, sets the wavFile path according to the args supplied
     *
     * @param train
     */
    public FileIterator(boolean train) {
        if (!train) {
            setWavPath(new File( "input/test"));
        } else {
            setWavPath(new File( "input/train/trainingQualityPack#1")); //first folder
        }

    }

    private void readFolder() {
//		System.out.println(getWavPath().getAbsolutePath());
        folderNames = new String[getWavPath().list().length];
        folderNames = getWavPath().list();// must return only folders
    }

    public String[] readWordWavFolder() {
        readFolder();
        return folderNames;
    }

    public File[] readWaveFilesList() {
        //readFolder();
        return getWavPath().listFiles();

    }

    public File getWavPath() {
        return wavPath;
    }

    public void setWavPath(File wavPath) {
        this.wavPath = wavPath;
        System.out.println("Current wav file Path   :" + this.wavPath.getName());
    }
}

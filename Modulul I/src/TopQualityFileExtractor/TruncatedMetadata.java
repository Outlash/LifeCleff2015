package TopQualityFileExtractor;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import javax.xml.stream.Location;

/**
 * Created by Daniel on 23-Apr-15.
 */
public class TruncatedMetadata
{

    public void setMediaID(int mediaID)
    {
        this.mediaID = mediaID;
    }

    public void setClassID(String classID)
    {
        this.classID = classID;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public void setQuality(int quality)
    {
        this.quality = quality;
    }

    public void setVernacularName(String vernacularName)
    {
        VernacularName = vernacularName;
    }



    private int mediaID;
    private String classID;
    private String fileName;

    public int getQuality()
    {
        return quality;
    }

    private int quality;

    public String getVernacularName()
    {
        return VernacularName;
    }



    //Bird Name
    private String VernacularName;

    TruncatedMetadata()
    {

    }

    TruncatedMetadata(int medId, String cls, String file, int qlty, String bird)
    {
        mediaID = medId;
        classID = cls;

        fileName = file;
        quality = qlty;
        VernacularName = bird;
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        result.append("<bird>" +LineSeparator.Windows);
        result.append("<VernacularName>" + VernacularName + "</VernacularName>" + LineSeparator.Windows);
        result.append("<mediaId>"+mediaID+"</mediaId>"+ LineSeparator.Windows);
        result.append("<class>"+classID+"</class>"+ LineSeparator.Windows);
        //location
        result.append("<Location>" + "C:\\LIFECLEF2015BIRDCHALLENGE_TRAIN_TEST_SETS\\sounds\\train\\" + fileName + "</Location>"+ LineSeparator.Windows);
        result.append("<quality>"+quality+"</quality>"+ LineSeparator.Windows);
        result.append("</bird>"+ LineSeparator.Windows);

        return result.toString();
    }
}

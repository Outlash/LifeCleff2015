package TopQualityFileExtractor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Daniel on 23-Apr-15.
 */
public class FileExtractor
{

    //<bird Name, Records>
    private static Map<String, LinkedList<TruncatedMetadata>> Birds;
    private static final String XMLSOURCE = "C:\\LIFECLEF2015BIRDCHALLENGE_TRAIN_TEST_SETS\\xml\\train";
    private static final String WAVSOURCE = "C:\\LIFECLEF2015BIRDCHALLENGE_TRAIN_TEST_SETS\\sounds\\train";
    private static long totalTime;
    private static ArrayList<Path> trainingFiles;

    //Log files
    private static PrintWriter xmlParserLog = null;

    FileExtractor()
    {
    }

    private static void loadXMLFiles()
    {
        //temporary resources
        trainingFiles = new ArrayList<Path>(24607);
        File folder = null;
        XmlParser xmlParser = null;

        //initialisations
        try
        {
            Birds = new HashMap<String, LinkedList<TruncatedMetadata>>(1000);

            folder = new File(XMLSOURCE);
            xmlParserLog = new PrintWriter("xmlParserLog.txt", "UTF-8");
            xmlParser = new XmlParser();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        int counter = 1;
        int progress = 0, total = folder.listFiles().length;

        //computing HashMap for every species
        for (File file : folder.listFiles())
        {

            TruncatedMetadata temp = xmlParser.parseXML(file, xmlParserLog);

            if (Birds.containsKey(temp.getVernacularName()))
                Birds.get(temp.getVernacularName()).add(temp);

            else
            {
                LinkedList<TruncatedMetadata> t = new LinkedList<TruncatedMetadata>();
                t.add(temp);

                Birds.put(temp.getVernacularName(), t);
            }

            if ((counter * 100 / total) > progress)
            {
                progress++;
                System.out.println("" + progress + "%");
            }

            counter++;
        }

        System.out.println("Loaded all fileNames");
        totalTime = System.currentTimeMillis() - totalTime;
        System.out.println(totalTime);

        //closing files
        xmlParserLog.close();

    }

    //extracts top 10 qualitative files
    private static void extractQualityFilenames()
    {
        PrintWriter destinationFile = null;
        try
        {
            destinationFile = new PrintWriter("topQualityFiles.txt", "UTF-8");
            destinationFile.println("<TrainingFiles>");

            //here I am sorting birds based on bird quality
            for (Map.Entry entry : Birds.entrySet())
            {

                //check last year discussion
                int maxQuality = 1;

                int savedRecords = 0;
                //max quality
                for (TruncatedMetadata bird : (LinkedList<TruncatedMetadata>) entry.getValue())
                {

                    if (bird.getQuality() == maxQuality && savedRecords < 10)
                    {
                        destinationFile.println(bird);
                        savedRecords++;
                    }

                }

                if (savedRecords >= 10)
                    continue;
                //maxQuality+1 => weaker quality
                for (TruncatedMetadata bird : (LinkedList<TruncatedMetadata>) entry.getValue())
                {

                    if (bird.getQuality() == maxQuality + 1 && savedRecords < 10)
                    {
                        destinationFile.println(bird);
                        savedRecords++;
                    }

                }
                if (savedRecords >= 10)
                    continue;
                //maxQuality + 2, or unknown Quality
                for (TruncatedMetadata bird : (LinkedList<TruncatedMetadata>) entry.getValue())
                {

                    if (bird.getQuality() >= maxQuality + 2 && savedRecords < 10)
                    {
                        destinationFile.println(bird);
                        savedRecords++;
                    }
                    else if (bird.getQuality() == 0 && savedRecords < 10)
                    {
                        destinationFile.println(bird);
                        savedRecords++;
                    }

                }

                //print birds which had a total of less than 10 records
                if (savedRecords < 10)
                {
                    System.out.println(entry.getKey() + " : " + savedRecords);
                }
            }

            destinationFile.println("</TrainingFiles>");
            destinationFile.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private static void copyQualityFilenames()
    {
        List<String> fileNames = null;
        PrintWriter copyLog = null;

        //before running this function you must manually edit to_copy.txt that on each line it l0oks like this:
        //LIFECLEF2015_BIRDAMAZON_XC_WAV_RN14165.wav
        //use RegEx
        try
        {
            fileNames = Files.readAllLines(Paths.get("to_copy.txt"));
            copyLog = new PrintWriter("CopyLog.txt");
            copyLog.println("Files not found. They exist in xml, but not in Original folder.\n");
            copyLog.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //counter variables
        int counter = 1;
        int progress = 0, total = fileNames.size();

        //loop where copying takes place
        for (String file : fileNames)
        {
            try
            {

                //copy Xml's
                Path source = Paths.get(XMLSOURCE + "\\" + file.substring(0, file.length() - 4) + ".xml");
                Path destination = Paths.get("E:\\BirdCLEF\\training\\xml\\" + file.substring(0, file.length() - 4) + ".xml");

                Files.copy(source, destination);

                //copy .wav
                source = Paths.get(WAVSOURCE + "\\" + file);
                destination = Paths.get("E:\\BirdCLEF\\training\\sounds\\" + file);

                Files.copy(source, destination);

                //inform user about progress
                if ((counter * 100 / total) > progress)
                {
                    progress++;
                    System.out.println("" + progress + "% T:" + counter);
                }


            }
            catch (Exception e)
            {
                System.out.println("E: " + counter);
                copyLog.println(e.getMessage());
                copyLog.flush();
            }

            finally
            {
                counter++;
            }

        }

    }

    public static void main(String[] args) throws FileNotFoundException
    {
        //loadXMLFiles();
       /* System.out.println("Done loading files!");
        extractQualityFilenames();
       */

        System.out.println("Started copying files...It may take a while");
        copyQualityFilenames();

        //get quality filenames...useful for other modules
        PrintWriter t = new PrintWriter("CopiedAudiFiles.txt");
        File folder = new File("E:\\BirdCLEF\\training\\sounds");

        for (File f : folder.listFiles())
        {
            t.println(f.getName());
        }

        t.close();
    }

}

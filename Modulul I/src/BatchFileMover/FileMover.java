package BatchFileMover;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by Daniel on 23-Apr-15.
 */
public class FileMover
{
    static private PrintWriter logFile = null;
    static ArrayList<Path> trainingFiles;
    static ArrayList<String> notFoundFiles;
    private static final String AUDIOFILESLOCATION = "C:\\LIFECLEF2015BIRDCHALLENGE_TRAIN_TEST_SETS\\sounds";
    private static final String DESTINATION = "C:\\LIFECLEF2015BIRDCHALLENGE_TRAIN_TEST_SETS\\sounds\\train";
    private static final String SOURCE = "C:\\LIFECLEF2015BIRDCHALLENGE_TRAIN_TEST_SETS\\xml\\train";
    private static long totalTime;


    public static void main(String[] args)
    {
        totalTime = System.currentTimeMillis();

        loadFiles();
        moveFiles();
        generateLog();

    }

    private static void moveFiles()
    {
        String source, target;
        int counter = 0;
        notFoundFiles = new ArrayList<String>(1000);

        for (Path path : trainingFiles)
        {
            try
            {
                source = AUDIOFILESLOCATION + "\\" + path.getFileName();
                target = DESTINATION + "\\" + path.getFileName();
                Files.move(Paths.get(source), Paths.get(target));

                if (counter % 100 == 0)
                {
                    System.out.println(" " + counter + path.getFileName());
                }
                counter++;
            }

            catch (IOException e)
            {
                notFoundFiles.add(e.getMessage());
            }
        }
    }

    private static void loadFiles()
    {
        //number of files
        trainingFiles = new ArrayList<Path>(24607);
        File folder = new File(SOURCE);

        int counter = 0;
        String newFilename;
        for (File file : folder.listFiles())
        {
            //change filename
            newFilename = file.getName().substring(0, file.getName().length() - 4);
            trainingFiles.add(Paths.get(SOURCE + "\\" + newFilename + ".wav"));

            if (counter % 1000 == 0)
                System.out.println(counter);

            counter++;
        }

        System.out.println("Loaded all fileNames");
        totalTime = System.currentTimeMillis() - totalTime;
        System.out.println(totalTime);

        System.out.println(trainingFiles.get(15000).getFileName());
    }

    private static void generateLog()
    {
        StringBuilder s =new StringBuilder();

        s.append("Files Not Found: "+notFoundFiles.size());
        s.append('\n');
        for(String file : notFoundFiles)
        {
            s.append(file+"\n");
        }


        try
        {
            logFile = new PrintWriter(DESTINATION+"\\#log.txt", "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        logFile.print("Total Time: ");
        logFile.println(System.currentTimeMillis() - totalTime);

        logFile.println();
        logFile.println(s.toString());
        logFile.close();

    }
}

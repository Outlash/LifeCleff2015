import java.io.*;
import java.util.*;

public class Format
{
    String inputTrain = "input" + File.separator + "Train";
    String outputFolder = "output" + File.separator + "TrainOutput";
    String currentDirectory;
    Map idClasses = new HashMap<String, Integer>();


    public void format()
    {
        /*inputTrain = "E:\\New folder";
        outputFolder = "E:\\";*/
        int i = 0;
        try
        {
            //Se seteaza fisierul de output in /output/output.txt
            String outputFileName = outputFolder + File.separator + "output.txt";
            FileWriter fileWriter = new FileWriter(outputFileName);
            BufferedWriter output = new BufferedWriter(fileWriter);

            File dir = new File(inputTrain);

            for (String file : dir.list())
            {
                i++;
                System.out.println("\t" + i);
                //Se ia fiecare fisier din /input/inputTrain
                String inputFile = inputTrain + File.separator + file;
                FileReader fileReader = new FileReader(inputFile);
                BufferedReader input = new BufferedReader(fileReader);


                //Se extrage class-id
                String classID = getLastLine(inputFile);
                if (classID.length() < 2)
                    classID = input.readLine();
                classID = classID.substring(1, classID.length());
                Integer ID = (Integer) idClasses.get(classID);


                String inputLine = null;
                String outputLine = "";


                //Se citeste cate o linie si se adauga clasa
                while ((inputLine = input.readLine()) != null)
                {
                    if (inputLine.length() > 10)
                    {
                        outputLine += ID + " " + inputLine + "\n";
                        output.write(outputLine);
                        outputLine = "";
                    }
                }

                input.close();
            }
            output.close();
        } catch (FileNotFoundException ex)
        {
            System.out.println("Unable to open file!");
        } catch (IOException ex)
        {
            System.out.println("Error reading file!");
            // ex.printStackTrace();
        }
    }

    public String getLastLine(String fileName)
    {
        File file = new File(fileName);
        String result = "";

        // file needs to exist
        if (file.exists() == false || file.isDirectory())
        {
            return "";
        }

        // avoid empty files
        if (file.length() == 0)
        {
            return "";
        }

        try
        {
            // open the file for read-only mode
            RandomAccessFile fileAccess = new RandomAccessFile(file, "r");

            // set initial position as last one, except if this is an empty line
            long position = file.length() - 2;
            int breakLine = "\n".charAt(0);

            // keep looking for a line break
            while (position > 0)
            {
                // look for the offset
                fileAccess.seek(position);
                // read the new char
                final int thisChar = fileAccess.read();
                // do we have a match?
                if (thisChar == breakLine)
                {
                    result = fileAccess.readLine();
                    //System.out.println(position + ": " + fileAccess.readLine());
                    break;
                }
                // decrease the offset
                position--;
            }
            // close the stream (thanks to Michael Schierl)
            fileAccess.close();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        // all done
        return result;
    }

    void loadMap()
    {
        try
        {
            FileReader fileReader = new FileReader(currentDirectory + File.separator + "id-classes.txt");
            BufferedReader input = new BufferedReader(fileReader);
            String inputLine = null;

            //citim cate o linie
            while ((inputLine = input.readLine()) != null)
            {
                Scanner scan = new Scanner(inputLine).useDelimiter("\n| ");

                if (scan.hasNext())
                {
                    String key = scan.next();
                    int value = scan.nextInt();
                    idClasses.put(key, value);
                }
            }

            input.close();

        } catch (IOException e)
        {
            System.out.println("Error reading file");
        }
    }

    void printMap()
    {
        Iterator iterator = idClasses.keySet().iterator();

        while (iterator.hasNext())
        {
            String key = iterator.next().toString();
            String value = idClasses.get(key).toString();

            System.out.println(key + " " + value);
        }

    }

    void setPaths()
    {
        currentDirectory = new File("").getAbsolutePath();
        inputTrain = currentDirectory + File.separator + inputTrain;
        outputFolder = currentDirectory + File.separator + outputFolder;
    }

}


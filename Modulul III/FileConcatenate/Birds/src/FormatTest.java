import java.io.*;

public class FormatTest
{
    String inputTrain = "input" + File.separator + "Test";
    String outputFolder = "output" + File.separator + "TestOutput";
    String currentDirectory;


    public void format()
    {
        int i = 0;
        try
        {

            File dir = new File(inputTrain);

            for (String file : dir.list())
            {
                i++;
                System.out.println("\t" + i);

                //Se ia fiecare fisier din /input/Test
                String inputFile = inputTrain + File.separator + file;
                FileReader fileReader = new FileReader(inputFile);
                BufferedReader input = new BufferedReader(fileReader);

                //Se seteaza fisierul de output in /output/TestOutput
                String outputFileName = outputFolder + File.separator + file;
                FileWriter fileWriter = new FileWriter(outputFileName);
                BufferedWriter output = new BufferedWriter(fileWriter);

                String inputLine = null;
                String outputLine = "";


                //Se citeste cate o linie si se adauga clasa
                while ((inputLine = input.readLine()) != null)
                {
                    if (inputLine.length() > 10)
                    {
                        outputLine += "0 " + inputLine + "\n";
                        output.write(outputLine);
                        outputLine = "";
                    }
                }

                input.close();
                output.close();
            }

        } catch (FileNotFoundException ex)
        {
            System.out.println("File not found!");
        } catch (IOException ex)
        {
            System.out.println("Error reading file!");
            // ex.printStackTrace();
        }
    }


    void setPaths()
    {
        currentDirectory = new File("").getAbsolutePath();
        inputTrain = currentDirectory + File.separator + inputTrain;
        outputFolder = currentDirectory + File.separator + outputFolder;
    }

}


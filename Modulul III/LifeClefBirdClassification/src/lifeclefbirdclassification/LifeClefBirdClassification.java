/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lifeclefbirdclassification;

/**
 *
 * @author Dinu
 */

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
<<<<<<< HEAD
=======
import java.util.Map.Entry;
>>>>>>> a5169b348b702d9b97e87b38c42cf89838df9d38


class Element implements Comparable{
    int cod, valoare;

    Element(){}

    Element(int val1, int val2) {
        cod = val1;
        valoare = val2;
    }

    int getCod(){
        return cod;
    }

    int getValoare() {
        return valoare;
    }


    @Override
    public int compareTo(Object o) {
        int compareVal=((Element)o).getValoare();
        /* For Ascending order*/
        return compareVal-this.valoare;
    }
}

class TestProcent {
    int cod;
    String fileName;
    float procentFinal;
    int nrLinii;
    Map<String, Double> procentaje;

    TestProcent(String name, int val1, int val2) {
        fileName = name;
        cod = val1;
        nrLinii = val2;
        procentaje = new HashMap<String, Double>();
    }
    
    TestProcent(int val1, int val2) {
        fileName = new String();
        cod = val1;
        nrLinii = val2;
        procentaje = new HashMap<String, Double>();
    }

<<<<<<< HEAD
=======
    public void setProcentaje(Map<String, Double> hashmap) {
        procentaje = hashmap;
    }
>>>>>>> a5169b348b702d9b97e87b38c42cf89838df9d38
    public void setProcentFinal(float valoare) {
        procentFinal = valoare;
    }
    
    public String getFileName() {
        return fileName;
    }

    public int getNrLinii(){
        return nrLinii;
    }

    public int getCod(){
        return cod;
    }

    public float getProcentFinal() {
        return procentFinal;
    }

    public Map<String, Double> getProcentaje(){
        return procentaje;
    }

    public void addProcent(String classID, Double valoare){
        procentaje.put(classID, valoare);
    }

}


public class LifeClefBirdClassification {

    private String pathPasari, pathID, pathScript, path;

    //private Map<Integer, Integer> trainNumbers = new HashMap();
    //private Map<String, Integer> pasariNameValue = new HashMap();

    private Map<String, Integer> classIDClassValue = new HashMap();
    private Map<Integer, String> classIDValueClass = new HashMap();

    //private List<Element> predictionsSet;

    List<TestProcent> valoriTeste = new ArrayList<TestProcent>();

    //private int newClassID = 1000;
    //private int[] nrRanduri = new int[100];

    private float procent;
    private List<Integer> predictionsList = new ArrayList<Integer>();
    private boolean executat = false;






    public LifeClefBirdClassification() throws IOException {
        //pathScript = "E:\\Facultate\\AnII\\IP\\LifeClef\\libsvm-3.20\\libsvm-3.20\\windows\\";
        classIDClassValue = getBirdNumber("E:\\Download Normal\\result.txt");
        invertClass();
<<<<<<< HEAD
        concatTrainFiles("E:\\Download Normal\\Train\\");
        countAndConcatTestFiles("E:\\Download Normal\\Test\\");
        executeCode("");
        predictionsList = getPredictionsList("predict.dat");
        getPredictionsApparition(predictionsList);
        print();
=======
        //concatTrainFiles("E:\\Download Normal\\Train\\");
        countAndConcatTestFiles("E:\\Download Normal\\Test1\\Test\\");
        //executeCode("");
        //predictionsList = getPredictionsList("predict.dat");
        //getPredictionsApparition(predictionsList);
        //print();
>>>>>>> a5169b348b702d9b97e87b38c42cf89838df9d38
    }

    public LifeClefBirdClassification(String cale) {
        //predictionsSet = new ArrayList<Element>();
        //predictionsApparitions = new ArrayList<TestProcent>();
        path = cale;
        //pasariName = new String();
        procent = 0;
        pathPasari = cale + "pasari.txt";
        pathID = cale + "class.txt";
        pathScript = cale + "svm_multiclass\\";

        try {
            classIDClassValue = getBirdNumber(pathID);
            predictionsList = getPredictionsList("example4\\predictions.txt");
            //predictionsSet = getPredictionsSet(predictions);
            getPredictionsApparition(predictionsList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void invertClass() {
        for(Map.Entry<String, Integer> entry : classIDClassValue.entrySet()){
            classIDValueClass.put(entry.getValue(), entry.getKey());
        }
    }



    /*
    // Aceasta functie va concatena fisierele (train)
    // intr-un singur fisier
 */
    public void concatTrainFiles(String path) throws IOException {
        File parent = new File(path);
        int[] ClassID = new int[50000];
        String val;
        int i = 0;

        List<Path> inputs = new ArrayList<Path>();



        for (final File fileEntry : parent.listFiles()) {
            if (fileEntry.isDirectory()) {
                concatTrainFiles(fileEntry.getPath());
            } else {
                val = getClassID(fileEntry.getPath());
                if (val.isEmpty()) continue;
                if (classIDClassValue.containsKey(val))
                    ClassID[i] = classIDClassValue.get(getClassID(fileEntry.getPath()));
                else ClassID[i] = 0;
                i++;
                System.out.println(fileEntry.getName());
                inputs.add(Paths.get(path + fileEntry.getName()));
            }
        }

        //Path output = Paths.get("outputFile.txt");
        FileWriter output = new FileWriter("train.dat");
        //BufferedWriter writer;
        //writer = new BufferedWriter(new FileWriter(output));


        // Charset for read and write
        Charset charset = StandardCharsets.UTF_8;
        List<String> lines = new ArrayList<String>();
        i = 0;
        // Join files (lines)
        for (Path pathFile : inputs) {
            lines = Files.readAllLines(pathFile, charset);
            System.out.println(lines.get(0));

            //Files.write(output, lines, charset, StandardOpenOption.CREATE,
            //        StandardOpenOption.APPEND);

            for (String line : lines) {
                output.write(ClassID[i] + " " + line + "\n" );
            }
            lines = null;
            i++;
        }

        System.out.println("-------------- Am concatenat fisierele TRAIN. ------------");
        output.close();
    }

<<<<<<< HEAD

=======
    /*
    // Aceasta functie va concatena fisierele (test)
    // intr-un singur fisier
 */
>>>>>>> a5169b348b702d9b97e87b38c42cf89838df9d38

    public void countAndConcatTestFiles(String path) throws IOException {
        File parent = new File(path);

        int[] ClassID = new int[50000];
        String val;
        int i = 0;
        int nrLines = 0;
        int tempClassID = 1000;

        List<Path> inputs = new ArrayList<Path>();



        for (final File fileEntry : parent.listFiles()) {
            if (fileEntry.isDirectory()) {
                countAndConcatTestFiles(fileEntry.getPath());
            } else {
                val = getClassID(fileEntry.getPath());
                if (val == null) continue;
                if (classIDClassValue.containsKey(val))
                    ClassID[i] = classIDClassValue.get(getClassID(fileEntry.getPath()));
                else ClassID[i] = tempClassID;
                i++;
                tempClassID++;
                inputs.add(Paths.get(path + fileEntry.getName()));
            }
        }

        //Path output = Paths.get("outputFile.txt");
        FileWriter output = new FileWriter("test.dat");
        //BufferedWriter writer;
        //writer = new BufferedWriter(new FileWriter(output));

        String name;

        // Charset for read and write
        Charset charset = StandardCharsets.UTF_8;
        List<String> lines = new ArrayList<String>();
        i = 0;
        int k = 0;
        // Join files (lines)
        for (Path pathFile : inputs) {
            name = new String();
            name = pathFile.toString();
            nrLines = 0;
            lines = Files.readAllLines(pathFile, charset);
<<<<<<< HEAD
            System.out.println(lines.get(0));
=======
            //System.out.println(lines.get(0));
>>>>>>> a5169b348b702d9b97e87b38c42cf89838df9d38

            //Files.write(output, lines, charset, StandardOpenOption.CREATE,
            //        StandardOpenOption.APPEND);

            for (String line : lines) {
                output.write(ClassID[i] + " " + line + "\n" );
                nrLines++;
            }
            lines = null;
            //valoriTeste.add(new TestProcent(ClassID[i], nrLines));
            valoriTeste.add(new TestProcent(name, ClassID[i], nrLines));
            lines = null;
            i++;
        }

        System.out.println("-------------- Am concatenat fisierele TEST. ------------");
        output.close();
    }



<<<<<<< HEAD

=======
    /*
    // Aceasta functie va executa algoritmul de cautare
 */
>>>>>>> a5169b348b702d9b97e87b38c42cf89838df9d38

    public void executeCode(String path) throws IOException {
        //Runtime rt = Runtime.getRuntime();
        Process q = Runtime.getRuntime().exec("svm-train -s 0 train.dat model.dat");
        Process p = Runtime.getRuntime().exec("svm-predict -b 0 test.dat model.dat predict.dat");

        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line;
        if ((line = input.readLine()) != null) {
            StringTokenizer lineToken = new StringTokenizer(line);
            String temp = new String();
            temp = lineToken.nextToken();
            temp = lineToken.nextToken();
            temp = lineToken.nextToken();
            temp = temp.substring(0, temp.length() - 1);
            procent = Float.parseFloat(temp);
        }

        System.out.println("-------------- Am executat codul. ------------");
        executat = true;
    }


    public List<Integer> getPredictionsList(String path) throws IOException {
        List<Integer> temp = new ArrayList<Integer>();

        //path = pathScript + path;

        if(!executat)
            executeCode("");

        BufferedReader buffer = new BufferedReader(new FileReader(path));

        // read the first line that contains the size of the dataset and number of attributes in each example
        String newLine = buffer.readLine();

        while (newLine != null) {
            StringTokenizer st = new StringTokenizer(newLine);
            temp.add(Integer.parseInt(st.nextToken()));

            newLine = buffer.readLine();
        }

        System.out.println("-------------- Am incarcat LISTA DE PREDICTII. ------------");
        return temp;
    }


    /*
        // Aceasta functie va numara corespondentele
        // fiecarei clase pentru un fiesier test introdus
     */

    public void getPredictionsApparition(List<Integer> pred){
        Map<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
        int k;
        int i = -1;
<<<<<<< HEAD
=======
        Map<String, Double> unsortedMap = new HashMap<String, Double>();
>>>>>>> a5169b348b702d9b97e87b38c42cf89838df9d38
        System.out.println(valoriTeste.size());
        for (int j = 0; j < valoriTeste.size(); j++) {
            for (k = 0; k < valoriTeste.get(j).getNrLinii(); k++) {
                ++i;
                if (tempMap.containsKey(pred.get(i))) {
                    tempMap.put(pred.get(i), tempMap.get(pred.get(i)) + 1);
                } else {
                    tempMap.put(pred.get(i), 1);
                }


            }
            System.out.println(valoriTeste.get(j).getNrLinii());
            i = k - 1;
            System.out.println(i);
            for (Integer key : tempMap.keySet()) {
<<<<<<< HEAD
                valoriTeste.get(j).addProcent(classIDValueClass.get(key), ((double)(tempMap.get(key)) / (double)(valoriTeste.get(j).getNrLinii())));
            }
=======
                unsortedMap.put(classIDValueClass.get(key), ((double)(tempMap.get(key)) / (double)(valoriTeste.get(j).getNrLinii())));
                //valoriTeste.get(j).addProcent(classIDValueClass.get(key), ((double)(tempMap.get(key)) / (double)(valoriTeste.get(j).getNrLinii())));
            }
            valoriTeste.get(j).setProcentaje(sortByComparator(unsortedMap, false));
>>>>>>> a5169b348b702d9b97e87b38c42cf89838df9d38
            valoriTeste.get(j).setProcentFinal(procent);
            tempMap = new HashMap<Integer, Integer>();
        }
        
        System.out.println("-------------- Am incarcat LISTELE cu PREDICTII ale claselor TEST. ------------");
    }







    /*
        // Aceasta functie se va ocupa de extragerea
        // ClassID-ul de pe ultima linie
     */

    public String getClassID(String path) {
        File file = new File(path);

        if (file.length() == 0) return null;

        RandomAccessFile fileHandler = null;
        try {
            fileHandler = new RandomAccessFile( file, "r" );
            long fileLength = fileHandler.length() - 1;
            StringBuilder sb = new StringBuilder();

            for(long filePointer = fileLength; filePointer != -1; filePointer--){
                fileHandler.seek( filePointer );
                int readByte = fileHandler.readByte();

                if( readByte == 0xA ) {
                    if( filePointer == fileLength ) {
                        continue;
                    }
                    break;

                } else if( readByte == 0xD ) {
                    if( filePointer == fileLength - 1 ) {
                        continue;
                    }
                    break;
                }

                sb.append( ( char ) readByte );
            }

            String lastLine = sb.reverse().toString();
            
<<<<<<< HEAD
            System.out.print("-------------- Am returnat CLASSID-ul pentru fisierul; " + file.getName() + " ----- ");
=======
            //System.out.print("-------------- Am returnat CLASSID-ul pentru fisierul; " + file.getName() + " ----- ");
>>>>>>> a5169b348b702d9b97e87b38c42cf89838df9d38
            return lastLine.substring(1, lastLine.length());
        } catch( java.io.FileNotFoundException e ) {
            e.printStackTrace();
            return null;
        } catch( java.io.IOException e ) {
            e.printStackTrace();
            return null;
        } finally {
            if (fileHandler != null )
                try {
                    fileHandler.close();
                } catch (IOException e) {
                /* ignore */
                }
        }
        
        
    }

    /*
        // Aceasta functie va extrage ClassID-urile
        // si le va atribui o valoare
     */

    public Map<String, Integer> getBirdNumber(String path) throws IOException {
        Map<String, Integer> temp = new HashMap();

        FileReader file = new FileReader(path);
        BufferedReader buffer = new BufferedReader(file);
        String newLine = new String();
        String sir = new String();
        int number;
        int i = 0;

        newLine = buffer.readLine();

        while (newLine != null) {
            StringTokenizer st = new StringTokenizer(newLine);
            sir = st.nextToken();
            number = Integer.parseInt(st.nextToken());

            temp.put(sir, number);

            newLine = buffer.readLine();
        }

        
<<<<<<< HEAD
        System.out.print("-------------- Am incarcat LISATA HASH cu CLASSID --------------");
=======
        //System.out.print("-------------- Am incarcat LISATA HASH cu CLASSID --------------");
>>>>>>> a5169b348b702d9b97e87b38c42cf89838df9d38
        return temp;
    }






    public void print(){
        System.out.println(this.procent);
        for (TestProcent testFile : valoriTeste) {
<<<<<<< HEAD
            System.out.println(testFile.getFileName() + ": " + testFile.getCod());
            //System.out.println(testFile.getCod() + ":");
            Iterator it = testFile.getProcentaje().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println("\t" + pair.getKey() + " = " + pair.getValue());
=======
            int i = 0;
            System.out.println(testFile.getFileName() + ": " + testFile.getCod());
            //System.out.println(testFile.getCod() + ":");
            i = testFile.getProcentaje().size();
            if (testFile.getProcentaje().size() > 10) i = 10;
            Iterator it = testFile.getProcentaje().entrySet().iterator();
            while (it.hasNext() && i > 0) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println("\t" + pair.getKey() + " = " + pair.getValue());
                i--;
>>>>>>> a5169b348b702d9b97e87b38c42cf89838df9d38
                it.remove(); // avoids a ConcurrentModificationException
            }
        }
    }

<<<<<<< HEAD
=======
    
    
    
    private Map<String, Double> sortByComparator(Map<String, Double> unsortMap, boolean order) {
        
        if(unsortMap.size() == 0) return null;

        List<Entry<String, Double>> list = new LinkedList<Entry<String, Double>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Double>>()
        {
            public int compare(Entry<String, Double> o1,
                    Entry<String, Double> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Entry<String, Double> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
>>>>>>> a5169b348b702d9b97e87b38c42cf89838df9d38


    /*
        // Aceasta functie va calcula cea mai lunga
        // secventa de Clase egale
     */
    /*
    public List<Element> getPredictionsSet(List<Integer> pred){
        List<Element> tempList = new ArrayList<Element>();
        Map<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
        int value;
        int count;
        int maxValue;
        int maxCount;

        maxValue = pred.get(0);
        maxCount = 1;

        for (int i = 0; i < pred.size() - 1; i++) {
            value = pred.get(i);
            count = 1;

            for (int j = i + 1; j < pred.size(); j++) {
                if (pred.get(j) == value) count++;
                else {
                    break;
                }
            }

            if (count > maxCount) {
                maxValue = value;
                maxCount = count;
            }

            if (tempMap.containsKey(maxValue)) {
                if (tempMap.get(value) < count) {
                    tempMap.put(value, count);
                }
            }
            else {
                tempMap.put(value, count);
            }
        }

        Element aux;
        for (Integer key : tempMap.keySet()) {
            aux = new Element(key, tempMap.get(key));
            tempList.add(aux);
        }

        tempMap.clear();

        Collections.sort(tempList);
        return tempList;
    }
    */


    /*
        // Aceasta functie va transforma fisierele de test (fara label)
        // in fisiere compatibile cu algoritmul
     */




    public void getProcent() throws IOException {

        Map<String, Double> procentClasses = new HashMap();
        //List<Integer> predictionCode = new ArrayList<Integer>;

        //testCode = getTestNumbers("test.dat");
        //predictionCode = getPredictions("predictions.dat");


        for(Map.Entry<String, Integer> entry : classIDClassValue.entrySet()){
            classIDValueClass.put(entry.getValue(), entry.getKey());
        }
    }

    /*
        // Va rula Script-ul pentru generarea corespondentelor
     */



    public Map<Integer, Integer> getClassNrLines(String path) throws IOException {
        Map<Integer, Integer> temp = new HashMap<Integer, Integer>();

        path = pathScript + path;

        int classID;
        BufferedReader buffer = new BufferedReader(new FileReader(path));

        String newLine = buffer.readLine();

        while (newLine != null) {
            StringTokenizer st = new StringTokenizer(newLine);
            classID = Integer.parseInt(st.nextToken());

            if (temp.containsKey(classID)) {
                temp.put(classID, temp.get(classID) + 1);
            }
            else {
                temp.put(classID, 1);
            }


            newLine = buffer.readLine();
        }

        return temp;
    }




    public static void main(String[] args) throws IOException {
	// write your code here

<<<<<<< HEAD
        LifeClefBirdClassification proc2 = new LifeClefBirdClassification();
=======
        LifeClefBirdClassification proces = new LifeClefBirdClassification();
>>>>>>> a5169b348b702d9b97e87b38c42cf89838df9d38
        //proc2.executeCode("C:\\Users\\Dinu\\Documents\\NetBeansProjects\\Procentaj\\src\\com\\company\\svm_multiclass\\");
        //Procentaj proc = new Procentaj("C:\\Users\\Dinu\\Documents\\NetBeansProjects\\Procentaj\\src\\com\\company\\");



    }
}

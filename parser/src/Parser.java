import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;

public class Parser
{
    private String[][] values;
    private File[] files;
    private int nrOfFiles;
    File directory;

    public Parser(){
        this.directory=new File("src/file");//setam directorul
        this.files=this.directory.listFiles();//listam toate fisierele
        this.nrOfFiles=this.files.length;// initializam nr de fisiere
        this.values=new String[this.nrOfFiles][];
    }

    public void machineParser(){

        DocumentBuilderFactory documentBuilderFactorytory =DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactorytory.newDocumentBuilder();
            try {
                int index=0;
                for(File file : files) {

                    Document document = documentBuilder.parse(file.getAbsoluteFile());//documentul pe care il parsam

                    NodeList nodeList = document.getElementsByTagName("*");//lista cu toate tagurile

                    System.out.println("List =" + nodeList.getLength());//numarul de taguri

                    values[index]=new String[nodeList.getLength()];//initializam o linie din matrice cu nr de taguri

                    for (int i = 1; i < nodeList.getLength(); i++)//pt fiecare tag
                    {
                        Element element = (Element) nodeList.item(i);//luam un tag
                        //System.out.println(element.getNodeName());
                        if (element.getTextContent().length() == 0) {
                            values[index][i]="#";
                           // System.out.println("not found");
                        } else{
                            //System.out.println(element.getTextContent());//numele valorii din tag
                            values[index][i]=element.getTextContent();
                             }
                    }
                }
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


    public String[][] getValues() {
        return values;
    }
}

package TopQualityFileExtractor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Created by Daniel on 23-Apr-15.
 */
class XmlParser
{
    private static DocumentBuilderFactory dbFactory;
    private static DocumentBuilder dBuilder;
    private static Document doc;                           //parsed (tree)xml


    XmlParser()
    {
        dbFactory = DocumentBuilderFactory.newInstance();
        try
        {
            dBuilder = dbFactory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param xmlFile   //a .xml File Object containing Metadata
     * @param xmlParserLog  //Used for error logging. Can be removed.
     * @return            //Object initialised with data from Xml
     */
    public TruncatedMetadata parseXML(File xmlFile,PrintWriter xmlParserLog)
    {
        TruncatedMetadata tempMetadata = null;
        try
        {
            doc = dBuilder.parse(xmlFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //optional, but recommended
        doc.getDocumentElement().normalize();


        try
        {
            Node nNode = doc.getElementsByTagName("Audio").item(0);

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) nNode;
                tempMetadata = new TruncatedMetadata();


                //Initialises an object with data from XML.
                //Here you can do anything with parsed Data

                tempMetadata.setMediaID(Integer.valueOf(eElement.getElementsByTagName("MediaId").item(0).getTextContent()));
                tempMetadata.setFileName(eElement.getElementsByTagName("FileName").item(0).getTextContent());
                tempMetadata.setClassID(eElement.getElementsByTagName("ClassId").item(0).getTextContent());
                tempMetadata.setQuality(Integer.valueOf(eElement.getElementsByTagName("Quality").item(0).getTextContent()));
                tempMetadata.setVernacularName(eElement.getElementsByTagName("VernacularNames").item(0).getTextContent().toLowerCase());
            }

        }
        catch (Exception e)
        {
            xmlParserLog.println(e.getMessage());
        }

        return tempMetadata;
    }

}

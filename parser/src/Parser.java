import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
public class Parser
{
    Parser()
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            try{
                Document doc = builder.parse("simple.xml");
                NodeList foodList = doc.getElementsByTagName("food");
                for(int i =0 ;i<foodList.getLength();i++)
                {
                    Node p = foodList.item(i);
                    if(p.getNodeType() == Node.ELEMENT_NODE){
                        Element food =(Element) p;
                        String id = food.getAttribute("id");
                        NodeList infoList  = food.getChildNodes();
                        for(int j = 0;j<=infoList.getLength();j++)
                        {
                            Node n = infoList.item(j);
                                if (n.getNodeType() == Node.ELEMENT_NODE) {
                                    try {
                                        Element info = (Element) n;
                                        System.out.println("Food " + id + ": " + info.getTagName() + " = " + "= "
                                                + info.getTextContent());

                                    }catch(NullPointerException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                        }
                    }
                }
            }catch(SAXException e)
            {
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }catch(ParserConfigurationException e)
        {
            e.printStackTrace();
        }
    }
}

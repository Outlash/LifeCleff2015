package files;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxParser extends DefaultHandler {
    String XmlFileName;
    boolean tmpValue;
    String classId="";
    public SaxParser(String XmlFileName) {
        this.XmlFileName = XmlFileName;
        parseDocument();
    }
    private void parseDocument() {
        // parse
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(XmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
    @Override
    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("ClassId")) {
            tmpValue=true;
        }
    }
    @Override
    public void endElement(String s, String s1, String element) throws SAXException {


    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (tmpValue)
            classId = new String(ch, start, length);
        tmpValue=false;
    }

    public String get_class_id()
    {
        return classId;
    }

}
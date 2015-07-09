// Author: Jon Larsen
// Date: 05/19/15
// Homework: 6
// Objective: Reads an xml file with system properties and displays the data
//******************************************************************************

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class ReadXml
{
    /****************************parse****************************************/
    public static void parse()
    {
        Document doc = null;

        //open the file
        try
        {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = f.newDocumentBuilder();
            doc = db.parse(new File("systemproperties.xml"));
        } catch (Exception e){System.out.println("Could not open the file");}
        
        doc.getDocumentElement().normalize();
        Element root = doc.getDocumentElement();
        System.out.println("\nRoot element: " + root.getTagName());
    
        System.out.println("\nJava Info");
        printNodes("Java", "Home", doc);
        printNodes("Java", "Version", doc);
           
        System.out.println("\nOS Info");
        printNodes("OS", "Name", doc);
        printNodes("OS", "Version", doc);
        
        System.out.println("\nUser Info");
        printNodes("User", "Name", doc);
        printNodes("User", "Home", doc);
    }

    /**************************printNodes**************************************/
    protected static void printNodes(String tag, String subTag, Document doc)
    {
        NodeList tempList = doc.getElementsByTagName(tag);
        for (int i = 0; i < tempList.getLength(); i++)
        {
            Node jNode = tempList.item(i);
            if(jNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element firstE = (Element)jNode;
                NodeList firstEList = firstE.getElementsByTagName(subTag);
                Element firstNodeElement = (Element)firstEList.item(0);
                NodeList firstValue = firstNodeElement.getChildNodes();
                System.out.println(subTag + ": " + firstValue.item(0).getNodeValue());
            }
        }

    }//end printNodes

}//end class

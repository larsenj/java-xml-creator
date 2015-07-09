// Author: Jon Larsen
// Date: 05/19/15
// Homework: 6
// Objective: Creates an xml file containing various system properties
//******************************************************************************

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.dom.DOMSource; 
import javax.xml.transform.stream.StreamResult; 
import java.util.Properties;
import java.io.*;

public class CreateXml 
{
    /*****************************createProperties*****************************/
    //places various sytem properties in a Document object, then calls the 
    //other function to write to a file
    public void createProperties()
    {
        //get the system properties
        String jHome = System.getProperty("java.home");
        String jVersion = System.getProperty("java.version");
        String oName = System.getProperty("os.name");
        String oVersion = System.getProperty("os.version");
        String uHome = System.getProperty("user.home");
        String uName = System.getProperty("user.name");
        
        try
        { 
            //create the file        
            File outputFile = new File ("systemproperties.xml");
            if (outputFile.createNewFile()){
                System.out.println("File is created!");
            }else
            System.out.println("File already exists.");

            //create Document object
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            //write the strings to the document
            Element root = doc.createElement("SystemProperties");
            doc.appendChild(root);

            Element javaInfo = doc.createElement("Java");
            root.appendChild(javaInfo);
            Element javaHome = doc.createElement("Home");
            javaInfo.appendChild(javaHome);
            javaHome.appendChild(doc.createTextNode(jHome));
            Element javaVersion = doc.createElement("Version"); 
            javaInfo.appendChild(javaVersion);
            javaVersion.appendChild(doc.createTextNode(jVersion));

            Element osInfo = doc.createElement("OS");
            root.appendChild(osInfo);
            Element osName= doc.createElement("Name"); 
            osInfo.appendChild(osName);
            osName.appendChild(doc.createTextNode(oName));
            Element osVersion = doc.createElement("Version");
            osInfo.appendChild(osVersion);
            osVersion.appendChild(doc.createTextNode(oVersion));

            Element userInfo = doc.createElement("User");
            root.appendChild(userInfo);
            Element userName = doc.createElement("Name");
            userInfo.appendChild(userName);
            userName.appendChild(doc.createTextNode(uName));
            Element userHome = doc.createElement("Home");
            userInfo.appendChild(userHome);
            userHome.appendChild(doc.createTextNode(uHome)); 

            //write document to the file
            writeDocumentToFile(doc, outputFile);

        } catch (ParserConfigurationException e){e.printStackTrace();
        } catch (IOException e){e.printStackTrace();
        }

    }//end createProperties

    /*************************writeDocumentToFile****************************/
    //writes the Document object to a file
    private void writeDocumentToFile(Document document, File file) 
    {
        try
        {
            //Create the transformer
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
        
            //Set the document as a DOMSource object
            DOMSource source = new DOMSource(document);
      
            //Set name of destination file
            StreamResult result = new StreamResult(file);
        
            //Write to file
            transformer.transform(source, result);
        } catch (TransformerException e){e.printStackTrace();}
    }//end writeDocumentToFile

}//end CreateXml

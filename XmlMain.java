// Author: Jon Larsen
// Date: 05/19/15
// Homework: 6
// Objective: Creates or reads an xml document with various system properties
//              based on user input.
//******************************************************************************
import java.util.Scanner;

public class XmlMain
{
    /*******************************main************************************/
    public static void main (String[] args)
    {
        int selection = -1;
        Scanner keyboard = new Scanner(System.in);

        while (selection < 0)
        {
            System.out.println("Press 1 to create the xml file");
            System.out.println("Press 2 to read the xml file");
            System.out.println("Press any other number to quit");

            selection = keyboard.nextInt();

            switch(selection)
            {
                case 1:
                    CreateXml create = new CreateXml();
                    create.createProperties();
                    System.out.println("Done");
                    break;
                case 2:
                    ReadXml.parse();
                    break;
                default:
                    System.out.println("Goodbye");
                    break;
            }//end switch
        }//end while
    }//end main
}//end class

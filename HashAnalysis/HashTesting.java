//Imports for I/O  
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class HashTesting
{
   public static void main(String[] args)
   {
      Table test = new Table<Integer, String>(31);
      TableDoubleHash doubleTest = new TableDoubleHash<Integer, String>(31);
      String filename = "names.txt";
      
      //Variables to handle the file I/O
      Scanner inputStream;
      File f;
      
      //Temp variables to store value from the line
      String name, temp;
      int key;
      int[] collisions = new int[25];
      int[] doubleCollisions = new int[25];
      int[] chainCollisions = new int[25];
      
      //Temp variable for splitting the line from the file into individual components
      String[] line;
      
      try 
      {
         //Initialize input stream
         f = new File(filename);
         inputStream = new Scanner(f);
         
         for(int i = 0; i < 25; i++)
         {
            temp = inputStream.nextLine();
            line = temp.split(" ");
            name = line[0];
            key = Integer.parseInt(line[1]);
            
            collisions[i] = test.put(key, name);
            doubleCollisions[i] = doubleTest.put(key, name);
         }
      } catch (Exception e) {
         System.out.println("Error: " + e.getClass().getCanonicalName() + "\n" + e.getMessage());
      }
      
      System.out.println("Collisions per attempted placement in table :");
      System.out.println("Attempt\tLinear\tDouble\tChain");
      
      for(int i = 0; i < 25; i++)
      {
         System.out.println((i + 1) + "\t" + collisions[i] + "\t" + doubleCollisions[i]);
      }
     
   }
}
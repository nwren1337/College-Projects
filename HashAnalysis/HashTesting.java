/***********************************************************************
 ***********************************************************************
 **                     Hash Algorithm Testing                        **
 **                           Nate Wren                               **
 **                             CS 103                                **
 **                           12/13/2018                              **
 ***********************************************************************
 ***********************************************************************/


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
      //Number of records we are scanning from the file
      int numRecords = 200;
      
      //Size of the table
      int size = 241;
      
      //Tables to insert key-value pairs to
      Table test = new Table<Integer, String>(size);
      TableDoubleHash doubleTest = new TableDoubleHash<Integer, String>(size);
      TableChainHash chainTest = new TableChainHash<Integer, String>(size);
      
      //Input file
      String filename = "names.txt";
      
      //Variables to handle the file I/O
      Scanner inputStream;
      File f;
      
      //Temp variables to store value from the line
      String name;
      int key;
      
      //Arrays to hold number of collisions per insertion
      int[] collisions = new int[numRecords];
      int[] doubleCollisions = new int[numRecords];
      int[] chainCollisions = new int[numRecords];
      
      try 
      {
         //Initialize input stream
         f = new File(filename);
         inputStream = new Scanner(f);
         
         //Create iterator to track attempts
         int i = 0;
         
         //While the file still has lines of input
         while(inputStream.hasNext())
         {
            //Get the key-value pair from the file
            name = inputStream.next();
            key = inputStream.nextInt();
            
            //Insert into table, store result for number of collisions
            collisions[i] = test.put(key, name);
            doubleCollisions[i] = doubleTest.put(key, name);
            chainCollisions[i] = chainTest.put(key, name);
            
            //iterate attempts
            i++;
         }
      } catch (Exception e) {
         System.out.println("Error: " + e.getClass().getCanonicalName() + "\n" + e.getMessage());
      }
      
      //Output results to the user
      System.out.println("Collisions per attempted placement in table :");
      System.out.println("Attempt\tLinear\tDouble\tChain");
      
      for(int i = 0; i < numRecords; i++)
      {
         if(i < 99)
            System.out.println("\t" + (i + 1) + "\t\t\t" + collisions[i] + "\t\t\t" + doubleCollisions[i] + "\t\t\t" + chainCollisions[i]);
         else
            System.out.println("\t" + (i + 1) + "\t\t" + collisions[i] + "\t\t\t" + doubleCollisions[i] + "\t\t\t" + chainCollisions[i]);
      }
      
      System.out.println("Average number of collisions for Linear : " + avg(collisions));
      System.out.println("Average number of collisions for Double : " + avg(doubleCollisions));
      System.out.println("Average number of collisions for Chain : " + avg(chainCollisions));
     
   }
   
   
   
   
   public static double avg(int[] arr)
   {
      int sum = 0;
      
      for(int i = 0; i < arr.length; i++)
      {
         sum += arr[i];
      }
      
      return (double) sum / arr.length;
   }
}
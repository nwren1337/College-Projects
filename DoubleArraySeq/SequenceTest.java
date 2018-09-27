/**
 * Class to test out functionality of the DoubleArraySeq Abstract Data Type.
 * @author Nathan Wren
 * @author Caleb Secor
 * @version 0.1
 * 9/24/2018
 */
 
import java.util.*;

public class SequenceTest
{
   public static void main(String[] args)
   {
      int menuOption = 0;
      boolean cont = true;
      char currSeq = 'A';
      final int INIT_SIZE = 20;
      Scanner keyboard = new Scanner(System.in);
      
      DoubleArraySeq sequenceA = new DoubleArraySeq();
      DoubleArraySeq sequenceB = new DoubleArraySeq(INIT_SIZE);
      
      System.out.println("Hello! This program is intended to test the functionality of the DoubleArraySeq abstract data type");
      System.out.println("Two sequences were initialized, a default one and a one of size 20.");
      
      do {
         try {
            menuOption = mainMenu(currSeq, keyboard);
            
            switch(menuOption)
            {
               case 1: printBoth(sequenceA, sequenceB);
                       break;
               case 2: printCapacity(sequenceA, sequenceB);
                       break;
               case 3: testEquality(sequenceA, sequenceB);
                       break;
               case 4: currSeq = swapActive(currSeq);
                       break;
               case 5: addToFront(currSeq, sequenceA, sequenceB, keyboard);
                       break;
               case 6: addNumBefore(currSeq, sequenceA, sequenceB, keyboard);
                       break;
               case 7: //addNumAfter(currSeq, sequenceA, sequenceB, keyboard);
                       break;
               case 8: //addNumToEnd(currSeq, sequenceA, sequenceB, keyboard);
                       break;
               case 9: //addBtoA(sequenceA, sequenceB);
                       break;
               case 10: //deleteAtIndex(currSeq, sequenceA, sequenceB, keyboard);
                        break;
               case 11: //deleteFirst(currSeq, sequenceA, sequenceB, keyboard);
                        break;
               case 12: //displayAtIndex(currSeq, sequenceA, sequenceB, keyboard);
                        break;
               case 13: //displayLast(currSeq, sequenceA, sequenceB);
                        break;
               case 14: //trimSequence(currSeq, sequenceA, sequenceB);
                        break;
               case 15: //cloneSequence(currSeq, sequenceA, sequenceB);
                        break;
               case 16: //mergeSequence(sequenceA, sequenceB);
                        break;
               case 17: cont = false;
                        break;
               default:
                        System.out.println("Invalid Input!");
                        break;
            }
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
            keyboard.next();
        }
     } while(cont);  
           
    System.out.println("Have a nice day!");
   }
   
   public static void printBoth(DoubleArraySeq A, DoubleArraySeq B)
   {
      System.out.println("Sequence A : " + A + "\nSequence B : " + B);
   }
   
   public static void printCapacity(DoubleArraySeq A, DoubleArraySeq B)
   {
      System.out.println("Sequence A capacity : " + A.getCapacity() + "\nSequence B : " + B.getCapacity());
   }
   
   public static void testEquality(DoubleArraySeq A, DoubleArraySeq B)
   {
      if(A.equals(B))
         System.out.println("Sequence A is equal to Sequence B");
      else
         System.out.println("The sequences are not equal!");
   }
   
   public static char swapActive(char curr)
   {
      char ret;
      ret = (curr == 'A') ? 'B' : 'A';
      return ret;
   }
   
   public static void addNumBefore(char currSeq, DoubleArraySeq A, DoubleArraySeq B, Scanner keyboard) throws InputMismatchException
   {
      double numToAdd;
      int index;
      if(currSeq == 'A')
      {
         System.out.println("There are " + A.size() + " elements in sequence A, which number do you want to insert before : ");
         index = keyboard.nextInt();
         
         if(index <= A.size())
         {
            System.out.println("Inserting before the " + index + " element.");
            A.setCurrent(index - 1);
            
            System.out.print("What number do you want to insert : ");
            numToAdd = keyboard.nextDouble();
            
            A.addBefore(numToAdd);
         } else {
            System.out.println("Out of range of A!");
         }
      } else {
         System.out.println("There are " + B.size() + " elements in sequence B, which number do you want to insert before : ");
         index = keyboard.nextInt();
         
         if(index <= B.size())
         {
            System.out.println("Inserting before the " + index + " element.");
            B.setCurrent(index - 1);
            
            System.out.print("What number do you want to insert : ");
            numToAdd = keyboard.nextDouble();
            
            B.addBefore(numToAdd);
         } else {
            System.out.println("Out of range of B!");
         }
      }
   }
   
   public static void addToFront(char currSeq, DoubleArraySeq A, DoubleArraySeq B, Scanner keyboard) throws InputMismatchException
   {
      double numToAdd;
      System.out.print("Please enter the number you would like to add to the front of " + currSeq + " :");
      numToAdd = keyboard.nextDouble();
      
      if(currSeq == 'A')
         A.addFront(numToAdd);
      else
         B.addFront(numToAdd);
   }
      
   
   public static int mainMenu(char curr, Scanner keyboard) throws InputMismatchException
   {
      int input;
      
      System.out.println("The current sequence is " + curr);
      System.out.println("1.	Print the sequences of A and B");
      System.out.println("2.	Report the capacity of A and B");
      System.out.println("3.	Report if A and B are equal");
      System.out.println("4.	Change active sequence");
      System.out.println("5.	Add a number to the front of a sequence");
      System.out.println("6.	Add a number before a given number");
      System.out.println("7.	Add a number after a given number");
      System.out.println("8.	Add a number to the end of a sequence");
      System.out.println("9.	Add sequence B to end of A");
      System.out.println("10.	Delete a number at a certain index");
      System.out.println("11.	Delete the first number from the sequence");
      System.out.println("12.	Display a number at a certain index");
      System.out.println("13.	Display the last element in the sequence");
      System.out.println("14.	Trim extra memory from both A and B");
      System.out.println("15.	Create a clone sequence and show");
      System.out.println("16.	Create a new sequence using concatenate of B and A and show");
      System.out.println("17.	Quit");
      System.out.print("Please enter option : ");
      input = keyboard.nextInt();
      
      return input;
   }
      
}
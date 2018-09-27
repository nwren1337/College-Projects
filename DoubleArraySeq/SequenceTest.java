/**
 * Class to test out functionality of the DoubleArraySeq Abstract Data Type.
 * @author Nathan Wren
 * @author Caleb Secor
 * @version 0.1
 * 9/24/2018
 */
 
 //For exceptions and Scanner
import java.util.*;

public class SequenceTest
{

   /**
    *Main method - Entry point for JVM
    *@param args 
    *       command line arguments
    *@return null
    */
   public static void main(String[] args)
   {
      /* VARS */
      
      //Flow control variables
      int menuOption = 0;
      boolean cont = true;
      
      //Default current sequence to A
      char currSeq = 'A';
      
      //Set constant for initial size of B
      final int INIT_SIZE_B = 20;
      
      //Create scanner for input
      Scanner keyboard = new Scanner(System.in);
      
      //Initialize both sequences
      DoubleArraySeq sequenceA = new DoubleArraySeq();
      DoubleArraySeq sequenceB = new DoubleArraySeq(INIT_SIZE_B);
      
      //Welcome the user
      System.out.println("Hello! This program is intended to test the functionality of the DoubleArraySeq abstract data type");
      System.out.println("Two sequences were initialized, a default one and a one of size 20.");
      
      
      do {
         try {
            //Get menu option from function
            menuOption = mainMenu(currSeq, keyboard);
            
            //Evaluate users option
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
               case 7: addNumAfter(currSeq, sequenceA, sequenceB, keyboard);
                       break;
               case 8: addNumToEnd(currSeq, sequenceA, sequenceB, keyboard);
                       break;
               case 9: addBtoA(sequenceA, sequenceB);
                       break;
               case 10: deleteAtIndex(currSeq, sequenceA, sequenceB, keyboard);
                        break;
               case 11: deleteFirst(currSeq, sequenceA, sequenceB);
                        break;
               case 12: displayAtIndex(currSeq, sequenceA, sequenceB, keyboard);
                        break;
               case 13: displayLast(currSeq, sequenceA, sequenceB);
                        break;
               case 14: trimSequence(sequenceA, sequenceB);
                        break;
               case 15: cloneSequence(currSeq, sequenceA, sequenceB);
                        break;
               case 16: mergeSequence(sequenceA, sequenceB);
                        break;
               case 17: cont = false;
                        break;
               default:
                        System.out.println("Invalid Input!");
                        break;
            }
            
        } catch (InputMismatchException e) {
            System.out.println("Number expected but you entered a word!"); 
            keyboard.nextLine();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
            
        }
     } while(cont);  
    
    //Wish them well       
    System.out.println("Have a nice day!");
   }
   
   /**
    *Adds the sequence B on to the end of sequence A
    *@param A
    *       Sequence A
    *@param B
    *       Sequence B
    */
   public static void addBtoA(DoubleArraySeq A, DoubleArraySeq B)
   {
      A.addAll(B);
   }
   
   /**
    *Prints both sequences
    *@param A
    *       Sequence A
    *@param B
    *       Sequence B
    */
   public static void printBoth(DoubleArraySeq A, DoubleArraySeq B)
   {
      System.out.println("Sequence A : " + A + "\nSequence B : " + B);
   }
   
   /**
    *Prints the capacity of both sequences
    *@param A
    *       Sequence A
    *@param B
    *       Sequence B
    */
   public static void printCapacity(DoubleArraySeq A, DoubleArraySeq B)
   {
      System.out.println("Sequence A capacity : " + A.getCapacity() + "\nSequence B : " + B.getCapacity());
   }
   
   /**
    *Tests if both sequences are equal and outputs the results
    *@param A
    *       Sequence A
    *@param B
    *       Sequence B
    */
   public static void testEquality(DoubleArraySeq A, DoubleArraySeq B)
   {
      if(A.equals(B))
         System.out.println("Sequence A is equal to Sequence B");
      else
         System.out.println("The sequences are not equal!");
   }
   
   /**
    *Swap the current sequence between A and B
    *@param curr
    *       Current sequence
    *@return
    *       The opposite sequence
    */
   public static char swapActive(char curr)
   {
      char ret;
      ret = (curr == 'A') ? 'B' : 'A';
      return ret;
   }
   
   /**
    *Adds num before index chosen by user.
    *@param currSeq
    *       Current sequence
    *@param A
    *       Sequence A
    *@param B
    *       Sequence B
    *@param keyboard
    *       Scanner object for input
    *@throws InputMismatchException
    *        If user is asked for a number but gives a string
    */
   public static void addNumBefore(char currSeq, DoubleArraySeq A, DoubleArraySeq B, Scanner keyboard) throws InputMismatchException
   {
      double numToAdd;
      int index;
      if(currSeq == 'A')
      {
         if(A.size() > 0)
         {
            System.out.print("There are " + A.size() + " elements in sequence A, which number do you want to insert before : ");
            index = keyboard.nextInt();
            
            if(index <= A.size() && index > 0)
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
            System.out.println("A is empty, adding to front!");
            addToFront(currSeq, A, B, keyboard);
         }
            
      } else {
         if(B.size() > 0)
         {
            System.out.print("There are " + B.size() + " elements in sequence B, which number do you want to insert before : ");
            index = keyboard.nextInt();
            
            if(index <= B.size() && index > 0)
            {
               System.out.println("Inserting before the " + index + " element.");
               B.setCurrent(index - 1);
               
               System.out.print("What number do you want to insert : ");
               numToAdd = keyboard.nextDouble();
               
               B.addBefore(numToAdd);
            } else {
               System.out.println("Out of range of B!");
            }
        } else {
            System.out.println("B is empty! Adding to front");
            addToFront(currSeq, A, B, keyboard);
        }
      }
   }
   
   /**
    *Adds num after index chosen by user.
    *@param currSeq
    *       Current sequence
    *@param A
    *       Sequence A
    *@param B
    *       Sequence B
    *@param keyboard
    *       Scanner object for input
    *@throws InputMismatchException
    *        If user is asked for a number but gives a string
    */
   public static void addNumAfter(char currSeq, DoubleArraySeq A, DoubleArraySeq B, Scanner keyboard) throws InputMismatchException
   {
      double numToAdd;
      int index;
      if(currSeq == 'A')
      {
         if(A.size() > 0)
         {
            System.out.print("There are " + A.size() + " elements in sequence A, which number do you want to insert after : ");
            index = keyboard.nextInt();
            
            if(index <= A.size() && index > 0)
            {
               System.out.println("Inserting after the " + index + " element.");
               A.setCurrent(index - 1);
               
               System.out.print("What number do you want to insert : ");
               numToAdd = keyboard.nextDouble();
               
               A.addAfter(numToAdd);
            } else {
               System.out.println("Out of range of A!");
            }
         } else {
            System.out.println("A is empty! Adding to front.");
            addToFront(currSeq, A, B, keyboard);
         }
      } else {
         if(B.size() > 0)
         {
            System.out.print("There are " + B.size() + " elements in sequence B, which number do you want to insert after : ");
            index = keyboard.nextInt();
            
            if(index <= B.size() && index > 0)
            {
               System.out.println("Inserting after the " + index + " element.");
               B.setCurrent(index - 1);
               
               System.out.print("What number do you want to insert : ");
               numToAdd = keyboard.nextDouble();
               
               B.addAfter(numToAdd);
            } else {
               System.out.println("Out of range of B!");
            }
         } else {
            System.out.println("B is empty! Adding to front!");
            addToFront(currSeq, A, B, keyboard);
         }
      }
   }
   
   /**
    *Adds num to the front of the sequence
    *@param currSeq
    *       Current sequence
    *@param A
    *       Sequence A
    *@param B
    *       Sequence B
    *@param keyboard
    *       Scanner object for input
    *@throws InputMismatchException
    *        If user is asked for a number but gives a string
    */
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
   
   /**
    *Adds num to the end of the sequence
    *@param currSeq
    *       Current sequence
    *@param A
    *       Sequence A
    *@param B
    *       Sequence B
    *@param keyboard
    *       Scanner object for input
    *@throws InputMismatchException
    *        If user is asked for a number but gives a string
    */
   public static void addNumToEnd(char currSeq, DoubleArraySeq A, DoubleArraySeq B, Scanner keyboard) throws InputMismatchException
   {
      double numToAdd;
      System.out.print("Please enter the number you would like to add to the end of " + currSeq + " :");
      numToAdd = keyboard.nextDouble();
      
      if(currSeq == 'A')
         A.addEnd(numToAdd);
      else
         B.addEnd(numToAdd);
   }
   
   /**
    *Deletes first element in current sequence
    *@param currSeq
    *       Current sequence
    *@param A
    *       Sequence A
    *@param B
    *       Sequence B
    */
   public static void deleteFirst(char currSeq, DoubleArraySeq A, DoubleArraySeq B) 
   {      
      if(currSeq == 'A')
         A.removeFront();
      else
         B.removeFront();
   }
   
   /**
    *Removes number at index chosen by user.
    *@param currSeq
    *       Current sequence
    *@param A
    *       Sequence A
    *@param B
    *       Sequence B
    *@param keyboard
    *       Scanner object for input
    *@throws InputMismatchException
    *        If user is asked for a number but gives a string
    */
   public static void deleteAtIndex(char currSeq, DoubleArraySeq A, DoubleArraySeq B, Scanner keyboard) throws InputMismatchException
   {
      double numToDel;
      int index;
      if(currSeq == 'A')
      {
         if(A.size() > 0)
         {
            System.out.print("There are " + A.size() + " elements in sequence A, which number do you want to delete : ");
            index = keyboard.nextInt();
            
            if(index <= A.size() && index > 0)
            {
               System.out.println("Deleting the " + index + " element.");
               A.setCurrent(index - 1);            
               A.removeCurrent();
            } else {
               System.out.println("Out of range of A!");
            }
         } else {
            System.out.println("A is empty!");
         }
      } else {
         if(B.size() > 0)
         {
            System.out.print("There are " + B.size() + " elements in sequence B, which number do you want to delete : ");
            index = keyboard.nextInt();
            
            if(index <= B.size() && index > 0)
            {
               System.out.println("Deleting the " + index + " element.");
               B.setCurrent(index - 1);
               B.removeCurrent();
            } else {
               System.out.println("Out of range of B!");
            }
         } else {
            System.out.println("B is empty!");
         }
      }
   }
   
   /**
    *Displays num before index chosen by user.
    *@param currSeq
    *       Current sequence
    *@param A
    *       Sequence A
    *@param B
    *       Sequence B
    *@param keyboard
    *       Scanner object for input
    *@throws InputMismatchException
    *        If user is asked for a number but gives a string
    */
   public static void displayAtIndex(char currSeq, DoubleArraySeq A, DoubleArraySeq B, Scanner keyboard) throws InputMismatchException
   {
      double numToDel;
      int index;
      if(currSeq == 'A')
      {
         if(A.size() > 0)
         {
            System.out.print("There are " + A.size() + " elements in sequence A, which number do you want to display : ");
            index = keyboard.nextInt();
            
            if(index <= A.size() && index > 0)
            {
               A.setCurrent(index - 1);            
               System.out.println("The element is " + A.getCurrent());
            } else {
               System.out.println("Out of range of A!");
            }
         } else {
            System.out.println("A is empty!");
         }
      } else {
         if(B.size() > 0)
         {
            System.out.print("There are " + B.size() + " elements in sequence B, which number do you want to display : ");
            index = keyboard.nextInt();
            
            if(index <= B.size() && index > 0)
            {
               B.setCurrent(index - 1);
               System.out.println("The element is " + B.getCurrent());
            } else {
               System.out.println("Out of range of B!");
            }
         } else {
            System.out.println("B is empty");
         }
      }
   }
   
   /**
    *Display last number in sequence
    *@param currSeq
    *       Current sequence
    *@param A
    *       Sequence A
    *@param B
    *       Sequence B
    */
   public static void displayLast(char curr, DoubleArraySeq A, DoubleArraySeq B)
   {
    
       if(curr == 'A')
       {
          A.setCurrentLast();
          System.out.println(A.getCurrent());
       }
       else
       {
          B.setCurrentLast();
          System.out.println(B.getCurrent());
       }
   }
   
   /**
    *Trims both sequences to number of items
    *@param currSeq
    *       Current sequence
    *@param A
    *       Sequence A
    *@param B
    *       Sequence B
    */
   public static void trimSequence(DoubleArraySeq A, DoubleArraySeq B)
   {
      A.trimToSize();
      B.trimToSize();
   }

   /**
    *Clones current sequence then outputs it
    *@param currSeq
    *       Current sequence
    *@param A
    *       Sequence A
    *@param B
    *       Sequence B
    */
   public static void cloneSequence(char curr, DoubleArraySeq A, DoubleArraySeq B)
   {
      DoubleArraySeq C;
      if (curr == 'A')
      {     
         C = A.clone();
         System.out.println("The cloned sequence is " + C);
      }
      else
      {
         C = B.clone();
         System.out.println("The cloned sequence is " + C);
      }
   }
   
   /**
    *Concatenates two sequences then outputs the resulting sequence
    *@param currSeq
    *       Current sequence
    *@param A
    *       Sequence A
    *@param B
    *       Sequence B
    */
   public static void mergeSequence(DoubleArraySeq A, DoubleArraySeq B)
   {
      DoubleArraySeq C = DoubleArraySeq.concatenation(A, B);
      System.out.println("The new sequence is " + C);
   }
   
   /**
    *Main menu in program, presents user with multiple options for using test class
    *@param currSeq
    *       Current sequence
    *@param keyboard
    *       Scanner object for input
    *@throws InputMismatchException
    *        If user is asked for a number but gives a string
    */
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
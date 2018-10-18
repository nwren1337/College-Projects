/**
 * Class to test the UnboundedInt class
 * @author Nathan Wren
 * @author Caleb Secor
 * @author Adesh Rai
 * @version 0.1
 * 10/17/2018
*/
   //For Scanner
   import java.util.*;
public class LargeNumberTester
{
   /**
    *@param args
    *       command line arguments
    *@return null
    */
   public static void main(String[] args)
   {  
      //Menu control 
      boolean exit = false;
      int menuOption = 0;
      
      //Scanner Creation
      Scanner input = new Scanner(System.in);
      
      //Get the inital numbers from the user and greet the user
      System.out.println("Hello, please enter two numbers so we can test UnboundedInt");
      String a = input.next();
      String b = input.next();
      UnboundedInt numberA = new UnboundedInt(a);
      UnboundedInt numberB = new UnboundedInt(b);
      System.out.println("Thank you");
      while(exit == false)
      {
           //The menu the user will pick functions from
           System.out.println("");
           System.out.println("type a number to select an option");
           System.out.println("1: Display both numbers");
           System.out.println("2: Input two new numbers");
           System.out.println("3: Check if numbers are equal");
           System.out.println("4: Report the sum of the two numbers");
           System.out.println("5: Report the multiplication of the two numbers");
           System.out.println("6: Clone and print the two numbers");
           System.out.println("7: End the program");
           System.out.println("");

           //chooses a function based on user input
           menuOption = input.nextInt();
          
           switch(menuOption)
           {
              case 1: printNumbers(numberA, numberB);
                      break;
              case 2: inputNumbers(a, b, input);
                      break;
              case 3: checkEquality(numberA, numberB);
                      break;
              case 4: addNumbers(numberA, numberB); 
                      break;
              /**case 5: multiplyNumbers(numberA, numberB);
                      break;
              case 6: cloneNumber(numberA, numberB);
                      break;
              */
              case 7: exit = true;
                      break;
              default: System.out.println("Invalid Input");
                       break;   
            }
      
      }
      //Say goodbye to the user
      System.out.println("Have a good day");
   }
   
   /**
    *Prints the numbers input by the user
    *@param a
    *       Number A
    *@param b
    *       Number B
    */
    
   public static void printNumbers(UnboundedInt a, UnboundedInt b)
   {
      System.out.println("The first number is, " + a.toString());
      System.out.println("The second number is, " + b.toString());
   }
   
   /**
   *Allows the users to input two new numbers
   *@param a
   *       A string of numbers
   *@param b
   *       A string of numbers
   */
   public static void inputNumbers(String a, String b, Scanner input)
   {
      System.out.println("Please enter two numbers");
      a = input.next();
      b = input.next();
      UnboundedInt numberA = new UnboundedInt(a);
      UnboundedInt numberB = new UnboundedInt(b);
   }
   /**
    *Checks the equality of the inputed numbers
    *@param a
    *       Number A
    *@param b
    *       Number B
   */

   public static void checkEquality(UnboundedInt a, UnboundedInt b)
   {
      if(a.equals(b))
      {
         System.out.println("The numbers are the same");
      }
      else
      {
         System.out.println("The numbers are not the same");
      }
   }
   
   /**
    *adds the inputed numbers together and prints the result
    *@param a
    *       Number A
    *@param b
    *       Number B
   */

   public static void addNumbers(UnboundedInt a, UnboundedInt b)
   {
      System.out.println(a.add(b));
   }
   
  /**
    *Multiplys the two numbers and prints the result
    *@param a
    *       Number A
    *@param b
    *       Number B
   */

  /**public static void multiplyNumbers(UnboundedInt a, UnboundedInt b)
   {
      System.out.println(a.multiply(b));
   }
  */
   
   /**
    *Clones both numbers and prints them to the screen
    *@param a
    *       Number A
    *@param b
    *       Number B
   */


  
   public static void cloneNumber(UnboundedInt a, UnboundedInt b)
   {
      UnboundedInt c = a.clone();
      System.out.println("The first cloned number is " + c + ".");
      c = b.clone();
      System.out.println("The second cloned number is " + c + ".");
   }
   
}

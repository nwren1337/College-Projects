/**
 * Class to test out functionality of the DoubleArraySeq Abstract Data Type.
 * @author Nathan Wren
 * @author Caleb Secor
 * @version 0.1
 * 9/24/2018
 */
 

public class SequenceTest
{
   public static void main(String[] args)
   {
      System.out.println("Hello.");
      
      DoubleArraySeq test = new DoubleArraySeq();
      test.addFront(1);
      test.addFront(3);
      System.out.println(test.getCurrent());
      test.advance();
      System.out.println(test.getCurrent());
      test.advance();
      test.addBefore(2);
      System.out.println(test.getCurrent());
      test.advance();
      System.out.println(test.getCurrent());;
   }
}
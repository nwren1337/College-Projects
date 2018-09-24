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
/*      
      DoubleArraySeq test = new DoubleArraySeq();
      test.addFront(1);
      System.out.println(test.getCurrent());
      test.addFront(3);
      System.out.println(test.getCurrent());
      test.advance();
      System.out.println(test.getCurrent());
      test.advance();
      test.addBefore(2);
      System.out.println(test.getCurrent());
      test.advance();
      System.out.println(test.getCurrent());
      test.addEnd(4);
      System.out.println(test.getCurrent());
      
      DoubleArraySeq test2 = new DoubleArraySeq();
      test2.addFront(1);
      test2.addFront(3);
      test2.advance();
      test2.advance();
      test2.addBefore(2);
      test2.addEnd(4);
      
      System.out.println(test.equals(test2));*/
      
      DoubleArraySeq test3 = new DoubleArraySeq();
      
      for(int i = 0; i < 15; i++)
      {
         test3.addFront(i);
         System.out.println("sup");
      }
      
      System.out.println("Size of data : " + test3.getCapacity() + " manyItems : " + test3.size());
   }
}
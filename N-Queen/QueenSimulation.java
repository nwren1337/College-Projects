public class QueenSimulation
{
   public static void main(String[] args)
   {
      Queen test1 = new Queen(4);
      
      Queen test2 = new Queen(0, 2, 4);
      
      System.out.println("Queen 1 : " + test1);
      System.out.println("Queen 2 : " + test2);
      System.out.println("Conflicts : " + test1.conflicts(test2));
      
      if(!test1.down())
         System.out.println("Could not move Queen 1 down");
      
      System.out.println("Queen 1 : " + test1);
      System.out.println("Queen 2 : " + test2);
      
      if(test2.right())
      {
         System.out.println("Moved Queen 2 to the right");
      }
      
      System.out.println("Queen 1 : " + test1);
      System.out.println("Queen 2 : " + test2);
      
      if(test1.up())
      {
         System.out.println("Moved Queen 1 up");
      }
      
      System.out.println("Queen 1 : " + test1);
      System.out.println("Queen 2 : " + test2);
      
      if(test2.left())
      {
         System.out.println("Moved Queen 2 left");
      }
      
      System.out.println("Queen 1 : " + test1);
      System.out.println("Queen 2 : " + test2);
      
      if(test1.conflicts(test2))
      {
         System.out.println("Queen 1 conflicts with Queen 2");
      } else {
         System.out.println("Queen 1 does not conflict with Queen 2");
      }
      
      Queen test3 = new Queen(0,3,4);
      
      Queen test4 = new Queen(3,0,4);
      
      System.out.println("Queen 3 : " + test3);
      System.out.println("Queen 4 : " + test4);
      
      if(test3.conflicts(test4))
      {
         System.out.println("Queen 3 conflicts with Queen 4");
      }
         
   }
}
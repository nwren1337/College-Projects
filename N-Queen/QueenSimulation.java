public class QueenSimulation
{
   public static void main(String[] args)
   {
      Queen test1 = new Queen(4);
      
      Queen test2 = new Queen(0, 2, 4);
      
      System.out.println("Queen 1 : " + test1);
      System.out.println("Queen 2 : " + test2);
      System.out.println("Conflicts : " + test1.conflicts(test2));
   }
}
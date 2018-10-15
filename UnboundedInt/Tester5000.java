public class Tester5000
{
   public static void main(String[] args)
   {
      UnboundedInt test = new UnboundedInt("12345");
      
      System.out.println(test);
      
      UnboundedInt test2 = new UnboundedInt("1000");
      
      System.out.println(test2);
      
      UnboundedInt test3 = new UnboundedInt("1000");
      
      System.out.println(test.equals(test2));
      System.out.println(test2.equals(test3));
      
      UnboundedInt test4 = new UnboundedInt("100100100100");
      UnboundedInt test5 = new UnboundedInt("100100100");
      
      System.out.println(test4.equals(test5));
   }
}
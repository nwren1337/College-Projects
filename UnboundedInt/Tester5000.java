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
      
      System.out.println(1234 * 123456);
      
      UnboundedInt test6 = new UnboundedInt("123456");
      UnboundedInt test7 = new UnboundedInt("1234");
      
      System.out.println(test7.multiply(test6));
      
      UnboundedInt test8 = new UnboundedInt("12345");
      UnboundedInt test9 = new UnboundedInt("12345");
      
      System.out.println(test8.add(test9));
      
      System.out.println(12345 + 12345);
   }
}
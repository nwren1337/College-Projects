public class GolferScoresTree
{
   /* driver file */
   public static void main(String[] args)
   {
      Golfer test1 = new Golfer("Aaron");
      Golfer test2 = new Golfer("Adam");
      Golfer test3 = new Golfer("Bill");
      Golfer test4 = new Golfer("Sam");
      Golfer test5 = new Golfer("Sam");
      
      if(test1.compareTo(test2) < 0)
      {
         System.out.println(test1.getName() + " is less than " + test2.getName());
      }
      
      if(test3.compareTo(test2) > 0)
      {
         System.out.println(test3.getName() + " is greater than " + test2.getName());
      }
      
      if(test4.compareTo(test5) == 0)
      {
         System.out.println(test4.getName() + " is equal to " + test5.getName()); 
      }
      
      //Golfer test6 = new Golfer("Tom", 3, -1, 44.0); //Throws exception as expected
      //Golfer test6 = new Golfer("Tom", 3, 55, 44.0); //Throws exception as expected
      Golfer test6 = new Golfer("Tom", 3, 5, 44.0); //Does not throw exception 
      
      System.out.println(test6.getName() + " played " + test6.getNumRounds() + " rounds with a handicap of " + test6.getHandicap() + " and average score of " + test6.getAverageScore());
      
      System.out.println(test6);
      
      test1.addScore(10);
      test1.addScore(15);
      test1.addScore(20);
      test1.addScore(18);
      
      System.out.println(test1.getName() + "'s average score is " + test1.getAverageScore());
   }
}
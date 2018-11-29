import java.util.Scanner;

public class GolferScoresTree
{
   /* driver file */
   public static void main(String[] args)
   {
      int menuOption = 0;
      final int NAME_ORDERED = 1, TREE_ORDERED = 2, SINGLE_GOLFER = 3;
      boolean loopAgain = true;
      String golferDatabase = "golferinfo.txt";
      TreeBag<Golfer> golfers = readFile(golferDatabase);
      
      System.out.println("Welcome to the Golfer Scores program!");
      System.out.println("This program will manage a databse of golfer scores.");
      
      do 
      {
         menuOption = menu();
         
         switch(menuOption)
         {
            case 1:
               display(NAME_ORDERED);
               break;
            case 2:
               display(TREE_ORDERED);
               break;
            case 3:
               display(SINGLE_GOLFER);
               break;
            case 4:
               update(golfers);
               break;
            case 5:
               remove(golfers);
               break;
            case 6:
               add(golfers);
               break;
            case 7:
               saveAndExit(golfers, golferDatabase);
               loopAgain = false;
               break;
            default :
               System.out.println("Unexpected input!");
               break;
         }
       } while(loopAgain);
       
       System.out.println("Have a great day!");
   }
   
   public static void display(int opt)
   {
      //to be implemented
   }
   
   public static void update(TreeBag<Golfer> golfers)
   {
      //to be implemented
   }
   
   public static void remove(TreeBag<Golfer> golfers)
   {
      //to be implemented
   }
   
   public static void add(TreeBag<Golfer> golfers)
   {
      //to be implemented
   }
   
   public static void saveAndExit(TreeBag<Golfer> golfers, String filename)
   {
      //to be implemented
   }
   
   public static TreeBag<Golfer> readFile(String filename)
   {
      //to be implemented
      return null;
   }
   
   public static int menu()
   {
      //to be implemented
      return 7;
   }
}
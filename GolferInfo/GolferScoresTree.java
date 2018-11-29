import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;

public class GolferScoresTree
{
   final static int NAME_ORDERED = 1, TREE_ORDERED = 2, SINGLE_GOLFER = 3;
   /* driver file */
   public static void main(String[] args)
   {
      int menuOption = 0;
      
      boolean loopAgain = true;
      String databaseFile = "golferinfo.txt";
      TreeBag<Golfer> golferDatabase = readFile(databaseFile);
      
      System.out.println("Welcome to the Golfer Scores program!");
      System.out.println("This program will manage a databse of golfer scores.");
      
      do 
      {
         menuOption = menu();
         
         switch(menuOption)
         {
            case 1:
               display(golferDatabase, NAME_ORDERED);
               break;
            case 2:
               display(golferDatabase, TREE_ORDERED);
               break;
            case 3:
               display(golferDatabase, SINGLE_GOLFER);
               break;
            case 4:
               update(golferDatabase);
               break;
            case 5:
               remove(golferDatabase);
               break;
            case 6:
               add(golferDatabase);
               break;
            case 7:
               saveAndExit(golferDatabase, databaseFile);
               loopAgain = false;
               break;
            default :
               System.out.println("Unexpected input!");
               break;
         }
       } while(loopAgain);
       
       System.out.println("Have a great day!");
   }
   
   public static void display(TreeBag<Golfer> golfers, int opt)
   {
      if(golfers != null)
      {
         switch(opt)
         {
            case NAME_ORDERED :
               golfers.display();
               break;
            case TREE_ORDERED :
               golfers.displayAsTree();
               break;
            case SINGLE_GOLFER :
               //do something
               break;
            default :
               System.out.println("Invalid option!");
               break;
          }
      }
      else
      {
         System.out.println("The database is empty!");
      }
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
      TreeBag<Golfer> database = null;
      String temp, lastName;
      String[] line;
      int numRounds, handicap, index = 0;
      double average;
      
      try 
      {
         File f = new File(filename);
         
         Scanner inputStream = new Scanner(f);
         
         database = new TreeBag<Golfer>();
         
         while(inputStream.hasNextLine())
         {
            temp = inputStream.nextLine();
            line = temp.split(" ");
            lastName = line[0];
            numRounds = Integer.parseInt(line[1]);
            handicap = Integer.parseInt(line[2]);
            average = Double.parseDouble(line[3]);
            
            database.add(new Golfer(lastName, numRounds, handicap, average));
         }        
      } 
      catch (java.io.FileNotFoundException e)
      {
         System.out.println("ERROR: Database file not found!");
      }
      catch (java.lang.NumberFormatException e)
      {
         System.out.println("ERROR: unexpected data in file!");
      }
      
      return database;
   }
   
   public static int menu()
   {
      Scanner keyboard = new Scanner(System.in);
      int input = 0;
      boolean invalidInput = false;
      
      do
      {
         try 
         {
            System.out.println("Menu options : ");
            System.out.println("\t1. Display all golfers sorted by last name");
            System.out.println("\t2. Display all golfers in a tree format");
            System.out.println("\t3. Display one golfer");
            System.out.println("\t4. Update a golfers average score");
            System.out.println("\t5. Remove a golfer from the database");
            System.out.println("\t6. Add a golfer to the database");
            System.out.println("\t7. Save and exit");
            System.out.print("Your choice : ");
            input = keyboard.nextInt();
            invalidInput = false;
         } 
         catch(java.util.InputMismatchException e)
         {
            System.out.println("ERROR: Invalid input!");
            keyboard.next();
            invalidInput = true;
         }
        } while(invalidInput);
         
      return input;
   }
}
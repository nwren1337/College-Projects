import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class GolferScoresTree
{
   final static int NAME_ORDERED = 1, TREE_ORDERED = 2, SINGLE_GOLFER = 3;

   public static void main(String[] args)
   {
      int menuOption = 0;
      
      boolean loopAgain = true;
      String databaseFile = "testinfo.txt";
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
               save(golferDatabase, databaseFile);
               loopAgain = false;
               break;
            default :
               System.out.println("ERROR: Unexpected input!");
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
               printSingle(golfers);
               break;
            default :
               System.out.println("ERROR: Invalid option!");
               break;
          }
      }
      else
      {
         System.out.println("The database is empty!");
      }
   }
   
   public static void printSingle(TreeBag<Golfer> golfers)
   {
      Scanner keyboard = new Scanner(System.in);
      String name;
      
      System.out.print("Please enter the name of the golfer you whose stats you want : ");
      name = keyboard.nextLine();
      
      Golfer test = new Golfer(name);
      Golfer toPrint = golfers.retrieve(test);
      
      if(toPrint != null)
      {
         System.out.println("Number of rounds played : " + toPrint.getNumRounds());
         System.out.println("Handicap : " + toPrint.getHandicap());
         System.out.println("Average Score : " + toPrint.getAverageScore());
      }
      else
      {
         System.out.println(name + " is not a golfer in the database!");
      }
   }
   
   public static void update(TreeBag<Golfer> golfers)
   {
      Scanner keyboard = new Scanner(System.in);
      String temp, name;
      double score;
      boolean invalidInput = false;
      
      System.out.print("Please enter the name of the golfer you wish to update : ");
      name = keyboard.nextLine();
      
      Golfer test = new Golfer(name);
      Golfer toUpdate = golfers.retrieve(test);
      
      if(toUpdate != null)
      {
         do
         {
            try 
            {
               System.out.print("Enter the score of " + name + "'s latest round : ");
               temp = keyboard.nextLine();
               score = Double.parseDouble(temp);
               toUpdate.addScore(score);
               invalidInput = false;
            } 
            catch (java.lang.NumberFormatException e)
            {
               System.out.println("ERROR: number expected!");
               invalidInput = true;
            }
           } while(invalidInput);
      }
      else
      {
         System.out.println(name + " is not a golfer in the database!");
      }     
      
   }
   
   public static void remove(TreeBag<Golfer> golfers)
   {
      Scanner keyboard = new Scanner(System.in);
      String name;
      
      System.out.print("Please enter the name of the golfer you wish to remove : ");
      name = keyboard.nextLine();
      
      Golfer toRemove = new Golfer(name);
      
      if(golfers.remove(toRemove))
      {
         System.out.println(name + " was removed!");
      }
      else
      {
         System.out.println(name + " is not a golfer in the database!");
      }
   }
   
   public static void add(TreeBag<Golfer> golfers)
   {
      Scanner keyboard = new Scanner(System.in);
      String input, name;
      int handicap, rounds;
      double score;
      boolean invalidInput = false;
      
      do
      {
         try 
         {
            System.out.print("Enter the golfer's last name : ");
            name = keyboard.nextLine();
            
            System.out.print("Enter the number of rounds they have played : ");
            input = keyboard.nextLine();
            rounds = Integer.parseInt(input);
            
            System.out.print("Enter their handicap : ");
            input = keyboard.nextLine();
            handicap = Integer.parseInt(input);
            
            System.out.print("Enter their average score : ");
            input = keyboard.nextLine();
            score = Double.parseDouble(input);
            
            golfers.add(new Golfer(name, rounds, handicap, score));
            invalidInput = false;
         } 
         catch (java.lang.NumberFormatException e)
         {
            System.out.println("ERROR: number expected!");
            invalidInput = true;
         }
        } while(invalidInput);
  
   }
   
   public static void save(TreeBag<Golfer> database, String filename)
   {
      File f;
      FileOutputStream outStream;
      PrintWriter output;
      String line;
      ArrayList<Golfer> golfers = database.getArray();
      int size = golfers.size();
      
      
      try 
      {
         f = new File(filename);
         outStream = new FileOutputStream(f);
         output = new PrintWriter(outStream);
         
         for(int i = 0; i < size; i++)
         {
            line = golfers.get(i).toString();
            output.println(line);
         }
         
         output.close();
       } 
       catch(java.io.FileNotFoundException e)
       {
         System.out.println("ERROR: Database could not be updated!");
       }    
      
   }
   
   public static TreeBag<Golfer> readFile(String filename)
   {
      TreeBag<Golfer> database = null;
      Scanner inputStream;
      File f;
      String temp, lastName;
      String[] line;
      int numRounds, handicap, index = 0;
      double average;
      
      try 
      {
         f = new File(filename);
         
         inputStream = new Scanner(f);
         
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
         
         inputStream.close();        
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
            System.out.println("\t3. Print a golfers stats");
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
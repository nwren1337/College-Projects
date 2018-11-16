import java.util.Scanner;

public class QueenSimulation
{
   public static void main(String[] args)
   {
      int N = 0, numSolutions = 0;
      boolean tryAgain = false, giveOutput = false;
      Scanner keyboard = new Scanner(System.in);
      String input;
      
      do{
      
         try {
         
            System.out.println("This program will place N queens on an N x N chess board in such a way that no queen threatens another.");
            System.out.print("Please enter the size of the chess board : ");
            N = keyboard.nextInt();
            tryAgain = false;
            ChessBoard board = new ChessBoard(N);
            
            System.out.print("Would you like to output the solutions as the program runs? (Y/N) ");
            input = keyboard.next();
            
            if(input.equals("Y") || input.equals("y"))
               giveOutput = true;
         
            numSolutions = findSolutions(board, giveOutput);
         } 
         catch(java.util.InputMismatchException e)
         {
            System.out.println("Invalid input!");
            keyboard.next();
            tryAgain = true;
            
         }
         catch(Exception e)
         {
            System.out.println(e.getClass().getCanonicalName());
            numSolutions = -1;
         }
      } while(tryAgain);
         
      
      if(numSolutions > 0)
      {
         System.out.println("There are " + numSolutions + " solutions to the " + N + " x " + N + " chess board.");
      } 
      else if(numSolutions == 0)
      {
         System.out.println("No solution exists for an " + N + " x " + N + " Chess board.");
      }
      else
      {
         System.out.println("Unexpected error occured while filling chess board");
      }
     
      System.out.println("Thank you, have a nice day!");    
   }
   
   public static int findSolutions(ChessBoard b, boolean giveOutput)
   {
      int numSolutions = 0, size = b.getSize(), currRow = 1;
      LinkedStack<Queen> solution;
      Queen origin = new Queen(size);
      Queen nextQueen = new Queen(size), lastQueen = new Queen(size);
      boolean added = b.addQueen(origin);
      
      while(b.queensOnBoard() > 0)
      {
         if(added)
         {
            if(b.queensOnBoard() < size)
            {
               lastQueen = b.peekQueen();
               nextQueen = new Queen(0, lastQueen.getRow() + 1, size);
               added = b.addQueen(nextQueen);
            }
         }
         else
         {
            nextQueen = b.popQueen();
         }

         
         while(!added && nextQueen.canMoveRight())
         {
            nextQueen.right();
            added = b.addQueen(nextQueen);
         }
         
         if(b.isComplete())
         {
            solution = b.getSolution();
            numSolutions++;
            if(giveOutput)
               printSolutions(solution, numSolutions);
            added = false;

          }
        }
        
        return numSolutions;
     } 
     
     public static void printSolutions(LinkedStack<Queen> solution, int numSolution)
     {
         System.out.print("Solution #" + numSolution + " : ");
         
         for(int i = solution.size() - 1 ; i >= 0 ; i--)
            System.out.print(solution.itemAt(i));
            
         System.out.println();
     }        
}
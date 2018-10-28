import java.util.Scanner;

public class QueenSimulation
{
   public static void main(String[] args)
   {
      int N = 0, numSolutions = 0;
      Scanner keyboard = new Scanner(System.in);
      
      System.out.println("This program will place N queens on an N x N chess board in such a way that no queen threatens another.");
      System.out.print("Please enter the size of the chess board : ");
      N = keyboard.nextInt();
      
      ChessBoard board = new ChessBoard(N);
      
      try {
      
         numSolutions = findSolutions(board);
      } 
      catch(Exception e)
      {
         System.out.println(e.getClass().getCanonicalName());
         numSolutions = -1;
      }
         
      
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
   
   public static int findSolutions(ChessBoard b)
   {
      int solutions = 0, size = b.getSize(), currRow = 1;
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
            //printSolution(solution);
            solutions++;
            added = false;

          }
        }
        
        return solutions;
     }                
}
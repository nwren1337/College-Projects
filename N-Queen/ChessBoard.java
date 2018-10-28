public class ChessBoard 
{
   private LinkedStack<Queen> queens;
   private int numQueens;
   private int size;
   
   private ChessBoard()
   {
      /* do nothing */
   }
   
   public ChessBoard(int s)
   {
      queens = new LinkedStack();
      numQueens = 0;
      size = s;
   }
   
   public boolean addQueen(Queen q)
   {
      /* attempts to add a queen to the board */
      queens.push(q);
      boolean success = validate();
      if(success)
      {
         numQueens++;
      }
      else
      {
         queens.pop();
      }
      
      return success;
   }
   
   public Queen popQueen()
   {
      numQueens--;
      return queens.pop();
   }
   
   public Queen peekQueen()
   {
      return queens.peek();
   }
   
   public LinkedStack<Queen> getSolution()
   {
      if(isComplete())
            return queens;
      else
         throw new IllegalStateException("The board is not complete!");
   }
   
   public int getSize()
   {
      return size;
   }
   
   public int queensOnBoard()
   {
      return numQueens;
   }
   
   public boolean isValid()
   {
      return validate();
   }
   
   public boolean isComplete()
   {
      return !(numQueens < size); 
   }
   
   private boolean validate()
   {  
      int conflicts = 0;
      
      Queen last = queens.peek();
      
      for(int j = numQueens; j > 0; j--)
      {
         if(last.conflicts(queens.itemAt(j)))
            return false;
      }
      
      return true;
   }
} 
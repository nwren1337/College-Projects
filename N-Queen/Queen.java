/**
 *The Queen class represents a Queen chess piece. A queen
 *is the most powerful of the chess pieces, able to attack
 *any chess piece in the same row, column, or diagonal to the queen
 *
 *@author Nathan Wren
 *@version 0.1.0
 *@since  2018-10-26
 */
public class Queen
{
   //Invariant of the Queen class
   // 1. x and y represent the position of the Queen in the rows and columns of a chess board respectively
   // 2. The rows and columns are indexed from 0
   // 3. bound is the sise of the N x N grid
   // 4. x and y must be valid ints such that x,y|{0 <= x,y < bound}
   private int x,y;
   private int bound;
   
   /**
    *Default constructor intentionally made blank to ensure
    *the queen is instantiated within a bound
    */
   private Queen()
   {
      /* do nothing */
   }
   
   /**
    *Creates a Queen at the origin of an N x N grid
    *@param b
    *    boundary size of the grid
    */
   public Queen(int b)
   {
      x = 0;
      y = 0;
      bound = b;
   }
   
   /**
    *Creates a Queen at a specified location on an N x N grid
    *@param row
    *    row to place the queen in
    *@param column
    *    column to place the queen in
    *@param b
    *    boundary size of the grid
    *@throws IllegalArgumentException
    *    either row or column is outside specified boundry
    */
   public Queen(int row, int column, int b)
   {
      bound = b;
      if(!validLocation(row, column))
            throw new IllegalArgumentException("Queen at invalid location within boundry : " + bound);
      x = row;
      y = column;
      
   }
   
   /**
    *Called any time the queen movies location to check if it is valid
    *@param row
    *    x value to test
    *@param column
    *    y value to test
    *@param b
    *    boundry value
    *@returns 
    *    true if row and column are both valid locations within bound
    */
   private boolean validLocation(int row, int column)
   {
      return (row >= 0) && (column >= 0) && (row < bound) && (column < bound);
   }
   
   /**
    *Attempt to move the queen up one column on the board
    *@returns 
    *    true if the queen was able to move, false if the queen was not
    */
   public boolean up()
   {
      boolean canShift = false;
      
      canShift = validLocation(x, y + 1);
      
      if(canShift)
         y = y + 1;
      
      return canShift;
   }
   
   /**
    *Attempt to move the queen down one column on the board
    *@returns 
    *    true if the queen was able to move, false if the queen was not
    */
   public boolean down()
   {
      boolean canShift = false;
      
      canShift = validLocation(x, y - 1);
      
      if(canShift)
         y = y - 1;
      
      return canShift;
   }
   
   /**
    *Attempt to move the queen to one position to the left in its row
    *@returns 
    *    true if the queen was able to move, false if the queen was not
    */
   public boolean left()
   {
      boolean canShift = false;
      
      canShift = validLocation(x - 1, y);
      
      if(canShift)
         x = x - 1;
      
      return canShift;
   }
   
   /**
    *Attempt to move the queen one position to the right in its row
    *@returns 
    *    true if the queen was able to move, false if the queen was not
    */
   public boolean right()
   {
      boolean canShift = false;
      
      canShift = validLocation(x + 1, y);
      
      if(canShift)
         x = x + 1;
      
      return canShift;
   }
   
   public boolean conflicts(Queen other)
   {
      int numConflicts = 0;
      
      //If in the same row
      if(this.x == other.x)
         numConflicts++;
      
      //If in the same column
      if(this.y == other.y)
         numConflicts++;
      
      //If we have not yet found a conflict
      if(numConflicts == 0)
      {
         //Test the diagonals
         numConflicts = diagonalTest(other);
      }
      
      //The other queen conflicts if the number of conflicts found is greater than 0
      return numConflicts > 0;
   }
   
   private int diagonalTest(Queen other)
   {  
      return 0;
   }
}
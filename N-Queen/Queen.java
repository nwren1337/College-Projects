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
   // 1. column represents horizontal value, row represents the verticle value
   // 2. The rows and columns are indexed from 0
   // 3. bound is the sise of the N x N grid
   // 4. column and row must be valid ints such that x,y|{0 <= x,y < bound}
   private int row,column;
   private int bound;
   
   /**
    *Default constructor intentionally made private to ensure
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
      row = 0;
      column = 0;
      bound = b;
   }
   
   /**
    *Creates a Queen at a specified location on an N x N grid
    *@param r
    *    row to place the queen in
    *@param c
    *    column to place the queen in
    *@param b
    *    boundary size of the grid
    *@throws IllegalArgumentException
    *    either row or column is outside specified boundry
    */
   public Queen(int c, int r, int b)
   {
      bound = b;
      if(!validLocation(row, column))
            throw new IllegalArgumentException("Queen at invalid location within boundry : " + bound);
      row = r;
      column = c;
      
   }
   
   /**
    *Accessor method for row of Queen
    *@returns
    *    value of x
    */
   public int getRow()
   {
      return row;
   }
   
   /**
    *Accessor method for column of Queen
    *@returns
    *    value of y
    */
   public int getColumn()
   {
      return column;
   }
   
    /**
    *Accessor method for boundry of N x N board
    *@returns
    *    value of bound
    */
   public int getBoundry()
   {
      return bound;
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
   private boolean validLocation(int r, int c)
   {
      return (r >= 0) && (c >= 0) && (r < bound) && (c < bound);
   }
   
   /**
    *Attempt to move the queen up one column on the board
    *@returns 
    *    true if the queen was able to move, false if the queen was not
    */
   public boolean up()
   {
      boolean canShift = false;
      
      canShift = validLocation(column, row + 1);
      
      if(canShift)
         row = row + 1;
      
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
      
      canShift = validLocation(column, row - 1);
      
      if(canShift)
         row = row - 1;
      
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
      
      canShift = validLocation(column - 1, row);
      
      if(canShift)
         column = column - 1;
      
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
      
      canShift = validLocation(column + 1, row);
      
      if(canShift)
         column = column + 1;
      
      return canShift;
   }
   
   public boolean canMoveRight()
   {
      return validLocation(column + 1, row);
   }
   
   public boolean conflicts(Queen other)
   {
      int numConflicts = 0;
      
      //If in the same row
      if(this.column == other.column)
         return true;
      
      //If in the same column
      if(this.row == other.row)
         return true;
      
      //If we have not yet found a conflict
      if(Math.abs(this.column - other.column) == Math.abs(this.row - other.row))
      {
         return true;
      }
      
      //The other queen conflicts if the number of conflicts found is greater than 0
      return false;
   }
   
   public String toString()
   {
      return "(" + this.column + ", " + this.row + ")";
   }
}
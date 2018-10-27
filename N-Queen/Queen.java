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
   // 2. bound is the sise of the N x N grid
   // 3. x and y must be valid ints such that x,y|{0 <= x,y <= bound}
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
      x = row;
      y = column;
      bound = b;
      if(!validLocation())
         throw new IllegalArgumentException("Queen at invalid location within boundry : " + bound);
   }
   
   private boolean validLocation()
   {
      return (x >= 0) && (y >= 0) && (x <= bound) && (y <= bound);
   }
}
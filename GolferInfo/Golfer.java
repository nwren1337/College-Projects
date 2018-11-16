/**
 * Golfer class to model information on a golfer. Tracks last name,
 * number of rounds, handicap, and average score of the golfer.
 *@author Nathan Wren
 *@version 0.1
 *@since 11/16/2018
 */
public class Golfer
{
   //Invariant of the Golfer class :
   // 1. Last name is the object used to implement compareTo.
   // 2. All numerical data members are updated together. Ie,
   //    if a new round is added, the average score and handicap
   //    are recalculated  
   private String lastName;
   private int numRounds;
   private int handicap;
   private double avgScore;
   
   public Golfer(String name)
   {
      lastName = name;
      numRounds = 0;
      handicap = 0;
      avgScore = 0;
   }
   
   public Golfer(String name, int rounds, int h, double sc)
   {
      lastName = name;
      numRounds = rounds;
      handicap = h;
      avgScore = sc;
   }
   
}
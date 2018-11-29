import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Golfer class to model information on a golfer. Tracks last name,
 * number of rounds, handicap, and average score of the golfer.
 *@author Nathan Wren
 *@version 0.1
 *@since 11/16/2018
 */
public class Golfer implements Comparable<Golfer>
{
   /* VARIABLES */
   
   //Invariant of the Golfer class :
   // 1. Last name is the object used to implement compareTo.
   // 2. All numerical data members are updated together. Ie,
   //    if a new round is added, the average score and handicap
   //    are recalculated 
   // 3. Handicap has a validate range of 0-20 
   private String lastName;
   private int numRounds;
   private int handicap;
   private double avgScore;
   
   public final int HANDICAP_MIN = 0;
   public final int HANDICAP_MAX = 20;
   
   /* CONSTRUCTORS */
   
   /**
    *Creates a Golfer with no value for metrics
    *@param name
    *    name of the golfer
    */
   public Golfer(String name)
   {
      lastName = name;
      numRounds = 0;
      handicap = 0;
      avgScore = 0;
   }
   
   /**
    *Creates a Queen at a specified location on an N x N grid
    *@param name
    *    Name of the golfer
    *@param rounds
    *    number of rounds played
    *@param h
    *    Golfers handicap, valid range is 0-20
    *@param sc
    *    Score of the golfer
    *@throws IllegalArgumentException
    *    handicap is not within valid range
    */
   public Golfer(String name, int rounds, int h, double sc)
   {
      if((h < HANDICAP_MIN) || (h > HANDICAP_MAX))
         throw new IllegalArgumentException("Handicap is not within range of 0-20!");
      lastName = name;
      numRounds = rounds;
      handicap = h;
      avgScore = sc;
   }
   
   /* ACCESSORS */
   
   public String getName()
   {
      return lastName;
   }
   
   public int getNumRounds()
   {
      return numRounds;
   }
   
   public int getHandicap()
   {
      return handicap;
   }
   
   public double getAverageScore()
   {
      return avgScore;
   }
   
   
   /* MODIFIERS */
   
   public void setName(String name)
   {
      lastName = name;
   }
   
   public void addScore(double sc)
   {
      double totScore = avgScore * numRounds;
      totScore += sc;
      numRounds++;
      avgScore = new BigDecimal(totScore / numRounds).setScale(2, RoundingMode.HALF_UP).doubleValue();
      
   }
   
   /* OVERRIDES */
   
   public int compareTo(Golfer other)
   {
      return this.lastName.toLowerCase().compareTo(other.lastName.toLowerCase());
   }
   
   public String toString()
   {
      return lastName + " " + numRounds + " " + handicap + " " + avgScore;
   }
}
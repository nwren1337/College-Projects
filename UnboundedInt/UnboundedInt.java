/**
 * UnboundedInt - A class to manipulate and represent integers larger than Math.INT_MAX
 *@author Nate Wren
 *@author Caleb Secor
 *@author Adesh Rai
 *@version 0.1
 */
 
 public class UnboundedInt
 {
 
   //Invariant of the UnboundedInt class :
   //  1. Each node can only store value between 0 and 999
   //  2. Head node represents the hundreds place, or start of the int
   //  3. Tail is the end of the number
   //  4. numNodes is the magnitude of the number, ie 1,000,000,000 = 4
   //  5. Cursor is the current node being operated on, if there is no current node cursor = null
   
   IntNode head;
   IntNode tail;
   IntNode cursor;
   int numNodes;
   
   /**
   * Initialize UnboundedInt given string input
   * @param s
   *   string representation of a large integer
   * @postcondition
   *   UnboundedInt is created 
   * @throws
   *   IllegalArgumentException if string cannot be converted to integer
   **/  
   
   public UnboundedInt(String s)
   {
      StringBuilder reversed = new StringBuilder(s), temp = new StringBuilder();
      String temp3;
      reversed = reversed.reverse();
      int intTemp = 0;
      int i = 0;
      
      while(i < reversed.length())
      {
         for(int j = 0; (j < 3) && (j + i < reversed.length()); j++)
         {
            temp.append(reversed.charAt(j + i));
         }
         temp3 = temp.toString();
         
         try
         {
            intTemp = Integer.parseInt(temp3);
         } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Cannot convert to integer!");
         }
         
         if(head == null)
         {
            head = new IntNode(intTemp, null);
            tail = head;
            cursor = head;
         } else {
            cursor.setLink(new IntNode(intTemp, null));
            cursor = cursor.getLink();
            tail = cursor;
         }
         
         temp = new StringBuilder();
         i += 3;
         numNodes++;
      }
      
      cursor = head;
   }
   
   /**
   * Tests equality of UnboundedInt objects
   * @param obj
   *   Other UnboundedInt object
   * @returns
   *   true if the two UnboundedInts are equal  
   **/  
   public boolean equals(Object obj)
   {
      UnboundedInt other;
      boolean eq = false;
      
      if(obj instanceof UnboundedInt)
      {
         other = (UnboundedInt) obj;
         other.start();
         this.start();
         while(this.cursor != null && other.cursor != null)
         {
            if(this.cursor.getData() != other.cursor.getData())
            {
               return false;
            }
            
            this.cursor = this.cursor.getLink();
            other.cursor = other.cursor.getLink();
         }
         
         eq = (this.numNodes == other.numNodes);
         
         return eq;
       }
       
       return false;
    }
         
   /**
   * Sets the cursor to the start of the UnboundedInt
   * @postcondition
   *   cursor at start of the UnboundedInt  
   **/ 
   public void start()
   {
      cursor = head;
   }
   
   /**
   * Advances the cursor to the next IntNode within the UnboundedInt if there is a next node
   * @postcondition
   *   Cursor advances to the next node
   * @throws
   *   IllegalStateException if the cursor is currently null  
   **/ 
   public void advance()
   {
      if(cursor != null)
      {
         cursor = cursor.getLink();
      } 
      else
      {
         throw new IllegalStateException("The cursor cannot advance!");
      }
    }
   
   /**
   * Return the data value of the current IntNode
   * @return
   *   Integer between 0-999 that represents the cursor value
   * @throws
   *   IllegalStateException if the cursor is currently null  
   **/ 
   public int getNodeValue()
   {
      if(cursor != null)
      {
         return reverseDigit(cursor.getData());
      } 
      else
      {
         throw new IllegalStateException("The cursor is null!");
      }
    }
   
   //Private helper function 
   //Necessary because the data is stored in reverse in the linked list
   private int reverseDigit(int n)
   {
      int rev = 0, lastDigit = 0;
      
      while(n > 0)
      {
         //extract last digit
         lastDigit = n % 10;
         
         //Add to return value increasing the degree
         rev = (rev * 10) + lastDigit;
         
         //chop off the digit we just added
         n = n / 10;
      }
      
      return rev;
   }
   
   /**
   * Adds two UnboundedInts together
   * @param pass
   *   Second UnboundedInt to add
   * @return
   *   new UnboundedInt representing the sum
   * @throws
   *   IllegalArgumentException if the passed UnboundedInt was null  
   **/ 
   public UnboundedInt add(UnboundedInt pass)
   {      
      //If UnboundedInt passed to the function is not null
      if(pass != null)
      {
         //StringBuilder to hold resultant value and temporary one to reverse individual digits
         StringBuilder result = new StringBuilder(), reversed;
         
         //Temporary string to hold pre-reversed value
         String tempR = new String();
         
         //Temp ints to facilitate calculation 
         int tempA = 0, tempB = 0, sum = 0, tempC = 0, carry = 0;
         
         //Set the cursor to the start for both unbounded ints
         this.start();
         pass.start();
         
         //While there are numbers left to add
         while(this.cursor != null && pass.cursor != null)
         {
            //Get the integer value of the data from the cursor
            tempA = this.cursor.getData();
            tempB = pass.cursor.getData();
            
            //The way we have the data structure designed holds the value in reverse order, so we need to swap that back
            tempA = reverseDigit(tempA);
            tempB = reverseDigit(tempB);
            
            //Sum both with the carry value from the previous iteration
            sum = tempA + tempB + carry;
            
            //If result is less than 100
            if(sum < 1000)
            {
               //Convert to String
               tempR = Integer.toString(sum);
               
               //Set carry int to 0
               carry = 0;
            }
            else
            {
               //Grab the three digits less than 1000
               tempC = sum % 1000;
              
               //Store the carry int
               carry = sum / 1000;
               
               //Convert the digits less than 1000 to the string
               tempR = Integer.toString(tempC);
            }
            
            //Initialize string builder
            reversed = new StringBuilder(tempR);
            
            //Reverse the value
            reversed = reversed.reverse();
            
            //Append to result
            result.append(reversed);
            
            
            this.advance();
            pass.advance();
          }
          
          //If the calling object was larger than the passed object
          if(this.cursor != null)
          {
            //continue appending to the result until you reach the tail
            while(this.cursor != null)
            {
               //Extact data
               tempA = this.cursor.getData();
               
               //Reverse the digit
               tempA = reverseDigit(tempA);
               
               //Add carry
               sum = tempA + carry;
               
               //If result is less than 100
               if(sum < 1000)
               {
                  //Convert to String
                  tempR = Integer.toString(sum);
               
                  //Set carry int to 0
                  carry = 0;
               }
               else
               {
                  //Grab the three digits less than 1000
                  tempC = sum % 1000;
              
                  //Store the carry int
                  carry = sum / 1000;
               
                  //Convert the digits less than 1000 to the string
                  tempR = Integer.toString(tempC);
               }
            
               //Initialize string builder
               reversed = new StringBuilder(tempR);
            
               //Reverse the value
               reversed = reversed.reverse();
            
               //Append to result
               result.append(reversed);
            
            
               this.advance();
            }
          }
          
          //If the passed object was larger than the passed object
          if(pass.cursor != null)
          {
            //continue appending to the result until you reach the tail
            while(pass.cursor != null)
            {
               //Extact data
               tempA = pass.cursor.getData();
               
               //reverse the digit
               tempA = reverseDigit(tempA);
               
               //Add carry
               sum = tempA + carry;
               
               //If result is less than 100
               if(sum < 1000)
               {
                  //Convert to String
                  tempR = Integer.toString(sum);
               
                  //Set carry int to 0
                  carry = 0;
               }
               else
               {
                  //Grab the three digits less than 1000
                  tempC = sum % 1000;
              
                  //Store the carry int
                  carry = sum / 1000;
               
                  //Convert the digits less than 1000 to the string
                  tempR = Integer.toString(tempC);
               }
            
               //Initialize string builder
               reversed = new StringBuilder(tempR);
            
               //Reverse the value
               reversed = reversed.reverse();
            
               //Append to result
               result.append(reversed);
            
            
               pass.advance();
            }
          }
          
          //swap back to correct order
          result = result.reverse();
          
          //return the result
          
          return new UnboundedInt(result.toString());
      } else {
         throw new IllegalArgumentException("Value passed is null!");
      }
   } 
   
   /**
   * Multiplies two UnboundedInts together
   * @param pass
   *   Second UnboundedInt to add
   * @return
   *   new UnboundedInt representing the resultant value
   * @throws
   *   IllegalArgumentException if the passed UnboundedInt was null  
   **/
   public UnboundedInt multiply(UnboundedInt pass)
   {
      if(pass != null)
      {
         //StringBuilder to hold resultant value and temporary one to reverse individual digits
         StringBuilder result = new StringBuilder(), reversed;
         
         IntNode temp;
         
         //Wrapper vars to improve readabilty
         UnboundedInt left = this, right = pass;
         
         UnboundedInt totalSum = new UnboundedInt("0"), tempSum = new UnboundedInt("0"), workingValue = new UnboundedInt("0");
         
         //Temporary string to hold pre-reversed value
         String tempR = new String();
         
         //Temp ints to facilitate calculation 
         int leftDigit = 0, rightDigit = 0, mult = 0, carry = 0, currDigit = 0, leftSize = left.numNodes, rightSize = right.numNodes, currNode = 0;
         
         //Set the cursor to the start for both unbounded ints
         left.start();
         right.start();
         
         while(left.cursor != null)
         { 
             leftDigit = left.cursor.getData();
             
             leftDigit = reverseDigit(leftDigit);
             
             right.start();
             
             while(right.cursor != null)
             {
                  rightDigit = right.cursor.getData();
                  
                  rightDigit = reverseDigit(rightDigit);
                  
                  mult = (leftDigit * rightDigit) + carry;
                  
                  if(mult < 1000)
                  {
                     tempR = Integer.toString(mult);
                     
                     carry = 0;
                  } 
                  else
                  {
                     currDigit = mult % 1000;
                     
                     tempR = Integer.toString(currDigit); 
                     
                     carry = mult / 1000;
                  }  
                  
                  reversed = new StringBuilder(tempR);
                  
                  reversed = reversed.reverse();
                  
                  if(currNode < rightSize)
                  {
                     for(int i = 0; i < currNode; i++)
                     {
                        reversed.append("000");
                     }
                  }
                     
                  reversed = reversed.reverse();
                  
                  
                  workingValue = new UnboundedInt(reversed.toString());
                  
                  totalSum = totalSum.add(workingValue);
                  
                  currNode++;
                  
                  right.advance();
              }
                  
                   
              left.advance();    
         }
         
         return totalSum;
               
      } else {
         throw new IllegalArgumentException("Passed value is null!");
      }
   } 
    
   /**
   * Convert UnboundedInt to string representation
   * @return
   *   String representation of UnboundedInt  
   **/
   public String toString()
   {
      StringBuilder num = new StringBuilder();
      
      int currDigit = 0;
      
      cursor = head;
      
      for(int i = 0; i < numNodes; i++)
      {
         currDigit = cursor.getData();
         if(cursor != tail)
         {
            if((currDigit > 10) && (currDigit < 100))
            {
               num.append("0");
            }
            else if(currDigit < 10)
            {
               num.append("00");
            }
         }
         
         num.append(currDigit);
         cursor = cursor.getLink();
      }
      
      return num.reverse().toString();   
   }
  
  /**
   * Clones the UnboundedInt to a new UnboundedInt object
   * @return
   *   new UnboundedInt with same values in data members
   * @throws
   *   RuntimeException if class does not support cloning  
   **/
  public UnboundedInt clone()
   {
        UnboundedInt answer;
      
      try
      {
         answer = (UnboundedInt) super.clone( );
      }
      catch (CloneNotSupportedException e)
      {  // This exception should not occur. But if it does, it would probably
         // indicate a programming error that made super.clone unavailable.
         // The most common error would be forgetting the "Implements Cloneable"
         // clause at the start of this class.
         throw new RuntimeException("This class does not implement Cloneable");
      }
            
      return answer; 
   }

}

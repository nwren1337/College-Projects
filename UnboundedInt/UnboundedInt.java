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
         
   
   public void start()
   {
      cursor = head;
   }
   
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
    
   public int getNodeValue()
   {
      if(cursor != null)
      {
         return cursor.getData();
      } 
      else
      {
         throw new IllegalStateException("The cursor is null!");
      }
    }
    
   private int reverseDigit(int n)
   {
      int reverse = 0, lastDigit = 0;
      
      while(n > 0)
      {
         //extract last digit
         lastDigit = n % 10;
         
         //Add to return value increasing the degree
         reverse = (reverse * 10) + lastDigit;
         
         //chop off the digit we just added
         n = n / 10;
      }
      
      return reverse;
   }
    
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
   
   public UnboundedInt multiply(UnboundedInt pass)
   {
      if(pass != null)
      {
         //StringBuilder to hold resultant value and temporary one to reverse individual digits
         StringBuilder result = new StringBuilder(), reversed;
         
         IntNode temp;
         
         UnboundedInt temp1 = null, temp2 = null, temp3 = null;
         
         //Temporary string to hold pre-reversed value
         String tempR = new String();
         
         //Temp ints to facilitate calculation 
         int currDigit = 0, currResult = 0, currOperand = 0, carry = 0, num = 0, tot = 0, i = 1;
         
         //Set the cursor to the start for both unbounded ints
         this.start();
         pass.start();
         
         while(this.cursor != null)
         {            
            //Get the integer value of the data from the cursor
            currDigit = this.cursor.getData();
                       
            //The way we have the data structure designed holds the value in reverse order, so we need to swap that back
            currDigit = reverseDigit(currDigit);
            
            
            
            while(currDigit > 0)
            {
               num = currDigit % 10;
               
               temp = pass.cursor;
               
               while(temp != null)
               {
                  currOperand = temp.getData();
                  
                  currOperand = reverseDigit(currOperand);
                  
                  currResult = (num * currOperand) + carry;
                  
                  if(currResult < 1000)
                  {
                     //Convert to String
                     tempR = Integer.toString(currResult);
                  
                     //Set carry int to 0
                     carry = 0;
                  }
                  else
                  {
                     //Grab the three digits less than 1000
                     tot = currResult % 1000;
                 
                     //Store the carry int
                     carry = currResult / 1000;
                  
                     //Convert the digits less than 1000 to the string
                     tempR = Integer.toString(tot);
                  }
                  
                  //Initialize string builder
                  reversed = new StringBuilder(tempR);
               
                  //Reverse the value
                  reversed = reversed.reverse();
               
                  //Append to result
                  result.append(reversed);
                  
                  temp = temp.getLink();
               }
               
               currDigit /= 10;
            }
            
            for(int j = 0; j < i; j++)
            {
               result.append("0");
            }
            
            result = result.reverse();
            
            if(temp1 != null)
            {
               temp2 = temp1;
               temp1 = new UnboundedInt(result.toString());
               temp3 = temp2.add(temp2);
            }
            else
            {
               temp1 = new UnboundedInt(result.toString());
            }
            
            i++;
            this.advance();  
         }
         
         return temp3;
               
      } else {
         throw new IllegalArgumentException("Passed value is null!");
      }
   } 
    
   
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
}
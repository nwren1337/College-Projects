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
   //  4. numNodes is the number of digits, ie 1,000,000,000 = 4
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
         intTemp = Integer.parseInt(temp3); 
         
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
   public UnboundedInt add(UnboundedInt pass)
   {
      String tempS = "";
      int carry = 0;
      UnboundedInt together = new UnboundedInt("");
      IntNode tempCurrA, tempCurrB;
      tempCurrA = this.head;
      tempCurrB = pass.head;
      int valueA, valueB, valueC;
      valueA = tempCurrA.getData();
      valueB = tempCurrB.getData();
      valueC = valueA + valueB;
      tempCurrA = tempCurrA.getLink();
      tempCurrB = tempCurrB.getLink();
      if(valueC > 1000)
      {
         valueC = valueC - 1000;
         tempS = Integer.toString(valueC);
         StringBuilder reversal = new StringBuilder(tempS);
         reversal = reversal.reverse();
         tempS = reversal.toString();
         valueC = Integer.parseInt(tempS);
         together.head = new IntNode(valueC, null);
         together.tail = together.head;
         together.cursor = together.head;
         together.numNodes++;
         carry = 1;
      }
      else
      {
         together.head = new IntNode(valueC, null);
         together.tail = together.head;
         together.cursor = together.head;
         together.numNodes++;
      }
      for(int i = 0; i < this.numNodes || i < pass.numNodes; i++)
      {
         if(tempCurrA != null && tempCurrB != null)
         {
            for(int k = 0; k < this.numNodes || k < pass.numNodes; k++)
            {
               if(tempCurrA != null && tempCurrB != null)
               {
                  valueA = tempCurrA.getData();
                  valueB = tempCurrB.getData();
                  valueC = valueA + valueB;
                  tempCurrA = tempCurrA.getLink();
                  tempCurrB = tempCurrB.getLink();
                  if(carry == 1)
                  {
                     together.cursor.setLink(new IntNode(valueC + 1, null));
                     together.cursor = together.cursor.getLink();
                     together.tail = together.cursor;
                     together.numNodes++;
                     carry = 0;
                 }
                 else if(carry == 1 && valueC > 1000)
                 {
                    valueC = valueC - 1000;
                    valueC = valueC + 1;
                    together.cursor.setLink(new IntNode(valueC, null));
                    together.cursor = together.cursor.getLink();
                    together.numNodes++;
                 }
                 else if(valueC > 1000 && carry != 1)
                 {
                    valueC = valueC - 1000;
                    valueC = valueC + 1;
                    together.cursor.setLink(new IntNode(valueC, null));
                    together.cursor = together.cursor.getLink();
                    together.numNodes++;
                    carry = 0;
                 }
                 else{
                    together.cursor.setLink(new IntNode(valueC, null));
                    together.cursor = together.cursor.getLink();
                    together.tail = together.cursor;
                    together.numNodes++;
                 }
                  
               }
            }
          }
          else if(tempCurrA == null && tempCurrB != null)
          {
             for(int j = 0; j < pass.numNodes; j++)
             valueB = tempCurrB.getData();
             valueC = valueB;
             tempCurrB = tempCurrB.getLink();
             if(carry == 1)
             {
               valueC = valueC + 1;
               together.cursor.setLink(new IntNode(valueC, null));
               together.cursor = together.cursor.getLink();
               together.tail = together.cursor;
               carry = 0;
               together.numNodes++;
             }
             else if(carry == 1 && valueC > 1000)
             {
                valueC = valueC - 1000;
                valueC = valueC + 1;
                together.cursor.setLink(new IntNode(valueC, null));
                together.cursor = together.cursor.getLink();
                together.numNodes++;
             }
             else if(valueC > 1000 && carry != 1)
             {
                valueC = valueC - 1000;
                valueC = valueC + 1;
                together.cursor.setLink(new IntNode(valueC, null));
                together.cursor = together.cursor.getLink();
                together.numNodes++;
                carry = 0;
             }
             else
             {
                together.cursor.setLink(new IntNode(valueC, null));
                together.cursor = together.cursor.getLink();
                together.tail = together.cursor;
                together.numNodes++;
             }
          }
          else if(tempCurrA != null && tempCurrB == null)
          {
             for(int m = 0; m < pass.numNodes; m++)
             valueA = tempCurrA.getData();
             valueC = valueA;
             tempCurrA = tempCurrA.getLink();
             if(carry == 1)
             {
               valueC = valueC + 1;
               together.cursor.setLink(new IntNode(valueC, null));
               together.cursor = together.cursor.getLink();
               together.tail = together.cursor;
               carry = 0;
               together.numNodes++;
             }
             else if(carry == 1 && valueC > 1000)
             {
                valueC = valueC - 1000;
                valueC = valueC + 1;
                together.cursor.setLink(new IntNode(valueC, null));
                together.cursor = together.cursor.getLink();
                together.numNodes++;
             }
             else if(valueC > 1000 && carry != 1)
             {
                valueC = valueC - 1000;
                valueC = valueC + 1;
                together.cursor.setLink(new IntNode(valueC, null));
                together.cursor = together.cursor.getLink();
                together.numNodes++;
                carry = 0;
             }
             else{
                together.cursor.setLink(new IntNode(valueC, null));
                together.cursor = together.cursor.getLink();
                together.tail = together.cursor;
                together.numNodes++;
             }

          }
 
       }
       if(carry == 1)
       {
       together.cursor.setLink(new IntNode(1, null));
       together.cursor = together.cursor.getLink();
       together.tail = together.cursor;
       together.numNodes++;
       }
       together.cursor = together.head;
       return together;
       
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
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
         for(int j = 0; j < 3; j++)
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
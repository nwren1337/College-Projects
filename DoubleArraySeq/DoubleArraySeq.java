// File: DoubleArraySeq.java 
//Implemented by Nate Wren and Caleb Secor
//9/27/2018

// This is an assignment for students to complete after reading Chapter 3 of
// "Data Structures and Other Objects Using Java" by Michael Main.


/******************************************************************************
* This class is a homework assignment;
* A DoubleArraySeq is a collection of double numbers.
* The sequence can have a special "current element," which is specified and 
* accessed through four methods that are not available in the bag class 
* (start, getCurrent, advance and isCurrent).
*
* @note
*   (1) The capacity of one a sequence can change after it's created, but
*   the maximum capacity is limited by the amount of free memory on the 
*   machine. The constructor, addAfter, 
*   addBefore, clone, 
*   and concatenation will result in an
*   OutOfMemoryError when free memory is exhausted.
*   <p>
*   (2) A sequence's capacity cannot exceed the maximum integer 2,147,483,647
*   (Integer.MAX_VALUE). Any attempt to create a larger capacity
*   results in a failure due to an arithmetic overflow. 
*
* @note
*   This file contains only blank implementations ("stubs")
*   because this is a Programming Project for my students.
*
* @see
*   <A HREF="../../../../edu/colorado/collections/DoubleArraySeq.java">
*   Java Source Code for this class
*   (www.cs.colorado.edu/~main/edu/colorado/collections/DoubleArraySeq.java)
*   </A>
*
* @version
*   March 5, 2002
******************************************************************************/
public class DoubleArraySeq implements Cloneable
{
   // Invariant of the DoubleArraySeq class:
   //   1. The number of elements in the sequences is in the instance variable 
   //      manyItems.
   //   2. For an empty sequence (with no elements), we do not care what is 
   //      stored in any of data; for a non-empty sequence, the elements of the
   //      sequence are stored in data[0] through data[manyItems-1], and we
   //      don�t care what�s in the rest of data.
   //   3. If there is a current element, then it lies in data[currentIndex];
   //      if there is no current element, then currentIndex equals manyItems. 
   private double[ ] data;
   private int manyItems;
   private int currentIndex; 

   //Constant for default initial capacity
   private final int INIT_CAP = 10;
   
   /**
   * Initialize an empty sequence with an initial capacity of 10.  Note that
   * the addAfter and addBefore methods work
   * efficiently (without needing more memory) until this capacity is reached.
   * @param - none
   * @postcondition
   *   This sequence is empty and has an initial capacity of 10.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: 
   *   new double[10].
   **/   
   public DoubleArraySeq( )
   {
      manyItems = 0;
      currentIndex = 0;
      data = new double[INIT_CAP];
   }
     

   /**
   * Initialize an empty sequence with a specified initial capacity. Note that
   * the addAfter and addBefore methods work
   * efficiently (without needing more memory) until this capacity is reached.
   * @param initialCapacity
   *   the initial capacity of this sequence
   * @precondition
   *   initialCapacity is non-negative.
   * @postcondition
   *   This sequence is empty and has the given initial capacity.
   * @exception IllegalArgumentException
   *   Indicates that initialCapacity is negative.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: 
   *   new double[initialCapacity].
   **/   
   public DoubleArraySeq(int initialCapacity)
   {
      manyItems = 0;
      currentIndex = 0;
      data = new double[initialCapacity];
   }
        
 
   /**
   * Add a new element to this sequence, after the current element. 
   * If the new element would take this sequence beyond its current capacity,
   * then the capacity is increased before adding the new element.
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to this sequence. If there was
   *   a current element, then the new element is placed after the current
   *   element. If there was no current element, then the new element is placed
   *   at the end of the sequence. In all cases, the new element becomes the
   *   new current element of this sequence. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the sequence's capacity.
   * @note
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause the sequence to fail with an
   *   arithmetic overflow.
   **/
   public void addAfter(double element)
   {
      // Implemented by Caleb.
      int i;
      if(manyItems == data.length)
      {
         ensureCapacity(manyItems*2 + 1);
      }
      if (!isCurrent())
      {
         currentIndex = 0;
      }
      for(i = manyItems; i > currentIndex; i--) 
      {
         data[i + 1] = data[i];        
      }
      if(currentIndex == manyItems)
      {
         data[currentIndex] = element;
         manyItems++;
      }
      else
      {
         data[currentIndex + 1] = element;
         manyItems++;
      }
   }
   


   /**
   * Add a new element to this sequence, before the current element. 
   * If the new element would take this sequence beyond its current capacity,
   * then the capacity is increased before adding the new element.
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to this sequence. If there was
   *   a current element, then the new element is placed before the current
   *   element. If there was no current element, then the new element is placed
   *   at the start of the sequence. In all cases, the new element becomes the
   *   new current element of this sequence. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the sequence's capacity.
   * @note
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause the sequence to fail with an
   *   arithmetic overflow.
   **/
   public void addBefore(double element)
   {
      // Implemented by Nate.
      
      if(currentIndex == 0)
      {
         //Space ensurance will be handled in addFront
         addFront(element);
      }
      else
      {
         //Ensure we have space
         if(manyItems == data.length)
         {
            ensureCapacity((manyItems + 1) * 2);
         }
         
         manyItems++;
         
         //Functional, but inefficient implementation
         //Requires a second array to be created in RAM
         double[] tmp = new double[data.length];
         System.arraycopy(data, currentIndex, tmp, 0, manyItems);
         data[currentIndex] = element;
         System.arraycopy(tmp, 0, data, currentIndex + 1, manyItems);
         
         //Move all elements forward one space
         /*
         for(int i = currentIndex + 1; i < manyItems - 1; i++)
         {
            tmp2 = data[i];
            data[i] = tmp;
            tmp = tmp2;
         }
         
         //Set the data at the current index to the 
         data[] = element;
         */
      }
   }
   
  /**
   * Add a new element to this sequence, at the front of the sequence 
   * If the new element would take this sequence beyond its current capacity,
   * then the capacity is increased before adding the new element.
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to this first spot of the sequence.
   *   The new element becomes the current element of this sequence. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the sequence's capacity.
   * @note
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause the sequence to fail with an
   *   arithmetic overflow.
   **/
   public void addFront(double element)
   {
      // Implemented by Nate.
      if(manyItems == data.length)
      {
         ensureCapacity((manyItems + 1) * 2);
      }

      manyItems++;
      
      double[] tmp = new double[data.length];
      System.arraycopy(data, 0, tmp, 1, manyItems);
      tmp[0] = element;
      data = tmp;
      currentIndex = 0;
      
   }
   
  /**
   * Add a new element to the end of the sequence 
   * If the new element would take this sequence beyond its current capacity,
   * then the capacity is increased before adding the new element.
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to this first spot of the sequence.
   *   The new element becomes the current element of this sequence. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the sequence's capacity.
   * @note
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause the sequence to fail with an
   *   arithmetic overflow.
   **/
   public void addEnd(double element)
   {
      // Implemented by Nate.
      if(manyItems == data.length)
      {
         ensureCapacity((manyItems + 1) * 2);
      }
      
      currentIndex = manyItems;
      manyItems++;
      data[currentIndex] = element;      
      
   }
   
   
   /**
   * Place the contents of another sequence at the end of this sequence.
   * @param addend
   *   a sequence whose contents will be placed at the end of this sequence
   * @precondition
   *   The parameter, addend, is not null. 
   * @postcondition
   *   The elements from addend have been placed at the end of 
   *   this sequence. The current element of this sequence remains where it 
   *   was, and the addend is also unchanged.
   * @exception NullPointerException
   *   Indicates that addend is null. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory to increase the size of this sequence.
   * @note
   *   An attempt to increase the capacity beyond
   *   Integer.MAX_VALUE will cause an arithmetic overflow
   *   that will cause the sequence to fail.
   **/
   public void addAll(DoubleArraySeq addend)
   {
      // Implemented by Caleb.
      if ((this.manyItems + addend.manyItems) >= data.length)
      {
         ensureCapacity((this.manyItems + addend.manyItems)*2 + 1);
      }
      int i = manyItems;
      for(int j = 0; j < addend.size(); j++)
      {
         manyItems++;
         data[i] = addend.data[j];
         i++;
      }
         
   

   }   
   
   
   /**
   * Move forward, so that the current element is now the next element in
   * this sequence.
   * @param - none
   * @precondition
   *   isCurrent() returns true. 
   * @postcondition
   *   If the current element was already the end element of this sequence 
   *   (with nothing after it), then there is no longer any current element. 
   *   Otherwise, the new element is the element immediately after the 
   *   original current element.
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   advance may not be called.
   **/
   public void advance( )
   {
      // Implemented by Nate
      if(isCurrent())
      {
         if(currentIndex < (manyItems - 1))
            currentIndex++;
      } else {
         throw new IllegalStateException("There is no current element");
      }
   }
   
   
   /**
   * Generate a copy of this sequence.
   * @param - none
   * @return
   *   The return value is a copy of this sequence. Subsequent changes to the
   *   copy will not affect the original, nor vice versa.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public DoubleArraySeq clone( )
   {  // Clone a DoubleArraySeq object.
      DoubleArraySeq answer;
      
      try
      {
         answer = (DoubleArraySeq) super.clone( );
      }
      catch (CloneNotSupportedException e)
      {  // This exception should not occur. But if it does, it would probably
         // indicate a programming error that made super.clone unavailable.
         // The most common error would be forgetting the "Implements Cloneable"
         // clause at the start of this class.
         throw new RuntimeException
         ("This class does not implement Cloneable");
      }
      
      answer.data = data.clone( );
      
      return answer;
   }
   

   /**
   * Create a new sequence that contains all the elements from one sequence
   * followed by another.
   * @param s1
   *   the first of two sequences
   * @param s2
   *   the second of two sequences
   * @precondition
   *   Neither s1 nor s2 is null.
   * @return
   *   a new sequence that has the elements of s1 followed by the
   *   elements of s2 (with no current element)
   * @exception NullPointerException.
   *   Indicates that one of the arguments is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the new sequence.
   * @note
   *   An attempt to create a sequence with a capacity beyond
   *   Integer.MAX_VALUE will cause an arithmetic overflow
   *   that will cause the sequence to fail.
   **/   
   public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2)
   {
      // Implemented by student.
      DoubleArraySeq concat = new DoubleArraySeq();
      
      for(int i = 0; i < s1.manyItems; i++)
      {
         concat.addEnd(s1.data[i]);
      }
      
      for(int i = 0; i < s2.manyItems; i++)
      {
         concat.addEnd(s2.data[i]);
      }
      
      concat.currentIndex = concat.manyItems;
      
      return concat;
   }


   /**
   * Change the current capacity of this sequence.
   * @param minimumCapacity
   *   the new capacity for this sequence
   * @postcondition
   *   This sequence's capacity has been changed to at least minimumCapacity.
   *   If the capacity was already at or greater than minimumCapacity,
   *   then the capacity is left unchanged.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for: new int[minimumCapacity].
   **/
   public void ensureCapacity(int minimumCapacity)
   {
      // Implemented by student.
      double[] larger;
      
      if(data.length < minimumCapacity)
      {
         larger = new double[minimumCapacity];
         System.arraycopy(data, 0, larger, 0, manyItems);
         data = larger;
      }
   }

   
   /**
   * Accessor method to get the current capacity of this sequence. 
   * The add method works efficiently (without needing
   * more memory) until this capacity is reached.
   * @param - none
   * @return
   *   the current capacity of this sequence
   **/
   public int getCapacity( )
   {
      // Implemented by student.
      return data.length;
   }


   /**
   * Accessor method to get the current element of this sequence. 
   * @param - none
   * @precondition
   *   isCurrent() returns true.
   * @return
   *   the current element of this sequence
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   getCurrent may not be called.
   **/
   public double getCurrent( )
   {
      // Implemented by student.
      return data[currentIndex];
   }


   /**
   * Accessor method to determine whether this sequence has a specified 
   * current element that can be retrieved with the 
   * getCurrent method. 
   * @param - none
   * @return
   *   true (there is a current element) or false (there is no current element at the moment)
   **/
   public boolean isCurrent( )
   {
      // Implemented by Caleb.
      return currentIndex < manyItems;
   }
              
   /**
   * Remove the current element from this sequence.
   * @param - none
   * @precondition
   *   isCurrent() returns true.
   * @postcondition
   *   The current element has been removed from this sequence, and the 
   *   following element (if there is one) is now the new current element. 
   *   If there was no following element, then there is now no current 
   *   element.
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   removeCurrent may not be called. 
   **/
   public void removeCurrent( )
   {
      // Implemented by Caleb.
      manyItems--;
      for(int j = currentIndex; j < manyItems; j++)
      {
         data[j] = data[j+1];
      }      
   }
                 
   
   /**
   * Determine the number of elements in this sequence.
   * @param - none
   * @return
   *   the number of elements in this sequence
   **/ 
   public int size( )
   {
      // Implemented by student.
      return manyItems;
   }
   
   
   /**
   * Set the current element at the front of this sequence.
   * @param - none
   * @postcondition
   *   The front element of this sequence is now the current element (but 
   *   if this sequence has no elements at all, then there is no current 
   *   element).
   **/ 
   public void start( )
   {
      // Implemented by student.
      currentIndex = 0;
   }
   /**
   *Sets the current element at the front of this sequence
   *@param - none
   *@postcondition
   *  The last element is now the current element.
      if the sequence is empty there is no current element.
   **/
   public void setCurrentLast( )
   {
      currentIndex = manyItems - 1;
   }
   /**
   *Sets the current element to the element chosen by the user
   *@param - element
   *  an elment given by the user
   *@postcondition
   *  The current element is set at the element chosen by the user
   *@throws IllegalStateException
   *        Thrown if input is larger than number of items in sequence or less than zero
   **/
   public void setCurrent(int element)
   {
      if(element >= 0 && element < manyItems)
         currentIndex = element;
      else
         throw new IllegalStateException("Element is larger than the number of items in the sequence!");
   }
   /**
   *Checks to see if two sequences are equal
   *@param - an object
   *  Another sequence given by the user
   *@return
   *  A boolean of if it is equal or not
   *@exception - IlleagalArgumentException
   *  Indicates the wrong class was passed through
   **/
   public boolean equals(Object obj)
   {
      if(obj instanceof DoubleArraySeq)
      {
         //Cast the reference object to a DoubleArraySeq
         DoubleArraySeq tmp = (DoubleArraySeq) obj;
         //Set up return variable
         boolean sameData = false;
         
         //If they are not the same length it will never enter the loop and will return false
         if(this.manyItems == tmp.manyItems)
         {
            //If they are the same length, it start at the first element and check each value in data for equality
            //Breaks out of the loop if we are either at the end of the array, or if one the elements do not match
            //The corresponding element in the passed object
            int i = 0;
            do
            {
                sameData = (this.data[i] == tmp.data[i]);
                i++;
            } while((i < this.manyItems) && (sameData));
         }
         
         //return results of computation
         return sameData;
       } else {
         //Thrown in the case that they pass another object type than DoubleArraySeq
         throw new IllegalArgumentException("Object passed to function was of type " + obj.getClass() + " not " + this.getClass());
       }
   }
   
   
   /**
   * Reduce the current capacity of this sequence to its actual size (i.e., the
   * number of elements it contains).
   * @param - none
   * @postcondition
   *   This sequence's capacity has been changed to its current size.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for altering the capacity. 
   **/
   public void trimToSize( )
   {
      double[ ] trimmedArray;
      
      if (data.length != manyItems)
      {
         trimmedArray = new double[manyItems];
         System.arraycopy(data, 0, trimmedArray, 0, manyItems);
         data = trimmedArray;
      }
   }
   /**
   *A method to remove the element at the front of the sequence.
   *If there is a next element, make that element the current element. 
   *@param - none
   *@postcondition
   *  element at the front of the list is removed,  
   *  the next element is now the current.
   *@exception IllegalStateException
   *  Indicates the list is empty
   **/
   public void removeFront( )
   {
      if(manyItems > 0)
      {
         double[] temp = new double[data.length];
         System.arraycopy(data, 1, temp, 0, --manyItems);
         data = temp;
      } else {
         throw new IllegalStateException("The sequence is empty!");
      }
   }
   /**
   *A method to output all elements in order separated by a space
   *@param - none
   *@return
   *  The list is printed out
   *@exception IlleagelStateException
   *  Indicates the sequence is empty 
   **/
   public String toString()
   {
      String list = "";
      for(int i = 0; i < manyItems; i++)
      {
         list += (data[i] + " ");
      }
      return list;
      
   }
      
}
           
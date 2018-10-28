// File: LinkedStack.java
// Complete documentation is available from the LinkedStack link in:
//   http://www.cs.colorado.edu/~main/docs/


import java.util.EmptyStackException;
import java.util.NoSuchElementException;


/******************************************************************************
* A <CODE>LinkedStack<E></CODE> is a stack of references to
* <code>E</code>objects.
*
* <dl><dt><b>Limitations:</b><dd>
*   Beyond <CODE>Int.MAX_VALUE</CODE> items, <CODE>size</CODE> is wrong. 
* </dl>
*
* <dt><b>Java Source Code for this class:</b><dd>
*   <A HREF="../../../../edu/colorado/collections/LinkedStack.java">
*   http://www.cs.colorado.edu/~main/edu/colorado/collections/LinkedStack.java
*   </A>
*
* @author Michael Main 
*   <A HREF="mailto:main@colorado.edu"> (main@colorado.edu) </A>
*
* @version
*   Jul 21, 2005
*
* @see ArrayStack
******************************************************************************/
public class LinkedStack<E> implements Cloneable
{
   // Invariant of the LinkedStack class:
   //   1. The items in the stack are stored in a linked list, with the top of
   //      the stack stored at the head node, down to the bottom of the stack
   //      at the final node.
   //   2. The instance variable top is the head reference of the linked list
   //      of items.
   //   3. Size is the current number of elements in the stack
   private Node<E> top; 
   private int size;

   /**
   * Initialize an empty stack.
   * @param - none
   * <dt><b>Postcondition:</b><dd>
   *   This stack is empty.
   **/   
   public LinkedStack( )
   {
      top = null;
      size = 0;
   }

   
   /**
   * Generate a copy of this stack.
   * @param - none
   * @return
   *   The return value is a copy of this stack. Subsequent changes to the
   *   copy will not affect the original, nor vice versa.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public LinkedStack<E> clone( )       
   {  // Clone a LinkedStack<E>.
      LinkedStack<E> answer;
      
      try
      {
         answer = (LinkedStack<E>) super.clone( );
      }
      catch (CloneNotSupportedException e)
      { 
         // This exception should not occur. But if it does, it would probably indicate a
         // programming error that made super.clone unavailable. The most comon error
         // The most common error would be forgetting the "Implements Cloneable"
         // clause at the start of this class.
         throw new RuntimeException
         ("This class does not implement Cloneable");
     }
      
      // The generic listCopy method gets the type of E from top.
      answer.top = Node.listCopy(top);
      
      return answer;
   }        
 
   
   /**
   * Determine whether this stack is empty.
   * @param - none
   * @return
   *   <CODE>true</CODE> if this stack is empty;
   *   <CODE>false</CODE> otherwise. 
   **/
   public boolean isEmpty( )
   {
      return (top == null);
   }
   

   /**
   * Get the top item of this stack, without removing the item.
   * @param - none
   * <dt><b>Precondition:</b><dd>
   *   This stack is not empty.
   * @return
   *   the top item of the stack
   * @exception EmptyStackException
   *   Indicates that this stack is empty.
   **/   
   public E peek( )   
   {
      if (top == null)
         // EmptyStackException is from java.util and its constructor has no argument.
         throw new EmptyStackException( );
      return top.getData( );
   }

   
   /**
   * Get the top item, removing it from this stack.
   * @param - none
   * <dt><b>Precondition:</b><dd>
   *   This stack is not empty.
   * <dt><b>Postcondition:</b><dd>
   *   The return value is the top item of this stack, and the item has
   *   been removed.
   * @exception EmptyStackException
   *   Indicates that this stack is empty.
   **/    
   public E pop( )
   {
      E answer;
      
      if (top == null)
         // EmptyStackException is from java.util and its constructor has no argument.
         throw new EmptyStackException( );
      
      answer = top.getData( );
      top = top.getLink( );
      size--;
      return answer;
   }    


   /**
   * Push a new item onto this stack. The new item may be the null
   * reference.
   * @param <CODE>item</CODE>
   *   the item to be pushed onto this stack 
   * <dt><b>Postcondition:</b><dd>
   *   The item has been pushed onto this stack.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for increasing the stack's capacity.
   **/    
   public void push(E item)
   {
      top = new Node<E>(item, top);
      size++;
   }
   
   /**
   * Get the item that is n elements away from the top
   * @param - n
   *     Distance from the top of the stack to check
   * <dt><b>Precondition:</b><dd>
   *   This stack is not empty.
   * <dt><b>Postcondition:</b><dd>
   *   The return value is the item that is n elements from the top.
   *   The stack is unchanged
   * @exception NoSuchElementException
   *   Indicates that the element does not exist
   **/ 
   public E itemAt(int n)
   {
      if(n < 0 || n > this.size)
         throw new NoSuchElementException();
      
      LinkedStack<E> tempStack = this.clone();
      int s = tempStack.size;
      
      for(int i = s; i > (s - n); i--)
      {
         tempStack.pop();
      }
      
      return (E) tempStack.peek();
    }
      
      
              

   /**
   * Accessor method to determine the number of items in this stack.
   * @param - none
   * @return
   *   the number of items in this stack
   **/ 
   public int size( )   
   {
      // The generic listLength method gets the type of E from top.
      return Node.listLength(top);
   }
 
}
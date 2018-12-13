public class TableChainHash<K, E>
{
   private Object[] table;
   
   public TableChainHash(int tableSize)
   {
      if (tableSize <= 0)
         throw new IllegalArgumentException("Table size must be positive.");
      //Allocate the table, which is initially all null head references.
      table = new Object[tableSize];
   }
   
   private int findIndex(K key)
   // Postcondition: If the specified key is found in the table, then the return
   // value is the index of head of the linked list. Otherwise, the return value is -1.
   {
      int count = 0;
      int i = hash(key);
      
      ChainedHashNode<K, E> cursor = null;
      
      if(table[i] != null)
      {
         cursor = (ChainedHashNode<K, E>) table[i];
         
         while(cursor != null)
         {
            
            if(cursor.key == key)
               return i;
               
            cursor = cursor.link;
         }
      }
      
      return -1;
   }
   
   private int hash(Object key)
   // The return value is a valid index of the table’s arrays. The index is
   // calculated as the remainder when the absolute value of the key’s
   // hash code is divided by the size of the table’s arrays.
   {
      return Math.abs(key.hashCode( )) % table.length;
   }
   
   /**
   * Add a new element to this table, using the specified key.
   * @param <CODE>key</CODE>
   *   the non-null key to use for the new element
   * @param <CODE>element</CODE>
   *   the new element that’s being added to this table
   * <dt><b>Precondition:</b><dd>
   *   If there is not already an element with the specified <CODE>key</CODE>,
   *   then this table’s size must be less than its capacity 
   *   (i.e., <CODE>size() < capacity()</CODE>). Also, neither <CODE>key</CODE>
   *   nor </CODE>element</CODE> is null.
   * <dt><b>Postcondition:</b><dd>
   *   If this table already has an object with the specified <CODE>key</CODE>,
   *   then that object is replaced by </CODE>element</CODE>. Otherwise, the new 
   *   </CODE>element</CODE> is added with the specified <CODE>key</CODE>.
   * @returns
   *   The number of collisions that occurred when attempting to insert an element  
   * @exception IllegalStateException
   *   Indicates that there is no room for a new object in this table.
   * @exception NullPointerException
   *   Indicates that <CODE>key</CODE> or <CODE>element</CODE> is null.   
   **/
   public int put(K key, E element)
   {
      //Cursor node
      ChainedHashNode<K, E> cursor = null;
      
      //Count of collisions
      int collisions = 0;
      
      if(key == null || element == null)
         throw new NullPointerException("Key or element is null");
      
      int index = findIndex(key);
      
      if(index != -1)
      {
         // The key is already in the table.
         //Starting from the head of the linked list at the index
         cursor = (ChainedHashNode<K, E>) table[index];
         
         //While cursor is not null or equal to the target element
         while((cursor != null) && (cursor.key != key))
            //move to the next link
            cursor = cursor.link;
      }
      
      //If the key was not already in the table
      if (cursor == null)
      {  
         //Compute the hash for the index
         int i = hash(key);
         
         //build the new node
         cursor = new ChainedHashNode();
         
         //Set the element and key
         cursor.element = element;
         cursor.key = key;
         
         //Set the link for the cursor to the top of the linked list at the hash
         cursor.link = (ChainedHashNode<K, E>) table[i];
         
         //Sum collisions with the number of links the cursor has
         collisions += numLinks(cursor.link);
         
         //Overwrite old linked list with cursor
         table[i] = cursor;
      }
      else
      { 
         //If they key was already in the table
         //Overwrite the element
         cursor.element = element;
      }
      
      return collisions;
   }
   
   //Simple function that traverses linked list counting nodes on the way down
   private int numLinks(ChainedHashNode<K, E> node)
   {
      int sum = 0;
      
      while(node != null)
      {
         sum++;
         node = node.link;
      }
      
      return sum;
   }
   
   private int nextIndex(int i)
   // The return value is normally i+1. But if i+1 is data.length, then the 
   // return value is zero instead.
   {
      if (i+1 == table.length)
         return 0;
      else
         return i+1;
   } 
   
   /**
   * Removes an object for a specified key.
   * @param <CODE>key</CODE>
   *   the non-null key to look for
   * <dt><b>Precondition:</b><dd>
   *   <CODE>key</CODE> cannot be null.
   * <dt><b>Postcondition:</b><dd>
   *   If an object was found with the specified </CODE>key</CODE>, then that
   *   object has been removed from this table and a copy of the removed object
   *   is returned; otherwise, this table is unchanged and the null reference
   *   is returned.  Note that 
   *   <CODE>key.equals( )</CODE> is used to compare the <CODE>key</CODE>
   *   to the keys that are in the table.
   * @exception NullPointerException
   *   Indicates that </CODE>key</CODE> is null.
   **/
   public E remove(K key)
   {
      int index = findIndex(key);
      E answer = null;
      
      if (index != -1)
      {
         ChainedHashNode<K, E> cursor = (ChainedHashNode<K, E>) table[index];
         ChainedHashNode<K, E> last = null;
         
         //While cursor is not null or equal to the target element
         while((cursor != null) && (cursor.key != key))
         {
            //Keep track of the last node
            last = cursor;
            
            //move on to the next node
            cursor = cursor.link;
         }  
         
         //Get return value from cursor
         answer = cursor.element;
         
         //If this key to remove was at the head of the linked list
         if(last == null)
         {
            //Set the head to the cursor's link
            table[index] = cursor.link;
         }
         else
         {
            //Set the parent's link to the cursors link
            last.link = cursor.link;
         }
         
      }
      
      //return the answer
      return answer;
   } 
   
   /** Retrieves an object for a specified key.
   * @param <CODE>key</CODE>
   *   the non-null key to look for
   * <dt><b>Precondition:</b><dd>
   *   <CODE>key</CODE> cannot be null.
   * @return
   *   a reference to the object with the specified <CODE>key</CODE (if this 
   *   table contains an such an object);  null otherwise. Note that 
   *   <CODE>key.equals( )</CODE> is used to compare the <CODE>key</CODE>
   *   to the keys that are in the table.
   * @exception NullPointerException
   *   Indicates that <CODE>key</CODE> is null.
   **/
   public E get(K key)
   {
      int index = findIndex(key);
      
      if (index == -1)
         return null;
      else
      {
         ChainedHashNode<K, E> cursor = (ChainedHashNode<K, E>) table[index];
         
         //While cursor is not null or equal to the target element
         while((cursor != null) && (cursor.key != key))
            //move to the next link
            cursor = cursor.link;
            
         return (E) cursor.element;
      }
   }
}
// File: Table.java
// Complete documentation is available from the Table link in:
//   http://www.cs.colorado.edu/~main/docs


/******************************************************************************
* A <CODE>Table</CODE> is an open-address hash table with a fixed capacity.
* The purpose is to show students how an open-address hash table is
* implemented. Programs should generally use java.util.Hashtable
* rather than this hash table.
*
* <dt><b>Java Source Code for this class:</b><dd>
*   <A HREF="../../../../edu/colorado/collections/Table.java">
*   http://www.cs.colorado.edu/~main/edu/colorado/collections/Table.java
*   </A>
*
* @author Michael Main 
*   <A HREF="mailto:main@colorado.edu"> (main@colorado.edu) </A>
*
******************************************************************************/
public class TableDoubleHash< K , E >
{
   // Invariant of the Table class:
   //   1. The number of items in the table is in the instance variable manyItems.
   //   2. The preferred location for an element with a given key is at index
   //      hash(key). If a collision occurs, then next-Index is used to search
   //      forward to find the next open address. When an open address is found
   //      at an index i, then the element itself is placed in data[i] and the
   //      element�s key is placed at keys[i].
   //   3. An index i that is not currently used has data[i] and key[i] set to
   //      null.
   //   4. If an index i has been used at some point (now or in the past), then
   //      hasBeenUsed[i] is true; otherwise it is false.
   private int manyItems;
   private Object[ ] keys;
   private Object[ ] data;
   private boolean[ ] hasBeenUsed;   

   /**
   * Initialize an empty table with a specified capacity.
   * @param <CODE>capacity</CODE>
   *   the capacity for this new open-address hash table
   * <dt><b>Postcondition:</b><dd>
   *   This table is empty and has the specified capacity.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the specified capacity. 
   **/   
   public TableDoubleHash(int capacity)
   {
      // The manyItems instance variable is automatically set to zero.
      // which is the correct initial value. The three arrays are allocated to
      // be the specified capacity. The boolean array is automatically
      // initialized to falses, and the other two arrays are automatically
      // initialized to all null.
      if (capacity <= 0)
         throw new IllegalArgumentException("Capacity is negative");
      keys = new Object[capacity];
      data = new Object[capacity];
      hasBeenUsed = new boolean[capacity];
   }
   
   
   /**
   * Determines whether a specified key is in this table.
   * @param <CODE>key</CODE>
   *   the non-null key to look for
   * <dt><b>Precondition:</b><dd>
   *   <CODE>key</CODE> cannot be null.
   * @return
   *   <CODE>true</CODE? (if this table contains an object with the specified 
   *   key); <CODE>false</CODE> otherwise. Note that <CODE>key.equals( )</CODE>
   *   is used to compare the <CODE>key</CODE> to the keys that are in the 
   *   table.
   * @exception NullPointerException
   *   Indicates that <CODE>key</CODE> is null.
   **/
   public boolean containsKey(K key)
   {
      return findIndex(key) != -1;
   }
   
   
   private int findIndex(K key)
   // Postcondition: If the specified key is found in the table, then the return
   // value is the index of the specified key. Otherwise, the return value is -1.
   {
      int count = 0;
      int i = hash(key);
      
      while (count < data.length && hasBeenUsed[i])
      {
         if (key.equals(keys[i]))
            return i;
         count++;
         i = nextIndex(i, key);
      }
      
      return -1;
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
         return (E) data[index];
   }
   
   
   private int hash(Object key)
   // The return value is a valid index of the table�s arrays. The index is
   // calculated as the remainder when the absolute value of the key�s
   // hash code is divided by the size of the table�s arrays.
   {
      return Math.abs(key.hashCode( )) % data.length;
   }
   
   private int hash2(Object key)
   // The return value is a valid index of the table�s arrays. The index is
   // calculated as the remainder when the absolute value of the key�s
   // hash code is divided by the size of the table�s arrays - 2 + 1.
   {
      return 1 + (Math.abs(key.hashCode( )) % (data.length - 2));
   }
   
   
   private int nextIndex(int i, K key)
   // The return value is normally i + hash2 computation. But if the result  
   // is data.length, then the return value is zero instead.
   {
      int ind = (i + hash2(key)) % data.length;
      if (ind == data.length)
         return 0;
      else
         return ind;
   }
   
   
   /**
   * Add a new element to this table, using the specified key.
   * @param <CODE>key</CODE>
   *   the non-null key to use for the new element
   * @param <CODE>element</CODE>
   *   the new element that�s being added to this table
   * <dt><b>Precondition:</b><dd>
   *   If there is not already an element with the specified <CODE>key</CODE>,
   *   then this table�s size must be less than its capacity 
   *   (i.e., <CODE>size() < capacity()</CODE>). Also, neither <CODE>key</CODE>
   *   nor </CODE>element</CODE> is null.
   * <dt><b>Postcondition:</b><dd>
   *   If this table already has an object with the specified <CODE>key</CODE>,
   *   then that object is replaced by </CODE>element</CODE>. Otherwise, the new 
   *   </CODE>element</CODE> is added with the specified <CODE>key</CODE>.
   * @returns
   *   The number of collisions that occur during the attempt to place the object
   * @exception IllegalStateException
   *   Indicates that there is no room for a new object in this table.
   * @exception NullPointerException
   *   Indicates that <CODE>key</CODE> or <CODE>element</CODE> is null.   
   **/
   public int put(K key, E element)
   {
      int index = findIndex(key);
      int collisions = 0;
      E answer;
      
      if (index != -1)
      {  
         // The key is already in the table.
         collisions++
         answer = (E) data[index];
         data[index] = element;
         return collisions;
      }
      else if (manyItems < data.length)
      {  
         // The key is not yet in this Table.
         index = hash(key);
         
         //While hash maps to a used index
         while (keys[index] != null)
         {
            //Gather next index
            index = nextIndex(index, key);
            
            //Increase number of collisions
            collisions++;
         }
         keys[index] = key;
         data[index] = element;
         hasBeenUsed[index] = true;
         manyItems++;
         return collisions;
      }
      else
      {  // The table is full.
         throw new IllegalStateException("Table is full.");
      }
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
         answer = (E) data[index];
         keys[index] = null;
         data[index] = null;
	      manyItems--;
      }
      
      return answer;
   }
        
}
           
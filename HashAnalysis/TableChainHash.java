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
   // value is the index of the specified key. Otherwise, the return value is -1.
   {
      int count = 0;
      int i = hash(key);
      
      ChainedHashNode<K, E> cursor = null;
      
      while (count < table.length)
      {
         cursor = (ChainedHashNode<K, E>) table[i];   
         if (cursor != null && key.equals(cursor.key))
            return i;
         count++;
         i = nextIndex(i);
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
   
   public int put(K key, E element)
   {
      ChainedHashNode<K, E> cursor = null;
      int collisions = 0;
      
      if(key == null || element == null)
         throw new NullPointerException("Key or element is null");
      
      int index = findIndex(key);
      
      if(index != -1)
      {
         // The key is already in the table.
         cursor = (ChainedHashNode<K, E>) table[index];
         
         while((cursor != null) && (cursor.element != element))
            cursor = cursor.link;
      }
      
      if (cursor == null)
      {  
         int i = hash(key);
         cursor = new ChainedHashNode();
         cursor.element = element;
         cursor.key = key;
         cursor.link = (ChainedHashNode<K, E>) table[i];
         collisions += numLinks(cursor.link);
         table[i] = cursor;
      }
      else
      { 
         cursor.element = element;
      }
      
      return collisions;
   }
   
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
}
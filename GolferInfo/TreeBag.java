//Nate Wren
//Project 4
//11/29/2018


import java.util.ArrayList;

// File: TreeBag.java 

// The implementation of most methods in this file is left as a student
// exercise from Section 9.5 of "Data Structures and Other Objects Using Java"


/******************************************************************************
* This class is a homework assignment;
* An <CODE>TreeBag</CODE> is a collection of int numbers.
*
* <dl><dt><b>Limitations:</b> <dd>
*   Beyond <CODE>Integer.MAX_VALUE</CODE> elements, <CODE>countOccurrences</CODE>,
*   and <CODE>size</CODE> are wrong. 
*
* <dt><b>Note:</b><dd>
*   This file contains only blank implementations ("stubs")
*   because this is a Programming Project for my students.
*
* @version
*   Jan 24, 2016
******************************************************************************/
public class TreeBag<E extends Comparable> implements Cloneable
{
   // The Term E extends Comparable is letting the compiler know that any type
   // used to instantiate E must implement Comparable. i. e. that means that whatever
   // type E is must have a compareTo method so that elements can be compared against one another
   // This is required becuase we are doing comparisons in our methods


   // Invariant of the TreeBag class:
   //   1. The elements in the bag are stored in a binary search tree.
   //   2. The instance variable root is a reference to the root of the
   //      binary search tree (or null for an empty tree).
   private BTNode<E> root;   


   /**
   * Insert a new element into this bag.
   * @param <CODE>element</CODE>
   *   the new element that is being inserted
   * <dt><b>Postcondition:</b><dd>
   *   A new copy of the element has been added to this bag.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory a new BTNode.
   **/
   public void add(E element)
   {      
      if(root == null)
      {
         root = new BTNode(element, null, null);
      }
      else
      {
         recursiveAdd(element, root);
      }
   }
   
   private void recursiveAdd(E element, BTNode<E> n)
   {
      if(element.compareTo(n.getData()) <= 0)
      {
         if(n.getLeft() == null)
         {
            n.setLeft(new BTNode(element, null, null));
         }
         else
         {
            recursiveAdd(element, n.getLeft());
         }
      }
      else
      {
         if(n.getRight() == null)
         {
            n.setRight(new BTNode(element, null, null));
         }
         else
         {
            recursiveAdd(element, n.getRight());
         }
      }  
   }

   /**
   * Retrieve location of a specified element from this bag.
   * @param <CODE>target</CODE>
   *   the element to locate in the bag
   * @return 
   *  the return value is a reference to the found element in the tree
   * <dt><b>Postcondition:</b><dd>
   *   If <CODE>target</CODE> was found in the bag, then method returns
   *   a reference to a comparable element. If the target was not found then
   *   the method returns null.
   *   The bag remains unchanged.
   **/
   public E retrieve(E target)
   {
      if(root == null)
      {
         return null;
      }
      else
      {
         return recursiveSearch(target, root);
      }
   }
   
   //Helper function to retrieve the target
   private E recursiveSearch(E target, BTNode<E> n)
   {
      //Base case if target is not present in BST
      if(n == null)
      {
         return null;
      }
      else
      {
         //Base case if target is found in BST
         if(target.compareTo(n.getData()) == 0)
         {
            return n.getData();
         }
         //Recursive case
         else
         {
            //if the target is larger than the current         
            if(target.compareTo(n.getData()) > 0)
            {
               //Look to the right
               return recursiveSearch(target, n.getRight());
            }
            else
            {
               //Look to the left
               return recursiveSearch(target, n.getLeft());
            }
         }
      }
   }
   
   /**
   * Return ArrayList representation of the TreeBag
   * @return 
   *  ArrayList contanting the contents of the bag in order
   **/
   public ArrayList<E> getArray()
   {
      if(root == null)
      {
         return null;
      }
      else
      {
         int size = size();
         int middle = size / 2;
         ArrayList<E> arr = new ArrayList<E>();
         
         fill(arr, root);
         
         return arr;
      }
   }
   
   //Private helper method for conversion
   private void fill(ArrayList<E> arr, BTNode<E> node)
   {
      //In order traversal to insert to ArrayList reference parameter
      if (node.getLeft() != null)
         fill(arr, node.getLeft()); 
      arr.add(node.getData());
      if (node.getRight() != null)
         fill(arr, node.getRight());
   }    
   
   
   

   
   /**
   * Remove one copy of a specified element from this bag.
   * @param <CODE>target</CODE>
   *   the element to remove from the bag
   * <dt><b>Postcondition:</b><dd>
   *   If <CODE>target</CODE> was found in the bag, then one copy of
   *   <CODE>target</CODE> has been removed and the method returns true. 
   *   Otherwise the bag remains unchanged and the method returns false. 
   **/
   public boolean remove(E target)
   {
      boolean removed = false;
           
      //If the tree is not empty
      if(root != null)
      {
         //If the target to be removed is the root
         if(target.compareTo(root.getData()) == 0)
         {
            //If there is no left subtree
            if(root.getLeft() == null && root.getRight() != null)
            {
               //overwrite root with the right subtree
               root = root.getRight();
            }
            //If there is no right subtree
            else if(root.getLeft() != null && root.getRight() == null)
            {
               //overwrite root with the left subtree
               root = root.getLeft();
            }
            //If root has both left and right subtrees
            else
            {
               //Set the data of root to the rightmost value of the left subtree
               root.setData(root.getLeft().getRightmostData());
               //Get rid of duplicate value
               root.getLeft().removeRightmost();
            }
         }
         else
         {
            //call recursive function to find the target to remove
            removed = recursiveRemove(target, root, null);
         }
      }
      
      return removed;
   }
   
   //This is voodoo, I believe it works...
   private boolean recursiveRemove(E target, BTNode<E> current, BTNode<E> previous)
   {
      //Base case if the target does not exist in tree
      if(current == null)
      {
         return false;
      }
      else
      {
         //Value of the comparison
         int comparison = target.compareTo(current.getData());
         
         //Base case if the target is the current node
         if(comparison == 0)
         {
            //If the target is a leaf
            if(current.isLeaf())
            {
               //Set corresponding value of the previous node to null
               if(current == previous.getLeft())
               {
                  previous.setLeft(null);
               }
               else
               {
                  previous.setRight(null);
               }
            }
            //If the target has children
            else
            {
               //If the target has a right subtree, but not left
               if(current.getLeft() == null && current.getRight() != null)
               {
                  //Shift parents corresponding link to the right node
                  if(current == previous.getLeft())
                  {  
                     previous.setLeft(current.getRight());
                  }
                  else
                  {
                     previous.setRight(current.getRight());
                  }
               }
               //If the target has a left subtree but not right
               else if(current.getLeft() != null && current.getRight() == null)
               {
                  //shift parents corresponding link to the left node
                  if(current == previous.getLeft())
                  {  
                     previous.setLeft(current.getLeft());
                  }
                  else
                  {
                     previous.setRight(current.getLeft());
                  }
               }
               //If the target has a left and right subtree
               else
               {
                  //Swap corresponding link in the parent with the rightmost data of the left subtree
                  //Remove duplicate data from the sub tree
                  if(current == previous.getLeft())
                  {
                     previous.getLeft().setData(current.getRightmostData());
                     current.removeRightmost();
                  }
                  else
                  {
                     previous.getRight().setData(current.getRightmostData());
                     current.removeRightmost();
                  }
               }
                  
            }
           
            return true;
         }
         //Recursive case if the target is less than the current node
         else if(comparison < 0)
         {
            //Check left subtree
            return recursiveRemove(target, current.getLeft(), current);
         }
         //Recursive case if the target is greater than the current node
         else
         {
            //check right subtree
            return recursiveRemove(target, current.getRight(), current);
         }
      }
   }
   
   /**
   * Displays the entire tree of Node elements in a order specified
   * by the elements compareTo method
   * 
   * @param 
   *   none
   * <dt><b>Postcondition:</b><dd>
   *   Outputs all elements in the tree to Screen.
   *   Does not change the structure 
   **/
   public void display()
   {
      root.inorderPrint();
      
   } 
     
   /**
   * Displays the entire tree of Node elements using the
   * built in print method of BTNode
   * which displays the entire tree in tree format
   * 
   * @param 
   *   none
   * <dt><b>Postcondition:</b><dd>
   *   Outputs all elements in the tree to Screen.
   *   Does not change the structure 
   **/   
   public void displayAsTree() {
      root.print(0);
   }
      
   
   
   /**
   * Generate a copy of this bag.
   * @param - none
   * @return
   *   The return value is a copy of this bag. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to an <CODE>TreeBag</CODE> before it can be used.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
   public TreeBag<E> clone( )
   {  // Clone an IntTreeBag object.
      // Student will replace this return statement with their own code:
      return null; 
   } 

   /**
   * Accessor method to count the number of occurrences of a particular element
   * in this bag.
   * @param <CODE>target</CODE>
   *   the element that needs to be counted
   * @return
   *   the number of times that <CODE>target</CODE> occurs in this bag
   **/
   public int countOccurrences(E target)
   {
      // Student will replace this return statement with their own code:
      return 0;
   }
   
       
   /**
   * Determine the number of elements in this bag.
   * @param - none
   * @return
   *   the number of elements in this bag
   **/                           
   public int size( )
   {
      return BTNode.treeSize(root);
   }




   /**
   * Add the contents of another bag to this bag.
   * @param <CODE>addend</CODE>
   *   a bag whose contents will be added to this bag
   * <dt><b>Precondition:</b><dd>
   *   The parameter, <CODE>addend</CODE>, is not null.
   * <dt><b>Postcondition:</b><dd>
   *   The elements from <CODE>addend</CODE> have been added to this bag.
   * @exception IllegalArgumentException
   *   Indicates that <CODE>addend</CODE> is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory to increase the size of the bag.
   **/
   public void addAll(TreeBag<E> addend)
   {
      // Implemented by student.
   }
   
   /**
   * Create a new bag that contains all the elements from two other bags.
   * @param <CODE>b1</CODE>
   *   the first of two bags
   * @param <CODE>b2</CODE>
   *   the second of two bags
   * <dt><b>Precondition:</b><dd>
   *   Neither b1 nor b2 is null.
   * @return
   *   the union of b1 and b2
   * @exception IllegalArgumentException
   *   Indicates that one of the arguments is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the new bag.
   **/   
   public static TreeBag union(TreeBag b1, TreeBag b2)
   {
      // Student will replace this return statement with their own code:
      return null;
   }
      
}
           

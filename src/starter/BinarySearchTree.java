package starter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

//BinarySearchTree class
//
//CONSTRUCTION: with no initializer
//
//******************PUBLIC OPERATIONS*********************
//void insert( x )       --> Insert x
//void remove( x )       --> Remove x
//boolean contains( x )  --> Return true if x is present
//Comparable findMin( )  --> Return smallest item
//Comparable findMax( )  --> Return largest item
//boolean isEmpty( )     --> Return true if empty; else false
//void makeEmpty( )      --> Remove all items
//void printTree( )      --> Print tree in sorted order
//******************ERRORS********************************
//Throws UnderflowException as appropriate

/**
 * I used Eclipse 4.10.0 with Java 1.8.0_191 on my Mac PC to build and execute my program.
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * Original code by Mark Allen Weiss
 * @author Jade Zeng
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
 /**
  * Construct the tree.
  */
 public BinarySearchTree( )
 {
     root = null;
 }

 /**
  * Insert into the tree; duplicates are ignored.
  * @param x the item to insert.
  */
 public void insert( AnyType x )
 {
     root = insert( x, root );
 }

 /**
  * Remove from the tree. Nothing is done if x is not found.
  * @param x the item to remove.
  */
 public void remove( AnyType x )
 {
     root = remove( x, root );
 }

 /**
  * Find the smallest item in the tree.
  * @return smallest item or null if empty.
  */
 public AnyType findMin( )
 {
     if( isEmpty( ) )
         throw new UnderflowException( );
     return findMin( root ).element;
 }

 /**
  * Find the largest item in the tree.
  * @return the largest item of null if empty.
  */
 public AnyType findMax( )
 {
     if( isEmpty( ) )
         throw new UnderflowException( );
     return findMax( root ).element;
 }

 /**
  * Find an item in the tree.
  * @param x the item to search for.
  * @return true if not found.
  */
 public boolean contains( AnyType x )
 {
     return contains( x, root );
 }

 /**
  * Make the tree logically empty.
  */
 public void makeEmpty( )
 {
     root = null;
 }

 /**
  * Test if the tree is logically empty.
  * @return true if empty, false otherwise.
  */
 public boolean isEmpty( )
 {
     return root == null;
 }

 /**
  * Print the tree contents in sorted order.
  */
 public void printTree( )
 {
     if( isEmpty( ) )
         System.out.println( "Empty tree" );
     else
         printTree( root );
 }

 /**
  * Internal method to insert into a subtree.
  * @param x the item to insert.
  * @param t the node that roots the subtree.
  * @return the new root of the subtree.
  */
 private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
 {
     if( t == null )
         return new BinaryNode<>( x, null, null );
     
     int compareResult = x.compareTo( t.element );
         
     if( compareResult < 0 )
         t.left = insert( x, t.left );
     else if( compareResult > 0 )
         t.right = insert( x, t.right );
     else
         ;  // Duplicate; do nothing
     return t;
 }

 /**
  * Internal method to remove from a subtree.
  * @param x the item to remove.
  * @param t the node that roots the subtree.
  * @return the new root of the subtree.
  */
 private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
 {
     if( t == null )
         return t;   // Item not found; do nothing
         
     int compareResult = x.compareTo( t.element );
         
     if( compareResult < 0 )
         t.left = remove( x, t.left );
     else if( compareResult > 0 )
         t.right = remove( x, t.right );
     else if( t.left != null && t.right != null ) // Two children
     {
         t.element = findMin( t.right ).element;
         t.right = remove( t.element, t.right );
     }
     else
         t = ( t.left != null ) ? t.left : t.right;
     return t;
 }

 /**
  * Internal method to find the smallest item in a subtree.
  * @param t the node that roots the subtree.
  * @return node containing the smallest item.
  */
 private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
 {
     if( t == null )
         return null;
     else if( t.left == null )
         return t;
     return findMin( t.left );
 }

 /**
  * Internal method to find the largest item in a subtree.
  * @param t the node that roots the subtree.
  * @return node containing the largest item.
  */
 private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
 {
     if( t != null )
         while( t.right != null )
             t = t.right;

     return t;
 }

 /**
  * Internal method to find an item in a subtree.
  * @param x is item to search for.
  * @param t the node that roots the subtree.
  * @return node containing the matched item.
  */
 private boolean contains( AnyType x, BinaryNode<AnyType> t )
 {
     if( t == null )
         return false;
         
     int compareResult = x.compareTo( t.element );
         
     if( compareResult < 0 )
         return contains( x, t.left );
     else if( compareResult > 0 )
         return contains( x, t.right );
     else
         return true;    // Match
 }

 /**
  * Internal method to print a subtree in sorted order.
  * @param t the node that roots the subtree.
  */
 private void printTree( BinaryNode<AnyType> t )
 {
     if( t != null )
     {
         printTree( t.left );
         System.out.println( t.element );
         printTree( t.right );
     }
 }

 /**
  * Internal method to compute height of a subtree.
  * @param t the node that roots the subtree.
  */
 private int height( BinaryNode<AnyType> t )
 {
     if( t == null )
         return -1;
     else
         return 1 + Math.max( height( t.left ), height( t.right ) );    
 }
 
 /**
  * Basic node stored in unbalanced binary search trees.
  */
 
 private static class BinaryNode<AnyType>
 {
         // Constructors
     BinaryNode( AnyType theElement )
     {
         this( theElement, null, null );
     }

     BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
     {
         element  = theElement;
         left     = lt;
         right    = rt;
     }

     AnyType element;            // The data in the node
     BinaryNode<AnyType> left;   // Left child
     BinaryNode<AnyType> right;  // Right child
 }

 /**
  * Given a number N, creates a list L of N unique random integers. 
  * The value for N will be passed in through a command line parameter.
  * @param N the random number 
  * @return the list of random integers
  */
 private static List<Integer> generateRandomNums(int N) {
 	Random ran = new Random();
 	System.out.print("Generated: ");
 	List<Integer> result = new ArrayList<>();
 	while (result.size() < N) {
 		int i = ran.nextInt(N + 1);
 		if (!result.contains(i)) {
 			result.add(i);
 		}
 	}
 	
 	for (Integer i : result) {
 		System.out.print(i + " ");
 	}
 	System.out.println();
 	return result;
 }
 
 /**
  * sort the list of random number
  * @param list the list of random integers
  */
 private static void sortRandomNumList(List<Integer> list) {
 	System.out.print("Sorted: ");
 	List<Integer> sortedList = new ArrayList<Integer>(list); 
 	Collections.sort(sortedList);
 	for (Integer i : sortedList) {
 		System.out.print(i + " ");
 	}
 	System.out.println();
 }

 /**
  * Help method to print the tree
  * @param t the node that roots the subtree.
  */
 private void printTree2( BinaryNode<AnyType> t )
 {
     if( t != null )
     {
         printTree2( t.left );
         if (t.left != null)
         	System.out.println( t.element + " -> " + t.left.element);
         if (t.right != null)
         	System.out.println( t.element + " -> " + t.right.element);
         printTree2( t.right );
     }
 }
 
 /**
  * Internal method to print a subtree in sorted order.
  */
 public void printTree2( )
 {
     if( isEmpty( ) )
         System.out.println( "Empty tree" );
     else {
     	System.out.print("Graphviz: \n");
         printTree2( root );
     }
 }
 
 /**
  * Internal method to print a subtree in Level order.
  */
 public void printTreeLevelOrder( )
 {
     if( isEmpty( ) )
         System.out.println( "Empty tree" );
     else {
     	System.out.print("Level order: ");
     	Queue<BinaryNode> queue = new LinkedList<>();
     	queue.add(root);
     	while (!queue.isEmpty()) {
     		BinaryNode node = queue.poll(); // remove the head
     		System.out.print(node.element + " ");
     		if (node.left != null) {
     			queue.add(node.left);
     		}
     		if (node.right != null) {
     			queue.add(node.right);
     		}
     	}
     }
 }
 
/** The tree root. */
 private BinaryNode<AnyType> root;
/**
 * Internal method to print a root.
 */
 public void printRoot() {
 	System.out.println(root.element);
 	if (root.left != null) System.out.println(root.left.element);
 	if (root.right != null) System.out.println(root.right.element);
 }
 
 /**
  * Internal method to print the final result.
  * @param arg A parameter that determines the value of variable N.
  */
 private static void printOutResult(String arg) {
 	List<Integer> randumNumList = generateRandomNums(Integer.parseInt(arg));
 	sortRandomNumList(randumNumList);
 	
     BinarySearchTree<Integer> t = new BinarySearchTree<>( );
     
     for (Integer i : randumNumList) {
     	t.insert(i);
     }
     
     t.printTree2( );
     
     t.printTreeLevelOrder( );
 }
 

/**
 * the main method to test the required features
 * @param args A parameter that determines the value of variable N.
 */
 public static void main( String [ ] args )
 {
 	List<Integer> randumNumList = generateRandomNums(Integer.parseInt(args[0]));
 	sortRandomNumList(randumNumList);
 	
     BinarySearchTree<Integer> t = new BinarySearchTree<>( );
     
     for (Integer i : randumNumList) {
     	t.insert(i);
     }
     
     t.printTree2( );
     
     //t.printRoot();
     t.printTreeLevelOrder( );
     
     System.out.println();
     for (int i = 1; i <= 3; i++) {
     	System.out.println("\nPermutation " + i + " : ");
     	printOutResult(args[0]);
     	System.out.println();
     }
     
     /**
     final int NUMS = 4000;
     final int GAP  =   37;

     System.out.println( "Checking... (no more output means success)" );

     for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
         t.insert( i );

     for( int i = 1; i < NUMS; i+= 2 )
         t.remove( i );

     if( NUMS < 40 )
         t.printTree( );
     
     if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
         System.out.println( "FindMin or FindMax error!" );

     for( int i = 2; i < NUMS; i+=2 )
          if( !t.contains( i ) )
              System.out.println( "Find error1!" );

     for( int i = 1; i < NUMS; i+=2 )
     {
         if( t.contains( i ) )
             System.out.println( "Find error2!" );
     }
     **/
 }
}

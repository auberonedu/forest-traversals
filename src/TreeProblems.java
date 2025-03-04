import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TreeProblems {

  /*
   postOrder (Node Version)
   -----------
   Given the root of a tree print out the values of the nodes in post-order.
   Print each value on a separate line.

   Example:
   If the tree is:
          5
       /  |  \
      3   9   8
        / | \  
       4  1  2
   The output should be:
   3
   4
   1
   2
   9
   8
   5

   If the root is null, do nothing.
   */

   // left right root
  public static <T> void postOrder(Node<T> root) {
    // if null return nothing
    if(root == null || root.children == null) return;

    // this grabs the child of each 'root' [which is the list of elements]
    // it then puts it into this loop below to navigatate itteratively until the bottom 
  
    for(var child : root.children){ // moving down to the very bottom in order that it came in [the bottom of the first child, then the next etc.]
      postOrder(child);
    }
    //the it prints out from the bottom and then works it way back up.
    System.out.println(root.value);
  }

  /*
   postOrder (Node Version)
   -----------
   Given the root of a tree print out the values of the nodes in post-order.
   Print each value on a separate line.
   If the tree is null or does not contain the root, do nothing.

   Example:
   For a tree represented as:
     5 -> [3, 9, 8]
     3 -> []
     9 -> [4, 1, 2]
     4 -> []
     1 -> []
     2 -> []
   The output should be:
   3
   4
   1
   2
   9
   8
   5
   */
  public static <T> void postOrder(Map<T, List<T>> tree, T root) {
    if(tree == null || !tree.containsKey(root)){
      return;
    }

    // Access the list of children for the current root node and recursively process each child 
    for(var child : tree.get(root)){
      postOrder(tree, child);
    }

    System.out.println(root);

  }


  /*
   sumTree (Node Version)
   -----------
   Given a tree built with the Node class (with integer values), compute and return the sum of all the node values.
   Example:
   If the tree is:
          5
       /  |  \
      3   9   8
        / | \  
       4  1  2
   then the method should return 32.
   A null tree should return 0
  */
  public static int sumTree(Node<Integer> root) {
        // if null return nothing
      if(root == null) return 0;

        // this grabs the child of each 'root' [which is the list of elements]
        // it then puts it into this loop below to navigatate itteratively until the bottom 
        int sum = root.value; 

      for(Node<Integer> child : root.children){ // moving down to the very bottom in order that it came in [the bottom of the first child, then the next etc.]
          sum += sumTree(child);
      }
        //the it prints out from the bottom and then works it way back up.
      return sum;
  }

  /*
   sumTree (Map Version)
   -----------
   Given a tree represented as a map (where every node appears as a key and leaf nodes map to an empty list),
   compute the sum of all nodes.
   Example:
   For a tree represented as:
     5 -> [3, 9, 8]
     3 -> []
     9 -> [4, 1, 2]
     4 -> []
     1 -> []
     2 -> []
   the method should return 32.

   A null tree should return 0

   Hint: There's a simple way to do this!
  */
  public static int sumTree(Map<Integer, List<Integer>> tree) {
    if(tree == null){
      return 0;
    }

    int sum = 0;

    //wow. Kind of anti climactic
    for(var key : tree.keySet()){
      sum += key;
    }






    return sum;
  }

  /*
   findRoot
   -----------
   Given a tree represented as a map where each key is a parent node and the value is a list of its children,
   find the root of the tree. The root is the node with no parents.
   Example:
   If the tree is represented as:
     20 -> [40]
     8  -> []
     30 -> []
     10 -> [20, 30, 99]
     40 -> []
     99 -> [8]
   then the method should return 10.

  You can assume the tree is non-null and well-formed.

   Hint: No recursion needed! Think about how you would do this by hand.
  */
  public static <T> T findRoot(Map<T, List<T>> tree) {
    Set<T> root = new HashSet<T>(tree.keySet());
    Set<T> nonRoots = new HashSet<>();
   
    
    for(var child : tree.values()){
      //addAll to add mult things to the collection
      nonRoots.addAll(child);

    }

    //DO REMOVE ALL 
    root.removeAll(nonRoots);
  
    return root.iterator().next();
  
    
    // remember - a key can never be a child. 
    // 20 -> [40]
    //  8  -> []
    //  30 -> []
    //  10 -> [20, 30, 99]
    //  40 -> []
    //  99 -> [8]
    // 4 - > [20]

    //my code essentially puts all the keys into a set
    // then puts all the children into another
    // then removes all children from the key set (root)
    // and if there is anything left that HAS to be the root 
  }

  /*
   maxDepth (Node Version)
   -----------
   Compute the maximum depth of a tree using the Node class. The depth is the number of nodes along
   the longest path from the root down to the farthest leaf. The root is at depth 1. If the tree is null, return 0.
   Example:
   For a tree structured as:
          A
       /  |  \
      B   E   C
      |      / \
      E     D   Q
             \ 
              Z
   the maximum depth is 4.

   
  */
  public static <T> int maxDepth(Node<T> root) {

    if(root == null) return 0;

    if(root.children.isEmpty()){

      return 1;

    }

    int maxChildDepth = 0;

    for (Node<T> child : root.children) {

      maxChildDepth = Math.max(maxChildDepth, maxDepth(child));

    }

    return 1 + maxChildDepth;
  }

  /*
   maxDepth (Map Version)
   -----------
   Compute the maximum depth of a tree using the Node class. The depth is the number of nodes along
   the longest path from the root down to the farthest leaf. The root is at depth 1. If the tree is null, return 0.
   Example:
   For a tree structured as:
    A -> [B, E, C]
    B -> [E]
    E -> []
    C -> [D, Q]
    D -> [Z]
    Q -> []
    Z -> []
   the maximum depth is 4 (A->C->D->Z).

   Hint: Use findRoot to start. Then, make a recursive helper method.
  */
  public static int maxDepth(Map<String, List<String>> tree) {
    if(tree == null) return 0;
    var root = findRoot(tree);

    // now I want to traverse it down 
    if(root ==null) return 0;

    return maxDepthHelper(tree, root);
  }

  private static int maxDepthHelper(Map<String, List<String>> tree, String root){
    if(!tree.containsKey(root) || tree.get(root).isEmpty() || tree == null){
      return 1;
    }

    int maxDepthOfChildren = 0;

    for (var child : tree.get(root)){
      // this is going to be traversing
      // Math.max - catches the deepest branch no matter what.
      maxDepthOfChildren = Math.max(maxDepthOfChildren, maxDepthHelper(tree, child));
    }

    //at the end of each revursive call - it adds plus one. This how it increment
    return 1 + maxDepthOfChildren;
  }
  
}

package skiplist;
public class Node {
	  Node up;
	  Node down;
	  Node left;
	  Node right;
	  int key;

	  Node(int value) {
	    this.key = value;
	    this.right = null;
	    this.left = null;
	    this.down = null;
	    this.up = null;
	  }
}
package RedBlackTree;


public class Node
{
	int data;
	Node left;
	Node right;
	char colour;
	Node parent;

	Node(int data)
	{
		super();
		this.data = data; // only including data. not key
		this.left = null; // left subtree
		this.right = null; // right subtree
		this.colour = 'R'; // colour . either 'R' or 'B'
		this.parent = null; // required at time of rechecking.
	}
}
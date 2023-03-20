package RedBlackTree;

import java.io.*;
import java.util.Scanner;

public class RedBlackTree
{
	public Node root;//root node
	public RedBlackTree()
	{
		super();
		root = null;
	}
	// this function performs left rotation
	Node rotateLeft(Node node)
	{
		Node x = node.right;
		Node y = x.left;
		x.left = node;
		node.right = y;
		node.parent = x; // parent resetting is also important.
		if(y!=null)
			y.parent = node;
		return(x);
	}
	//this function performs right rotation
	Node rotateRight(Node node)
	{
		Node x = node.left;
		Node y = x.right;
		x.right = node;
		node.left = y;
		node.parent = x;
		if(y!=null)
			y.parent = node;
		return(x);
	}


	
	

	boolean ll = false;
	boolean rr = false;
	boolean lr = false;
	boolean rl = false;
	Node insertHelp(Node root, int data)
	{
		boolean f=false;
		
		if(root==null)
			return(new Node(data));
		else if(data<root.data)
		{
			root.left = insertHelp(root.left, data);
			root.left.parent = root;
			if(root!=this.root)
			{
				if(root.colour=='R' && root.left.colour=='R')
					f = true;
			}
		}
		else
		{
			root.right = insertHelp(root.right,data);
			root.right.parent = root;
			if(root!=this.root)
			{
				if(root.colour=='R' && root.right.colour=='R')
					f = true;
			}
		
		}

		if(this.ll) 
		{
			root = rotateLeft(root);
			root.colour = 'B';
			root.left.colour = 'R';
			this.ll = false;
		}
		else if(this.rr) 
		{
			root = rotateRight(root);
			root.colour = 'B';
			root.right.colour = 'R';
			this.rr = false;
		}
		else if(this.rl) 
		{
			root.right = rotateRight(root.right);
			root.right.parent = root;
			root = rotateLeft(root);
			root.colour = 'B';
			root.left.colour = 'R';

			this.rl = false;
		}
		else if(this.lr) 
		{
			root.left = rotateLeft(root.left);
			root.left.parent = root;
			root = rotateRight(root);
			root.colour = 'B';
			root.right.colour = 'R';
			this.lr = false;
		}
		
		if(f)
		{
			if(root.parent.right == root) 
			{
				if(root.parent.left==null || root.parent.left.colour=='B') 
				{
					if(root.left!=null && root.left.colour=='R')
						this.rl = true;
					else if(root.right!=null && root.right.colour=='R')
						this.ll = true;
				}
				else 
				{
					root.parent.left.colour = 'B';
					root.colour = 'B';
					if(root.parent!=this.root)
						root.parent.colour = 'R';
				}
			}
			else
			{
				if(root.parent.right==null || root.parent.right.colour=='B')
				{
					if(root.left!=null && root.left.colour=='R')
						this.rr = true;
					else if(root.right!=null && root.right.colour=='R')
						this.lr = true;
				}
				else
				{
					root.parent.right.colour = 'B';
					root.colour = 'B';
					if(root.parent!=this.root)
						root.parent.colour = 'R';
				}
			}
			f = false;
		}
		return(root);
	}

	public void insert(int data)
	{
		if(this.root==null)
		{
			this.root = new Node(data);
			this.root.colour = 'B';
		}
		else
			this.root = insertHelp(this.root,data);
	}
	void inorderTraversalHelper(Node node)
	{
		if(node!=null)
		{
			inorderTraversalHelper(node.left);
			System.out.printf("%d ", node.data);
			inorderTraversalHelper(node.right);
		}
	}

	public void inorderTraversal()
	{
		inorderTraversalHelper(this.root);
	}
	
	public boolean search(int data) {
        Node current = root;
        while (current != null) {
            if (data == current.data) {
                return true;
            } else if (data < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

	public int min() {
        if (root == null) throw new IllegalStateException("Tree is empty");
        Node node = root;
        while (node.left != null) node = node.left;
        return node.data;
    }

    public int max() {
        if (root == null) throw new IllegalStateException("Tree is empty");
        Node node = root;
        while (node.right != null) node = node.right;
        return node.data;
    }
    public int successor(int key) {
        Node node = search(root, key);
        if (node == null) throw new IllegalArgumentException("Key not found");

        if (node.right != null) {
            node = node.right;
            while (node.left != null) node = node.left;
            return node.data;
        } else {
            Node parent = node.parent;
            while (parent != null && node == parent.right) {
                node = parent;
                parent = parent.parent;
            }
            return parent.data;
        }
    }
    int height(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }
    private Node search(Node node, int key) {
        if (node == null) return null;
        if (key < node.data) return search(node.left, key);
        else if (key > node.data) return search(node.right, key);
        else return node;
    }
    public int predecessor(int key) {
        Node node = search(root, key);
        if (node == null) throw new IllegalArgumentException("Key not found");

        if (node.left != null) {
            node = node.left;
            while (node.right != null) node = node.right;
            return node.data;
        } else {
            Node parent = node.parent;
            while (parent != null && node == parent.left) {
                node = parent;
                parent = parent.parent;
            }
            return parent.data;
        }
    }
    void printTreeHelper(Node root, int space)
    {
        int i;
        if(root != null)
        {
            space = space + 10;
            printTreeHelper(root.right, space);
            System.out.printf("\n");
            for ( i = 10; i < space; i++)
            {
                System.out.printf(" ");
            }
            System.out.printf("%d(%s)", root.data, root.colour);
            System.out.printf("\n");
            printTreeHelper(root.left, space);
        }
    }

    Node deleteRec(Node root, int key)
    {
        if (root == null)
            return root;
 
        if (key < root.data)
            root.left = deleteRec(root.left, key);
        else if (key > root.data)
            root.right = deleteRec(root.right, key);
 
       
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
 
          
            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data);
        }
 
        return root;
    }
    int minValue(Node root)
    {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }
    public void printTree()
    {
        printTreeHelper(this.root, 0);
    }
}

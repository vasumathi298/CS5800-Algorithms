package skiplist;

import java.util.Random;

public class SkipList {

	private Node head;
	private Node tail;
	
	private final int MIN = Integer.MIN_VALUE;
	private final int MAX = Integer.MAX_VALUE;
	
	private int height=0;
	public Random r= new Random();
	
	public SkipList() {
		head = new Node(MIN);
		tail = new Node(MAX);
		head.right= tail;
		tail.left = head;		
	}
	
	public Node skipListSearch(int key) {
		Node temp=head;
		while (temp.down != null) {
			temp = temp.down;
			
			while(key >= temp.right.key) {
				temp=temp.right;						
			}
		}
		return temp;
	}
	public Node skipListInsert(int key) {
		Node position = skipListSearch(key);
		Node temp;
		
		int level=1;
		int numOfHeads = -1;
		if(position.key == key) {
			return position;
		}
		do {
			System.out.println("Inside insert");
			numOfHeads++;
			level++;
			increaseLevel(level);
			
			temp = position;
			while(position.up == null) {
				position = position.left;
			}
			position =position.up;
			temp = insertAfterAbove (position,temp,key);
		}while(r.nextBoolean() == true);
		return temp;
	}
	public  Node remove(int key) {
		Node nodeToBeRemoved =skipListSearch(key);
		if(nodeToBeRemoved.key !=key) {
			return null;
		}
		removeRefToNode(nodeToBeRemoved);
		while(nodeToBeRemoved!= null) {
			removeRefToNode(nodeToBeRemoved);
			if(nodeToBeRemoved.up!=null)
				nodeToBeRemoved= nodeToBeRemoved.up;
			else
				break;
		}
		return nodeToBeRemoved;
	}
	
	private void removeRefToNode(Node nodeToBeRemoved) {
		Node afterNode = nodeToBeRemoved.right;
		Node beforeNode= nodeToBeRemoved.left;
		beforeNode.right= afterNode;
		afterNode.left = beforeNode;
	}

	private Node insertAfterAbove(Node position, Node q, int key) {

		Node newNode = new Node(key);
		Node nodeBeforeNewNode = position.down.down;
		setLeftAndRightRef(q, newNode);
		setUpAndDownRef(position, key, newNode, nodeBeforeNewNode);
		return newNode;
	}

	private void setUpAndDownRef(Node position, int key, Node newNode, Node nodeBeforeNewNode) {
		if(nodeBeforeNewNode != null) {
			while(true) {
				if(nodeBeforeNewNode.right.key != key)
					nodeBeforeNewNode=nodeBeforeNewNode.right;
				else
					break;
			}
			newNode.down =  nodeBeforeNewNode.right;
			nodeBeforeNewNode.right.up = newNode;
		}
		if(position != null) {
			if(position.right.key == key)
				newNode.up = position.right;
		}
	}

	private void setLeftAndRightRef(Node q, Node newNode) {
		newNode.right= q.right;
		newNode.left = q;
		q.right.left= newNode;
		q.right = newNode;		
	}

	private void increaseLevel(int level) {
		if(level >= height) {
			height++;
			addEmptyLevel();
		}
	}
	/**
	 * 
	 * Level 1 headNode--------------------> tailNode
	 * Level 0 head -----------------------> tail
	 * 
	 */
	private void addEmptyLevel() {
		Node headNode= new Node(MIN);
		Node tailNode= new Node(MAX);
		headNode.right= tailNode;
		headNode.down = head;
		tailNode.left= headNode;
		tailNode.down = tail;
		
		head.up = headNode;
		tail.up= tailNode;
		
		head= headNode;
		tail= tailNode;
	}
	
	void printSkipList() {
		StringBuilder sb= new StringBuilder();
		sb.append("\nSkipList from top left to bottem right\n");
		Node begin = head;
		Node highestLevel = begin;
		int level = height;
		
		while(highestLevel != null) {
			sb.append("\nLevel: "+ level + "\n");
			
			while(begin !=null) {
				if(begin.key != MIN && begin.key !=MAX)
					sb.append(begin.key);
				if(begin.right != null & begin.key != MIN && begin.key !=MAX)
					sb.append(" : ");
				begin = begin.right;
			}
			highestLevel= highestLevel.down;
			begin = highestLevel;
			level --;
		}
		System.out.println(sb.toString());
	}
}

package skiplist;

import java.util.Scanner;

public class SkipListMain{
	public static void main(String[] args) {
	    SkipList s = new SkipList();
	    Scanner inp = new Scanner(System.in);
	    while (true) {
	      System.out.println("Input:\n 1.Insert into SkipList \n 2.LookUp into SkipList \n 3.Delete from SkipList \n 4.Print in SkipList");
	      int n = inp.nextInt();
	      switch (n) {
	        case 1:
	          System.out.println("Enter key to insert:");
	          s.skipListInsert(inp.nextInt());
	          break;
	        case 2:
	          System.out.println("Enter key to lookup:");
	          int search= inp.nextInt();
	          System.out.println(s.skipListSearch(search).key == search ? "Key found" : "Key not found");
	          break;
	        case 3:
	          System.out.println("Enter key to delete:");
	          s.remove(inp.nextInt());
	          break;
	        case 4:
	        	s.printSkipList();
	        	break;
	      }
	    }
	  }

}

package RedBlackTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RedBlackTreeMain {
	public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        try {
            File inputFile = new File("C:\\Users\\User\\eclipse-workspace\\dummy\\src\\dummy\\input.txt");
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextInt()) {
                int key = scanner.nextInt();
                tree.insert(key);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("The tree after insertion");
        tree.printTree();
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a command (insert, delete, sort, search, min, max, successor, predecessor, display):");
            String command = inputScanner.nextLine().trim();

            switch (command) {
                case "insert":
                    System.out.print("Enter a key to insert: ");
                    int key = inputScanner.nextInt();
                    inputScanner.nextLine(); // consume newline character
                    tree.insert(key);
                    break;
                case "sort":
                    System.out.println("Sorted order:");
                    tree.inorderTraversal();
                    break;
                case "search":
                    System.out.print("Enter a key to search for: ");
                    key = inputScanner.nextInt();
                    inputScanner.nextLine(); // consume newline character
                    boolean found = tree.search(key);
                    if (found) System.out.println(key + " was found in the tree.");
                    else System.out.println(key + " was not found in the tree.");
                    break;
                case "min":
                    System.out.println("Minimum key: " + tree.min());
                    break;
                case "max":
                    System.out.println("Maximum key: " + tree.max());
                    break;
                case "successor":
                    System.out.print("Enter a key to find the successor of: ");
                    key = inputScanner.nextInt();
                    inputScanner.nextLine(); // consume newline character
                    try {
                        int successor = tree.successor(key);
                        System.out.println("Successor of " + key + ": " + successor);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "predecessor":
                    System.out.print("Enter a key to find the predecessor of: ");
                    key = inputScanner.nextInt();
                    inputScanner.nextLine(); // consume newline character
                    try {
                        int predecessor = tree.predecessor(key);
                        System.out.println("Predecessor of " + key + ": " + predecessor);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "delete":
                	System.out.print("Enter a key to delete from the tree: ");
                    key = inputScanner.nextInt();
                    inputScanner.nextLine(); // consume newline character
                    try {
                      tree.deleteRec(tree.root,key);
                      System.out.println("The key was deleted from the tree");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "display":
                	System.out.println("*******RED BLACK TREE*********");
                	tree.printTree();
                	break;
                default:
                    System.out.println("Invalid command.");
                    break;
            }

            System.out.println("Tree height: " + tree.height(tree.root));
            
        }
    }
}

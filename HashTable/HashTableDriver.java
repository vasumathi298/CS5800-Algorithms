package HashTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HashTableDriver {
	public static TextHash loadFileToHashTable(TextHash hashTable ) throws FileNotFoundException {
		 File file = new File("C:\\Users\\User\\eclipse-workspace\\HashTable\\input\\Alice_in_wonderland.txt");
	        BufferedReader br = new BufferedReader(new FileReader(file));
	        String st;
	        StringBuilder allWords = new StringBuilder();
	        try {
				while ((st = br.readLine()) != null) {
				  allWords.append("\n").append(st);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        for(String k: allWords.toString().toLowerCase().split("[ ,.?!\"\\[\\]()'-_\n#&%$]")) {
	          if(!k.isEmpty()) {
	            if(hashTable.find(k)!=-1) {
	            	hashTable.increase(k);
	            }
	            else {
	            	hashTable.insert(k, 1);
	            }
	          }
	        }
	        return hashTable;
	}
    public static void main(String[] args) throws IOException {
        TextHash hashTable_30 = new TextHash(30);
        hashTable_30=loadFileToHashTable(hashTable_30);
       
        TextHash hashTable_300 = new TextHash(300);
        hashTable_300=loadFileToHashTable(hashTable_300);
        TextHash hashTable_1000 = new TextHash(1000);
        hashTable_1000=loadFileToHashTable(hashTable_1000);
        
        PrintWriter writerhistogram_30 = new PrintWriter(new File("C:\\Users\\User\\eclipse-workspace\\HashTable\\output\\histogram_30.txt"));
        PrintWriter writerhistogram_300 = new PrintWriter(new File("C:\\Users\\User\\eclipse-workspace\\HashTable\\output\\histogram_300.txt"));
        PrintWriter writerhistogram_1000 = new PrintWriter(new File("C:\\Users\\User\\eclipse-workspace\\HashTable\\output\\histogram_1000.txt"));

        hashTable_30.printListLengthsHistogram(writerhistogram_30);
        hashTable_300.printListLengthsHistogram(writerhistogram_300);
        hashTable_1000.printListLengthsHistogram(writerhistogram_1000);
   
       TextHash hashTable = new TextHash(33);
       hashTable=loadFileToHashTable(hashTable);

       Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Insert a word");
            System.out.println("2. Delete a word");
            System.out.println("3. Increase count of a word");
            System.out.println("4. Find count of a word");
            System.out.println("5. List all words and their counts");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    if (hashTable == null) {
                        System.out.println("Please create a hash table first.");
                        break;
                    }
                    System.out.println("Enter the word to insert:");
                    String word = scanner.next();
                    hashTable.insert(word, 1);
                    System.out.println(word + " inserted successfully.");
                    break;

                case 2:
                    if (hashTable == null) {
                        System.out.println("Please create a hash table first.");
                        break;
                    }
                    System.out.println("Enter the word to delete:");
                    String wordToDelete = scanner.next();
                    hashTable.delete(wordToDelete);
                    break;

                case 3:
                    if (hashTable == null) {
                        System.out.println("Please create a hash table first.");
                        break;
                    }
                    System.out.println("Enter the word to increase its count:");
                    String wordToIncrease = scanner.next();
                    hashTable.increase(wordToIncrease);
                    System.out.println(wordToIncrease + " count increased successfully.");
                    break;

                case 4:
                    if (hashTable == null) {
                        System.out.println("Please create a hash table first.");
                        break;
                    }
                    System.out.println("Enter the word to find its count:");
                    String wordToFind = scanner.next();
                    int count = hashTable.find(wordToFind);
                    System.out.println(wordToFind + " count is " + count);
                    break;

                case 5:
                    if (hashTable == null) {
                        System.out.println("Please create a hash table first.");
                        break;
                    }
                    System.out.println("Words and their counts in the hash table:");
                    hashTable.listAllKeys();
                    break;
               
                case 0:
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
        //hashTable.collisionHistogram(hashTable.table, 30);
        //hashTable.collisionHistogram(hashTable.table, 300);
        //hashTable.collisionHistogram(hashTable.table, 1000);

    }
}

package HashTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TextHash {
    private static int MAX_HASH; // A large prime number
    Node[] table;
    private int count;


    private static class Node {
        String key;
        int value;
        Node next;

        Node(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public TextHash(int m) {
    	count=0;
    	MAX_HASH=m;
        table = new Node[MAX_HASH];
    }

    private int hashFunction(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (31 * hash + key.charAt(i)) % MAX_HASH;
        }
        return hash;
    }

    public void insert(String key, int value) {
        int hash = hashFunction(key);
        Node newNode = new Node(key, value);
        newNode.next = table[hash];
        table[hash] = newNode;
        count++;
    }

    public void delete(String key) {
        int hash = hashFunction(key);
        Node curr = table[hash];
        Node prev = null;

        while (curr != null) {
            if (curr.key.equals(key)) {
                if (prev == null) {
                    table[hash] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                count--;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    public void increase(String key) {
        int hash = hashFunction(key);
        Node curr = table[hash];

        while (curr != null) {
            if (curr.key.equals(key)) {
                curr.value++;
                return;
            }
            curr = curr.next;
        }
    }

    public int find(String key) {
        int hash = hashFunction(key);
        Node curr = table[hash];

        while (curr != null) {
            if (curr.key.equals(key)) {
                return curr.value;
            }
            curr = curr.next;
        }
        return -1;
    }

    public void listAllKeys() {
        try {
            PrintWriter writer = new PrintWriter(new File("C:\\Users\\User\\eclipse-workspace\\HashTable\\output\\output.txt"));
            for (int i = 0; i < MAX_HASH; i++) {
                Node curr = table[i];
                while (curr != null) {
                    writer.println(curr.key + ": " + curr.value);
                    curr = curr.next;
                }
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void printListLengthsHistogram(PrintWriter writerhistogram) throws FileNotFoundException {
       int[] listLengths = new int[table.length];
        int maxListLength = 0;
        for (int i = 0; i < table.length; i++) {
            int length = 0;
            Node current = table[i];
            while (current != null) {
                length++;
                current = current.next;
            }
            listLengths[i] = length;
            if (length > maxListLength) {
                maxListLength = length;
            }
        }

        // Calculate average list length
        double alpha = (double) count / (double) MAX_HASH;

        // Calculate variance of list lengths
        double variance = 0.0;
        for (int i = 0; i < listLengths.length; i++) {
            double diff = listLengths[i] - alpha;
            variance += diff * diff;
        }
        variance /= listLengths.length;

        // Print histogram
        System.out.println("Histogram of list lengths:");
        int[] histogram = new int[maxListLength + 1];
        for (int length : listLengths) {
            histogram[length]++;
        }
     

        for (int i = 0; i < histogram.length; i++) {
        	writerhistogram.println(i + "," +histogram[i]);
            System.out.println(histogram[i]);
            System.out.println();
        }
        writerhistogram.close();
        HistogramPlot plot=new HistogramPlot(histogram);
        plot.plotGraph(plot);
        System.out.printf("Average list length: %.2f\n", alpha);
        System.out.printf("Variance of list lengths: %.2f\n", variance);

        // Print longest list lengths
        int numLongestLists = (int) (0.1 * table.length);
        int[] longestListLengths = new int[numLongestLists];
        for (int i = 0; i < numLongestLists; i++) {
            longestListLengths[i] = -1;
        }
        for (int i = 0; i < listLengths.length; i++) {
            if (listLengths[i] > longestListLengths[0]) {
                longestListLengths[0] = listLengths[i];
                for (int j = 1; j < longestListLengths.length; j++) {
                    if (longestListLengths[j] < longestListLengths[j - 1]) {
                        int temp = longestListLengths[j];
                        longestListLengths[j] = longestListLengths[j - 1];
                        longestListLengths[j - 1] = temp;
                    } else {
                        break;
                    }
                }
            }
        }
        System.out.printf("Longest %d list lengths: ", numLongestLists);
        for (int length : longestListLengths) {
            System.out.print(length + " ");
        }
        System.out.println();
    }

}

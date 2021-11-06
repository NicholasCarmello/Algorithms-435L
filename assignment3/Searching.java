import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Random;

public class Searching {
    private static final int HASH_TABLE_SIZE = 250;

    public static void main(String[] args) {
        String myArray[];
        myArray = new String[666];
        int count = 0;
        // This reads all of the elements from a file into an array
        try {
            File magicItems = new File("magicitems.txt");
            Scanner myScanner = new Scanner(magicItems);

            while (myScanner.hasNextLine()) {
                String data = myScanner.nextLine();
                myArray[count] = data.toLowerCase();
                count++;
            }
            myScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String RandomItems[] = new String[42];
        Random rand = new Random();

        // Randomly chooses 42 of the 666 items
        for (int i = 0; i < RandomItems.length; i++) {
            RandomItems[i] = myArray[rand.nextInt(666)];

        }

        // calls for each of the searches and the hash table.
        linearSearch(RandomItems, myArray);
        binarySearch(RandomItems, myArray);
        hashTable(RandomItems, myArray);

    }

    public static void linearSearch(String array[], String searchArray[]) {
        int averageTimeForLinearSearch = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < searchArray.length; j++) {
                // This compares each element in the magic items text file to the current random
                // item
                if (array[i] == searchArray[j]) {
                    break;
                }
                // global count
                averageTimeForLinearSearch += 1;
            }
        }
        System.out.println("linear search is " + averageTimeForLinearSearch / 42);
    }

    public static void binarySearch(String array[], String searchArray[]) {
        float averageTimeForBinarySearch = 0;

        for (int i = 0; i < array.length; i++) {

            int start = 0;
            int end = array.length - 1;

            while (start <= end) {

                int medium = start + (end - start) / 2;

                // if the item is equal to the current mid then it will break out of the while
                // loop
                if (searchArray[medium].replaceAll("\\s", "").compareTo(array[i].replaceAll("\\s", "")) == 0) {
                    averageTimeForBinarySearch += 1;
                    break;
                }
                // if the item is less than the current mid then the start becomes the medium
                // plus 1
                if (searchArray[medium].replaceAll("\\s", "").compareTo(array[i].replaceAll("\\s", "")) < 0) {
                    start = medium + 1;
                    // if the item is greater than the current mid then end becomes the medium - 1
                } else {
                    end = medium - 1;

                }
                averageTimeForBinarySearch += 1;
            }

        }
        System.out.println("Binary search is " + averageTimeForBinarySearch / 42);
    }

    // This function makes a hash table
    public static void hashTable(String randomItems[], String array[]) {
        // create a new array of nodes size 250 and initializing the indices to null
        Node newArray[] = new Node[250];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = null;
        }

        for (int i = 0; i < array.length; i++) {
            Node newNode = new Node();
            int getHashCode = makeHashCode(array[i]);
            // This checks to see if there isn't an item in the indice and then adds the
            // first node if there isn't
            if (newArray[getHashCode] == null) {
                newNode.name = array[i];
                newArray[getHashCode] = newNode;
            } else {
                // This will find the last position
                // in the linked list and will add the new node to the end.
                newNode.name = array[i];
                Node current = newArray[getHashCode];

                while (current.name != null) {
                    if (current.next == null) {
                        current.next = newNode;
                        break;
                    }
                    current = current.next;
                }
            }
        }
        hashTableLookup(randomItems, newArray);
    }

    public static void hashTableLookup(String[] randomitems, Node[] array) {
        float averageLookUpTime = 0;
        for (int i = 0; i < randomitems.length; i++) {
            int hashCode = makeHashCode(randomitems[i]);
            Node current = array[hashCode];
            // this checks if the current node or the first node
            // in the linked list is the
            // item.
            if (current.name == randomitems[i]) {
                averageLookUpTime += 1;
                continue;
            } else {
                // This goes through every node in the
                // linked list until it finds the correct
                // item.
                while (current.next != null) {
                    averageLookUpTime += 1;
                    if (current.name == randomitems[i]) {
                        break;
                    }
                    current = current.next;
                }
            }

        }
        // This prints out of the total divided by the number of random items in the
        // array.
        System.out.println("Hash look up average is " + averageLookUpTime / 42);
    }

    private static int makeHashCode(String str) {
        str = str.toUpperCase();
        int length = str.length();
        int letterTotal = 0;

        // Iterate over all letters in the string, totalling their ASCII values.
        for (int i = 0; i < length; i++) {
            char thisLetter = str.charAt(i);
            int thisValue = (int) thisLetter;
            letterTotal = letterTotal + thisValue;
            /*
             * 32 System.out.print(" ["); 33 System.out.print(thisLetter); 34
             * System.out.print(thisValue); 35 System.out.print("] "); 36 //
             */
        }

        // Scale letterTotal to fit in HASH_TABLE_SIZE.
        int hashCode = (letterTotal * 1) % HASH_TABLE_SIZE; // % is the "mod" operator
        // TODO: Experiment with letterTotal * 2, 3, 5, 50, etc.

        return hashCode;
    }

    public static int selectionSort(String[] myArray) {
        int comparisions = 0;

        int arrayLength = myArray.length;
        for (int i = 0; i < arrayLength - 2; i++) {
            int smallPosition = i;
            for (int j = i + 1; j < arrayLength; j++) {

                if (myArray[j].replaceAll("\\s", "").compareTo(myArray[smallPosition].replaceAll("\\s", "")) < 0) {

                    smallPosition = j;
                }
                comparisions++;
            }
            // this is the swapping work that is done. it finds the the next smallest
            // element and swaps its position with i
            String temp = myArray[smallPosition];
            myArray[smallPosition] = myArray[i];
            myArray[i] = temp;
        }
        return comparisions;
    }
}

// Node class
class Node {
    String name;
    Node next;
}

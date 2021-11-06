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

        for (int i = 0; i < RandomItems.length; i++) {
            RandomItems[i] = myArray[rand.nextInt(666)];
        }

        linearSearch(RandomItems, myArray);
        binarySearch(RandomItems, myArray);
        hashTable(RandomItems, myArray);

    }

    public static void linearSearch(String array[], String searchArray[]) {
        int averageTimeForLinearSearch = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < searchArray.length; j++) {
                if (array[i] == searchArray[j]) {
                    break;
                }
                averageTimeForLinearSearch += 1;
            }
        }
        System.out.println("linear search is " +averageTimeForLinearSearch / 42);
    }

    public static void binarySearch(String array[], String searchArray[]) {
        float averageTimeForBinarySearch = 0;
       

        for (int i = 0; i < array.length; i++) {
            
            int start = 0;
            int end = array.length - 1;
            
            while (start <= end) {

                int medium = start + (end - start) / 2;
            
                if (searchArray[medium].replaceAll("\\s", "").compareTo(array[i].replaceAll("\\s", "")) == 0) {
                    averageTimeForBinarySearch += 1;
                    break;
                }
                if (searchArray[medium].replaceAll("\\s", "").compareTo(array[i].replaceAll("\\s", "")) < 0) {
                    start = medium + 1;
                    
                } else {
                    end = medium - 1;

                }
                averageTimeForBinarySearch += 1;
            }

        }
        System.out.println("Binary search is " + averageTimeForBinarySearch /42);
    }

    public static void hashTable(String randomItems[], String array[]) {
        Node newArray[] = new Node[250];
        for (int i = 0; i < newArray.length; i++){
            newArray[i] = null;
        }

        for (int i = 0; i < array.length; i++) {
            Node newNode = new Node();
            int getHashCode = makeHashCode(array[i]);
            if (newArray[getHashCode] == null) {

                newNode.name = array[i];
                newArray[getHashCode] = newNode;
            } else {
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
            if (current.name == randomitems[i]) {
                averageLookUpTime += 1;
                continue;
            } else {
                while (current.next != null) {
                    averageLookUpTime += 1;
                    if (current.name == randomitems[i]) {
                        break;
                    }
                    current=current.next;
                }
            }

        }
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

class Node {
    String name;
    Node next;
}

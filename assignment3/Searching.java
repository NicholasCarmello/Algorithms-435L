import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Searching {    
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
        
        selectionSort(myArray);
        linearSearch(myArray);
        binarySearch(myArray);
        hashTable(myArray);
    }

    public static void linearSearch(String array[]){
        
    }
    public static void binarySearch(String array[]){

    }

    public static void hashTable(String array[]){

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
            //this is the swapping work that is done. it finds the the next smallest element and swaps its position with i 
            String temp = myArray[smallPosition];
            myArray[smallPosition] = myArray[i];
            myArray[i] = temp;
        }
        return comparisions;
    }
}
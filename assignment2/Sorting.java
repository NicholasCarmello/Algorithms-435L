import java.io.*;
import java.util.Scanner;

public class Sorting {

    public static void main(String[] args) {
        int count = 0;
        String myArray[];
        myArray = new String[666];
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

        int selectionSortComparision = selectionSort(myArray.clone());
        System.out.println(selectionSortComparision);
        int comparisionArray = insertionSort(myArray.clone());
        System.out.println(comparisionArray);
        String mergeSortArray[] = mergeSort(myArray.clone());
        String quickSortArray[] = quickSort(myArray.clone());

    }

    public static int selectionSort(String[] myArray) {
        int comparisions = 0;

        int arrayLength = myArray.length;
        for (int i = 0; i < arrayLength - 2; i++) {
            int smallPosition = i;
            for (int j = i + 1; j < arrayLength; j++) {

                if (myArray[j].compareTo(myArray[smallPosition]) < 0) {
                    
                    smallPosition = j;
                }
                comparisions++;
            }
            String temp = myArray[smallPosition];
            myArray[smallPosition] = myArray[i];
            myArray[i] = temp;
        }
        return comparisions;
    }

    public static int insertionSort(String[] myArray) {
        int comparisions = 0;

        for (int j =1;j<myArray.length;j++){

            String key = myArray[j].replaceAll("\\s", "");
            int i = j - 1;
            while (i >= 0 && (myArray[i].replaceAll("\\s", "").compareTo(key) >0)){

                myArray[i + 1] = myArray[i];
                i--;
                comparisions +=1;
            }
            myArray[i + 1] = key;

        }
        return comparisions;
    }

    public static String[]  mergeSort(String[] myArray) {

        if (myArray.length ==1){
            return myArray;
        }
        
        int lowLength = myArray.length / 2;
        int highLength = (myArray.length/2) + 1;
        mergeSort(myArray[0:lowLength]);
        return myArray;
    }

    public static String[] quickSort(String[] myArray) {
        return myArray;
    }
}
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Random;

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
        
        int averageTimeForBinarySearch = 0;
        

        String RandomItems[] = new String[42];
        Random rand = new Random();

        for (int i = 0; i < RandomItems.length;i++){
            RandomItems[i] = myArray[rand.nextInt(666)];
        }
        
        linearSearch(RandomItems,myArray);
        
    }

    public static void linearSearch(String array[], String searchArray[]){
        int averageTimeForLinearSearch = 0;
        for (int i = 0; i< array.length;i++){
            for (int j = 0; j < searchArray.length; j++){
                if (array[i]==searchArray[j]){
                    break;
                }
                averageTimeForLinearSearch +=1;
            }
        }
        System.out.println(averageTimeForLinearSearch/42);
    }
    public static void binarySearch(String array[],String searchArray[]){
        int averageTimeForBinarySearch = 0;
        boolean found = false;

        for (int i = 0; i < array.length; i ++){
            while (found == false){

                int medium = searchArray.length / 2;
                


            }
        }
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
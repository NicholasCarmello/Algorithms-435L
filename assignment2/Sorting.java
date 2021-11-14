import java.io.*;
import java.util.Scanner;

public class Sorting {
    //global variables for mergesort and quicksort
    public static int quickSortCount = 0;
    public static int mergeSortCount = 0;

    public static void main(String[] args) {
        int count = 0;
        String myArray[];
        myArray = new String[666];
        //This reads all of the elements from a file into an array
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

        //This clones all of the arrays for the specified sort.
        String[] quickSortArray = myArray.clone();
        String[] selectionSortArray = myArray.clone();
        String[] mergeSortArray = myArray.clone();

        int selectionSortComparision = selectionSort(selectionSortArray);
        int comparisionArray = insertionSort(myArray.clone());
        //I'm calling the quicksort and mergesort from the start and end of the array
        quickSort(quickSortArray, 0, myArray.length - 1);
        mergeSort(mergeSortArray, 0,myArray.length -1 );

        //This prints out all of the arrays
        System.out.println("mergesort sort is " + mergeSortCount);
        System.out.println("Quicksort sort is "  + quickSortCount);
        System.out.println("Insertion sort is " + comparisionArray);
        System.out.println("Selection sort is "+ selectionSortComparision);
    

    }

    //this is an o(n2) algorithm
    //This finds the next mallest element in the array and puts it in correct postion according to the "i"th place in the array.
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

    public static int insertionSort(String[] myArray) {
        int comparisions = 0;

        for (int j = 1; j < myArray.length; j++) {

            String key = myArray[j].replaceAll("\\s", "");
            int i = j - 1;
            //This while loop will keep swapping the current element with the next element until the conidtion isn't met anymore
            while (i >= 0 && (myArray[i].replaceAll("\\s", "").compareTo(key) > 0)) {

                myArray[i + 1] = myArray[i];
                i--;
                comparisions += 1;
            }
            myArray[i + 1] = key;

        }
        //This returns the comparison count
        return comparisions;
    }

    public static void mergeSort(String[] myArray, int p, int r) {
        //This is like the base case for the function calls
        if (p < r) {
            int q = (p + r) / 2;
            //This calls merge sort for the first half of the array and the second half of the array
            //This will keep going until the length of the arrays are 1
            mergeSort(myArray, p, q);
            mergeSort(myArray, q + 1, r);
            //This is used to merge everything together
            merge(myArray, p, q, r);
        }
    }

    
    public static void merge(String[] nums, int p, int q, int r){
        int left = p;
        int right = q + 1;
        String[] sentinal = new String[r-p+1];
        int t = 0;
        
        while (left <= q && right <= r){
            if (nums[left].compareTo(nums[right]) < 0){
                sentinal[t++] = nums[left++];
            }else{
                sentinal[t++] = nums[right++];
            }
            mergeSortCount+=1;
        }
        
        while (left <= q) {
            sentinal[t++] = nums[left++];}

        while (right <= r)
        { 
        sentinal[t++] = nums[right++];
        }


        for (int i = p; i <= r; i++){
            nums[i] = sentinal[i-p];
        }
    }

    
    public static void quickSort(String[] myArray, int smallIndex, int bigIndex) {

        //This is like the base case for the quickSort because we dont want the small index to be equal to the big index.
        if (smallIndex < bigIndex) {
            //The pivot value is the element we will pivot around
            int pivotValue = getPivotIndex(myArray, smallIndex, bigIndex);

            //Recursively calls it self untill the arrays are sorted
            quickSort(myArray, smallIndex, pivotValue - 1);
            quickSort(myArray, pivotValue + 1, bigIndex);

        }
    }

    public static int getPivotIndex(String[] myArray, int smallIndex, int bigIndex) {

        
        String pivotIndex = myArray[bigIndex].replaceAll("\\s", "");

        int smallerElement = smallIndex - 1;
        for (int j = smallIndex; j <= bigIndex - 1; j++) {

            //Im comparing the elements in the array to the current pivot value
            //If the element is smaller than the right then it will be on the left otherwise its on the right
            if ((myArray[j].replaceAll("\\s", "").compareTo(pivotIndex)) < 0) {
                smallerElement += 1;
                String temp = myArray[smallerElement];
                myArray[smallerElement] = myArray[j];
                myArray[j] = temp;
            }
            //quicksort count goes up for every comparison
            quickSortCount += 1;
            
        }
        
        String temp = myArray[smallerElement + 1];
        myArray[smallerElement + 1] = myArray[bigIndex];
        myArray[bigIndex] = temp;
        smallerElement +=1;
        return smallerElement;
    }
}

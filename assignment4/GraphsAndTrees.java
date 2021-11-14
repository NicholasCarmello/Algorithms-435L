import java.util.Hashtable;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class GraphsAndTrees{
    public static void main(String[]args){
        String myArray[] = new String[666];
        int count = 0;
        try {
            File magicItems = new File("graphs1.txt");
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
        

        for(int i =0; i < myArray.length; i ++){
            
        }
        
        

    }

}
class Node{
    Node left;
    Node right;
}
class BinarySearchTree{
    
}
import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
public class Homework1{
    
    public static void main(String[] args){
        int countOfPalindrome; 
        int count = 0;
        String myArray[];
        myArray = new String[666];
        try{
        File magicItems = new File("magicitems.txt");
        Scanner myScanner = new Scanner(magicItems);
        
        
        while (myScanner.hasNextLine()) {
            String data = myScanner.nextLine();
            myArray[count] = data;
            count++;
          }
          myScanner.close();
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        
        

        
    }


}
class Queue{
    Queue(){
        
    }
    void enqueue(){

    }
    String dequeue(){
        return "";
    }
}
class Stack{
    void push(){}
    String pop(){return "";}
    String top(){return "";}

}
class Node{
    Node newNode;
    Node nextNode;
    Node tail;
}
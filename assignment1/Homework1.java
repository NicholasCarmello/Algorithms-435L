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
        


    //initalize a new Stack
    Stack newStack = new Stack();
    
    for (int i = 0; i < myArray.length;i++){
        for (int j = 0; j < myArray[i].length(); j++){
            //System.out.println(Character.toString(myArray[i].charAt(j)));
            if (Character.toString(myArray[i].charAt(j)).equals(" ")){
                continue;
            }
            newStack.push(Character.toString(myArray[i].charAt(j)));
            
        }
    }
    
    while (newStack.top.next != null){
        System.out.println(newStack.top.name);
        newStack.top = newStack.top.next;
    }
    
    }
}

class Stack{
    Node top;
    Node next;
    void push(String previous){
       
        Node oldtop = top;
        top = new Node();
        top.name = previous;
        top.next = oldtop;
    }
    Node pop(){
        Node oldTop = top;
        top = top.next;
        return oldTop;
    }
    
    public boolean isEmpty(){return false;}
}
class Node{
    String name;
    Node next;
}











/*class Queue{
    Node head,tail;
    void enqueue(){

    }
    String dequeue(){
        return "";
    }
    boolean isEmpty(){return false;}
}*/
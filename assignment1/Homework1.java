import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
public class Homework1{
    
    public static void main(String[] args){
        int countOfPalindrome = 0; 
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
    Queue newQueue = new Queue();
    String queueValue;
    String stackValue;
    //This for loop will push every string onto the stack and each character by character.
    for (int i = 0; i < myArray.length;i++){
        for (int j = 0; j < myArray[i].length(); j++){
            //System.out.println(Character.toString(myArray[i].charAt(j)));
            if (Character.toString(myArray[i].charAt(j)).equals(" ")){
                continue;
            }
            newStack.push(Character.toString(myArray[i].charAt(j)).toLowerCase());
            newQueue.enqueue(Character.toString(myArray[i].charAt(j)).toLowerCase());
            
        }
        //This while loop will dequeue and pop every character by character. 
        //If the characters aren't equal it will create a new stack and queue object.
        while(newQueue.head != null && newStack.top != null){
            queueValue = newQueue.dequeue();
            stackValue = newStack.pop();
            
            if (!queueValue.equals(stackValue)){
                newQueue = new Queue();
                newStack = new Stack();
                break;
            }
            //This will print every palindrome.
            if (queueValue.equals(stackValue) && newStack.isEmpty()){
                System.out.println(myArray[i]);
                countOfPalindrome++;
            }
            
            
        }
    }
    System.out.println("There are " + countOfPalindrome + " paliondromes");

    }
}
//This class is used for the class object. It has a top and next attribute.
//The functions for the stack are push,pop and isempty.
class Stack{
    Node top;
    Node next;
    //push will assign the new node to the top and the top will be the previous node.
    //push will take the top.next and assign it to the old top.
    void push(String previous){
       
        Node oldtop = top;
        top = new Node();
        top.name = previous;
        top.next = oldtop;
    }
    //pop will assign a new node to the top. The top will then become the next in the stack.
    //it will return the old tops' name. 
    String pop(){
        Node oldTop = top;
        top = top.next;
        return oldTop.name;
    }
    //isEmpty function will return true if the top is null, else it's false.
    public boolean isEmpty(){return top == null;}
}

class Node{
    String name;
    Node next;
}










// This class is used for creating a Queue object. FIFO.
class Queue{
    Node head,tail;
    //Enqueue assigns oldtail to the current tail.
    //The new tail is then a new node with the name of an incoming character
    void enqueue(String inComingChar){

        Node oldtail = tail;
        tail = new Node();
        tail.name = inComingChar;
        tail.next = null;
        
    
        if (isEmpty()){
            head = tail;
        }
        else{
            oldtail.next = tail;
        }

    }
    //Dequeue takes in a new character.
    //It assigns the new head to the head.next which is the next in the queue.
    String dequeue(){
        String fetVal = "";
        if (!isEmpty()){
            fetVal = head.name;
            head = head.next;
            return fetVal;
        }
        //is empty returns the next variable thats empty
        if (isEmpty()){
            return "Isempty";
            
        }
        return fetVal;
        
    }
    boolean isEmpty(){if (head == null){
        return true;
    }
    else{
        return false;
    }
}
}
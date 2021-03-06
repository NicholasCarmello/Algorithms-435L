import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.File;
import java.util.Scanner;

import javax.swing.Action;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Queue;

public class GraphsAndTrees {
    public static int countCo = 0;
    public static int currentCount = 0;
    public static int matrixCount = 0;
    public static int flag = 0;
    public static void main(String[] args) {
        
        // Reading from the file and putting every line in an array for easy access.
        String graphArray[] = new String[375];
        String magicItems[] = new String[666];
        String magicItemsFindInBST[] = new String[42];

        // passing the filename to be read and put in the associated array which can be
        // done
        // because it's passed as a reference to the array.
        readFile("magicitems.txt", magicItems);
        readFile("graphs1.txt", graphArray);
        readFile("magicitems-find-in-bst.txt", magicItemsFindInBST);

        makeGraphs(graphArray);
        System.out.println(" ");
        // This will go through
        TreeNode root = makeBST(magicItemsFindInBST, magicItems);

        // This will print out the in order Traversal of the BST
        inOrderTraversal(root);
        for (int i = 0; i < magicItemsFindInBST.length; i++) {
            searchBST(root, magicItemsFindInBST[i]);
        }

        // This will print out the average number of searches in the BST
        System.out.println("Average number of Searches: " + countCo / 42);

    }

    // In Order traversal of a binary Tree. Binary Search Trees happen to be Binary
    // Trees.
    public static void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        // This will go all the way to the left side of the tree and up.
        inOrderTraversal(root.left);

        // This will print the current node
        System.out.println(root.val + " ");

        // This will print the right side of the current node
        inOrderTraversal(root.right);
    }

    // Searches through the BST to find the element were looking for.
    public static void searchBST(TreeNode root, String findElement) {

        if (findElement.equals(root.val)) {
            System.out.println("Compares:" + currentCount);
            currentCount = 0;
        } else {

            // This goes to the left side of the tree if the element is less than the root
            // Node
            if (findElement.compareToIgnoreCase(root.val) <= 0) {
                countCo += 1;
                currentCount += 1;
                System.out.print("L ");
                root = root.left;
                // Recursive search on the current Node.
                searchBST(root, findElement);

            }
            // otherwise this recursively goes to the right side of the tree until we find
            // the element
            else {
                countCo += 1;
                currentCount += 1;
                System.out.print("R ");
                root = root.right;
                searchBST(root, findElement);
            }
        }
    }

    // This will make a BST out of an array of Strings and returns the root node.
    public static TreeNode makeBST(String[] findItems, String[] magicItems) {

        TreeNode root = new TreeNode();
        root.val = magicItems[0];
        TreeNode tempRoot = root;
        // Goes through the whole array
        for (int i = 1; i < magicItems.length; i++) {
            // inserted is automatically false until its in the BST.
            boolean inserted = false;
            TreeNode nextNode = new TreeNode();
            nextNode.val = magicItems[i];

            while (!inserted) {
                // This will check to see if the current item is less than the root value.
                if (magicItems[i].compareToIgnoreCase(root.val) <= 0) {
                    // if root.left is null then it will put the current magic item there.
                    if (root.left == null) {
                        System.out.print("L ");
                        root.left = nextNode;
                        root = tempRoot;
                        inserted = true;
                    } else {
                        // if root.left isn't null, we traverse to the left.
                        root = root.left;
                    }
                } else {
                    // if root.right is null then it will put the current magic item there.
                    if (root.right == null) {
                        System.out.print("R ");
                        root.right = nextNode;
                        root = tempRoot;
                        inserted = true;
                    }
                    // if root.right isn't null then we traverse to the right of the current node
                    else {
                        root = root.right;

                    }
                }
            }

        }

        return root;
    }

    public static void makeGraphs(String[] graphArray) {

        //All of the datastructures used to store the nodes
        HashMap<String, List<String>> newGraph = new HashMap<String, List<String>>();
        ArrayList<int[]> matrix = new ArrayList<int[]>();
        GraphNode graph = new GraphNode();
        ArrayList<GraphNode> listOfNodes = new ArrayList<GraphNode>();
        

        //This goes through the graphs array which contains the contents of the txt file
        for (int i = 0; i < graphArray.length; i++) {

            //splits the line by spaces to find the numbers
            String findArray[] = graphArray[i].split(" ");

            if (graphArray[i].equals("new graph") || i == graphArray.length - 1) {


           
            // Printing and display the elements in ArrayList
             
                System.out.println(" ");
                //This prints out the Current adjacency List
                newGraph.entrySet().forEach(entry -> {
                    System.out.println("key:" + entry.getKey() + " Value:" + entry.getValue());
                });
                System.out.println(" ");

                //prints out the breadth First and depth First traversal.
                if (listOfNodes.size() != 0) {
                    
                    BreadthFirst(listOfNodes.get(0),listOfNodes);
                    //DepthFirst(listOfNodes.get(0));
                }
                
                //This prints out the current matrix
                for (int o = 0; o < matrix.size(); o ++){
                    System.out.println(" ");
                    for (int k = 0; k < matrix.get(0).length; k ++){
                        System.out.print(matrix.get(o)[k]);
                    }
                }
                System.out.println(" ");
                //Clear the adjacency list, matrix and nodes.
                matrix = new ArrayList<int[]>();
                newGraph.clear();
                listOfNodes.clear();
                matrixCount =0;
                flag = 0;

                
            }

            else {

                if (findArray[0].equals("add")) {

                    if (findArray[1].equals("vertex")) {
                        newGraph.put(findArray[2], new ArrayList<String>());

                        matrixCount +=1;
                        

                        //creates a new node in the graph
                        graph = new GraphNode();
                        graph.id = findArray[2];
                        graph.neighbors = new ArrayList<GraphNode>();
                        listOfNodes.add(graph);
                        
                    } else {
                        // This adds both the adjacencys to the dictionary.
                        newGraph.get(findArray[2]).add(findArray[4]);
                        newGraph.get(findArray[4]).add(findArray[2]);
                        
                        if (flag ==0){
                            flag = 1;
                            for(int j = 0; j < matrixCount + 1; j++ ){
                                matrix.add(new int[matrixCount + 1] );
                            }
                        }
                        //These will add the number one to the correct positions in the matrix
                        matrix.get(Integer.parseInt(findArray[2]))[Integer.parseInt(findArray[4])] = 1;
                        matrix.get(Integer.parseInt(findArray[4]))[Integer.parseInt(findArray[2])] = 1;
                        //Creating 2 new graph nodes. Each for the 2 elements.
                        GraphNode newNodeInGraph = new GraphNode();
                        newNodeInGraph.id = findArray[2];
                        newNodeInGraph.neighbors = new ArrayList<GraphNode>();

                        GraphNode anotherNodeInGraph = new GraphNode();
                        anotherNodeInGraph.id = findArray[4];
                        anotherNodeInGraph.neighbors = new ArrayList<GraphNode>();

                        //Finds each of the nodes in the file and inserts their neighbors.
                        listOfNodes.forEach(action -> {
                            if (action.id.equals(findArray[4])) {
                                action.neighbors.add(newNodeInGraph);

                            }
                            if (action.id.equals(findArray[2])) {
                                action.neighbors.add(anotherNodeInGraph);
                            }

                        });

                    }

                }

            }

        }
    }

    // This function will read the files and put them into an array.
    public static void readFile(String fileName, String[] newArray) {
        int count = 0;
        try {
            File readFile = new File(fileName);
            Scanner myScanner = new Scanner(readFile);
            while (myScanner.hasNextLine()) {
                String data = myScanner.nextLine();
                if (data.equals(" ")) {
                    continue;
                }
                newArray[count] = data.toLowerCase();
                count++;
            }
            myScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // BreadthFirst traversal or aka level order traversal.
    public static void BreadthFirst(GraphNode node, ArrayList<GraphNode> list) {
        Queue<GraphNode> q = new LinkedList<>();
        node.processed = true;
        q.add(node);
        while (q.size() > 0) {
            GraphNode cv = q.remove();
            System.out.println(cv.id);
            list.forEach(action->{
                if (action.id.equals(cv.id)){
                    for (int i =0; i < action.neighbors.size(); i++){
                        if(!action.neighbors.get(i).processed){
                            action.neighbors.get(i).processed = true;
                            
                            q.add(action.neighbors.get(i));
                        }

                    }
                }
            });
            /*
            cv.neighbors.forEach(action -> {
                if (!action.processed) {
                    action.processed = true;
                    q.add(action);

                }
            });*/
        }
    }

    public static void DepthFirst(GraphNode startNode) {
        if (startNode.id == null) {
            return;
        } else {
            if (!startNode.processed) {
                System.out.println(startNode.id);
                startNode.processed = true;

            }

            startNode.neighbors.forEach(action -> {
                if (!action.processed) {
                    DepthFirst(startNode);
                }
            });

        }
    }
}
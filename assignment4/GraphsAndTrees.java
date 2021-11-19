import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.File;
import java.util.Scanner;


import java.io.FileNotFoundException;
import java.util.List;
import java.util.Queue;
public class GraphsAndTrees {
    public static int matrixAdd = 0;
    public static int newAdd =0;
    public static void main(String[] args) {

        // Reading from the file and putting every line in an array for easy access.
        String myArray[] = new String[375];
        int count = 0;
        try {
            File magicItems = new File("graphs1.txt");
            Scanner myScanner = new Scanner(magicItems);
            while (myScanner.hasNextLine()) {
                String data = myScanner.nextLine();
                if (data.equals(" ")) {
                    continue;
                }
                myArray[count] = data.toLowerCase();
                count++;
            }
            myScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        HashMap<String, List<String>> newGraph = new HashMap<String, List<String>>();
        ArrayList<int[]> matrix = new ArrayList<int[]>();
        GraphNode graph = new GraphNode();
        ArrayList<GraphNode> listOfNodes = new ArrayList<GraphNode>();
        graph.id = null;
        for (int i = 0; i < myArray.length; i++) {

            String findArray[] = myArray[i].split(" ");

            if (myArray[i].equals("new graph") || i == myArray.length -1) {

                newGraph.entrySet().forEach(entry -> {

                    System.out.println("key:" + entry.getKey() + " Value:" + entry.getValue());

                });

                if (listOfNodes.size() != 0) {
                    /*for (int j =0; j < listOfNodes.size(); j ++){
                        System.out.println("key" + listOfNodes.get(j).id);
                        listOfNodes.get(j).neighbors.forEach(action ->
                        System.out.print( action.id + " "));

                    }*/
                    //BreadthFirst(listOfNodes.get(0));
                    // DepthFirst(listOfNodes.get(0));
                }
                for (int m = 0; m < matrix.size(); m++){
                    System.out.println(" ");
                    for(int j = 0; j < matrix.get(m).length; j ++){
                        System.out.print(matrix.get(m)[j]);
                    }
                }
                matrix = new ArrayList<int[]>();
                matrixAdd = 0;
                newAdd = 0;
                newGraph.clear();
            }

            else {

                if (findArray[0].equals("add")) {
                    if (findArray[1].equals("vertex")) {
                        newGraph.put(findArray[2], new ArrayList<String>());
                        matrixAdd +=1;
                        
                        graph = new GraphNode();
                        graph.id = findArray[2];
                        graph.processed = false;
                        graph.neighbors = new ArrayList<GraphNode>();
                        listOfNodes.add(graph);

                    
                    } else {
                        // This adds both the adjacencys to the dictionary.
                        if(newAdd == 0){
                            newAdd = 1;
                            
                            for (int m = 0; m <= matrixAdd; m ++)
                            matrix.add(new int[matrixAdd  +1]);
                        }
                    newGraph.get(findArray[2]).add(findArray[4]);
                        newGraph.get(findArray[4]).add(findArray[2]);
                matrix.get(Integer.parseInt(findArray[2]))[Integer.parseInt(findArray[4])] = 1;
                matrix.get(Integer.parseInt(findArray[4]))[Integer.parseInt(findArray[2])] = 1;
                        GraphNode newNodeInGraph = new GraphNode();
                        newNodeInGraph.id = findArray[2];
                        newNodeInGraph.neighbors = new ArrayList<GraphNode>();

                        GraphNode anotherNodeInGraph = new GraphNode();
                        anotherNodeInGraph.id = findArray[4];
                        anotherNodeInGraph.neighbors = new ArrayList<GraphNode>();
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

    public static void BreadthFirst(GraphNode node) {
        Queue<GraphNode> q = new LinkedList<>();
        node.processed = true;
        q.add(node);
        while (q.size()>0){
            GraphNode cv = q.remove();
            System.out.println(cv.id);
            cv.neighbors.forEach(action ->{
                if (!action.processed){
                    action.processed = true;
                    q.add(action);
                    
                }
            });
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

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;

public class GraphsAndTrees {
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
        ArrayList<List<String>> matrix = new ArrayList<List<String>>();
        GraphNode graph = new GraphNode();
        ArrayList<GraphNode> listOfNodes = new ArrayList<GraphNode>();
        graph.id = null;
        for (int i = 0; i < myArray.length; i++) {

            String findArray[] = myArray[i].split(" ");

            if (myArray[i].equals("new graph")) {

                newGraph.entrySet().forEach(entry -> {

                    System.out.println("key:" + entry.getKey() + " Value:" + entry.getValue());

                });

                if (listOfNodes.size() != 0) {
                    BreadthFirst(listOfNodes.get(0));
                    DepthFirst(listOfNodes.get(0));
                }

                matrix = new ArrayList<List<String>>();
                newGraph.clear();
            }

            else {

                if (findArray[0].equals("add")) {
                    if (findArray[1].equals("vertex")) {
                        newGraph.put(findArray[2], new ArrayList<String>());
                        // matrix.add(new ArrayList<String>());
                        graph = new GraphNode();
                        graph.id = findArray[2];
                        graph.processed = false;
                        graph.neighbors = new ArrayList<>();
                        listOfNodes.add(graph);
                    } else {
                        // This adds both the adjacencys to the dictionary.
                        newGraph.get(findArray[2]).add(findArray[4]);
                        newGraph.get(findArray[4]).add(findArray[2]);
                        // matrix.get(Integer.parseInt(findArray[2])).add(findArray[4]);
                        // matrix.get(Integer.parseInt(findArray[4])).add(findArray[2]);
                        GraphNode newNodeInGraph = new GraphNode();
                        newNodeInGraph.id = findArray[2];
                        newNodeInGraph.processed = false;

                        GraphNode anotherNodeInGraph = new GraphNode();
                        anotherNodeInGraph.id = findArray[4];
                        anotherNodeInGraph.processed = false;
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
        if (node.id == null) {
            return;
        } else {

        }
    }

    public static void DepthFirst(GraphNode startNode) {
        if (startNode.id == null) {
            return;
        } else {
            if (startNode.processed) {
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
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ssspAndKnapsack {

    public static int newGraphCount = 0;

    public static void main(String[] args) {
        // Graph and Spice Arrays
        String[] txtFileGraph = new String[88];
        String[] spiceArray = new String[14];

        // Reads the file, inserts contents into above arrays.
        readFile("graphs2.txt", txtFileGraph);
        readFile("spice.txt", spiceArray);

        // Calls spice algorithm on spiceArray
        spiceAlgorithm(spiceArray);

        // Creates graph out of textFileGraph
        createGraph(txtFileGraph);
    }

    public static void spiceAlgorithm(String[] spiceArray) {
        // contains the capacities and spice objects from the file
        ArrayList<Integer> capacities = new ArrayList<Integer>();
        ArrayList<spice> spiceObjects = new ArrayList<spice>();
        String spicesUsed = "";

        // This will go through the whole spice array
        for (int i = 0; i < spiceArray.length; i++) {
            String[] lineSplit = spiceArray[i].split(" ");
            // Splits the current line
            // checks to see if first index of split is spice
            if (lineSplit[0].equals("spice")) {
                // Splits the line again by semicolon
                String[] firstSplit = spiceArray[i].split(";");
                // Gets the name,price and quanitity lines
                String[] nameLine = firstSplit[0].split(" ");
                String[] priceLine = firstSplit[1].split(" ");
                String[] quantityLine = firstSplit[2].split(" ");
                // creates newSpice object
                spice newSpice = new spice();

                // Gets the name,price and quantity from new split lines
                int price = (int) Float.parseFloat(priceLine[priceLine.length - 1]);
                int quantity = Integer.parseInt(quantityLine[quantityLine.length - 1]);
                String name = nameLine[nameLine.length - 1];

                // This gives the current spice object different attributes.
                newSpice.total_price = price;
                newSpice.qty = quantity;
                newSpice.name = name;
                newSpice.unity_price = price / quantity;

                // adds the spice object to newSpice arrayList
                spiceObjects.add(newSpice);
            }
            if (lineSplit[0].equals("knapsack")) {
                String[] firstSplit = spiceArray[i].split(";");
                String[] getCapacity = firstSplit[0].split(" ");

                capacities.add(Integer.parseInt(getCapacity[getCapacity.length - 1]));
            }
        }

        int arrayLength = spiceObjects.size();
        // Reverses the order of the array in descending order
        for (int i = 0; i < arrayLength - 2; i++) {
            int smallPosition = i;
            for (int j = i + 1; j < arrayLength; j++) {

                if (spiceObjects.get(j).unity_price > spiceObjects.get(smallPosition).unity_price) {

                    smallPosition = j;
                }
            }
            // this is the swapping work that is done. it finds the the next smallest
            // element and swaps its position with i
            spice temp = spiceObjects.get(smallPosition);
            spiceObjects.set(smallPosition, spiceObjects.get(i));
            spiceObjects.set(i, temp);
        }

        for (int i = 0; i < capacities.size(); i++) {

            // this keeps the runningTotal of the total price
            int runningTotal = 0;
            int currentCapacity = capacities.get(i);
            for (int k = 0; k < spiceObjects.size(); k++) {
                spice currentSpice = spiceObjects.get(k);
                if (currentCapacity == 0) {
                    break;
                } else {
                    if (currentCapacity >= currentSpice.qty) {

                        // This adds the total price of the current spice to the running total
                        runningTotal += currentSpice.total_price;
                        // The current capacity then gets the current qty subtracted from it
                        currentCapacity -= currentSpice.qty;
                        spicesUsed += currentSpice.qty + " scoops of " + currentSpice.name + " ";
                    } else {
                        // Divides the current capacity by current spice quantity.
                        // This number is used to multiply by the total price
                        // which in return gives us how much spice to take
                        float fraction = (float) currentCapacity / currentSpice.qty;
                        float newVal = currentSpice.total_price * fraction;
                        runningTotal += (int) newVal;

                        spicesUsed += currentCapacity + " scoops of " + currentSpice.name
                                + " ";
                        currentCapacity = 0;

                    }
                }
            }

            // This prints out the current capacity and the spices collected
            System.out.println("Knapsack of capacity " + capacities.get(i) + " is worth "
                    + runningTotal + " and contains " + spicesUsed);
            spicesUsed = "";
        }

    }

    public static void createGraph(String[] graphArrayTxtFile) {
        // create new graph object
        graph newGraph = new graph();
        for (int i = 0; i < graphArrayTxtFile.length; i++) {

            // This skips the first new graph since there isn't any data in the graph
            if (graphArrayTxtFile[i].equals("new graph")) {
                if (newGraphCount == 0) {
                    newGraphCount += 1;
                    continue;
                }

                newGraph.bellman(newGraph);
                newGraph = new graph();

            } else {
                // if line is empty, it will go to the next iteration
                if (graphArrayTxtFile[i].equals("") || graphArrayTxtFile[i].equals(" ")) {
                    continue;
                }

                // splits the current line
                String findArray[] = graphArrayTxtFile[i].split(" ");

                // checks to see if line is a vertex
                if (findArray[1].equals("vertex")) {

                    // if vertex is in the line, a new graphNode is created
                    // and added to the vertices array in the graph object
                    graphNode newGraphNode = new graphNode();
                    newGraphNode.from = Integer.parseInt(findArray[2]);
                    newGraph.vertices.add(newGraphNode);

                }
                // if the line is an edge, it will branch here
                else if (findArray[1].equals("edge")) {

                    // This will create a new graph node
                    graphNode newGraphNode = new graphNode();

                    // adds the from, to and weight to the graph nodes.
                    newGraphNode.to = Integer.parseInt(findArray[4]);
                    newGraphNode.from = Integer.parseInt(findArray[2]);
                    if (findArray.length == 6) {
                        newGraphNode.weight = Integer.parseInt(findArray[5]);
                    } else {
                        newGraphNode.weight = Integer.parseInt(findArray[6]);
                    }

                    // This adds the edge or graph node into the edges array
                    newGraph.edges.add(newGraphNode);
                }

            }

        }
        // this calls the bellman for the last graph implementation
        newGraph.bellman(newGraph);

    }

    // This functions reads a file and puts its content in an array provided
    public static void readFile(String fileName, String[] newArray) {
        int count = 0;
        try {
            File readFile = new File(fileName);
            Scanner myScanner = new Scanner(readFile);
            while (myScanner.hasNextLine()) {
                String data = myScanner.nextLine();

                newArray[count] = data.toLowerCase();
                count++;
            }
            myScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}

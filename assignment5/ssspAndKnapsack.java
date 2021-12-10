import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class ssspAndKnapsack {
    public static int newGraphCount = 0;

    public static void main(String[] args) {
        String[] txtFileGraph = new String[88];
        String[] spiceArray = new String[14];
        readFile("graphs2.txt", txtFileGraph);
        readFile("spice.txt", spiceArray);

        spiceAlgorithm(spiceArray);
        createGraph(txtFileGraph);
    }

    public static void spiceAlgorithm(String[] spiceArray) {
        ArrayList<Integer> capacities = new ArrayList<Integer>();

        ArrayList<spice> spiceObjects = new ArrayList<spice>();

        for (int i = 0; i < spiceArray.length; i++) {
            String[] lineSplit = spiceArray[i].split(" ");
            if (lineSplit[0].equals("spice")) {
                String[] firstSplit = spiceArray[i].split(";");
                String[] nameLine = firstSplit[0].split(" ");
                String[] priceLine = firstSplit[1].split(" ");
                String[] quantityLine = firstSplit[2].split(" ");
                spice newSpice = new spice();

                int price = (int) Float.parseFloat(priceLine[priceLine.length - 1]);
                int quantity = Integer.parseInt(quantityLine[quantityLine.length - 1]);
                String name = nameLine[nameLine.length - 1];

                newSpice.total_price = price;
                newSpice.qty = quantity;
                newSpice.name = name;
                newSpice.unity_price = price / quantity;

                spiceObjects.add(newSpice);
            }
            if (lineSplit[0].equals("knapsack")) {
                String[] firstSplit = spiceArray[i].split(";");
                String[] getCapacity = firstSplit[0].split(" ");

                capacities.add(Integer.parseInt(getCapacity[getCapacity.length - 1]));
            }
        }

        int arrayLength = spiceObjects.size();
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

            int runningTotal = 0;
            int currentCapacity = capacities.get(i);
            for (int k = 0; k < spiceObjects.size(); k++) {
                spice currentSpice = spiceObjects.get(k);
                if (currentCapacity == 0) {
                    break;
                } else {
                    if (currentCapacity >= currentSpice.qty) {

                        runningTotal += currentSpice.total_price;
                        currentCapacity -= currentSpice.qty;
                    } else {
                        float man = (float) currentCapacity / currentSpice.qty;

                        float newVal = currentSpice.total_price * man;
                        runningTotal += (int) newVal;
                        currentCapacity = 0;
                    }
                }
            }
            System.out.println("Knapsack of capacity " + capacities.get(i) + " is worth "
                    + runningTotal);
        }

    }

    public static void createGraph(String[] graphArrayTxtFile) {
        graph newGraph = new graph();
        for (int i = 0; i < graphArrayTxtFile.length; i++) {

            if (graphArrayTxtFile[i].equals("new graph")) {
                if (newGraphCount == 0) {
                    newGraphCount += 1;
                    continue;
                }

                newGraph.bellman(newGraph);
                newGraph = new graph();

            } else {

                if (graphArrayTxtFile[i].equals("") || graphArrayTxtFile[i].equals(" ")) {
                    continue;
                }
                String findArray[] = graphArrayTxtFile[i].split(" ");

                if (findArray[1].equals("vertex")) {

                    graphNode newGraphNode = new graphNode();
                    newGraphNode.from = Integer.parseInt(findArray[2]);
                    newGraph.vertices.add(newGraphNode);

                } else if (findArray[1].equals("edge")) {

                    graphNode newGraphNode = new graphNode();
                    newGraphNode.to = Integer.parseInt(findArray[4]);
                    newGraphNode.from = Integer.parseInt(findArray[2]);

                    if (findArray.length == 6) {
                        newGraphNode.weight = Integer.parseInt(findArray[5]);
                    } else {
                        newGraphNode.weight = Integer.parseInt(findArray[6]);
                    }

                    newGraph.edges.add(newGraphNode);
                    ;
                }

            }

        }
        newGraph.bellman(newGraph);

    }

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

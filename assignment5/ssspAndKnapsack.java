import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap; // import the HashMap class
import java.util.Arrays;
import java.util.Collections;

public class ssspAndKnapsack {
    public static int newGraphCount = 0;

    public static void main(String[] args) {
        String[] txtFileGraph = new String[666];
        String[] spiceArray = new String[14];
        readFile("graphs2.txt", txtFileGraph);
        readFile("spice.txt", spiceArray);

        spiceAlgorithm(spiceArray);
    }

    public static void spiceAlgorithm(String[] spiceArray) {
        ArrayList<Integer> capacities = new ArrayList<Integer>();
        HashMap<Integer,  spice> valueToName = new HashMap<Integer, spice>();
        ArrayList<Integer> values = new ArrayList<Integer>();

        for (int i = 0; i < spiceArray.length; i++) {
            String[] lineSplit = spiceArray[i].split(" ");
            if (lineSplit[0].equals("spice")) {
                String[] firstSplit = spiceArray[i].split(";");
                String[] nameLine = firstSplit[0].split(" ");
                String[] priceLine = firstSplit[1].split(" ");
                String[] quantityLine = firstSplit[2].split(" ");
                spice newSpice = new spice(); 

                int price = (int)Float.parseFloat(priceLine[priceLine.length-1]);
                int quantity = Integer.parseInt(quantityLine[quantityLine.length-1]);

                String name = nameLine[nameLine.length-1];
                newSpice.total_price = price;
                newSpice.qty = quantity;
                newSpice.name = name;
                
                values.add(price/quantity);
                valueToName.put(price / quantity, newSpice);
            }
            if (lineSplit[0].equals("knapsack")) {
                 String [] firstSplit = spiceArray[i].split(";");
                 String [] getCapacity = firstSplit[0].split(" ");
                
                capacities.add(Integer.parseInt(getCapacity[getCapacity.length - 1]));
            }
        }

        Collections.sort(values);
        
    
        for(int i = 0; i < capacities.size(); i ++){



            int currentCapacity = capacities.get(i);
            for ( int k = 0; k < values.size(); k++){
                if (currentCapacity ==0){
                    break;
                }
                else{
                    if (currentCapacity> valueToName.get())
                }
            }
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
                for (int m = 0; m < newGraph.vertices.size(); m++) {

                }
                newGraph.bellman();
                newGraph.vertices.clear();

            } else {
                String findArray[] = graphArrayTxtFile[i].split(" ");

                if (findArray[1].equals("vertex")) {
                    graphNode newGraphNode = new graphNode();
                    newGraphNode.from = Integer.parseInt(findArray[2]);
                    newGraph.vertices.add(newGraphNode);
                } else {

                    for (int j = 0; j < newGraph.vertices.size(); j++) {
                        if (newGraph.vertices.get(i).from == Integer.parseInt(findArray[2])) {

                            newGraph.vertices.get(i).to = Integer.parseInt(findArray[4]);
                            newGraph.vertices.get(i).weight = Integer.parseInt(findArray[5]);

                        }

                    }
                }

            }
        }
    }

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
}

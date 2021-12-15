import java.util.ArrayList;
import java.util.Random;

public class Simulation{
    private static int case1=0;
    private static int case3=0;
    private static int case2=0;

    public static void main(String []args){
        int array[] = fillArray(1000, .02,8);
        ArrayList<Integer> newArrayList = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++){
            newArrayList.add(array[i]);
        }
        ArrayList<Integer> manArrayList = new ArrayList<Integer>();
        int totalCase = 0;
        for (int i =0; i < newArrayList.size(); i++){
            manArrayList.add(newArrayList.get(i));
            if (manArrayList.size() ==8){
                totalCase += caseStudies(manArrayList, 8);
            }
        }
        System.out.println("Case 1: " + case1);
        System.out.println("Case 2: " + case2);
        System.out.println("Case 3: " + case3);
        System.out.println("total: " + (case3 + case1 + case2));
        System.out.println("total: " + (totalCase));

    }
    public static int[] fillArray(int population, double infectionRate,int testSize){
        int populationOfPeople[] = new int[population];
       
        int curCount = 0;
        Random rand = new Random();
        while (curCount != (population * infectionRate)){
            int nextIndexToBeInfected  = rand.nextInt(population);
            populationOfPeople[nextIndexToBeInfected] = 1;
            curCount +=1;
        }
        return populationOfPeople;
    }
    public static int caseStudies(ArrayList<Integer> populationArray, int testSize){

        //Meaning 1 isn't in the current array which will return 1 for the current array
        if (populationArray.indexOf(1) == -1){
            return 1;
        }
        ArrayList<Integer> newArray = new ArrayList<Integer>(testSize);
        for (int i= 0; i < populationArray.size(); i++){
            newArray.add(populationArray.get(i));
            if (newArray.size() ==testSize){
            caseStudies(newArray, testSize);
            }
        }
        return 0;
    }
}
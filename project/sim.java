import java.util.ArrayList;
import java.util.Random;

public class sim{
    private static int case1=0;
    private static int case3=0;
    private static int case2=0;

    public static void main(String []args){
        int array[] = fillArray(Integer.parseInt(args[0]), .02,8);
        int counter = 0;
        for (int i = 0; i <array.length; i++){
            if (array[i] == 1){
                counter +=1;
            }
        }
        System.out.print(counter);
        ArrayList<Integer> newArrayList = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++){
            newArrayList.add(array[i]);
            if (newArrayList.size() ==8){
                caseStudies(newArrayList, 8);
                newArrayList.clear();
            }
        }
        

        System.out.println("Case 1: " + case1);
        System.out.println("Case 2: " + case2);
        System.out.println("Case 3: " + case3  + "");
        System.out.println("total: " + (case3 + case1 + case2));

    }
    public static int[] fillArray(int population, double infectionRate,int testSize){
        int populationOfPeople[] = new int[population];
       
        int curCount = 0;
        Random rand = new Random();
        while (curCount != (population * infectionRate)){
            int nextIndexToBeInfected  = rand.nextInt(population);
            
            if (populationOfPeople[nextIndexToBeInfected] == 0){
                curCount +=1;
                populationOfPeople[nextIndexToBeInfected] = 1;
            }
            
        }
        return populationOfPeople;
    }
    public static void caseStudies(ArrayList<Integer> populationArray, int testSize){
        ArrayList<Integer> studiesOne = new ArrayList<Integer>();
        ArrayList<Integer> studiesTwo = new ArrayList<Integer>();
        if (populationArray.indexOf(1) == -1){
            case1 +=1; 
            return;
        }
        else{
            for (int i = 0;i< populationArray.size(); i ++){
                if (i <= 3){
                    studiesOne.add(populationArray.get(i));


                }
                else{
                    studiesTwo.add(populationArray.get(i));

                }

            }  
            
            if (studiesOne.contains(1)){
                if (studiesTwo.contains(1)){
                    case3+=11;
                    
                }
                else{
                    case2+=7;
                }
            }
            else{
                case2+=7;

            }
        }
    }
}
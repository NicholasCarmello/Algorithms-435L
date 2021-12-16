import java.util.ArrayList;
import java.util.Random;

public class sim{
    private static int case1=0;
    private static int case3=0;
    private static int case2=0;

    public static void main(String []args){
        //Calls the fill array method on this array.
        int array[] = fillArray(Integer.parseInt(args[0]), .02);
        
        ArrayList<Integer> newArrayList = new ArrayList<Integer>();

        //This will call the case studies functio everytime the array is length 8
        for (int i = 0; i < array.length; i++){
            newArrayList.add(array[i]);
            if (newArrayList.size() ==8){
                caseStudies(newArrayList, 8);
                newArrayList.clear();
            }
        }
        caseStudies(newArrayList, newArrayList.size());


        //prints out the expected case studies found on the project site and also what the simulation created.
        System.out.println("Case (1): "  + (Integer.parseInt(args[0]) / 8) + " X " + ".85 = " 
        + ((Integer.parseInt(args[0]) / 8) * .85) +  " instances requiring " + case1 +  " tests.");
        System.out.println("Case (2): "  + (Integer.parseInt(args[0]) / 8) + " X " + ".1496 = " 
        + ((Integer.parseInt(args[0]) / 8) * .1496) +  " instances requiring " + case2 +  " tests.");
        System.out.println("Case (3): "  + (Integer.parseInt(args[0]) / 8) + " X " + ".0004 = " 
        + ((Integer.parseInt(args[0]) / 8) * .0004) +  " instances requiring " + case3 +  " tests.");
       
       //Prints out the total tests for the simulation.
        System.out.println((case2+case1+case3) +" tests to screen a population of " + args[0] + " people for a disease with " + .02 + " infection rate.");

    }
    //This will polute the array with 0's and 1's. Amount depends on infection rate and population
    public static int[] fillArray(int population, double infectionRate){
        int populationOfPeople[] = new int[population];
        int curCount = 0;
        Random rand = new Random();

        //This loop will infect the population * infectionRate.
        //Ex: population is 1000 and infectionRate is .02, then 20 people will be infected.
        while (curCount != (population * infectionRate)){
            int nextIndexToBeInfected  = rand.nextInt(population);
            if (populationOfPeople[nextIndexToBeInfected] == 0){
                curCount +=1;
                populationOfPeople[nextIndexToBeInfected] = 1;
            }
            
        }
        //Returns the array of infected people
        return populationOfPeople;
    }
    public static void caseStudies(ArrayList<Integer> populationArray, int testSize){

        //2 arrayLists for the groups of 4
        ArrayList<Integer> studiesOne = new ArrayList<Integer>();
        ArrayList<Integer> studiesTwo = new ArrayList<Integer>();

        //If the populationArray is of size 8 and has no infected people in it, it will then
        //return and increase case1 by 1
        if (populationArray.indexOf(1) == -1){
            case1 +=1; 
            return;
        }
        //The else statement will first ut the first half of the array into one array
        //and the other half in the second array
        else{
            for (int i = 0;i< populationArray.size(); i ++){
                if (i <= 3){
                    studiesOne.add(populationArray.get(i));


                }
                else{
                    studiesTwo.add(populationArray.get(i));

                }

            }  
            
            //The first group is then checked for covid, and if they are infected,
            // it will check to see if the second array has covid.
            //If the second array has covid and the first, then the case3 is increased by 11.
            //Else the case2 is increased by 7
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
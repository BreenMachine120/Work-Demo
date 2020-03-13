/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack;


import java.util.Arrays;
import java.util.Random;
/**
 *
 * @author Mike
 */
public class Knapsack {

    private static final Random randGenerator = new Random();

    private static final String[] WORDS = {"CCSCNE", "is", "the", "northeastern", "region", "of", "the", 
    "national", "Consortium", "for", "Computing", "Sciences", "in", "Colleges", "(CCSC)", "Started", "in", 
    "1996", "CCSCNE", "is", "one", "of", "the", "largest", "regions", "of", "CCSC", "CCSCNE", "brings", 
    "together", "faculty", "staff", "and", "students", "from", "academic", "institutions", "throughout", 
    "the", "Northeast", "for", "exchange", "of", "ideas", "and", "information", "concerning", "undergraduate", 
    "computing", "curricula", "This", "conference", "provides", "a", "regional", "forum", "for", "the", "exchange", 
    "of", "information", "and", "ideas", "pertaining", "to", "the", "concerns", "of", "computing", "and", "computing", 
    "curricula", "in", "a", "smaller", "academic", "environment", "CCSCNE", "holds", "a", "refereed", "conference", "every", 
    "Spring", "also", "called", "CCSCNE", "This", "conference", "draws", "participants", "from", "all", "over", "the", "northeast", 
    "and", "eastern", "United", "States", "The", "proceedings", "of", "the", "conference", "are", "published", "as", "an", "issue", 
    "of", "the", "Journal", "of", "Computing", "Sciences", "in", "Colleges"};
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       String[][] container = createContainer(WORDS.length);
       organizeWords(container);
       printContainer(container);
    }
    
    private static void organizeWords(String[][] container){
        int n = 0;
        for (int i = 0; i < container.length; i++) {
            for (int j = 0; j < container[i].length; j++) {
                container[i][j] = WORDS[n];
                n++;
            }
        }
    }

    private static void printContainer(String[][] container){
        for(int i = 0; i < container.length; i++){
            for(int j = 0; j < container[i].length; j++)
                System.out.print(" "+container[i][j]);
            System.out.println();
        }
    }

    private static String[][] createContainer(int numOfChars){

        String[][] container = new String[1][]; 
        if(numOfChars < 4){
            container[container.length-1] = new String[numOfChars];
            return container;
        }
        else {
            int randContainerSize = randGenerator.nextInt(numOfChars)+1;

            container[container.length-1] = new String[randContainerSize];
            return createContainer(numOfChars-randContainerSize, container);
                
        }
    }

    private static String[][] createContainer(int numOfChars, String[][] container){
        container = Arrays.copyOf(container, container.length + 1); // resize array to accomadate new size 

        if(numOfChars < 4){
            container[container.length-1] = new String[numOfChars];
            return container;
        }
        else {
            int randContainerSize = randGenerator.nextInt(numOfChars)+1;

            container[container.length-1] = new String[randContainerSize];
            return createContainer(numOfChars-randContainerSize, container);

        }
    }
}

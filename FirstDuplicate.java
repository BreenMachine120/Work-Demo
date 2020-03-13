/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstduplicate;

import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author Mike
 */
public class FirstDuplicate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random generator = new Random();
        Scanner scan = new Scanner(System.in);
        
        String answer;
        boolean sentinel;
        
        do {
            // Generate number
            String number = "";
            for (int i = 0; i < 11; i++) {
                number += generator.nextInt(10);
            }
            System.out.println(number);
            
            // Scan through number
            sentinel = false;
            for (int i = 0; i < number.length(); i++) {
                if (sentinel == false) {
                    for (int j = 0; j < i; j++) {
                        if (number.charAt(i) == number.charAt(j)) {
                            System.out.println("Result: " + number.charAt(i));
                            sentinel = true;
                            break;
                        }
                    }
                }
            }
            
            // If no repeats
            if (sentinel == false) {
                System.out.println("No repeating numbers");
            }
            
            // Ask to repeat
            System.out.print("Run again (y/n): ");
            answer = scan.next();
            
        } while(answer.equals("y"));
    }
    
}

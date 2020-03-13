/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bracket;

import java.util.Scanner;
/**
 *
 * @author Mike
 */
public class Bracket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        String input;
        System.out.print("Enter input: ");
        input = scan.next();
        
        boolean result = true;
        
        String openTracker = "";
        for (int i = 0; i < input.length(); i++) {
            char bracket = input.charAt(i);
            switch (bracket) {
                case '(':
                    openTracker += bracket;
                    break;
                case ')':
                    if (!(openTracker.charAt(openTracker.length()-1)=='(')){
                        result = false;
                        break;
                    }
                        
                    openTracker = openTracker.substring(0, openTracker.length()-1);
                    break;
                case '[':
                    openTracker += bracket;
                    break;
                case ']':
                    if (!(openTracker.charAt(openTracker.length()-1)=='[')){
                        result = false;
                        break;
                    }   
                    openTracker = openTracker.substring(0, openTracker.length()-1);
                    break;
                case '{':
                    openTracker += bracket;
                    break;
                case '}':
                    if (!(openTracker.charAt(openTracker.length()-1)=='{')){
                        result = false;
                        break;
                    } 
                    openTracker = openTracker.substring(0, openTracker.length()-1);
                    break;
                default:
                    result = false;
                    break;
            }
            if (result == false)
                break;
        }
        System.out.println(result);
     }
}

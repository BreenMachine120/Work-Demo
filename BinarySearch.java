/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearch;

/**
 *
 * @author Mike
 */
public class BinarySearch {

    /**
     * @param args the command line arguments
     */
    
    // Lowercase alphabet
    public static String alpha = "abcdefghijklmnopqrstuvwxyz";
    
    //Binary Search function
    public char BinarySearch(char search, String alpha) {
        // If search is not found in alpha
        if (alpha.length() == 1 && alpha.charAt(0) != search) {
            return '0';
        }
        // If search is below the middle index of alpha
        else if ((int) search < (int) alpha.charAt(alpha.length()/2)) {
            return BinarySearch(search, alpha.substring(0, alpha.length()/2));
        }
        // If search is above the middle index of alpha
        else if ((int) search > (int) alpha.charAt(alpha.length()/2)) {
            return BinarySearch(search, alpha.substring(alpha.length()/2, alpha.length()));
        }
        // If search is equal to the middle index of alpha
        else {
            return alpha.charAt(alpha.length()/2);
        }
    }
    
    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        System.out.println(bs.BinarySearch('5', alpha));
    }
    
}

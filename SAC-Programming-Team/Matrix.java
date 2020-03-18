/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author Mike
 */
public class Matrix {

    /**
     * @param args the command line arguments
     */
    private static final Scanner scan = new Scanner(System.in);
    
    private static final Random generator = new Random();
    
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public static void main(String[] args) {
        int size;
        String repeat;
        char[][] matrix, repeats;
        
        do {
            System.out.print("Enter size of matrix (in between 3 and 10, inclusive): ");
            size = scan.nextInt();
            
            matrix = matrixCreate(size);
            matrixPrint(matrix);
            
            repeats = matrixRepeats(matrix);
            matrixPrint(repeats);
            
            System.out.print("Repeat (y/n)? ");
            repeat = scan.next();
        } while (repeat.equals("y"));
    }
    
    public static void matrixPrint(char[][] matrix) {
        System.out.println();
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        
        System.out.println();
    }
    
    public static char[][] matrixCreate(int size) {
        char[][] matrix = new char[size][size];
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = LETTERS.charAt(generator.nextInt(LETTERS.length()));
            }
        }
        
        return matrix;
    }
    
    public static char[][] matrixRepeats(char[][] matrix) {
        int size = matrix.length;
        char[][] repeats = new char[size][size];
        
        // Fill with '-'
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                repeats[i][j] = '-';
            }
        }
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // First row
                if (i == 0) {
                    if (matrix[0][j] == matrix[1][j]) {
                        repeats[i][j] = matrix[i][j];
                    }
                }
                // Last row
                else if (i == size-1) {
                    if (matrix[size-1][j] == matrix[size-2][j]) {
                        repeats[i][j] = matrix[i][j];
                    }
                }
                // Middle rows
                else {
                    if (matrix[i][j] == matrix[i+1][j] || matrix[i][j] == matrix[i-1][j]) {
                        repeats[i][j] = matrix[i][j];
                    }
                }
                
                // First column
                if (j == 0) {
                    if (matrix[i][0] == matrix[i][1]) {
                        repeats[i][j] = matrix[i][j];
                    }
                }
                // Last column
                else if (j == size-1) {
                    if (matrix[i][size-1] == matrix[i][size-2]) {
                        repeats[i][j] = matrix[i][j];
                    }
                }
                // Middle columns
                else {
                    if (matrix[i][j] == matrix[i][j+1] || matrix[i][j] == matrix[i][j-1]) {
                        repeats[i][j] = matrix[i][j];
                    }
                }
                
                // Diagonals
                if (0 < i && i < size-1 && 0 < j && j < size-1) {
                    if (matrix[i][j] == matrix[i+1][j+1] || matrix[i][j] == matrix[i+1][j-1] ||
                        matrix[i][j] == matrix[i-1][j+1] || matrix[i][j] == matrix[i-1][j-1]) {
                        repeats[i][j] = matrix[i][j];
                    }
                }
            }
        }
        
        return repeats;
    }
    
}

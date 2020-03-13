/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Mike
 */
public class Battleship {
    
    public static Random generator = new Random();
    
    public static Scanner scan = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create board
        System.out.print("Enter board size: ");
        int boardSize = scan.nextInt();
        System.out.println();
        
        int[][] board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = 0;
            }
        }
        
        int[] ships = {5, 4, 3, 3, 2};
        
        int shipSize, randX, randY, randZ;
        boolean collide;
        
        for (int ship = 0; ship < ships.length; ship++) {
            shipSize = ships[ship];
            
            do {
                collide = false;
                
                randZ = generator.nextInt(2);   // orientation
                if (randZ == 0) {
                    randX = generator.nextInt(boardSize - shipSize + 1);
                    randY = generator.nextInt(boardSize);
                }
                else {
                    randX = generator.nextInt(boardSize);
                    randY = generator.nextInt(boardSize - shipSize + 1);
                }
                
                // horizontal collision check
                if (randZ == 0) {
                    if (randY == 0) {   // top row
                        for (int i = 0; i < shipSize; i++) {
                            if (board[randX + i][randY + 1] != 0) {
                                collide = true;
                            }
                        }
                    }
                    else if (randY == boardSize - 1) {  // bottom row
                        for (int i = 0; i < shipSize; i++) {
                            if (board[randX + i][randY - 1] != 0) {
                                collide = true;
                            }
                        }
                    }
                    else {  // middle rows
                        for (int i = 0; i < shipSize; i++) {
                            if (board[randX + i][randY + 1] != 0 || board[randX + i][randY - 1] != 0) {
                                collide = true;
                            }
                        }
                    }
                    if (randX == 0) {    // left column
                        if (board[shipSize + 1][randY] != 0) {
                            collide = true;
                        }
                    }
                    else if (randX == boardSize - shipSize) {   // right column
                        if (board[randX - 1][randY] != 0) {
                            collide = true;
                        }
                    }
                    else if (board[randX - 1][randY] != 0 || board[randX + shipSize][randY] != 0) { // middle columns
                        collide = true;
                    }
                }
                // vertical collision check
                else if (randZ == 1) {
                    if (randX == 0) {   // left column
                        for (int i = 0; i < shipSize; i++) {
                            if (board[randX + 1][randY + i] != 0) {
                                collide = true;
                            }
                        }
                    }
                    else if (randX == boardSize - 1) {  // right column
                        for (int i = 0; i < shipSize; i++) {
                            if (board[randX - 1][randY + i] != 0) {
                                collide = true;
                            }
                        }
                    }
                    else {  // middle columns
                        for (int i = 0; i < shipSize; i++) {
                            if (board[randX + 1][randY + i] != 0 || board[randX - 1][randY + i] != 0) {
                                collide = true;
                            }
                        }
                    }
                    if (randY == 0) {   // top row
                        if (board[randX][shipSize + 1] != 0) {
                            collide = true;
                        }
                    }
                    else if (randY == boardSize - shipSize) {   // bottom row
                        if (board[randX][randY - 1] != 0) {
                            collide = true;
                        }
                    }
                    else if (board[randX][randY - 1] != 0 || board[randX][randY + shipSize] != 0) { // middle rows
                        collide = true;
                    }
                }
                
            } while (collide);  // repeat if there is a collision
            
            // Place ships
            if (randZ == 0) {
                for (int i = 0; i < shipSize; i++) {
                    board[randX + i][randY] = 1;
                }
            }
            else {
                for (int i = 0; i < shipSize; i++) {
                    board[randX][randY + i] = 1;
                }
            }
        }
        
        // Display board
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}

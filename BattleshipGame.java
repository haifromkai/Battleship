package battleship;

import java.util.Scanner;

/**
 * This is the “main” class, containing the main method.
 */

public class BattleshipGame {
    public static void main(String args[]) {
        // use scanner for player input
        Scanner s = new Scanner(System.in);

        //intro message
        System.out.println("Welcome to the game of battleship!");

        // start by creating an instance of Ocean
        Ocean ocean = new Ocean();
        // set up the game
        ocean.placeAllShipsRandomly();

        //print ocean
        ocean.print();

        // loop while isGameOver is false
        while (!ocean.isGameOver()) {
            try {
                // prompt user to enter row
                System.out.println("Enter row: ");
                // taking first input as row and taking second input as column
                int row = s.nextInt();
                // prompt user to enter column
                System.out.println("Enter column: ");
                int col = s.nextInt();

                // call shootAt method with given row and column, if hit ship update hit array of ship
                if (ocean.shootAt(row, col)) {
                    System.out.println("You hit a ship at (" + row + " , " + col + ")!");
                }
                else {
                    System.out.println("You missed.");
                }
                //print ocean after each shot
                ocean.print();
            }
            catch (Exception e) {
                System.out.println("Error: Please enter a valid integer.");
                s.next();
            }
        }
        //close scanner
        s.close();
        // print final scores
        System.out.println();
        System.out.println("Final Score:\n" +
                "Shots Fired: " + ocean.getShotsFired() + "\n" +
                "Hits: " + ocean.getHitCount() + "\n" +
                "Ships Sunk: " + ocean.getShipsSunk());
    }
}

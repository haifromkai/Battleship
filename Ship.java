package battleship;

import java.util.Arrays;

/**
 * This abstract class describes the characteristics common to all ships
 */
public abstract class Ship {

    // Instance Variables

    /**
     * The row that contains the bow (front part of the ship)
     */
    private int bowRow;

    /**
     * The column that contains the bow (front part of the ship)
     */
    private int bowColumn;

    /**
     * The length of the ship
     */
    private int length;

    /**
     * A boolean that represents whether the ship is going to be placed horizontally or vertically
     */
    private boolean horizontal;

    /**
     * An array of booleans that indicate whether that part of the ship has been hit or not
     */
    private boolean[] hit;


    // Constructor

    /**
     * This constructor sets the length property of the particular ship
     * and initializes the hit array based on that length
     * @param length of the ship
     */
    public Ship(int length) {
        this.length = length;
        // create boolean array of length size
        this.hit = new boolean[length];

        // set each array element as boolean false to indicate entire ship is not hit
        for (int i = 0; i < length; i++) {
            hit[i] = false;
        }
    }


    // Getter Methods

    /**
     * Get the ship length
     * @return length of ship
     */
    public int getLength() {
        return length;
    }

    /**
     * Get the row corresponding to the position of the bow
     * @return the row corresponding to the position of the bow
     */
    public int getBowRow() {
        return bowRow;
    }

    /**
     * Get the bow column location
     * @return the bow column location
     */
    public int getBowColumn() {
        return bowColumn;
    }

    /**
     * Get the hit array
     * @return the hit array
     */
    public boolean[] getHit() {
        return hit;
    }

    /**
     * Returns whether the ship is horizontal or not
     * @return whether the ship is horizontal or not
     */
    public boolean isHorizontal() {
        return horizontal;
    }


    // Setter Methods

    /**
     * Sets the value of bowRow
     * @param row of the bow of ship
     */
    public void setBowRow(int row) {
        this.bowRow = row;
    }

    /**
     * Sets the value of bowColumn
     * @param column of the bow of the ship
     */
    public void setBowColumn(int column) {
        this.bowColumn = column;
    }

    /**
     * Sets the value of the instance variable horizontal
     * @param horizontal of the instance variable
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }


    // Abstract Methods

    /**
     * Returns the type of ship as a String, every type of ship must override and
     * implement this method and return corresponding ship type
     * @return the type of ship as a String
     */
    public abstract String getShipType();


    // Other Methods

    /**
     * Based on the given row, column, and orientation, returns true if it is okay to put a ship of this length
     * with its bow in this location; false otherwise
     * Ship must not overlap another ship, or touch another ship (vertically, horizontally,
     * or diagonally), and it must not ”stick out” beyond the array.
     * Does not actually change either the ship or the Ocean - it just says if it is legal to do so.
     * @param row of bow of ship
     * @param column of bow of ship
     * @param horizontal or not for ship
     * @param ocean of the game (10x10 grid)
     * @return boolean value if it is ok to place ship of this length with its bow in given location
     */
    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {

        // Check if Ship would be out of bounds
        // HORIZONTAL ORIENTATION means check if LEFT edge is valid
        if (horizontal) {
            if (length - column > 1) {
                //System.out.println("Left edge not valid, retrying with new generated numbers");
                return false;
            }

            // Check for Ship Overlaying and Touching (skip iterations that would be off the 10x10)
            // Iterate columns (+1 to -1 of ship spaces)
            for (int c = column + 1; c >= column - length; c--) {
                // Iterate rows (+1 to -1 of ship spaces)
                for (int r = row + 1; r >= row - 1; r--) {
                    // Skip iterations of r,c outside the 10x10 grid
                    if (c < 0 || c > 9 || r < 0 || r > 9) {
                        continue;
                    }
                    // Check if space is occupied by non EmptySea Ship
                    else if (ocean.isOccupied(r, c)) {
                        //System.out.println("Space is occupied by a ship");
                        return false;
                    }
                }
            }
        }

        // Check if Ship would be out of bounds
        // VERTICAL ORIENTATION means check if TOP edge is valid
        else {
            if (length - row > 1) {
                //System.out.println("Top edge not valid, retrying with new generated numbers");
                return false;
            }

            // Check for Ship Overlaying and Touching (skip iterations that would be off the 10x10)
            // Iterate columns (+1 to -1 of ship spaces)
            for (int c = column + 1; c >= column - 1; c--) {
                // Iterate rows (+1 to -1 of ship spaces)
                for (int r = row + 1; r >= row - length; r--) {
                    // Skip iterations of r,c outside the 10x10 grid
                    if (c < 0 || c > 9 || r < 0 || r > 9) {
                        continue;
                    }
                    // Check if space is occupied by non EmptySea Ship
                    else if (ocean.isOccupied(r, c)) {
                       // System.out.println("Space is occupied by a ship");
                        return false;
                    }
                }
            }
        }
        // All placement conditions were valid
        //System.out.println("Returned True");
        return true;
    }


    /**
     * “Puts” the ship in the ocean with the given row, column, orientation and ocean grid
     * @param row of bow of ship
     * @param column of bow of ship
     * @param horizontal or not for ship
     * @param ocean of the game (10x10 grid)
     */
    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {

        // call setter methods to establish ship bow row, column, and orientation
        setBowRow(row);
        setBowColumn(column);
        setHorizontal(horizontal);

        // increment i through length of ship
        for (int i = 0; i < this.getLength(); i++) {
            // if ship orientation is horizontal (bow faces east)
            if (horizontal) {
                // place this Ship Obj
                ocean.getShipArray()[row][column - i] = this;
            }
            // otherwise ship orientation is vertical (bow faces south)
            else {
                // place this Ship Obj
//                System.out.println("Row: " + row + " Column:" + column + ", i: " + i);
                ocean.getShipArray()[row - i][column] = this;
            }
        }
    }

    /**
     * shoot the given row and column given, if the ship hasn’t been sunk, mark that part of the ship as “hit” and
     * return true, otherwise false
     * @param row of shooting at
     * @param column of shooting at
     * @return true if ship is hit, otherwise false
     */
    boolean shootAt(int row, int column) {
        // if ship has not been sunk
        if(!this.isSunk()){
        // if ship is horizontal, loop over i that is less than this.ship length
            if(this.isHorizontal()){
                for(int i = 0; i < this.getLength(); i++){
                    // if the given row is same as the ship bowRow, and if the given column is same as bowColumn - i
                    if(this.bowRow == row && column == this.bowColumn - i){
                        // update the hit array of that ship at the ith position to be true and return true
                        this.getHit()[i] = true;
                        return true;
                    }
                }
            }
            //if ship is vertical, loop over i that is less than this.ship length
            else{
                for(int i = 0; i < this.getLength(); i++){
                    // if the given column is same as bowColumn and if given row is same as bowRow - i
                    if(this.bowColumn == column && row == this.bowRow - i){
                        // update the hit array of that ship at the ith position to be true and return true
                        this.getHit()[i] = true;
                        return true;
                    }
                }
            }
        }
        // otherwise return false, if nothing was hit or ship is sunk
        return false;
    }

    /**
     * Check if every part of the ship has been hit
     * @return true if every part of the ship has been hit, false otherwise
     */
    boolean isSunk() {
        // flag set to true initially for sunk
        boolean sunk = true;
        //loop over each boolean value in hit array
        for (boolean h : this.getHit()) {
            // if h is false, set sunk to false, return sunk
            if (!h) {
                sunk = false;
                break;
            }
        }
        //return sunk variable
        return sunk;
    }

    /**
     * Returns a single-character String to use in the Ocean’s print method
     * return ”s” if the ship has been sunk and ”x” if it has not been sunk
     * used to print out locations in the ocean that have been shot at
     * should not be used to print locations that have not been shot at
     * @return a single-character String to use in the Ocean’s print method
     */
    @Override
    public String toString() {
        // if isSunk is true, return single char "s"
        if (isSunk()){
            return "s";
        }
        //otherwise if isSunk is false, return single char "x"
        else {
            return "x";
        }
    }
}
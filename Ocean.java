package battleship;

import java.util.Random;

/**
 * This contains a 10x10 array of Ships, representing an “Ocean”, and some methods to manipulate it
 */
public class Ocean {

    // Instance Variables

    /**
     * Used to determine which ship is in any given location
     */
    private Ship[][] ships = new Ship[10][10];

    /**
     * The total number of shots fired by the user
     */
    private int shotsFired;

    /**
     * The number of times a shot hit a ship. If the user shoots the same part of a ship more than once,
     * every hit is counted, even though additional “hits” don’t do the user any good.
     */
    private int hitCount;

    /**
     * The number of ships sunk (10 ships total)
     */
    private int shipsSunk;

    // Constructor

    /**
     * Creates an ”empty” ocean (and fills the ships array with EmptySea objects).
     * Also initializes any game variables, such as how many shots have been fired.
     */
    public Ocean() {

        // fill ships 10x10 array with EmptySea Objs (Subclass of Class Ship)
        // iterate through rows 0 to 9
        for (int r = 0; r < 10; r++) {
            // iterate through cols 0 to 9
            for (int c = 0; c < 10; c++) {
                // create EmptySea Obj at each r,c position, set bowRow and bowColumn to r,c respectively
                ships[r][c] = new EmptySea();
                ships[r][c].setBowRow(r);
                ships[r][c].setBowColumn(c);
            }
        }

        // initialize game vars
        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;

    }

    // Methods

    /**
     * Place all ten ships randomly on the (initially empty) ocean.
     * Place larger ships first before smaller ships, use random class in the java.util package
     */
    void placeAllShipsRandomly() {

        // create a Random Obj from Java's Random class
        Random rand = new Random();

        // create array of all 10 Ship objects that will be placed in Ocean
        Ship[] shipsToBePlaced = {new Battleship(),
                new Cruiser(), new Cruiser(),
                new Destroyer(), new Destroyer(), new Destroyer(),
                new Submarine(), new Submarine(), new Submarine(), new Submarine()};

        // iterate through each Ship Obj i in the Array shipsToBePlaced
        for (Ship i : shipsToBePlaced) {

            // flag set to false before placing Ship i
            boolean placed = false;

            // while Ship i is not placed
            while (!placed) {

                // generate random row and col with int values b/n 0 inclusive and 10 exclusive
                int r = rand.nextInt(10);
                int c = rand.nextInt(10);
                // generate random boolean value for orientation of placement
                boolean horizontal = rand.nextBoolean();

                // call okToPlaceShipAt method to check if legal to place Ship i in this Ocean location
                if (i.okToPlaceShipAt(r, c, horizontal, this)) {
                    // call placeShipAt method to place Ship i in this Ocean location
                    i.placeShipAt(r, c, horizontal, this);
                    // toggle flag to true
                    placed = true;
                }
            }
        }
    }

    /**
     * Check if the given location contains a ship
     * @param row to be checked
     * @param column to be checked
     * @return true if the given location contains a ship, false if it does not
     */
    boolean isOccupied(int row, int column) {
        // returns false when specified location in ships array contains EmptySea Obj (no ship)
        // returns true otherwise
        return !ships[row][column].getShipType().equals("empty");
    }

    /**
     * Check if the given location contains a ”real” ship, still afloat, (not an EmptySea),
     * false if it does not. In addition, this method updates the number of shots that have been fired,
     * and the number of hits.
     * If a location contains a “real” ship, shootAt should return true every time the user shoots at that same location.
     * Once a ship has been ”sunk”, additional shots at its location should return false.
     * @param row of bow of ship
     * @param column of bow of ship
     * @return true if the given location contains a ”real” ship, still afloat, (not an EmptySea), false if it does not.
     */
    boolean shootAt(int row, int column) {

        // increment # of shots fired by 1
        shotsFired++;

        // if given location in Ship[][] ships contains Ship type "empty" return false
        // by calling shootAt method at empty sea
        if (ships[row][column].getShipType().equals("empty")) {
            return this.ships[row][column].shootAt(row, column);
        }
        // otherwise
        else {
            // if ship is sunk, return false
            if (ships[row][column].isSunk()) {
                return false;
            }
            // increment # of hits and return true
            hitCount++;
            //if shoot at is true, check is ship isSunk is true, increment shipsSunk, then return true
            if (this.ships[row][column].shootAt(row, column)) {
                if (this.ships[row][column].isSunk()) {
                    shipsSunk++;
                }
                return true;
            }
        }
        //otherwise return false
        return false;
    }

    /**
     * Returns the number of shots fired (in the game)
     * @return the number of shots fired (in the game)
     */
    int getShotsFired() {
        return shotsFired;
    }

    /**
     * Returns the number of hits recorded (in the game). All hits are counted, not just
     * the first time a given square is hit.
     * @return the number of hits recorded (in the game).
     */
    int getHitCount() {
        return hitCount;
    }

    /**
     * Returns the number of ships sunk (in the game)
     * @return the number of ships sunk (in the game)
     */
    int getShipsSunk() {
        return shipsSunk;
    }

    /**
     * Returns true if all ships have been sunk, otherwise false
     * @return true if all ships have been sunk, otherwise false
     */
    boolean isGameOver() {
        // return true if shipsSunk is value 10
        return shipsSunk == 10;
    }

    /**
     * Returns the 10x10 array of Ships. The methods in the Ship class that take an Ocean parameter need to
     * be able to look at the contents of this array; the placeShipAt() method even needs to modify it.
     * @return 10x10 array of Ships
     */
    public Ship[][] getShipArray() {
        return ships;
    }

    /**
     * Prints the Ocean. Row numbers should be displayed along the left edge of the array, and column numbers
     * should be displayed along the top. Numbers should be 0 to 9.
     * ‘x’ to indicate a location that you have fired upon and hit a (real) ship.
     * ‘-’ to indicate a location that you have fired upon and found nothing there.
     * ‘s’ to indicate a location containing a sunken ship.
     * ‘.’ (a period) to indicate a location that you have never fired upon
     * The only method in the Ocean class that does any input/output,
     * and it is never called from within the Ocean class, only from the BattleshipGame class.
     */
    void print() {

        // print col numbers on top of array
        System.out.println("  0 1 2 3 4 5 6 7 8 9");

        // print row numbers on left edge of array
        for (int r = 0; r < 10; r++) {
            System.out.print(r + " ");

            // in this row, iterate each column and print appropriate symbol
            for (int c = 0; c < 10; c++) {

                // set default symbol to never fired upon (.)
                String symbol = ".";

                // set each position as a ship object
                Ship ship = this.ships[r][c];

                //get ship's hitarray, bowRow, bowColumn and isHorizontal
                boolean[] shipHitArray = ship.getHit();
                int shipBowRow = ship.getBowRow();
                int shipBowColumn = ship.getBowColumn();
                boolean shipHorizontal = ship.isHorizontal();

                // if ship is not occupied (empty sea), get hit array index 0, call toString of empty sea
                if (!this.isOccupied(r,c)) {
                    if (shipHitArray[0]) {
                        symbol = ship.toString();
                    }
                }
                // if ocean is occupied
                else {
                    // if ship is horizontal, if index of column in the ship object's hit array is true
                    if (shipHorizontal) {
                        if (shipHitArray[shipBowColumn - c]) {
                            // call to String from ship class
                            symbol = ship.toString();
                        }
                    }
                    else {
                        // if ship is vertical, if index of row in the ship object's hit array is true
                        if (shipHitArray[shipBowRow - r]) {
                            // call to String from ship class
                            symbol = ship.toString();
                        }
                    }
                }
                // print symbol established from above
                System.out.print(symbol + " ");
            }
            // blank line before iterating to next row
            System.out.println();
        }
    }
    /**
     * USED FOR DEBUGGING PURPOSES ONLY.
     * prints the Ocean with row and column numbers displayed along the left edge of the array
     * Unlike the print() method, this method shows the location of the ships.
     * ‘b’: Use ‘b’ to indicate a Battleship.
     * ‘c’: Use ‘c’ to indicate a Cruiser.
     * ‘d’: Use ‘d’ to indicate a Destroyer.
     * ‘s’: Use ‘s’ to indicate a Submarine.
     * ‘ ’ (single space) to indicate an EmptySea.
     */
    void printWithShips() {
        // print col numbers on top of array
        System.out.println("  0 1 2 3 4 5 6 7 8 9");

        // print row numbers on left edge of array
        for (int r = 0; r < 10; r++) {
            System.out.print(r + " ");

            // in this row, iterate each column and print appropriate symbol
            for (int c = 0; c < 10; c++) {

                // set default symbol to never fired upon (.)
                String symbol = ".";

                if (ships[r][c].getShipType().equals("battleship")) {
                    symbol = "b";
                }
                else if (ships[r][c].getShipType().equals("cruiser")) {
                    symbol = "c";
                }
                else if (ships[r][c].getShipType().equals("destroyer")) {
                    symbol = "d";
                }
                else if (ships[r][c].getShipType().equals("submarine")) {
                    symbol = "s";
                }
                else if (ships[r][c].getShipType().equals("empty")) {
                    symbol = " ";
                }

                // print symbol established from above
                System.out.print(symbol + " ");
            }
            // blank line before iterating to next row
            System.out.println();
        }
    }


}
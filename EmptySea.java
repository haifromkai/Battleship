package battleship;

/**
 * Describes a part of the ocean that doesn’t have a ship in it.
 */
public class EmptySea extends Ship{

    // Constructor
    /**
     * This zero-argument constructor sets the length variable to 1 by calling constructor in Super Class Ship
     */
    public EmptySea() {
        super(1);
    }

    // Methods

    /**
     * This method overrides shootAt(int row, int column) that is inherited
     * from Ship, and always returns false to indicate that nothing was hit
     * @param row of shooting at
     * @param column of shooting at
     * @return false always 
     */
    @Override
    boolean shootAt(int row, int column) {
        return false;
    }

    /**
     * This method overrides isSunk() that is inherited from Ship, and always
     * returns false to indicate that didn’t sink anything
     * @return false always
     */
    @Override
    boolean isSunk() {
        return false;
    }

    /**
     * Returns the single-character '-' String to use in the Ocean’s print method
     * this is the character to be displayed if a shot has been fired, but nothing has been hit
     * @return the single char '-'
     */
    @Override
    public String toString() {
        return "-";
    }

    /**
     * Returns the string "empty"
     * @return the type of ship as a String
     */
    @Override
    public String getShipType() {
        return "empty";
    }
}

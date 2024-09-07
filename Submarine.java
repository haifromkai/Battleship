package battleship;

/**
 * Describes a ship of length 1
 */
public class Submarine extends Ship {

    // Static Variables
    /**
     * Hard coded length for Submarine Obj
     */
    static final int LENGTH = 1;

    /**
     * Hard coded type for Submarine Obj
     */
    static final String TYPE = "submarine";

    // Constructor
    /**
     * This constructor sets the length property of submarine
     * and initializes the hit array based on that length
     */
    public Submarine() {
        super(LENGTH);
    }

    // Methods
    /**
     * Returns the string "submarine"
     * and return corresponding ship type
     * @return the type of ship as a String
     */
    @Override
    public String getShipType() {
        return TYPE;
    }
}

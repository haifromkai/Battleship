package battleship;

/**
 * Describes a ship of length 3
 */
public class Cruiser extends Ship{

    // Static Variables
    /**
     * Hard coded length for Cruiser Obj
     */
    static final int LENGTH = 3;

    /**
     * Hard coded type for Cruiser Obj
     */
    static final String TYPE = "cruiser";

    // Constructor
    /**
     * This constructor sets the length property of cruiser
     * and initializes the hit array based on that length
     */
    public Cruiser() {
        super(LENGTH);
    }

    // Methods
    /**
     * Returns the string "cruiser"
     * and return corresponding ship type
     * @return the type of ship as a String
     */
    @Override
    public String getShipType() {
        return TYPE;
    }
}

package battleship;

/**
 * Describes a ship of length 2
 */
public class Destroyer extends Ship{

    // Static Variables
    /**
     * Hard coded length for Destroyer Obj
     */
    static final int LENGTH = 2;

    /**
     * Hard coded type for Destroyer Obj
     */
    static final String TYPE = "destroyer";

    // Constructor
    /**
     * This constructor sets the length property of destroyer and initializes
     * the hit array based on that length
     */
    public Destroyer() {
        super(LENGTH);
    }

    // Methods
    /**
     * Returns the string "destroyer"
     * and return corresponding ship type
     * @return the type of ship as a String
     */
    @Override
    public String getShipType() {
        return TYPE;
    }
}

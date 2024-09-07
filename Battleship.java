package battleship;

/**
 * Describes a ship of length 4
 */
public class Battleship extends Ship {

    // Static Variables
    /**
     * Hard coded length for Battleship Obj
     */
    static final int LENGTH = 4;

    /**
     * Hard coded type for Battleship Obj
     */
    static final String TYPE = "battleship";

    // Constructor
    /**
     * This constructor sets the length property of battleship
     * and initializes the hit array based on that length
     */
    public Battleship() {
        super(LENGTH);
    }

    // Methods
    /**
     * Returns the string "battleship"
     * and return corresponding ship type
     * @return the type of ship as a String
     */
    @Override
    public String getShipType() {
        return TYPE;
    }
}

package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		Ship emptysea = new EmptySea();
		Ship cruiser = new Cruiser();

		// test getting battleship length returns 4
		assertEquals(4, ship.getLength());
		
		// test getting emptysea length returns 0
		assertEquals(1, emptysea.getLength());

		// test getting cruiser length returns 3
		assertEquals(3, cruiser.getLength());
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		// test battleship bow was placed in correct row
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		Ship cruiser = new Cruiser();
		int row2 = 5;
		int column2 = 9;
		boolean horizontal2 = false;
		// test cruiser bow was placed in correct row
		battleship.placeShipAt(row2, column2, horizontal2, ocean);
		assertEquals(row, cruiser.getBowRow());

		Ship submarine = new Submarine();
		int row3 = 5;
		int column3 = 6;
		horizontal = true;
		// test submarine bow was placed in correct row
		battleship.placeShipAt(row3, column3, horizontal, ocean);
		assertEquals(row, submarine.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		// test battleship bow was placed in col 4
		assertEquals(column, battleship.getBowColumn());	

		Ship cruiser = new Cruiser();
		int row2 = 2;
		int column2 = 6;
		boolean horizontal2 = true;
		cruiser.placeShipAt(row2, column2, horizontal2, ocean);
		cruiser.setBowColumn(column2);
		// test cruiser bow was placed in col 4
		assertEquals(column2, cruiser.getBowColumn());	

		Ship cruiser2 = new Cruiser();
		int row3 = 2;
		int column3 = 3;
		cruiser2.placeShipAt(row3, column3, horizontal, ocean);
		cruiser2.setBowColumn(column3);
		// test cruiser2's bow was placed in col 3
		assertEquals(column3, cruiser2.getBowColumn());
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];

		// test if battleship's hit array is equal to boolean [] hits
		assertArrayEquals(hits, ship.getHit());
		// test if battleship's hit array index 0 is false
		assertFalse(ship.getHit()[0]);
		// test if battleship's hit array index 1 is false
		assertFalse(ship.getHit()[1]);
		
		// create Cruiser and check hit array is all false
		Ship ship2 = new Cruiser();
		boolean[] hits2 = new boolean[3];
		assertArrayEquals(hits2, ship2.getHit());
		// test if cruiser hit array index 0 is false
		assertFalse(ship2.getHit()[0]);
		// test if cruiser hit array index 1 is false
		assertFalse(ship2.getHit()[1]);
		// test if cruiser hit array index 2 is false
		assertFalse(ship2.getHit()[2]);

		// create EmptySea and check hit array is false
		Ship ship3 = new EmptySea();
		boolean[] hits3 = new boolean[1];
		assertArrayEquals(hits3, ship3.getHit());
		// test if cruiser hit array index 0 is false
		assertFalse(ship3.getHit()[0]);
	}
	@Test
	void testGetShipType() {
		// test if Ship obj Battleship returns type "battleship"
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		// test if Ship obj EmptyShip returns type "empty"
		Ship ship2 = new EmptySea();
		assertEquals("empty", ship2.getShipType());

		// test if Ship obj Submarine returns type "empty"
		Ship ship3 = new Submarine();
		assertEquals("submarine", ship3.getShipType());
	}
	
	@Test
	void testIsHorizontal() {
		// test if created Ship obj Battleship is in horizontal orientation
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		// test if created Ship obj Destroyer is not in horizontal orientation
		Ship destroyer = new Destroyer();
		int row2 = 3;
		int column2 = 5;
		boolean horizontal2 = false;
		destroyer.placeShipAt(row2, column2, horizontal2, ocean);
		assertFalse(destroyer.isHorizontal());

		// test if created Ship obj EmptySea is in horizontal orientation
		Ship emptysea = new EmptySea();
		int row3 = 5;
		int column3 = 8;
		emptysea.placeShipAt(row3, column3, horizontal, ocean);
		assertTrue(emptysea.isHorizontal());
	}
	
	@Test
	void testSetBowRow() {
		// test setting battleship bowrow to row gives row
		Ship battleship = new Battleship();
		int row = 0;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		// test setting destroyer bowrow to row2 gives row2
		Ship destroyer = new Destroyer();
		int row2 = 4;
		destroyer.setBowRow(row2);
		assertEquals(row2, destroyer.getBowRow());

		// test setting destroyer bowrow to row3 gives row3
		int row3 = 6;
		destroyer.setBowRow(row3);
		assertEquals(row3, destroyer.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		// test setting battleship bowcol to column gives column
		Ship battleship = new Battleship();
		int column = 4;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		// test setting battleship bowcol to col2 gives col2
		int col2 = 6;
		battleship.setBowColumn(col2);
		assertEquals(col2, battleship.getBowColumn());

		// test setting destroyer bowcol to col3 gives col3
		Ship cruiser = new Cruiser();
		int col3 = 0;
		cruiser.setBowColumn(col3);
		assertEquals(col3, cruiser.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		// test setting battleship orientation to horizontal
		Ship battleship = new Battleship();
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		// test setting battleship orientation to vertical
		boolean horizontal2 = false;
		battleship.setHorizontal(horizontal2);
		assertFalse(battleship.isHorizontal());

		// test setting cruiser orientation to vertical
		Ship cruiser = new Cruiser();
		cruiser.setHorizontal(horizontal2);
		assertFalse(cruiser.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		
		// test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		// test when ship would extend off left edge of grid
		int col2 = 1;
		boolean no = battleship.okToPlaceShipAt(row, col2, horizontal, ocean);
		assertFalse(no, "Not okay to place ship, extends off left edge of grid.");

		// test when ship would extend off top edge of grid
		boolean horizontal2 = false;
		int row2 = 1;
		boolean no2 = battleship.okToPlaceShipAt(row2, column, horizontal2, ocean);
		assertFalse(no2, "Not okay to place ship, extends off top edge of grid.");
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//placing a ship diagonally adjacent to battleship1, should return false
		Battleship battleship3 = new Battleship();
		row = 4;
		column = 5;
		horizontal = false;
		boolean ok3 = battleship3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok3, "Not OK to place ship diagonally adjacent below.");

		//placing a ship horizontally adjacent to battleship1, should return false
		Battleship battleship4 = new Battleship();
		row = 3;
		column = 5;
		horizontal = false;
		boolean ok4 = battleship4.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok4, "Not OK to place ship horizontally adjacent.");
	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		
		//placing ship vertically
		Ship battleship1 = new Battleship();
		row = 9;
		column = 0;
		horizontal = false;
		battleship1.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship1.getBowRow());
		assertEquals(column, battleship1.getBowColumn());
		assertFalse(battleship1.isHorizontal());
		assertEquals(battleship1, ocean.getShipArray()[8][0]);

		//placing empty ship
		Ship empty = new EmptySea();
		row = 3;
		column = 1;
		horizontal = true;
		empty.placeShipAt(row,column,horizontal, ocean);
		assertEquals("empty", ocean.getShipArray()[3][1].getShipType());

	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		// if shoot at battleship above, should return true and update hitArray
		assertTrue(battleship.shootAt(0, 9));
		boolean[] hitArray1 = {true, false, false, false};
		assertArrayEquals(hitArray1, battleship.getHit());

		assertTrue(battleship.shootAt(0, 8));
		boolean[] hitArray2 = {true, true, false, false};
		assertArrayEquals(hitArray2, battleship.getHit());

		//if shoot empty sea, should return false
		Ship empty = new EmptySea();
		row = 1;
		column = 1;
		horizontal = false;
		empty.placeShipAt(row,column,horizontal, ocean);
		assertFalse(empty.shootAt(1, 1));
	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//if shoot at submarine above, should return true for isSunk
		assertTrue(submarine.shootAt(3, 3));
		assertTrue(submarine.isSunk());

		//shoot at empty sea should return false for isSunk
		Ship empty = new EmptySea();
		row = 1;
		column = 1;
		horizontal = false;
		empty.placeShipAt(row,column,horizontal, ocean);
		assertFalse(empty.shootAt(1, 1));
		
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		//shoot at empty sea, should return "-"
		Ship empty = new EmptySea();
		row = 1;
		column = 1;
		horizontal = false;
		empty.placeShipAt(row,column,horizontal, ocean);

		empty.shootAt(1,1);
		assertEquals("-", empty.toString());

		//shoot at ship that is sunk, should return "s"
		Ship submarine = new Submarine();
		row = 3;
		column = 3;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		submarine.shootAt(3,3);
		assertEquals("s", submarine.toString());
	}

}
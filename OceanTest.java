package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;
	
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));

		//test if the vertical battleship is occupying the left bottom corner of the grid
		Ship battleship = new Battleship();
		row = 9;
		column = 0;
		horizontal = false;
		battleship.placeShipAt(row,column, horizontal, ocean);

		assertTrue(ocean.isOccupied(row, column));

		// test if the horizontal cruiser is occupying the top right corner of grid
		Ship cruiser = new Cruiser();
		row = 0;
		column = 9;
		horizontal = true;

		cruiser.placeShipAt(row,column,horizontal,ocean);

		assertTrue(ocean.isOccupied(row,column));

		// test if place emptySea object, method should return false
		Ship empty = new EmptySea();
		row = 0;
		column = 1;
		horizontal = false;

		empty.placeShipAt(row,column,horizontal, ocean);

		assertFalse(ocean.isOccupied(row, column));
	}

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		
		// shoot at empty sea objects should return false
		Ship empty = new EmptySea();
		row = 1;
		column = 1;
		horizontal = false;
		empty.placeShipAt(row,column,horizontal, ocean);

		assertFalse(ocean.shootAt(1,1));

		// test if submarine is sunk, shootAt should return false
		Ship sub = new Submarine();
		row = 3;
		column = 1;
		horizontal = false;
		sub.placeShipAt(row,column,horizontal, ocean);

		assertTrue(ocean.shootAt(3, 1));
		assertTrue(sub.isSunk());
		assertFalse(ocean.shootAt(3, 1));

		//if shoot at the same location of ship, should return true every time

		Destroyer destroy = new Destroyer();
		row = 5;
		column = 5;
		horizontal = false;
		destroy.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(5, 5));
		assertTrue(ocean.shootAt(5, 5));
		assertTrue(ocean.shootAt(5, 5));
	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		//test if shooting at sunk position, should also increase shots fired
		assertFalse(ocean.shootAt(0, 5));
		assertEquals(7, ocean.getShotsFired());

		//test if shooting at empty sea objects, should increase shots fired
		Ship empty = new EmptySea();
		row = 1;
		column = 0;
		horizontal = true;
		empty.placeShipAt(row, column, horizontal, ocean);

		assertFalse(ocean.shootAt(1,0));
		assertEquals(8, ocean.getShotsFired());
	}

	@Test
	void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		//shooting at empty sea obj, should not increase hit count
		Ship empty = new EmptySea();
		row = 1;
		column = 0;
		horizontal = true;
		empty.placeShipAt(row, column, horizontal, ocean);
		assertFalse(ocean.shootAt(1,0));
		assertEquals(1, ocean.getHitCount());

		//shooting a sunk ship, should not increase hit count
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(2, ocean.getHitCount());

		assertFalse(ocean.shootAt(0, 5));
		assertEquals(2, ocean.getHitCount());
	}
	
	@Test
	void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		//if ship is sunk, shipsSunk count should increase
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(1, ocean.getShipsSunk());

		//hitting an empty sea should not increase shipSunk
		Ship empty = new EmptySea();
		row = 1;
		column = 0;
		horizontal = true;
		empty.placeShipAt(row, column, horizontal, ocean);
		assertFalse(ocean.shootAt(1,0));
		assertEquals(1, ocean.getShipsSunk());
	}

	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());

		// adding ship to array should not change size of array
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);

		//adding a ship should update the array
		assertEquals("destroyer", shipArray[1][5].getShipType());

		Ship empty = new EmptySea();
		row = 1;
		column = 0;
		horizontal = true;
		empty.placeShipAt(row, column, horizontal, ocean);
		assertEquals("empty", shipArray[1][0].getShipType());
	}

}
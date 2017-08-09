package tests;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import code.model.Inventory_024_062;
import code.model.Tile_024_062;
import code.model.TileRack_024;

public class TileRackTest {

	// Test maximum capacity of 12 for tile rack
	@Test
	public void capacityTest(){
		Inventory_024_062 inv = new Inventory_024_062();
		TileRack_024 rack = new TileRack_024(inv);
		assertTrue("The tile rack should fill to a maximum size of 12 but I got " + rack.getSize(), rack.getSize() == 12);
	}

	// Test removing tiles
	@Test
	public void removeTest(){
		Inventory_024_062 inv = new Inventory_024_062();
		TileRack_024 rack = new TileRack_024(inv);
		Tile_024_062 t = rack.removeTile(0);
		assertTrue("I removed one tile at index 0, now the rack size should be 11, but I got " + rack.getSize(), rack.getSize() == 11);
	}

	// Test adding tiles after removing tiles
	@Test
	public void addTest(){
		Inventory_024_062 inv = new Inventory_024_062();
		TileRack_024 rack = new TileRack_024(inv);
		rack.removeTile(0);
		rack.removeTile(1);
		rack.removeTile(2);
		rack.fillRack();
		assertTrue("I removed 3 tiles, then filled the rack, it's size should be 12, but I got " + rack.getSize(), rack.getSize() == 12);
	}



}
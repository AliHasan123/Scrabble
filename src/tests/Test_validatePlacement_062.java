package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.model.Board;
import code.model.Tile;


public class Test_validatePlacement_062 {
	
	@Test
	public void test1() {
		Board board = new Board(null, null, null);
		Tile[][] t = new Tile[20][20];
		board.setBoard(t);
		board.setVirtualBoard(t);
		t[0][0] = new Tile('A', 1);
		t[0][1] = new Tile('R', 1);
		t[0][2] = new Tile('M', 1);
		assertTrue("", board.validatePlacement());
	}
}

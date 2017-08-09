package tests;

//james

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.model.Board_024_062;
import code.model.Tile_024_062;

public class ValidatePlacementTests_062 {

	@Test
	public void Test0(){
		Board_024_062 board = new Board_024_062(null,null);
		Tile_024_062[][] gameBoard = new Tile_024_062[20][20];
		gameBoard[0][0] = new Tile_024_062('D',5);
		gameBoard[0][1] = new Tile_024_062('O',1);
		gameBoard[0][2] = new Tile_024_062('G',5);		
		boolean expected = true;
		boolean actual = board.validatePlacement();
		assertTrue('\n'+"Expected:"+expected+'\n'+"Actual:"+actual, expected == actual);
	}
	@Test
	public void Test1(){
		Board_024_062 board = new Board_024_062(null,null);
		Tile_024_062[][] gameBoard = new Tile_024_062[20][20];
		gameBoard[0][0] = new Tile_024_062('S',5);
		gameBoard[0][2] = new Tile_024_062('U',1);
		gameBoard[0][3] = new Tile_024_062('N',5);
		boolean expected = false;
		boolean actual = board.validatePlacement();
		assertTrue('\n'+"Expected:"+expected+'\n'+"Actual:"+actual, expected == actual);
	}
}

package tests;

import java.awt.Color;

import org.junit.Test;

import code.model.Board_024_062;
import code.model.Player_024_062;
import code.model.Tile_024_062;

/**
 * 
 * @author James and Ali 4/20/15
 *
 */
public class FlipTileColorTests_062 {

	@Test
	public void Test0(){
		Tile_024_062[][] gameBoard = new Tile_024_062[20][20];
		Board_024_062 board = new Board_024_062(null,null,null,null);
		Player_024_062 player1 = new Player_024_062(null, Color.RED, "Ali");
		Player_024_062 player2 = new Player_024_062(null, Color.BLUE, "James");
		Color color = player1.getColor();
		Color color1 = player2.getColor();
		
		board.setBoard1(gameBoard);
		
	}
}

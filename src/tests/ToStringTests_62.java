package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.model.Board_024_062;
import code.model.Tile_024_062;

public class ToStringTests_62 {

	//james
	
	@Test
	public void Test0(){
		Tile_024_062[][] gameBoard = new Tile_024_062[20][20];
		Board_024_062 board = new Board_024_062(null,null);
		for(int i=0;i<20;i=i+1){
			for(int j=0;j<20;j=j+1){
				gameBoard[i][j] = new Tile_024_062('B',5);
				
			}
		}
	    board.setBoard(gameBoard);
		String expected = "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
						+ "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
						+ "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
						+ "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
						+ "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
						+ "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
						+ "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
						+ "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
						+ "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
						+ "BBBB";
		String actual = board.toString();
		assertTrue('\n'+"Expected:"+expected+'\n'+"Actual:"+actual, expected.equals(actual));
	}
	@Test
	public void Test1(){
		Tile_024_062[][] gameBoard = new Tile_024_062[20][20];
		Board_024_062 board = new Board_024_062(null,null);
		for(int i=0;i<20;i=i+1){
			for(int j=0;j<20;j=j+1){
				//Empty game board test
			}
		}
		String expected = "--------------------------------------------"
						+ "--------------------------------------------"
						+ "--------------------------------------------"
						+ "--------------------------------------------"
						+ "--------------------------------------------"
						+ "--------------------------------------------"
						+ "--------------------------------------------"
						+ "--------------------------------------------"
						+ "--------------------------------------------"
						+ "----";
		String actual = board.toString();
		assertTrue('\n'+"Expected:"+expected+'\n'+"Actual:"+actual, expected.equals(actual));
	}
}
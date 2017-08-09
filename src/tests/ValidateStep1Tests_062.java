package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import code.model.Board_024_062;
import code.model.Tile_024_062;
import code.model.WordTracker_062;
/**
 * Date: 4/20/15
 * @author Driver: James 
 * @author Navigator: Noah
 * 
 *4/21/15 noah and ali d and n
 */
public class ValidateStep1Tests_062 {

	@Test
	public void Test0(){
		Tile_024_062[][] gameBoard = new Tile_024_062[20][20];
		Board_024_062 board = new Board_024_062();
		Tile_024_062[][] virtualBoard = new Tile_024_062[20][20];
		ArrayList<WordTracker_062> newWords = new ArrayList<WordTracker_062>();
		gameBoard[0][0] = new Tile_024_062('D',5);
		gameBoard[1][0] = new Tile_024_062('O',2);
		gameBoard[2][0] = new Tile_024_062('G',5);
		gameBoard[2][1] = new Tile_024_062('E',2);
		gameBoard[2][2] = new Tile_024_062('T',5);
		virtualBoard[2][1] = new Tile_024_062('E',2);
		virtualBoard[2][2] = new Tile_024_062('T',5);
		board.setBoard(gameBoard);
		board.setVirtualBoard(virtualBoard);
		assertTrue("",board.validateStep1());
		
	}
	
	@Test
	public void Test1(){
		Tile_024_062[][] gameBoard = new Tile_024_062[20][20];
		Board_024_062 board = new Board_024_062();
		Tile_024_062[][] virtualBoard = new Tile_024_062[20][20];
		ArrayList<WordTracker_062> newWords = new ArrayList<WordTracker_062>();
		gameBoard[0][0] = new Tile_024_062('D',5);
		gameBoard[1][0] = new Tile_024_062('O',2);
		gameBoard[2][0] = new Tile_024_062('G',5);
		gameBoard[2][1] = new Tile_024_062('X',2);
		gameBoard[2][2] = new Tile_024_062('T',5);
		virtualBoard[2][1] = new Tile_024_062('X',2);
		virtualBoard[2][2] = new Tile_024_062('T',5);
		board.setBoard(gameBoard);
		board.setVirtualBoard(virtualBoard);
		assertTrue("",!board.validateStep1());
		
	}
	
	@Test
	public void Test2(){
		Tile_024_062[][] gameBoard = new Tile_024_062[20][20];
		Board_024_062 board = new Board_024_062();
		Tile_024_062[][] virtualBoard = new Tile_024_062[20][20];
		ArrayList<WordTracker_062> newWords = new ArrayList<WordTracker_062>();
		gameBoard[0][0] = new Tile_024_062('D',5);
		gameBoard[1][0] = new Tile_024_062('O',2);
		gameBoard[2][0] = new Tile_024_062('G',5);
		gameBoard[0][1] = new Tile_024_062('O',2);
		gameBoard[2][1] = new Tile_024_062('E',5);
		gameBoard[2][2] = new Tile_024_062('T',5);
		gameBoard[0][2] = new Tile_024_062('T',2);
		gameBoard[1][2] = new Tile_024_062('O',5);
		virtualBoard[0][2] = new Tile_024_062('T',2);
		virtualBoard[1][2] = new Tile_024_062('O',5);
		board.setBoard(gameBoard);
		board.setVirtualBoard(virtualBoard);
		assertTrue("",board.validateStep1());
		
	}
	@Test
	public void Test3(){
		Tile_024_062[][] gameBoard = new Tile_024_062[20][20];
		Board_024_062 board = new Board_024_062();
		Tile_024_062[][] virtualBoard = new Tile_024_062[20][20];
		ArrayList<WordTracker_062> newWords = new ArrayList<WordTracker_062>();
		gameBoard[0][0] = new Tile_024_062('D',5);
		gameBoard[1][0] = new Tile_024_062('O',2);
		gameBoard[2][0] = new Tile_024_062('G',5);
		gameBoard[0][1] = new Tile_024_062('O',2);
		gameBoard[2][1] = new Tile_024_062('E',5);
		gameBoard[2][2] = new Tile_024_062('T',5);
		gameBoard[0][2] = new Tile_024_062('T',2);
		gameBoard[1][2] = new Tile_024_062('O',5);
		gameBoard[1][1] = new Tile_024_062('X',2);
		virtualBoard[0][2] = new Tile_024_062('T',2);
		virtualBoard[1][2] = new Tile_024_062('O',5);
		board.setBoard(gameBoard);
		board.setVirtualBoard(virtualBoard);
		assertTrue("",!board.validateStep1());
	}
}

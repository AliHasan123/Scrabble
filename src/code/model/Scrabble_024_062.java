package code.model;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Scrabble_024_062 {
	
	/**
	 * Stores tiles.
	 */
	private Inventory_024_062 _inv;
	/**
	 * Stores players.
	 */
	private ArrayList<Player_024_062> _players;
	/**
	 * tile board to be played on
	 */
	private Board_024_062 _board;
	/**
	 * Number of players
	 */
	private int _numberOfPlayers;
	
	/**
	 * Class constructor
	 */
	public Scrabble_024_062(String dictionaryFilePath, String restoreFilePath, String[] args){
		_inv = new Inventory_024_062();
		
		_players = new ArrayList<Player_024_062>();
		
		/* (5) s*/
		
		ArrayList<String> playerList = new ArrayList<String>();
		Color[] colorList = {Color.cyan, Color.red, Color.yellow, Color.green};
		
		if (args.length > 0) { //that is, if we are required to create new players 
			for(int i = 1; i < args.length; i = i + 1){
				String s = args[i];							
				playerList.add(s);
			}
			
			Collections.shuffle(playerList);
			int i = 0;
			for (String s: playerList) {
				addNewPlayer(s, colorList[i]);
				i++;
			}
		}
		_board = new Board_024_062(dictionaryFilePath, restoreFilePath, _inv,_players); //Noah 4/28
	}
	
	 /* (5) e*/
	
	/**
	 * Adds a new player to the game.
	 */
	private void addNewPlayer(String name, Color color){ //noah 4/9 changed argument list
		_players.add(new Player_024_062(_inv, color, name));
	}
	
	/**
	 * D Noah N Ali 4/30
	 * @param function
	 */
	
	public void functionButtonPressed(String function) { //author Noah during meeting 4/3
		// System.out.println(function + " pressed!");
		if (function.equals("REMOVE")) {
			_board.removePressed();
		}
		
		if (function.equals("SAVE")) {
			try {
				_board.save();
			} catch (FileNotFoundException e) {
				System.err.println("Save file could not be located! Save unsuccessful.");
			}
		}
		
		if (function.equals("SUBMIT")) {
			_board.submitPressed();
		}
		
		if (function.equals("PASS")) { //noah 5/6
			_board.passPressed();
		}
	}
	
	public Board_024_062 getBoard() { //Noah and Ali 4/7
		return _board;
	}
	
	public ArrayList<Player_024_062> getPlayers() {
		return _players;
	}
	
	public Inventory_024_062 getInventory() {
		return _inv;
	}
	
	
	
}

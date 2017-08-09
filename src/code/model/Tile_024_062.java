package code.model;

public class Tile_024_062 {

	private Player_024_062 _player;//Noah and James 4/6
	/**
	 * Stores the character of the tile.
	 */
	private char _letter;
	/**
	 * Stores the point value of the tile.
	 */
	private int _pointValue;
	
	/**d noah n ali
	 * test constructor
	 */
	
	public Tile_024_062(char c, int i) {
		_letter = c;
		_pointValue = i;
	}
	
	/**
	 * Class constructor.
	 * 
	 * @param c character of the tile
	 * @param i point value of the tile
	 */
	
	public Tile_024_062(char c, int i, Player_024_062 p){
		_letter = c;
		_pointValue = i;
		_player = p;//Noah and James 4/6
	}
	
	/**
	 * Gets the character value of the tile.
	 * 
	 * @return character value of the tile.
	 */
	public char getChar(){
		return _letter;
	}
	
	/**
	 * Gets the point value of the tile.
	 * 
	 * @return the point value of the tile.
	 */
	public int getValue(){
		return _pointValue;
	}
	/**
	 * Noah and James 4/6
	 * @return
	 */
	public Player_024_062 getPlayer(){
		return _player;
	}
	
	public void restoredPLayer(String s){
		_player.setName(s); 
	}
	/**
	 * (N)Noah (D)James 4/27/15
	 * @param p
	 */
	public void setPlayer(Player_024_062 p){
		_player = p;
	}
	
	/**
	 * 
	 * D Adithya N Noah 5/1
	 * 
	 */

	public int charValue(char c) {
		if ((c == 'A')||(c == 'E')||(c == 'I')||(c == 'O')||(c =='U')){
			return 1;
		} else if (c == 'Y'){
			return 2;
		} else {
			return 5;
		}
	}
	
	/**
	 * author Noah 5/1
	 * @param i
	 */
	public void setValue(int i) {
		_pointValue = i;
	}
	
}

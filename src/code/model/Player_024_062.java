package code.model;

import java.awt.Color; //noah 4/9

public class Player_024_062 {

	private Color _color; //Noah and James 4/6, noah 4/9
	
	/**
	 * Records the score.
	 */
	private int _score;
	
	/**
	 * Allows the Player to access the Tiles on its own TileRack.
	 */
	private TileRack_024 _rack;
	
	private String _name; //Noah 4/7
	
	/**
	 * Class constructor.
	 * 
	 * @param inv can draw Tiles from the Inventory
	 */
	public Player_024_062(Inventory_024_062 inv, Color color, String name){
		_score = 0;
		_rack = new TileRack_024(inv,this);
		_color = color; //Noah and James 4/6
		_name = name; //Noah and Ali 4/7
	}
	
	/**
	 * Gets the int value of the Player's score.
	 * 
	 * @return Player's score.
	 */
	public int getScore(){
		return _score;
	}

	/**
	 * Adds the new points to the original score
	 * 
	 * @param score Score to be added to
	 * @param i additional points added to the score
	 * @return Player's score.
	 */
	public int addScore(int score, int i){
		_score = score;
		_score = _score + i;
		return _score;
	}
	
	public void setScore(int score) {
		_score = _score + score;
	}
	/**
	 * Noah and James 4/6
	 * @return
	 */
	public Color getColor(){ //noah 4/9
		return _color;
	}
	
	public void setColor(Color color) {
		_color = color;
	}
	
	public String getName() {
		return _name; 
	}
	
	public void setName(String s){
		_name = s;
	}
	 
	//Adi and James
	public String playerInfo(){
		String s = "["+_name + " " + _color + " " + _score + " [ " + _rack.toString()+" ] "+ "]";
		return s;
		
	}
	/**
	 * Driver: Ali
	 * Navigator: Noah
	 * 4/28
	 * @return
	 */
	public TileRack_024 getRack() {
		return _rack;
	}
	
}
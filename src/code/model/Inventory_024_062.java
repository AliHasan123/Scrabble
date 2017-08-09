package code.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Inventory_024_062 {
	
	/**
	 * Stores the tiles that have yet to be drawn.
	 */
	private ArrayList<Tile_024_062> _tileBag;
	
	/**
	 * Class constructor.
	 */
	public Inventory_024_062(){
		_tileBag = new ArrayList<Tile_024_062>();
		fillBag();
	}
	
	/**
	 * Fills the inventory of tiles with the correct number of tiles on creation.
	 */
	private void fillBag() {
		for (int i = 'A'; i <= 'Z'; i = i + 1){
			// Create 29 of each vowel with point value 1
			if ((i == 'A')||(i == 'E')||(i == 'I')||(i == 'O')||(i =='U')){
				for (int j = 0; j < 29; j++){
					_tileBag.add(new Tile_024_062((char)i,1,null));
				}
			}
			// Create 15 y's with point value 2
			else if (i == 'Y'){
				for (int j = 0; j < 15; j++){
					_tileBag.add(new Tile_024_062((char)i,2,null));
				}
			}
			// Create 12 of every other letter with point value 5
			else{
				for (int j = 0; j < 12; j++){
					_tileBag.add(new Tile_024_062((char)i,5,null));
				}
			}
		}
	}

	/**
	 * Pulls a single random tile from the inventory.
	 * 
	 * @return the random tile that was removed
	 */
	public Tile_024_062 removeRandomTile(){
		int randomIndex = new Random().nextInt(_tileBag.size());
		return _tileBag.remove(randomIndex);
	}
	
	/**
	 * Returns number of tiles in the tile bag.
	 * 
	 * @return the size of the array list containing the tiles
	 */
	public int getSize(){
		return _tileBag.size();
	}
	
	/**
	 * Returns the tile at a specific index.
	 * 
	 * @param i index of the tile bag array list
	 * @return tile at index i
	 */
	public Tile_024_062 getTile(int i){
		return _tileBag.get(i);
	}

	/**
	 * D Noah N Ali 5/1
	 * @return
	 */
	
	public String getTileBag() {
		String s = "";
		for (Tile_024_062 t : _tileBag) {
			s = s + t.getChar();
		}
		return s;	
	}
	
	//Adi and James
	public void setInventory(String s){
           char[] restored =s.toCharArray();
           for(int i = 0; i < restored.length; i++){
        	   Tile_024_062 t = new Tile_024_062(restored[i],0,null); //Noah 5/1
        	   t.setValue(t.charValue(restored[i])); //Noah 5/1
        	  _tileBag.add(t); 
           }
		
	}
}

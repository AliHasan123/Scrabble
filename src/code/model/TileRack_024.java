package code.model;

import java.util.ArrayList;

public class TileRack_024 {
	
	/**
	 * Inventory to draw tiles from
	 */
	private Inventory_024_062 _inv;
	
	/**
	 * Stores the tiles that can be played.
	 */
	private ArrayList<Tile_024_062> _rack;
	
	private Player_024_062 _p;
	
	/**
	 * Class cosntructor.
	 * 
	 * @param inv inventory to draw tiles from
	 */
	public TileRack_024(Inventory_024_062 inv, Player_024_062 p){
		_inv = inv;
		_rack = new ArrayList<Tile_024_062>();
		_p = p;
		fillRack();
	}
	
	/**
	 * Fills tile rack to maximum capacity of 12
	 */
	public void fillRack(){
		while (_rack.size() < 12){
			Tile_024_062 t;
			t = _inv.removeRandomTile();
			t.setPlayer(_p);
			_rack.add(t);
		}
	}
	
	/**
	 * Removes a tile from rack.
	 * 
	 * @param t tile to be removed from the rack
	 * @return tile removed from the rack
	 */
	public Tile_024_062 removeTile(Tile_024_062 t){
		Tile_024_062 tileToRemove = t;
		_rack.remove(t);
		return tileToRemove;
	}
	
	/**
	 * Removes a tile from an index from rack
	 * 
	 * @param i index of tile to be removed
	 * @return tile removed from the rack
	 */
	public Tile_024_062 removeTile(int i){
		Tile_024_062 tileToRemove = _rack.get(i);
		_rack.remove(i);
		return tileToRemove;
	}

	/**
	 * Gets size of the tile rack
	 * 
	 * @return size of the tile rack
	 */
	public int getSize() {
		return _rack.size();
	}
	
	/**
	 * Driver Ali
	 * Nav Noah
	 * 4/28
	 * @return
	 */
	public ArrayList<Tile_024_062> getRack() {
		return _rack;
	}
	
	/**
	 * D Noah N Ali 5/1
	 */
	
	@Override
	public String toString() {
		int i = 0;
		String s = "";
		for (Tile_024_062 t : _rack) {
			s = s + t.getChar();
			i++;
		}
		i = 12 - i;
		for (int j = 0; j < i; j++) {
			s = s + "-"; //denotes a partially full tileRack
		}
		return s;
	}
	
}

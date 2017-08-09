package code.model;

//author Noah during meeting 4/3

/* ---Revisions---
 * 	  Noah 4/4 - boardTileClicked declaration
 * 	  Noah and James 4/6 - various methods
 */

public interface Board_MVC_062 {
	
	/*Methods for the Controller */
	public void boardTileClicked(int x, int y); /* it's up to the board's functionality to determine what to do
												   when a tile on the board is clicked */
	public void tileRackClicked(int num, int pos); /* board handles a tileRack button click event */ //Noah and Ali 4/7
	
	/* Methods for the View */
	public String toString();  /* returns the contents of the board in the form of a string of length 400
	 							"AC-D-XS---J" and so forth */
	public String colors();    /* returns the color of each tile in the form of a string of length 400
	 							  "BGGRYY--YYGGBRR   " and so forth */
	public String getError(); 	   /* possible errors: "invalidPlacement" , "invalidWord" */ //noah 4/10
	public Player_024_062 whoseTurn(); /* returns whose turn it is */
	//public Integer[] highlight(); 
	/* Returns an Integer array of length 6 containing information about which tile
								  the game needs to highlight. The string will contain only ints.
								  
								  index 0: 0 means no board tile is highlighted. 1 means a board tile is highlighted.
								  index 1: x coordinate of highlighted board tile
								  index 2: y coordinate of highlighted board tile
								  index 3: 0 means no tileTack is highlighted ... vice versa
								  index 4: Player # of whose tileRack contains the highlighted tile (1-4)
								  index 5: position on said tileRack of highlighted tile
								  
								  Two zeros in indices 0 and 3 means no tile is highlighted.
								  Example return array: {0, 0, 0, 1, 3, 11}
								   */
	
	// 4/28 I decided that the above version of highlight is obsolete, because board is now operating on a state basis - Noah
	
	public Integer[] highlight(); /* returns an array of length 5 that contains the values of
										_clickState
										_whichRack
										_rackPosition
										_newTileX
										_newTileY
								  */ // -- Noah individual edit 4/28
	
	
	public boolean checkIfWordIsValid(String word); //Checks to see if word is valid with dictionary txt file 
													// -James 4/5 --> Noah and james 4/6
	
	public int leftInBag(); /*returns the number of tiles remaining in the inventory */
	
	/* Methods for model functionality */ //driver:noah navigator:james 4/8	
	
	public boolean validatePlacement(); /* checks to make sure the newly placed words are valid. If there are any invalid
									   placements, the method will write "invalid placement" to the error instance variable
									   and will then return false. */
	
	public void flipTileColors(Tile_024_062 tile, int x, int y); /* (after a word submission has been confirmed as valid, but not yet scored)
									  scans the board for places where tile colors need to be flipped
									  and then assigns the appropriate color to the flipped tiles */
	
	public int evaluateWords(); /* calculates the total score of the newly formed words (taking into account the colors)
	 								   and will add the value to the appropriate player's current score.
	 								   After all is said and done, the new words should be added to the
	 								   _wordsOnBoard ArrayList. See the WordTracker class for proper formatting */
	
	/* validatePlacement(), flipTileColors(), and evaluateWords() will be called in order of the declaration 
	 * (they should also be written in order of declaration) */
	
	/* Words that already exist on the board are kept in the _wordsOnBoard ArrayList. Since players only receive points
	 * for new words, we must compare potential new words to the old words to ensure that the new words are actually new.
	 * To do this, simply iterate through words on board. Pack the new word into WordTracker format. If a is an old word
	 * and b is potentially a new word, just go a.equals(b). If b isn't equal to anything in the list, then it's a new
	 * word and points can be awarded for it. (The word must be determined to be valid before beginning the comparison process
	 */
	
}

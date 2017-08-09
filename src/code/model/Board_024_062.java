package code.model;

import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

public class Board_024_062 extends Observable implements Board_MVC_062 {

	// Noah and James 4/8 (driver:noah navigator:james)
	private Integer[] _highlight; /* see interface for format */
	private ArrayList<WordTracker_062> _wordsOnBoard;
	private Inventory_024_062 _inv; // Adi and James
	private ArrayList<Player_024_062> _players;

	private String _error;
	private int _currentTurn;

	private ArrayList<WordTracker_062> _newlyCreatedWords;

	// Noah and James 4/9 Drive:James Navigator:Noah
	private Tile_024_062[][] _virtualBoard;

	// Noah 4/9
	private String _dictionaryFilePath;
	private String _restoreFilePath; //d noah n ali 5/6

	private Player_024_062 _whoseTurn; // Noah and James 4/6
	/**
	 * 2-D array of tiles to store tiles.
	 */
	private Tile_024_062[][] _board;

	private Integer[] _oldHighlightInformation; // Noah individual edit 4/28
	private int _clickState; // Noah Ali 4/28
	private int _whichRack; // Noah Ali 4/28
	private int _rackPosition; // Noah Ali 4/28
	private int _newTileX; // Noah Ali 4/28
	private int _newTileY; // Noah Ali 4/28
	private int[][] _letterMultiplier;//5/4/15 James Ali
	private int[][] _wordMultiplier;//5/4/15 James Ali

	/**
	 * test constructor
	 * d noah n ali 5/5
	 */
	public Board_024_062() {
		_newlyCreatedWords = new ArrayList<WordTracker_062>();
	}

	/**
	 *Class constructor.
	 */
	public Board_024_062(String dictionaryFilePath, String restoreFilePath,
			Inventory_024_062 inv, ArrayList<Player_024_062> players) {

		//all this is mostly in the case that we have a new game. i think new game
		//and restore game assignments should be in separate methods and
		//called discriminately in the constructor

		//note: when a game is being restored, the players temp variable
		//is an empty array list

		_newlyCreatedWords = new ArrayList<WordTracker_062>();
		_board = new Tile_024_062[20][20];
		_virtualBoard = new Tile_024_062[20][20];
		_wordsOnBoard = new ArrayList<WordTracker_062>(); // Noah and James 4/8
		_inv = inv; // Adi and James
		_dictionaryFilePath = dictionaryFilePath; // Noah 4/9
		_restoreFilePath = restoreFilePath; // d noah n ali
		_players = players;// Noah Ali 4/28
		setChanged();// Noah Ali 4/28

		_oldHighlightInformation = new Integer[5]; // Noah individual edit 4/28
		saveHighlightInformation(); // Noah individual edit 4/28
		_clickState = 0;// Noah Ali 4/28
		_whichRack = 0;// Noah Ali 4/28
		_rackPosition = 0;// Noah Ali 4/28
		_whoseTurn = _players.get(0); // Noah individual edit 4/28
		_letterMultiplier = new int[20][20]; //5/4/15 James Ali
		_wordMultiplier = new int [20][20];// 5/4/15 James Ali
		_currentTurn = 0; //noah individual edit 5/5

		assignLetterMultipliers(); //Noah 5/4
		assignWordMultipliers();   //Noah 5/4

		System.out.println("Let's Play Scrabble!");
		System.out.println("********************");
		System.out.println(_whoseTurn.getName() + "'s turn!");
	}

	/**
	 * Adds a tile to a position on the board.
	 * 
	 * @param t
	 *            the tile to be added
	 * @param x
	 *            the x-coordinate of the placement location
	 * @param y
	 *            the y-coordinate of the placement location
	 */
	public void addTile(Tile_024_062 t, int x, int y) {
		_board[x][y] = t;
		// Noah and James 4/9
		_virtualBoard[x][y] = t;
	}

	/**
	 * Removes a tile from a position on the board.
	 * 
	 * @param x
	 *            the x-coordinate of the tile to be removed
	 * @param y
	 *            the y-coordinate of the tile to be removed
	 * @return
	 */
	public Tile_024_062 removeTile(int x, int y) {
		Tile_024_062 temp = _board[x][y];
		_board[x][y] = null;
		return temp;
	}

	/**
	 * Returns the tile at a position.
	 * 
	 * @param x
	 *            the x-coordinate of the position
	 * @param y
	 *            the y-coordinate of the position
	 * @return
	 */
	public Tile_024_062 getTile(int x, int y) {
		return _board[x][y];
	}
	/**
	 * 5/4/15 D James N Ali
	 * @return
	 */
	public int getRandomIndex(){
		return (int)(Math.random()*20);
	}
	/**
	 * 5/4/15
	 * D James N Ali
	 */
	public void assignLetterMultipliers(){
		for(int i = 0; i < 60; i = i + 1){
			int row = getRandomIndex();
			int column = getRandomIndex();
			if (_letterMultiplier[row][column] == 0) { //noah and ali 5/5
				_letterMultiplier[row][column] = 2;
			} else {
				i--;
			}
		}
		for(int j = 0; j < 40; j = j + 1){
			int row = getRandomIndex();
			int column = getRandomIndex();
			if(_letterMultiplier[row][column] == 0){ //noah and ali 5/5
				_letterMultiplier[row][column] = 3;
			}
			else{
				j = j - 1;
			}
		}
		for(int k = 0; k < 300; k = k + 1){
			int row = getRandomIndex();
			int column = getRandomIndex();
			if(_letterMultiplier[row][column] == 0){ //noah edit 5/4
				_letterMultiplier[row][column] = 1;
			}
			else{
				k = k - 1;
			}
		}
	}
	/**
	 * 5/4/15 D James N Ali
	 */
	public void assignWordMultipliers(){
		for(int i = 0; i < 40; i = i + 1){
			int row = getRandomIndex();
			int column = getRandomIndex();
			if(_wordMultiplier[row][column] == 0){
				_wordMultiplier[row][column] = 2;
			}
			else{
				i = i - 1;
			}
		}

		for(int j = 0; j < 20; j = j + 1){
			int row = getRandomIndex();
			int column = getRandomIndex();
			if(_wordMultiplier[row][column] == 0){ //noah and ali 5/5
				_wordMultiplier[row][column] = 3;
			}
			else{
				j = j - 1;
			}
		}
		for(int k = 0; k < 340; k = k + 1){
			int row = getRandomIndex();
			int column = getRandomIndex();
			if(_wordMultiplier[row][column] == 0){ //noah edit 5/4
				_wordMultiplier[row][column] = 1;
			}
			else{
				k = k - 1;
			}
		}
	}
	/**
	 * 5/4/15 D James N Ali
	 * @param x
	 * @param y
	 * @return
	 */
	public int getLetterMultiplier(int x, int y) {
		return _letterMultiplier[x][y];
	}
	/**
	 * 5/4/15 D James N Ali
	 * @param x
	 * @param y
	 * @return
	 */
	public int getWordMultiplier(int x, int y) {
		return _wordMultiplier[x][y];
	}
	/**
	 * Driver: Ali Navigator: Noah 4/28
	 * 
	 * @return
	 */
	@Override
	public void boardTileClicked(int x, int y) {
		saveHighlightInformation(); // Noah individual edit
		switch (_clickState) {
		case 0: // nothing has happened
			if (_virtualBoard[x][y] != null) {
				_clickState = 1;
				_newTileX = x;
				_newTileY = y;
			}
			break;

		case 1: // newly placed tile has been clicked
			if (_virtualBoard[x][y] != null) {
				_clickState = 1;
				_newTileX = x;
				_newTileY = y;
			} else {
				_clickState = 0;
			}
			break;

		case 2: // tileRack has been clicked
			if (_board[x][y] != null && _virtualBoard[x][y] == null) {
				_error = "Invalid Placement";
			}

			else if (_board[x][y] == null) {
				Tile_024_062 tile;
				tile = _players.get(_whichRack).getRack()
						.removeTile(_rackPosition);
				_board[x][y] = tile; // Noah individual edit 4/28
				flipTileColors(_board[x][y], x, y);
				_virtualBoard[x][y] = tile; // "" ""
			}

			else if (_board[x][y] /* start */!= null/* end */
					&& _virtualBoard[x][y] != null) { // Noah individual edit
				// 4/28
				Tile_024_062 tile = _virtualBoard[x][y];
				Tile_024_062 removedTile = _players.get(_whichRack).getRack()
						.removeTile(_rackPosition);
				_players.get(_whichRack).getRack().getRack().add(tile);
				_virtualBoard[x][y] = removedTile;
				_board[x][y] = removedTile; // Noah individual edit 4/28
			}
			_clickState = 0;
			break;
		}

		setChanged();
		notifyObservers();

	}
	
	@Override
	public void flipTileColors(Tile_024_062 tile, int x, int y) {
		Tile_024_062 currentTilePlaced = tile;
		Tile_024_062 topTile = _board[x-1][y];
		Tile_024_062 bottomTile = _board[x+1][y];
		Tile_024_062 leftTile = _board[x][y-1];
		Tile_024_062 rightTile = _board[x][y+1];
		
		Tile_024_062 topTile1 = _board[x-2][y];
		Tile_024_062 bottomTile1 = _board[x+2][y];
		Tile_024_062 leftTile1 = _board[x][y-2];
		Tile_024_062 rightTile1 = _board[x][y+2];
		
		if(topTile != null && topTile.getPlayer() != tile.getPlayer()) {
			if(topTile1 != null && tile.getPlayer() == topTile1.getPlayer()) {
				topTile.setPlayer(tile.getPlayer());
				topTile.getPlayer().setColor(tile.getPlayer().getColor());
			}
		}
		
		if(bottomTile != null && bottomTile.getPlayer() != tile.getPlayer()) {
			if(bottomTile1 != null && tile.getPlayer() == bottomTile1.getPlayer()) {
				bottomTile.setPlayer(tile.getPlayer());
				bottomTile.getPlayer().setColor(tile.getPlayer().getColor());
			}
		}
		
		if(leftTile != null && leftTile.getPlayer() != tile.getPlayer()) {
			if(leftTile1 != null && tile.getPlayer() == leftTile1.getPlayer()) {
				leftTile.setPlayer(tile.getPlayer());
				leftTile.getPlayer().setColor(tile.getPlayer().getColor());
			}
		}
		
		if(rightTile != null && rightTile.getPlayer() != tile.getPlayer()) {
			if(rightTile1 != null && tile.getPlayer() == rightTile1.getPlayer()) {
				rightTile.setPlayer(tile.getPlayer());
				rightTile.getPlayer().setColor(tile.getPlayer().getColor());
			}
		}
		
	}
	

	/**
	 * author: Noah Poczciwinski 4/28
	 */

	@Override
	public void tileRackClicked(int num, int pos) {
		saveHighlightInformation();

		if (_whoseTurn.equals(_players.get(num))
				&& _whoseTurn.getRack().getRack().size() >= (pos + 1)) {

			/* noahpocz individual edit 5/7 */
			if (_clickState == 2) {
				//this allows swapping within a tileRack
				Tile_024_062 highlightedTile = _players.get(num).getRack().getRack().get(pos);
				Tile_024_062 selectedTile = _players.get(_whichRack).getRack().getRack().set(_rackPosition, highlightedTile);
				_players.get(num).getRack().getRack().set(pos, selectedTile);
				_clickState = 0;
				saveHighlightInformation(); //so that the view knows to overwrite the highlighted tileRack space
				/* end edit */
			} else {
				_clickState = 2; // tileRack has been clicked
				_whichRack = num;
				_rackPosition = pos;
			}
		} else {
			System.err.println("You can't select that!");
			_clickState = 0;
		}

		setChanged();
		notifyObservers();
	}

	/**
	 * Driver: Ali Navigator: James 4/27
	 * 
	 * @return
	 */
	public String boardToString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 20; i = i + 1) {
			for (int j = 0; j < 20; j = j + 1) {
				if (_board[i][j] != null) {
					sb.append(_board[i][j].getChar());
				} else {
					sb.append(' ');
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Driver: Ali Navigator: Noah 4/28
	 * 
	 * @return
	 */
	public String tileRacksToString() {
		StringBuffer sb = new StringBuffer();
		for (Player_024_062 p : _players) {
			int i = 0;
			for (Tile_024_062 t : p.getRack().getRack()) {
				sb.append("" + t.getChar());
				i = i + 1;
			}
			i = 12 - i;
			for (int j = 0; j < i; j++) {
				sb.append(" ");
			}
		}
		return sb.toString();

	}

	/**
	 * Noah and James 4/6
	 * note: 5/5 this method is no longer necessary, because of the way things turned out in the view
	 */
	@Override
	public String colors() { // this needs to be an array of type color, so we
		// need to change this
		String s = "";
		for (int i = 0; i < 20; i = i + 1) {
			for (int j = 0; i < 20; j = j + 1) {
				if (_board[i][j] != null) {
					s = s + _board[i][j].getPlayer().getColor();
				} else {
					s = s + "-";
				}
			}
		}
		return s;
	}

	@Override
	public Player_024_062 whoseTurn() {
		return _whoseTurn;
	}

	/**
	 * author: noahpocz (Noah Poczciwinski) 
	 * notes: The view will determine
	 * whether or not it is appropriate to highlight based on the state
	 */
	@Override
	public Integer[] highlight() {
		Integer[] theArray = new Integer[5];
		theArray[0] = _clickState;
		theArray[1] = _whichRack;
		theArray[2] = _rackPosition;
		theArray[3] = _newTileX;
		theArray[4] = _newTileY;
		return theArray;
	}

	/**
	 * @author jbkuczma (James Kuczmarski) //noah and ali 4/20
	 * @date 4/5/15
	 */
	@Override
	public boolean checkIfWordIsValid(String word) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(_dictionaryFilePath)); //noah 5/6
			word = word.toLowerCase();
			String s = in.readLine();
			while (s != null) {
				s = in.readLine();
				if (word == "") {
					return true;
				} else if (word.equals(s)) {
					return true;
				}
			}
			in.close();
		} catch (IOException e) {
			// error
		}
		return false;
	}

	/**
	 * Navigator:Noah and Driver:James 4/20/15 & 4/27/15 
	 * Driver:Noah and Navigator:Ali 4/21/15
	 * @returns true if the new tiles all form actual words
	 */
	public boolean validateStep1() {
		for (int i = 0; i < 20; i = i + 1) {
			for (int j = 0; j < 20; j = j + 1) {

				int leftMostBound = 20;
				int rightMostBound = 20;
				int upperMostBound = 20;
				int lowerMostBound = 20;
				if (_virtualBoard[i][j] != null) {

					int c = i - 1; // start check left
					if (c == -1) {
						c = 19;
					}
					if (_board[c][j] != null) { // check left
						int goodBound = c;
						while (_board[c][j] != null) {
							goodBound = c;
							c--;
							if (c == -1) {
								c = 19;
							}
						}
						leftMostBound = goodBound;
						rightMostBound = i;
					}

					// end of check left

					c = i + 1; // start check right
					if (c == 20) {
						c = 0;
					}
					if (_board[c][j] != null) { // check right
						int goodBound = c;
						while (_board[c][j] != null) {
							goodBound = c; //noah individual edit 5/5
							c++;
							if (c == 20) {
								c = 0;
							}
						}
						if (leftMostBound == 20) {
							leftMostBound = i;
						}
						rightMostBound = goodBound;
					}
					// end of check right

					c = j - 1;// start check up
					if (c == -1) {
						c = 19;
					}
					if (_board[i][c] != null) {// check up
						int goodBound = c;
						while (_board[i][c] != null) {
							goodBound = c;
							c--;
							if (c == -1) {
								c = 19;
							}
						}

						upperMostBound = goodBound;
						lowerMostBound = j;
					}

					c = j + 1; // start check down
					if (c == 20) {
						c = 0;
					}
					if (_board[i][c] != null) { // check down
						int goodBound = c;
						while (_board[i][c] != null) {
							goodBound = c;
							c++;
							if (c == 20) {
								c = 0;
							}
						}
						if (upperMostBound == 20) {
							upperMostBound = j;
						}
						lowerMostBound = goodBound;
					}
					// end of check down

					String horizontalWord = "";
					String verticalWord = "";
					if (leftMostBound != 20 && rightMostBound != 20) {
						int a = leftMostBound;
						while (true) {
							horizontalWord = horizontalWord
									+ _board[a][j].getChar();
							if (a == rightMostBound)
								break;
							a++;
							if (a == 20) {
								a = 0;
							}
						}
					}


					if (upperMostBound != 20 && lowerMostBound != 20) {
						int a = upperMostBound;
						while (true) {
							verticalWord = verticalWord
									+ _board[i][a].getChar();
							if (a == lowerMostBound)
								break;
							a++;
							if (a == 20) {
								a = 0;
							}
						}
					}

					/* Word validation process output

					System.out.println("Tile: " + _board[i][j].getChar());
					System.out.println("leftmost:" + leftMostBound
							+ " rightmost:" + rightMostBound);
					System.out.println("upper:" + upperMostBound + " lower:"
							+ lowerMostBound);

					System.out.println("Horizontal Validation: " + horizontalWord);
					System.out.println(horizontalWord+ " was checked and is: "
							+ checkIfWordIsValid(horizontalWord));
					System.out.println("Vertical Validation: " + verticalWord);
					System.out.println(verticalWord + " was checked and is: "
							+ checkIfWordIsValid(verticalWord));
					System.out.println("*****");
					 */

					if (checkIfWordIsValid(horizontalWord)) {
						if (!horizontalWord.equals("")) {
							WordTracker_062 temp = new WordTracker_062(
									horizontalWord, leftMostBound, j,
									rightMostBound, j);
							boolean contained = false;
							for (WordTracker_062 w : _newlyCreatedWords) {
								if (w.equals(temp)) {
									contained = true;
								}
							}
							if (!contained) {
								_newlyCreatedWords.add(temp);
							}
						}
					} else {
						return false;
					}
					if (checkIfWordIsValid(verticalWord)) {
						if (!verticalWord.equals("")) {
							WordTracker_062 temp = new WordTracker_062(
									verticalWord, i, upperMostBound, i,
									lowerMostBound);
							boolean contained = false;
							for (WordTracker_062 w : _newlyCreatedWords) {
								if (w.equals(temp)) {
									contained = true;
								}
							}
							if (!contained) {
								_newlyCreatedWords.add(temp);
							}
						}
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 4/27/15 Driver: James Navigator: Noah
	 */
	public WordTracker_062 validateStep2() {
		ArrayList<Character> lettersPlaced = new ArrayList<Character>();
		int letterCount = 0;
		for (int i = 0; i < 20; i = i + 1) {
			for (int j = 0; j < 20; j = j + 1) {
				if (_virtualBoard[i][j] != null) {
					lettersPlaced.add(_virtualBoard[i][j].getChar());
					letterCount++;
				}
			}
		}
		for (WordTracker_062 w : _newlyCreatedWords) {
			Tile_024_062 start;
			Tile_024_062 end;
			start = _board[w.getStartingX()][w.getStartingY()];
			end = _board[w.getEndingX()][w.getEndingY()];
			if (w.getStartingX() == w.getEndingX()) { // vertical word case
				int a = w.getStartingY();
				int temp = 0;
				while (true) {
					if (_virtualBoard[w.getStartingX()][a] != null) {
						temp++;
					}
					if (a == w.getEndingY())
						break;
					a++;
					if (a == 20) {
						a = 0;
					}
				}
				if (temp == letterCount) {
					return w;
				}
			}
			if (w.getStartingY() == w.getEndingY()) { // horizontal word
				// case
				int a = w.getStartingX();
				int temp = 0;
				while (true) {
					if (_virtualBoard[a][w.getStartingY()] != null) {
						temp++;
					}
					if (a == w.getEndingX())
						break;
					a++;
					if (a == 20) {
						a = 0;
					}
				}
				if (temp == letterCount) {
					return w;
				}
			}

		}

		return null;
	}

	/**
	 * 4/27/15 Driver:James Navigator: Noah
	 */
	public boolean validateStep3(WordTracker_062 w) {
		if (w.getStartingX() == w.getEndingX()) { // vertical word case
			int a = w.getStartingY();
			int b = w.getStartingX() - 1;
			if (b == -1) {
				b = 19;
			}
			int c = w.getStartingX() + 1;
			if (c == 20) {
				c = 0;
			}
			int g = w.getStartingX(); //d noah n ali 5/5 start

			int d = a;
			int e = w.getEndingY(); //noah 5/5
			d--;
			e++;
			if (d == -1) {
				d = 19;
			}

			if (e == 20) { //d noah n ali 5/5 end
				e = 0;
			}

			while (true) {
				if (_board[b][a] != null || _board[c][a] != null) {
					return true;
				}
				if (a == w.getEndingY())
					break;
				a++;
				if (a == 20) {
					a = 0;
				}
			}
			if (_board[g][d] != null || _board[g][e] != null) {
				return true;
			}
		}
		if (w.getStartingY() == w.getEndingY()) { // horizontal word case
			int a = w.getStartingX();
			int b = w.getStartingY() - 1;
			if (b == -1) {
				b = 19;
			}
			int c = w.getStartingY() + 1;
			if (c == 20) {
				c = 0;
			}

			int g = w.getStartingY(); //d noah n ali 5/5 start

			int d = a;	
			int e = w.getEndingX(); //noah 5/5
			d--;
			e++;
			if (d == -1) {
				d = 19;
			}

			if (e == 20) {	//d noah n ali 5/5 end
				e = 0;
			}
			while (true) {
				if (_board[a][b] != null || _board[a][c] != null) {
					return true;
				}
				if (a == w.getEndingY())
					break;
				a++;
				if (a == 20) {
					a = 0;
				}
			}
			if (_board[d][g] != null || _board[e][g] != null) {
				if (_board[d][g] != null)
					System.out.println(_board[d][g].getChar() + " ");
				if (_board[e][g] != null)
					System.out.println(_board[e][g].getChar());
				return true;
			}
		}
		return false;
	}

	/**
	 * 4/27/15
	 * 
	 * @driver: jbkuczma (James Kuczmarski)
	 * @navigator: noahpocz (Noah Poczciwinski)
	 * notes: overhauled by (noahpocz)
	 * 
	 * @describe: utilizes three main helper methods to determine whether tiles are well-placed on the board
	 */
	@Override
	public boolean validatePlacement() {
		_newlyCreatedWords = new ArrayList<WordTracker_062>();

		boolean step1 = validateStep1();
		boolean step2 = true;
		WordTracker_062 temp = validateStep2();
		if (temp == null) {
			step2 = false;
		}
		boolean step3 = false;
		if (step2)
			step3 = validateStep3(temp);
		if (_currentTurn == 0) { //on the first turn, the word cannot be connected to anything
			step3 = true;
		}

		for (WordTracker_062 t : _newlyCreatedWords) {
			_wordsOnBoard.add(t);
		}

		return step1 && step2 && step3;
	}

	/**
	 * Driver: Ali Navigator: James
	 * Various edits made by: Driver: Noah Navigator:Ali
	 * Date: 5/6
	 * @return
	 */

	@Override
	public int evaluateWords() {
		int score = 0;
		int scoreOfTileWithLM = 0;
		int valueOfWM = 1;
		for( WordTracker_062 word : _newlyCreatedWords) {
			if(word.getStartingX() == word.getEndingX()) {
				for(int i = word.getStartingY(); i <= word.getEndingY(); i++) { 
					if (_board[word.getStartingX()][i].getPlayer() == _whoseTurn) {
						scoreOfTileWithLM = (_board[word.getStartingX()][i].getValue()) * getLetterMultiplier(word.getStartingX(), i);
						_letterMultiplier[word.getStartingX()][i] = 1; //d noah n ali
						score = score + scoreOfTileWithLM;

						valueOfWM = valueOfWM * getWordMultiplier(word.getStartingX(), i);
						_wordMultiplier[word.getStartingX()][i] = 1;
					}
				}
				score = score * valueOfWM;
			}
			else if(word.getStartingY() == word.getEndingY()) {
				for(int i = word.getStartingX(); i <= word.getEndingX(); i++) { 
					if (_board[i][word.getStartingY()].getPlayer() == _whoseTurn) {
						scoreOfTileWithLM = (_board[i][word.getStartingY()].getValue()) * getLetterMultiplier(i, word.getStartingY());
						_letterMultiplier[i][word.getStartingY()] = 1; //d noah n ali
						score = score + scoreOfTileWithLM;

						valueOfWM = valueOfWM * getWordMultiplier(i, word.getStartingY());
						_wordMultiplier[i][word.getStartingY()] = 1;
					}
				}
				score = score * valueOfWM;
			}
		}
		return score;
	}

	/**
	 * Driver: Noah Navigator: Adi
	 * Date: 5/1
	 * @param previousBoard
	 */
	public void restoreBoard(String previousBoard) {
		char tileChar = 0;
		Player_024_062 thePlayer = null;
		int currentBoardIndex = 0;
		for (int i = 0; i < previousBoard.length(); i++) {
			char c = previousBoard.charAt(i);
			if (c == '-') {
				currentBoardIndex++;
			}
			if (c == ',') {
				int j = i + 1;
				String s = "";
				while (previousBoard.charAt(i) != ']') {
					s = s + previousBoard.charAt(j);
					j++;
				}
				tileChar = previousBoard.charAt(i - 1);
				for (Player_024_062 p : _players) {
					if (p.getName().equals(s)) {
						thePlayer = p;
						break;
					}
				}

				/* edits made by Noah 5/1 */
				Tile_024_062 t = new Tile_024_062(c, 0, thePlayer);
				t.setValue(t.charValue(c));
				if(i > 20){
					_board[Math.round((i-i%20)/20)][i%20] = t;
				}
				else{
					_board[0][i] = t;
				}
				/* end edits */

				currentBoardIndex++;
			}
		}
	}

	public String saveBoardString() {
		String s = "";
		for (int i = 0; i < 20; i = i + 1) {
			for (int j = 0; j < 20; j = j + 1) {
				if (_board[i][j] != null) {

					s = s + "[" + _board[i][j].getChar() + ","
							+ _board[i][j].getPlayer().getName() + "]";
				} else {
					s = s + "-";
				}
			}
		}
		return s;
	}
	/**
	 * D James N Ali 5/6/15
	 * @return
	 */
	public String saveLetterMultiplier(){
		String s = "";
		for(int i = 0; i < 20; i = i + 1){
			for(int j = 0; j < 20; j = j + 1){
				if(_board[i][j] != null){
					s = s + "1";
				}
				else{
					s = s + getLetterMultiplier(i,j);
				}
			}
		}
		return s;
	}
	/**
	 * D James N Ali 5/6/15
	 * @return
	 */
	public String saveWordMultiplier(){
		String s= "";
		for(int i = 0; i < 20; i = i + 1){
			for(int j = 0; j < 20; j = j + 1){
				if(_board[i][j] != null){
					s = s + "1";
				}
				else{
					s = s + getWordMultiplier(i,j);
				}
			}
		}
		return s;
	}

	/**
	 *D Ali N James 5/1
	 * @return
	 */

	public int whoseTurnNumber() {
		int number = 0;
		for(int i = 0; i < _players.size(); i = i + 1) {
			Player_024_062 p = _players.get(i);
			if (p == _whoseTurn) {
				number = i;
			}
		}
		return number;
	}

	/**
	 * D Ali N James 5/1
	 * @return
	 */

	public String restOfPlayersInfo() {
		String s = "";
		if(whoseTurnNumber() == 0) {
			s = s + _players.get(1).playerInfo() + _players.get(2).playerInfo() + _players.get(3).playerInfo();
		}
		else if(whoseTurnNumber() == 1) {
			s = s + _players.get(2).playerInfo() + _players.get(3).playerInfo() + _players.get(0).playerInfo();
		}
		else if(whoseTurnNumber() == 2) {
			s = s + _players.get(3).playerInfo() + _players.get(0).playerInfo() + _players.get(1).playerInfo();
		}
		else if(whoseTurnNumber() == 3) {
			s = s + _players.get(0).playerInfo() + _players.get(1).playerInfo() + _players.get(2).playerInfo();
		}
		return s;
	}

	// Adi and James
	public String saveInfoText() {
		String s = "20 20 \n /Stage2/dictionary.txt \n"
				+ _whoseTurn.playerInfo() + restOfPlayersInfo() + "\n" + _inv.getTileBag() + " \n"
				+ saveBoardString() + "\n" + saveLetterMultiplier() + "\n" + saveWordMultiplier();
		return s; // edited by Ali and James
	}

	// Adi and James
	public void save() throws FileNotFoundException { // Noah and Ali edit 4/30
		System.out.println("Saving...");
		String text = saveInfoText();
		PrintWriter out = new PrintWriter("saveinfo.txt");
		out.println(text);
		out.close();
		System.out.println("Save complete!");
	}

	// Adi and James
	public void restore(String restoreFilePath) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(restoreFilePath));

		if (scan.nextLine() == "20 20") {
			scan.nextLine(); // Adi and James
			String playerInformation = scan.nextLine();
			String remainingInInventory = scan.nextLine();
			_inv.setInventory(remainingInInventory);
			String previousBoard = scan.nextLine();
			// previousBoard.replaceAll("[","");
			// previousBoard.replaceAll("]","");
			// previousBoard.replaceAll(""," " );
			// StringTokenizer str = new StringTokenizer(previousBoard);
			String s = "";
			// while(str.hasMoreTokens()){
			//
			// }
			// if(str.nextToken() == "-"){
			// s = s + " ";
			// }
			// else if(str.nextToken().length() == 1){
			// s = s + str.nextToken();
			// }
			// else{
			//
			// }

			for (int i = 0; i < 20; i++) {

				// _board[0][i] = new Tile((char)(s.charAt(i)));
			}

		}
	}

	/* All of the following were written by Noah Poczciwinski on 4/10 */

	@Override
	public String getError() {
		return _error;
	}

	@Override
	public int leftInBag() {
		return _inv.getSize();
	}

	public void setBoard(Tile_024_062[][] b) {
		_board = b;
	}

	// public Tile_024_062[][] setBoard1(Tile_024_062[][] b) {
	// _board = b;
	// return _board;
	// }

	public void setVirtualBoard(Tile_024_062[][] t) {
		_virtualBoard = t;
	}

	public boolean checkBoard(int x, int y) {
		return _board[x][y] != null;
	}

	/**
	 * author (noahpocz) Noah Poczciwinski info: saves the location of where the
	 * old highlight would be, so that the view can overwrite it
	 */
	public void saveHighlightInformation() {
		Integer[] temp = highlight();
		for (int i = 0; i < 5; i++) {
			_oldHighlightInformation[i] = temp[i];
		}
	}

	/**
	 * author (noahpocz) Noah Poczciwinski returns the _oldHighlightInformation
	 * array
	 * 
	 */
	public Integer[] getOldHighlightInformation() {
		return _oldHighlightInformation;
	}

	/**
	 * D Noah N Ali 4/30
	 */
	public void removePressed() {
		if (_clickState == 1) { // if a newly placed tile has been clicked
			saveHighlightInformation();
			Tile_024_062 theTile = _board[_newTileX][_newTileY];
			_board[_newTileX][_newTileY] = null;
			_virtualBoard[_newTileX][_newTileY] = null;
			_players.get(_whichRack).getRack().getRack().add(theTile);
			_clickState = 0;

			setChanged();
			notifyObservers();
		}
	}

	/**
	 * author: (noahpocz) Noah Poczciwinski
	 */
	public void submitPressed() {
		if (validatePlacement()) {

			/* The word(s) placed on the board are valid, so now we can:
			 * -apply othello rules (flip tile colors)
			 * -evaluate the score earned for this player
			 * -do other housekeeping to prepare for the next turn	
			 */

			System.out.println(_whoseTurn.getName() + " made:");
			for (WordTracker_062 t : _newlyCreatedWords) {
				System.out.println(t.getWord());
			}

			int playerScore = evaluateWords();
			_whoseTurn.setScore(playerScore);

			System.out.println("and received " + playerScore + " points");
			System.out.println("");

			_whoseTurn.getRack().fillRack();
			takeTurns();

			_virtualBoard = new Tile_024_062[20][20]; //clear out the virtual board for the next player
		} else {
			System.out.println("**Sorry, that's an invalid tile placement!**");
			removeTiles();
			takeTurns();
		}

		setChanged();
		notifyObservers();

	}

	/**
	 * @author: (noahpocz) Noah Poczciwinski
	 * @describe: Executes the appropriate steps for when the pass button is pressed
	 * - put the newly placed tiles back in the player's tile rack
	 * - switch the current player to the next one in the order
	 * - update the view
	 */
	public void passPressed() {
		removeTiles();
		takeTurns();

		setChanged();
		notifyObservers();
	}

	/**
	 * @author: (noahpocz) Noah Poczciwinski
	 * @describe: sets the value of _whoseTurn to the next player in the order
	 */
	public void takeTurns() {
		int index = _players.indexOf(_whoseTurn); //now it's the next player's turn
		index++;
		if (index == _players.size()) {
			index = 0;
		}
		_whoseTurn = _players.get(index);
		System.out.println(_whoseTurn.getName() + "'s turn!");
	}

	/**
	 * author: (noahpocz) Noah Poczciwinski
	 */
	public void removeTiles() {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (_virtualBoard[i][j] != null) {
					_whoseTurn.getRack().getRack().add(_virtualBoard[i][j]);
					_virtualBoard[i][j] = null;
					_board[i][j] = null;
				}
			}
		}
	}

}

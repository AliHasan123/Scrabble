package code.model;

/**
 * 
 * @driver Noah Poczciwinski
 * @navigator James Kuczmarski
 * @date 4/8
 * 
 * @describe Serves to ensure that each word currently on the board can be distinguished from newly created words
 *
 */

public class WordTracker_062 {
	
	private String _word;
	private int _startingX;     //the X starting point of the word
	private int _startingY;     //the Y starting point of the word
	private int _endingX;
	private int _endingY;
	
	public WordTracker_062(String word, int sx, int sy, int ex, int ey) {
		_word = word;
		_startingX = sx;
		_startingY = sy;
		_endingX = ex;
		_endingY = ey;
	}
	
	public String getWord() {
		return _word;
	}
	
	public int getStartingX() {
		return _startingX;
	}
	
	public int getStartingY() {
		return _startingY;
	}
	public int getEndingX(){
		return _endingX;
	}
	public int getEndingY(){
		return _endingY;
	}
	@Override
	public String toString(){
		return _word;
	}
	@Override
	public boolean equals(Object o){
		WordTracker_062 temp = (WordTracker_062) o;
		if((temp.getStartingX() == _startingX && temp.getStartingY() == _startingY && temp.getEndingX() == _endingX && temp.getEndingY() == _endingY)){
			return true;
		}
		return false;
	}
}

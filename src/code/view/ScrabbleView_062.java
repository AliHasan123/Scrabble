package code.view;

/* 6 = driver:noah nav:james */

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import code.controller.Controller_062;
import code.model.Board_024_062;
import code.model.Player_024_062;
import code.model.Scrabble_024_062;
import code.model.Tile_024_062;

public class ScrabbleView_062 implements Runnable,Observer {
	
	private Scrabble_024_062 _scrabble; //a reference to the main model class
	
	private JButton[][] _buttonsForBoard;
	private ArrayList<JButton[]> _buttonsForRacks;
	private JButton[] _functionButtons;	//Noah and Ali 4/7
	
	private Controller_062 _controller;
	boolean _scrabbleViewConstructorCompleted;
	private JLabel _label;
	private JLabel _playerLabel0;
	private JLabel _playerLabel1;
	private JLabel _playerLabel2;
	private JLabel _playerLabel3;
	private JLabel _whoseTurnLabel;
	
	/* Begin noahpocz (Noah Poczciwinski) individual edit 4/28 */
	int _windowWidth; //(5)
	int _windowHeight;
	int _buttonSize;
	int _buttonPadding;
	int _thickness;
	/* end edit (made these into instance vars, they were local vars before) 4/28 */
	
	public ScrabbleView_062(String dictionaryFilePath, String restoreFilePath, String[] args) {
		
		System.out.println(dictionaryFilePath);
		
		_windowWidth = 1300; //(5)
		_windowHeight = 825;
		_buttonSize = 26;
		_buttonPadding = 2;
		_thickness = 7;
		
		_scrabble = new Scrabble_024_062(dictionaryFilePath, restoreFilePath, args); //Noah 4/7, 4/9
		_buttonsForBoard = fillBoard();
		_buttonsForRacks = createTileRackButtons();
		_functionButtons = createFunctionButtons(); // Ali 4/16
		_controller = new Controller_062(_scrabble, _buttonsForBoard, _buttonsForRacks, _functionButtons); //Noah 4/9
		_label = new JLabel(); //Driver ALi Nav Noah 4/28
		_playerLabel0 = new JLabel();
		_playerLabel1 = new JLabel();
		_playerLabel2 = new JLabel();
		_playerLabel3 = new JLabel();
		_whoseTurnLabel = new JLabel();
		
		_scrabble.getBoard().addObserver(this); //Driver ALi Nav Noah 4/28
		_scrabble.getBoard().notifyObservers(); //Driver ALi Nav Noah 4/28
		
		
	}
	
	// Ali and Noah  Start 4/10
	private ArrayList<JButton[]> createTileRackButtons() {
			
			ArrayList<JButton[]> returnList = new ArrayList<JButton[]>();
			for (int i = 0; i < _scrabble.getPlayers().size(); i++) { //(5)
				
				JButton[] buttons = new JButton[12];
				for (int j = 0; j < 12; j++) {
					buttons[j] = new JButton();
					buttons[j].setFont(new java.awt.Font("Times New Roman", 1, 10)); //(noahpocz) individual edit
				}
				returnList.add(buttons);
			}
			return returnList;
		}
	
	// Ali 4/16
		private JButton[] createFunctionButtons() {
			JButton[] returnArray = new JButton[4];
			for (int i = 0; i < returnArray.length; i = i + 1){
				returnArray[i] = new JButton();
			}
			return returnArray;
		}
		
	// Ali and Noah End 4/10
	
		private JButton[][] fillBoard() {
			JButton[][] board = new JButton[20][20];
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					board[i][j] = new JButton();
					board[i][j].setFont(new java.awt.Font("Times New Roman", 1, 10)); //(noahpocz) individual edit
				}
			}
			return board;
		}

		@Override
		public void run() {
			
			//code for the swing functionality was modified from http://stackoverflow.com/questions/10962597/how-to-place-an-object-in-a-specific-location-x-y-on-a-jframe
			
			JFrame window = new JFrame("Scrabble");
			JPanel contentPane = new JPanel();
			contentPane.setLayout(null);
			window.setContentPane(contentPane);
			window.setSize(1300, 825);
			window.setLocationByPlatform(true);
			window.setResizable(false);
			
			for (int i = 0; i < 20; i++) {	//Noah after meeting with Ali 4/7
				for (int j = 0; j < 20; j++) {
					JButton button = _buttonsForBoard[i][j];
					button.setSize(_buttonSize, _buttonSize);
					button.setLocation(350 + i * (_buttonSize + _buttonPadding), 115 + j * (_buttonSize + _buttonPadding));
					button.setOpaque(true);
					button.setBackground(new Color(210,180,140));
					button.setBorder(BorderFactory.createMatteBorder(_buttonSize / _thickness, 
						_buttonSize / _thickness, _buttonSize / _thickness, _buttonSize / _thickness, Color.black ));
					contentPane.add(button);
				}
			}
			
			
			// Ali Start 4/10
			for (int i = 0; i < _scrabble.getPlayers().size(); i = i + 1) {
				Player_024_062 player = _scrabble.getPlayers().get(i);
				JButton[] buttons = _buttonsForRacks.get(i);
				
				if(i == 0) {
					JPanel playerpanel = new JPanel();
					playerpanel.setLayout(new FlowLayout());
					playerpanel.setLocation(100,(_windowHeight/2) + 40);
					playerpanel.setSize(150, 40);
					contentPane.add(playerpanel);
					JLabel label = _playerLabel0;
					label.setVisible(true);
					label.setFont(new java.awt.Font("Times New Roman", 1, 18));
					playerpanel.add(label, JLabel.CENTER);
					
					
					for(int j = 0; j < 12; j = j + 1) {
						
						JButton button = buttons[j];
						button.setSize(_buttonSize, _buttonSize);
						button.setLocation(5 + j * (_buttonSize + _buttonPadding), _windowHeight / 2);
						button.setOpaque(true);
						button.setBackground(player.getColor());
						button.setBorder(BorderFactory.createMatteBorder(_buttonSize / _thickness, 
						_buttonSize / _thickness, _buttonSize / _thickness, _buttonSize / _thickness, Color.black ));
						contentPane.add(button);
						
					}
				}
				
				if(i == 1) {
					JPanel playerpanel = new JPanel();
					playerpanel.setLayout(new FlowLayout());
					playerpanel.setLocation(600, 50);
					playerpanel.setSize(150, 35);
					contentPane.add(playerpanel);
					JLabel label = _playerLabel1;
					label.setVisible(true);
					label.setFont(new java.awt.Font("Times New Roman", 1, 18));
					playerpanel.add(label, JLabel.CENTER);
					
					for(int j = 0; j < 12; j = j + 1) {
						JButton button = buttons[j];
						button.setSize(_buttonSize, _buttonSize);
						button.setLocation(500 + j * (_buttonSize + _buttonPadding), 20);
						button.setOpaque(true);
						button.setBackground(player.getColor());
						button.setBorder(BorderFactory.createMatteBorder(_buttonSize / _thickness, 
						_buttonSize / _thickness, _buttonSize / _thickness, _buttonSize / _thickness, Color.black ));
						contentPane.add(button);
						
					}
				}
				
				if(i == 2) {
					JPanel playerpanel = new JPanel();
					playerpanel.setLayout(new FlowLayout());
					playerpanel.setLocation(1020,(_windowHeight/2) + 40);
					playerpanel.setSize(150, 40);
					contentPane.add(playerpanel);
					JLabel label = _playerLabel2;
					label.setVisible(true);
					label.setFont(new java.awt.Font("Times New Roman", 1, 18));
					playerpanel.add(label, JLabel.CENTER);
					
					for(int j = 0; j < 12; j = j + 1) {
						JButton button = buttons[j];
						button.setSize(_buttonSize, _buttonSize);
						button.setLocation(925 + j * (_buttonSize + _buttonPadding), _windowHeight / 2);
						button.setOpaque(true);
						button.setBackground(player.getColor());
						button.setBorder(BorderFactory.createMatteBorder(_buttonSize / _thickness, 
						_buttonSize / _thickness, _buttonSize / _thickness, _buttonSize / _thickness, Color.black ));
						contentPane.add(button);
						
					}
				}
				
				if(i == 3) {
					JPanel playerpanel = new JPanel();
					playerpanel.setLayout(new FlowLayout());
					playerpanel.setLocation(600,730);
					playerpanel.setSize(150, 30);
					contentPane.add(playerpanel);
					JLabel label = _playerLabel3;
					label.setVisible(true);
					label.setFont(new java.awt.Font("Times New Roman", 1, 18));
					playerpanel.add(label, JLabel.CENTER);
					
					for(int j = 0; j < 12; j = j + 1) {
						JButton button = buttons[j];
						button.setSize(_buttonSize, _buttonSize);
						button.setLocation(500 + j * (_buttonSize + _buttonPadding), 700);
						button.setOpaque(true);
						button.setBackground(player.getColor());
						button.setBorder(BorderFactory.createMatteBorder(_buttonSize / _thickness, 
						_buttonSize / _thickness, _buttonSize / _thickness, _buttonSize / _thickness, Color.black ));
						contentPane.add(button);
						
					}
				}
				
			}
			
			JButton saveButton = _functionButtons[3];//6
			saveButton.setText("SAVE"); //6
			saveButton.setSize(_buttonSize + 50, _buttonSize);
			saveButton.setLocation(20, 20);
			saveButton.setOpaque(true);
			saveButton.setBackground(Color.gray);
			saveButton.setBorder(BorderFactory.createMatteBorder(_buttonSize / _thickness, 
						_buttonSize / _thickness, _buttonSize / _thickness, _buttonSize / _thickness, Color.black ));
			contentPane.add(saveButton);
			_functionButtons[3] = saveButton;
			
			JButton passButton = _functionButtons[2]; //6
			passButton.setText("PASS"); //6
			passButton.setSize(_buttonSize + 50, _buttonSize);
			passButton.setLocation(20, 50);
			passButton.setOpaque(true);
			passButton.setBackground(Color.gray);
			passButton.setBorder(BorderFactory.createMatteBorder(_buttonSize / _thickness, 
						_buttonSize / _thickness, _buttonSize / _thickness, _buttonSize / _thickness, Color.black ));
			contentPane.add(passButton);
			_functionButtons[2] = passButton;
			
			JButton submitButton = _functionButtons[0]; //6
			submitButton.setText("SUBMIT"); //6
			submitButton.setSize(_buttonSize + 50, _buttonSize);
			submitButton.setLocation(20, 80);
			submitButton.setOpaque(true);
			submitButton.setBackground(Color.gray);
			submitButton.setBorder(BorderFactory.createMatteBorder(_buttonSize / _thickness, 
						_buttonSize / _thickness, _buttonSize / _thickness, _buttonSize / _thickness, Color.black ));
			contentPane.add(submitButton);
			_functionButtons[0] = submitButton;
			
			JButton removeButton = _functionButtons[1]; //6
			removeButton.setText("REMOVE"); //6
			removeButton.setSize(_buttonSize + 50, _buttonSize);
			removeButton.setLocation(20, 110);
			removeButton.setOpaque(true);
			removeButton.setBackground(Color.gray);
			removeButton.setBorder(BorderFactory.createMatteBorder(_buttonSize / _thickness, 
						_buttonSize / _thickness, _buttonSize / _thickness, _buttonSize / _thickness, Color.black ));
			contentPane.add(removeButton);
			_functionButtons[1] = removeButton;
			
			JPanel labelPanel = new JPanel();
			labelPanel.setLayout(new FlowLayout());
			labelPanel.setLocation(100,140);
			labelPanel.setSize(200, 30);
			contentPane.add(labelPanel);
			JLabel leftInBagLabel = _label;
			leftInBagLabel.setVisible(true);
			leftInBagLabel.setFont(new java.awt.Font("Times New Roman", 1, 18));
			labelPanel.add(leftInBagLabel, JLabel.CENTER);
			// Ali End 4/10
			
			// Ali start 5/6
			JPanel whoseTurnPanel = new JPanel();
			whoseTurnPanel.setLayout(new FlowLayout());
			whoseTurnPanel.setLocation(90, 250);
			whoseTurnPanel.setSize(200, 30);
			contentPane.add(whoseTurnPanel);
			JLabel whoseTurnLabel = _whoseTurnLabel;
			whoseTurnLabel.setVisible(true);
			whoseTurnLabel.setFont(new java.awt.Font("Times New Roman", 1, 18));
			whoseTurnPanel.add(whoseTurnLabel, JLabel.CENTER);
			//Ali end 5/6
			
			window.setVisible(true);
		}
		 
		
		
		/**
		 * Driver: Ali
		 * Navigator: James
		 * 4/27
		 */
		@Override
		public void update(Observable o, Object arg) {
			
			Tile_024_062 dummyTile = new Tile_024_062(' ',0);
			
			/* edit by Noah, Ali, and James Driver:Noah Navigators:Ali and James 4/29 */
			
			for (int i = 0; i < _scrabble.getPlayers().size(); i = i + 1) {
				Player_024_062 player = _scrabble.getPlayers().get(i);
				JButton[] buttons = _buttonsForRacks.get(i);
				
				if(i == 0) {
					_playerLabel0.setText(player.getName() + " : " + player.getScore());
				}
				
				if(i == 1) {
					_playerLabel1.setText(player.getName() + " : " + player.getScore());
				}
				
				if(i == 2) {
					_playerLabel2.setText(player.getName() + " : " + player.getScore());
				}
				
				if(i == 3) {
					_playerLabel3.setText(player.getName() + " : " + player.getScore());
				}
			}
			
			/* end edit 4/29 */
			
			String boardString = _scrabble.getBoard().boardToString();
			int stringBufferIndex = 0;
			for(int i = 0; i < 20; i = i + 1) {
				for(int j = 0; j < 20; j = j + 1) {
					
					/* Begin noahpocz (Noah Poczciwinski) individual edit (various times) */
					JButton button = _buttonsForBoard[i][j];
					
					Integer letter = new Integer(_scrabble.getBoard().getLetterMultiplier(i, j));
					Integer word = new Integer(_scrabble.getBoard().getWordMultiplier(i, j));
					_buttonsForBoard[i][j].setText(letter.toString() + " " + word.toString());
					_buttonsForBoard[i][j].setBackground(new Color(210,180,140)); //noah
					
					char c = boardString.charAt(stringBufferIndex);
					if (c != ' ') {
						String intVal = (new Integer(dummyTile.charValue(c))).toString();
						_buttonsForBoard[i][j].setText("" + c + "-" + intVal);
						_buttonsForBoard[i][j].setBackground(_scrabble.getBoard().getTile(i, j).getPlayer().getColor());
					}
					stringBufferIndex = stringBufferIndex + 1;
					/* End noahpocz (Noah Poczciwinski) individual edit (various times) */
				}
			}
			
			String tileRackString = _scrabble.getBoard().tileRacksToString(); //Driver ALi Nav Noah 4/28
			int stringBufferIndexForTileRacks = 0;
			for(int i = 0; i < _buttonsForRacks.size(); i = i + 1) {
				JButton[] buttonArray = _buttonsForRacks.get(i);
				for(int j = 0; j < 12; j = j + 1) {
					
					/* Begin noahpocz (Noah Poczciwinski) individual edit various times*/
					
					JButton button = buttonArray[j];
					char c = tileRackString.charAt(stringBufferIndexForTileRacks);
					String intVal = (new Integer(dummyTile.charValue(c))).toString();
					button.setText("" + c + "-" + (c == ' ' ? " " : intVal));
					stringBufferIndexForTileRacks = stringBufferIndexForTileRacks + 1;
					
					/* End noahpocz (Noah Poczciwinski) individual edit various times */
				}
			}
			
			_label.setText("Tiles left in bag : " + _scrabble.getBoard().leftInBag());
			_whoseTurnLabel.setText("Players Turn : " + _scrabble.getBoard().whoseTurn().getName());// Ali 5/6
			
			/* Begin noahpocz (Noah Poczciwinski) individual edit 4/28 */
			
			//highlighting is fully functional
			
			Integer[] oldHighlight = _scrabble.getBoard().getOldHighlightInformation();
			Integer[] highlightInformation = _scrabble.getBoard().highlight();
			
			_buttonsForBoard[oldHighlight[3]][oldHighlight[4]].setBorder(BorderFactory.createMatteBorder(_buttonSize / _thickness, 
					_buttonSize / _thickness, _buttonSize / _thickness, _buttonSize / _thickness, Color.black));
			
			//edits by Noah and Ali 5/5 d noah n ali (and noah individually)
			if (_scrabble.getBoard().getTile(oldHighlight[3], oldHighlight[4]) == null) {
				Integer letter = new Integer(_scrabble.getBoard().getLetterMultiplier(oldHighlight[3],oldHighlight[4]));
				Integer word = new Integer(_scrabble.getBoard().getWordMultiplier(oldHighlight[3],oldHighlight[4]));
			}
			//end edits
			
			_buttonsForRacks.get(oldHighlight[1])[oldHighlight[2]].setBorder(BorderFactory.createMatteBorder(_buttonSize / _thickness, 
					_buttonSize / _thickness, _buttonSize / _thickness, _buttonSize / _thickness, Color.black));
			
			if (highlightInformation[0] == 1) { //we'll need to highlight a freshly placed tile
				_buttonsForBoard[highlightInformation[3]][highlightInformation[4]].setBorder(BorderFactory.createMatteBorder(_buttonSize / _thickness, 
						_buttonSize / _thickness, _buttonSize / _thickness, _buttonSize / _thickness, Color.red));
			}
			
			if (highlightInformation[0] == 2) { //highlight something on the tileRack
				_buttonsForRacks.get(highlightInformation[1])[highlightInformation[2]].setBorder(BorderFactory.createMatteBorder(_buttonSize / _thickness, 
						_buttonSize / _thickness, _buttonSize / _thickness, _buttonSize / _thickness, Color.red));
			}
			
			
			/* End noahpocz (Noah Poczciwinski) individual edit 4/28 */
		}
		
}

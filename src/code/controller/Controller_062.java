package code.controller;

import java.util.ArrayList;

import javax.swing.JButton;

import code.controller.BoardButtonHandler_062;
import code.controller.FunctionButtonHandler_062;
import code.controller.TileRackButtonHandler_062;
import code.model.Scrabble_024_062;

/**
@author Noah, Ali
@description This class serves to instantiate and assign EventHandlers to the JButtons instantiated in the ScrabbleView class
@datecreated 4/7
**/

public class Controller_062 {
	
	Scrabble_024_062 _model;
	JButton[][] _buttonsForBoard;
	ArrayList<JButton[]> _buttonsForRack;
	JButton[] _functionButtons;
	
	public Controller_062(Scrabble_024_062 model, JButton[][] boardButtons, ArrayList<JButton[]> tileRackButtons, JButton[] functionButtons) {
		_buttonsForBoard = boardButtons;
		_buttonsForRack = tileRackButtons;
		_functionButtons = functionButtons;
		_model = model;
		
		for (int i = 0; i < 20; i++) {		//add an event handler to each board button with the corresponding coordinates
			for (int j = 0; j < 20; j++) {
				_buttonsForBoard[i][j].addActionListener(new BoardButtonHandler_062(i, j, _model));
			}
		}
		
		int a = 0;
		for (JButton[] b : _buttonsForRack) {
			for (int i = 0; i < 12; i++) {
				b[i].addActionListener(new TileRackButtonHandler_062(a, i, _model));
			}
			a++;
		}
		_functionButtons[0].addActionListener(new FunctionButtonHandler_062("SUBMIT", _model));
		_functionButtons[1].addActionListener(new FunctionButtonHandler_062("REMOVE", _model));
		_functionButtons[2].addActionListener(new FunctionButtonHandler_062("PASS", _model));
		_functionButtons[3].addActionListener(new FunctionButtonHandler_062("SAVE", _model));
	}

}

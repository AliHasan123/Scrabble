package code.controller;

//author Noah during meeting 4/3

import code.model.Scrabble_024_062;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardButtonHandler_062 implements ActionListener {
	
	int _x;
	int _y;
	Scrabble_024_062 _model;
	
	public BoardButtonHandler_062(int x, int y, Scrabble_024_062 model) {
		_x = x;
		_y = y;
		_model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		_model.getBoard().boardTileClicked(_x, _y); //Noah 4/7
	}

}

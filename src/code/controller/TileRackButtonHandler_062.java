package code.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.model.Scrabble_024_062;

public class TileRackButtonHandler_062 implements ActionListener {
	
	private int _playerNumber;
	private int _rackPosition;
	private Scrabble_024_062 _model;
	
	public TileRackButtonHandler_062(int num, int pos, Scrabble_024_062 model) {
		_playerNumber = num;
		_rackPosition = pos;
		_model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		_model.getBoard().tileRackClicked(_playerNumber, _rackPosition);
	}

}

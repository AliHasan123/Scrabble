package code.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import code.model.Scrabble_024_062;

//author Noah during meeting 4/3

public class FunctionButtonHandler_062 implements ActionListener {
	
	String _function;
	Scrabble_024_062 _model;
	
	public FunctionButtonHandler_062(String function, Scrabble_024_062 model) {
		_function = function;
		_model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		_model.functionButtonPressed(_function);
	}
	
	

}

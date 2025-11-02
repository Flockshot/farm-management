package me.somaan.farmapp.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputValidationListener implements KeyListener
{
	private InputType inputType;
	
	public InputValidationListener(InputType inputType)
	{
		setInputType(inputType);
	}

	
	
	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}



	@Override
	public void keyTyped(KeyEvent e)
	{
		char input = e.getKeyChar();
		
		if(getInputType().equals(InputType.INTEGER))
		{
			if(!Character.isDigit(input))
				e.consume();			
		}
		else if(getInputType().equals(InputType.DOUBLE))
		{
			if(!(Character.isDigit(input) || input == '.'))
				e.consume();		
		}			
	}



	@Override
	public void keyPressed(KeyEvent e) {
	}



	@Override
	public void keyReleased(KeyEvent e) {
	}
}

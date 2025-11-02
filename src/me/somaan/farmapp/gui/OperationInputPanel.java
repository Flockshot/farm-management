package me.somaan.farmapp.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OperationInputPanel extends JPanel {
	private JComponent inputField;
	private InputType inputType;

	/**
	 * Create the panel.
	 */
	public OperationInputPanel(String inputPrompt, InputType inputType)
	{
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		setPreferredSize(new Dimension(200, 0));
		
		JLabel lblNewLabel = new JLabel(inputPrompt);
		add(lblNewLabel);
		
		
		if(inputType.equals(InputType.BOOLEAN))
		{
			inputField = new JCheckBox();			
		}
		else
		{
			JTextField textArea = new JTextField();
			textArea.setColumns(10);
			
			textArea.addKeyListener(new InputValidationListener(inputType));
			
			inputField = textArea;
		}
		
		add(inputField);
		
		setInputType(inputType);
		//JComboBox<Object> comboBox = new JComboBox<Object>();
		//add(comboBox);
		
	}
	
	public Object getInputValue()
	{
		if(inputField instanceof JCheckBox)
			return ((JCheckBox) inputField).isSelected();
		
		if(inputField instanceof JTextField)
		{			
			String text = ((JTextField) inputField).getText();
			
			if(getInputType().equals(InputType.INTEGER))
				return Integer.valueOf(text);
			else if(getInputType().equals(InputType.DOUBLE))
				return Double.valueOf(text);
			
			return text;
		}
			
		
		
		return null;		
	}

	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}
	
	public JComponent getInputField() {
		return inputField;
	}
	
	public void reset()
	{
		if(inputField instanceof JCheckBox)
			((JCheckBox) inputField).setSelected(false);
		
		if(inputField instanceof JTextField)
			((JTextField) inputField).setText("");

	}


}

package me.somaan.farmapp.gui.pages;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import me.somaan.farmapp.gui.InputType;
import me.somaan.farmapp.gui.OperationInputPanel;

public class OperationsPage extends JPanel {
	private JButton addButton;
	private OperationInputPanel deleteInput;
	private JButton deleteButton;
	private OperationInputPanel printInput;
	private JButton printButton;
	private ArrayList<OperationInputPanel> inputs;

	/**
	 * Create the panel.
	 */
	public OperationsPage(ArrayList<OperationInputPanel> inputs)
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setInputs(inputs);
		
		for(OperationInputPanel input : inputs)
		{
			add(input);
		}
		
		//setPreferredSize(new Dimension(318, 215));
		
		addButton = new JButton("Add");
		add(addButton);
		
		
		setDeleteInput(new OperationInputPanel("Delete Key (ID)", InputType.INTEGER));
		add(getDeleteInput());
		deleteButton = new JButton("Delete");
		add(deleteButton);
				
		setPrintInput(new OperationInputPanel("Print Key (ID)", InputType.INTEGER));
		add(getPrintInput());
		printButton = new JButton("Print");
		add(printButton);
		
		


	}

	public JButton getAddButton() {
		return addButton;
	}

	public void setAddButton(JButton addButton) {
		this.addButton = addButton;
	}

	public OperationInputPanel getDeleteInput() {
		return deleteInput;
	}

	public void setDeleteInput(OperationInputPanel deleteInput) {
		this.deleteInput = deleteInput;
		deleteInput.setMinimumSize(new Dimension(96, 10));
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(JButton deleteButton) {
		this.deleteButton = deleteButton;
	}

	public JButton getPrintButton() {
		return printButton;
	}

	public void setPrintButton(JButton printButton) {
		this.printButton = printButton;
	}

	public OperationInputPanel getPrintInput() {
		return printInput;
	}

	public void setPrintInput(OperationInputPanel printInput) {
		this.printInput = printInput;
	}

	public ArrayList<OperationInputPanel> getInputs() {
		return inputs;
	}

	public void setInputs(ArrayList<OperationInputPanel> inputs) {
		this.inputs = inputs;
	}
	
	public ArrayList<Object> getInputValues() {
		ArrayList<Object> inputValues = new ArrayList<Object>();
		
		for(OperationInputPanel input : getInputs())
		{
			inputValues.add(input.getInputValue());
		}
		
		return inputValues;
	}
	
	public void resetAll()
	{
		for(OperationInputPanel input : getInputs())
		{
			input.reset();
		}
		deleteInput.reset();
		printInput.reset();
		
	}

}

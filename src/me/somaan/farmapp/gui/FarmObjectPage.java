package me.somaan.farmapp.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import me.somaan.farmapp.gui.pages.OperationsPage;
import me.somaan.farmapp.gui.pages.SingleListPage;

public abstract class FarmObjectPage extends JSplitPane  {

	protected JTextArea textArea;
	protected OperationsPage inputs;
	protected SingleListPage dataList;
	
	/**
	 * Create the panel.
	 */
	public FarmObjectPage(SingleListPage dataList, OperationsPage inputs)
	{
		setName(dataList.getName());
		//setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.inputs = inputs;
		
		JSplitPane splitPane_1 = this;
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane_1.setRightComponent(splitPane);
		
		//JPanel panel = new JPanel();
		splitPane.setRightComponent(dataList);
		this.dataList = dataList;

		
		
		
		textArea = new JTextArea(8, 40);
		
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		splitPane.setLeftComponent(scrollPane);
		
		//operations
		splitPane_1.setLeftComponent(inputs);

		
		inputs.getAddButton().addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        createAndAdd(inputs.getInputValues());
		        inputs.resetAll();
		        
		    }
		});
		
		inputs.getDeleteButton().addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        deleteObject(inputs.getDeleteInput().getInputValue());
		        inputs.resetAll();
		    }
		});
		
		inputs.getPrintButton().addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        printObject(inputs.getPrintInput().getInputValue());
		        inputs.resetAll();
		    }
		});
		
		
		
		


	}

	protected abstract void printObject(Object inputValue);

	protected abstract void deleteObject(Object inputValue);

	protected abstract void createAndAdd(ArrayList<Object> inputValues);
	
	protected void println(String text)
	{
		print(text + "\n");
	}

	protected void print(String text) {
		textArea.append(text);		
	}
}

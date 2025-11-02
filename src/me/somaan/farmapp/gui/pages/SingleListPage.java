package me.somaan.farmapp.gui.pages;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import me.somaan.farmapp.gui.FarmTable;
import me.somaan.farmapp.gui.FarmTableModel;

@SuppressWarnings("serial")
public class SingleListPage extends JScrollPane
{
	private JTable table;

	/**
	 * Create the panel.
	 */

	public SingleListPage(FarmTableModel tableModel, String name)
	{
		setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		table = new JTable();
		
		if(tableModel==null)
			table.setModel(new DefaultTableModel());
		else
			table.setModel(tableModel);
		
		
		

		table.setName("Test");
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//add(table);
		//setColumnHeaderView(table);
		setViewportView(table);
		setName(name);
		setToolTipText("Lists "+name);


	}
	
	public void updateTable(ArrayList<FarmTable> farmData)
	{
		
		
		table.setModel(new FarmTableModel(farmData));
	}

}

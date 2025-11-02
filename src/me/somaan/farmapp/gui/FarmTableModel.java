package me.somaan.farmapp.gui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class FarmTableModel extends DefaultTableModel
{
	
	private ArrayList<FarmTable> farmData;
	
	public FarmTableModel(ArrayList<FarmTable> data)
	{
		
		if(data.size()>0)
		{
			ArrayList<String> columnNames = data.get(0).getColumnNames();
			
			Object[][] allData = new Object[data.size()][columnNames.size()];
			
			for(int i = 0; i<data.size(); i++)
			{
				allData[i] = data.get(i).getData().toArray();
			}
			
			super.setDataVector(allData, columnNames.toArray());			
		}
		
		setFarmData(data);
				
	}
	
	@Override
	public boolean isCellEditable(int row, int column){  
        return false;
    }

	public ArrayList<FarmTable> getFarmData() {
		return farmData;
	}

	public void setFarmData(ArrayList<FarmTable> farmData) {
		this.farmData = farmData;
	}
	
	
	

}

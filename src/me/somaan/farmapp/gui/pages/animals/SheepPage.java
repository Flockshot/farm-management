package me.somaan.farmapp.gui.pages.animals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import me.somaan.farmapp.FarmApp;
import me.somaan.farmapp.animals.AnimalType;
import me.somaan.farmapp.animals.Sheep;
import me.somaan.farmapp.gui.pages.OperationsPage;
import me.somaan.farmapp.gui.pages.SingleListPage;

public class SheepPage extends AnimalPage
{

	public SheepPage(SingleListPage dataList, OperationsPage inputs)
	{
		super(dataList, inputs);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	protected void createAndAdd(ArrayList<Object> inputValues)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		if(inputValues.size()>=4)		
			FarmApp.addAnimal( new Sheep((int) inputValues.get(0), (String) inputValues.get(1), LocalDate.parse((String) inputValues.get(2), formatter), (boolean) inputValues.get(3)));
	
		println("Sheep Added.");
		updateTable();
	}



	@Override
	public AnimalType getType() {
		return AnimalType.SHEEP;
	}

}

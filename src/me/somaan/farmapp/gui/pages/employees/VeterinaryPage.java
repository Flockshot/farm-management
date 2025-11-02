package me.somaan.farmapp.gui.pages.employees;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import me.somaan.farmapp.FarmApp;
import me.somaan.farmapp.employees.EmployeeType;
import me.somaan.farmapp.employees.Veterinary;
import me.somaan.farmapp.gui.pages.OperationsPage;
import me.somaan.farmapp.gui.pages.SingleListPage;

public class VeterinaryPage extends EmployeePage
{

	public VeterinaryPage(SingleListPage dataList, OperationsPage inputs)
	{
		super(dataList, inputs);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	protected void createAndAdd(ArrayList<Object> inputValues)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		if(inputValues.size()>=6)		
		{
			Veterinary vet = null;
			
			if(((String) inputValues.get(5)).isBlank() || !((boolean) inputValues.get(4)))
				vet = new Veterinary((int) inputValues.get(0), (String) inputValues.get(1), LocalDate.parse((String) inputValues.get(2), formatter), (int) inputValues.get(3));
			else
				vet = new Veterinary((int) inputValues.get(0), (String) inputValues.get(1), LocalDate.parse((String) inputValues.get(2), formatter), (int) inputValues.get(3), (boolean) inputValues.get(4), LocalDate.parse((String) inputValues.get(5), formatter));
			
			FarmApp.addEmployee(vet);
		}
			
	
		println("Vet Added.");
		updateTable();
	}



	@Override
	public EmployeeType getType() {
		return EmployeeType.VETERINARY;
	}

}

package me.somaan.farmapp.gui.pages.employees;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import me.somaan.farmapp.FarmApp;
import me.somaan.farmapp.employees.EmployeeType;
import me.somaan.farmapp.employees.FarmWorker;
import me.somaan.farmapp.gui.pages.OperationsPage;
import me.somaan.farmapp.gui.pages.SingleListPage;

public class FarmWorkerPage extends EmployeePage
{

	public FarmWorkerPage(SingleListPage dataList, OperationsPage inputs)
	{
		super(dataList, inputs);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	protected void createAndAdd(ArrayList<Object> inputValues)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		if(inputValues.size()>=5)		
		{
			FarmWorker farmWorker = new FarmWorker((int) inputValues.get(0), (String) inputValues.get(1), LocalDate.parse((String) inputValues.get(2), formatter), (int) inputValues.get(3), (String) inputValues.get(4));
			FarmApp.addEmployee(farmWorker);
		}
			
	
		println("FarmWorker Added.");
		updateTable();
	}



	@Override
	public EmployeeType getType() {
		return EmployeeType.FARM_WORKER;
	}

}

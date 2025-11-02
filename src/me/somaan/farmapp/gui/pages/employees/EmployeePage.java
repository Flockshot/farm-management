package me.somaan.farmapp.gui.pages.employees;

import me.somaan.farmapp.FarmApp;
import me.somaan.farmapp.employees.Employee;
import me.somaan.farmapp.employees.EmployeeType;
import me.somaan.farmapp.gui.FarmObjectPage;
import me.somaan.farmapp.gui.pages.OperationsPage;
import me.somaan.farmapp.gui.pages.SingleListPage;

public abstract class EmployeePage extends FarmObjectPage
{
	

	public EmployeePage(SingleListPage dataList, OperationsPage inputs) {
		super(dataList, inputs);
	
	}
	
	

	@Override
	protected void printObject(Object inputValue) {
		if(inputValue instanceof Integer)
		{
			//TODO print
			Employee emp = FarmApp.getEmployee((int) inputValue);
			for(String printString : emp.printDetailsString())
				println(printString);
				
		}
	}

	@Override
	protected void deleteObject(Object inputValue) {
		
		if(inputValue instanceof Integer)
		{
			FarmApp.deleteEmployee((int) inputValue);
			println("Employee deleted.");
			
			updateTable();
		}
		
	}
	
	void updateTable()
	{
		dataList.updateTable(FarmApp.getEmployeeMap().get(getType()));
	}
	
	public abstract EmployeeType getType();



}

package me.somaan.farmapp.employees;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

import me.somaan.farmapp.gui.InputType;
import me.somaan.farmapp.gui.OperationInputPanel;

/**
 * FarmWorker class to represent the farm-workers providing treatments for the animals.
 * @author Muhammad Somaan
 * @version 1.0
 */
public class FarmWorker extends Employee {

    /**
     * Work experience in number of years.
     */
    private int workExperience;
    /**
     * Name of the previous farm the worker worked at.
     */
    private String previousFarmName;


    /**
     * Constructor to create FarmWorker object without working on any previous farm.
     * @param empID          ID number of the farm-worker.
     * @param gender         gender of the farm-worker.
     * @param dateOfBirth    date of birth of the farm-worker.
     */
    public FarmWorker(int empID, String gender, LocalDate dateOfBirth) {
        this(empID, gender, dateOfBirth, 0, ""); //Assumed that work experience is 0.
    }

    /**
     * Constructor to create FarmWorker object.
     * @param empID             ID number of the farm-worker.
     * @param gender            gender of the farm-worker.
     * @param dateOfBirth       date of birth of the farm-worker.
     * @param workExperience    expertise level of the farm-worker.
     * @param previousFarmName  name of the previous farm the worker worked at.
     */
    public FarmWorker(int empID, String gender, LocalDate dateOfBirth, int workExperience, String previousFarmName) {
        super(empID, gender, dateOfBirth);
        setWorkExperience(workExperience);
        setPreviousFarmName(previousFarmName);
    }

    /**
     * Get the salary of the farm-worker.
     * @return the salary of the farm-worker.
     */
    @Override
    public double getSalary() {
        return grossSalary+grossSalary*0.02*getWorkExperience();
    }

    /**
     * Get the type of employee (FarmWorker).
     * @return EmployeeType as farm-worker.
     */
    @Override
    public EmployeeType getType() {
        return EmployeeType.FARM_WORKER;
    }

    /**
     * Prints all the details of the farm-worker.
     */
    public void printDetails() {
        super.printDetails();

        System.out.println(getType().toString() + "'s work experience in years: " + getWorkExperience());
        System.out.println(getType().toString() + "'s previous farm name: " + getPreviousFarmName());
        System.out.println("");
    }
    
	public ArrayList<String> printDetailsString() {
		ArrayList<String> printString = super.printDetailsString();
		
		printString.add(getType().toString() + "'s work experience in years: " + getWorkExperience());
		printString.add(getType().toString() + "'s previous farm name: " + getPreviousFarmName());
		printString.add("");
		
		
		return printString;
	}


    /**
     * Get the work experience of the farm-worker.
     * @return the work experience of the farm-worker.
     */
    public int getWorkExperience() {
        return workExperience;
    }
    /**
     * Set the work experience of the farm-worker.
     * @param workExperience work experience of the farm-worker.
     */
    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    /**
     * Get the name of the previous farm the worker worked at.
     * @return the name of previous farm.
     */
    public String getPreviousFarmName() {
        return previousFarmName;
    }
    /**
     * Set the name of the previous farm the worker worked at.
     * @param previousFarmName name of the previous farm.
     */
    public void setPreviousFarmName(String previousFarmName) {
        this.previousFarmName = previousFarmName;
    }
    
	public ArrayList<String> getColumnNames()
	{
		ArrayList<String> columns = super.getColumnNames();
		columns.add("Work Experience");
		columns.add("Previous Farm Name");
		
		return columns;		
	}

	
	public ArrayList<Object> getData() 
	{
		ArrayList<Object> data = super.getData();
		
		data.add(getWorkExperience());
		data.add(getPreviousFarmName());
		
		return data;
	}
	
	
	public static ArrayList<OperationInputPanel> getOperationInputPanels()
	{
		ArrayList<OperationInputPanel> inputs = Employee.getOperationInputPanels();
		
		inputs.add(new OperationInputPanel("Work Experience", InputType.INTEGER));
		inputs.add(new OperationInputPanel("Previous Farm Name", InputType.STRING));
		
		return inputs;
	}

    
}

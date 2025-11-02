package me.somaan.farmapp.employees;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import me.somaan.farmapp.gui.FarmTable;
import me.somaan.farmapp.gui.InputType;
import me.somaan.farmapp.gui.OperationInputPanel;

/**
 * Employee class to represent the employees providing treatments for the animals.
 * @author Muhammad Somaan
 * @version 1.0
 */
public abstract class Employee implements Payment, Comparable<Employee>, FarmTable, Serializable {
    /**
     * Employee ID number.
     */
    private int empID;
    /**
     * Gender of the employee.
     */
    private String gender;
    /**
     * Date of birth of the employee.
     */
    private LocalDate dateOfBirth;

    /**
     * Constructor to create Veterinary object.
     * @param empID ID number of the vet.
     * @param gender gender of the vet.
     * @param dateOfBirth date of birth of the vet.
     */
    public Employee(int empID, String gender, LocalDate dateOfBirth) {
        setEmpID(empID);
        setGender(gender);
        setDateOfBirth(dateOfBirth);
    }

    /**
     * Get the type of employee, eg; if its vet or farm worker.
     * @return EmployeeType enum holding which type of employee it is.
     */
    public abstract EmployeeType getType();

    /**
     * Get the ID number of the employee.
     * @return the ID number of the employee.
     */
    public int getEmpID() {
        return empID;
    }
    /**
     * Set the ID number of the employee.
     * @param empID ID number of the employee.
     */
    public void setEmpID(int empID) {
        this.empID = empID;
    }

    /**
     * Get the gender of the employee.
     * @return the gender of the employee.
     */
    public String getGender() {
        return gender;
    }
    /**
     * Set the gender of the employee.
     * @param gender gender of the employee.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get the date of birth of the employee.
     * @return the date of birth of the employee.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    /**
     * Set the date of birth of the employee.
     * @param dateOfBirth date of birth of the employee.
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    /**
     * Prints all the details of the vet.
     */
    public void printDetails() {
        System.out.println(getType().toString() + "'s ID: " + getEmpID());
        System.out.println(getType().toString() + "'s gender: " + getGender());
        System.out.println(getType().toString() + "'s date of birth: " + getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println(getType().toString() + "'s Salary: " + getSalary());
    }
    
	public ArrayList<String> printDetailsString() {
		ArrayList<String> printString = new ArrayList<String>();
		
		printString.add(getType().toString() + "'s ID: " + getEmpID());
		printString.add(getType().toString() + "'s gender: " + getGender());
		printString.add(getType().toString() + "'s date of birth: " + getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		printString.add(getType().toString() + "'s Salary: " + getSalary());		
		
		
		return printString;
	}

    /**
     * Compares the id of the employee with another employee
     * @param other the object to be compared.
     * @return 1 if the employee id is greater, 0 if employee id is same, -1 if the employee id is lesser
     */
    @Override
    public int compareTo(Employee other) {
        int comparison = Integer.compare(getEmpID(), other.getEmpID());
        return comparison !=0 ? (comparison/comparison) : comparison;
    }
    
    
	public ArrayList<String> getColumnNames()
	{
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("ID");
		columns.add("Gender");
		columns.add("Date of Birth");
		
		return columns;		
	}

	
	public ArrayList<Object> getData() 
	{
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(getEmpID());
		data.add(getGender());
		data.add(getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		return data;
	}
	
	
	public static ArrayList<OperationInputPanel> getOperationInputPanels()
	{
		ArrayList<OperationInputPanel> inputs = new ArrayList<OperationInputPanel>();
		
		inputs.add(new OperationInputPanel("Employee ID", InputType.INTEGER));
		inputs.add(new OperationInputPanel("Gender", InputType.STRING));
		inputs.add(new OperationInputPanel("Date of Birth (dd/mm/yyyy)", InputType.STRING));
		
		return inputs;
	}

    public String toString()
    {
        String toReturn = "";

        for(String str : printDetailsString())
            toReturn = toReturn + "\n" + str;

        return toReturn;
    }
}

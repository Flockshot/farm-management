package me.somaan.farmapp.employees;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import me.somaan.farmapp.gui.InputType;
import me.somaan.farmapp.gui.OperationInputPanel;

/**
 * Veterinary class to represent the vets providing treatments for the cows.
 * @author Muhammad Somaan
 * @version 2.0
 */
public class Veterinary extends Employee {

    /**
     * Expertise level of the vet.
     */
    private int expertiseLevel;
    /**
     * if the vet has a BSc Degree.
     */
    private boolean bscDegree;
    /**
     * Date of graduation of the vet.
     */
    private LocalDate dateOfGraduation;


    /**
     * Constructor to create Veterinary object without a Bsc Degree.     *
     * @param empID          ID number of the vet.
     * @param gender         gender of the vet.
     * @param dateOfBirth    date of birth of the vet.
     * @param expertiseLevel expertise level of the vet.
     */
    public Veterinary(int empID, String gender, LocalDate dateOfBirth, int expertiseLevel) {
        this(empID, gender, dateOfBirth, expertiseLevel, false, LocalDate.MAX);
    }

    /**
     * Constructor to create Veterinary object.
     * @param empID             ID number of the vet.
     * @param gender            gender of the vet.
     * @param dateOfBirth       date of birth of the vet.
     * @param expertiseLevel    expertise level of the vet.
     * @param bscDegree         if the vet has a bsc degree.
     * @param dateOfGraduation  the date of graduation.
     */
    public Veterinary(int empID, String gender, LocalDate dateOfBirth, int expertiseLevel, boolean bscDegree, LocalDate dateOfGraduation) {
        super(empID, gender, dateOfBirth);
        setExpertiseLevel(expertiseLevel);
        setBscDegree(bscDegree);
        setDateOfGraduation(dateOfGraduation);
    }

    /**
     * Get the salary of the vet.
     * @return the salary of the vet.
     */
    @Override
    public double getSalary()
    {
        int numberOfYearsSinceGrad = 0;
        if(hasBscDegree())
            numberOfYearsSinceGrad = Period.between(getDateOfGraduation(), LocalDate.now()).getYears();

        return grossSalary+grossSalary*0.1*numberOfYearsSinceGrad;
    }

    /**
     * Get the type of employee (Veterinary).
     * @return EmployeeType as veterinary.
     */
    @Override
    public EmployeeType getType() {
        return EmployeeType.VETERINARY;
    }

    /**
     * Prints all the details of the vet.
     */
    public void printDetails() {
        super.printDetails();

        System.out.println(getType().toString() + " has BSc Degree: " + hasBscDegree());
        if(hasBscDegree())
            System.out.println(getType().toString() + "'s date of graduation: " + getDateOfGraduation().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println(getType().toString() + "'s expertise level: " + getExpertiseLevel());

        System.out.println("");
    }

	public ArrayList<String> printDetailsString() {
		ArrayList<String> printString = super.printDetailsString();
		
		printString.add(getType().toString() + " has BSc Degree: " + hasBscDegree());
        if(hasBscDegree())
        	printString.add(getType().toString() + "'s date of graduation: " + getDateOfGraduation().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		printString.add(getType().toString() + "'s expertise level: " + getExpertiseLevel());
		printString.add("");
		
		
		return printString;
	}

    /**
     * Get the expertise level of the vet
     * @return the expertise level of the vet
     */
    public int getExpertiseLevel() {
        return expertiseLevel;
    }
    /**
     * Set the expertiseLevel of the vet.
     * @param expertiseLevel expertise level of the vet.
     */
    public void setExpertiseLevel(int expertiseLevel) {
        this.expertiseLevel = expertiseLevel;
    }

    /**
     * Get if the vet has BSc Degree.
     * @return true or false depending on if vet has a BSc Degree.
     */
    public boolean hasBscDegree() {
        return bscDegree;
    }
    /**
     * Set if the vet has BSc Degree.
     * @param bscDegree if the vet has BSc Degree.
     */
    public void setBscDegree(boolean bscDegree) {
        this.bscDegree = bscDegree;
    }

    /**
     * Get the date of graduation of the vet.
     * @return the date of graduation of the vet.
     */
    public LocalDate getDateOfGraduation() {
        return dateOfGraduation;
    }
    /**
     * Set the date of graduation of the vet.
     * @param dateOfGraduation date of graduation of the vet.
     */
    public void setDateOfGraduation(LocalDate dateOfGraduation) {
        this.dateOfGraduation = dateOfGraduation;
    }
    
    
	public ArrayList<String> getColumnNames()
	{
		ArrayList<String> columns = super.getColumnNames();
		columns.add("Experience Level");
		columns.add("Has BSc Degree");
		columns.add("Date of Graduation");
		
		return columns;		
	}

	
	public ArrayList<Object> getData() 
	{
		ArrayList<Object> data = super.getData();
		
		data.add(getExpertiseLevel());
		data.add(hasBscDegree());
		data.add(getDateOfGraduation().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		return data;
	}
	
	
	public static ArrayList<OperationInputPanel> getOperationInputPanels()
	{
		ArrayList<OperationInputPanel> inputs = Employee.getOperationInputPanels();
		
		inputs.add(new OperationInputPanel("Experience Level", InputType.INTEGER));
		inputs.add(new OperationInputPanel("Has BSc Degree", InputType.BOOLEAN));
		inputs.add(new OperationInputPanel("Date of Graduation (dd/mm/yyyy)", InputType.STRING));
		
		return inputs;
	}


}

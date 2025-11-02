package me.somaan.farmapp.treatments;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import me.somaan.farmapp.gui.FarmTable;
import me.somaan.farmapp.gui.InputType;
import me.somaan.farmapp.gui.OperationInputPanel;

/**
 * Treatment class to represent the treatments received by an animal.
 * @author Muhammad Somaan
 * @version 2.0
 */
public class Treatment implements FarmTable, Serializable {

    /**
     * Date on which the treatment took place.
     */
    private LocalDate dateOfTreatment;
    /**
     * Details of the treatment.
     */
    private String details;


    /**
     * Constructor to create a Treatment object with today's date.
     * @param details details of the treatment.
     */
    public Treatment(String details) {
        this(details, LocalDate.now());
    }
    /**
     * Constructor to create a Treatment object with the given date.
     * @param details details of the treatment.
     * @param dateOfTreatment the date on which treatment took place.
     */
    public Treatment(String details, LocalDate dateOfTreatment) {
        setDetails(details);
        setDateOfTreatment(dateOfTreatment);
    }


    /**
     * Get the type of treatment (Treatment)
     * @return TreatmentType as Treatment
     */
    public TreatmentType getType(){
        return TreatmentType.TREATMENT;
    }

    /**
     * Prints all details of the treatment.
     */
    public void printDetails() {
        System.out.println("Treatment's date: " + getDateOfTreatment().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Treatment's details: " + getDetails());
    }
    
	public ArrayList<String> printDetailsString()
	{
		ArrayList<String> printStrings = new ArrayList<String>();
    	
		printStrings.add("Treatment's date: " + getDateOfTreatment().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		printStrings.add("Treatment's details: " + getDetails());
    	
        return printStrings;
	}

    /**
     * Get the date on which treatment took place.
     * @return the date on which treatment took place.
     */
    public LocalDate getDateOfTreatment() {
        return dateOfTreatment;
    }
    /**
     * Set the date on which the treatment took place.
     * @param dateOfTreatment date on which the treatment took place.
     */
    public void setDateOfTreatment(LocalDate dateOfTreatment) {
        this.dateOfTreatment = dateOfTreatment;
    }

    /**
     * Get the details of the treatment.
     * @return the details of the treatment.
     */
    public String getDetails() {
        return details;
    }
    /**
     * Set the details of the treatment.
     * @param details details of the treatment.
     */
    public void setDetails(String details) {
        this.details = details;
    }
    
    
	@Override
	public ArrayList<String> getColumnNames()
	{
		ArrayList<String> columns = new ArrayList<String>();
		
		columns.add("Date of Treatment");
		columns.add("Details");
		
		return columns;
	}
	@Override
	public ArrayList<Object> getData()
	{
		ArrayList<Object> data = new ArrayList<Object>();
		
		data.add(getDateOfTreatment().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		data.add(getDetails());
		
		return data;
	}

	
	public static ArrayList<OperationInputPanel> getOperationInputPanels()
	{
		ArrayList<OperationInputPanel> inputs = new ArrayList<OperationInputPanel>();
		
		//inputs.add(new OperationInputPanel("Experience Level", InputType.INTEGER));
		
		inputs.add(new OperationInputPanel("Animal ID", InputType.INTEGER));
		inputs.add(new OperationInputPanel("Employee ID", InputType.INTEGER));
		inputs.add(new OperationInputPanel("Date of Treatment (dd/mm/yyyy)", InputType.STRING));
		inputs.add(new OperationInputPanel("Details", InputType.STRING));
		
		
		
		return inputs;
	}


}

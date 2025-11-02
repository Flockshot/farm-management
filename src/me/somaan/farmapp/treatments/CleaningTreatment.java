package me.somaan.farmapp.treatments;

import java.time.LocalDate;
import java.util.ArrayList;

import me.somaan.farmapp.employees.FarmWorker;


/**
 * CleaningTreatment class to represent the cleaning treatments received by an animal.
 * @author Muhammad Somaan
 * @version 1.0
 */
public class CleaningTreatment extends Treatment{

    /**
     * The materials used in cleaning the animal.
     */
    private String materialsUsed;
    /**
     * The farm-worker that performed the cleaning of the animal.
     */
    private FarmWorker givenBy;


    /**
     * Constructor to create a CleaningTreatment object with today's date as the date of treatment.
     * @param givenBy the farm-worker who gave the treatment.
     * @param details details of the treatment.
     * @param materialsUsed the materials used in the cleaning treatment.
     */
    public CleaningTreatment(FarmWorker givenBy, String details, String materialsUsed) {
        this(givenBy, LocalDate.now(), details, materialsUsed);
    }


    /**
     * Constructor to create a CleaningTreatment object.
     * @param givenBy the farm-worker who gave the treatment.
     * @param dateOfTreatment the date on which treatment took place.
     * @param details details of the treatment.
     * @param materialsUsed materials used in the cleaning treatment.
     */
    public CleaningTreatment(FarmWorker givenBy, LocalDate dateOfTreatment, String details, String materialsUsed) {
        super(details, dateOfTreatment);
        setGivenBy(givenBy);
        setMaterialsUsed(materialsUsed);
    }


    /**
     * Get the type of treatment (Cleaning Treatment)
     * @return TreatmentType as Cleaning_Treatment
     */
    @Override
    public TreatmentType getType(){
        return TreatmentType.CLEANING_TREATMENT;
    }

    /**
     * Prints all details of the treatment, including farm-worker information.
     */
    @Override
    public void printDetails()
    {
  	
        System.out.println("Treatment given by farm-worker: ");
        getGivenBy().printDetails();

        super.printDetails();
        System.out.println("Materials used in treatment: " + getMaterialsUsed());

        System.out.println();
    }
    
	public ArrayList<String> printDetailsString()
	{
		ArrayList<String> printStrings = new ArrayList<String>();
		
		printStrings.add("Treatment given by farm-worker: ");
		printStrings.addAll(getGivenBy().printDetailsString());

		printStrings.addAll(super.printDetailsString());
		printStrings.add("Materials used in treatment: " + getMaterialsUsed());
		
		printStrings.add("");
    	
        return printStrings;
	}

    /**
     * Get the materials used in the cleaning treatment.
     * @return the materials used in the cleaning treatment.
     */
    public String getMaterialsUsed() {
        return materialsUsed;
    }

    /**
     * Sets the materials used in the cleaning treatment.
     * @param materialsUsed materials used in the cleaning treatment.
     */
    public void setMaterialsUsed(String materialsUsed) {
        this.materialsUsed = materialsUsed;
    }

    /**
     * Get the farm-worker that gave the treatment.
     * @return the FarmWorker object who gave the treatment.
     */
    public FarmWorker getGivenBy() {
        return givenBy;
    }
    /**
     * Set the FarmWorker object who gave the treatment.
     * @param givenBy FarmWorker object who gave the treatment.
     */
    public void setGivenBy(FarmWorker givenBy) {
        this.givenBy = givenBy;
    }
    
    
	@Override
	public ArrayList<String> getColumnNames()
	{
		ArrayList<String> columns = super.getColumnNames();
		
		columns.add("Materials Used");
		
		return columns;
	}
	@Override
	public ArrayList<Object> getData()
	{
		ArrayList<Object> data = super.getData();
		
		data.add(getMaterialsUsed());
		
		return data;
	}


}

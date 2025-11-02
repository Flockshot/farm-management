package me.somaan.farmapp.treatments;

import java.time.LocalDate;
import java.util.ArrayList;

import me.somaan.farmapp.employees.Veterinary;

/**
 * HealthTreatment class to represent the health treatments received by an animal.
 * @author Muhammad Somaan
 * @version 1.0
 */
public class HealthTreatment extends Treatment{

    /**
     * If the health treatment was an emergency or not.
     */
    private boolean emergency;
    /**
     * The veterinary that gave the medication to the animal.
     */
    private Veterinary givenBy;
    /**
     * List of all the medications given for the treatment.
     */
    private ArrayList<Medication> medications;


    /**
     * Constructor to create a HealthTreatment object with a single Medication
     * @param givenBy the veterinary who gave the treatment.
     * @param dateOfTreatment the date on which treatment took place.
     * @param details details of the treatment.
     * @param medication medication given for the treatment.
     * @param emergency if the health treatment was an emergency.
     */
    public HealthTreatment(Veterinary givenBy, LocalDate dateOfTreatment, String details, Medication medication, boolean emergency) {
        this(givenBy, dateOfTreatment, details, emergency);
        getMedications().add(medication);
    }

    /**
     * Constructor to create a HealthTreatment object with multiple Medication objects.
     * @param givenBy the veterinary who gave the treatment.
     * @param dateOfTreatment the date on which treatment took place.
     * @param details details of the treatment.
     * @param medicationList ArrayList of Medication objects to represent all the medications given for the treatment.
     * @param emergency if the health treatment was an emergency.
     */
    public HealthTreatment(Veterinary givenBy, LocalDate dateOfTreatment, String details, ArrayList<Medication> medicationList, boolean emergency) {
        this(givenBy, dateOfTreatment, details, emergency);
        setMedications(medicationList);
    }

    /**
     * Private constructor used in other public constructors to avoid repetition of code.
     * @param givenBy the veterinary who gave the treatment.
     * @param dateOfTreatment the date on which treatment took place.
     * @param details details of the treatment.
     * @param emergency if the health treatment was an emergency.
     */
    //Only exists to avoid repetition of code.
    private HealthTreatment(Veterinary givenBy, LocalDate dateOfTreatment, String details, boolean emergency) {
        super(details, dateOfTreatment);
        setGivenBy(givenBy);
        setEmergency(emergency);
        setMedications(new ArrayList<Medication>());
    }


    /**
     * Get the type of treatment (Health Treatment)
     * @return TreatmentType as Health_Treatment
     */
    @Override
    public TreatmentType getType(){
        return TreatmentType.HEALTH_TREATMENT;
    }

    /**
     * Prints all details of the treatment, including Veterinary information and all Medications details.
     */
    @Override
    public void printDetails()
    {
        System.out.println("Treatment given by vet: ");
        getGivenBy().printDetails();
        super.printDetails();
        System.out.println("Is Treatment an emergency: " + isEmergency());
        System.out.println("Treatment's medications: ");
        for (Medication medication : getMedications())
            medication.printDetails();

        System.out.println();
    }

	public ArrayList<String> printDetailsString()
	{
		ArrayList<String> printStrings = new ArrayList<String>();
		
		
		printStrings.add("Treatment given by vet: ");
		printStrings.addAll(getGivenBy().printDetailsString());
		printStrings.addAll(super.printDetailsString());
		printStrings.add("Is Treatment an emergency: " + isEmergency());
		printStrings.add("Treatment's medications: ");
        for (Medication medication : getMedications())
        	printStrings.addAll(medication.printDetailsString());
		
		printStrings.add("");
    	
        return printStrings;
	}


    /**
     * Gets if the health treatment is an emergency.
     * @return true if health treatment is an emergency, otherwise false.
     */
    public boolean isEmergency() {
        return emergency;
    }
    /**
     * Sets if the health treatment is an emergency.
     * @param emergency if the health treatment is an emergency.
     */
    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }

    /**
     * Get the Veterinary that gave the treatment.
     * @return the Veterinary object who gave the treatment.
     */
    public Veterinary getGivenBy() {
        return givenBy;
    }
    /**
     * Set the Veterinary object who gave the treatment.
     * @param givenBy Veterinary object who gave the treatment.
     */
    public void setGivenBy(Veterinary givenBy) {
        this.givenBy = givenBy;
    }

    /**
     * List containing all medications given for the treatment.
     * @return medications given for the treatment.
     */
    public ArrayList<Medication> getMedications() {
        return medications;
    }
    /**
     * Set the ArrayList of medications given for the treatment.
     * @param medications List of medications given for the treatment.
     */
    public void setMedications(ArrayList<Medication> medications) {
        this.medications = medications;
    }
    
    
	@Override
	public ArrayList<String> getColumnNames()
	{
		ArrayList<String> columns = super.getColumnNames();
		
		columns.add("Is Emergency");
		
		return columns;
	}
	@Override
	public ArrayList<Object> getData()
	{
		ArrayList<Object> data = super.getData();
		
		data.add(isEmergency());
		
		return data;
	}

}

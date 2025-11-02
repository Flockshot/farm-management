package me.somaan.farmapp.animals;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import me.somaan.farmapp.gui.FarmTable;
import me.somaan.farmapp.gui.InputType;
import me.somaan.farmapp.gui.OperationInputPanel;
import me.somaan.farmapp.treatments.Treatment;


/**
 * Abstract Animal class to create animal objects that are present on the farm.
 * @author Muhammad Somaan
 * @version 1.0
 */
public abstract class Animal implements FarmTable, Serializable {
    /**
     * Unique tag number of the animal.
     */
    private int tagNo;
    /**
     * Gender of the animal.
     */
    private String gender;
    /**
     * Date of birth of the animal.
     */
    private LocalDate dateOfBirth;
    /**
     * If the animal was purchased, or was it farm raised.
     */
    private boolean isPurchased;
    /**
     * List of all the treatments the animal received.
     */
    private ArrayList<Treatment> treatmentsReceived;
    /**
     * Record of all the milking measurements of the animal.
     */
    private HashMap<LocalDate, Double> milking;


    /**
     * Abstract constructor to create an animal object.
     * @param tagNo tag number of the animal.
     * @param gender gender of the animal.
     * @param dateOfBirth date of birth of the animal.
     * @param isPurchased if the animal was purchased or farm raised.
     */
    public Animal(int tagNo, String gender, LocalDate dateOfBirth, boolean isPurchased) {
        this(tagNo, gender, dateOfBirth, isPurchased, new ArrayList<Treatment>(), new HashMap<LocalDate, Double>());
    }

    /**
     * Constructor to create an animal object with a single treatment.
     * @param tagNo tag number of the animal.
     * @param gender gender of the animal.
     * @param dateOfBirth date of birth of the animal.
     * @param isPurchased if the animal was purchased or farm raised.
     * @param treatment treatment received by the animal.
     */
    public Animal(int tagNo, String gender, LocalDate dateOfBirth, boolean isPurchased, Treatment treatment) {
        this(tagNo, gender, dateOfBirth, isPurchased);
        addTreatment(treatment);
    }

    /**
     * Constructor to create an animal object with multiple treatments and empty milking record.
     * @param tagNo tag number of the animal.
     * @param gender gender of the animal.
     * @param dateOfBirth date of birth of the animal.
     * @param isPurchased if the animal was purchased or farm raised.
     * @param treatmentsReceived List of treatments received by the animal.
     */
    public Animal(int tagNo, String gender, LocalDate dateOfBirth, boolean isPurchased, ArrayList<Treatment> treatmentsReceived) {
        this(tagNo, gender, dateOfBirth, isPurchased, treatmentsReceived, new HashMap<LocalDate, Double>());
    }

    /**
     * Constructor to create an animal object with multiple treatments.
     * @param tagNo tag number of the animal.
     * @param gender gender of the animal.
     * @param dateOfBirth date of birth of the animal.
     * @param isPurchased if the animal was purchased or farm raised.
     * @param treatmentsReceived List of treatments received by the animal.
     * @param milking Record of all the milking measurements of the animal.
     */
    public Animal(int tagNo, String gender, LocalDate dateOfBirth, boolean isPurchased, ArrayList<Treatment> treatmentsReceived, HashMap<LocalDate, Double> milking) {
        setTagNo(tagNo);
        setGender(gender);
        setDateOfBirth(dateOfBirth);
        setPurchased(isPurchased);
        setTreatmentsReceived(treatmentsReceived);
        setMilkingMap(milking);
    }

    /**
     * Calculates the age of the animal.
     * @return the age of the animal.
     */
    public int getAge() {
        Period period = Period.between(getDateOfBirth(), LocalDate.now());
        return period.getYears();
    }

    /**
     * Decides what needs to be fed to the animal.
     * @return the feed that needs to be fed to the animal.
     */
    public abstract String feeding();

    /**
     * Get the type of animal, eg; if its cow or sheep.
     * @return AnimalType enum holding which type of animal it is.
     */
    public abstract AnimalType getType();

    /**
     * Prints details of the animal.
     * @param withTreatments to print details with treatments or without treatments.
     */
    public void printDetails(boolean withTreatments)
    {
    	for(String printLine : getPrintString(withTreatments))
    	{
    		System.out.println(printLine);
    	}
     }
    
    public ArrayList<String> getPrintString(boolean withTreatments)
    {
    	ArrayList<String> printStrings = new ArrayList<String>();
    	
    	printStrings.add(getType().toString() + "'s tag number: " + getTagNo() );
    	printStrings.add(getType().toString() + "'s gender: " + getGender());
    	printStrings.add(getType().toString() + "'s date of birth: " + getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    	printStrings.add(getType().toString() + "'s age: " + getAge());
    	printStrings.add(isPurchased() ? getType().toString() + " is purchased" : getType().toString() + " is farm-raised");
    	
    	printStrings.addAll(printMilkingRecordsStrings());
        if(withTreatments)
        	printStrings.addAll(getPrintTreatmentsString());
        
        return printStrings;
     }

    /**
     * Prints all the milking records of the animal.
     */
    public ArrayList<String> printMilkingRecordsStrings()
    {
    	ArrayList<String> printStrings = new ArrayList<String>();
    	
    	printStrings.add("Milking Record:");
        if (getMilkingMap().size() == 0)
        	printStrings.add("None");
        else
            for (LocalDate date : getMilkingMap().keySet())
            	printStrings.add(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " = " + getMilking(date));
        
        return printStrings;
    }

    /**
     * Prints all treatments received by the animal.
     */
    public void printTreatments()
    {
        System.out.println("Treatments Received:");
        if (getTreatmentsReceived().size() == 0)
            System.out.println("None");
        else
            for (Treatment treatment : getTreatmentsReceived())
                treatment.printDetails();
    }
    
    public  ArrayList<String> getPrintTreatmentsString()
    {
    	ArrayList<String> printStrings = new ArrayList<String>();
    	
    	printStrings.add("Treatments Received:");
        if (getTreatmentsReceived().size() == 0)
        	printStrings.add("None");
        else
            for (Treatment treatment : getTreatmentsReceived())
            	printStrings.addAll(treatment.printDetailsString());
        
        return printStrings;
    }

    /**
     * Get the tag number of the animal.
     * @return the tag number of the animal.
     */
    public int getTagNo() {
        return tagNo;
    }

    /**
     * Set the tag number of the animal with the tag number provided.
     * @param tagNo tag number of the animal.
     */
    public void setTagNo(int tagNo) {
        this.tagNo = tagNo;
    }

    /**
     * Get the gender of the animal.
     * @return the gender of the animal as "male" or "female".
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the gender of the animal.
     * @param gender gender of the animal.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Get the date of birth of the animal.
     * @return the date of birth of the animal.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Set the date of birth of the animal with the date provided.
     * @param dateOfBirth date of birth of the animal.
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Get the boolean which represents if the animal is purchased or farm-raised.
     * @return true if animal is purchased and false if the animal is farm raised.
     */
    public boolean isPurchased() {
        return isPurchased;
    }

    /**
     * Set if the animal is purchased or farm raised, true for purchased, false for farm raised.
     * @param isPurchased boolean which represents if the animal is purchased (true) or farm raised (false).
     */
    public void setPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
    }

    /**
     * Get the ArrayList of treatments received by the animal.
     * @return the ArrayList of Treatment objects, which are the treatments the animal recieved.
     */
    public ArrayList<Treatment> getTreatmentsReceived() {
        return treatmentsReceived;
    }

    /**
     * Add a single treatment to the ArrayList of treatments received by the animal.
     * @param treatment Treatment object of the treatment received by the animal.
     */
    public void addTreatment(Treatment treatment) {
        getTreatmentsReceived().add(treatment);
    }

    /**
     * Set the ArrayList of Treatment objects to the ArrayList provided.
     * @param treatmentsReceived ArrayList of the treatments received by the animal.
     */
    public void setTreatmentsReceived(ArrayList<Treatment> treatmentsReceived) {
        this.treatmentsReceived = treatmentsReceived;
    }

    /**
     * Get the Hashmap of all the milking record of the animal.
     * @return the HashMap which holds the record of all the milking amount against date.
     */
    public HashMap<LocalDate, Double> getMilkingMap() {
        return milking;
    }

    /**
     * Get the milking measurement on a specific date.
     * @param date Date of which the milking measurement is required.
     * @return the milking amount on that date as a double.
     */
    public double getMilking(LocalDate date) {
        return milking.get(date);
    }

    /**
     * Add a single milking measurement for a specific date
     * @param date Date on which the measurement took place
     * @param milkingAmount the amount of milk that the animal gave, as a double
     */
    public void addMilking(LocalDate date, double milkingAmount) {
        getMilkingMap().put(date, milkingAmount);
    }

    /**
     * Set the HashMap of the milking measurements to the HashMap provided.
     * @param milking HashMap of all the milking measurement record.
     */
    public void setMilkingMap(HashMap<LocalDate, Double> milking) {
        this.milking = milking;
    }

    
	
	public ArrayList<String> getColumnNames()
	{
		ArrayList<String> columns = new ArrayList<String>();
		columns.add("Tag Number");
		columns.add("Gender");
		columns.add("Date of Birth");
		columns.add("Is Purchased");
		columns.add("Feed");
		
		return columns;		
	}

	
	public ArrayList<Object> getData() 
	{
		ArrayList<Object> data = new ArrayList<Object>();
		data.add(getTagNo());
		data.add(getGender());
		data.add(getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		data.add(isPurchased());
		data.add(feeding());
		
		return data;
	}
	
	
	public static ArrayList<OperationInputPanel> getOperationInputPanels()
	{
		ArrayList<OperationInputPanel> inputs = new ArrayList<OperationInputPanel>();
		
		inputs.add(new OperationInputPanel("Tag Number", InputType.INTEGER));
		inputs.add(new OperationInputPanel("Gender", InputType.STRING));
		inputs.add(new OperationInputPanel("Date of Birth (dd/mm/yyyy)", InputType.STRING));
		inputs.add(new OperationInputPanel("Is Purchased", InputType.BOOLEAN));
		
		
		return inputs;
	}

    public String toString()
    {
        String toReturn = "";

        for(String str : getPrintString(true))
            toReturn = toReturn + "\n" + str;

        return toReturn;
    }


}

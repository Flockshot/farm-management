package me.somaan.farmapp.treatments;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import me.somaan.farmapp.gui.FarmTable;

 /**
 * Medication class to represent the medications given for a treatment.
 * @author Muhammad Somaan
 * @version 1.0
 */
public class Medication implements FarmTable, Serializable {

    /**
     * Details of the medication.
     */
    private String details;
    /**
     * Duration of the medication.
     */
    private int duration;
    /**
     * Starting date of the medication.
     */
    private LocalDate startDate;
    /**
     * Dosage of the medication.
     */
    private double dosage;
    /**
     * Additional notes for the medications.
     */
    private String notes;

    /**
     * Constructor to create Medication object without any notes.
     * @param details details of the medication.
     * @param duration duration of the medication.
     * @param startDate starting date of the medication.
     * @param dosage dosage of the medication.
     */
    public Medication(String details, int duration, LocalDate startDate, double dosage) {
        this(details, duration, startDate, dosage, "");
    }

    /**
     * Constructor to create Medication object with notes.
     * @param details details of the medication.
     * @param duration duration of the medication.
     * @param startDate starting date of the medication.
     * @param dosage dosage of the medication.
     * @param notes additional notes for the medications.
     */
    public Medication(String details, int duration, LocalDate startDate, double dosage, String notes) {
        setDetails(details);
        setDuration(duration);
        setStartDate(startDate);
        setDosage(dosage);
        setNotes(notes);
    }

    /**
     * Get the details of the medication.
     * @return the details of the medication.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Set the details of the medication.
     * @param details details of the medication.
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Get the duration of the medication.
     * @return the duration of the medication.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Set the duration of the medication
     * @param duration duration of the medication.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Get the starting date of the medication.
     * @return the starting date of the medication.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Set the starting date of the medication.
     * @param startDate starting date of the medication.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Get the dosage of the medication.
     * @return the dosage of the medication.
     */
    public double getDosage() {
        return dosage;
    }

    /**
     * Set the dosage of the medication.
     * @param dosage dosage of the medication.
     */
    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    /**
     * Get the notes for the medication.
     * @return the notes for the medication.
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Set the notes for the medication.
     * @param notes notes for the medication.
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Prints all the details of the medication.
     */
    public void printDetails() {
        System.out.println("Medication details: " + getDetails());
        System.out.println("Medication duration: " + getDuration());
        System.out.println("Medication start date: " + getStartDate());
        System.out.println("Medication dosage: " + getDosage());
        System.out.println("Medication notes: " + getNotes());
        System.out.println();
    }

	@Override
	public ArrayList<String> getColumnNames()
	{
		ArrayList<String> columns = new ArrayList<String>();
		
		columns.add("Details");
		columns.add("Duration");
		columns.add("Start Datae");
		columns.add("Dosage");
		columns.add("Notes");
		
		return columns;
	}
	@Override
	public ArrayList<Object> getData()
	{
		ArrayList<Object> data = new ArrayList<Object>();
		
		data.add(getDetails());
		data.add(getDuration());
		data.add(getStartDate());
		data.add(getDosage());
		data.add(getNotes());		
		
		
		return data;
	}

	public Collection<? extends String> printDetailsString() {
		
		ArrayList<String> printStrings = new ArrayList<String>();
		
		printStrings.add("Medication details: " + getDetails());
		printStrings.add("Medication duration: " + getDuration());
		printStrings.add("Medication start date: " + getStartDate());
		printStrings.add("Medication dosage: " + getDosage());
		printStrings.add("Medication notes: " + getNotes());
		printStrings.add("");
        
        return printStrings;
	}
}

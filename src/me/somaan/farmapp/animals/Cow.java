package me.somaan.farmapp.animals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import me.somaan.farmapp.gui.InputType;
import me.somaan.farmapp.gui.OperationInputPanel;
import me.somaan.farmapp.treatments.Treatment;


/**
 * Cow class to create cow objects that are present on the farm.
 * @author Muhammad Somaan
 * @version 2.0
 */
public class Cow extends Animal {

    /**
     * Weight of the cow.
     */
    private double weight;

    /**
     * Constructor to create a cow object.
     * @param tagNo tag number of the cow.
     * @param gender gender of the cow.
     * @param dateOfBirth date of birth of the cow.
     * @param isPurchased if the cow was purchased or farm raised.
     * @param weight weight of the cow.
     */
    public Cow(int tagNo, String gender, LocalDate dateOfBirth, boolean isPurchased, double weight) {
        this(tagNo, gender, dateOfBirth, isPurchased, weight, new ArrayList<Treatment>(), new HashMap<LocalDate, Double>());
    }

    /**
     * Constructor to create a cow object with a single treatment.
     * @param tagNo tag number of the cow.
     * @param gender gender of the cow.
     * @param dateOfBirth date of birth of the cow.
     * @param isPurchased if the animal was purchased or farm cow.
     * @param weight weight of the cow.
     * @param treatment treatment received by the cow.
     */
    public Cow(int tagNo, String gender, LocalDate dateOfBirth, boolean isPurchased, double weight, Treatment treatment) {
        this(tagNo, gender, dateOfBirth, isPurchased, weight);
        addTreatment(treatment);
    }

    /**
     * Constructor to create a cow object with multiple treatments and empty milking record.
     * @param tagNo tag number of the cow.
     * @param gender gender of the cow.
     * @param dateOfBirth date of birth of the cow.
     * @param isPurchased if the animal was purchased or farm cow.
     * @param weight weight of the cow.
     * @param treatmentsReceived List of treatments received by the cow.
     */
    public Cow(int tagNo, String gender, LocalDate dateOfBirth, boolean isPurchased, double weight, ArrayList<Treatment> treatmentsReceived) {
        this(tagNo, gender, dateOfBirth, isPurchased, weight, treatmentsReceived, new HashMap<LocalDate, Double>());
    }

    /**
     * Constructor to create a cow object with multiple treatments.
     * @param tagNo tag number of the cow.
     * @param gender gender of the cow.
     * @param dateOfBirth date of birth of the cow.
     * @param isPurchased if the animal was purchased or farm cow.
     * @param weight weight of the cow.
     * @param treatmentsReceived List of treatments received by the cow.
     * @param milking Record of all the milking measurements of the cow.
     */
    public Cow(int tagNo, String gender, LocalDate dateOfBirth, boolean isPurchased, double weight, ArrayList<Treatment> treatmentsReceived, HashMap<LocalDate, Double> milking) {
        super(tagNo, gender, dateOfBirth, isPurchased, treatmentsReceived, milking);
        setWeight(weight);
    }

    /**
     * Decides what needs to be fed to the cow.
     * @return the feed that needs to be fed to the cow.
     */
    @Override
    public String feeding()
    {
        String feed = "";
        if(getAge()<3)
            feed = "Grass";
        else if(getAge()>=5 && getWeight()<500)
            feed = "Total mixed ration (TMR)";
        else if(getAge()>=10)
            feed = "Grains and Oilseed";
        else
            feed = "Grass and Grains";
        return feed;
    }

    /**
     * Get the type of animal (Cow).
     * @return AnimalType as cow.
     */
    @Override
    public AnimalType getType() {
        return AnimalType.COW;
    }

    /**
     * Prints details of the cow.
     * @param withTreatments to print details with treatments or without treatments.
     */
    /*
    @Override
    public void printDetails(boolean withTreatments)
    {
        super.printDetails(false);
        System.out.println("Cow's weight: " + getWeight());

        if(withTreatments)
            printTreatments();

        System.out.println("");
    }
    */
    
    public ArrayList<String> getPrintString(boolean withTreatments)
    {
    	ArrayList<String> printStrings = super.getPrintString(false);
    	
    	printStrings.add("Cow's weight: " + getWeight());
    	
    	if(withTreatments)
    		printStrings.addAll(getPrintTreatmentsString());
    	
    	printStrings.add("");
    	
        return printStrings;
     }


    /**
     * Get the weight of the cow.
     * @return the weight of the cow
     */
    public double getWeight() {
        return weight;
    }
    /**
     * Set the weight of the cow with the weight provided.
     * @param weight weight of the cow.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    
	@Override
	public ArrayList<String> getColumnNames()
	{
		ArrayList<String> columns = super.getColumnNames();
		columns.add("Weight");
		
		return columns;		
	}

	@Override
	public ArrayList<Object> getData() 
	{
		ArrayList<Object> data = super.getData();
		data.add(getWeight());
		
		return data;
	}
	
	

	public static ArrayList<OperationInputPanel> getOperationInputPanels()
	{
		ArrayList<OperationInputPanel> inputs = Animal.getOperationInputPanels();
		
		inputs.add(new OperationInputPanel("Weight", InputType.DOUBLE));
		
		
		return inputs;
	}
}

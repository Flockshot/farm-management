package me.somaan.farmapp.animals;

import me.somaan.farmapp.treatments.Treatment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Cow class to create sheep objects that are present on the farm.
 * @author Muhammad Somaan
 * @version 1.0
 */
public class Sheep extends Animal {

    /**
     * Constructor to create a sheep object.
     * @param tagNo tag number of the sheep.
     * @param gender gender of the sheep.
     * @param dateOfBirth date of birth of the sheep.
     * @param isPurchased if the sheep was purchased or farm raised.
     */
    public Sheep(int tagNo, String gender, LocalDate dateOfBirth, boolean isPurchased) {
        super(tagNo, gender, dateOfBirth, isPurchased);
    }

    /**
     * Constructor to create an sheep object with a single treatment.
     * @param tagNo tag number of the sheep.
     * @param gender gender of the sheep.
     * @param dateOfBirth date of birth of the sheep.
     * @param isPurchased if the sheep was purchased or farm raised.
     * @param treatment treatment received by the sheep.
     */
    public Sheep(int tagNo, String gender, LocalDate dateOfBirth, boolean isPurchased, Treatment treatment) {
        super(tagNo, gender, dateOfBirth, isPurchased, treatment);
    }

    /**
     * Constructor to create a sheep object with multiple treatments and empty milking record.
     * @param tagNo tag number of the sheep.
     * @param gender gender of the sheep.
     * @param dateOfBirth date of birth of the sheep.
     * @param isPurchased if the sheep was purchased or farm raised.
     * @param treatmentsReceived List of treatments received by the sheep.
     */
    public Sheep(int tagNo, String gender, LocalDate dateOfBirth, boolean isPurchased, ArrayList<Treatment> treatmentsReceived) {
        super(tagNo, gender, dateOfBirth, isPurchased, treatmentsReceived);
    }

    /**
     * Constructor to create a sheep object with multiple treatments.
     * @param tagNo tag number of the sheep.
     * @param gender gender of the sheep.
     * @param dateOfBirth date of birth of the sheep.
     * @param isPurchased if the sheep was purchased or farm raised.
     * @param treatmentsReceived List of treatments received by the sheep.
     * @param milking Record of all the milking measurements of the sheep.
     */
    public Sheep(int tagNo, String gender, LocalDate dateOfBirth, boolean isPurchased, ArrayList<Treatment> treatmentsReceived, HashMap<LocalDate, Double> milking) {
        super(tagNo, gender, dateOfBirth, isPurchased, treatmentsReceived, milking);
    }



    /**
     * Decides what needs to be fed to the sheep.
     * @return the feed that needs to be fed to the sheep.
     */
    @Override
    public String feeding()
    {
        String feed = "";
        if((getAge()<5 && getGender().equalsIgnoreCase("male")) || (getAge()<8 && getGender().equalsIgnoreCase("female")))
            feed = "Grass";
        else
            feed = "Total mixed ration (TMR)";
        return feed;
    }

    /**
     * Get the type of animal (Sheep).
     * @return AnimalType as sheep.
     */
    @Override
    public AnimalType getType() {
        return AnimalType.SHEEP;
    }

    /**
     * Prints details of the animal.
     * @param withTreatments to print details with treatments or without treatments.
     */
    /*
    @Override
    public void printDetails(boolean withTreatments)
    {
        super.printDetails(withTreatments);

        System.out.println("");
    }
    */
    
    


}

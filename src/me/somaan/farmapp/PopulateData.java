package me.somaan.farmapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import me.somaan.farmapp.animals.Cow;
import me.somaan.farmapp.animals.Sheep;
import me.somaan.farmapp.employees.FarmWorker;
import me.somaan.farmapp.employees.Veterinary;
import me.somaan.farmapp.treatments.CleaningTreatment;
import me.somaan.farmapp.treatments.HealthTreatment;
import me.somaan.farmapp.treatments.Medication;

/**
 * PopulateData class to populate data such as Cow Veterinary Treatments Medications at the start of the program.
 * @author Muhammad Somaan
 * @version 1.0
 */
public class PopulateData
{
    /**
     * Creates 3 Veterinary objects and Cow objects with Treatment and adds to the FarmApp vets ArrayList.
     */
    public static void populateData()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Veterinary vet1 = new Veterinary(1, "male", LocalDate.parse("01/01/1998", formatter), 2);
        Veterinary vet2 = new Veterinary(2, "female", LocalDate.parse("11/02/1997", formatter), 2, true, LocalDate.parse("11/02/2020", formatter));

        FarmWorker farm1 = new FarmWorker(3, "male", LocalDate.parse("21/03/1996", formatter));
        FarmWorker farm2 = new FarmWorker(4, "female", LocalDate.parse("02/07/1999", formatter), 2, "Gola-Farm");

        Medication medication1 = new Medication("Panadol", 2, LocalDate.now(), 2.0, "Take daily.");
        Medication medication2 = new Medication("Viagra", 1, LocalDate.now(), 1.0, "Single dose only.");
        Medication medication3 = new Medication("Ibuprofen", 7, LocalDate.now(), 1.0, "Take daily.");
        Medication medication4 = new Medication("Enfexia", 7, LocalDate.now(), 2.0, "Take daily.");

        HealthTreatment treatment1 = new HealthTreatment(vet1, LocalDate.now(), "Fever medication", medication1, false);
        HealthTreatment treatment2 = new HealthTreatment(vet2, LocalDate.now(), "ED medication", medication2, false);
        HealthTreatment treatment3 = new HealthTreatment(vet1, LocalDate.now(), "Pain medication", medication3, false);
        HealthTreatment treatment4 = new HealthTreatment(vet2, LocalDate.now(), "Antibiotic medication", medication4, false);

        CleaningTreatment cleaningTreatment1 = new CleaningTreatment(farm1, "Full Bath", "Water, Shampoo");
        CleaningTreatment cleaningTreatment2 = new CleaningTreatment(farm2, "Partial Bath", "Water");



        Cow cow1 = new Cow(1, "male", LocalDate.parse("01/01/2015", formatter), true, 100, treatment1);
        cow1.addTreatment(cleaningTreatment1);
        Cow cow2 = new Cow(2, "female", LocalDate.parse("11/02/2016", formatter), false, 200, treatment2);
        cow2.addTreatment(cleaningTreatment2);

        Sheep sheep1 = new Sheep(3, "male", LocalDate.parse("21/03/2017", formatter), false, treatment3);
        sheep1.addTreatment(cleaningTreatment1);
        Sheep sheep2 = new Sheep(4, "female", LocalDate.parse("21/03/2017", formatter), false, treatment4);
        sheep2.addTreatment(cleaningTreatment2);

        FarmApp.addAnimal(cow1);
        FarmApp.addAnimal(cow2);
        FarmApp.addAnimal(sheep1);
        FarmApp.addAnimal(sheep2);

        FarmApp.addEmployee(vet1);
        FarmApp.addEmployee(vet2);
        FarmApp.addEmployee(farm1);
        FarmApp.addEmployee(farm2);

    }
}

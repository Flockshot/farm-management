package me.somaan.farmapp;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import me.somaan.farmapp.animals.Animal;
import me.somaan.farmapp.animals.AnimalType;
import me.somaan.farmapp.animals.Cow;
import me.somaan.farmapp.animals.Sheep;
import me.somaan.farmapp.database.DataStorage;
import me.somaan.farmapp.employees.Employee;
import me.somaan.farmapp.employees.EmployeeType;
import me.somaan.farmapp.employees.FarmWorker;
import me.somaan.farmapp.employees.Veterinary;
import me.somaan.farmapp.gui.FarmTable;
import me.somaan.farmapp.md5.MD5Handler;
import me.somaan.farmapp.treatments.CleaningTreatment;
import me.somaan.farmapp.treatments.HealthTreatment;
import me.somaan.farmapp.treatments.Medication;
import me.somaan.farmapp.treatments.Treatment;
import me.somaan.farmapp.treatments.TreatmentType;
import me.somaan.farmapp.utils.Type;


/**
 * Main class to handle all app and console interactions
 * @author Muhammad Somaan
 * @version 2.0
 */
public class FarmApp {

    /**
     * An ArrayList of all animals present on the farm.
     */
    public static ArrayList<Animal> animals = new ArrayList<Animal>();
    /**
     * An ArrayList of all employees.
     */
    public static ArrayList<Employee> employees = new ArrayList<Employee>();

    public static DataStorage database;
    
    public static String directory;

    /**
     * Main function which handles all the interaction with user for using the app.
     * @param args runtime arguments
     */
    public static void main(String[] args) throws SQLException, IOException, NoSuchAlgorithmException {

    	directory = System.getProperty("user.dir");

        database = new DataStorage();
        database.readData();


        //PopulateData.populateData();
        
        //saveFiles(directory);
        //loadFiles(directory);

        //database.writeData();


        File animalFile = new File(directory, "Animals.save");
        if(!animalFile.exists())
            animalFile.createNewFile();

        new MD5Handler(true, animalFile);




        
        FarmGUI window = new FarmGUI();
        window.mainPage.getDirectory().setText(directory);
		window.frame.setVisible(true);
		
		
		
		while (true) {
            menu();
            int input = takeMenuInput();

            try {
                switch (input) {
                    case 1:
                        addAnimal();
                        break;
                    case 2:
                        deleteAnimal(takeTagInput());
                        break;
                    case 3:
                        getAnimalDetails(takeTagInput());
                        break;
                    case 4:
                        addEmployee();
                        break;
                    case 5:
                        deleteEmployee(takeEmpIDInput());
                        break;
                    case 6:
                        getEmployeeDetails(takeEmpIDInput());
                        break;
                    case 7:
                        addTreatment(takeEmpIDInput(), takeTagInput());
                        break;
                    case 8:
                        getAnimalTreatment(takeTagInput());
                        break;
                    case 9:
                        getAnimalTreatment(takeTagInput(), takeDateInput("of treatment"));
                        break;
                    case 10:
                        listAnimals();
                        break;
                    case 11:
                        listEmployees();
                        break;
                    case 12:
                        feedingAnimal(takeTagInput());
                        break;
                    case 13:
                        addMilkingMeasurement(takeTagInput(), takeDoubleInput("Enter the milk amount: "));
                        break;
                    case 14:
                        exit();
                }
            } catch (Exception e) {
                println(e.getMessage());
            }
        }
    }

    public static void loadFiles(String directory)
    {
    	File animalFile = new File(directory, "Animals.save");
    	File employeeFile = new File(directory, "Employees.save");
    	
    	try {
    		if(!animalFile.exists())
    			animalFile.createNewFile();
    		if(!employeeFile.exists())
    			employeeFile.createNewFile();
    	} catch (IOException e) {
					// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	ArrayList<Serializable> animalList = readFile(animalFile);    	
    	ArrayList<Serializable> empList = readFile(employeeFile);
    	
    	animals.clear();
    	employees.clear();
    	
    	for(Serializable object : animalList)
    		animals.add((Animal) object);
    	
    	for(Serializable object : empList)
    		employees.add((Employee) object);
		
	}

	public static void saveFiles(String directory)
    {
        try {
            database.writeData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        File animalFile = new File(directory, "Animals.save");
    	File employeeFile = new File(directory, "Employees.save");
    	
    	try {
    		if(!animalFile.exists())
    			animalFile.createNewFile();
    		if(!employeeFile.exists())
    			employeeFile.createNewFile();
    	} catch (IOException e) {
					// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	ArrayList<Serializable> animalList = new ArrayList<Serializable>();
    	
    	for(Animal animal : animals)
    		animalList.add(animal);
    	
    	ArrayList<Serializable> empList = new ArrayList<Serializable>();
    	
    	for(Employee emp : employees)
    		empList.add(emp);
    	
        writeFile(animalFile, animalList);
        writeFile(employeeFile, empList);




        new MD5Handler(false, animalFile);

    }







    private static void writeFile(File file, ArrayList<Serializable> toSave)
	{
		try
        {
			FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileStream);

            for(Serializable saveObject : toSave)
            	out.writeObject(saveObject);

            out.close();
            fileStream.close();
        }         
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }		
	}
	
	private static ArrayList<Serializable> readFile(File file)
	{
		ArrayList<Serializable> loadedObjects = new ArrayList<Serializable>();


		FileInputStream fileInput = null;
		ObjectInputStream in = null;
		
		try {
			fileInput = new FileInputStream(file);
			in = new ObjectInputStream(fileInput);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			return loadedObjects;
		}

		while(true)
		{
			try {

				loadedObjects.add((Serializable) in.readObject());

			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {
				break;
			} catch (Exception e) {
                break;
            }
		}
		


		try {
            if(in!=null)
			    in.close();
            if(fileInput!=null)
			    fileInput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        println("file.getName()");
        
		return loadedObjects;        
	}
	
	

	/**
     * Prints out the menu selection.
     */
    public static void menu() {
        println("\n1. Add a new animal.");
        println("2. Delete an animal.");
        println("3. Print an animal's details.");
        println("4. Add a new employee.");
        println("5. Delete an employee.");
        println("6. Print an employee details.");
        println("7. Add a treatment.");
        println("8. Print an animal's treatments.");
        println("9. Print an animal's treatments on a given date.");
        println("10. List all animal.");
        println("11. List all employee.");
        println("12. Feed the animal.");
        //println("13. Print employee's salary.");
        println("13. Add a milking measurement.");
        println("14. Exit the application.\n");
    }

    //TODO: add uniqueness to tagNo

    /**
     * Takes all necessary inputs from user to create an Animal object (cow or sheep), and then add it to the ArrayList animals.
     */
    public static void addAnimal() {
        boolean hasError;
        int tagNo = 0;
        do{
            hasError = false;
            tagNo = takeIntInput("Enter the animal's tag number: ");

            if(getAnimal(tagNo)!=null)
            {
                println("That tag number is already added, enter a unique tag number.");
                hasError = true;
            }
        }
        while(hasError);

        String gender = takeGenderInput();
        LocalDate dateOfBirth = takeDateInput("of birth");
        boolean isPurchased = takeBooleanInput("Enter true if the animal is purchased, false if farm-raised: ");

        AnimalType type = takeAnimalTypeInput();

        if(type.equals(AnimalType.COW))
        {
            double weight = takeDoubleInput("Enter the weight of the cow: ");
            addAnimal(new Cow(tagNo, gender, dateOfBirth, isPurchased, weight ));
        }
        else if(type.equals(AnimalType.SHEEP))
            addAnimal(new Sheep(tagNo, gender, dateOfBirth, isPurchased));

        println("Successfully added.");
    }

    //TODO: Change for arrayList: Done

    /**
     * Deletes a cow from ArrayList matching the tag number.
     * If incorrect tagNo is provided it prints out an error instead.
     * @param tagNo Tag number of the cow to delete.
     *
     */
    public static void deleteAnimal(int tagNo) {
        for(int i=0; i<animals.size(); i++)
        {
            if(animals.get(i).getTagNo() == tagNo)
            {
                animals.remove(i);
                println("Successfully deleted.");
                return;
            }
        }

        println("Animal with that tag number does not exist.");
    }

    /**
     * Given a tag number of a cow prints the details of that cow.
     * If a cow with that tag number does not exist it displays error.
     * @param tagNo tag number of cow to search the details.
     */
    public static void getAnimalDetails(int tagNo) {
        Animal animal = getAnimal(tagNo);
        if(animal!=null)
            animal.printDetails(false);
        else
            println("Animal with that tag number does not exist.");
    }

    /**
     * Takes all necessary inputs from user to create a Veterinary object, and add it to the ArrayList vets.
     */
    public static void addEmployee()
    {
        int empID = takeIntInput("Enter employee ID: ");
        String gender = takeGenderInput();
        LocalDate dateOfBirth = takeDateInput("of birth");
        EmployeeType type = takeEmployeeTypeInput();

        if(type.equals(EmployeeType.VETERINARY))
        {
            boolean hasBScDegree = takeBooleanInput("Veterinary has Bsc Degree? (true or false): ");
            LocalDate dateOfGraduation = null;
            if(hasBScDegree)
                dateOfGraduation = takeDateInput("of graduation");
            int expertiseLevel = takeIntInput("Enter veterinary expertise level: ");

            employees.add(new Veterinary(empID, gender, dateOfBirth, expertiseLevel, hasBScDegree, dateOfGraduation));
        }
        else if(type.equals(EmployeeType.FARM_WORKER))
        {
            String previousFarmName = takeStringInput("Enter farm-worker previous farm-name: ");
            int workExperience = takeIntInput("Enter farm-worker years of work experience: ");

            employees.add(new FarmWorker(empID, gender, dateOfBirth, workExperience, previousFarmName));
        }

        println("Successfully added.");
    }

    //TODO: adjust for Arraylist

    /**
     * Deletes the vet object having the vet ID number provided from the ArrayList vets.
     * If incorrect vetID is provided it prints out an error instead.
     * @param empID vet ID number of the vet object which is to be deleted from the ArrayList
     */
    public static void deleteEmployee(int empID) {
        for(int i=0; i<employees.size(); i++)
        {
            if(employees.get(i).getEmpID() == empID)
            {
                employees.remove(i);
                println("Successfully deleted.");
                return;
            }
        }

        println("Employee with that ID number does not exist.");
    }

    /**
     * Prints the details of the vet from the ArrayList vets having the vet ID number provided.
     * If incorrect vetID is provided it prints out an error instead.
     * @param empID vet ID number of the vet object whose details are to be printed.
     */
    public static void getEmployeeDetails(int empID) {
        Employee emp = getEmployee(empID);
        if(emp!=null)
            emp.printDetails();
        else
            println("Employee with that ID number does not exist.");
    }

    /**
     * Takes all necessary input from user to create a Treatment object, for the cow having the tag number provided.
     * If incorrect vetID or tagNo is provided it prints out an error instead.
     * @param empID this gets the vet from the ArrayList vets who gives the treatment.
     * @param tagNo tag number of the cow to receive the treatment.
     */
    public static void addTreatment(int empID, int tagNo) {
        Animal animal = getAnimal(tagNo);
        Employee givenBy = getEmployee(empID);
        if(givenBy==null)
        {
            println("Employee with that ID number does not exist.");
            return;
        }
        if(animal==null)
        {
            println("Animal with that tag number does not exist.");
            return;
        }

        LocalDate dateOfTreatment = takeDateInput("of treatment");
        String details = takeStringInput("Enter Treatment details: ");
        TreatmentType type = takeTreatmentTypeInput();

        if(type.equals(TreatmentType.CLEANING_TREATMENT) && givenBy.getType().equals(EmployeeType.FARM_WORKER))
        {
            String materialsUsed = takeStringInput("Enter the materials used for cleaning treatment: ");
            animal.addTreatment(new CleaningTreatment((FarmWorker) givenBy, dateOfTreatment, details, materialsUsed));
        }
        else if(type.equals(TreatmentType.HEALTH_TREATMENT) && givenBy.getType().equals(EmployeeType.VETERINARY))
        {
            boolean isEmergency = takeBooleanInput("Is treatment an emergency(true or false): ");
            ArrayList<Medication> medications = new ArrayList<Medication>();
            do
                medications.add(takeMedicationInput());
            while(takeBooleanInput("Continue adding medications? (true or false): "));

            animal.addTreatment(new HealthTreatment((Veterinary) givenBy, dateOfTreatment, details, medications, isEmergency));
        }
        else
        {
            println(givenBy.getType().toString() + " cannot give a " + type.toString());
            return;
        }

        println("Successfully added.");
    }

    /**
     * Takes all necessary input from user to create a Medication object.
     * @return the Medication object created by taking inputs from user.
     */
    private static Medication takeMedicationInput()
    {
        String details = takeStringInput("Enter medication details: ");
        int duration = takeIntInput("Enter medication duration: ");
        LocalDate startDate = takeDateInput("to start medication");
        double dosage = takeDoubleInput("Enter medication dosage: ");
        String notes = takeStringInput("Enter medication notes: ");

        return new Medication(details, duration, startDate, dosage, notes);
    }

    /**
     * Prints the details of a cow's treatments. If cow with that tag number does not exist, prints out an error.
     * @param tagNo tag number of cow to get treatment of.
     */
    public static void getAnimalTreatment(int tagNo) {
        Animal animal = getAnimal(tagNo);
        if(animal!=null)
            animal.printTreatments();
        else
            println("Animal with that tag number does not exist.");
    }

    /**
     * Prints the details of a cow's treatments on a specific date. If cow with that tag number does not exist, prints out an error.
     * @param tagNo tag number of cow to get treatment of.
     * @param dateOfTreatment date of treatment to search details for.
     */
    public static void getAnimalTreatment(int tagNo, LocalDate dateOfTreatment)
    {
        Animal animal = getAnimal(tagNo);
        if(animal==null)
        {
            println("Animal with that tag number does not exist.");
            return;
        }

        for(Treatment treatment : animal.getTreatmentsReceived())
        {
            if(treatment.getDateOfTreatment().equals(dateOfTreatment))
            {
                treatment.printDetails();
                return;
            }
        }
        println("No treatments received on this date.");
    }

    /**
     * Lists all cow details in ArrayList cows
     */
    public static void listAnimals() {
        for (Animal animal : animals)
            animal.printDetails(false);
    }

    /**
     * Lists all veterinary details in ArrayList vets
     */
    public static void listEmployees() {
        for (Employee emp : employees)
            emp.printDetails();
    }

    /**
     * Print what to feed the animal.
     * @param tagNo tag number of the animal of which feed is to be printed.
     */
    public static void feedingAnimal(int tagNo)
    {
        Animal animal = getAnimal(tagNo);
        if(animal!=null)
            println(animal.feeding()+" should be fed to the animal.");
        else
            println("Animal with that tag number does not exist.");
    }

    /**
     * Gets the salary of the employee.
     * @param empID the employee ID of whose salary to get.
     * @return the salary of the employee
     * @throws Exception in case invalid employee ID is entered.
     */
    double getEmployeeSalary(int empID) throws Exception
    {
        Employee emp = getEmployee(empID);
        if(emp!=null)
            return emp.getSalary();
        else
            throw new Exception("Employee with that ID number does not exist.");
    }

    /**
     * Add a milking measurement for an animal for that day.
     * @param tagNo the tag number of the animal.
     * @param amount the amount of milk the animal produced.
     */
    public static void addMilkingMeasurement(int tagNo, double amount)
    {
        Animal animal = getAnimal(tagNo);
        if(animal!=null)
            if(animal.getMilkingMap().containsKey(LocalDate.now()))
                println("Measurement for today has already been taken.");
            else
            {
                animal.addMilking(LocalDate.now(), amount);
                println("Successfully added.");
            }

        else
            println("Animal with that tag number does not exist.");
    }

    /**
     * Exists the program.
     */
    public static void exit() {
        println("Bye Bye!");
        System.exit(1);
    }


    /**
     * Takes correct input from user for menu selection.
     * @return an int containing a value from 1-12 for menu selection.
     */
    private static int takeMenuInput() {
        int input;

        do
            input = takeIntInput("Enter the menu input (1-14): ");
        while (input < 1 || input > 14);

        println("");
        return input;
    }

    /**
     * Takes input from user for cow tag number, in case of incorrect input throws an exception.
     * @return an int containing a proper cow tag number for which Cow object exists in ArrayList cows.
     * @throws Exception with error message in case where no cow object exists in ArrayList cows with the cow tag number provided.
     */
    private static int takeTagInput() throws Exception {
        int tagNo = takeIntInput("Enter animal's tag number: ");
        if (getAnimal(tagNo) != null)
            return tagNo;
        throw new Exception("Invalid animal tag number.");
    }

    /**
     * Take input from user regarding which Animal type to select.
     * @return the AnimalType the user selects.
     */
    private static AnimalType takeAnimalTypeInput() {
        return AnimalType.values()[takeTypeInput(AnimalType.COW)];
    }

    /**
     * Take input from user regarding which Employee type to select.
     * @return the EmployeeType the user selects.
     */
    private static EmployeeType takeEmployeeTypeInput() {
        return EmployeeType.values()[takeTypeInput(EmployeeType.VETERINARY)];
    }

    /**
     * Take input from user regarding which Treatment type to select.
     * @return the TreatmentType the user selects.
     */
    private static TreatmentType takeTreatmentTypeInput() {
        return TreatmentType.values()[takeTypeInput(TreatmentType.TREATMENT)];
    }

    /**
     * Take the input from user for the enum type (AnimalType or EmployeeType).
     * @param type (AnimalType or EmployeeType).
     * @return the selected number which represents the type.
     */
    private static int takeTypeInput(Type type)
    {
        println("Listing all the types: ");
        ArrayList<String> values = type.getValues();

        for(int i=0; i<values.size(); i++)
            println(i+". "+values.get(i));

        int typeNumber = 0;
        do
            typeNumber = takeIntInput("Enter the type number: ");
        while(!(typeNumber>=0 && typeNumber<values.size()));

        return typeNumber;
    }




    /**
     * Takes input from user for vet ID, in case of incorrect input throws an exception.
     * @return an int containing a proper vet ID for which Veterinary object exists in ArrayList vets.
     * @throws Exception with error message in case where no veterinary object exists in ArrayList vets with the vet ID provided.
     */
    private static int takeEmpIDInput() throws Exception {
        int empID = takeIntInput("Enter employee ID number: ");
        if (getEmployee(empID) != null)
            return empID;
        throw new Exception("Invalid employee ID number.");
    }
    /**
     *  Take an input from user for date, making sure that user only enters the date in correct format.
     * @param dateOf In the input prompt mention to user which date they are inputting.
     * @return a LocalDate containing date in proper format.
     */
    private static LocalDate takeDateInput(String dateOf) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = null;

        Scanner scanner = new Scanner(System.in);
        boolean hasError;

        do {
            hasError = false;
            try {
                print("Enter the date "+dateOf+" (dd/mm/yyyy): ");
                date = LocalDate.parse(scanner.next(), formatter);
            } catch (Exception e) {
                hasError = true;
            }
        }
        while (hasError);
        return date;
    }
    /**
     *  Take an input from user for gender, making sure that user only enters male or female.
     *  @return a String containing male or female.
     */
    private static String takeGenderInput()
    {
        String input;

        do
            input = takeStringInput("Enter gender (male or female): ");
        while (!(input.equalsIgnoreCase("male") || input.equalsIgnoreCase("female")));

        return input;
    }
    /**
     *  Take a full line input from user.
     * @param inputPrompt Print the input prompt to console before taking the input.
     * @return a String object containing the full line input taken from user.
     */
    private static String takeStringInput(String inputPrompt) {
        Scanner scanner = new Scanner(System.in);
        print(inputPrompt);

        return scanner.nextLine();
    }

    /**
     *  Take correct boolean input from user without any error.
     * @param inputPrompt Print the input prompt to console before taking the input.
     * @return a boolean input taken from user.
     */
    private static boolean takeBooleanInput(String inputPrompt) {
        Scanner scanner = new Scanner(System.in);
        boolean input = false;
        boolean hasError;
        do {
            hasError = false;

            print(inputPrompt);
            if (scanner.hasNextBoolean())
                input = scanner.nextBoolean();
            else {
                println("Please enter true or false.");
                scanner.next();
                hasError = true;
            }
        }
        while (hasError);

        return input;
    }

    /**
     *  Take correct int input from user without any error.
     * @param inputPrompt Print the input prompt to console before taking the input.
     * @return an int input taken from user.
     */
    private static int takeIntInput(String inputPrompt) {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        boolean hasError;
        do {
            hasError = false;

            print(inputPrompt);
            if (scanner.hasNextInt())
                input = scanner.nextInt();
            else {
                println("Please enter an int.");
                scanner.next();
                hasError = true;
            }
        }
        while (hasError);


        return input;
    }
    /**
     *  Take correct double input from user without any error.
     * @param inputPrompt Print the input prompt to console before taking the input.
     * @return a double input taken from user.
     */
    private static double takeDoubleInput(String inputPrompt)
    {
        Scanner scanner = new Scanner(System.in);
        double input = 0;
        boolean hasError;
        do {
            hasError = false;

            print(inputPrompt);
            if (scanner.hasNextDouble())
                input = scanner.nextDouble();
            else {
                println("Please enter a double.");
                scanner.next();
                hasError = true;
            }
        }
        while (hasError);

        return input;
    }

    /**
     * Gets the cow object matching the tag number from the ArrayList cows.
     * @param tagNo tag number of cow to get the object of.
     * @return Cow object matching the tag number.
     */
    public static Animal getAnimal(int tagNo) {
        for (Animal animal : animals)
            if (animal.getTagNo() == tagNo)
                return animal;
        return null;
    }

    /**
     * Gets the veterinary object matching the ID number from the ArrayList vets.
     * @param empID ID number of veterinary to get the object of.
     * @return veterinary object matching the ID number.
     */
    public static Employee getEmployee(int empID) {
        for (Employee emp : employees)
            if (emp.getEmpID() == empID)
                return emp;
        return null;
    }

    /**
     * Adds a cow object to the ArrayList cows.
     * @param animalObject Cow object to add to the list.
     */
    public static void addAnimal(Animal animalObject) {
        animals.add(animalObject);
    }

    /**
     * Adds a veterinary object to the ArrayList vets.
     * @param empObject Veterinary object to add to the list.
     */
    public static void addEmployee(Employee empObject) {
        employees.add(empObject);
    }


    /**
     * Print a string to console.
     * @param toPrint String containing what to print to console.
     */
    private static void print(String toPrint) {
        System.out.print(toPrint);
    }
    /**
     * Print line a string to console.
     * @param toPrint String containing what to print to console.
     */
    private static void println(String toPrint) {
        System.out.println(toPrint);
    }

	public static HashMap<AnimalType, ArrayList<FarmTable>> getAnimalMap()
	{
        HashMap<AnimalType, ArrayList<FarmTable>> animalMap = new HashMap<AnimalType, ArrayList<FarmTable>>();
        
        for(Animal animal : animals)
        {
        	ArrayList<FarmTable> animalInMap = animalMap.get(animal.getType());
        	if(animalInMap == null)
        		animalInMap = new ArrayList<FarmTable>();
        	animalInMap.add(animal);
        	animalMap.putIfAbsent(animal.getType(), animalInMap);
        }
        
        return animalMap;
	}
	
	public static HashMap<EmployeeType, ArrayList<FarmTable>> getEmployeeMap()
	{
        HashMap<EmployeeType, ArrayList<FarmTable>> employeeMap = new HashMap<EmployeeType, ArrayList<FarmTable>>();
        
        for(Employee employee : employees)
        {
        	ArrayList<FarmTable> employeeInMap = employeeMap.get(employee.getType());
        	if(employeeInMap == null)
        		employeeInMap = new ArrayList<FarmTable>();
        	employeeInMap.add(employee);
        	employeeMap.putIfAbsent(employee.getType(), employeeInMap);
        }
        
        return employeeMap;
	}
	

}
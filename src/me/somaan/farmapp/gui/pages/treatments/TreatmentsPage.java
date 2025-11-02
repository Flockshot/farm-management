package me.somaan.farmapp.gui.pages.treatments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import me.somaan.farmapp.FarmApp;
import me.somaan.farmapp.animals.Animal;
import me.somaan.farmapp.employees.Employee;
import me.somaan.farmapp.employees.EmployeeType;
import me.somaan.farmapp.employees.FarmWorker;
import me.somaan.farmapp.employees.Veterinary;
import me.somaan.farmapp.gui.InputType;
import me.somaan.farmapp.gui.OperationInputPanel;
import me.somaan.farmapp.gui.pages.OperationsPage;
import me.somaan.farmapp.treatments.CleaningTreatment;
import me.somaan.farmapp.treatments.HealthTreatment;
import me.somaan.farmapp.treatments.Medication;
import me.somaan.farmapp.treatments.Treatment;

public class TreatmentsPage extends JSplitPane {

	/**
	 * Create the panel.
	 */
	
	private JTextArea textArea;
	private OperationsPage inputs;
	
	private JComboBox<String> treatmentType;
	private OperationInputPanel materialsInput;
	//private JButton deleteButton;
	private OperationInputPanel emergencyInput;
	private OperationsPage medication;
	private OperationInputPanel keepAdding;
	private HealthTreatment lastTreatment;
	//private JButton addButton;
	
	
	public TreatmentsPage(OperationsPage inputs)
	{
		removeExtraButtons(inputs);
		
		treatmentType = new JComboBox<String>();
		treatmentType.setModel(new DefaultComboBoxModel<String>(new String[] {"Cleaning", "Health"}));
		
		inputs.add(treatmentType);
		
		treatmentType.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        
		    	if(treatmentType.getSelectedIndex() == 0)
		    	{
		    		setCleaningVisible();
		    	}
		    	else
		    	{
		    		setHealthVisible();
		    	}
		    	
		    	//getLeftComponent().setVisible(false);
		    	//getLeftComponent().setVisible(true);
		    	
		    }			
		});		
		
		
		materialsInput = new OperationInputPanel("Materials Used", InputType.STRING);
		emergencyInput = new OperationInputPanel("Is Emergency", InputType.BOOLEAN);
		inputs.getInputs().add(materialsInput);
		inputs.getInputs().add(emergencyInput);
		
		
		
		ArrayList<OperationInputPanel> medicationInputs = new ArrayList<OperationInputPanel>();
		
		medicationInputs.add(new OperationInputPanel( "Medication Detail", InputType.STRING));
		medicationInputs.add(new OperationInputPanel( "Medication Duration", InputType.INTEGER));
		medicationInputs.add(new OperationInputPanel( "Medication Date(dd/mm/yyyy)", InputType.STRING));
		medicationInputs.add(new OperationInputPanel( "Medication Dosage", InputType.DOUBLE));
		medicationInputs.add(new OperationInputPanel( "Medication Notes", InputType.STRING));
		
		medication = new OperationsPage(medicationInputs);		
		removeExtraButtons(medication);
		
		
		keepAdding = new OperationInputPanel("Keep Adding", InputType.BOOLEAN);
		
				
		inputs.add(materialsInput);
		inputs.add(emergencyInput);
		inputs.add(medication);
		inputs.add(keepAdding);
		inputs.add(inputs.getAddButton());
		
		inputs.getAddButton().addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    	
		    	ArrayList<Object> inputValues = inputs.getInputValues();
		    	Animal animal = FarmApp.getAnimal((int) inputValues.get(0));
		    	Employee emp = FarmApp.getEmployee((int) inputValues.get(1));		    	
	        	String dateInString = (String) inputValues.get(2);
		    	LocalDate date = LocalDate.parse(dateInString, formatter);
		    	String details = (String) inputValues.get(3);
		    	
		    	if(animal==null)
			    {
		    		println("Animal with that tag number does not exist.");			            
			    }
		    	else if(emp == null)
		    	{
		    		println("Employee with that ID does not exist.");
		    	}
		    	else
		    	{
		    		Treatment treatment = null;
		    		
		    		if(treatmentType.getSelectedIndex() == 0)
			    	{
		    			if(emp.getType().equals(EmployeeType.FARM_WORKER))
		    			{
		    				treatment = new CleaningTreatment((FarmWorker) emp, date, details, (String) materialsInput.getInputValue());
		    			}
		    			else
		    			{
		    				println("Only Farm worker can give cleaning treatment.");
		    			}
		    			
		    			
		    			
			    		
			    	}
			    	else
			    	{
			    		if(emp.getType().equals(EmployeeType.VETERINARY))
		    			{
			    			ArrayList<Object> medicationValues = medication.getInputValues();
			    			
			    			String medDetails = (String) medicationValues.get(0);			    			
					    	int duration = (int) medicationValues.get(1);
					    	dateInString = (String) medicationValues.get(2);
					    	LocalDate medDate = LocalDate.parse(dateInString, formatter);
					    	double dosage = (double) medicationValues.get(3);
					    	String notes = (String) medicationValues.get(4);
					    	
			    			
			    			Medication medicationInput = new Medication(medDetails, duration, medDate, dosage, notes);
			    			
			    			if(lastTreatment!=null)
			    			{
			    				lastTreatment.getMedications().add(medicationInput);
			    				
			    				if(!((boolean) keepAdding.getInputValue()))
			    				{
			    					lastTreatment = null;
			    					println("Treatment Added");
			    					inputs.resetAll();
				    				materialsInput.reset();
				    				emergencyInput.reset();
				    				medication.resetAll();
				    				keepAdding.reset();
			    				}
			    					
			    			
			    			}
			    			else
			    			{
			    				if((boolean) keepAdding.getInputValue())
				    			{

				    				lastTreatment = new HealthTreatment((Veterinary) emp, date, details, medicationInput, (boolean) emergencyInput.getInputValue());
				    				animal.addTreatment(lastTreatment);
				    				
				    			}
				    			else
				    			{
				    				treatment = new HealthTreatment((Veterinary) emp, date, details, medicationInput, (boolean) emergencyInput.getInputValue());
				    				lastTreatment = null;
				    			}
			    			}
			    			
			    			
		    			}
		    			else
		    			{
		    				println("Only Vet can give health treatment.");
		    			}
			    	}
		    		
		    		
		    		
		    		
		    		
		    		if(((boolean) keepAdding.getInputValue()))
	    			{
	    				println("Please add another medication and press Add");
	    				medication.resetAll();
	    			}
	    			else
	    			{
	    				if(treatment!=null)
			    		{
			    			animal.addTreatment(treatment);
			    			println("Treatment Added");
		    				
		    				inputs.resetAll();
		    				materialsInput.reset();
		    				emergencyInput.reset();
		    				medication.resetAll();
		    				keepAdding.reset();
			    		}
	    				
	    			}
		    				    		
		    	}		    	
		    }			
		});	
		
		
		//JComboBox<String> comboBox = new JComboBox<String>();
		//treatmentType.setModel(new DefaultComboBoxModel<String>(new String[] {"Cleaning", "Health"}));
		setCleaningVisible();
		
		
		setOrientation(JSplitPane.VERTICAL_SPLIT);
		setName("Treatments");
		
		textArea = new JTextArea(8, 40);
		//textArea.setText("SOmething to write\r\nand then it continues\r\nto print further and further treatments.\r\nContinue\r\nand then\r\nfurthermore\r\non and on\r\nwant to see\r\nif scroll\\\r\nis possible\r\nor if fixed length\r\npossible\r\n");
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		setRightComponent(scrollPane);
		
		//JComboBox comboBox = new JComboBox();
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"Test", "T"}));
		//scrollPane.setRowHeaderView(comboBox);
		

		
		
		
		setInputs(inputs);
		setLeftComponent(inputs);
		
		
		
		
	}
	
	void removeExtraButtons(OperationsPage page)
	{
		page.remove(page.getDeleteInput());
		page.remove(page.getDeleteButton());
		page.remove(page.getPrintInput());
		page.remove(page.getPrintButton());
		page.remove(page.getAddButton());
	}

	protected void setHealthVisible()
	{
		materialsInput.setVisible(false);
		emergencyInput.setVisible(true);
		medication.setVisible(true);
		keepAdding.setVisible(true);
		setDividerLocation(0.5);
		
	}

	protected void setCleaningVisible()
	{
		materialsInput.setVisible(true);
		emergencyInput.setVisible(false);	
		medication.setVisible(false);
		keepAdding.setVisible(false);
		
	}

	public OperationsPage getInputs() {
		return inputs;
	}

	public void setInputs(OperationsPage inputs) {
		this.inputs = inputs;
	}
	
	public OperationsPage getMedication() {
		return medication;
	}

	public void setMedication(OperationsPage medication) {
		this.medication = medication;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public OperationInputPanel getMaterialsInput() {
		return materialsInput;
	}

	public void setMaterialsInput(OperationInputPanel materialsInput) {
		this.materialsInput = materialsInput;
	}

	public OperationInputPanel getEmergencyInput() {
		return emergencyInput;
	}

	public void setEmergencyInput(OperationInputPanel emergencyInput) {
		this.emergencyInput = emergencyInput;
	}

	public JComboBox<String> getTreatmentType() {
		return treatmentType;
	}

	public void setTreatmentType(JComboBox<String> treatmentType) {
		this.treatmentType = treatmentType;
	}

	public OperationInputPanel getKeepAdding() {
		return keepAdding;
	}

	public void setKeepAdding(OperationInputPanel keepAdding) {
		this.keepAdding = keepAdding;
	}

	protected void println(String text)
	{
		print(text + "\n");
	}

	protected void print(String text) {
		textArea.append(text);		
	}

	public HealthTreatment getLastTreatment() {
		return lastTreatment;
	}

	public void setLastTreatment(HealthTreatment lastTreatment) {
		this.lastTreatment = lastTreatment;
	}

}

package me.somaan.farmapp.gui.pages.animals;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;

import me.somaan.farmapp.FarmApp;
import me.somaan.farmapp.animals.Animal;
import me.somaan.farmapp.animals.AnimalType;
import me.somaan.farmapp.gui.FarmObjectPage;
import me.somaan.farmapp.gui.InputType;
import me.somaan.farmapp.gui.OperationInputPanel;
import me.somaan.farmapp.gui.pages.OperationsPage;
import me.somaan.farmapp.gui.pages.SingleListPage;
import me.somaan.farmapp.treatments.Treatment;

public abstract class AnimalPage extends FarmObjectPage
{
	protected OperationInputPanel milkingTag;
	protected OperationInputPanel milkingInput;
	protected OperationInputPanel treatmentTag;
	protected OperationInputPanel treatmentDateInput;
	protected JButton addMilkingButton;
	protected JButton printTreatmentsButton;
	

	public AnimalPage(SingleListPage dataList, OperationsPage inputs) {
		super(dataList, inputs);
		
		milkingTag = (new OperationInputPanel("Milking Tag", InputType.INTEGER));
		milkingInput = (new OperationInputPanel("Milking Amount", InputType.DOUBLE));
		
				
		this.inputs.add(milkingTag);
		this.inputs.add(milkingInput);		
		addMilkingButton = new JButton("Add Milk");
		this.inputs.add(addMilkingButton);
		
		treatmentTag = new OperationInputPanel("Treatment Tag", InputType.INTEGER);	
		treatmentDateInput = new OperationInputPanel("Treatment Date (Leave Empty for all treatments)", InputType.STRING);		
		printTreatmentsButton = new JButton("Print Treatments");
		
		this.inputs.add(treatmentTag);
		this.inputs.add(treatmentDateInput);
		this.inputs.add(printTreatmentsButton);
		
		printTreatmentsButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        
		    	if(treatmentTag.getInputValue() instanceof Integer)
				{
		    		
		    		
					//textArea.append("Milking Added");
					
			        Animal animal = FarmApp.getAnimal((int) treatmentTag.getInputValue());
			        if(animal==null)
			        {
			            println("Animal with that tag number does not exist.");			            
			        }
			        else
			        {
			        	boolean printed = false;
			        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			        	String dateInString = (String) treatmentDateInput.getInputValue();
			        	
			        	if(dateInString.isBlank())
			        	{
			        		for(String text : animal.getPrintString(true))
			        			println(text);
			        	}
			        	else
			        	{
			        		LocalDate dateOfTreatment = LocalDate.parse(dateInString, formatter);
				        	
				        	for(Treatment treatment : animal.getTreatmentsReceived())
					        {
					            if(treatment.getDateOfTreatment().equals(dateOfTreatment))
					            {
					                for(String text : treatment.printDetailsString())
					        			println(text);
					                printed = true;
					                break;
					            }
					        }
				        	if(!printed)
				        		println("No treatments received on this date.");
			        	}     	
			        	
			        }
			        
				}
		    	treatmentTag.reset();
		    	treatmentDateInput.reset();
		    	
		    }
		});		
		
		
		
		addMilkingButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        
		    	if(milkingTag.getInputValue() instanceof Integer)
				{
					FarmApp.getAnimal((int) milkingTag.getInputValue()).addMilking(LocalDate.now(), (double) milkingInput.getInputValue());
					println("Milking Added");
				}
		    	
		    	milkingTag.reset();
		    	milkingInput.reset();
		    }
		});		
		
		
	}
	
	

	@Override
	protected void printObject(Object inputValue) {
		if(inputValue instanceof Integer)
		{
			//TODO print
			Animal animal = FarmApp.getAnimal((int) inputValue);
			for(String printString : animal.getPrintString(false))
				println(printString);
				
		}
	}

	@Override
	protected void deleteObject(Object inputValue) {
		
		if(inputValue instanceof Integer)
		{
			FarmApp.deleteAnimal((int) inputValue);
			println("Animal deleted.");
			
			updateTable();
		}
		
	}
	
	void updateTable()
	{
		dataList.updateTable(FarmApp.getAnimalMap().get(getType()));
	}
	
	public abstract AnimalType getType();



}

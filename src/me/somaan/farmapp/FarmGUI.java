package me.somaan.farmapp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import me.somaan.farmapp.animals.AnimalType;
import me.somaan.farmapp.animals.Cow;
import me.somaan.farmapp.animals.Sheep;
import me.somaan.farmapp.employees.EmployeeType;
import me.somaan.farmapp.employees.FarmWorker;
import me.somaan.farmapp.employees.Veterinary;
import me.somaan.farmapp.gui.FarmObjectPage;
import me.somaan.farmapp.gui.FarmTable;
import me.somaan.farmapp.gui.FarmTableModel;
import me.somaan.farmapp.gui.pages.MainPage;
import me.somaan.farmapp.gui.pages.OperationsPage;
import me.somaan.farmapp.gui.pages.SingleListPage;
import me.somaan.farmapp.gui.pages.TabbedListPage;
import me.somaan.farmapp.gui.pages.animals.CowPage;
import me.somaan.farmapp.gui.pages.animals.SheepPage;
import me.somaan.farmapp.gui.pages.employees.FarmWorkerPage;
import me.somaan.farmapp.gui.pages.employees.VeterinaryPage;
import me.somaan.farmapp.gui.pages.treatments.TreatmentsPage;
import me.somaan.farmapp.treatments.Treatment;

/**
 * Licenses: 
 * @author somaa
 *
 */


public class FarmGUI {

	public JFrame frame;
	public JTabbedPane tabbedPane;
	public MainPage mainPage;
	public TabbedListPage animalPage;
	public TabbedListPage employeePage;



	/**
	 * Create the application.
	 */
	public FarmGUI()
	{
		initialize();
		
		setTables(false);
		
		TreatmentsPage treatmentPage = new TreatmentsPage(new OperationsPage(Treatment.getOperationInputPanels()));
		
		
		tabbedPane.add(animalPage.getName(), animalPage);
		tabbedPane.add(employeePage.getName(), employeePage);
		tabbedPane.add(treatmentPage.getName(), treatmentPage);
		//window.tabbedPane.add(new SingleListPage(new FarmTableModel((FarmTable) animals.get(0)), "test"));
		
		
		
	}



	public void setTables(boolean refresh)
	{
        ArrayList<FarmObjectPage> animalOperationPages = new ArrayList<FarmObjectPage>();        
        HashMap<AnimalType, ArrayList<FarmTable>> animalMap = FarmApp.getAnimalMap();
        
        ArrayList<FarmObjectPage> employeeOperationPages = new ArrayList<FarmObjectPage>();        
        HashMap<EmployeeType, ArrayList<FarmTable>> employeelMap = FarmApp.getEmployeeMap();
        
        
        for(AnimalType type : animalMap.keySet())
        {
        	if(type.equals(AnimalType.COW))
        		animalOperationPages.add(new CowPage(new SingleListPage(new FarmTableModel(animalMap.get(type)), type.toString()), new OperationsPage (Cow.getOperationInputPanels())));
        	else if(type.equals(AnimalType.SHEEP))
        		animalOperationPages.add(new SheepPage(new SingleListPage(new FarmTableModel(animalMap.get(type)), type.toString()), new OperationsPage (Sheep.getOperationInputPanels())));
        }



        for(EmployeeType type : employeelMap.keySet())
        {
        	if(type.equals(EmployeeType.FARM_WORKER))
        		employeeOperationPages.add(new FarmWorkerPage(new SingleListPage(new FarmTableModel(employeelMap.get(type)), type.toString()), new OperationsPage (FarmWorker.getOperationInputPanels())));
        	else if(type.equals(EmployeeType.VETERINARY))
        		employeeOperationPages.add(new VeterinaryPage(new SingleListPage(new FarmTableModel(employeelMap.get(type)), type.toString()), new OperationsPage (Veterinary.getOperationInputPanels())));
        }


		if(animalMap.isEmpty())
		{
			animalOperationPages.add(new CowPage(new SingleListPage(new FarmTableModel(new ArrayList<FarmTable>()), AnimalType.COW.toString()), new OperationsPage (Cow.getOperationInputPanels())));
			animalOperationPages.add(new SheepPage(new SingleListPage(new FarmTableModel(new ArrayList<FarmTable>()), AnimalType.SHEEP.toString()), new OperationsPage (Sheep.getOperationInputPanels())));
		}
		if(employeelMap.isEmpty())
		{
			employeeOperationPages.add(new FarmWorkerPage(new SingleListPage(new FarmTableModel(new ArrayList<FarmTable>()), EmployeeType.FARM_WORKER.toString()), new OperationsPage (FarmWorker.getOperationInputPanels())));
			employeeOperationPages.add(new VeterinaryPage(new SingleListPage(new FarmTableModel(new ArrayList<FarmTable>()), EmployeeType.VETERINARY.toString()), new OperationsPage (Veterinary.getOperationInputPanels())));
		}
        	
        
		//SingleListPage page = new SingleListPage(new FarmTableModel(tableData), animals.get(0).getType().toString());
		
		
		animalPage = new TabbedListPage(animalOperationPages, "Animals");
		employeePage = new TabbedListPage(employeeOperationPages, "Employees");
		
		if(refresh)
		{
			tabbedPane.setComponentAt(1, animalPage);
			tabbedPane.setComponentAt(2, employeePage);
		}
		
		
	}



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		    	if(!mainPage.getDirectory().getText().isBlank())
					FarmApp.saveFiles(mainPage.getDirectory().getText());
		    }
		});
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFileMenu = new JMenu("File");
		menuBar.add(mnFileMenu);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setToolTipText("Save all files");
		mnFileMenu.add(mntmSave);
		
		mntmSave.addActionListener(new SaveListener());
		
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setToolTipText("Exit the application without saving");
		mnFileMenu.add(mntmExit);
		
		mntmExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
	        }
				
		});
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		
		
		JMenuItem mntmAnout = new JMenuItem("About");
		mntmAnout.setToolTipText("Information about author");
		mnHelp.add(mntmAnout);
		
		mntmAnout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JDialog dialog = new JDialog(frame, "About");				 
	            JLabel label = new JLabel("Created by Somaan");
	 
	            dialog.add(label);
	            dialog.setSize(256, 256);

	            dialog.setVisible(true);
	        }
				
		});
		
		JMenuItem mntmLicenses = new JMenuItem("Licenses");
		mntmLicenses.setToolTipText("Information regarding licenses");
		mnHelp.add(mntmLicenses);
		
		mntmLicenses.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JDialog dialog = new JDialog(frame, "Licences");				 
	            JLabel label = new JLabel("Free to use");
	 
	            dialog.add(label);
	            dialog.setSize(256, 256);

	            dialog.setVisible(true);
	        }
				
		});
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		mainPage = new MainPage();
		tabbedPane.addTab("Main", null, mainPage, "Main Page");
		
		//Reload Data
		mainPage.load.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!mainPage.getDirectory().getText().isBlank())
					FarmApp.loadFiles(mainPage.getDirectory().getText());
				setTables(true);
	        }
				
		});
		


	}
	
	class SaveListener implements ActionListener {		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(!mainPage.getDirectory().getText().isBlank())
				FarmApp.saveFiles(mainPage.getDirectory().getText());
	    }
	}

}




package me.somaan.farmapp.gui.pages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import me.somaan.farmapp.FarmApp;

public class MainPage extends JPanel {
	private JTextField directory;
	public JButton load;

	/**
	 * Create the panel.
	 */
	public MainPage() {
		
		JLabel fileLabel = new JLabel("File Directory");
		add(fileLabel);
		
		directory = new JTextField();
		directory.setEditable(false);
		add(directory);
		directory.setColumns(10);
		
		
		JButton select = new JButton("Select");
		add(select);
		
		select.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
	            JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	 
	            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	 
	            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
	            {
	            	directory.setText(chooser.getSelectedFile().getAbsolutePath());
	            	FarmApp.directory = directory.getText();
	            }
	        }
				
		});
		
		
		load = new JButton("Load");
		add(load);
		

		JButton save = new JButton("Save");
		add(save);
		
		save.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!directory.getText().isBlank())
					FarmApp.saveFiles(directory.getText());
	        }
				
		});
		

	}

	public JTextField getDirectory() {
		return directory;
	}

	public void setDirectory(JTextField directory) {
		this.directory = directory;
	}

}

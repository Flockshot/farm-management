package me.somaan.farmapp.gui.pages;

import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;

import me.somaan.farmapp.gui.FarmObjectPage;

@SuppressWarnings("serial")
public class TabbedListPage extends JScrollPane
{
	private ArrayList<FarmObjectPage> pages;
	private JTabbedPane tabbedPane;

	/**
	 * Create the panel.
	 */
	public TabbedListPage(ArrayList<FarmObjectPage> pages, String name)
	{
		setPages(pages);
		
		
		setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setName(name);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//add(tabbedPane);
		//setColumnHeaderView(tabbedPane);
		setViewportView(tabbedPane);
		
		for(FarmObjectPage page : pages)
		{
			addTabPage(page);
		}
		
		
		

	}

	public ArrayList<FarmObjectPage> getPages() {
		return pages;
	}
	
	public void addPage(FarmObjectPage page) {
		getPages().add(page);
	}

	private void setPages(ArrayList<FarmObjectPage> pages) {
		this.pages = pages;
	}
	
	private void addTabPage(FarmObjectPage page) {
		tabbedPane.addTab(page.getName(), null, page, page.getToolTipText());
	}

}

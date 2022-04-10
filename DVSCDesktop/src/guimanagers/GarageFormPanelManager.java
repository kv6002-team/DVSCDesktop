package guimanagers;

import javax.swing.JPanel;

import gui.GarageFormPanel;

public class GarageFormPanelManager {
	
	GarageFormPanel GarageFormPanel;
	
	public GarageFormPanelManager() {
		
		GarageFormPanel = new GarageFormPanel();
		
	}
	
	public JPanel getGarageFormPanel() {
		return this.GarageFormPanel;
	}
}

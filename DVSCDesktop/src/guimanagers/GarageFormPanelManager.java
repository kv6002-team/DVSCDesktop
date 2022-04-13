package guimanagers;

import javax.swing.JPanel;

import gui.GarageFormPanel;

public class GarageFormPanelManager {
	
	GarageFormPanel garageFormPanel = new GarageFormPanel();;
	
	public GarageFormPanelManager() {
		
	}
	
	GarageFormPanel getGarageFormPanel() {
		return this.garageFormPanel;
	}
	
	
	
}

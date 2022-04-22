package guimanagers;

import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import connection.Connection;
import domain.Garage;
import gui.AlertDialog;
import gui.GarageFormPanel;

public class GarageFormPanelManager{
	
	GarageFormPanel garageFormPanel = new GarageFormPanel();;
	Connection connection = new Connection();
	
	public GarageFormPanelManager(CRUDPanelManager CRUDPanelManager) {
		
		garageFormPanel.getConfirmButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean valid = validateInputs();
				
				if(valid) {
					Garage newGarage = new Garage(
							garageFormPanel.getVTS().getText(),
							garageFormPanel.getOwnerName().getText(),
							garageFormPanel.getGarageName().getText(),
							garageFormPanel.getContactEmail().getText(),
							garageFormPanel.getContactNum().getText(),
							garageFormPanel.getPaidUntil().getDate()
							);
					int id = connection.addGarage(newGarage);
					
					if(id == -1) {
						AlertDialog x = new AlertDialog();
						x.run("Garage Creation Failed. Please try again.");
					}
					else {
						newGarage.setGarageID(id);
						DefaultListModel<Garage> garagesList = (DefaultListModel<Garage>) CRUDPanelManager.getCRUDPanel().getGaragesList().getModel();
						garagesList.addElement(newGarage);
						CRUDPanelManager.getCRUDPanel().getGaragesList().setModel(garagesList);
						
						JOptionPane.showMessageDialog(new JFrame(), "Garage Created");
						((Window) garageFormPanel.getTopLevelAncestor()).dispose();
					}
					
					
				}
			}
		});
	}
	
	boolean validateInputs() {
		
		boolean valid = true;
		
		boolean missingFields = false;
		boolean invalidEmail = false;
		boolean invalidNum = false;
		boolean invalidDate = false;
		
		if(
				garageFormPanel.getVTS().getText().equals("") ||
				garageFormPanel.getOwnerName().getText().equals("") ||
				garageFormPanel.getGarageName().getText().equals("") ||
				garageFormPanel.getContactEmail().getText().equals("") ||
				garageFormPanel.getContactNum().getText().equals("") ||
				garageFormPanel.getPaidUntil().getDate() == null
		){
			missingFields = true;
		}
		
		if(!garageFormPanel.getContactEmail().getText().matches("^(?=.{1,128}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@(?=.{1,128})[A-Za-z0-9]{1,}(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
			invalidEmail = true;
		}
		
		if(!garageFormPanel.getContactNum().getText().matches("[0-9]+")) {
			invalidNum = true;
		}
		
		if(garageFormPanel.getPaidUntil().getDate() != null) {
			if(garageFormPanel.getPaidUntil().getDate().before(new Date()) ) {
				invalidDate = true;
			}
		}
		
		String errorMsg = "";
		
		if(missingFields) {
			errorMsg += "Missing Fields\n";
			
			AlertDialog x = new AlertDialog();
			x.run(errorMsg);
			
			valid = false;
		}
		else { 
			if(invalidEmail || invalidNum || invalidDate) {
				if(invalidEmail) {
					errorMsg += "Invalid Email\n";
				}
				if(invalidNum) {
					errorMsg += "Invalid Contact Number\n";
				}
				if(invalidDate) {
					errorMsg += "Invalid Date\n";
				}
				
				AlertDialog x = new AlertDialog();
				x.run(errorMsg);
				
				valid = false;	
			}else {
				valid = true;
			}	
		}
		return valid;
	}
	
	GarageFormPanel getGarageFormPanel() {
		return this.garageFormPanel;
	}
	
	
	
}

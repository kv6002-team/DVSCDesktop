package guimanagers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JPanel;

import domain.Garage;
import gui.AlertDialog;
import gui.GarageFormPanel;

public class GarageFormPanelManager {
	
	GarageFormPanel garageFormPanel = new GarageFormPanel();;
	
	public GarageFormPanelManager() {
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
					
					System.out.println(newGarage);
					
					//ADD TO LIST AND SEND TO SERVER
					
					AlertDialog x = new AlertDialog();
					x.run("Garage Created");
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
			//System.out.println("missing");
			missingFields = true;
		}
		
		if(!garageFormPanel.getContactEmail().getText().matches("^(?=.{1,128}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@(?=.{1,128})[A-Za-z0-9]{1,}(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
			invalidEmail = true;
		}
		
		//System.out.println(!garageFormPanel.getContactNum().getText().matches("[0-9]+"));
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

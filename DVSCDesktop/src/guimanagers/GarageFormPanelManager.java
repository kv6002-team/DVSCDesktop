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

/**
 * Manager Class for the GarageFormPanel UI Class
 * 
 * @author Callum
 *
 */
public class GarageFormPanelManager{
	
	GarageFormPanel garageFormPanel = new GarageFormPanel();;
	Connection connection = new Connection();
	
	public GarageFormPanelManager(CRUDPanelManager CRUDPanelManager) {
		
		/**
		 * Mouse Listener for click on Confirm button.
		 */
		garageFormPanel.getConfirmButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Validate form inputs
				boolean valid = validateInputs();
				
				// If inputs are valid
				if(valid) {
					// Creates new Garage Object using input fields.
					Garage newGarage = new Garage(
							garageFormPanel.getVTS().getText(),
							garageFormPanel.getOwnerName().getText(),
							garageFormPanel.getGarageName().getText(),
							garageFormPanel.getContactEmail().getText(),
							garageFormPanel.getContactNum().getText(),
							garageFormPanel.getPaidUntil().getDate()
							);
					
					// Sends the new Garage off to the server, returning a Garage ID
					int id = connection.addGarage(newGarage);
					
					/* Check to see if the sending Garage failed.
					 * if fails, create AlertDialog informing user
					 * otherwise, add new garage to garagesList ListModel.
					 */
					if(id == -1) {
						AlertDialog x = new AlertDialog();
						x.run("Garage Creation Failed. Please try again.");
					}
					else {
						newGarage.setGarageID(id);
						DefaultListModel<Garage> garagesList = (DefaultListModel<Garage>) CRUDPanelManager.getCRUDPanel().getGaragesList().getModel();
						garagesList.addElement(newGarage);
						CRUDPanelManager.getCRUDPanel().getGaragesList().setModel(garagesList);
						
						clearInputs();
						
						JOptionPane.showMessageDialog(new JFrame(), "Garage Created");
						((Window) garageFormPanel.getTopLevelAncestor()).dispose();
					}
					
					
				}
			}
		});
	}
	
	/**
	 * Validates the input fields of the Garage Form.
	 * 
	 * @return validity of the form inputs (True / False)
	 */
	boolean validateInputs() {
		
		boolean valid = true;
		
		boolean missingFields = false;
		boolean invalidEmail = false;
		boolean invalidNum = false;
		boolean invalidDate = false;
		
		// Check if any of the fields are empty, setting a flag to true.
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
		
		/* Check if email address does not meet the 
		 * expected format, setting flag to true. */
		if(!garageFormPanel.getContactEmail().getText()
				.matches(
						"^"
						+ "(?=.{1,128}@)"
						+ "[A-Za-z0-9_-]+"
						+ "(\\.[A-Za-z0-9_-]+)*"
						+ "@(?=.{1,128})"
						+ "[A-Za-z0-9]"
						+ "(\\.[A-Za-z0-9-]+)*"
						+ "(\\.[A-Za-z]{2,})"
						+ "$"
						)
				) {
			invalidEmail = true;
		}
		
		// Checks if the phone number isn't numeric. setting a flag to true.
		if(!garageFormPanel.getContactNum().getText().matches("[0-9]+")) {
			invalidNum = true;
		}
		
		// Checks if the paidUntil date is not in the past, setting a flag to true.
		if(garageFormPanel.getPaidUntil().getDate() != null) {
			if(garageFormPanel.getPaidUntil().getDate().before(new Date()) ) {
				invalidDate = true;
			}
		}
		
		String errorMsg = "";
		/* Depending on the flags which are true,
		 * append an error message to display to the user
		 * within an new AlertDialog box */
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
	
	/**
	 * Clears the form input fields.
	 */
	void clearInputs() {
		garageFormPanel.getVTS().setText("");
		garageFormPanel.getOwnerName().setText("");
		garageFormPanel.getGarageName().setText("");
		garageFormPanel.getContactEmail().setText("");
		garageFormPanel.getContactNum().setText("");
		garageFormPanel.getPaidUntil().setDate(null);
	}
	
	/* GarageFormPanelManager Getters
	 * -------------------------------------------------- */
	
	GarageFormPanel getGarageFormPanel() {
		return this.garageFormPanel;
	}
}

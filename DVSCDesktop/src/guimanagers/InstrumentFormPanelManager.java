package guimanagers;

import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import connection.Connection;
import domain.Instrument;
import domain.Instrument.CheckStatus;
import gui.AlertDialog;
import gui.InstrumentFormPanel;

/**
 *  Manager Class for the InstrumentFormPanel UI Class.
 *  
 * @author callu
 *
 */
public class InstrumentFormPanelManager{
	
	InstrumentFormPanel instrumentFormPanel = new InstrumentFormPanel();;
	Connection connection = new Connection();
	
	public InstrumentFormPanelManager(CRUDPanelManager CRUDPanelManager) {
		
		/**
		 * Mouse Listener for click on Confirm button.
		 */
		instrumentFormPanel.getConfirmButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Validate form inputs
				boolean valid = validateInputs();
				
				// If inputs are valid
				if(valid) {
					// Creates new Instrument Object using input fields.
					Instrument newInstrument = new Instrument(
							instrumentFormPanel.getInstrumentName().getText(),
							instrumentFormPanel.getSerialNumber().getText(),
							instrumentFormPanel.getCheckDate().getDate(),
							instrumentFormPanel.getStatusExpiryDate().getDate(),
							CheckStatus.UNCHECKED
							);
				
					// Sends the new Instrument off to the server, returning an Instrument ID
					int id = connection.addInstrument(
							CRUDPanelManager.getCRUDPanel().getGaragesList().getSelectedValue(),
							newInstrument
							);
					
					/* Check to see if the sending Garage failed.
					 * if fails, create AlertDialog informing user
					 * otherwise, add new garage to garagesList ListModel.
					 */
					if(id == -1) {
						AlertDialog x = new AlertDialog();
						x.run("Instrument creation failed. Please try again.");
					}
					else {
						
						newInstrument.setInstrumentID(id);	
						
						CRUDPanelManager.getCRUDPanel().getGaragesList().getSelectedValue().getGarageInfo().getInstrumentList().add(newInstrument);
						
						DefaultListModel<Instrument> instrumentsList = (DefaultListModel<Instrument>) CRUDPanelManager.getCRUDPanel().getInstrumentList().getModel();
						instrumentsList.addElement(newInstrument);
						CRUDPanelManager.getCRUDPanel().setInstrumentList(instrumentsList);
						
						JOptionPane.showMessageDialog(new JFrame(), "Instrument Created");
						
						clearInputs();
						
						((Window) instrumentFormPanel.getTopLevelAncestor()).dispose();
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
		boolean invalidSerialNumber = false;
		boolean invalidStatusExpiryDate = false;
		
		// Check if any of the fields are empty, setting a flag to true.
		if(
				instrumentFormPanel.getInstrumentName().getText().equals("") ||
				instrumentFormPanel.getSerialNumber().getText().equals("") ||
				instrumentFormPanel.getCheckDate().getDate() == null ||
				instrumentFormPanel.getStatusExpiryDate().getDate() == null
		){
			missingFields = true;
		}
		
		// Check if serial number is not alphanumeric, setting a flag to true
		if(!instrumentFormPanel.getSerialNumber().getText().matches("[A-Za-z0-9]+")) {
			invalidSerialNumber = true;
		}
		
		// Check if the StatusExpiryDate is before tomorrow, setting a flag to true
		if(
				instrumentFormPanel.getStatusExpiryDate().getDate() != null &&
				instrumentFormPanel.getStatusExpiryDate().getDate().before(new Date(System.currentTimeMillis() - (System.currentTimeMillis() % 86400000) + 86400000))
		) {
			invalidStatusExpiryDate = true;
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
			if(invalidSerialNumber || invalidStatusExpiryDate) {
				if(invalidSerialNumber) {
					errorMsg += "Invalid Serial Number\n";
				}
				
				if(invalidStatusExpiryDate) {
					errorMsg += "Invalid Status Expiry Date\n";
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
		instrumentFormPanel.getInstrumentName().setText("");
		instrumentFormPanel.getSerialNumber().setText("");
		instrumentFormPanel.getCheckDate().setDate(null);
		instrumentFormPanel.getStatusExpiryDate().setDate(null);
	}
	
	/* InstrumentFormPanelManager Getters
	 * -------------------------------------------------- */
	
	InstrumentFormPanel getInstrumentFormPanel() {
		return this.instrumentFormPanel;
	}
	
	
	
}

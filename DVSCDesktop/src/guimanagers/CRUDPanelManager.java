package guimanagers;

import gui.AlertDialog;
import gui.CRUDPanel;
import gui.Wrapper;
import domain.Garage;
import domain.GarageInfo;
import domain.Instrument;
import domain.Instrument.CheckStatus;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import connection.Connection;

/**
 * Manager Class for the CRUDPanel UI Class.
 * 
 * @author Callum
 *
 */

public class CRUDPanelManager {
	
	private CRUDPanel CRUDPanel = new CRUDPanel();
	private GarageFormPanelManager garageFormPanelManager = new GarageFormPanelManager(this);
	private InstrumentFormPanelManager instrumentFormPanelManager = new InstrumentFormPanelManager(this);
	
	private Connection connection = new Connection(); 
	
	private Garage lastGarage;
	private Instrument lastInstrument;
	
	public CRUDPanelManager(Wrapper wrapper){
		
		// Retrieve all garages from the server.
		ArrayList<Garage> allGarages = connection.getAllGarages();
		
		/**
		 * Change Listener for Garages TabbedPane being selected.
		 */
		wrapper.getTabbedPane().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// Populates the Garages JList with garages from server.
				populateGarageList(allGarages);
			}
		});
		
		/**
		 * List Selection Listener for Garage List Selection.
		 */
		CRUDPanel.getGaragesList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				// Reset All UI Info Fields.
				resetGarageFields();
				resetInstrumentFields();
				
				// Stop action from occurring twice by only acting on final event.
				if(e.getValueIsAdjusting()){
					return;
				}
				
				// Re-populate fields if a garage in the list is selected.
				if(CRUDPanel.getGaragesList().getSelectedValue() != null) {
					if(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo() == null) {
						CRUDPanel.getGaragesList().getSelectedValue().addGarageInfo();
					}
					populateGarageFields();
					populateInstrumentList(CRUDPanel
							.getGaragesList()
							.getSelectedValue()
							.getGarageInfo()
							.getInstrumentList()
							);
				}
				
			}
		});
		
		/**
		 * List Selection Listener for Instrument List Selection.
		 */
		CRUDPanel.getInstrumentList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				// Stop action from occurring twice by only acting on final event.
				if(e.getValueIsAdjusting()) {
					return;
				}
				
				// Reset the combo box UI component.
				DefaultComboBoxModel<String> checkStatusModel = new DefaultComboBoxModel<String>();
				for(CheckStatus status : CheckStatus.values()) {	
					checkStatusModel.addElement(CheckStatus.checkStatusToString(status));
			 	}
				
				// Update the previous instrument if one was previously selected.
				System.out.println(CRUDPanel.getInstrumentList().getSelectedValue());
				System.out.println(lastInstrument);
				if(lastInstrument != null) {
					if(validateInstrumentInputs()) {
						updateInstrument(lastInstrument);
					}
				}
				
				/* If there is a currently selected instrument 
				 * set the instrument fields to hold that instruments data. */
				if(CRUDPanel.getInstrumentList().getSelectedValue() != null) { 
						CRUDPanel.getSerialNumTextField().setText(
								CRUDPanel
								.getInstrumentList()
								.getSelectedValue()
								.getSerialNum()
								);
						
						CRUDPanel.getInstrumentNameTextField().setText(
								CRUDPanel
								.getInstrumentList()
								.getSelectedValue()
								.getInstrumentName()
								);
						
						CRUDPanel.getCheckDate().setDate(
								CRUDPanel
								.getInstrumentList()
								.getSelectedValue()
								.getCheckDate()
								);
						
						CRUDPanel.getStatusExpiryDate().setDate(
								CRUDPanel
								.getInstrumentList()
								.getSelectedValue()
								.getStatusExpiryDate()
								);
						
						CRUDPanel.setCheckboxList(checkStatusModel);
						
						CRUDPanel.getCheckStatusComboBox().setSelectedItem(
								CheckStatus.checkStatusToString(
										CRUDPanel
										.getInstrumentList()
										.getSelectedValue()
										.getCheckStatus()
								)
							);
				}
				
				// Set the last Garage and Instrument to the currently selected value.
				lastGarage = CRUDPanel.getGaragesList().getSelectedValue();
				lastInstrument = CRUDPanel.getInstrumentList().getSelectedValue();
			}
		});
		
		/**
		 * Mouse Listener for click on the Add Garage button.
		 */
		CRUDPanel.getAddGarageButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Runs method to produce new Garage window
				addGarage();
			}
		});
		
		/**
		 * Mouse Listener for click on the Delete Garage button.
		 */
		CRUDPanel.getDeleteGarageButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/* Run method to remove garage, passing
				 * in currently selected garage */
				removeGarage(CRUDPanel.getGaragesList().getSelectedValue());
			}
		});
		
		/**
		 * Mouse Listener for click on Add Garage button.
		 */
		CRUDPanel.getAddInstrumentButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Run method to produce new Instrument window
				addInstrument();
			}	
		});
		
		/**
		 * Mouse Listener for click on Delete Instrument button.
		 */
		CRUDPanel.getDeleteInstrumentButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/* Run method to remove instrument, passing
				 * in the currently selected instrument. */
				removeInstrument(CRUDPanel.getInstrumentList().getSelectedValue());
			}
		});
	
		/**
		 * Mouse Listener for click on Discard Changes button.
		 */
		CRUDPanel.getDiscardChangesButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Run discard changes method.
				discardChanges();
			}
		});
		
		/**
		 * Mouse Listener for click on Save Changes button
		 */
		CRUDPanel.getSaveChangesButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// If there is a currently selected instrument, update the instrument to keep its changes
				if(CRUDPanel.getInstrumentList().getSelectedValue() != null || CRUDPanel.getInstrumentList().getSelectedIndex() != -1) {
					updateInstrument(CRUDPanel.getInstrumentList().getSelectedValue());
				}
				// Run method to save currently made changes to server.
				saveChanges();
			}
		});	
	}
	
	/**
	 * Getter for CRUDPanel object.
	 * 
	 * @return The created CRUDPanel object.
	 */
	public CRUDPanel getCRUDPanel(){
		return CRUDPanel;
	}

	/**
	 * Populates the JList of Garages with a provided 
	 * ArrayList of Garage objects.
	 * 
	 * @param garages
	 */
	public void populateGarageList(ArrayList<Garage> garages){
		// Create a new empty DefaultListModel.
		DefaultListModel<Garage> list = new DefaultListModel<Garage>();
		/* Loop through the list of Garages 
		 * adding them to the DefaultListModel. */
		for(int i=0; i<garages.size(); i++){
			list.addElement(garages.get(i));
		}
		// Set the JList's ListModel to the new DefaultListModel
		CRUDPanel.setGarageList(list);	
	}
	
	/**
	 * Resets the fields relating to Garage Information.
	 */
	public void resetGarageFields() {
		CRUDPanel.getGarageNameTextField().setText("");
		CRUDPanel.getGarageOwnerTextField().setText("");
		CRUDPanel.getGarageEmailTextField().setText("");
		CRUDPanel.getGarageNumberTextField().setText("");
		CRUDPanel.getGaragePaidUntil().setDate(null);	
	}
	
	/**
	 * Populate the fields relating to garage list. 
	 */
	public void populateGarageFields() {
		/* If there is a currently selected Garage in the list,
		 * set the garage fields with that garages information */
		if(CRUDPanel.getGaragesList().getSelectedValue() != null) {
			CRUDPanel.getGarageNameTextField().setText(
					CRUDPanel
					.getGaragesList()
					.getSelectedValue()
					.getGarageName()
					);
			CRUDPanel.getGarageOwnerTextField().setText(
					CRUDPanel
					.getGaragesList()
					.getSelectedValue()
					.getGarageInfo()
					.getOwnerName()
					);
			CRUDPanel.getGarageEmailTextField().setText(
					CRUDPanel
					.getGaragesList()
					.getSelectedValue()
					.getGarageInfo()
					.getEmailAddress()
					);
			CRUDPanel.getGarageNumberTextField().setText(
					CRUDPanel
					.getGaragesList()
					.getSelectedValue()
					.getGarageInfo()
					.getTelephoneNum()
					);
			CRUDPanel.getGaragePaidUntil().setDate(
					CRUDPanel
					.getGaragesList()
					.getSelectedValue()
					.getGarageInfo()
					.getPaidUntil());
		}
	}

	/**
	 * Creates a new JFrame, populating it with
	 * a new GarageFormPanel instance from the relevant
	 * manager.
	 */
	public void addGarage() {
		JFrame garageFormFrame = new JFrame();
		garageFormFrame.add(garageFormPanelManager.getGarageFormPanel());
		garageFormFrame.setBounds(100, 100, 640, 360);
		garageFormFrame.setVisible(true);
	}

	/**
	 * Confirms if the user would like to delete the garage,
	 * if yes, garage is removed from the server and if successful 
	 * is removed from the local garages list,
	 * otherwise does nothing.
	 * 
	 * @param garage
	 */
	public void removeGarage(Garage garage) {
		
		int confirm = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete this garage?", "Please select", JOptionPane.YES_NO_OPTION);
		
		if(confirm == JOptionPane.YES_OPTION) {
			boolean removed = connection.removeGarage(garage.getGarageID());
			
			if(removed) {
				DefaultListModel<Garage> garagesList = (DefaultListModel<Garage>) CRUDPanel.getGaragesList().getModel();
				CRUDPanel.getGaragesList().clearSelection();
				garagesList.removeElement(garage);
				CRUDPanel.setGarageList(garagesList);	
			}
		}
	}

	/**
	 * Resets the fields relating to instrument information on the UI.
	 */
	public void resetInstrumentFields() {
		CRUDPanel.getInstrumentList().clearSelection();
		CRUDPanel.getSerialNumTextField().setText("");
		CRUDPanel.getInstrumentNameTextField().setText("");
		CRUDPanel.getCheckDate().setDate(null);
		CRUDPanel.getStatusExpiryDate().setDate(null);
		CRUDPanel.getCheckStatusComboBox().removeAllItems();
	}
	
	/**
	 * Populates the JList of Instruments with a provided 
	 * ArrayList of Instrument objects
	 * 
	 * @param instruments
	 */
	public void populateInstrumentList(ArrayList<Instrument> instruments){
		// If the list of instruments is not null
		if(instruments != null) {
			// Create a new empty DefaultListModel.
			DefaultListModel<Instrument> list = new DefaultListModel<Instrument>();
			/* Loop through the list of Instruments 
			*  adding them to the DefaultListModel. */
			for(int i=0; i<instruments.size(); i++) {
				list.addElement(instruments.get(i));
			}
			// Set the JList's ListModel to the new DefaultListModel
			CRUDPanel.setInstrumentList(list);
		}
	}
	
	/**
	 * Creates a new JFrame, populating it with
	 * a new InstrumentFormPanel instance from the relevant
	 * manager.
	 */
	public void addInstrument() {
		JFrame instrumentFormFrame = new JFrame();
		instrumentFormFrame.add(instrumentFormPanelManager.getInstrumentFormPanel());
		instrumentFormFrame.setBounds(100, 100, 640, 360);
		instrumentFormFrame.setVisible(true);
	}
	
	/**
	 * Confirms if the user would like to delete the instrument,
	 * and deletes it
	 * 
	 * @param instrument
	 */
	public void removeInstrument(Instrument instrument) {
		
		// Create a new option pane to ask user if they want to remove the Instrument
		int confirm = JOptionPane.showConfirmDialog(
				new JFrame(),
				"Are you sure you want to delete this garage?",
				"Please select", JOptionPane.YES_NO_OPTION);
		
		// if they choose yes remove, otherwise do nothing.
		if(confirm == JOptionPane.YES_OPTION) {	
			// Remove the Instrument from the server
			boolean removed = connection.removeInstrument(instrument.getInstrumentID());
			
			/* If successfully removed from the server,
			 * remove locally from instrument list */
			if(removed) {
				CRUDPanel.getInstrumentList().clearSelection();
				
				CRUDPanel.getGaragesList().getSelectedValue()
				.getGarageInfo()
				.getInstrumentList()
				.remove(instrument);
				
				DefaultListModel<Instrument> instrumentsList = 
						(DefaultListModel<Instrument>) CRUDPanel
						.getInstrumentList()
						.getModel();
				
				instrumentsList.removeElement(instrument);
				CRUDPanel.setInstrumentList(instrumentsList);
			}
		}
	}

	/**
	 * Updates the provided instrument's parameters with changed fields
	 * 
	 * @param instrument
	 */
	public void updateInstrument(Instrument instrument) {
		// If combo box is not null, and garage info is not null
		if(CRUDPanel.getCheckStatusComboBox().getSelectedItem() != null) {
			//if(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo() != null) {
				// Get the index of the instrument to be updated,
				int i = lastGarage.getGarageInfo().getInstrumentList().indexOf(instrument);
				
				if(i < 0) {
					i = 0;
				}
				
				// Set instrument attributes to the values in the UI.
				instrument.setInstrumentName(CRUDPanel.getInstrumentNameTextField().getText());
				instrument.setCheckDate(CRUDPanel.getCheckDate().getDate());
				instrument.setStatusExpiryDate(CRUDPanel.getStatusExpiryDate().getDate());
				instrument.setCheckStatus(CheckStatus.of(CRUDPanel.getCheckStatusComboBox().getSelectedItem().toString()));
				
				// Update the provided Instrument of the previously selected Garage
				lastGarage.getGarageInfo().getInstrumentList().set(i, instrument);		
			//}		
		}
	}
	
	/**
	 * discard any changes made to Instruments and Garages.
	 */
	public void discardChanges() {
		// Resets the fields related to the Instrument
		resetInstrumentFields();
		/* Create a temporary garage into, populated 
		 * with the data from the server */
		GarageInfo tempGarageInfo = connection.getGarage(
				CRUDPanel
				.getGaragesList()
				.getSelectedValue()
				.getGarageID()
				);
		/* Set the currently selected Garage's GarageInfo 
		 * to the new temporary GarageInfo */
		CRUDPanel.getGaragesList()
		.getSelectedValue()
		.setGarageInfo(tempGarageInfo);
		
		// Populate the InstrumentList and GaragesField with the now reset fields.
		populateInstrumentList(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getInstrumentList());
		populateGarageFields();
	}
	
	/**
	 * save any local changes for Garages and Instruments to the server.
	 */
	public void saveChanges() {
		// If there is a currently selected instrument 
		if(CRUDPanel.getInstrumentList().getSelectedValue() != null || 
				CRUDPanel.getInstrumentList().getSelectedIndex() != -1) {
			/* if there is a selected instrument, check 
			 * if the fields for Instrument and Garage are valid. */
			if(validateGarageInputs() && validateInstrumentInputs()) {
				//Update selected Garage attributes with current fields.
				CRUDPanel
				.getGaragesList()
				.getSelectedValue()
				.setGarageName(
						CRUDPanel.getGarageNameTextField().getText());
				
				CRUDPanel
				.getGaragesList()
				.getSelectedValue()
				.getGarageInfo()
				.setOwnerName(CRUDPanel.getGarageOwnerTextField().getText());
				
				CRUDPanel
				.getGaragesList()
				.getSelectedValue()
				.getGarageInfo()
				.setEmailAddress(CRUDPanel.getGarageEmailTextField().getText());
				
				CRUDPanel
				.getGaragesList()
				.getSelectedValue()
				.getGarageInfo()
				.setTelephoneNum(CRUDPanel.getGarageNumberTextField().getText());
				
				CRUDPanel
				.getGaragesList()
				.getSelectedValue()
				.getGarageInfo()
				.setPaidUntil(CRUDPanel.getGaragePaidUntil().getDate());
				
				// Send updated information to server.
				connection.saveChanges(CRUDPanel.getGaragesList().getSelectedValue());
			}
		}
		// if no Instrument is selected, only validate the Garage inputs.
		else if(validateGarageInputs()) {
			// Update selected Garage attributes with current fields.
			CRUDPanel
			.getGaragesList()
			.getSelectedValue()
			.setGarageName(CRUDPanel.getGarageNameTextField().getText());
			
			CRUDPanel
			.getGaragesList()
			.getSelectedValue()
			.getGarageInfo()
			.setOwnerName(CRUDPanel.getGarageOwnerTextField().getText());
			
			CRUDPanel
			.getGaragesList()
			.getSelectedValue()
			.getGarageInfo()
			.setEmailAddress(CRUDPanel.getGarageEmailTextField().getText());
			
			CRUDPanel
			.getGaragesList()
			.getSelectedValue()
			.getGarageInfo()
			.setTelephoneNum(CRUDPanel.getGarageNumberTextField().getText());
			
			CRUDPanel
			.getGaragesList()
			.getSelectedValue()
			.getGarageInfo()
			.setPaidUntil(CRUDPanel.getGaragePaidUntil().getDate());
			
			CRUDPanel.getGaragesList()
			.getSelectedValue()
			.getGarageInfo()
			.setPaidUntil(CRUDPanel.getGaragePaidUntil().getDate());
			
			// Send updated information to server.
			connection.saveChanges(CRUDPanel.getGaragesList().getSelectedValue());
		}
	}
	
	/**
	 * Validates the input fields for Garage to stop invalid data
	 * being sent to the server.
	 * 
	 * @return the validity of the garage inputs (true / false)
	 */
	boolean validateGarageInputs() {
		boolean valid = true;
		
		boolean missingFields = false;
		boolean invalidEmail = false;
		boolean invalidNum = false;
		boolean invalidDate = false;
		
		// Check if any of the fields are empty, setting a flag to true.
		if(
				CRUDPanel.getGarageOwnerTextField().getText().equals("") ||
				CRUDPanel.getGarageNameTextField().getText().equals("") ||
				CRUDPanel.getGarageEmailTextField().getText().equals("") ||
				CRUDPanel.getGarageNumberTextField().getText().equals("") ||
				CRUDPanel.getGaragePaidUntil().getDate() == null
		){
			missingFields = true;
		}
		
		/* Check if email address does not meet the 
		 * expected format, setting flag to true. */
		if(!CRUDPanel.getGarageEmailTextField().getText()
				.matches(
						"^"
						+ "(?=.{1,128}@)"
						+ "[A-Za-z0-9_-]+"
						+ "(\\.[A-Za-z0-9_-]+)*"
						+ "@"
						+ "(?=.{1,128})"
						+ "[A-Za-z0-9]"
						+ "(\\.[A-Za-z0-9-]+)*"
						+ "(\\.[A-Za-z]{2,})"
						+ "$"
						)
				) {
			invalidEmail = true;
		}
		
		// Checks if the phone number isn't numeric. setting a flag to true.
		if(!CRUDPanel.getGarageNumberTextField().getText().matches("[0-9]+")) {
			invalidNum = true;
		}
		
		// Checks if the paidUntil date is not in the past, setting a flag to true.
		if(CRUDPanel.getGaragePaidUntil().getDate() != null &&
				CRUDPanel.getGaragePaidUntil().getDate().before(new Date()) ) {
				invalidDate = true;
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
	 *  Validates the input fields for Instrument to stop invalid data
	 * being sent to the server.
	 * 
	 * @return the validity of the Instrument inputs (True, False)
	 */
	boolean validateInstrumentInputs() {
		boolean valid = true;
		
		boolean missingFields = false;
		boolean invalidSerialNumber = false;
		boolean invalidStatusExpiryDate = false;
		
		// Check if any of the fields are empty, setting a flag to true.
		if(
				CRUDPanel.getInstrumentNameTextField().getText().equals("") ||
				CRUDPanel.getSerialNumTextField().getText().equals("") ||
				CRUDPanel.getStatusExpiryDate().getDate() == null
		){
			missingFields = true;
		}
		
		// Check if the serial number is not alphanumeric, setting a flag to true.
		if(!CRUDPanel.getSerialNumTextField().getText().matches("[A-Za-z0-9]+")) {
			invalidSerialNumber = true;
		}
		
		// Check if the StatusExpiryDate before tomorrow, setting a flag to true
		if(
				CRUDPanel.getStatusExpiryDate().getDate() != null &&
				CRUDPanel.getStatusExpiryDate().getDate().before(new Date(System.currentTimeMillis() - (System.currentTimeMillis() % 86400000) + 86400000))
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
}


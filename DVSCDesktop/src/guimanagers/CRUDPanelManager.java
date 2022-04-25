package guimanagers;

import gui.AlertDialog;
import gui.CRUDPanel;
import gui.Wrapper;
import utils.Console;
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
 * 
 * @author Callum
 *
 */

public class CRUDPanelManager {
	
	private CRUDPanel CRUDPanel = new CRUDPanel();
	private GarageFormPanelManager garageFormPanelManager = new GarageFormPanelManager(this);
	private InstrumentFormPanelManager instrumentFormPanelManager = new InstrumentFormPanelManager(this);
	
	ArrayList<Garage> garages = new ArrayList<Garage>();
	
	private Connection connection = new Connection(); 
	
	private Instrument lastInstrument;
	
	public CRUDPanelManager(Wrapper wrapper){
		
		ArrayList<Garage> allGarages = connection.getAllGarages();
		
		// Listener for the change of tab to the CRUD Panel.
		wrapper.getTabbedPane().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				populateGarageList(allGarages);
			}
		});
		
		// Listener for the change of selection within the JList of Garages.
		CRUDPanel.getGaragesList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				resetGarageFields();
				resetInstrumentFields();
				
				if(e.getValueIsAdjusting()){
					return;
				}
				
				if(CRUDPanel.getGaragesList().getSelectedValue() != null) {
					if(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo() == null) {
						CRUDPanel.getGaragesList().getSelectedValue().addGarageInfo();
					}
					populateGarageFields();
					populateInstrumentList(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getInstrumentList());
				}
				
			}
		});
		
		// Listener for the change of selection within the JList of Instruments.
		CRUDPanel.getInstrumentList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				
				if(e.getValueIsAdjusting()) {
					return;
				}
				
				// Load in next selected instrument
				DefaultComboBoxModel<String> checkStatusModel = new DefaultComboBoxModel<String>();
				for(CheckStatus status : CheckStatus.values()) {	
					checkStatusModel.addElement(CheckStatus.checkStatusToString(status));
			 	}
				
				if(lastInstrument != null) {
					updateInstrument(lastInstrument);
				}
					
				if(CRUDPanel.getInstrumentList().getSelectedValue() != null) { 
						CRUDPanel.getSerialNumTextField().setText(CRUDPanel.getInstrumentList().getSelectedValue().getSerialNum());
						CRUDPanel.getInstrumentNameTextField().setText(CRUDPanel.getInstrumentList().getSelectedValue().getInstrumentName());
						CRUDPanel.getCheckDate().setDate(CRUDPanel.getInstrumentList().getSelectedValue().getCheckDate());
						CRUDPanel.getStatusExpiryDate().setDate(CRUDPanel.getInstrumentList().getSelectedValue().getStatusExpiryDate());
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
				
				lastInstrument = CRUDPanel.getInstrumentList().getSelectedValue();
			}
		});
		
		// Listener for the mouse clicking on the add garage button.
		CRUDPanel.getAddGarageButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addGarage();
			}
		});
		
		// Listener for the mouse clicking on the delete garage button.
		CRUDPanel.getDeleteGarageButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeGarage(CRUDPanel.getGaragesList().getSelectedValue());
			}
		});
		
		// Listener for the mouse clicking on the add instrument button.
		CRUDPanel.getAddInstrumentButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addInstrument();
			}	
		});
		
		// Listener for the mouse clicking on delete instrument button
		CRUDPanel.getDeleteInstrumentButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeInstrument(CRUDPanel.getInstrumentList().getSelectedValue());
			}
		});
	
		// Listener for the mouse click on the discard changes button.
		CRUDPanel.getDiscardChangesButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				discardChanges();
			}
		});
		
		// Listener for the mouse click on the save changes button.
		CRUDPanel.getSaveChangesButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				saveChanges();
			}
		});
		
	}
		
	public CRUDPanel getCRUDPanel(){
		return CRUDPanel;
	}

	public void populateGarageList(ArrayList<Garage> garages){
		DefaultListModel<Garage> list = new DefaultListModel<Garage>();
		for(int i=0; i<garages.size(); i++){
			list.addElement(garages.get(i));
		}
		CRUDPanel.setGarageList(list);	
	}
	
	
	public void resetGarageFields() {
		CRUDPanel.getGarageNameTextField().setText("");
		CRUDPanel.getGarageOwnerTextField().setText("");
		CRUDPanel.getGarageEmailTextField().setText("");
		CRUDPanel.getGarageNumberTextField().setText("");
		CRUDPanel.getGaragePaidUntil().setDate(null);	
	}
	
	
	public void populateGarageFields() {
		if(CRUDPanel.getGaragesList().getSelectedValue() != null) {
			CRUDPanel.getGarageNameTextField().setText(CRUDPanel.getGaragesList().getSelectedValue().getGarageName());
			CRUDPanel.getGarageOwnerTextField().setText(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getOwnerName());
			CRUDPanel.getGarageEmailTextField().setText(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getEmailAddress());
			CRUDPanel.getGarageNumberTextField().setText(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getTelephoneNum());
			CRUDPanel.getGaragePaidUntil().setDate(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getPaidUntil());
		}
	}
	

	public void addGarage() {
		JFrame garageFormFrame = new JFrame();
		garageFormFrame.add(garageFormPanelManager.getGarageFormPanel());
		garageFormFrame.setBounds(100, 100, 640, 360);
		garageFormFrame.setVisible(true);
	}

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

	public void resetInstrumentFields() {
		CRUDPanel.getInstrumentList().clearSelection();
		CRUDPanel.getSerialNumTextField().setText("");
		CRUDPanel.getInstrumentNameTextField().setText("");
		CRUDPanel.getCheckDate().setDate(null);
		CRUDPanel.getStatusExpiryDate().setDate(null);
		CRUDPanel.getCheckStatusComboBox().removeAllItems();
	}
	
	
	public void populateInstrumentList(ArrayList<Instrument> instruments){
		if(!(instruments == null)) {
			DefaultListModel<Instrument> list = new DefaultListModel<Instrument>();
			for(int i=0; i<instruments.size(); i++) {
				list.addElement(instruments.get(i));
			}
			CRUDPanel.setInstrumentList(list);
		}
	}

	public void addInstrument() {
		JFrame garageFormFrame = new JFrame();
		garageFormFrame.add(instrumentFormPanelManager.getInstrumentFormPanel());
		garageFormFrame.setBounds(100, 100, 640, 360);
		garageFormFrame.setVisible(true);
	}

	public void removeInstrument(Instrument instrument) {
		
		int confirm = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete this garage?", "Please select", JOptionPane.YES_NO_OPTION);
		
		if(confirm == JOptionPane.YES_OPTION) {	
			boolean removed = connection.removeInstrument(instrument.getInstrumentID());
			
			if(removed) {
				
				
				CRUDPanel.getInstrumentList().clearSelection();
				
				CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getInstrumentList().remove(instrument);
				
				DefaultListModel<Instrument> instrumentsList = (DefaultListModel<Instrument>) CRUDPanel.getInstrumentList().getModel();
				instrumentsList.removeElement(instrument);
				CRUDPanel.setInstrumentList(instrumentsList);
			}
		}
	}

	public void updateInstrument(Instrument instrument) {
		
		if(CRUDPanel.getCheckStatusComboBox().getSelectedItem() != null) {
			if(!(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo() == null)) {
				int i = CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getInstrumentList().indexOf(instrument);
			
				if(i < 0) {
					i = 0;
				}
				
				instrument.setInstrumentName(CRUDPanel.getInstrumentNameTextField().getText());
				instrument.setCheckDate(CRUDPanel.getCheckDate().getDate());
				instrument.setStatusExpiryDate(CRUDPanel.getStatusExpiryDate().getDate());
				instrument.setCheckStatus(CheckStatus.of(CRUDPanel.getCheckStatusComboBox().getSelectedItem().toString()));
				
				CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getInstrumentList().set(i, instrument);		
			}		
		}
	}

	public void discardChanges() {
		resetInstrumentFields();
		GarageInfo tempGarageInfo = connection.getGarage(CRUDPanel.getGaragesList().getSelectedValue().getGarageID());	
		CRUDPanel.getGaragesList().getSelectedValue().setGarageInfo(tempGarageInfo);
		populateInstrumentList(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getInstrumentList());
		populateGarageFields();
	}
	
	public void saveChanges() {
		if(CRUDPanel.getInstrumentList().getSelectedValue() != null || CRUDPanel.getInstrumentList().getSelectedIndex() != -1) {
			if(validateGarageInputs() && validateInstrumentInputs()) {
				CRUDPanel.getGaragesList().getSelectedValue().setGarageName(CRUDPanel.getGarageNameTextField().getText());
				CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().setOwnerName(CRUDPanel.getGarageOwnerTextField().getText());
				CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().setEmailAddress(CRUDPanel.getGarageEmailTextField().getText());
				CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().setTelephoneNum(CRUDPanel.getGarageNumberTextField().getText());
				CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().setPaidUntil(CRUDPanel.getGaragePaidUntil().getDate());
				
				connection.saveChanges(CRUDPanel.getGaragesList().getSelectedValue());
			}
		}
		else if(validateGarageInputs()) {
			CRUDPanel.getGaragesList().getSelectedValue().setGarageName(CRUDPanel.getGarageNameTextField().getText());
			CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().setOwnerName(CRUDPanel.getGarageOwnerTextField().getText());
			CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().setEmailAddress(CRUDPanel.getGarageEmailTextField().getText());
			CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().setTelephoneNum(CRUDPanel.getGarageNumberTextField().getText());				CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().setPaidUntil(CRUDPanel.getGaragePaidUntil().getDate());
			CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().setPaidUntil(CRUDPanel.getGaragePaidUntil().getDate());

			connection.saveChanges(CRUDPanel.getGaragesList().getSelectedValue());
		}
	}
	
	boolean validateGarageInputs() {
		boolean valid = true;
		
		boolean missingFields = false;
		boolean invalidEmail = false;
		boolean invalidNum = false;
		boolean invalidDate = false;
		
		if(
				CRUDPanel.getGarageOwnerTextField().getText().equals("") ||
				CRUDPanel.getGarageNameTextField().getText().equals("") ||
				CRUDPanel.getGarageEmailTextField().getText().equals("") ||
				CRUDPanel.getGarageNumberTextField().getText().equals("") ||
				CRUDPanel.getGaragePaidUntil().getDate() == null
		){
			missingFields = true;
		}
		
		if(!CRUDPanel.getGarageEmailTextField().getText().matches("^(?=.{1,128}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@(?=.{1,128})[A-Za-z0-9]{1,}(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
			invalidEmail = true;
		}
		
		if(!CRUDPanel.getGarageNumberTextField().getText().matches("[0-9]+")) {
			invalidNum = true;
		}
		
		if(CRUDPanel.getGaragePaidUntil().getDate() != null) {
			if(CRUDPanel.getGaragePaidUntil().getDate().before(new Date()) ) {
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
	
	boolean validateInstrumentInputs() {
		boolean valid = true;
		
		boolean missingFields = false;
		boolean invalidSerialNumber = false;
		boolean invalidStatusExpiryDate = false;
		
		if(
				CRUDPanel.getInstrumentNameTextField().getText().equals("") ||
				CRUDPanel.getSerialNumTextField().getText().equals("") ||
				CRUDPanel.getStatusExpiryDate().getDate() == null
		){
			missingFields = true;
		}
		
		if(!CRUDPanel.getSerialNumTextField().getText().matches("[A-Za-z0-9]+")) {
			invalidSerialNumber = true;
		}
				
		if(
				CRUDPanel.getStatusExpiryDate().getDate() != null &&
				CRUDPanel.getStatusExpiryDate().getDate().before(new Date())
		) {
			invalidStatusExpiryDate = true;
		}

		
		String errorMsg = "";
		
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


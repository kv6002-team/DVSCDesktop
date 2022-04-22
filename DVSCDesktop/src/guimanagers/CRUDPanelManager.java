package guimanagers;

import gui.CRUDPanel;
import gui.Wrapper;
import domain.Garage;
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
				
				resetFields();
				
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
									
				DefaultComboBoxModel<String> checkStatusModel = new DefaultComboBoxModel<String>();
				for(CheckStatus status : CheckStatus.values()) {
					checkStatusModel.addElement(CheckStatus.checkStatusToString(status));
				}
				
				if(e.getValueIsAdjusting()) {
					return;
				}
				
				if(CRUDPanel.getInstrumentList().getSelectedValue() != null) { 
					CRUDPanel.getSerialNumTextField().setText(CRUDPanel.getInstrumentList().getSelectedValue().getSerialNum());
					CRUDPanel.getCheckDate().setDate(CRUDPanel.getInstrumentList().getSelectedValue().getStatusExpiryDate());
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
			}
		});
		
		//Listener for the mouse clicking on the add garage button.
		CRUDPanel.getAddGarageButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addGarage();
			}
		});
		
		//Listener for the mouse clicking on the delete garage button.
		CRUDPanel.getDeleteGarageButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeGarage(CRUDPanel.getGaragesList().getSelectedValue());
			}
		});
		
		CRUDPanel.getAddInstrumentButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addInstrument();
			}	
		});
		
		CRUDPanel.getDeleteInstrumentButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeInstrument(CRUDPanel.getInstrumentList().getSelectedValue());
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
	
	public void resetFields(){
		CRUDPanel.getGarageNameTextField().setText("");
		CRUDPanel.getGarageEmailTextField().setText("");
		CRUDPanel.getGarageNumberTextField().setText("");
		CRUDPanel.getGaragePaidUntil().setDate(null);
		
		CRUDPanel.getInstrumentList().clearSelection();
		CRUDPanel.getSerialNumTextField().setText("");
		CRUDPanel.getCheckDate().setDate(null);
		
		CRUDPanel.getCheckStatusComboBox().removeAllItems();
	}
	
	public void populateGarageFields() {
		if(CRUDPanel.getGaragesList().getSelectedValue() != null) {
			CRUDPanel.getGarageNameTextField().setText(CRUDPanel.getGaragesList().getSelectedValue().getGarageName());
			CRUDPanel.getGarageEmailTextField().setText(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getEmailAddress());
			CRUDPanel.getGarageNumberTextField().setText(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getTelephoneNum());
			CRUDPanel.getGaragePaidUntil().setDate(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getPaidUntil());
		}
	}

	public void addGarage(){
		JFrame garageFormFrame = new JFrame();
		garageFormFrame.add(garageFormPanelManager.getGarageFormPanel());
		garageFormFrame.setBounds(100, 100, 640, 360);
		garageFormFrame.setVisible(true);
	}

	public void removeGarage(Garage garage){
		
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

	public void changeGarageNameValue(String garageName){

	}

	public void changeGarageEmailValue(String emailAddress){

	}

	public void changeGaragePhoneNumber(String phoneNumber){

	}

	public void changePaidUntil(Date paidUntil){
		
	}

	public void changeCalibrationDate(Date calibrationDate){

	}

	public void populateInstrumentList(ArrayList<Instrument> instruments){
		DefaultListModel<Instrument> list = new DefaultListModel<Instrument>();
		for(int i=0; i<instruments.size(); i++) {
			list.addElement(instruments.get(i));
		}
		CRUDPanel.setInstrumentList(list);
	}

	public void addInstrument(){
		JFrame garageFormFrame = new JFrame();
		garageFormFrame.add(instrumentFormPanelManager.getInstrumentFormPanel());
		garageFormFrame.setBounds(100, 100, 640, 360);
		garageFormFrame.setVisible(true);
	}

	public void removeInstrument(Instrument instrument){
		
		int confirm = JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to delete this garage?", "Please select", JOptionPane.YES_NO_OPTION);
			
		if(confirm == JOptionPane.YES_OPTION) {	
			boolean removed = connection.removeInstrument(instrument.getInstrumentID());
			
			if(removed) {
				
				CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getInstrumentList().remove(instrument);
				
				DefaultListModel<Instrument> instrumentsList = (DefaultListModel<Instrument>) CRUDPanel.getInstrumentList().getModel();
				CRUDPanel.getInstrumentList().clearSelection();
				instrumentsList.removeElement(instrument);
				CRUDPanel.setInstrumentList(instrumentsList);
			}
		}
	}

	public void saveChanges(){

	}
}

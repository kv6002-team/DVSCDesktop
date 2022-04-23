package guimanagers;

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
				
				if(CRUDPanel.getInstrumentList().getSelectedValue() != null || CRUDPanel.getInstrumentList().getSelectedIndex() != -1) {
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
	
		CRUDPanel.getDiscardChangesButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				discardChanges();
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
		CRUDPanel.getGarageEmailTextField().setText("");
		CRUDPanel.getGarageNumberTextField().setText("");
		CRUDPanel.getGaragePaidUntil().setDate(null);	
	}
	
	
	public void populateGarageFields() {
		if(CRUDPanel.getGaragesList().getSelectedValue() != null) {
			CRUDPanel.getGarageNameTextField().setText(CRUDPanel.getGaragesList().getSelectedValue().getGarageName());
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
		DefaultListModel<Instrument> list = new DefaultListModel<Instrument>();
		for(int i=0; i<instruments.size(); i++) {
			list.addElement(instruments.get(i));
		}
		CRUDPanel.setInstrumentList(list);
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
				
				CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getInstrumentList().remove(instrument);
				
				DefaultListModel<Instrument> instrumentsList = (DefaultListModel<Instrument>) CRUDPanel.getInstrumentList().getModel();
				CRUDPanel.getInstrumentList().clearSelection();
				instrumentsList.removeElement(instrument);
				CRUDPanel.setInstrumentList(instrumentsList);
			}
		}
	}

	
	public void updateInstrument(Instrument instrument) {
		
		if(CRUDPanel.getCheckStatusComboBox().getSelectedItem() != null) {
			int i = CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getInstrumentList().indexOf(instrument);
			
			instrument.setInstrumentName(CRUDPanel.getInstrumentNameTextField().getText());
			instrument.setCheckDate(CRUDPanel.getCheckDate().getDate());
			instrument.setStatusExpiryDate(CRUDPanel.getStatusExpiryDate().getDate());
			instrument.setCheckStatus(CheckStatus.of(CRUDPanel.getCheckStatusComboBox().getSelectedItem().toString()));
			
			CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getInstrumentList().set(i, instrument);
			
		}
	}

	public void discardChanges() {
		//CRUDPanel.getInstrumentList().clearSelection();
		resetInstrumentFields();
		GarageInfo tempGarageInfo = connection.getGarage(CRUDPanel.getGaragesList().getSelectedValue().getGarageID());
//		System.out.println(CRUDPanel.getGaragesList().getSelectedValue().getOGGarageInfo());
//		System.out.println(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo());
//		
		CRUDPanel.getGaragesList().getSelectedValue().setGarageInfo(tempGarageInfo);
		populateInstrumentList(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getInstrumentList());
		populateGarageFields();
	}
}

package guimanagers;

import gui.CRUDPanel;
import gui.Wrapper;
import domain.Garage;
import domain.Instrument;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
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
	private GarageFormPanelManager GarageFormPanelManager = new GarageFormPanelManager(this);
	
	ArrayList<Garage> garages = new ArrayList<Garage>();
	
	private Connection connection = new Connection(); 
	
	
	
	public CRUDPanelManager(Wrapper wrapper){
		
		ArrayList<Garage> allGarages = connection.getAllGarages();
		
		wrapper.getTabbedPane().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				populateGarageList(allGarages);
			}
		});
		
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
				
					if(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getInstrumentList() != null) {
						populateInstrumentList(CRUDPanel.getGaragesList().getSelectedValue().getGarageInfo().getInstrumentList());
					}
				}
				
			}
		});
		
		CRUDPanel.getInstrumentList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if(e.getValueIsAdjusting()) {
					return;
				}
				
				if(CRUDPanel.getInstrumentList().getSelectedValue() != null) { 
					CRUDPanel.getSerialNumTextField().setText(CRUDPanel.getInstrumentList().getSelectedValue().getSerialNum());
					CRUDPanel.getCalibrationDate().setDate(CRUDPanel.getInstrumentList().getSelectedValue().getStatusExpiryDate());
				}	
			}
		});
		
		CRUDPanel.getAddGarageButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addGarage();
			}
		});
		
		CRUDPanel.getDeleteGarageButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeGarage(CRUDPanel.getGaragesList().getSelectedValue());
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
		CRUDPanel.getCalibrationDate().setDate(null);
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
		garageFormFrame.add(GarageFormPanelManager.getGarageFormPanel());
		garageFormFrame.setBounds(100, 100, 640, 360);
		garageFormFrame.setVisible(true);
		
	}

	public void removeGarage(Garage garage){
		//connection.removeGarage(garage);
		
		DefaultListModel<Garage> garagesList = (DefaultListModel<Garage>) CRUDPanel.getGaragesList().getModel();
		CRUDPanel.getGaragesList().clearSelection();
		garagesList.removeElement(garage);
		CRUDPanel.getGaragesList().setModel(garagesList);
		
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

	public void addInstrument(Instrument instrument){

	}

	public void removeInstrument(Instrument instrument){

	}

	public void saveChanges(){

	}
}

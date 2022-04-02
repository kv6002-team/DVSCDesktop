package guimanagers;

import gui.CRUDPanel;
import gui.Wrapper;
import domain.Garage;
import domain.Instrument;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
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
	ArrayList<Garage> garages = new ArrayList<Garage>();

	Wrapper wrapper;
	private Connection connection = new Connection(); 
	
	
	
	public CRUDPanelManager(Wrapper wrapper){
		
		this.wrapper = wrapper;
		
		ArrayList<Garage> allGarages = connection.getAllGarages();
		
		wrapper.getTabbedPane().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				populateGarageList(allGarages);
			}
		});
		
		CRUDPanel.getGaragesList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if(e.getValueIsAdjusting()){
					return;
				}
				
				Garage tempGarage = CRUDPanel.getGaragesList().getSelectedValue();
				System.out.println(tempGarage.getGarageID());
				CRUDPanel.getGaragesList().getSelectedValue().addGarageInfo();
			}
		});
		
		
	}
	
	public CRUDPanel getCRUDPanel(){
		return CRUDPanel;
	}

	public void populateGarageList(ArrayList<Garage> garages){
		
		DefaultListModel<Garage> list = new DefaultListModel<Garage>();
		
		for(int i=0; i<garages.size(); i++){
			list.addElement((garages.get(i)));
		}
		
		CRUDPanel.setGarageList(list);
		
	}

	public void addGarage(Garage garage){
	
	}

	public void removeGarage(int index){

	}

	public void changeGarageNameValue(String garageName){

	}

	public void changeGarageEmailValue(String emailAddress){

	}

	public void changeGaragePhoneNumber(String phoneNumber){

	}

	public void changeYearPaid(Boolean yearPaid){
		
	}

	public void changeCalibrationDate(Date calibrationDate){

	}

	public void populateInstrumentList(String[] instrumentList){

	}

	public void addInstrument(Instrument instrument){

	}

	public void removeInstrument(int index){

	}

	public void saveChanges(){

	}
}

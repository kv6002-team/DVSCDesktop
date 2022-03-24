package guimanagers;

import gui.CRUDPanel;
import domain.Garage;
import domain.Instrument;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * 
 * @author Callum
 *
 */

public class CRUDPanelManager {
	
	private CRUDPanel CRUDPanel = new CRUDPanel();
	ArrayList<Garage> garages = new ArrayList<Garage>();
	
	
	
	
	
	public CRUDPanelManager() {

		
		
		garages.add(new Garage("garage 1", 1));
		garages.add(new Garage("garage 2", 2));
		garages.add(new Garage("garage 3", 3));
		
		CRUDPanel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				populateGarageList(garages);
			}
		});
		
		
		
	}
	
	public CRUDPanel getCRUDPanel(){
		return CRUDPanel;
	}

	public void populateGarageList(ArrayList<Garage> garages){
		
		DefaultListModel<String> list = new DefaultListModel();
		
		for(int i=0; i<garages.size(); i++){
			list.addElement((garages.get(i).getGarageName()));
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

//	public void addInstrument(Instrument instrument){
//
//	}

	public void removeInstrument(int index){

	}

	public void saveChanges(){

	}
}

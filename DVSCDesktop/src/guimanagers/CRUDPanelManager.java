package guimanagers;

import gui.CRUDPanel;
import gui.Wrapper;
import domain.Garage;
import domain.Instrument;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
		System.out.print(garages);
		
		wrapper.getTabbedPane().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				populateGarageList(allGarages);
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

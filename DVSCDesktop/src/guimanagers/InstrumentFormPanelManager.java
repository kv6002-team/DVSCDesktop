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


public class InstrumentFormPanelManager{
	
	InstrumentFormPanel instrumentFormPanel = new InstrumentFormPanel();;
	Connection connection = new Connection();
	
	public InstrumentFormPanelManager(CRUDPanelManager CRUDPanelManager) {
		
		instrumentFormPanel.getConfirmButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean valid = validateInputs();
				
				if(valid) {
					Instrument newInstrument = new Instrument(
							instrumentFormPanel.getInstrumentName().getText(),
							instrumentFormPanel.getSerialNumber().getText(),
							instrumentFormPanel.getCheckDate().getDate(),
							instrumentFormPanel.getStatusExpiryDate().getDate(),
							CheckStatus.UNCHECKED
							);
					
					System.out.println(newInstrument);
	
					int id = connection.addInstrument(
							CRUDPanelManager.getCRUDPanel().getGaragesList().getSelectedValue(),
							newInstrument
							);
					
					if(id == -1) {
						AlertDialog x = new AlertDialog();
						x.run("Instrument creation failed. Please try again.");
					}
					else {
						
						newInstrument.setInstrumentID(id);	
						
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
	
	boolean validateInputs() {
		
		boolean valid = true;
		
		boolean missingFields = false;
		boolean invalidSerialNumber = false;
		boolean invalidCheckDate = false;
		boolean invalidStatusExpiryDate = false;
		
		if(
				instrumentFormPanel.getInstrumentName().getText().equals("") ||
				instrumentFormPanel.getSerialNumber().getText().equals("") ||
				instrumentFormPanel.getCheckDate().getDate() == null ||
				instrumentFormPanel.getStatusExpiryDate().getDate() == null
		){
			missingFields = true;
		}
		
		if(!instrumentFormPanel.getSerialNumber().getText().matches("[A-Za-z0-9]+")) {
			invalidSerialNumber = true;
		}
		
		if(
				instrumentFormPanel.getCheckDate().getDate() != null && 
				instrumentFormPanel.getCheckDate().getDate().before(new Date(System.currentTimeMillis() - 86400000)) 
		) {
			invalidCheckDate = true;
		}
		
		if(
				instrumentFormPanel.getStatusExpiryDate().getDate() != null &&
				instrumentFormPanel.getStatusExpiryDate().getDate().before(new Date())
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
			if(invalidSerialNumber || invalidCheckDate || invalidStatusExpiryDate) {
				if(invalidSerialNumber) {
					errorMsg += "Invalid Serial Number\n";
				}
				if(invalidCheckDate) {
					errorMsg += "Invalid Check Date\n";
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
	
	void clearInputs() {
		instrumentFormPanel.getInstrumentName().setText("");
		instrumentFormPanel.getSerialNumber().setText("");
		instrumentFormPanel.getCheckDate().setDate(null);
		instrumentFormPanel.getStatusExpiryDate().setDate(null);
	}
	
	InstrumentFormPanel getInstrumentFormPanel() {
		return this.instrumentFormPanel;
	}
	
	
	
}

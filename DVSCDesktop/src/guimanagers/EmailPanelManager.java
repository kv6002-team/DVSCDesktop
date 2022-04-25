package guimanagers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import connection.Connection;
import domain.Garage;
import gui.EmailPanel;
import gui.Wrapper;

/**
 * 
 * @author Liam
 *
 */

public class EmailPanelManager {
	private EmailPanel EmailPanel = new EmailPanel();
	private Connection connection = new Connection(); 

	public EmailPanel getEmailPanel() {
		return EmailPanel;
	}

	public void getGarageList(ArrayList<Garage> allGarages) {
		//Function to allow for a query to the DB to be made, take all Garages
		//And display them in order within the "Garage Emails" list
		DefaultListModel<Garage> garageList = new DefaultListModel<Garage>();
		for(int i = 0; i < allGarages.size(); i++) {
			garageList.addElement(allGarages.get(i));
			//Get all garages from the DB, store it within the garageList list
		}
		EmailPanel.setListOfGarages(garageList);
	}

	private ArrayList<Integer> getGarageIDs() {
		//Function to extract each Approved Garage's ID and send it to the
		//"sendEmailList" in Connection.Java
		ArrayList<Integer> idList = new ArrayList<Integer>();
		int totalElements = EmailPanel.getApprovedEmails().getModel().getSize();
		for (int i = 0; i < totalElements; i++) {
			idList.add(
				EmailPanel
				.getApprovedEmails()
				.getModel()
				.getElementAt(i)
				.getGarageID()
			);
		}
		return idList;
	}
	
	private void sendListToServer() {
		ArrayList<Integer> emailIDs = getGarageIDs();
		connection.sendEmailList(emailIDs);
	}
	
	public EmailPanelManager(Wrapper wrapper) {
		
		ArrayList<Garage> allGarages = connection.getAllGarages();

		wrapper.getTabbedPane().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				getGarageList(allGarages);
			}
		});
		
		EmailPanel.getMoveAll().addActionListener(new ActionListener() {
			//Moves all elements within the Garage Emails list, places them within the Approved Emails list
			//Also removes all moved elements from the Garage Emails List once finished
			public void actionPerformed(ActionEvent e) {
				int totalElements = EmailPanel.getGarageEmails().getModel().getSize();
				//Get the total list elements of the Garage Emails list
				for(int i = 0; i < totalElements; i++) {
					((DefaultListModel<Garage>) 
							EmailPanel
							.getApprovedEmails()
							.getModel())
							.addElement(((DefaultListModel<Garage>) 
							EmailPanel
							.getGarageEmails()
							.getModel())
							.getElementAt(i)
					);
					//For each entry in the Garage Emails list, add this entry to the Approved Emails
				}
				((DefaultListModel<Garage>)EmailPanel.getGarageEmails().getModel()).clear();
				//Remove all elements from the Garage Emails list
			}
		});
		
		EmailPanel.getMoveSelected().addActionListener(new ActionListener() {
			//Moves all selected elements from the Garage Emails list, places them within the Approved Emails list
			//Then removes selected elements from the Garage Emails list
			public void actionPerformed(ActionEvent e) {
				int[] selectedElements = 
						EmailPanel
						.getGarageEmails()
						.getSelectedIndices();
				//Get all selected list elements from the Garage Emails list
				for(int i=0; i < selectedElements.length; i++) {
					((DefaultListModel<Garage>) 
							EmailPanel
							.getApprovedEmails()
							.getModel())
							.addElement(((DefaultListModel<Garage>) 
							EmailPanel
							.getGarageEmails()
							.getModel())
							.getElementAt(selectedElements[i])
					);
					//Move all selected Garage Emails and place them in the Approved Emails
				}
				for (int i = selectedElements.length -1; i >= 0; i--) {
					((DefaultListModel<Garage>)
							EmailPanel
							.getGarageEmails()
							.getModel())
							.remove(selectedElements[i]);
					//Go backwards through the Garage Emails list, remove all selected elements
				}
			}
		});
		
		EmailPanel.getRemoveSelected().addActionListener(new ActionListener() {
			//Moves all selected list elements from the Approved Emails list, places them within the Garage Emails list
			//Removes selected elements from the Approved Emails list
			public void actionPerformed(ActionEvent e) {
				int[] selectedElements = 
						EmailPanel
						.getApprovedEmails()
						.getSelectedIndices();
				//Get all selected list elements from the Approved Emails list
				for(int i=0; i < selectedElements.length; i++) {
					((DefaultListModel<Garage>) 
							EmailPanel
							.getGarageEmails()
							.getModel())
							.addElement(((DefaultListModel<Garage>) 
							EmailPanel
							.getApprovedEmails()
							.getModel())
							.getElementAt(selectedElements[i])
					);
					//Move all selected Approved Emails and place them in the Garage Emails
				}
				for (int i = selectedElements.length -1; i >= 0; i--) {
					((DefaultListModel<Garage>)
							EmailPanel
							.getApprovedEmails()
							.getModel())
							.remove(selectedElements[i]);
					//Remove the selected elements from the Approved Emails list
				}
			}
		});
		
		EmailPanel.getRemoveAll().addActionListener(new ActionListener() {
			//Moves all list elements from the Approved Emails list, places them within the Garage Emails list
			//Removes all elements from the Approved Emails list
			public void actionPerformed(ActionEvent e) {
				int totalElements = 
						EmailPanel
						.getApprovedEmails()
						.getModel()
						.getSize();
				//Get the total list elements of the Approved Emails list
				for(int i = 0; i < totalElements; i++) {
					((DefaultListModel<Garage>) 
							EmailPanel
							.getGarageEmails()
							.getModel())
							.addElement(((DefaultListModel<Garage>) 
							EmailPanel
							.getApprovedEmails()
							.getModel())
							.getElementAt(i)
					);
					//For each entry in the Approved Emails list, add this entry to the Garage Emails
				}
				((DefaultListModel<Garage>)
						EmailPanel
						.getApprovedEmails()
						.getModel())
						.clear();
				//Remove all elements from the Approved Emails list
			}
		});
		
		EmailPanel.getApprove().addActionListener(new ActionListener() {
			//Creates JFrame, displays two options to either approve or reject the currently selected list elements within the 
			//Approved Email List
			public void actionPerformed(ActionEvent e) {
				//Call function to send selected emails to backend for processing
				//And sending to Email API
				sendListToServer();
			}
		});
		
		EmailPanel.getTempApproval().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getGarageList(allGarages);
			}
		});
	}
		
}
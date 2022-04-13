package guimanagers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import domain.Garage;
import gui.EmailPanel;
import utils.Console;
import javax.swing.AbstractListModel;

/**
 * 
 * @author Liam
 *
 */

public class EmailPanelManager {
	private EmailPanel EmailPanel = new EmailPanel();
	
	public EmailPanel getEmailPanel() {
		return EmailPanel;
	}
	
	public EmailPanelManager() {
		EmailPanel.getMoveAll().addActionListener(new ActionListener() {
			//Moves all elements within the Garage Emails list, places them within the Approved Emails list
			//Also removes all moved elements from the Garage Emails List once finished
			public void actionPerformed(ActionEvent e) {
				int totalElements = EmailPanel.getGarageEmails().getModel().getSize();
				for(int i = 0; i < totalElements; i++) {
					((DefaultListModel<Garage>) EmailPanel.getApprovedEmails().getModel()).addElement(
						((DefaultListModel<Garage>) EmailPanel.getGarageEmails().getModel()).getElementAt(i)
					);
				}
				((DefaultListModel<Garage>)EmailPanel.getGarageEmails().getModel()).clear();
			}
		});
		
		EmailPanel.getMoveSelected().addActionListener(new ActionListener() {
			//Moves all selected elements from the Garage Emails list, places them within the Approved Emails list
			//Then removes selected elements from the Garage Emails list
			public void actionPerformed(ActionEvent e) {
				int[] selectedElements = EmailPanel.getGarageEmails().getSelectedIndices();
				for(int i=0; i < selectedElements.length; i++) {
					((DefaultListModel<Garage>) EmailPanel.getApprovedEmails().getModel()).addElement(
						((DefaultListModel<Garage>) EmailPanel.getGarageEmails().getModel()).getElementAt(selectedElements[i])
					);
				}
				for (int i = selectedElements.length -1; i >= 0; i--) {
					((DefaultListModel<Garage>)EmailPanel.getGarageEmails().getModel()).remove(selectedElements[i]);
				}
			}
		});
		
		EmailPanel.getRemoveSelected().addActionListener(new ActionListener() {
			//Moves all selected list elements from the Approved Emails list, places them within the Garage Emails list
			//Removes selected elements from the Approved Emails list
			public void actionPerformed(ActionEvent e) {
				int[] selectedElements = EmailPanel.getApprovedEmails().getSelectedIndices();
				for(int i=0; i < selectedElements.length; i++) {
					((DefaultListModel<Garage>) EmailPanel.getGarageEmails().getModel()).addElement(
						((DefaultListModel<Garage>) EmailPanel.getApprovedEmails().getModel()).getElementAt(selectedElements[i])
					);
				}
				for (int i = selectedElements.length -1; i >= 0; i--) {
					((DefaultListModel<Garage>)EmailPanel.getApprovedEmails().getModel()).remove(selectedElements[i]);
				}
			}
		});
		
		EmailPanel.getRemoveAll().addActionListener(new ActionListener() {
			//Moves all list elements from the Approved Emails list, places them within the Garage Emails list
			//Removes all elements from the Approved Emails list
			public void actionPerformed(ActionEvent e) {
				int totalElements = EmailPanel.getApprovedEmails().getModel().getSize();
				for(int i = 0; i < totalElements; i++) {
					((DefaultListModel<Garage>) EmailPanel.getGarageEmails().getModel()).addElement(
						((DefaultListModel<Garage>) EmailPanel.getApprovedEmails().getModel()).getElementAt(i)
					);
				}
				((DefaultListModel<Garage>)EmailPanel.getApprovedEmails().getModel()).clear();
			}
		});
		
		EmailPanel.getApprove().addActionListener(new ActionListener() {
			//Creates JFrame, displays two options to either approve or reject the currently selected list elements within the 
			//Approved Email List
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
		
}
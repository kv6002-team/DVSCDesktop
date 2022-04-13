package guimanagers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import domain.Email;
import gui.EmailPanel;

/**
 * 
 * @author Liam
 *
 */

public class EmailPanelManager {
	private static EmailPanel EmailPanel = new EmailPanel();
	public static EmailPanel getEmailPanel() {
		return EmailPanel;
	}
	ArrayList<Email> unapprovedEmails = new ArrayList<Email>();
	
	public EmailPanelManager() {
		unapprovedEmails.add(new Email(1, null));
		unapprovedEmails.add(new Email(2, null));
		unapprovedEmails.add(new Email(3, null));
		
		
		
		EmailPanel.getMoveAll().addActionListener(new ActionListener() {
			//Moves all elements within the Garage Emails list, places them within the Approved Emails list
			//Also removes all moved elements from the Garage Emails List once finished
			public void actionPerformed(ActionEvent e) {
				int totalElements = EmailPanel.getGarageEmails().getModel().getSize();
				for(int i=0; i < totalElements; i++) {
					((DefaultListModel)EmailPanel.getApprovedEmails().getModel()).addElement(i);
					((DefaultListModel)EmailPanel.getGarageEmails().getModel()).remove(i);
				}
			}
		});
		
		EmailPanel.getMoveSelected().addActionListener(new ActionListener(){
			//Moves all selected elements from the Garage Emails list, places them within the Approved Emails list
			//Then removes selected elements from the Garage Emails list
			public void actionPerformed(ActionEvent e) {
				int[] selectedElements = EmailPanel.getGarageEmails().getSelectedIndices();
				for(int i=0; i < selectedElements.length; i++) {
				      Object listElements = EmailPanel.getGarageEmails().getComponent(selectedElements[i]);
				      ((DefaultListModel)EmailPanel.getApprovedEmails().getModel()).addElement(listElements);
				      ((DefaultListModel)EmailPanel.getGarageEmails().getModel()).remove(selectedElements[i]);
				}
			}
		});
		
		EmailPanel.getRemoveSelected().addActionListener(new ActionListener() {
			//Moves all selected list elements from the Approved Emails list, places them within the Garage Emails list
			//Removes selected elements from the Approved Emails list
			public void actionPerformed(ActionEvent e) {
				int[] selectedElements = EmailPanel.getApprovedEmails().getSelectedIndices();
				for(int i=0; i < selectedElements.length; i++) {
				      Object listElements = EmailPanel.getApprovedEmails().getComponent(selectedElements[i]);
				      ((DefaultListModel)EmailPanel.getGarageEmails().getModel()).addElement(listElements);
				      ((DefaultListModel)EmailPanel.getApprovedEmails().getModel()).remove(selectedElements[i]);
				}
			}
		});
		
		EmailPanel.getRemoveAll().addActionListener(new ActionListener() {
			//Moves all list elements from the Approved Emails list, places them within the Garage Emails list
			//Removes all elements from the Approved Emails list
			public void actionPerformed(ActionEvent e) {
				int totalElements = EmailPanel.getApprovedEmails().getModel().getSize();
				for(int i=0; i < totalElements; i++) {
					((DefaultListModel)EmailPanel.getGarageEmails().getModel()).addElement(i);
					((DefaultListModel)EmailPanel.getApprovedEmails().getModel()).remove(i);
				}
			}
		});
		
		EmailPanel.getApprove().addActionListener(new ActionListener() {
			//Creates JFrame, displays two options to either approve or reject the currently selected list elements within the 
			//Approved Email List
			public void actionPerformed(ActionEvent e) {
				 JFrame jFrame = new JFrame();
				 String[] buttons = {"Approve", "Reject"};
				    int result = JOptionPane.showOptionDialog(null, "Please confirm your choice", "Confirmation",
				        JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[2]);
			        if (result == 0)
			            ApproveEmails();
			        else if (result == 1)
			            System.out.println("Approval Cancelled");
			}
		});
	}
	
	public void ApproveEmails() {
		//Function takes all list elements within Approved Emails, places them in a new list called SelectedApprovedEmails
		//All elements within Approved Emails is then removed
		int totalElements = EmailPanel.getApprovedEmails().getModel().getSize();
		for(int i = 0; i < totalElements; i++) {
			((DefaultListModel)EmailPanel.getSelectedApprovedEmails().getModel()).addElement(i);
			((DefaultListModel)EmailPanel.getApprovedEmails().getModel()).remove(i);
		}
		JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, "Emails approved");  
	}
	
	public void populateUnapprovedEmails(ArrayList<Email> emails) {
		//Populates the Garage Emails list 
		for(int i = 0; i < emails.size(); i++) {
			EmailPanel.setUnapprovedEmailList(emails.get(i));
		}
	}	
}
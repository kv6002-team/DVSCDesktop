package guimanagers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import domain.Email;
import gui.EmailPanel;

public class EmailPanelManager {
	private EmailPanel EmailPanel = new EmailPanel();
	ArrayList<Email> unapprovedEmails = new ArrayList<Email>();
	
	public EmailPanelManager() {
		unapprovedEmails.add(new Email(1, null));
		unapprovedEmails.add(new Email(2, null));
		unapprovedEmails.add(new Email(3, null));
		
		EmailPanel.getMoveAll().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int totalElements = EmailPanel.getGarageEmails().getModel().getSize();
				for(int i=0; i < totalElements; i++) {
					((DefaultListModel)EmailPanel.getApprovedEmails().getModel()).addElement(i);
				}
			}
		});
		
		EmailPanel.getMoveSelected().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int[] selectedElements = EmailPanel.getGarageEmails().getSelectedIndices();
				for(int i=0; i < selectedElements.length; i++) {
				      Object listElements = EmailPanel.getGarageEmails().getComponent(selectedElements[i]);
				      ((DefaultListModel)EmailPanel.getApprovedEmails().getModel()).addElement(listElements);
				}
			}
		});
		
		EmailPanel.getApprove().addActionListener(new ActionListener() {
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
	
	public void populateUnapprovedEmails(ArrayList<Email> emails) {
		for(int i = 0; i < emails.size(); i++) {
			EmailPanel.setUnapprovedEmailList(emails.get(i));
		}
	}
	
	public void ApproveEmails() {
		JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, "Emails approved");  
	}
	
}
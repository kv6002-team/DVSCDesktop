package guimanagers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import connection.Connection;
import gui.AlertDialog;
import gui.AuthenticationPanel;
import gui.NoConnectionDialog;
import gui.Wrapper;
import security.AuthenticationManager;
import security.Sanitiser;

/**
 * 
 * @author Scrub
 *
 */
public class AuthenticationPanelManager {
	private AuthenticationPanel authenticationPanel = new AuthenticationPanel();
	private Connection connection = new Connection();
	public AuthenticationPanelManager(JFrame mainWindow) {
		addAuthenticationEventListener(mainWindow);
		addResetEventListener();
	}
	
	public AuthenticationPanel getPanel() {
		return authenticationPanel;
	}
	
	
	private void addAuthenticationEventListener(JFrame mainWindow) { //Create a event listening for the login button
		authenticationPanel.getAuthenticationButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(connection.ping()) {	//Ensures a connection to the server can be made
					if(Sanitiser.containsColon(authenticationPanel.getUsernameField())) { //Ensures the input does not contain a colon
						AlertDialog x = new AlertDialog(); // Creates an alertdialog
						x.run("Username cannot contain a colon"); // Runs alert dialog with message
						authenticationPanel.clearFields(); // Clear input fields
					}else if(AuthenticationManager.authenticate(Sanitiser.trim(authenticationPanel.getUsernameField()), authenticationPanel.getPasswordField())) { // Check if authentication is valid
						mainWindow.setContentPane(new Wrapper()); //sets the content pane to a new wrapper object
						mainWindow.revalidate(); // revalidates main window to show wrapper
					}
				} else {
					NoConnectionDialog x = new NoConnectionDialog(); // Error that is shown if there is no connection
					x.run();
				}
				authenticationPanel.clearFields(); // Clear fields if no connection
			}
		});
	}
	
	private void addResetEventListener() {
		authenticationPanel.getResetButton().addActionListener(new ActionListener() { // Add event listener for password reset button
			public void actionPerformed(ActionEvent e) {
				AlertDialog x = new AlertDialog(); // Create an alertdialog
				if(authenticationPanel.getUsernameField().length() > 0) { // Make sure there is something in the username field
					 if(AuthenticationManager.resetPassword(Sanitiser.trim(authenticationPanel.getUsernameField()))) { // Send request to server to reset password
						 x.run("An email containing a password reset link has been sent to the inbox of the account associated with this user."); // If password reset is a success
					 }else {
						 x.run("An Error occured while trying to send a password reset email, please contact a system administrator"); // If password reset failed
					 }
				}else {
					x.run("Provide a username to send a password reset email to!"); // if username field is blank
				}

			}
		});
	}
}

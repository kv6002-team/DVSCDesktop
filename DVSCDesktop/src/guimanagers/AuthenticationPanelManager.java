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
	
	
	private void addAuthenticationEventListener(JFrame mainWindow) {
		authenticationPanel.getAuthenticationButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(connection.ping()) {
					if(Sanitiser.containsColon(authenticationPanel.getUsernameField())) {
						AlertDialog x = new AlertDialog();
						x.run("Username cannot contain a colon");
						authenticationPanel.clearFields();
					}else if(AuthenticationManager.authenticate(Sanitiser.trim(authenticationPanel.getUsernameField()), authenticationPanel.getPasswordField())) {
						mainWindow.setContentPane(new Wrapper());
						mainWindow.revalidate();
					}
				} else {
					NoConnectionDialog x = new NoConnectionDialog();
					x.run();
				}
				authenticationPanel.clearFields();
			}
		});
	}
	
	private void addResetEventListener() {
		authenticationPanel.getResetButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlertDialog x = new AlertDialog();
				if(authenticationPanel.getUsernameField().length() > 0) {
					 if(AuthenticationManager.resetPassword(Sanitiser.trim(authenticationPanel.getUsernameField()))) {
						 x.run("An email containing a password reset link has been sent to the inbox of the account associated with this user.");
					 }else {
						 x.run("An Error occured while trying to send a password reset email, please contact a system administrator");
					 }
				}else {
					x.run("Provide a username to send a password reset email to!");
				}

			}
		});
	}
}

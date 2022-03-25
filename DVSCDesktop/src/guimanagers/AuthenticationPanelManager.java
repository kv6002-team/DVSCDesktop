package guimanagers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import connection.Connection;
import gui.AuthenticationPanel;
import gui.NoConnectionDialog;
import gui.Wrapper;
import security.AuthenticationManager;
import security.SecurityManager;
import utils.Console;

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
	}
	
	public AuthenticationPanel getPanel() {
		return authenticationPanel;
	}
	
	
	private void addAuthenticationEventListener(JFrame mainWindow) {
		authenticationPanel.getAuthenticationButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(connection.ping()) {					
					if(AuthenticationManager.authenticate(authenticationPanel.getUsernameField(), authenticationPanel.getPasswordField())) {
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
}

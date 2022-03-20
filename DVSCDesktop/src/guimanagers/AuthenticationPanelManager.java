package guimanagers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import gui.AuthenticationPanel;
import gui.NoConnectionDialog;
import gui.Wrapper;
import security.AuthenticationManager;
import security.SecurityManager;

/**
 * 
 * @author Scrub
 *
 */
public class AuthenticationPanelManager {
	private AuthenticationPanel authenticationPanel = new AuthenticationPanel();
	
	public AuthenticationPanelManager(JFrame mainWindow) {
		authenticationPanel.getAuthenticationButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(SecurityManager.checkConnection()) {
					authenticationPanel.clearFields();
					if(AuthenticationManager.authenticate(authenticationPanel.getUsernameField(), authenticationPanel.getPasswordField())) {
						mainWindow.setContentPane(new Wrapper());
						mainWindow.revalidate();
					}
				} else {
					NoConnectionDialog x = new NoConnectionDialog();
					x.run();
				}
			}
		});
	}
	
	public AuthenticationPanel getPanel() {
		return authenticationPanel;
	}
}

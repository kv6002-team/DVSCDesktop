package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import guimanagers.AuthenticationPanelManager;
import security.AuthenticationManager;
import security.SecurityManager;
/**
 * 
 * @author Scrub
 *
 */
@SuppressWarnings("serial")
public class ApplicationWindow extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	SecurityManager.testSystemSecurity();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationWindow frame = new ApplicationWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ApplicationWindow() {
		init();
	}
	
	public void init(){
		
		AuthenticationPanelManager authPanelManager = new AuthenticationPanelManager(this);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 576);
		setContentPane(authPanelManager.getPanel());
		
	}
}

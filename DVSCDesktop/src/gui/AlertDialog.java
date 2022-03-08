package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * 
 * @author Scrub
 * Creates a Pop-up dialog box with Alert Presets
 */
public class AlertDialog {
	
	public AlertDialog (String message) {
		run(message);
	}
	private void generateDialog(String message) {
		JOptionPane.showMessageDialog(new JFrame(), message, "Alert" , JOptionPane.WARNING_MESSAGE);
	}
	
	public void run(String message) {
		generateDialog(message);
	}
}
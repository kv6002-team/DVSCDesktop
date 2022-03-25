package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPasswordField;
import javax.swing.JButton;
/**
 * 
 * @author Scrub
 *
 */
@SuppressWarnings("serial")
public class AuthenticationPanel extends JPanel {
	private JTextField txt_UsernameField;
	private JPasswordField pass_PasswordField;
	
	public JButton btn_Authenticate = new JButton("Login");
	public JButton btn_resetPasswd = new JButton("Reset");


	/**
	 * Create the panel.
	 */
	public AuthenticationPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 0;
		add(verticalStrut, gbc_verticalStrut);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 0;
		gbc_horizontalStrut_1.gridy = 1;
		add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		JLabel lbl_Username = new JLabel("Username");
		GridBagConstraints gbc_lbl_Username = new GridBagConstraints();
		gbc_lbl_Username.anchor = GridBagConstraints.EAST;
		gbc_lbl_Username.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Username.gridx = 1;
		gbc_lbl_Username.gridy = 1;
		add(lbl_Username, gbc_lbl_Username);
		
		txt_UsernameField = new JTextField();
		GridBagConstraints gbc_txt_UsernameField = new GridBagConstraints();
		gbc_txt_UsernameField.insets = new Insets(0, 0, 5, 5);
		gbc_txt_UsernameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_UsernameField.gridx = 2;
		gbc_txt_UsernameField.gridy = 1;
		add(txt_UsernameField, gbc_txt_UsernameField);
		txt_UsernameField.setColumns(10);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut.gridx = 3;
		gbc_horizontalStrut.gridy = 1;
		add(horizontalStrut, gbc_horizontalStrut);
		
		JLabel lbl_Password = new JLabel("Password");
		GridBagConstraints gbc_lbl_Password = new GridBagConstraints();
		gbc_lbl_Password.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_Password.anchor = GridBagConstraints.EAST;
		gbc_lbl_Password.gridx = 1;
		gbc_lbl_Password.gridy = 2;
		add(lbl_Password, gbc_lbl_Password);
		
		pass_PasswordField = new JPasswordField();
		GridBagConstraints gbc_pass_PasswordField = new GridBagConstraints();
		gbc_pass_PasswordField.insets = new Insets(0, 0, 5, 5);
		gbc_pass_PasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_pass_PasswordField.gridx = 2;
		gbc_pass_PasswordField.gridy = 2;
		add(pass_PasswordField, gbc_pass_PasswordField);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		panel.add(btn_Authenticate);
		
		panel.add(btn_resetPasswd);

	}
	
	public JButton getAuthenticationButton() {
		return btn_Authenticate;
	}
	
	public String getPasswordField() {
		return new String(pass_PasswordField.getPassword());
	}
	
	public String getUsernameField() {
		return txt_UsernameField.getText();
	}
	
	public void clearFields() {
		pass_PasswordField.setText("");
		txt_UsernameField.setText("");
	}
	
	public JButton getResetButton() {
		return btn_resetPasswd;
	}
}

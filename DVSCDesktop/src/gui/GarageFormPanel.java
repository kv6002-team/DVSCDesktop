package gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;

/**
 * GarageFormPanel UI Class 
 * 
 * @author callu
 *
 */
@SuppressWarnings("serial")
public class GarageFormPanel extends JPanel {
	private JTextField txt_garageName;
	private JTextField txt_contactNum;
	private JTextField txt_contactEmail;
	private JTextField txt_vts;
	private JDateChooser date_paidUntil = new JDateChooser();
	private JButton btn_confirm = new JButton("Add Garage");
	private JTextField txt_ownerName;

	/**
	 * Create the panel.
	 */
	public GarageFormPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{225, 0};
		gridBagLayout.rowHeights = new int[]{1, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel pnl_addGarage = new JPanel();
		GridBagConstraints gbc_pnl_addGarage = new GridBagConstraints();
		gbc_pnl_addGarage.fill = GridBagConstraints.BOTH;
		gbc_pnl_addGarage.gridx = 0;
		gbc_pnl_addGarage.gridy = 0;
		add(pnl_addGarage, gbc_pnl_addGarage);
		GridBagLayout gbl_pnl_addGarage = new GridBagLayout();
		gbl_pnl_addGarage.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnl_addGarage.rowHeights = new int[]{0, 27, 23, 0, 24, 0, 0, 0, 0, 0, 0};
		gbl_pnl_addGarage.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_pnl_addGarage.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnl_addGarage.setLayout(gbl_pnl_addGarage);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_4 = new GridBagConstraints();
		gbc_verticalStrut_4.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_4.gridx = 3;
		gbc_verticalStrut_4.gridy = 0;
		pnl_addGarage.add(verticalStrut_4, gbc_verticalStrut_4);
		
		JLabel lbl_addGarage = new JLabel("Add Garage");
		lbl_addGarage.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_addGarage.setFont(new Font("Tahoma", Font.BOLD, 22));
		GridBagConstraints gbc_lbl_addGarage = new GridBagConstraints();
		gbc_lbl_addGarage.gridwidth = 5;
		gbc_lbl_addGarage.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_addGarage.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_addGarage.anchor = GridBagConstraints.NORTH;
		gbc_lbl_addGarage.gridx = 1;
		gbc_lbl_addGarage.gridy = 1;
		pnl_addGarage.add(lbl_addGarage, gbc_lbl_addGarage);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 0;
		gbc_horizontalStrut_1.gridy = 2;
		pnl_addGarage.add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 3;
		gbc_horizontalStrut.gridy = 2;
		pnl_addGarage.add(horizontalStrut, gbc_horizontalStrut);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_2.gridx = 6;
		gbc_horizontalStrut_2.gridy = 2;
		pnl_addGarage.add(horizontalStrut_2, gbc_horizontalStrut_2);
		
		JLabel lbl_vts = new JLabel("VTS Number");
		GridBagConstraints gbc_lbl_vts = new GridBagConstraints();
		gbc_lbl_vts.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_vts.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_vts.gridx = 1;
		gbc_lbl_vts.gridy = 3;
		pnl_addGarage.add(lbl_vts, gbc_lbl_vts);
		
		txt_vts = new JTextField();
		GridBagConstraints gbc_txt_vts = new GridBagConstraints();
		gbc_txt_vts.insets = new Insets(0, 0, 5, 5);
		gbc_txt_vts.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_vts.gridx = 2;
		gbc_txt_vts.gridy = 3;
		pnl_addGarage.add(txt_vts, gbc_txt_vts);
		txt_vts.setColumns(10);
		
		JLabel lbl_ownerName = new JLabel("Owner Name");
		GridBagConstraints gbc_lbl_ownerName = new GridBagConstraints();
		gbc_lbl_ownerName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_ownerName.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_ownerName.gridx = 4;
		gbc_lbl_ownerName.gridy = 3;
		pnl_addGarage.add(lbl_ownerName, gbc_lbl_ownerName);
		
		txt_ownerName = new JTextField();
		GridBagConstraints gbc_txt_ownerName = new GridBagConstraints();
		gbc_txt_ownerName.insets = new Insets(0, 0, 5, 5);
		gbc_txt_ownerName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_ownerName.gridx = 5;
		gbc_txt_ownerName.gridy = 3;
		pnl_addGarage.add(txt_ownerName, gbc_txt_ownerName);
		txt_ownerName.setColumns(10);
		
		JLabel lbl_garageName = new JLabel("Garage Name");
		GridBagConstraints gbc_lbl_garageName = new GridBagConstraints();
		gbc_lbl_garageName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_garageName.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_garageName.gridx = 1;
		gbc_lbl_garageName.gridy = 5;
		pnl_addGarage.add(lbl_garageName, gbc_lbl_garageName);
		
		txt_garageName = new JTextField();
		GridBagConstraints gbc_txt_garageName = new GridBagConstraints();
		gbc_txt_garageName.insets = new Insets(0, 0, 5, 5);
		gbc_txt_garageName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_garageName.gridx = 2;
		gbc_txt_garageName.gridy = 5;
		pnl_addGarage.add(txt_garageName, gbc_txt_garageName);
		txt_garageName.setColumns(10);
		
		JLabel lbl_contactNum = new JLabel("Contact Number");
		GridBagConstraints gbc_lbl_contactNum = new GridBagConstraints();
		gbc_lbl_contactNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_contactNum.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_contactNum.gridx = 4;
		gbc_lbl_contactNum.gridy = 5;
		pnl_addGarage.add(lbl_contactNum, gbc_lbl_contactNum);
		
		txt_contactNum = new JTextField();
		GridBagConstraints gbc_txt_contactNum = new GridBagConstraints();
		gbc_txt_contactNum.insets = new Insets(0, 0, 5, 5);
		gbc_txt_contactNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_contactNum.gridx = 5;
		gbc_txt_contactNum.gridy = 5;
		pnl_addGarage.add(txt_contactNum, gbc_txt_contactNum);
		txt_contactNum.setColumns(10);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 3;
		gbc_verticalStrut.gridy = 6;
		pnl_addGarage.add(verticalStrut, gbc_verticalStrut);
		
		JLabel lbl_contactEmail = new JLabel("Contact Email");
		GridBagConstraints gbc_lbl_contactEmail = new GridBagConstraints();
		gbc_lbl_contactEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_contactEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_contactEmail.gridx = 1;
		gbc_lbl_contactEmail.gridy = 7;
		pnl_addGarage.add(lbl_contactEmail, gbc_lbl_contactEmail);
		
		txt_contactEmail = new JTextField();
		GridBagConstraints gbc_txt_contactEmail = new GridBagConstraints();
		gbc_txt_contactEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txt_contactEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_contactEmail.gridx = 2;
		gbc_txt_contactEmail.gridy = 7;
		pnl_addGarage.add(txt_contactEmail, gbc_txt_contactEmail);
		txt_contactEmail.setColumns(10);
		
		JLabel lbl_paidUntil = new JLabel("Paid Until");
		GridBagConstraints gbc_lbl_paidUntil = new GridBagConstraints();
		gbc_lbl_paidUntil.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_paidUntil.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_paidUntil.gridx = 4;
		gbc_lbl_paidUntil.gridy = 7;
		pnl_addGarage.add(lbl_paidUntil, gbc_lbl_paidUntil);
		
		GridBagConstraints gbc_date_paidUntil = new GridBagConstraints();
		gbc_date_paidUntil.insets = new Insets(0, 0, 5, 5);
		gbc_date_paidUntil.fill = GridBagConstraints.HORIZONTAL;
		gbc_date_paidUntil.gridx = 5;
		gbc_date_paidUntil.gridy = 7;
		pnl_addGarage.add(date_paidUntil, gbc_date_paidUntil);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 3;
		gbc_verticalStrut_1.gridy = 8;
		pnl_addGarage.add(verticalStrut_1, gbc_verticalStrut_1);
		
		GridBagConstraints gbc_btn_confirm = new GridBagConstraints();
		gbc_btn_confirm.gridwidth = 3;
		gbc_btn_confirm.insets = new Insets(0, 0, 0, 5);
		gbc_btn_confirm.gridx = 2;
		gbc_btn_confirm.gridy = 9;
		pnl_addGarage.add(btn_confirm, gbc_btn_confirm);

	}

	/* GarageFormPanel Getters 
	 * -------------------- */
	
	public JTextField getVTS() {
		return this.txt_vts;
	}
	
	public JTextField getGarageName() {
		return this.txt_garageName;
	}	
	
	public JTextField getOwnerName() {
		return this.txt_ownerName;
	}
	
	public JTextField getContactEmail() {
		return this.txt_contactEmail;
	}
	
	public JTextField getContactNum() {
		return this.txt_contactNum;
	}
	
	public JDateChooser getPaidUntil() {
		return this.date_paidUntil;
	}
	
	public JButton getConfirmButton() {
		return this.btn_confirm;
	} 
}

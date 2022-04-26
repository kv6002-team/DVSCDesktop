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
 * InstrumentFormPanel UI Class. 
 * 
 * @author callu
 *
 */
@SuppressWarnings("serial")
public class InstrumentFormPanel extends JPanel {
	private JTextField txt_serialNumber;
	private JDateChooser date_checkDate = new JDateChooser();
	private JDateChooser date_statusExpiryDate = new JDateChooser();
	private JButton btn_confirm = new JButton("Add Instrument");
	private JTextField txt_instrumentName;

	/**
	 * Create the panel.
	 */
	public InstrumentFormPanel() {
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
		gbl_pnl_addGarage.columnWidths = new int[]{0, 0, 0, 0, 97, 0, 0, 0};
		gbl_pnl_addGarage.rowHeights = new int[]{0, 27, 23, 0, 24, 0, 0, 0, 0};
		gbl_pnl_addGarage.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_pnl_addGarage.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnl_addGarage.setLayout(gbl_pnl_addGarage);
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_4 = new GridBagConstraints();
		gbc_verticalStrut_4.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_4.gridx = 3;
		gbc_verticalStrut_4.gridy = 0;
		pnl_addGarage.add(verticalStrut_4, gbc_verticalStrut_4);
		
		JLabel lbl_addInstrument = new JLabel("Add Instrument");
		lbl_addInstrument.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_addInstrument.setFont(new Font("Tahoma", Font.BOLD, 22));
		GridBagConstraints gbc_lbl_addInstrument = new GridBagConstraints();
		gbc_lbl_addInstrument.gridwidth = 5;
		gbc_lbl_addInstrument.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_addInstrument.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_addInstrument.anchor = GridBagConstraints.NORTH;
		gbc_lbl_addInstrument.gridx = 1;
		gbc_lbl_addInstrument.gridy = 1;
		pnl_addGarage.add(lbl_addInstrument, gbc_lbl_addInstrument);
		
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
		
		JLabel lbl_instrumentName = new JLabel("Instrument Name");
		GridBagConstraints gbc_lbl_instrumentName = new GridBagConstraints();
		gbc_lbl_instrumentName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_instrumentName.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_instrumentName.gridx = 1;
		gbc_lbl_instrumentName.gridy = 3;
		pnl_addGarage.add(lbl_instrumentName, gbc_lbl_instrumentName);
		
		txt_instrumentName = new JTextField();
		GridBagConstraints gbc_txt_instrumentName = new GridBagConstraints();
		gbc_txt_instrumentName.insets = new Insets(0, 0, 5, 5);
		gbc_txt_instrumentName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_instrumentName.gridx = 2;
		gbc_txt_instrumentName.gridy = 3;
		pnl_addGarage.add(txt_instrumentName, gbc_txt_instrumentName);
		txt_instrumentName.setColumns(10);
		
		JLabel lbl_serialNumber = new JLabel("Serial Number");
		GridBagConstraints gbc_lbl_serialNumber = new GridBagConstraints();
		gbc_lbl_serialNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_serialNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_serialNumber.gridx = 4;
		gbc_lbl_serialNumber.gridy = 3;
		pnl_addGarage.add(lbl_serialNumber, gbc_lbl_serialNumber);
		
		txt_serialNumber = new JTextField();
		GridBagConstraints gbc_txt_serialNumber = new GridBagConstraints();
		gbc_txt_serialNumber.insets = new Insets(0, 0, 5, 5);
		gbc_txt_serialNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_serialNumber.gridx = 5;
		gbc_txt_serialNumber.gridy = 3;
		pnl_addGarage.add(txt_serialNumber, gbc_txt_serialNumber);
		txt_serialNumber.setColumns(10);
		
		JLabel lbl_checkDate = new JLabel("Check Date");
		GridBagConstraints gbc_lbl_checkDate = new GridBagConstraints();
		gbc_lbl_checkDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_checkDate.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_checkDate.gridx = 1;
		gbc_lbl_checkDate.gridy = 5;
		pnl_addGarage.add(lbl_checkDate, gbc_lbl_checkDate);
		
		GridBagConstraints gbc_date_checkDate = new GridBagConstraints();
		gbc_date_checkDate.insets = new Insets(0, 0, 5, 5);
		gbc_date_checkDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_date_checkDate.gridx = 2;
		gbc_date_checkDate.gridy = 5;
		pnl_addGarage.add(date_checkDate, gbc_date_checkDate);
		
		JLabel lbl_statusExpiryDate = new JLabel("Status Expiry Date");
		GridBagConstraints gbc_lbl_statusExpiryDate = new GridBagConstraints();
		gbc_lbl_statusExpiryDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_statusExpiryDate.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_statusExpiryDate.gridx = 4;
		gbc_lbl_statusExpiryDate.gridy = 5;
		pnl_addGarage.add(lbl_statusExpiryDate, gbc_lbl_statusExpiryDate);
		
		GridBagConstraints gbc_date_statusExpiryDate = new GridBagConstraints();
		gbc_date_statusExpiryDate.insets = new Insets(0, 0, 5, 5);
		gbc_date_statusExpiryDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_date_statusExpiryDate.gridx = 5;
		gbc_date_statusExpiryDate.gridy = 5;
		pnl_addGarage.add(date_statusExpiryDate, gbc_date_statusExpiryDate);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 3;
		gbc_verticalStrut.gridy = 6;
		pnl_addGarage.add(verticalStrut, gbc_verticalStrut);
		
		
		GridBagConstraints gbc_btn_confirm = new GridBagConstraints();
		gbc_btn_confirm.gridwidth = 3;
		gbc_btn_confirm.insets = new Insets(0, 0, 0, 5);
		gbc_btn_confirm.gridx = 2;
		gbc_btn_confirm.gridy = 7;
		pnl_addGarage.add(btn_confirm, gbc_btn_confirm);

	}
	
	/* InstrumentFormPanel Getters
	 * -------------------------------------------------- */
	
	public JTextField getInstrumentName() {
		return this.txt_instrumentName;
	}
	
	public JTextField getSerialNumber() {
		return this.txt_serialNumber;
	}
	
	public JDateChooser getCheckDate() {
		return this.date_checkDate;
	}
	
	public JDateChooser getStatusExpiryDate() {
		return this.date_statusExpiryDate;
	}
	
	public JButton getConfirmButton() {
		return this.btn_confirm;
	}

}
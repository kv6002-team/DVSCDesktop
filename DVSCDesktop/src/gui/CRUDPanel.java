package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Cursor;
import com.toedter.calendar.JDateChooser;

import domain.Garage;
import domain.Instrument;

import java.awt.Dimension;

public class CRUDPanel extends JPanel {

	public JList<Garage> lst_garages = new JList<Garage>();
	public JTextField txt_garageName = new JTextField();
	public JTextField txt_contactNum = new JTextField();
	public JTextField txt_contactEmail = new JTextField();
	public JDateChooser date_paidUntil = new JDateChooser();
	public JButton btn_deleteGarage = new JButton("Delete Garage");
	public JList<Instrument> lst_instruments = new JList<Instrument>();
	public JButton btn_addInstrument = new JButton("Add Instrument");
	public JTextField txt_serialNum = new JTextField();
	public JDateChooser date_calibrationDate = new JDateChooser();
	public JButton btn_deleteInstrument = new JButton("Delete Instrument");
	public JButton btn_addGarage = new JButton("Add Garage");
	public JButton btn_saveChanges = new JButton("Save Changes");
	
	public static final String tabName = "Garages";
	
	/**
	 * Create the panel.
	 */
	public CRUDPanel() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{138, 260, 0};
		gridBagLayout.rowHeights = new int[]{27, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel pnl_garageList = new JPanel();
		GridBagConstraints gbc_pnl_garageList = new GridBagConstraints();
		gbc_pnl_garageList.fill = GridBagConstraints.BOTH;
		gbc_pnl_garageList.insets = new Insets(5, 5, 5, 5);
		gbc_pnl_garageList.anchor = GridBagConstraints.NORTHWEST;
		gbc_pnl_garageList.gridx = 0;
		gbc_pnl_garageList.gridy = 0;
		add(pnl_garageList, gbc_pnl_garageList);
		GridBagLayout gbl_pnl_garageList = new GridBagLayout();
		gbl_pnl_garageList.columnWidths = new int[]{158, 0};
		gbl_pnl_garageList.rowHeights = new int[]{0, 172, 142, 0};
		gbl_pnl_garageList.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnl_garageList.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		pnl_garageList.setLayout(gbl_pnl_garageList);
		
		JLabel lbl_garages = new JLabel("Garages");
		lbl_garages.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_garages.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_lbl_garages = new GridBagConstraints();
		gbc_lbl_garages.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_garages.weighty = 1.0;
		gbc_lbl_garages.weightx = 1.0;
		gbc_lbl_garages.gridx = 0;
		gbc_lbl_garages.gridy = 0;
		pnl_garageList.add(lbl_garages, gbc_lbl_garages);
		lbl_garages.setLabelFor(pnl_garageList);
		lbl_garages.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_garages.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JScrollPane scrl_garagesList = new JScrollPane();
		GridBagConstraints gbc_scrl_garagesList = new GridBagConstraints();
		gbc_scrl_garagesList.gridheight = 2;
		gbc_scrl_garagesList.fill = GridBagConstraints.BOTH;
		gbc_scrl_garagesList.gridx = 0;
		gbc_scrl_garagesList.gridy = 1;
		pnl_garageList.add(scrl_garagesList, gbc_scrl_garagesList);
	
		lst_garages.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrl_garagesList.setViewportView(lst_garages);
		
		JPanel pnl_garageInfo = new JPanel();
		pnl_garageInfo.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		GridBagConstraints gbc_pnl_garageInfo = new GridBagConstraints();
		gbc_pnl_garageInfo.insets = new Insets(5, 5, 5, 5);
		gbc_pnl_garageInfo.weighty = 1.0;
		gbc_pnl_garageInfo.weightx = 2.0;
		gbc_pnl_garageInfo.fill = GridBagConstraints.BOTH;
		gbc_pnl_garageInfo.gridx = 1;
		gbc_pnl_garageInfo.gridy = 0;
		add(pnl_garageInfo, gbc_pnl_garageInfo);
		GridBagLayout gbl_pnl_garageInfo = new GridBagLayout();
		gbl_pnl_garageInfo.columnWidths = new int[]{-15, 0, 145, 31, 230, 0, 35, 100, 0, 209, 0, 24, 0};
		gbl_pnl_garageInfo.rowHeights = new int[]{72, 62, 60, 81, 0, 0, 0, 0};
		gbl_pnl_garageInfo.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 9.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnl_garageInfo.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		pnl_garageInfo.setLayout(gbl_pnl_garageInfo);
		
		JLabel lbl_garageInfo = new JLabel("Garage Information");
		GridBagConstraints gbc_lbl_garageInfo = new GridBagConstraints();
		gbc_lbl_garageInfo.gridwidth = 10;
		gbc_lbl_garageInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_garageInfo.gridx = 1;
		gbc_lbl_garageInfo.gridy = 0;
		pnl_garageInfo.add(lbl_garageInfo, gbc_lbl_garageInfo);
		lbl_garageInfo.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_garageInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_garageInfo.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		JLabel lbl_garageName = new JLabel("Garage Name");
		lbl_garageName.setPreferredSize(new Dimension(50, 14));
		lbl_garageName.setMinimumSize(new Dimension(30, 14));
		lbl_garageName.setMaximumSize(new Dimension(50, 14));
		GridBagConstraints gbc_lbl_garageName = new GridBagConstraints();
		gbc_lbl_garageName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_garageName.weighty = 1.0;
		gbc_lbl_garageName.weightx = 1.0;
		gbc_lbl_garageName.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_garageName.gridx = 2;
		gbc_lbl_garageName.gridy = 1;
		pnl_garageInfo.add(lbl_garageName, gbc_lbl_garageName);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 3;
		gbc_horizontalStrut.gridy = 1;
		pnl_garageInfo.add(horizontalStrut, gbc_horizontalStrut);
		
		GridBagConstraints gbc_txt_garageName = new GridBagConstraints();
		gbc_txt_garageName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_garageName.insets = new Insets(0, 0, 5, 5);
		gbc_txt_garageName.weighty = 1.0;
		gbc_txt_garageName.weightx = 1.0;
		gbc_txt_garageName.gridx = 4;
		gbc_txt_garageName.gridy = 1;
		txt_garageName.setPreferredSize(new Dimension(30, 20));
		txt_garageName.setMinimumSize(new Dimension(30, 20));
		pnl_garageInfo.add(txt_garageName, gbc_txt_garageName);
		txt_garageName.setColumns(10);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_2.gridx = 6;
		gbc_horizontalStrut_2.gridy = 1;
		pnl_garageInfo.add(horizontalStrut_2, gbc_horizontalStrut_2);
		
		JLabel lbl_contactNum = new JLabel("Contact Number");
		lbl_contactNum.setPreferredSize(new Dimension(50, 14));
		lbl_contactNum.setMinimumSize(new Dimension(30, 14));
		lbl_contactNum.setMaximumSize(new Dimension(50, 14));
		GridBagConstraints gbc_lbl_contactNum = new GridBagConstraints();
		gbc_lbl_contactNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_contactNum.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_contactNum.weighty = 1.0;
		gbc_lbl_contactNum.weightx = 1.0;
		gbc_lbl_contactNum.gridx = 7;
		gbc_lbl_contactNum.gridy = 1;
		pnl_garageInfo.add(lbl_contactNum, gbc_lbl_contactNum);
		
		GridBagConstraints gbc_txt_contactNum = new GridBagConstraints();
		gbc_txt_contactNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_contactNum.insets = new Insets(0, 0, 5, 5);
		gbc_txt_contactNum.gridx = 9;
		gbc_txt_contactNum.gridy = 1;
		txt_contactNum.setMinimumSize(new Dimension(30, 20));
		txt_contactNum.setPreferredSize(new Dimension(100, 20));
		pnl_garageInfo.add(txt_contactNum, gbc_txt_contactNum);
		txt_contactNum.setColumns(10);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 10;
		gbc_horizontalStrut_1.gridy = 1;
		pnl_garageInfo.add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_3 = new GridBagConstraints();
		gbc_horizontalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_3.gridx = 1;
		gbc_horizontalStrut_3.gridy = 2;
		pnl_garageInfo.add(horizontalStrut_3, gbc_horizontalStrut_3);
		
		JLabel lbl_contactEmail = new JLabel("Contact Email");
		lbl_contactEmail.setMinimumSize(new Dimension(30, 14));
		lbl_contactEmail.setMaximumSize(new Dimension(50, 14));
		lbl_contactEmail.setPreferredSize(new Dimension(50, 14));
		GridBagConstraints gbc_lbl_contactEmail = new GridBagConstraints();
		gbc_lbl_contactEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_contactEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_contactEmail.gridx = 2;
		gbc_lbl_contactEmail.gridy = 2;
		pnl_garageInfo.add(lbl_contactEmail, gbc_lbl_contactEmail);
		
		GridBagConstraints gbc_txt_contactEmail = new GridBagConstraints();
		gbc_txt_contactEmail.weighty = 1.0;
		gbc_txt_contactEmail.weightx = 2.0;
		gbc_txt_contactEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txt_contactEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_contactEmail.gridx = 4;
		gbc_txt_contactEmail.gridy = 2;
		txt_contactEmail.setPreferredSize(new Dimension(30, 20));
		txt_contactEmail.setMinimumSize(new Dimension(30, 20));
		pnl_garageInfo.add(txt_contactEmail, gbc_txt_contactEmail);
		txt_contactEmail.setColumns(10);
		
		JLabel lbl_paidUntil = new JLabel("Paid Until");
		lbl_paidUntil.setPreferredSize(new Dimension(50, 14));
		lbl_paidUntil.setMinimumSize(new Dimension(30, 14));
		lbl_paidUntil.setMaximumSize(new Dimension(50, 14));
		GridBagConstraints gbc_lbl_paidUntil = new GridBagConstraints();
		gbc_lbl_paidUntil.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_paidUntil.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_paidUntil.gridx = 7;
		gbc_lbl_paidUntil.gridy = 2;
		pnl_garageInfo.add(lbl_paidUntil, gbc_lbl_paidUntil);
		
		GridBagConstraints gbc_date_paidUntil = new GridBagConstraints();
		gbc_date_paidUntil.fill = GridBagConstraints.HORIZONTAL;
		gbc_date_paidUntil.insets = new Insets(0, 0, 5, 5);
		gbc_date_paidUntil.gridx = 9;
		gbc_date_paidUntil.gridy = 2;
		pnl_garageInfo.add(date_paidUntil, gbc_date_paidUntil);
		
		
		btn_deleteGarage.setPreferredSize(new Dimension(200, 23));
		btn_deleteGarage.setMaximumSize(new Dimension(200, 23));
		GridBagConstraints gbc_btn_deleteGarage = new GridBagConstraints();
		gbc_btn_deleteGarage.gridwidth = 8;
		gbc_btn_deleteGarage.insets = new Insets(0, 0, 5, 5);
		gbc_btn_deleteGarage.gridx = 2;
		gbc_btn_deleteGarage.gridy = 3;
		pnl_garageInfo.add(btn_deleteGarage, gbc_btn_deleteGarage);
		
		JPanel pnl_instrumentList = new JPanel();
		pnl_instrumentList.setBorder(null);
		GridBagConstraints gbc_pnl_instrumentList = new GridBagConstraints();
		gbc_pnl_instrumentList.weighty = 1.0;
		gbc_pnl_instrumentList.weightx = 1.0;
		gbc_pnl_instrumentList.gridheight = 2;
		gbc_pnl_instrumentList.gridwidth = 2;
		gbc_pnl_instrumentList.insets = new Insets(0, 0, 5, 5);
		gbc_pnl_instrumentList.fill = GridBagConstraints.BOTH;
		gbc_pnl_instrumentList.gridx = 2;
		gbc_pnl_instrumentList.gridy = 4;
		pnl_garageInfo.add(pnl_instrumentList, gbc_pnl_instrumentList);
		GridBagLayout gbl_pnl_instrumentList = new GridBagLayout();
		gbl_pnl_instrumentList.columnWidths = new int[]{128, 0};
		gbl_pnl_instrumentList.rowHeights = new int[]{65, 186, 0, 0};
		gbl_pnl_instrumentList.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnl_instrumentList.rowWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		pnl_instrumentList.setLayout(gbl_pnl_instrumentList);
		
		JLabel lbl_instruments = new JLabel("Instruments");
		lbl_instruments.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_instruments.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_instruments.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lbl_instruments = new GridBagConstraints();
		gbc_lbl_instruments.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_instruments.insets = new Insets(0, 0, 5, 0);
		gbc_lbl_instruments.gridx = 0;
		gbc_lbl_instruments.gridy = 0;
		pnl_instrumentList.add(lbl_instruments, gbc_lbl_instruments);
		
		JScrollPane scrl_instrumentList = new JScrollPane();
		GridBagConstraints gbc_scrl_instrumentList = new GridBagConstraints();
		gbc_scrl_instrumentList.insets = new Insets(0, 0, 5, 0);
		gbc_scrl_instrumentList.fill = GridBagConstraints.BOTH;
		gbc_scrl_instrumentList.gridx = 0;
		gbc_scrl_instrumentList.gridy = 1;
		pnl_instrumentList.add(scrl_instrumentList, gbc_scrl_instrumentList);
		
		lst_instruments.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrl_instrumentList.setViewportView(lst_instruments);
		
		
		GridBagConstraints gbc_btn_addInstrument = new GridBagConstraints();
		gbc_btn_addInstrument.gridx = 0;
		gbc_btn_addInstrument.gridy = 2;
		pnl_instrumentList.add(btn_addInstrument, gbc_btn_addInstrument);
		
		JPanel pnl_instrumentInfo = new JPanel();
		pnl_instrumentInfo.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		GridBagConstraints gbc_pnl_instrumentInfo = new GridBagConstraints();
		gbc_pnl_instrumentInfo.weighty = 5.0;
		gbc_pnl_instrumentInfo.weightx = 2.0;
		gbc_pnl_instrumentInfo.gridheight = 2;
		gbc_pnl_instrumentInfo.gridwidth = 6;
		gbc_pnl_instrumentInfo.insets = new Insets(0, 0, 5, 5);
		gbc_pnl_instrumentInfo.fill = GridBagConstraints.BOTH;
		gbc_pnl_instrumentInfo.gridx = 4;
		gbc_pnl_instrumentInfo.gridy = 4;
		pnl_garageInfo.add(pnl_instrumentInfo, gbc_pnl_instrumentInfo);
		GridBagLayout gbl_pnl_instrumentInfo = new GridBagLayout();
		gbl_pnl_instrumentInfo.columnWidths = new int[]{0, 291, 249, 0, 0};
		gbl_pnl_instrumentInfo.rowHeights = new int[]{27, 0, 42, 30, 0};
		gbl_pnl_instrumentInfo.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_pnl_instrumentInfo.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		pnl_instrumentInfo.setLayout(gbl_pnl_instrumentInfo);
		
		JLabel lbl_instrumentInfo = new JLabel("Instrument Information");
		lbl_instrumentInfo.setFont(new Font("Tahoma", Font.BOLD, 22));
		GridBagConstraints gbc_lbl_instrumentInfo = new GridBagConstraints();
		gbc_lbl_instrumentInfo.gridwidth = 2;
		gbc_lbl_instrumentInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_instrumentInfo.weighty = 1.0;
		gbc_lbl_instrumentInfo.weightx = 1.0;
		gbc_lbl_instrumentInfo.gridx = 1;
		gbc_lbl_instrumentInfo.gridy = 0;
		pnl_instrumentInfo.add(lbl_instrumentInfo, gbc_lbl_instrumentInfo);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_5 = new GridBagConstraints();
		gbc_horizontalStrut_5.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_5.gridx = 0;
		gbc_horizontalStrut_5.gridy = 1;
		pnl_instrumentInfo.add(horizontalStrut_5, gbc_horizontalStrut_5);
		
		JLabel lbl_serialNum = new JLabel("Serial Number");
		GridBagConstraints gbc_lbl_serialNum = new GridBagConstraints();
		gbc_lbl_serialNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_serialNum.weighty = 1.0;
		gbc_lbl_serialNum.weightx = 1.0;
		gbc_lbl_serialNum.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_serialNum.gridx = 1;
		gbc_lbl_serialNum.gridy = 1;
		pnl_instrumentInfo.add(lbl_serialNum, gbc_lbl_serialNum);
		
		GridBagConstraints gbc_txt_serialNum = new GridBagConstraints();
		gbc_txt_serialNum.insets = new Insets(0, 0, 5, 5);
		gbc_txt_serialNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_serialNum.gridx = 2;
		gbc_txt_serialNum.gridy = 1;
		pnl_instrumentInfo.add(txt_serialNum, gbc_txt_serialNum);
		txt_serialNum.setColumns(10);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_6 = new GridBagConstraints();
		gbc_horizontalStrut_6.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_6.gridx = 3;
		gbc_horizontalStrut_6.gridy = 1;
		pnl_instrumentInfo.add(horizontalStrut_6, gbc_horizontalStrut_6);
		
		JLabel lbl_calibrationDate = new JLabel("Calibration Date");
		GridBagConstraints gbc_lbl_calibrationDate = new GridBagConstraints();
		gbc_lbl_calibrationDate.anchor = GridBagConstraints.LINE_START;
		gbc_lbl_calibrationDate.weighty = 1.0;
		gbc_lbl_calibrationDate.weightx = 1.0;
		gbc_lbl_calibrationDate.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_calibrationDate.gridx = 1;
		gbc_lbl_calibrationDate.gridy = 2;
		pnl_instrumentInfo.add(lbl_calibrationDate, gbc_lbl_calibrationDate);
		
		GridBagConstraints gbc_date_calibrationDate = new GridBagConstraints();
		gbc_date_calibrationDate.insets = new Insets(0, 0, 5, 5);
		gbc_date_calibrationDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_date_calibrationDate.gridx = 2;
		gbc_date_calibrationDate.gridy = 2;
		pnl_instrumentInfo.add(date_calibrationDate, gbc_date_calibrationDate);
		
		GridBagConstraints gbc_btn_deleteInstrument = new GridBagConstraints();
		gbc_btn_deleteInstrument.gridwidth = 2;
		gbc_btn_deleteInstrument.insets = new Insets(0, 0, 0, 5);
		gbc_btn_deleteInstrument.gridx = 1;
		gbc_btn_deleteInstrument.gridy = 3;
		pnl_instrumentInfo.add(btn_deleteInstrument, gbc_btn_deleteInstrument);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut.gridx = 7;
		gbc_verticalStrut.gridy = 6;
		pnl_garageInfo.add(verticalStrut, gbc_verticalStrut);
		
		GridBagConstraints gbc_btn_addGarage = new GridBagConstraints();
		gbc_btn_addGarage.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_addGarage.insets = new Insets(0, 5, 5, 5);
		gbc_btn_addGarage.gridx = 0;
		gbc_btn_addGarage.gridy = 1;
		add(btn_addGarage, gbc_btn_addGarage);
		
		GridBagConstraints gbc_btn_saveChanges = new GridBagConstraints();
		gbc_btn_saveChanges.insets = new Insets(0, 5, 5, 5);
		gbc_btn_saveChanges.anchor = GridBagConstraints.SOUTH;
		gbc_btn_saveChanges.gridx = 1;
		gbc_btn_saveChanges.gridy = 1;
		add(btn_saveChanges, gbc_btn_saveChanges);

	}

	public JList<Garage> getGaragesList() {
		return lst_garages;
	}
	
	public JButton getAddGarageButton() {
		return btn_addGarage;
	}
	
	public JTextField getGarageNameTextField() {
		return txt_garageName;
	}
	
	public JTextField getGarageNumberTextField() {
		return txt_contactNum;
	}
	
	public JTextField getGarageEmailTextField() {
		return txt_contactEmail;
	}
	
	public JDateChooser getGaragePaidUntil() {
		return date_paidUntil;
	}
	
	public JButton getDeleteGarageButton() {
		return btn_deleteGarage;
	}
	
	public JList<Instrument> getInstrumentList() {
		return lst_instruments;
	}
	
	public JButton getAddInstrumentButton() {
		return btn_addInstrument;
	}
	
	public JTextField getSerialNumTextField() {
		return txt_serialNum;
	}
	
	public JDateChooser getCalibrationDate() {
		return date_calibrationDate;
	}
	
	public void setGarageList(DefaultListModel<Garage> list) {
		lst_garages.setModel(list);
	}
	
	public void setInstrumentList(DefaultListModel<Instrument> list) {
		lst_instruments.setModel(list);
	}
}

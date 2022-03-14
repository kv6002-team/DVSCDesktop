package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.ComponentOrientation;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Cursor;
//import com.toedter.calendar.JDateChooser;
//import com.toedter.calendar.JCalendar;
import java.awt.Dimension;

public class CRUDPanel extends JPanel {
	private JTextField txt_garageName;
	private JTextField txt_contactNum;
	private JTextField txt_contactEmail;
	private JTextField txt_serialNum;

	public static final String tabName = "garages";
	
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
		
		JList lst_garages = new JList();
		lst_garages.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lst_garages.setModel(new AbstractListModel() {
			String[] values = new String[] {"Garage", "Garage", "Garage", "Garage", "Garage", "Garage", "Garage", "Garage", "Garage", "Garage", "Garage", "Garage", "Garage", "Garage", "Garage", "Garage", "Garage", "Garage", "Garage", "Garage"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
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
		gbl_pnl_garageInfo.columnWidths = new int[]{0, 0, 126, 198, 35, 194, 0, 159, 0, 0, 0, 0};
		gbl_pnl_garageInfo.rowHeights = new int[]{27, 14, 0, 69, 0, 0, 0, 0};
		gbl_pnl_garageInfo.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnl_garageInfo.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		pnl_garageInfo.setLayout(gbl_pnl_garageInfo);
		
		JLabel lbl_garageInfo = new JLabel("Garage Information");
		GridBagConstraints gbc_lbl_garageInfo = new GridBagConstraints();
		gbc_lbl_garageInfo.gridwidth = 8;
		gbc_lbl_garageInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_garageInfo.gridx = 0;
		gbc_lbl_garageInfo.gridy = 0;
		pnl_garageInfo.add(lbl_garageInfo, gbc_lbl_garageInfo);
		lbl_garageInfo.setHorizontalTextPosition(SwingConstants.CENTER);
		lbl_garageInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_garageInfo.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_1.gridx = 1;
		gbc_horizontalStrut_1.gridy = 1;
		pnl_garageInfo.add(horizontalStrut_1, gbc_horizontalStrut_1);
		
		JLabel lbl_garageName = new JLabel("Garage Name");
		GridBagConstraints gbc_lbl_garageName = new GridBagConstraints();
		gbc_lbl_garageName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_garageName.weighty = 1.0;
		gbc_lbl_garageName.weightx = 1.0;
		gbc_lbl_garageName.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_garageName.gridx = 2;
		gbc_lbl_garageName.gridy = 1;
		pnl_garageInfo.add(lbl_garageName, gbc_lbl_garageName);
		
		txt_garageName = new JTextField();
		GridBagConstraints gbc_txt_garageName = new GridBagConstraints();
		gbc_txt_garageName.insets = new Insets(0, 0, 5, 5);
		gbc_txt_garageName.weighty = 1.0;
		gbc_txt_garageName.weightx = 2.0;
		gbc_txt_garageName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_garageName.gridx = 3;
		gbc_txt_garageName.gridy = 1;
		pnl_garageInfo.add(txt_garageName, gbc_txt_garageName);
		txt_garageName.setColumns(10);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut_2.gridx = 4;
		gbc_horizontalStrut_2.gridy = 1;
		pnl_garageInfo.add(horizontalStrut_2, gbc_horizontalStrut_2);
		
		JLabel lbl_contactNum = new JLabel("Contact Number");
		GridBagConstraints gbc_lbl_contactNum = new GridBagConstraints();
		gbc_lbl_contactNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_contactNum.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_contactNum.weighty = 1.0;
		gbc_lbl_contactNum.weightx = 1.0;
		gbc_lbl_contactNum.gridx = 5;
		gbc_lbl_contactNum.gridy = 1;
		pnl_garageInfo.add(lbl_contactNum, gbc_lbl_contactNum);
		
		txt_contactNum = new JTextField();
		GridBagConstraints gbc_txt_contactNum = new GridBagConstraints();
		gbc_txt_contactNum.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_contactNum.insets = new Insets(0, 0, 5, 5);
		gbc_txt_contactNum.weighty = 1.0;
		gbc_txt_contactNum.weightx = 2.0;
		gbc_txt_contactNum.gridx = 7;
		gbc_txt_contactNum.gridy = 1;
		pnl_garageInfo.add(txt_contactNum, gbc_txt_contactNum);
		txt_contactNum.setColumns(10);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_3 = new GridBagConstraints();
		gbc_horizontalStrut_3.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_3.gridx = 10;
		gbc_horizontalStrut_3.gridy = 1;
		pnl_garageInfo.add(horizontalStrut_3, gbc_horizontalStrut_3);
		
		JLabel lbl_contactEmail = new JLabel("Contact Email");
		GridBagConstraints gbc_lbl_contactEmail = new GridBagConstraints();
		gbc_lbl_contactEmail.anchor = GridBagConstraints.LINE_START;
		gbc_lbl_contactEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_contactEmail.gridx = 2;
		gbc_lbl_contactEmail.gridy = 2;
		pnl_garageInfo.add(lbl_contactEmail, gbc_lbl_contactEmail);
		
		txt_contactEmail = new JTextField();
		GridBagConstraints gbc_txt_contactEmail = new GridBagConstraints();
		gbc_txt_contactEmail.weighty = 1.0;
		gbc_txt_contactEmail.weightx = 2.0;
		gbc_txt_contactEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txt_contactEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_contactEmail.gridx = 3;
		gbc_txt_contactEmail.gridy = 2;
		pnl_garageInfo.add(txt_contactEmail, gbc_txt_contactEmail);
		txt_contactEmail.setColumns(10);
		
		JLabel lbl_yearPaid = new JLabel("Year Paid");
		GridBagConstraints gbc_lbl_yearPaid = new GridBagConstraints();
		gbc_lbl_yearPaid.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbl_yearPaid.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_yearPaid.gridx = 5;
		gbc_lbl_yearPaid.gridy = 2;
		pnl_garageInfo.add(lbl_yearPaid, gbc_lbl_yearPaid);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 7;
		gbc_chckbxNewCheckBox.gridy = 2;
		pnl_garageInfo.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		JButton btn_deleteGarage = new JButton("Delete Garage");
		btn_deleteGarage.setPreferredSize(new Dimension(200, 23));
		btn_deleteGarage.setMaximumSize(new Dimension(200, 23));
		GridBagConstraints gbc_btn_deleteGarage = new GridBagConstraints();
		gbc_btn_deleteGarage.gridwidth = 5;
		gbc_btn_deleteGarage.insets = new Insets(0, 0, 5, 5);
		gbc_btn_deleteGarage.gridx = 3;
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
		gbl_pnl_instrumentList.rowHeights = new int[]{0, 0, 0, 0};
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
		
		JList lst_instruments = new JList();
		lst_instruments.setModel(new AbstractListModel() {
			String[] values = new String[] {"Instrument", "Instrument", "Instrument", "Instrument", "Instrument", "Instrument", "Instrument", "Instrument", "Instrument", "Instrument", "Instrument", "Instrument", "Instrument", "Instrument", "Instrument\t"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrl_instrumentList.setViewportView(lst_instruments);
		
		JButton btn_addInstrument = new JButton("Add Instrument");
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
		gbc_pnl_instrumentInfo.gridwidth = 3;
		gbc_pnl_instrumentInfo.insets = new Insets(0, 0, 5, 5);
		gbc_pnl_instrumentInfo.fill = GridBagConstraints.BOTH;
		gbc_pnl_instrumentInfo.gridx = 5;
		gbc_pnl_instrumentInfo.gridy = 4;
		pnl_garageInfo.add(pnl_instrumentInfo, gbc_pnl_instrumentInfo);
		GridBagLayout gbl_pnl_instrumentInfo = new GridBagLayout();
		gbl_pnl_instrumentInfo.columnWidths = new int[]{0, 308, 267, 0, 0};
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
		gbc_lbl_serialNum.anchor = GridBagConstraints.LINE_START;
		gbc_lbl_serialNum.weighty = 1.0;
		gbc_lbl_serialNum.weightx = 1.0;
		gbc_lbl_serialNum.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_serialNum.gridx = 1;
		gbc_lbl_serialNum.gridy = 1;
		pnl_instrumentInfo.add(lbl_serialNum, gbc_lbl_serialNum);
		
		txt_serialNum = new JTextField();
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
		
		//JDateChooser dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 2;
		//pnl_instrumentInfo.add(dateChooser, gbc_dateChooser);
		
		JButton btn_deleteInstrument = new JButton("Delete Instrument");
		GridBagConstraints gbc_btn_deleteInstrument = new GridBagConstraints();
		gbc_btn_deleteInstrument.gridwidth = 2;
		gbc_btn_deleteInstrument.insets = new Insets(0, 0, 0, 5);
		gbc_btn_deleteInstrument.gridx = 1;
		gbc_btn_deleteInstrument.gridy = 3;
		pnl_instrumentInfo.add(btn_deleteInstrument, gbc_btn_deleteInstrument);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 0, 5);
		gbc_verticalStrut.gridx = 5;
		gbc_verticalStrut.gridy = 6;
		pnl_garageInfo.add(verticalStrut, gbc_verticalStrut);
		
		JButton btn_addGarage = new JButton("Add Garage");
		GridBagConstraints gbc_btn_addGarage = new GridBagConstraints();
		gbc_btn_addGarage.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_addGarage.insets = new Insets(0, 5, 5, 5);
		gbc_btn_addGarage.gridx = 0;
		gbc_btn_addGarage.gridy = 1;
		add(btn_addGarage, gbc_btn_addGarage);
		
		JButton btn_saveChanges = new JButton("Save Changes");
		GridBagConstraints gbc_btn_saveChanges = new GridBagConstraints();
		gbc_btn_saveChanges.insets = new Insets(0, 5, 5, 5);
		gbc_btn_saveChanges.anchor = GridBagConstraints.SOUTH;
		gbc_btn_saveChanges.gridx = 1;
		gbc_btn_saveChanges.gridy = 1;
		add(btn_saveChanges, gbc_btn_saveChanges);

	}

}

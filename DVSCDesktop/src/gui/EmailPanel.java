package gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.List;
import java.awt.Button;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Choice;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.LineBorder;

import domain.Email;

import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;

public class EmailPanel extends JPanel {
	
	public JList<?> lst_GarageEmails = new JList();
	public JList<?> lst_ApprovedEmails = new JList();
	public JButton btn_MoveAll = new JButton("Move all");
	public JButton btn_MoveSelected = new JButton("Move selected");
	public JButton btn_RemoveSelected = new JButton("Remove selected");
	public JButton btn_RemoveAll = new JButton("Remove All");
	public JButton btn_Approve = new JButton("Approve");
	
	public JList getGarageEmails() {
		return lst_GarageEmails;
	}

	public JList getApprovedEmails() {
		return lst_ApprovedEmails;
	}
	
	public JButton getMoveAll() {
		return btn_MoveAll;
	}
	
	public JButton getMoveSelected() {
		return btn_MoveSelected;
	}
	
	public JButton getRemoveSelected() {
		return btn_RemoveSelected;
	}
	
	public JButton getRemoveAll() {
		return btn_RemoveAll;
	}
	
	public JButton getApprove() {
		return btn_Approve;
	}
	
	public void setUnapprovedEmailList(Email email) {
		lst_GarageEmails.setModel((ListModel) email);
	}
	
	public void setApprovedEmailList(Email email) {
		lst_ApprovedEmails.setModel((ListModel) email);
	}
	
	/**
	 * Create the panel.
	 */
	
	public EmailPanel() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 210, 159, 210, 0, 0};
		gridBagLayout.rowHeights = new int[]{21, 0, 327, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel pnl_Recipients = new JPanel();
		GridBagConstraints gbc_pnl_Recipients = new GridBagConstraints();
		gbc_pnl_Recipients.weightx = 0.5;
		gbc_pnl_Recipients.insets = new Insets(0, 0, 5, 5);
		gbc_pnl_Recipients.fill = GridBagConstraints.BOTH;
		gbc_pnl_Recipients.gridx = 1;
		gbc_pnl_Recipients.gridy = 2;
		add(pnl_Recipients, gbc_pnl_Recipients);
		pnl_Recipients.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tab_GarageEmails = new JTabbedPane(JTabbedPane.TOP);
		pnl_Recipients.add(tab_GarageEmails);
		
		JPanel pnl_GarageEmails = new JPanel();
		tab_GarageEmails.addTab("Garages", null, pnl_GarageEmails, null);
		GridBagLayout gbl_pnl_GarageEmails = new GridBagLayout();
		gbl_pnl_GarageEmails.columnWidths = new int[]{0, 0};
		gbl_pnl_GarageEmails.rowHeights = new int[]{0, 0};
		gbl_pnl_GarageEmails.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnl_GarageEmails.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnl_GarageEmails.setLayout(gbl_pnl_GarageEmails);
		
		JScrollPane scrl_GarageEmails = new JScrollPane();
		GridBagConstraints gbc_scrl_GarageEmails = new GridBagConstraints();
		gbc_scrl_GarageEmails.fill = GridBagConstraints.BOTH;
		gbc_scrl_GarageEmails.gridx = 0;
		gbc_scrl_GarageEmails.gridy = 0;
		pnl_GarageEmails.add(scrl_GarageEmails, gbc_scrl_GarageEmails);
		lst_GarageEmails.setModel(new AbstractListModel() {
			String[] values = new String[] {"aaaa", "bbb", "ccc", "ddd"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		scrl_GarageEmails.setViewportView(lst_GarageEmails);
		
		JPanel pnl_ButtonPannel = new JPanel();
		GridBagConstraints gbc_pnl_ButtonPannel = new GridBagConstraints();
		gbc_pnl_ButtonPannel.insets = new Insets(0, 0, 5, 5);
		gbc_pnl_ButtonPannel.fill = GridBagConstraints.BOTH;
		gbc_pnl_ButtonPannel.gridx = 2;
		gbc_pnl_ButtonPannel.gridy = 2;
		add(pnl_ButtonPannel, gbc_pnl_ButtonPannel);
		GridBagLayout gbl_pnl_ButtonPannel = new GridBagLayout();
		gbl_pnl_ButtonPannel.columnWidths = new int[]{88, 0};
		gbl_pnl_ButtonPannel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnl_ButtonPannel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pnl_ButtonPannel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnl_ButtonPannel.setLayout(gbl_pnl_ButtonPannel);
		
		GridBagConstraints gbc_btn_MoveAll = new GridBagConstraints();
		gbc_btn_MoveAll.insets = new Insets(0, 0, 5, 0);
		gbc_btn_MoveAll.gridx = 0;
		gbc_btn_MoveAll.gridy = 2;
		pnl_ButtonPannel.add(btn_MoveAll, gbc_btn_MoveAll);
		
		GridBagConstraints gbc_btn_MoveSelected = new GridBagConstraints();
		gbc_btn_MoveSelected.insets = new Insets(0, 0, 5, 0);
		gbc_btn_MoveSelected.gridx = 0;
		gbc_btn_MoveSelected.gridy = 3;
		pnl_ButtonPannel.add(btn_MoveSelected, gbc_btn_MoveSelected);
		
		GridBagConstraints gbc_btn_Approve = new GridBagConstraints();
		gbc_btn_Approve.insets = new Insets(0, 0, 5, 0);
		gbc_btn_Approve.gridx = 0;
		gbc_btn_Approve.gridy = 5;
		pnl_ButtonPannel.add(btn_Approve, gbc_btn_Approve);
		
		GridBagConstraints gbc_btn_RemoveSelected = new GridBagConstraints();
		gbc_btn_RemoveSelected.insets = new Insets(0, 0, 5, 0);
		gbc_btn_RemoveSelected.gridx = 0;
		gbc_btn_RemoveSelected.gridy = 7;
		pnl_ButtonPannel.add(btn_RemoveSelected, gbc_btn_RemoveSelected);
		
		GridBagConstraints gbc_btn_RemoveAll = new GridBagConstraints();
		gbc_btn_RemoveAll.insets = new Insets(0, 0, 5, 0);
		gbc_btn_RemoveAll.gridx = 0;
		gbc_btn_RemoveAll.gridy = 8;
		pnl_ButtonPannel.add(btn_RemoveAll, gbc_btn_RemoveAll);
		
		JPanel pnl_EmailRecpients = new JPanel();
		GridBagConstraints gbc_pnl_EmailRecpients = new GridBagConstraints();
		gbc_pnl_EmailRecpients.insets = new Insets(0, 0, 5, 5);
		gbc_pnl_EmailRecpients.fill = GridBagConstraints.BOTH;
		gbc_pnl_EmailRecpients.gridx = 3;
		gbc_pnl_EmailRecpients.gridy = 2;
		add(pnl_EmailRecpients, gbc_pnl_EmailRecpients);
		GridBagLayout gbl_pnl_EmailRecpients = new GridBagLayout();
		gbl_pnl_EmailRecpients.columnWidths = new int[]{0, 0};
		gbl_pnl_EmailRecpients.rowHeights = new int[]{0, 0};
		gbl_pnl_EmailRecpients.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnl_EmailRecpients.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnl_EmailRecpients.setLayout(gbl_pnl_EmailRecpients);
		
		JTabbedPane tab_ApprovedEmails = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tab_ApprovedEmails = new GridBagConstraints();
		gbc_tab_ApprovedEmails.fill = GridBagConstraints.BOTH;
		gbc_tab_ApprovedEmails.gridx = 0;
		gbc_tab_ApprovedEmails.gridy = 0;
		pnl_EmailRecpients.add(tab_ApprovedEmails, gbc_tab_ApprovedEmails);
		
		JPanel pnl_ApprovedEmails = new JPanel();
		tab_ApprovedEmails.addTab("Approved Emails", null, pnl_ApprovedEmails, null);
		GridBagLayout gbl_pnl_ApprovedEmails = new GridBagLayout();
		gbl_pnl_ApprovedEmails.columnWidths = new int[]{2, 0};
		gbl_pnl_ApprovedEmails.rowHeights = new int[]{2, 0};
		gbl_pnl_ApprovedEmails.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnl_ApprovedEmails.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnl_ApprovedEmails.setLayout(gbl_pnl_ApprovedEmails);
		
		JScrollPane scrl_ApprovedEmails = new JScrollPane();
		GridBagConstraints gbc_scrl_ApprovedEmails = new GridBagConstraints();
		gbc_scrl_ApprovedEmails.fill = GridBagConstraints.BOTH;
		gbc_scrl_ApprovedEmails.gridx = 0;
		gbc_scrl_ApprovedEmails.gridy = 0;
		pnl_ApprovedEmails.add(scrl_ApprovedEmails, gbc_scrl_ApprovedEmails);
		
		scrl_ApprovedEmails.setViewportView(lst_ApprovedEmails);

	}
}

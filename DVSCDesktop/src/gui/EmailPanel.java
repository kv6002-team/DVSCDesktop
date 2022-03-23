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
import javax.swing.BoxLayout;
import java.awt.List;
import java.awt.Button;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Choice;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JTextArea;

public class EmailPanel extends JPanel {
	private JTextField EmailSubject;
	private JTextField EmailRecipient;

	/**
	 * Create the panel.
	 */
	public EmailPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{17, 161, 294, 0, 0};
		gridBagLayout.rowHeights = new int[]{21, 0, 327, 13, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel pnl_emailLists = new JPanel();
		GridBagConstraints gbc_pnl_emailLists = new GridBagConstraints();
		gbc_pnl_emailLists.weightx = 0.5;
		gbc_pnl_emailLists.insets = new Insets(0, 0, 5, 5);
		gbc_pnl_emailLists.fill = GridBagConstraints.BOTH;
		gbc_pnl_emailLists.gridx = 1;
		gbc_pnl_emailLists.gridy = 2;
		add(pnl_emailLists, gbc_pnl_emailLists);
		pnl_emailLists.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tab_EmailTabs = new JTabbedPane(JTabbedPane.TOP);
		pnl_emailLists.add(tab_EmailTabs);
		
		JPanel pnl_UnapprovedEmails = new JPanel();
		tab_EmailTabs.addTab("Unapproved Emails", null, pnl_UnapprovedEmails, null);
		GridBagLayout gbl_pnl_UnapprovedEmails = new GridBagLayout();
		gbl_pnl_UnapprovedEmails.columnWidths = new int[]{0, 0};
		gbl_pnl_UnapprovedEmails.rowHeights = new int[]{0, 0};
		gbl_pnl_UnapprovedEmails.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnl_UnapprovedEmails.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnl_UnapprovedEmails.setLayout(gbl_pnl_UnapprovedEmails);
		
		JScrollPane scrl_UnapprovedEmails = new JScrollPane();
		GridBagConstraints gbc_scrl_UnapprovedEmails = new GridBagConstraints();
		gbc_scrl_UnapprovedEmails.fill = GridBagConstraints.BOTH;
		gbc_scrl_UnapprovedEmails.gridx = 0;
		gbc_scrl_UnapprovedEmails.gridy = 0;
		pnl_UnapprovedEmails.add(scrl_UnapprovedEmails, gbc_scrl_UnapprovedEmails);
		
		JList lst_UnapprovedList = new JList();
		scrl_UnapprovedEmails.setViewportView(lst_UnapprovedList);
		
		JPanel pnl_ApprovedEmails = new JPanel();
		tab_EmailTabs.addTab("Approved Emails", null, pnl_ApprovedEmails, null);
		GridBagLayout gbl_pnl_ApprovedEmails = new GridBagLayout();
		gbl_pnl_ApprovedEmails.columnWidths = new int[]{76, 0};
		gbl_pnl_ApprovedEmails.rowHeights = new int[]{1, 0};
		gbl_pnl_ApprovedEmails.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnl_ApprovedEmails.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnl_ApprovedEmails.setLayout(gbl_pnl_ApprovedEmails);
		
		JScrollPane scrl_ApprovedEmails = new JScrollPane();
		GridBagConstraints gbc_scrl_ApprovedEmails = new GridBagConstraints();
		gbc_scrl_ApprovedEmails.fill = GridBagConstraints.BOTH;
		gbc_scrl_ApprovedEmails.gridx = 0;
		gbc_scrl_ApprovedEmails.gridy = 0;
		pnl_ApprovedEmails.add(scrl_ApprovedEmails, gbc_scrl_ApprovedEmails);
		
		JList lst_ApprovedList = new JList();
		scrl_ApprovedEmails.setViewportView(lst_ApprovedList);
		
		JPanel pnl_ArchivedEmails = new JPanel();
		tab_EmailTabs.addTab("Archived Emails", null, pnl_ArchivedEmails, null);
		GridBagLayout gbl_pnl_ArchivedEmails = new GridBagLayout();
		gbl_pnl_ArchivedEmails.columnWidths = new int[]{75, 0};
		gbl_pnl_ArchivedEmails.rowHeights = new int[]{2, 0};
		gbl_pnl_ArchivedEmails.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnl_ArchivedEmails.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnl_ArchivedEmails.setLayout(gbl_pnl_ArchivedEmails);
		
		JScrollPane scrl_ArchivedEmails = new JScrollPane();
		GridBagConstraints gbc_scrl_ArchivedEmails = new GridBagConstraints();
		gbc_scrl_ArchivedEmails.fill = GridBagConstraints.BOTH;
		gbc_scrl_ArchivedEmails.gridx = 0;
		gbc_scrl_ArchivedEmails.gridy = 0;
		pnl_ArchivedEmails.add(scrl_ArchivedEmails, gbc_scrl_ArchivedEmails);
		
		JList list = new JList();
		scrl_ArchivedEmails.setViewportView(list);
		
		JPanel pnl_EmailBody = new JPanel();
		pnl_EmailBody.setBorder(new LineBorder(Color.BLACK));
		GridBagConstraints gbc_pnl_EmailBody = new GridBagConstraints();
		gbc_pnl_EmailBody.insets = new Insets(0, 0, 5, 5);
		gbc_pnl_EmailBody.fill = GridBagConstraints.BOTH;
		gbc_pnl_EmailBody.gridx = 2;
		gbc_pnl_EmailBody.gridy = 2;
		add(pnl_EmailBody, gbc_pnl_EmailBody);
		GridBagLayout gbl_pnl_EmailBody = new GridBagLayout();
		gbl_pnl_EmailBody.columnWidths = new int[]{0, 82, 0, 0, 0, 0, 0};
		gbl_pnl_EmailBody.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnl_EmailBody.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_pnl_EmailBody.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		pnl_EmailBody.setLayout(gbl_pnl_EmailBody);
		
		JLabel lblNewLabel_2 = new JLabel("Email Approval");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 7;
		gbc_lblNewLabel_2.insets = new Insets(20, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		pnl_EmailBody.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Subject");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		pnl_EmailBody.add(lblNewLabel, gbc_lblNewLabel);
		
		EmailSubject = new JTextField();
		EmailSubject.setBorder(new LineBorder(new Color(171, 173, 179)));
		GridBagConstraints gbc_EmailSubject = new GridBagConstraints();
		gbc_EmailSubject.gridwidth = 4;
		gbc_EmailSubject.insets = new Insets(0, 0, 5, 5);
		gbc_EmailSubject.fill = GridBagConstraints.HORIZONTAL;
		gbc_EmailSubject.gridx = 2;
		gbc_EmailSubject.gridy = 2;
		pnl_EmailBody.add(EmailSubject, gbc_EmailSubject);
		EmailSubject.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Recipient");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 3;
		pnl_EmailBody.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		EmailRecipient = new JTextField();
		EmailRecipient.setBorder(new LineBorder(new Color(171, 173, 179)));
		GridBagConstraints gbc_EmailRecipient = new GridBagConstraints();
		gbc_EmailRecipient.gridwidth = 4;
		gbc_EmailRecipient.insets = new Insets(0, 0, 5, 5);
		gbc_EmailRecipient.fill = GridBagConstraints.HORIZONTAL;
		gbc_EmailRecipient.gridx = 2;
		gbc_EmailRecipient.gridy = 3;
		pnl_EmailBody.add(EmailRecipient, gbc_EmailRecipient);
		EmailRecipient.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 5;
		gbc_textArea.gridheight = 4;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 4;
		pnl_EmailBody.add(textArea, gbc_textArea);
		
		JButton AmendEmail = new JButton("Amend");
		GridBagConstraints gbc_AmendEmail = new GridBagConstraints();
		gbc_AmendEmail.insets = new Insets(0, 0, 5, 5);
		gbc_AmendEmail.gridx = 1;
		gbc_AmendEmail.gridy = 8;
		pnl_EmailBody.add(AmendEmail, gbc_AmendEmail);
		
		JButton ApproveEmail = new JButton("Approve");
		GridBagConstraints gbc_ApproveEmail = new GridBagConstraints();
		gbc_ApproveEmail.insets = new Insets(0, 0, 5, 5);
		gbc_ApproveEmail.gridx = 5;
		gbc_ApproveEmail.gridy = 8;
		pnl_EmailBody.add(ApproveEmail, gbc_ApproveEmail);

	}

}

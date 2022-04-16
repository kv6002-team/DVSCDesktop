package gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

import guimanagers.CRUDPanelManager;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import guimanagers.EmailPanelManager;

/**
 * 
 * @author Scrub
 *
 */
@SuppressWarnings("serial")
public class Wrapper extends JPanel {
	
	private JTabbedPane tab_MainTabPane = new JTabbedPane(JTabbedPane.TOP);
	
	CRUDPanelManager CRUDPanelManager = new CRUDPanelManager(this);
	EmailPanelManager emailpanelmanager = new EmailPanelManager();
	/**
	 * Create the panel.
	 */
	public Wrapper() {
		setLayout(new BorderLayout(0, 0));
		add(tab_MainTabPane);
		tab_MainTabPane.addTab(CRUDPanel.tabName, null, CRUDPanelManager.getCRUDPanel(), null);
		tab_MainTabPane.addTab(EmailPanel.tabName, null, emailpanelmanager.getEmailPanel(), null);
	}
	
	public JTabbedPane getTabbedPane(){
		return tab_MainTabPane;
	}
}

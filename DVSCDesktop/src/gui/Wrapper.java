package gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

import guimanagers.CRUDPanelManager;
/**
 * 
 * @author Scrub
 *
 */
@SuppressWarnings("serial")
public class Wrapper extends JPanel {
	
	CRUDPanelManager CRUDPanelManager = new CRUDPanelManager();
	/**
	 * Create the panel.
	 */
	public Wrapper() {
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tab_MainTabPane = new JTabbedPane(JTabbedPane.TOP);
		add(tab_MainTabPane);
		tab_MainTabPane.addTab(CRUDPanel.tabName, null, CRUDPanelManager.getCRUDPanel(), null);
		JPanel panel = new JPanel();
		tab_MainTabPane.addTab("name", panel);
	}

}

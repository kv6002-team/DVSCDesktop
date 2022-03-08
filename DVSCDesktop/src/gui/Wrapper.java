package gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class Wrapper extends JPanel {

	/**
	 * Create the panel.
	 */
	public Wrapper() {
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tab_MainTabPane = new JTabbedPane(JTabbedPane.TOP);
		add(tab_MainTabPane);
		tab_MainTabPane.addTab("Test", null, new testPanel(), null);
	}

}

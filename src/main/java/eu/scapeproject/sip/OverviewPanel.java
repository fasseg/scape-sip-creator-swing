package eu.scapeproject.sip;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OverviewPanel extends JPanel {
	private final JTree sipTree;
	private final DefaultMutableTreeNode rootNode;
	private final SipTreeModel treeModel;
	private JPanel dataPanel;

	private static final Logger LOG = LoggerFactory.getLogger(OverviewPanel.class);

	OverviewPanel(Map<String, SIP> sips) {
		rootNode = new DefaultMutableTreeNode();
		treeModel = new SipTreeModel(sips, rootNode);
		sipTree = new JTree(treeModel);
		sipTree.setCellRenderer(new SipTreeCellRenderer());
		sipTree.setRowHeight(24);
		// sipTree.setRootVisible(false);
		sipTree.addTreeSelectionListener(new SipTreeSelectionListener(sipTree));
		dataPanel = new JPanel();
		dataPanel.setLayout(new GridBagLayout());
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHEAST;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.25f;
		c.weighty = 1f;
		this.add(sipTree, c);
		c.gridx = 1;
		c.weightx = 0.75f;
		this.add(dataPanel, c);

	}

	public void setSips(Map<String, SIP> sips) {
		treeModel.setSips(sips);
		expandRootNode();
		treeModel.reload();
		LOG.debug("displaying " + sips.size() + " SIPs");
	}

	public void hideDetails() {
		dataPanel.removeAll();
		dataPanel.repaint();
	}

	private void expandRootNode() {
		sipTree.expandPath(new TreePath(rootNode.getPath()));
	}

	private class SipTreeSelectionListener implements TreeSelectionListener {

		private final JTree sipTree;

		public SipTreeSelectionListener(JTree sipTree) {
			this.sipTree = sipTree;
		}

		@Override
		public void valueChanged(TreeSelectionEvent e) {
		}

	}

}

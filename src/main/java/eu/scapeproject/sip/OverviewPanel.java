package eu.scapeproject.sip;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OverviewPanel extends JPanel {

	private final JSplitPane splitPane = new JSplitPane();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTree sipTree;
	private final DefaultMutableTreeNode rootNode;
	private final SipTreeModel treeModel;

	private static final Logger LOG = LoggerFactory.getLogger(OverviewPanel.class);

	OverviewPanel(Map<String, SIP> sips, int dividerLocation) {
		rootNode = new DefaultMutableTreeNode();
		treeModel = new SipTreeModel(sips, rootNode);
		sipTree = new JTree(treeModel);
		sipTree.setCellRenderer(new SipTreeCellRenderer());
		sipTree.setRowHeight(24);
		// sipTree.setRootVisible(false);
		sipTree.addTreeSelectionListener(new SipTreeSelectionListener(sipTree));
		JPanel rightPanel = new JPanel();
		splitPane.setLeftComponent(new JScrollPane(sipTree));
		scrollPane.add(rightPanel);
		splitPane.setRightComponent(scrollPane);
		splitPane.setDividerLocation(dividerLocation);
		this.setLayout(new BorderLayout());
		this.add(splitPane, BorderLayout.CENTER);
	}

	void showSipDetails(SIP s) {
		this.scrollPane.setViewportView(new SipPanel(s));
		LOG.debug("showing details of SIP " + s.getTitle());
	}

	public void setSips(Map<String, SIP> sips) {
		treeModel.setSips(sips);
		expandRootNode();
		treeModel.reload();
		LOG.debug("displaying " + sips.size() + " SIPs");
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
			SipTreeNode node = (SipTreeNode) e.getPath().getLastPathComponent();
			if (node.getUserObject() instanceof SIP) {
				showSipDetails((SIP) node.getUserObject());
			}
		}

	}

}

package eu.scapeproject.sip;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.scapeproject.model.BitStream;
import eu.scapeproject.model.File;
import eu.scapeproject.model.IntellectualEntity;
import eu.scapeproject.model.Representation;
import eu.scapeproject.model.metadata.dc.DCMetadata;

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
        // sipTree.setRootVisible(false);
        sipTree.addTreeSelectionListener(new SipTreeSelectionListener(sipTree));
        dataPanel = new JPanel();
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

    private void expandRootNode() {
        sipTree.expandPath(new TreePath(rootNode.getPath()));
    }

    public void editEntity(IntellectualEntity obj) {
        LOG.debug("editing Intellectual entity");
        this.dataPanel.removeAll();
        this.dataPanel.add(new EditEntityPanel(obj));
        this.dataPanel.invalidate();
    }

    private class SipTreeModel extends DefaultTreeModel {

        private Map<String, SIP> sips;
        private DefaultMutableTreeNode rootNode;

        public SipTreeModel(Map<String, SIP> sips, DefaultMutableTreeNode root) {
            super(root);
            this.rootNode = root;
            this.sips = sips;
            updateNodes();
        }

        private void updateNodes() {
            rootNode.removeAllChildren();
            for (Entry<String, SIP> e : sips.entrySet()) {
                SIP s = e.getValue();
                SipTreeNode sipNode = new SipTreeNode(s.getTitle());
                SipTreeNode entitiesNode = new SipTreeNode("Intellectual Entities");
                for (IntellectualEntity entity : s.getEntities()) {
                    SipTreeNode entityNode = new SipTreeNode(entity);
                    SipTreeNode descMDNode = new SipTreeNode("Descriptive MD");
                    entityNode.add(descMDNode);
                    if (entity.getRepresentations() != null) {
                        SipTreeNode repsNode = new SipTreeNode("Representations");
                        for (Representation rep : entity.getRepresentations()) {
                            SipTreeNode repNode = new SipTreeNode(rep);
                            SipTreeNode technMDNode = new SipTreeNode("Technical MD");
                            SipTreeNode rightsMDNode = new SipTreeNode("Rights MD");
                            SipTreeNode sourceMDNode = new SipTreeNode("Source MD");
                            SipTreeNode provMDNode = new SipTreeNode("Digital Provenance MD");
                            repNode.add(technMDNode);
                            repNode.add(rightsMDNode);
                            repNode.add(sourceMDNode);
                            repNode.add(provMDNode);
                            repsNode.add(repNode);
                        }
                        entityNode.add(repsNode);
                    }
                    entitiesNode.add(entityNode);
                }
                sipNode.add(entitiesNode);
                rootNode.add(sipNode);
            }
        }

        void setSips(Map<String, SIP> sips) {
            this.sips = sips;
            updateNodes();
        }

    }

    private class SipTreeNode extends DefaultMutableTreeNode {
        public SipTreeNode(Object userObject) {
            super(userObject);
        }

        @Override
        public String toString() {
            if (userObject == null) {
                return "";
            }
            if (userObject instanceof IntellectualEntity) {
                IntellectualEntity e = (IntellectualEntity) userObject;
                if (e.getDescriptive() != null) {
                    DCMetadata dc = (DCMetadata) e.getDescriptive();
                    if (dc.getTitle() != null && dc.getTitle().size() > 0 && dc.getTitle().get(0).length() > 0) {
                        return dc.getTitle().get(0);
                    }
                }
                return "Untitled entity";
            } else if (userObject instanceof Representation) {
                Representation r = (Representation) userObject;
                if (r.getTitle() != null && r.getTitle().length() > 0) {
                    return r.getTitle();
                }
                return "Untitled representation";
            } else if (userObject instanceof File) {
                File f = (File) userObject;
                return f.getIdentifier().getValue();
            } else if (userObject instanceof BitStream) {
                BitStream bs =(BitStream) userObject;
                return bs.getIdentifier().getValue();
            } else if (userObject instanceof String) {
                return (String) userObject;
            } else {
                return "";
            }
        }
    }

    private class SipTreeSelectionListener implements TreeSelectionListener {

        private final JTree sipTree;

        public SipTreeSelectionListener(JTree sipTree) {
            this.sipTree = sipTree;
        }

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                    sipTree.getLastSelectedPathComponent();
            Object obj = node.getUserObject();
            if (obj instanceof IntellectualEntity){
                editEntity((IntellectualEntity)obj);
            }
        }

    }

}

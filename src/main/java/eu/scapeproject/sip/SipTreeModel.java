package eu.scapeproject.sip;

import java.util.Map;
import java.util.Map.Entry;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import eu.scapeproject.model.IntellectualEntity;
import eu.scapeproject.model.Representation;
import eu.scapeproject.model.metadata.dc.DCMetadata;

class SipTreeModel extends DefaultTreeModel {

	/**
	 * 
	 */
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
			SipTreeNode sipNode = new SipTreeNode(s);
			SipTreeNode entitiesNode = new SipTreeNode("Intellectual Entities",IconFactory.getInstance().getEntitiesIcon());
			for (IntellectualEntity entity : s.getEntities()) {
				SipTreeNode entityNode = new SipTreeNode(entity);
				// create dc entries
				DCMetadata dc = (DCMetadata) entity.getDescriptive();
				this.addDcNodes(entityNode, dc);
				if (entity.getRepresentations() != null) {
					SipTreeNode repsNode = new SipTreeNode("Representations",IconFactory.getInstance().getRepresentationsIcon());
					for (Representation rep : entity.getRepresentations()) {
						SipTreeNode repNode = new SipTreeNode(rep);
						SipTreeNode technMDNode = new SipTreeNode("Technical MD",IconFactory.getInstance().getMDIcon());
						SipTreeNode rightsMDNode = new SipTreeNode("Rights MD",IconFactory.getInstance().getMDIcon());
						SipTreeNode sourceMDNode = new SipTreeNode("Source MD",IconFactory.getInstance().getMDIcon());
						SipTreeNode provMDNode = new SipTreeNode("Digital Provenance MD",IconFactory.getInstance().getMDIcon());
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

	private void addDcNodes(SipTreeNode parent, DCMetadata dc) {
		SipTreeNode dcNode = new SipTreeNode("Descriptive Metadata",IconFactory.getInstance().getMDIcon());
		SipTreeNode dcContributorsNode = new SipTreeNode("Titles", dc.getTitle());
		SipTreeNode dcCoveragesNode = new SipTreeNode("Coverages", dc.getTitle());
		SipTreeNode dcCreatorsNode = new SipTreeNode("Contributors", dc.getTitle());
		SipTreeNode dcDatesNode = new SipTreeNode("Creators", dc.getTitle());
		SipTreeNode dcDescriptionsNode = new SipTreeNode("Dates", dc.getTitle());
		SipTreeNode dcFormatsNode = new SipTreeNode("Descriptions", dc.getTitle());
		SipTreeNode dcLanguagesNode = new SipTreeNode("Formats", dc.getTitle());
		SipTreeNode dcPublishersNode = new SipTreeNode("Languages", dc.getTitle());
		SipTreeNode dcRelationsNode = new SipTreeNode("Publishers", dc.getTitle());
		SipTreeNode dcRightsNode = new SipTreeNode("Relations", dc.getTitle());
		SipTreeNode dcSourcesNode = new SipTreeNode("Rights", dc.getTitle());
		SipTreeNode dcSubjectsNode = new SipTreeNode("Sources", dc.getTitle());
		SipTreeNode dcTitleNode = new SipTreeNode("Subjects", dc.getTitle());
		SipTreeNode dcTypesNode = new SipTreeNode("Types", dc.getTitle());
		dcNode.add(dcTitleNode);
		dcNode.add(dcCreatorsNode);
		dcNode.add(dcDatesNode);
		dcNode.add(dcDescriptionsNode);
		dcNode.add(dcContributorsNode);
		dcNode.add(dcCoveragesNode);
		dcNode.add(dcFormatsNode);
		dcNode.add(dcLanguagesNode);
		dcNode.add(dcPublishersNode);
		dcNode.add(dcRelationsNode);
		dcNode.add(dcRightsNode);
		dcNode.add(dcSourcesNode);
		dcNode.add(dcSubjectsNode);
		dcNode.add(dcTypesNode);
		parent.add(dcNode);
	}

	void setSips(Map<String, SIP> sips) {
		this.sips = sips;
		updateNodes();
	}

}
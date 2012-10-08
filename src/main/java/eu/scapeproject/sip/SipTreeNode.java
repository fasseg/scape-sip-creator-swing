package eu.scapeproject.sip;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;

import eu.scapeproject.model.BitStream;
import eu.scapeproject.model.File;
import eu.scapeproject.model.IntellectualEntity;
import eu.scapeproject.model.Representation;
import eu.scapeproject.model.metadata.dc.DCMetadata;

class SipTreeNode extends DefaultMutableTreeNode {

	private final String title;
	private ImageIcon icon;

	public SipTreeNode(IntellectualEntity e) {
		super(e);
		String tmp = null;
		if (e.getDescriptive() != null) {
			DCMetadata dc = (DCMetadata) e.getDescriptive();
			if (dc.getTitle() != null && dc.getTitle().size() > 0 && dc.getTitle().get(0).length() > 0) {
				tmp = dc.getTitle().get(0);
			}
		}
		if (tmp == null) {
			this.title = "Untitled entity";
		} else {
			this.title = tmp;
		}
		this.icon = IconFactory.getInstance().getEntityIcon();
	}

	public SipTreeNode(SIP sip) {
		super(sip);
		if (sip.getTitle() == null) {
			title = "Untitled SIP";
		} else {
			this.title = sip.getTitle();
		}
		this.icon = IconFactory.getInstance().getSipIcon();
	}

	public SipTreeNode(Representation r) {
		super(r);
		String tmp = null;
		if (r.getTitle() != null && r.getTitle().length() > 0) {
			tmp = r.getTitle();
		}
		if (tmp == null) {
			this.title = "Untitled representation";
		} else {
			this.title = tmp;
		}
		this.icon = IconFactory.getInstance().getRepresentationIcon();
	}

	public SipTreeNode(File f) {
		super(f);
		this.title = f.getIdentifier().getValue();
		this.icon = IconFactory.getInstance().getFileIcon();
	}

	public SipTreeNode(BitStream bs) {
		super(bs);
		this.title = bs.getIdentifier().getValue();
		this.icon = IconFactory.getInstance().getBitstreamIcon();
	}

	public SipTreeNode(String title,ImageIcon icon) {
		super(title);
		this.title = title;
		this.icon = icon;
	}

	public SipTreeNode(String title, ImageIcon icon,Object userObject) {
		super(userObject);
		this.title = title;
		this.icon = icon;
	}

	public SipTreeNode(Object userObject) {
		super(userObject);
		this.title = "Unknown Object type!";
	}

	public SipTreeNode(String title, List<String> data) {
		super(data);
		this.title = title;
		this.icon = IconFactory.getInstance().getEntryIcon();
	}

	@Override
	public String toString() {
		return title;
	}

	public ImageIcon getIcon() {
		return icon;
	}

}
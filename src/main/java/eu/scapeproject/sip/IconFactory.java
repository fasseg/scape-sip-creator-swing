package eu.scapeproject.sip;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public final class IconFactory {
	private static IconFactory INSTANCE;
	private ImageIcon packageIcon;
	private ImageIcon representationIcon;
	private ImageIcon entityIcon;
	private ImageIcon fileIcon;
	private ImageIcon bitstreamIcon;
	private ImageIcon entryIcon;
	private ImageIcon dcIcon;
	private ImageIcon entitiesIcon;
	private ImageIcon representationsIcon;

	private IconFactory() {
		BufferedImage orig;
		try {
			orig = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("package.png"));
			packageIcon = new ImageIcon(orig.getScaledInstance(16, 16, BufferedImage.SCALE_FAST));
			orig = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("representation.png"));
			representationIcon = new ImageIcon(orig.getScaledInstance(16, 16, BufferedImage.SCALE_FAST));
			orig = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("entity.png"));
			entityIcon = new ImageIcon(orig.getScaledInstance(16, 16, BufferedImage.SCALE_FAST));
			orig = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("file.png"));
			fileIcon = new ImageIcon(orig.getScaledInstance(16, 16, BufferedImage.SCALE_FAST));
			orig = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("bitstream.png"));
			bitstreamIcon = new ImageIcon(orig.getScaledInstance(16, 16, BufferedImage.SCALE_FAST));
			orig = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("entry.png"));
			entryIcon = new ImageIcon(orig.getScaledInstance(16, 16, BufferedImage.SCALE_FAST));
			orig = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("dc.png"));
			dcIcon = new ImageIcon(orig.getScaledInstance(16, 16, BufferedImage.SCALE_FAST));
			orig = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("entities.png"));
			entitiesIcon = new ImageIcon(orig.getScaledInstance(16, 16, BufferedImage.SCALE_FAST));
			orig = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("representations.png"));
			representationsIcon = new ImageIcon(orig.getScaledInstance(16, 16, BufferedImage.SCALE_FAST));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static IconFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new IconFactory();
		}
		return INSTANCE;
	}

	public ImageIcon getEntitiesIcon() {
		return entitiesIcon;
	}

	public ImageIcon getRepresentationsIcon() {
		return representationsIcon;
	}

	public ImageIcon getDcIcon() {
		return dcIcon;
	}

	public ImageIcon getSipIcon() {
		return packageIcon;
	}

	public ImageIcon getBitstreamIcon() {
		return bitstreamIcon;
	}

	public ImageIcon getEntityIcon() {
		return entityIcon;
	}

	public ImageIcon getFileIcon() {
		return fileIcon;
	}

	public ImageIcon getPackageIcon() {
		return packageIcon;
	}

	public ImageIcon getRepresentationIcon() {
		return representationIcon;
	}

	public ImageIcon getEntryIcon() {
		return entryIcon;
	}
}

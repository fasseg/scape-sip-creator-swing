package eu.scapeproject.sip;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.tree.TreeCellRenderer;

public class SipTreeCellRenderer implements TreeCellRenderer {

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		JPanel panel = new JPanel(new BorderLayout(3, 0));
		panel.setBackground(Color.WHITE);
		if (value instanceof SipTreeNode) {
			SipTreeNode node = (SipTreeNode) value;
			JLabel iconLabel = new JLabel(node.getIcon());
			JLabel titleLabel = new JLabel(value.toString());
			panel.add(iconLabel, BorderLayout.WEST);
			panel.add(titleLabel, BorderLayout.CENTER);
		}
		return panel;
	}
}

package eu.scapeproject.sip;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eu.scapeproject.model.IntellectualEntity;
import eu.scapeproject.model.metadata.dc.DCMetadata;

public class EditEntityPanel extends JPanel{
    private JLabel titleLabel;
    private JTextField titleField;
    
    public EditEntityPanel(IntellectualEntity e) {
        titleLabel = new JLabel("Title");
        titleField = new JTextField();
        if (e.getDescriptive() != null){
            DCMetadata dc = (DCMetadata) e.getDescriptive();
            if (dc.getTitle() != null && dc.getTitle().size() > 0){
                titleField.setText(dc.getTitle().get(0));
            }
        }
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.25f;
        this.add(titleLabel,c);
        c.gridx = 1;
        c.weightx = 0.75f;
        this.add(titleField,c);
    }
}

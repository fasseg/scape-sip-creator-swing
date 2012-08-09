package eu.scapeproject.sip;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EditSIPPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private JTextField title = new JTextField();
    private JTextArea desc = new JTextArea();
    private JButton cancel  = new JButton("Cancel");
    private JButton ok = new JButton("Ok");

    public EditSIPPanel(SIP sip) {
        if (sip != null) {
            title.setText(sip.getTitle());
            desc.setText(sip.getDesc());
        }
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0f;
        c.weighty = 0f;
        c.gridy = 0;
        c.gridx = 0;
        this.add(new JLabel("Title"), c);
        c.gridy = 1;
        this.add(title, c);
        c.gridy = 2;
        this.add(new JLabel("Description"), c);
        c.gridy = 3;
        desc.setRows(5);
        desc.setBorder(BorderFactory.createEtchedBorder());
        this.add(desc, c);
        c.gridy = 4;
        c.anchor = GridBagConstraints.SOUTHEAST;
        this.add(cancel,c);
        c.gridy = 5;
        this.add(ok,c);
    }

}

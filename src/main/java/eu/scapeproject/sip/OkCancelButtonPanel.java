package eu.scapeproject.sip;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

public class OkCancelButtonPanel extends JPanel {
    private JButton okButton;
    private JButton cancelButton;
    private Action ok;
    private Action cancel;

    public OkCancelButtonPanel(Action ok, Action cancel) {
        okButton = new JButton(ok);
        cancelButton = new JButton(cancel);
        this.ok = ok;
        this.cancel = cancel;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.SOUTHEAST;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(4, 4, 4, 4);
        this.add(okButton, c);
        c.gridx = 1;
        this.add(cancelButton);
    }
}

package eu.scapeproject.sip;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class SipPanel extends JPanel {
    private JTextField title = new JTextField();
    private JLabel size = new JLabel();
    private JTextArea descr = new JTextArea();
    private SIP sip;

    private DocumentListener updateListener = new DocumentListener() {

        @Override
        public void removeUpdate(DocumentEvent e) {
            try {
                sip.setTitle(e.getDocument().getText(0, e.getDocument().getLength()));
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            try {
                sip.setTitle(e.getDocument().getText(0, e.getDocument().getLength()));
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            try {
                sip.setTitle(e.getDocument().getText(0, e.getDocument().getLength()));
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }
    };

    public SipPanel(SIP s) {
        Font norm = UIManager.getFont("TextField.font");
        this.title.setText("aölksjda aöskdak laksdöl aköl a");
        this.size.setText("109821,2983 MByte");
        this.descr.setText("öalkjödlkasfjkfasfsalkfölksafölksöflsf\nkjaäsaklkaöldkalsdaskdjölkfds\nölkajsdasddöalkjödlkasfjkfasfsalkfölksafölksöflsf\nkjaäsaklkaöldkalsdaskdjölkfds\nölkajsdasdd\nöalkjödlkasfjkfasfsalkfölksafölksöflsf\nkjaäsaklkaöldkalsdaskdjölkfds\nölkajsdasddöalkjödlkasfjkfasfsalkfölksafölksöflsf\nkjaäsaklkaöldkalsdaskdjölkfds\nölkajsdasddöalkjödlkasfjkfasfsalkfölksafölksöflsf\nkjaäsaklkaöldkalsdaskdjölkfds\nölkajsdasddöalkjödlkasfjkfasfsalkfölksafölksöflsf\nkjaäsaklkaöldkalsdaskdjölkfds\nölkajsdasddöalkjödlkasfjkfasfsalkfölksafölksöflsf\nkjaäsaklkaöldkalsdaskdjölkfds\nölkajsdasddöalkjödlkasfjkfasfsalkfölksafölksöflsf\nkjaäsaklkaöldkalsdaskdjölkfds\nölkajsdasddöalkjödlkasfjkfasfsalkfölksafölksöflsf\nkjaäsaklkaöldkalsdaskdjölkfds\nölkajsdasdd");
        this.title.setFont(norm);
        this.title.getDocument().addDocumentListener(updateListener);
        this.size.setFont(norm);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.NORTHEAST;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(8, 8, 2, 0);
        this.add(new JLabel("Title"), c);
        c.insets = new Insets(2, 8, 2, 0);
        c.gridy = 2;
        this.add(new JLabel("Size"), c);
        c.gridy = 4;
        this.add(new JLabel("Description"), c);
        c.gridy = 1;
        c.gridwidth = 2;
        this.add(title, c);
        c.gridy = 3;
        this.add(size, c);
        c.gridy = 5;
        this.add(descr, c);
        c.gridy = 6;
        c.weighty = 1.0f;
        this.add(Box.createVerticalGlue(), c);
        c.gridx = 2;
        c.weighty = 0f;
        c.weightx = 1.0f;
        this.add(Box.createHorizontalGlue(), c);

        // actions

    }

}

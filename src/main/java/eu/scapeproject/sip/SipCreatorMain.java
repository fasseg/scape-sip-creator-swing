package eu.scapeproject.sip;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SipCreatorMain extends JFrame {
    
    private static final long serialVersionUID = 1L;

    private JPanel sipListPanel = new SipListPanel(this);

    public SipCreatorMain() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("SCAPE Sip Creation Utility");
    }

    public void showSipList() {
        this.setContentPane(sipListPanel);
        this.pack();
    }
    
    public void showAddSip(SIP sip){
        this.setContentPane(new EditSIPPanel(sip));
        this.pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SipCreatorMain main = new SipCreatorMain();
                main.setLocationRelativeTo(null);
                main.setVisible(true);
                main.showSipList();
            };
        });
    }
}

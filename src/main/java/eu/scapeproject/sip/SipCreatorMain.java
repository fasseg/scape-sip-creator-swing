package eu.scapeproject.sip;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eu.scapeproject.model.BitStream;
import eu.scapeproject.model.File;
import eu.scapeproject.model.IntellectualEntity;
import eu.scapeproject.model.Representation;

@SuppressWarnings("serial")
public class SipCreatorMain extends JFrame {
	
	private static final Logger LOG = LoggerFactory.getLogger(SipCreatorMain.class);
	
    private JMenuBar mainMenu;
    private OverviewPanel overview;

    private Map<String, SIP> sips = new HashMap<String, SIP>();


    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Runnable() {
    		
    		public void run() {
    			try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
    			UIManager.put("TextArea.margin", new Insets(4,4,4,4));
    			SipCreatorMain sipCreator = new SipCreatorMain();
    			sipCreator.setVisible(true);
    		}
    	});
    }

    private Action closeAction = new AbstractAction("Exit", null) {

        public void actionPerformed(ActionEvent e) {
            SipCreatorMain.this.close();
        }
    };

    private Action saveSipsAction = new AbstractAction("Save...",null) {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            SIP sip = sips.entrySet().iterator().next().getValue();
            new SipStoreWorker(sip, "test.zip").execute();
        }
    };
    
    private Action newSipAction = new AbstractAction("New SIP", null) {

        public void actionPerformed(ActionEvent e) {
            SIP sip = new SIP();
            sip.setTitle(getNextUntitledName());

            BitStream bs = new BitStream.Builder()
                    .build();
            File file = new File.Builder()
                    .bitStream(bs)
                    .build();
            Representation rep = new Representation.Builder()
                    .file(file)
                    .build();
            IntellectualEntity entity = new IntellectualEntity.Builder()
                    .representations(Arrays.asList(rep))
                    .build();
            sip.addEntity(entity);
            sips.put(sip.getTitle(), sip);
            SipCreatorMain.this.updateTree();
            SipCreatorMain.this.setCurrentSip(sip);
        }
    };

    public SipCreatorMain() {
        this.setSize(1024, 800);
        this.setTitle("SCAPE SIP Creation Utility v0.0.1");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        mainMenu = createMenu();
        this.setJMenuBar(mainMenu);
        this.showOverView();

    }

    private void close() {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private JMenuBar createMenu() {
        JMenuBar main = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newSIP = new JMenuItem(newSipAction);
        fileMenu.add(newSIP);
        JMenuItem saveSIP = new JMenuItem(saveSipsAction);
        fileMenu.add(saveSIP);
        fileMenu.addSeparator();
        JMenuItem exitItem = new JMenuItem(closeAction);
        fileMenu.add(exitItem);
        main.add(fileMenu);
        return main;

    }

    protected String getNextUntitledName() {
        String prefix = "Untitled ";
        int idx = 1;
        while (sips.containsKey(prefix + idx)) {
            idx++;
        }
        return prefix + idx;
    }

    protected void setCurrentSip(SIP sip) {
    }

    private void showOverView() {
        if (overview == null) {
            overview = new OverviewPanel(sips,this.getWidth() / 3);
        }
        this.setContentPane(overview);
    }

    protected void updateTree() {
        overview.setSips(sips);
    }
}

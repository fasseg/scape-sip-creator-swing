package eu.scapeproject.sip;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class SipListPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private JTable sipTable;
    private TableModel sipTableModel;
    private SipCreatorMain main;

    public SipListPanel(SipCreatorMain main) {
        this.main = main;
        sipTableModel = new SipListModel(new ArrayList<SIP>());
        sipTable = new JTable(sipTableModel);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 1.0f;
        c.weighty = 1.0f;
        c.gridy = 0;
        c.gridx = 0;
        this.add(new JScrollPane(sipTable), c);
        c.fill = GridBagConstraints.NONE;
        c.weightx= 0.1f;
        c.gridx = 1;
        c.gridy = 0;
        this.add(new ButtonPanel(),c);
    }
    
    public void addNewSIP() {
        main.showAddSip(null);
    }
    
    private class ButtonPanel extends JPanel{
        private JButton addSipButton = new JButton("Add");
        private JButton deleteSipButton = new JButton("Delete");
        private JButton editSipButton = new JButton("Edit");
        
        
        
        public ButtonPanel() {
            this.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.NONE;
            c.anchor = GridBagConstraints.NORTHWEST;
            c.weightx = 1;
            c.weighty = 1;
            c.gridy = 0;
            c.gridx = 0;
            this.add(addSipButton,c);
            c.gridy = 1;
            c.gridx = 0;
            this.add(deleteSipButton,c);
            c.gridy = 2;
            c.gridx = 0;
            this.add(editSipButton,c);
            this.addSipButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addNewSIP();
                }
            });
        }
    }

    private class SipListModel extends AbstractTableModel {

        private List<SIP> sips;

        SipListModel(List<SIP> sips) {
            this.sips = sips;
        }

        public int getRowCount() {
            return sips.size();
        }

        public int getColumnCount() {
            return 2;
        }

        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
            case 0:
                return "Location";
            case 1:
                return "Size";
            default:
                return "";
            }
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
            case 0:
                return sips.get(rowIndex).getLocation();
            case 1:
                return sips.get(rowIndex).getSize();
            default:
                return null;
            }
        }

    }
}

package eu.scapeproject.sip;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import eu.scapeproject.model.IntellectualEntity;
import eu.scapeproject.model.metadata.dc.DCMetadata;

public class EditEntityPanel extends JPanel {
    private final JLabel labelIds = new JLabel("IDs");
    private final JLabel labelTitles = new JLabel("Titles");
    private final JLabel labelDates = new JLabel("Dates");
    private final JLabel labelCreators = new JLabel("Creators");
    private final JLabel labelCoverages = new JLabel("Coverages");
    private final JLabel labelDescriptions = new JLabel("Descriptions");
    private final JLabel labelFormats = new JLabel("Formats");
    private final JLabel labelLanguages = new JLabel("Languages");
    private final JLabel labelPublishers = new JLabel("Publishers");
    private final JLabel labelSubjects = new JLabel("Subjects");
    private final JLabel labelTypes = new JLabel("Types");
    private final JLabel labelSources = new JLabel("Sources");
    private final JLabel labelRelations = new JLabel("Relations");
    private final JLabel labelContributors = new JLabel("Contributors");
    private final JLabel labelRights = new JLabel("Rights");
    
    private final JTextField idField = new JTextField();
    private final JList titleList;

    private final OkCancelButtonPanel buttonPanel;
    private final OverviewPanel overview;
    private IntellectualEntity entity;

    private Action ok = new AbstractAction("Ok", null) {

        @Override
        public void actionPerformed(ActionEvent e) {
            overview.hideDetails();
        }
    };

    private Action cancel = new AbstractAction("Cancel", null) {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

        }
    };

    public EditEntityPanel(OverviewPanel overview, IntellectualEntity e) {
        this.entity = e;
        this.overview = overview;
        DCMetadata dc = (DCMetadata) e.getDescriptive();
        buttonPanel = new OkCancelButtonPanel(ok, cancel);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        this.add(labelIds, c);
        c.gridx=1;
        idField.setText(dc.getId());
        this.add(idField,c);
        c.gridx = 0;
        c.gridy = 1;
        this.add(labelTitles, c);
        c.gridx=1;
        titleList = new JList(dc.getTitle());
        this.add(titleTable,c);
        c.gridx = 0;
        c.gridy = 2;
        this.add(labelDates, c);
        c.gridx = 0;
        c.gridy = 3;
        this.add(labelCreators, c);
        c.gridx = 0;
        c.gridy = 4;
        this.add(labelCoverages, c);
        c.gridx = 0;
        c.gridy = 5;
        this.add(labelDescriptions, c);
        c.gridx = 0;
        c.gridy = 6;
        this.add(labelFormats, c);
        c.gridx = 0;
        c.gridy = 7;
        this.add(labelLanguages, c);
        c.gridx = 0;
        c.gridy = 8;
        this.add(labelPublishers, c);
        c.gridx = 0;
        c.gridy = 9;
        this.add(labelSubjects, c);
        c.gridx = 0;
        c.gridy = 10;
        this.add(labelTypes, c);
        c.gridx = 0;
        c.gridy = 11;
        this.add(labelSources, c);
        c.gridx = 0;
        c.gridy = 12;
        this.add(labelRelations, c);
        c.gridx = 0;
        c.gridy = 13;
        this.add(labelContributors, c);
        c.gridx = 0;
        c.gridy = 14;
        this.add(labelRights, c);
        c.gridx = 0;
        c.gridy = 15;
        this.add(buttonPanel,c);

    }
}

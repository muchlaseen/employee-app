/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author muchlas
 */
public class FormPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel ageLabel;
    private JTextField nameField;
    private JTextField ageField;
    private JButton okBtn;
    private FormListener formListener;
    private JList ageList;
    private JComboBox empBox;
    private JCheckBox citizenCheck;
    private JTextField taxField;
    private JLabel taxLabel;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;

    public FormPanel() {
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        // Init Components
        nameLabel = new JLabel("Name: ");
        ageLabel = new JLabel("Age: ");
        nameField = new JTextField(10);
        ageField = new JTextField(10);
        ageList = new JList();
        empBox = new JComboBox();
        citizenCheck = new JCheckBox();
        taxField = new JTextField(10);
        taxLabel = new JLabel("Tax ID: ");
        okBtn = new JButton("OK");
        
        // Set up mnemonics
        okBtn.setMnemonic(KeyEvent.VK_O);
        
        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);

        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");

        maleRadio.setActionCommand("Male");
        femaleRadio.setActionCommand("Female");

        genderGroup = new ButtonGroup();

        maleRadio.setSelected(true);

        // Set up gender radios
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // Set up Tax ID
        taxLabel.setEnabled(false);
        taxField.setEnabled(false);

        citizenCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isTicked = citizenCheck.isSelected();
                taxLabel.setEnabled(isTicked);
                taxField.setEnabled(isTicked);
            }
        });

        // Set up ListBox
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0, "Married"));
        ageModel.addElement(new AgeCategory(1, "Single"));
        ageModel.addElement(new AgeCategory(2, "Other"));
        ageList.setModel(ageModel);

        ageList.setPreferredSize(new Dimension(110, 70));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);

        // Set up ComboBox
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("Software Engineer");
        empModel.addElement("Network Engineer");
        empModel.addElement("Admin");
        empBox.setModel(empModel);
        empBox.setSelectedIndex(0);

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String age = ageField.getText();
                AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue();
                String empCat = (String) empBox.getSelectedItem();
                String taxId = taxField.getText();
                boolean usCitizen = citizenCheck.isSelected();

                String gender = genderGroup.getSelection().getActionCommand();

                FormEvent ev = new FormEvent(this, name, age, ageCat.getId(), empCat, taxId, usCitizen, gender);

                if (formListener != null) {
                    formListener.formEventOccured(ev);
                }
            }

        });

        // Create Main Border
        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponent();
    }

    public void layoutComponent() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        //////// First Row ////////
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gc);

        //////// Second Row ////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(ageLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(ageField, gc);

        //////// Next Row ////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Status: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(ageList, gc);

//        //////// Next Row ////////
//        gc.gridy++;
//
//        gc.weightx = 1;
//        gc.weighty = 0.2;
//
//        gc.gridx = 0;
//        gc.insets = new Insets(0, 0, 0, 5);
//        gc.anchor = GridBagConstraints.FIRST_LINE_END;
//        add(new JLabel("US Citizen: "), gc);
//
//        gc.gridx = 1;
//        gc.anchor = GridBagConstraints.FIRST_LINE_START;
//        gc.insets = new Insets(0, 0, 0, 0);
//        add(citizenCheck, gc);

        //////// Next Row ////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(taxLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(taxField, gc);
        
          //////// Next Row ////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Department: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(empBox, gc);

        //////// Next Row ////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Gender: "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(maleRadio, gc);

        //////// Next Row ////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(femaleRadio, gc);

        //////// Next Row ////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(okBtn, gc);
    }

    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }
}

class AgeCategory {

    private int id;
    private String text;

    public AgeCategory(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public String toString() {
        return text;
    }

    public int getId() {
        return id;
    }

}

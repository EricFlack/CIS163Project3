package BankProgram;


import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.*;

/**
 * Created by flackeri on 10/23/15.
 */
public class BankGUI extends JFrame {

    /** menu bar for JFrame */
    private JMenuBar mBar;

    /** two menus for the menu bar */
    private JMenu mnuFile;
    private JMenu mnuSort;

    /** menu items for file menu */
    private JMenuItem miLoadFromBinary;
    private JMenuItem miSaveAsBinary;
    private JMenuItem miLoadFromText;
    private JMenuItem miSaveAsText;
    private JMenuItem miLoadFromXML;
    private JMenuItem miSaveAsXML;
    private JMenuItem miQuit;

    /** menu itmes for the sort menu */
    private JMenuItem miSortByNum;
    private JMenuItem miSortByOwner;
    private JMenuItem miSortByDate;

    /** bank model object, to be used in table */
    private BankModel dTableModel;

    /** JTable and JScrollPane */
    private JTable acctTable;
    private JScrollPane scrollPane;

    /** radio buttons to select checking or savings */
    private JRadioButton rdoChecking;
    private JRadioButton rdoSavings;

    /** labels to direct user input */
    private JLabel lblAcctNum;
    private JLabel lblAcctOwner;
    private JLabel lblDateOpen;
    private JLabel lblAcctBal;
    private JLabel lblMonthlyFee;
    private JLabel lblIntRate;
    private JLabel lblMinBal;

    /** text fields for input */
    private JTextField txtAcctNum;
    private JTextField txtAcctOwner;
    private JTextField txtAcctBal;
    private JTextField txtMonthlyFee;
    private JTextField txtIntRate;
    private JTextField txtMinBal;

    /** calendar drop down for date opened */
    private JDateChooser calDateOpen;

    /** buttons for user operations */
    private JButton btnAddAcct;
    private JButton btnDeleteAcct;
    private JButton btnUpdateAcct;
    private JButton btnClearAccts;

    /** panels to organize JFrame layout */
    private JPanel mainPanel;
    private JPanel infoEntryPanel;
    private JPanel radioPanel;
    private JPanel textFieldsPanel;
    private JPanel buttonPanel;

    public BankGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bank Application");

        //create action listener
        ButtonListener listener = new ButtonListener();

        //menu bar, menus, and menu items
        mBar = new JMenuBar();
        mnuFile = new JMenu("File");
        mnuSort = new JMenu("Sort");
        miLoadFromBinary = new JMenuItem("Load From Binary");
        miSaveAsBinary = new JMenuItem("Save As Binary");
        miLoadFromText = new JMenuItem("Load From Text");
        miSaveAsText = new JMenuItem("Save As Text");
        miLoadFromXML = new JMenuItem("Load From XML");
        miSaveAsXML = new JMenuItem("Save As XML");
        miQuit = new JMenuItem("Quit");
        miSortByNum = new JMenuItem("By Account Number");
        miSortByOwner = new JMenuItem("By Account Owner");
        miSortByDate = new JMenuItem("By Date Opened");

        //add action listener to menu items
        miLoadFromBinary.addActionListener(listener);
        miSaveAsBinary.addActionListener(listener);
        miLoadFromText.addActionListener(listener);
        miSaveAsText.addActionListener(listener);
        miLoadFromXML.addActionListener(listener);
        miSaveAsXML.addActionListener(listener);
        miQuit.addActionListener(listener);
        miSortByNum.addActionListener(listener);
        miSortByOwner.addActionListener(listener);
        miSortByDate.addActionListener(listener);

        //add menu items to file menu
        mnuFile.add(miLoadFromBinary);
        mnuFile.add(miSaveAsBinary);
        mnuFile.addSeparator();
        mnuFile.add(miLoadFromText);
        mnuFile.add(miSaveAsText);
        mnuFile.addSeparator();
        mnuFile.add(miLoadFromXML);
        mnuFile.add(miSaveAsXML);
        mnuFile.addSeparator();
        mnuFile.add(miQuit);

        //add menu items to sort menu
        mnuSort.add(miSortByNum);
        mnuSort.add(miSortByOwner);
        mnuSort.add(miSortByDate);

        //add menus to menu bar, and menu bar to frame
        mBar.add(mnuFile);
        mBar.add(mnuSort);
        setJMenuBar(mBar);

        //set up the JTable
        dTableModel = new BankModel();
        acctTable = new JTable(dTableModel);
        scrollPane = new JScrollPane(acctTable);

        //set up radio button panel
        radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(1, 2));
        radioPanel.setBackground(Color.lightGray);
        rdoChecking = new JRadioButton("Checking");
        rdoSavings = new JRadioButton("Savings");
        rdoChecking.addActionListener(listener);
        rdoSavings.addActionListener(listener);
        radioPanel.add(rdoChecking);
        radioPanel.add(rdoSavings);

        //set up text fields panel
        textFieldsPanel = new JPanel();
        textFieldsPanel.setLayout(new GridLayout(7, 2));
        textFieldsPanel.setBackground(Color.lightGray);
        lblAcctNum = new JLabel("Account Number:");
        lblAcctOwner = new JLabel("Account Owner:");
        lblDateOpen = new JLabel("Date Opened:");
        lblAcctBal = new JLabel("Account Balance:");
        lblMonthlyFee = new JLabel("Monthly Fee:");
        lblIntRate = new JLabel("Interest Rate:");
        lblMinBal = new JLabel("Minimum Balance:");
        txtAcctNum = new JTextField();
        txtAcctOwner = new JTextField();
        calDateOpen = new JDateChooser();
        txtAcctBal = new JTextField();
        txtMonthlyFee = new JTextField();
        txtIntRate = new JTextField();
        txtMinBal = new JTextField();
        textFieldsPanel.add(lblAcctNum);
        textFieldsPanel.add(txtAcctNum);
        textFieldsPanel.add(lblAcctOwner);
        textFieldsPanel.add(txtAcctOwner);
        textFieldsPanel.add(lblDateOpen);
        textFieldsPanel.add(calDateOpen);
        textFieldsPanel.add(lblAcctBal);
        textFieldsPanel.add(txtAcctBal);
        textFieldsPanel.add(lblMonthlyFee);
        textFieldsPanel.add(txtMonthlyFee);
        textFieldsPanel.add(lblIntRate);
        textFieldsPanel.add(txtIntRate);
        textFieldsPanel.add(lblMinBal);
        textFieldsPanel.add(txtMinBal);

        //set initial radio button and text field status
        rdoChecking.setSelected(true);
        txtIntRate.setEnabled(false);
        txtMinBal.setEnabled(false);

        //set up buttons panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.setBackground(Color.GRAY);
        btnAddAcct = new JButton("Add");
        btnDeleteAcct = new JButton("Delete");
        btnUpdateAcct = new JButton("Update");
        btnClearAccts = new JButton("Clear");
        btnAddAcct.addActionListener(listener);
        btnDeleteAcct.addActionListener(listener);
        btnUpdateAcct.addActionListener(listener);
        btnClearAccts.addActionListener(listener);
        buttonPanel.add(btnAddAcct);
        buttonPanel.add(btnDeleteAcct);
        buttonPanel.add(btnUpdateAcct);
        buttonPanel.add(btnClearAccts);

        //set up information entry panel
        infoEntryPanel = new JPanel();
        infoEntryPanel.setLayout(new BorderLayout());
        infoEntryPanel.setBorder(BorderFactory.createEtchedBorder());
        infoEntryPanel.setBackground(Color.GRAY);
        infoEntryPanel.add(radioPanel, BorderLayout.NORTH);
        infoEntryPanel.add(textFieldsPanel, BorderLayout.CENTER);
        infoEntryPanel.add(buttonPanel, BorderLayout.EAST);

        //set up main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        mainPanel.setBackground(Color.lightGray);
        mainPanel.add(scrollPane);
        mainPanel.add(infoEntryPanel);

        getContentPane().add(mainPanel);
        pack();
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == rdoChecking) {
                rdoChecking.setSelected(true);
                rdoSavings.setSelected(false);
                txtMonthlyFee.setEnabled(true);
                txtIntRate.setEnabled(false);
                txtMinBal.setEnabled(false);
            }

            if (e.getSource() == rdoSavings) {
                rdoChecking.setSelected(false);
                rdoSavings.setSelected(true);
                txtMonthlyFee.setEnabled(false);
                txtIntRate.setEnabled(true);
                txtMinBal.setEnabled(true);
            }

            if (miQuit == e.getSource()) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        BankGUI launch = new BankGUI();
    }
}

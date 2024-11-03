package stockmanagement;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class MainGUI extends JFrame {
    private int index = 0;
    private Map<String, Integer> globalStockQuantities = new HashMap<>();
    public MainGUI() {
        super("Investment Portfolio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);
        setForeground(Color.CYAN);
        Font font = new Font("CALIBRI", Font.BOLD, 25); 
        // Modified by Harsh
        // Button declarations
        JButton menuProfile = new JButton("Profile");
        JButton menuBuyBtn = new JButton("Buy Investment");
        JButton menuSellBtn = new JButton("Sell Investment");
        JButton menuUpdateBtn = new JButton("Update Prices");
        JButton menuGetGain = new JButton("Get Gain");
        JButton NavsaveAs = new JButton("Save");
        JButton loadNavBtn = new JButton("Load");
        JButton quitNavBtn = new JButton("Quit");

        // Font settings
        menuProfile.setFont(font);
        menuBuyBtn.setFont(font);
        menuSellBtn.setFont(font);
        menuUpdateBtn.setFont(font);
        menuGetGain.setFont(font);
        NavsaveAs.setFont(font);
        loadNavBtn.setFont(font);
        quitNavBtn.setFont(font);

        // Background color settings
        menuProfile.setBackground(Color.CYAN);
        menuBuyBtn.setBackground(Color.CYAN);
        menuSellBtn.setBackground(Color.CYAN);
        menuUpdateBtn.setBackground(Color.CYAN);
        menuGetGain.setBackground(Color.CYAN);
        NavsaveAs.setBackground(Color.CYAN);
        loadNavBtn.setBackground(Color.CYAN);
        quitNavBtn.setBackground(Color.RED);        
        
        // Declarations
        JPanel topPanel = new JPanel();
        JTextArea welcomeArea = new JTextArea(10, 10);
        JPanel buyPanelOutput = new JPanel();
        JTextArea messageArea = new JTextArea(10, 10);
        JLabel msgLbl = new JLabel("                     Messages");
        JPanel sellPanelOutput = new JPanel();
        JTextArea sellMessageArea = new JTextArea(10, 10);
        JLabel sellMsgLbl = new JLabel("Messages");
        JPanel updatePanelOutput = new JPanel();
        JTextArea updateMessageArea = new JTextArea(10, 10);
        JLabel updateMsgLbl = new JLabel("Messages");
        JPanel getGainOutput = new JPanel();
        JTextArea getGainMsgArea = new JTextArea(10, 10);
        JLabel getGainLbl = new JLabel("Individual Gains");
        JPanel profileResults = new JPanel();

        // Layout configurations
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        buyPanelOutput.setLayout(new BoxLayout(buyPanelOutput, BoxLayout.Y_AXIS));
        sellPanelOutput.setLayout(new BoxLayout(sellPanelOutput, BoxLayout.Y_AXIS));
        updatePanelOutput.setLayout(new BoxLayout(updatePanelOutput, BoxLayout.Y_AXIS));
        getGainOutput.setLayout(new BoxLayout(getGainOutput, BoxLayout.Y_AXIS));
        profileResults.setLayout(new BoxLayout(profileResults, BoxLayout.Y_AXIS));

        // Adding components to panels
        topPanel.add(menuProfile);
        topPanel.add(menuBuyBtn);
        topPanel.add(menuSellBtn);
        topPanel.add(menuUpdateBtn);
        topPanel.add(menuGetGain);
        topPanel.add(NavsaveAs);
        topPanel.add(loadNavBtn);
        topPanel.add(quitNavBtn);
        
        buyPanelOutput.add(msgLbl);
        buyPanelOutput.add(new JScrollPane(messageArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        sellPanelOutput.add(sellMsgLbl);
        sellPanelOutput.add(new JScrollPane(sellMessageArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        updatePanelOutput.add(updateMsgLbl);
        updatePanelOutput.add(new JScrollPane(updateMessageArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        getGainOutput.add(getGainLbl);
        getGainOutput.add(new JScrollPane(getGainMsgArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        // Font settings
        welcomeArea.setFont(font);
        messageArea.setFont(font);
        msgLbl.setFont(font);
        sellMessageArea.setFont(font);
        sellMsgLbl.setFont(font);
        updateMessageArea.setFont(font);
        updateMsgLbl.setFont(font);
        getGainMsgArea.setFont(font);
        getGainLbl.setFont(font);

        // Alignment and text settings
        msgLbl.setBorder(new EmptyBorder(10, 0, 0, 0));
        sellMsgLbl.setBorder(new EmptyBorder(10, 0, 0, 0));
        updateMsgLbl.setBorder(new EmptyBorder(10, 0, 0, 0));
        getGainLbl.setBorder(new EmptyBorder(10, 0, 0, 0));
        welcomeArea.setText("\n                                                     Welcome to your Stock Management System.\n");
        welcomeArea.setLineWrap(true);
        welcomeArea.setWrapStyleWord(true);
        messageArea.setEditable(false);
        sellMessageArea.setEditable(false);
        updateMessageArea.setEditable(false);
        getGainMsgArea.setEditable(false);
        welcomeArea.setEditable(false);

        // Background and foreground color settings
        topPanel.setBackground(Color.BLACK);
        welcomeArea.setBackground(Color.BLACK);
        welcomeArea.setForeground(Color.CYAN);
        buyPanelOutput.setBackground(Color.BLACK);
        messageArea.setBackground(Color.BLACK);
        msgLbl.setBackground(Color.BLACK);
        msgLbl.setForeground(Color.CYAN);
        sellPanelOutput.setBackground(Color.BLACK);
        sellMessageArea.setBackground(Color.BLACK);
        sellMsgLbl.setForeground(Color.CYAN);
        updatePanelOutput.setBackground(Color.BLACK);
        updateMessageArea.setBackground(Color.BLACK);
        updateMsgLbl.setForeground(Color.CYAN);
        getGainOutput.setBackground(Color.BLACK);
        getGainMsgArea.setBackground(Color.BLACK);
        getGainLbl.setForeground(Color.CYAN);
        profileResults.setBackground(Color.BLACK);

        // Border settings
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buyPanelOutput.setBorder(new EmptyBorder(10, 0, 0, 0));
        sellPanelOutput.setBorder(new EmptyBorder(10, 0, 0, 0));
        updatePanelOutput.setBorder(new EmptyBorder(10, 0, 0, 0));
        getGainOutput.setBorder(new EmptyBorder(10, 0, 0, 0));
        messageArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        sellMessageArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        updateMessageArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        getGainMsgArea.setBorder(new EmptyBorder(10, 10, 10, 10));    
        
       // Initialize dataset with default values
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("No Stocks", 100);

        // Create chart
        JFreeChart pieChart = ChartFactory.createPieChart(
            "Investment Distribution", // Chart title
            dataset, // Dataset
            true, // Include legend
            true,
            false);

        // Customize the plot
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setSectionPaint("No Stocks", Color.BLUE);
        plot.setBackgroundPaint(Color.BLACK);
        plot.setOutlineVisible(false);
        plot.setLabelBackgroundPaint(Color.BLACK);
        plot.setLabelPaint(Color.CYAN);

        // Add chart to a panel
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
        profileResults.add(chartPanel, BorderLayout.SOUTH);
        
        ///////////////////////////////////////////////////////////////////////// MODIFIED BY HARSH
                                            // BUY Window Starts Here //
        // Declarations
        JPanel buyPanelWrapper = new JPanel();
        JPanel buyPanel = new JPanel();
        JLabel buyLbl = new JLabel("Buying an investment");
        JLabel typeLbl = new JLabel("Type: ");
        JLabel symbolLbl = new JLabel("Symbol: ");
        JLabel nameLbl = new JLabel("Name: ");
        JLabel quantityLbl = new JLabel("Quantity: ");
        JLabel priceLbl = new JLabel("Price: ");
        String[] types = {"Stock", "Mutual Fund"};
        JComboBox typeFld = new JComboBox(types);
        JTextField symbolFld = new JTextField(30);
        JTextField nameFld = new JTextField(30);
        JTextField quantityFld = new JTextField(30);
        JTextField priceFld = new JTextField(30);
        JPanel buyForm = new JPanel();
        JPanel buyFormWrapper = new JPanel();
        JPanel buyPanelRight = new JPanel();
        JPanel buyPanelButtons = new JPanel();
        JButton resetBuyBtn = new JButton("Reset");
        JButton buyBtn = new JButton("Buy");

        // Layout configurations
        buyPanelWrapper.setLayout(new BorderLayout());
        buyPanel.setLayout(new GridLayout(1, 2));
        buyForm.setLayout(new GridLayout(5, 2));
        buyFormWrapper.setLayout(new BorderLayout());
        buyPanelRight.setLayout(new BorderLayout());
        buyPanelButtons.setLayout(new BoxLayout(buyPanelButtons, BoxLayout.Y_AXIS));

        // Adding components to panels
        buyForm.add(typeLbl);
        buyForm.add(typeFld);
        buyForm.add(symbolLbl);
        buyForm.add(symbolFld);
        buyForm.add(nameLbl);
        buyForm.add(nameFld);
        buyForm.add(quantityLbl);
        buyForm.add(quantityFld);
        buyForm.add(priceLbl);
        buyForm.add(priceFld);
        
        buyFormWrapper.add(buyLbl, BorderLayout.NORTH);
        buyFormWrapper.add(buyForm, BorderLayout.SOUTH);
        
        buyPanelButtons.add(resetBuyBtn);
        buyPanelButtons.add(buyBtn);
        
        buyPanelRight.add(buyPanelButtons, BorderLayout.SOUTH);
        
        buyPanel.add(buyFormWrapper);
        buyPanel.add(buyPanelRight);
        
        buyPanelWrapper.add(buyPanel, BorderLayout.NORTH);
        buyPanelWrapper.add(buyPanelOutput, BorderLayout.CENTER);

        // Font settings
        buyLbl.setFont(font);
        typeLbl.setFont(font);
        symbolLbl.setFont(font);
        nameLbl.setFont(font);
        quantityLbl.setFont(font);
        priceLbl.setFont(font);
        typeFld.setFont(font);
        symbolFld.setFont(font);
        nameFld.setFont(font);
        quantityFld.setFont(font);
        priceFld.setFont(font);
        resetBuyBtn.setFont(font);
        buyBtn.setFont(font);

        // Border settings
        buyForm.setBorder(new EmptyBorder(10, 0, 0, 0));
        buyFormWrapper.setBorder(new EmptyBorder(10, 10, 0, 10));

        
        // Background color settings
        buyPanelWrapper.setBackground(Color.BLACK);
        buyPanel.setBackground(Color.BLACK);
        typeFld.setBackground(Color.DARK_GRAY);
        symbolFld.setBackground(Color.DARK_GRAY);
        nameFld.setBackground(Color.DARK_GRAY);
        quantityFld.setBackground(Color.DARK_GRAY);
        priceFld.setBackground(Color.DARK_GRAY);
        buyForm.setBackground(Color.BLACK);
        buyFormWrapper.setBackground(Color.BLACK);
        buyPanelRight.setBackground(Color.BLACK);
        buyPanelButtons.setBackground(Color.BLACK);
        resetBuyBtn.setBackground(Color.DARK_GRAY);
        buyBtn.setBackground(Color.DARK_GRAY);

        // Foreground color settings
        buyLbl.setForeground(Color.CYAN);
        typeLbl.setForeground(Color.CYAN);
        symbolLbl.setForeground(Color.CYAN);
        nameLbl.setForeground(Color.CYAN);
        quantityLbl.setForeground(Color.CYAN);
        priceLbl.setForeground(Color.CYAN);
        typeFld.setForeground(Color.WHITE);
        symbolFld.setForeground(Color.WHITE);
        nameFld.setForeground(Color.WHITE);
        quantityFld.setForeground(Color.WHITE);
        priceFld.setForeground(Color.WHITE);
        resetBuyBtn.setForeground(Color.CYAN);
        buyBtn.setForeground(Color.CYAN);

               
        ///////////////////////////SELL Window //////////////////////////////////
        // Declarations
        JPanel sellPanelWrapper = new JPanel();
        JPanel sellPanel = new JPanel();
        JLabel sellLbl = new JLabel("Selling an investment", JLabel.CENTER);
        JTextField sellSymbolFld = new JTextField(30);
        JTextField sellQuantityFld = new JTextField(30);
        JTextField sellPriceFld = new JTextField(30);
        JLabel sellSymbolLbl = new JLabel("Symbol: ", JLabel.CENTER);
        JLabel sellQuantityLbl = new JLabel("Quantity: ", JLabel.CENTER);
        JLabel sellPriceLbl = new JLabel("Price: ", JLabel.CENTER);
        JPanel sellForm = new JPanel();
        JPanel sellFormWrapper = new JPanel();
        JPanel sellPanelRight = new JPanel();
        JPanel sellPanelButtons = new JPanel();
        JButton resetSellBtn = new JButton("Reset");
        JButton sellBtn = new JButton("Sell");

        // Layout configurations
        sellPanelWrapper.setLayout(new BorderLayout());
        sellPanel.setLayout(new GridLayout(1, 2));
        sellForm.setLayout(new GridLayout(3, 2));
        sellFormWrapper.setLayout(new BorderLayout());
        sellPanelRight.setLayout(new BorderLayout());
        sellPanelButtons.setLayout(new BoxLayout(sellPanelButtons, BoxLayout.Y_AXIS));

        // Adding components to panels
        sellForm.add(sellSymbolLbl);
        sellForm.add(sellSymbolFld);
        sellForm.add(sellQuantityLbl);
        sellForm.add(sellQuantityFld);
        sellForm.add(sellPriceLbl);
        sellForm.add(sellPriceFld);
        sellFormWrapper.add(sellLbl, BorderLayout.NORTH);
        sellFormWrapper.add(sellForm, BorderLayout.SOUTH);
        sellPanelButtons.add(resetSellBtn);
        sellPanelButtons.add(sellBtn);
        sellPanelRight.add(sellPanelButtons, BorderLayout.SOUTH);
        sellPanel.add(sellFormWrapper);
        sellPanel.add(sellPanelRight);
        sellPanelWrapper.add(sellPanel, BorderLayout.NORTH);
        sellPanelWrapper.add(sellPanelOutput, BorderLayout.CENTER);

        // Font settings
        sellLbl.setFont(font);
        sellSymbolFld.setFont(font);
        sellQuantityFld.setFont(font);
        sellPriceFld.setFont(font);
        sellSymbolLbl.setFont(font);
        sellQuantityLbl.setFont(font);
        sellPriceLbl.setFont(font);
        resetSellBtn.setFont(font);
        sellBtn.setFont(font);

        // Border settings
        sellForm.setBorder(new EmptyBorder(10, 0, 0, 0));
        sellFormWrapper.setBorder(new EmptyBorder(10, 10, 0, 10));

        // Background color settings
        sellPanel.setBackground(Color.BLACK);
        sellSymbolFld.setBackground(Color.DARK_GRAY);
        sellQuantityFld.setBackground(Color.DARK_GRAY);
        sellPriceFld.setBackground(Color.DARK_GRAY);
        sellForm.setBackground(Color.BLACK);
        sellFormWrapper.setBackground(Color.BLACK);
        sellPanelRight.setBackground(Color.BLACK);
        sellPanelButtons.setBackground(Color.BLACK);
        resetSellBtn.setBackground(Color.DARK_GRAY);
        sellBtn.setBackground(Color.DARK_GRAY);

        // Foreground color settings
        sellLbl.setForeground(Color.CYAN);
        sellSymbolFld.setForeground(Color.CYAN);
        sellQuantityFld.setForeground(Color.CYAN);
        sellPriceFld.setForeground(Color.CYAN);
        sellSymbolLbl.setForeground(Color.CYAN);
        sellQuantityLbl.setForeground(Color.CYAN);
        sellPriceLbl.setForeground(Color.CYAN);
        resetSellBtn.setForeground(Color.CYAN);
        sellBtn.setForeground(Color.CYAN);

        
        /// UPDATE Window
        // Declarations
        JPanel updatePanelWrapper = new JPanel();
        JPanel updatePanel = new JPanel();
        JLabel updateLbl = new JLabel("Updating investments");
        JTextField updateSymbolFld = new JTextField(30);
        JTextField updateNameFld = new JTextField(30);
        JTextField updatePriceFld = new JTextField(30);
        JLabel updateSymbolLbl = new JLabel("Symbol: ");
        JLabel updateNameLbl = new JLabel("Name: ");
        JLabel updatePriceLbl = new JLabel("Price: ");
        JPanel updateForm = new JPanel();
        JPanel updateFormWrapper = new JPanel();
        JPanel updatePanelRight = new JPanel();
        JPanel updatePanelButtons = new JPanel();
        JButton prevButton = new JButton("Prev");
        JButton nextButton = new JButton("Next");
        JButton updateSaveButton = new JButton("Save");

        // Layout configurations
        updatePanelWrapper.setLayout(new BorderLayout());
        updatePanel.setLayout(new GridLayout(1, 2));
        updateForm.setLayout(new GridLayout(3, 2));
        updateFormWrapper.setLayout(new BorderLayout());
        updatePanelRight.setLayout(new BorderLayout());
        updatePanelButtons.setLayout(new BoxLayout(updatePanelButtons, BoxLayout.Y_AXIS));

        // Component additions
        updateForm.add(updateSymbolLbl);
        updateForm.add(updateSymbolFld);
        updateForm.add(updateNameLbl);
        updateForm.add(updateNameFld);
        updateForm.add(updatePriceLbl);
        updateForm.add(updatePriceFld);
        updateFormWrapper.add(updateLbl, BorderLayout.NORTH);
        updateFormWrapper.add(updateForm, BorderLayout.SOUTH);
        updatePanelButtons.add(prevButton);
        updatePanelButtons.add(nextButton);
        updatePanelButtons.add(updateSaveButton);
        updatePanelRight.add(updatePanelButtons, BorderLayout.SOUTH);
        updatePanel.add(updateFormWrapper);
        updatePanel.add(updatePanelRight);
        updatePanelWrapper.add(updatePanel, BorderLayout.NORTH);
        updatePanelWrapper.add(updatePanelOutput, BorderLayout.CENTER);

        // Font settings
        updateLbl.setFont(font);
        updateSymbolFld.setEditable(false);
        updateSymbolFld.setFont(font);
        updateNameFld.setEditable(false);
        updateNameFld.setFont(font);
        updatePriceFld.setFont(font);
        updateSymbolLbl.setFont(font);
        updateNameLbl.setFont(font);
        updatePriceLbl.setFont(font);
        prevButton.setFont(font);
        nextButton.setFont(font);
        updateSaveButton.setFont(font);

        // Border settings
        updateForm.setBorder(new EmptyBorder(10, 0, 0, 0));
        updateFormWrapper.setBorder(new EmptyBorder(10, 10, 0, 10));

        // Background color settings
        updatePanelWrapper.setBackground(Color.BLACK);
        updateSymbolFld.setBackground(Color.DARK_GRAY);
        updateNameFld.setBackground(Color.DARK_GRAY);
        updatePriceFld.setBackground(Color.DARK_GRAY);
        updateForm.setBackground(Color.BLACK);
        updateFormWrapper.setBackground(Color.BLACK);
        updatePanelRight.setBackground(Color.BLACK);
        updatePanelButtons.setBackground(Color.BLACK);
        prevButton.setBackground(Color.DARK_GRAY);
        nextButton.setBackground(Color.DARK_GRAY);
        updateSaveButton.setBackground(Color.DARK_GRAY);
        
        // Foreground color settings
        updateLbl.setForeground(Color.CYAN);
        updateSymbolFld.setForeground(Color.WHITE);
        updateNameFld.setForeground(Color.WHITE);
        updatePriceFld.setForeground(Color.WHITE);
        updateSymbolLbl.setForeground(Color.CYAN);
        updateNameLbl.setForeground(Color.CYAN);
        updatePriceLbl.setForeground(Color.CYAN);
        prevButton.setForeground(Color.CYAN);
        nextButton.setForeground(Color.CYAN);
        updateSaveButton.setForeground(Color.CYAN);

        //////////////////////////GET GAIN Window ///////////////////////////
        // Declarations
        JPanel getGainPanelWrapper = new JPanel();
        JPanel getGainPanel = new JPanel();
        JLabel getGainHeading = new JLabel("Getting total gain");
        JLabel totalGainLbl = new JLabel("Total gain: ");
        JTextField getGainFld = new JTextField(20);
        JPanel getGainForm = new JPanel();
        JPanel getGainFormWrapper = new JPanel();

        // Layout configurations
        getGainPanelWrapper.setLayout(new BorderLayout());
        getGainForm.setLayout(new BoxLayout(getGainForm, BoxLayout.X_AXIS));

        // Component additions
        getGainForm.add(totalGainLbl);
        getGainForm.add(getGainFld);
        getGainFormWrapper.add(getGainHeading, BorderLayout.NORTH);
        getGainFormWrapper.add(getGainForm, BorderLayout.SOUTH);
        getGainPanel.add(getGainFormWrapper);
        getGainPanelWrapper.add(getGainPanel, BorderLayout.NORTH);
        getGainPanelWrapper.add(getGainOutput, BorderLayout.CENTER);

        // Font settings
        getGainHeading.setFont(font);
        totalGainLbl.setFont(font);
        getGainFld.setEditable(false);
        getGainFld.setFont(font);

        // Border settings
        getGainForm.setBorder(new EmptyBorder(10, 0, 0, 0));
        getGainFormWrapper.setBorder(new EmptyBorder(10, 10, 0, 10));

        // Background settings
        updatePanelWrapper.setBackground(Color.black);
        getGainPanel.setBackground(Color.black);
        getGainFormWrapper.setBackground(Color.black);
        getGainForm.setBackground(Color.black);
        getGainFld.setBackground(Color.darkGray);

        // Foreground settings
        getGainHeading.setForeground(Color.cyan);
        totalGainLbl.setForeground(Color.cyan);
        getGainFld.setForeground(Color.white);
        getGainForm.setForeground(Color.cyan);

        
        ////////////////////////////////////Profile Window /////////////////////////////////
        JPanel profilePanel = new JPanel();
        JPanel profilePanelWrapper = new JPanel();
        
        profilePanelWrapper.setLayout(new BorderLayout());
        profilePanelWrapper.add(profileResults, BorderLayout.CENTER);
        profilePanelWrapper.setBackground(Color.black);
        profilePanel.setBackground(Color.black);
        
        
        ////////////////////////// content panel declaration ////////////////////
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new CardLayout());
        
        bottomPanel.add(welcomeArea, "welcomeScreen");
        bottomPanel.add(buyPanelWrapper, "buyPanel");
        bottomPanel.add(sellPanelWrapper, "sellPanel");
        bottomPanel.add(updatePanelWrapper, "updatePanel");
        bottomPanel.add(getGainPanelWrapper, "getGainPanel");
        bottomPanel.add(profilePanelWrapper, "profilePanel");
        ((CardLayout) bottomPanel.getLayout()).show(bottomPanel, "welcomeScreen");
        
        add(topPanel);
        add(bottomPanel);
        
        
        // navigation menu
        menuBuyBtn.addActionListener((ActionEvent ae) -> {
            messageArea.setText("");
            ((CardLayout) bottomPanel.getLayout()).show(bottomPanel, "buyPanel");
        });
        
        menuSellBtn.addActionListener((ActionEvent ae) -> {
            sellMessageArea.setText("");
            ((CardLayout) bottomPanel.getLayout()).show(bottomPanel, "sellPanel");
        });
        
        menuUpdateBtn.addActionListener((ActionEvent ae) -> {
            if (!Stock_Management.portfolio.getInvestmentList().isEmpty()) {
                index = 0;
                Investment_Type i = Stock_Management.portfolio.getInvestmentList().get(index);
                updateSymbolFld.setText(i.getSymbol());
                updateNameFld.setText(i.getName());
                updatePriceFld.setText("" + i.getPrice());
                updateMessageArea.setText("");
                if (i.getPrice()<0){
                    updateMessageArea.setForeground(Color.red);
                } else {
                    updateMessageArea.setForeground(Color.green);
                }
            }
            ((CardLayout) bottomPanel.getLayout()).show(bottomPanel, "updatePanel");
        });
        
        menuGetGain.addActionListener((ActionEvent ae) -> {
            double totalGain = Stock_Management.portfolio.getGain(getGainMsgArea);
            String o = String.format("$%.2f", totalGain);
            getGainFld.setText(o);
            if (totalGain <= 0){
                getGainMsgArea.setForeground(Color.red);
            } else {
                getGainMsgArea.setForeground(Color.green);
            }
            
            ((CardLayout) bottomPanel.getLayout()).show(bottomPanel, "getGainPanel");
        });
        
        menuProfile.addActionListener((ActionEvent ae) -> {
            ((CardLayout) bottomPanel.getLayout()).show(bottomPanel, "profilePanel");
        });
        
        quitNavBtn.addActionListener((ActionEvent ae) -> {
            System.exit(0);
        });
        
        NavsaveAs.addActionListener((ActionEvent ae) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(null);
            if (fileChooser.getSelectedFile() != null) {
                String filepath = fileChooser.getSelectedFile().getPath();
                Stock_Management.portfolio.savePortfolio(filepath);
            }
        });
        
        loadNavBtn.addActionListener((ActionEvent ae) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            if (fileChooser.getSelectedFile() != null) {
                String filepath = fileChooser.getSelectedFile().getPath();
                Stock_Management.portfolio.loadPortfolio(filepath);
            }
        });
        
   
        // buttons
        // Add this as a class field at the top of your class
        

        // Then your button handler:
        // Class field for tracking quantities


        buyBtn.addActionListener((ActionEvent ae) -> {
            int type1 = typeFld.getSelectedIndex();
            String[] symbols = symbolFld.getText().split(","); // Assuming comma-separated symbols
            String name1 = nameFld.getText();
            String[] quantityStrings = quantityFld.getText().split(","); // Assuming comma-separated quantities
            int[] quantities = new int[quantityStrings.length];
            double price;

            try {
                price = Double.parseDouble(priceFld.getText());
                // Parse all quantities
                for (int i = 0; i < quantityStrings.length; i++) {
                    quantities[i] = Integer.parseInt(quantityStrings[i].trim());
                }
            } catch (NumberFormatException e) {
                messageArea.setText("Input Error: NumberFormatException");
                messageArea.setForeground(Color.red);
                return;
            }

            // Validate array lengths match
            if (symbols.length != quantities.length) {
                messageArea.setText("Error: Number of symbols must match number of quantities");
                messageArea.setForeground(Color.red);
                return;
            }

            boolean allSuccess = true;
            // Process each symbol and quantity pair
            for (int i = 0; i < symbols.length; i++) {
                int result = Stock_Management.portfolio.buyInvestment(
                    type1, 
                    symbols[i].trim(), 
                    name1, 
                    quantities[i], 
                    price, 
                    messageArea
                );

                if (result != Stock_Management.SUCCESS) {
                    messageArea.setText(Stock_Management.getError(result));
                    messageArea.setForeground(Color.red);
                    allSuccess = false;
                    break;
                }
            }

            if (allSuccess) {
                symbolFld.setText("");
                nameFld.setText("");
                quantityFld.setText("");
                priceFld.setText("");
                messageArea.setForeground(Color.green);

                // If this is the first input, clear initial "No stocks" entry
                if (dataset.getItemCount() <= 1 && dataset.getKey(0).toString().equals("No stocks")) {
                    dataset.clear();
                    globalStockQuantities.clear();
                }

                // Update global quantities
                for (int i = 0; i < symbols.length; i++) {
                    String symbol = symbols[i].trim();
                    globalStockQuantities.merge(symbol, quantities[i], Integer::sum);
                }

                // Calculate total quantity
                int totalQuantity = globalStockQuantities.values().stream()
                    .mapToInt(Integer::intValue)
                    .sum();

                // Clear and update dataset with new percentages
                dataset.clear();
                for (Map.Entry<String, Integer> entry : globalStockQuantities.entrySet()) {
                    double percentage = ((double) entry.getValue() / totalQuantity) * 100;
                    dataset.setValue(entry.getKey(), percentage);
                }
            }
        });
        
        resetBuyBtn.addActionListener((ActionEvent ae) -> {
            symbolFld.setText("");
            nameFld.setText("");
            quantityFld.setText("");
            priceFld.setText("");
        });
        
        sellBtn.addActionListener((ActionEvent ae) -> {
            String symbol = sellSymbolFld.getText();
            int quantity;
            double price;
            try {
                price = Double.parseDouble(sellPriceFld.getText());
                quantity = Integer.parseInt(sellQuantityFld.getText());
            }
            catch (NumberFormatException e) {
                sellMessageArea.setText("Input Error: NumberFormatException");
                sellMessageArea.setForeground(Color.red);
                return;
            }
            sellMessageArea.setForeground(Color.green);
            int result = Stock_Management.portfolio.sellInvestment(symbol, quantity, price, sellMessageArea);
            if (result != Stock_Management.SUCCESS) {
                sellMessageArea.setText(Stock_Management.getError(result));
                sellMessageArea.setForeground(Color.red);
            } else {
                sellSymbolFld.setText("");
                sellQuantityFld.setText("");
                sellPriceFld.setText("");
                sellMessageArea.setForeground(Color.green);
            }
        });
        
        resetSellBtn.addActionListener((ActionEvent ae) -> {
            sellSymbolFld.setText("");
            sellQuantityFld.setText("");
            sellPriceFld.setText("");
        });
        
        nextButton.addActionListener((ActionEvent ae) -> {
            ArrayList investmentList = Stock_Management.portfolio.getInvestmentList();
            if (investmentList.isEmpty())
                return;
            if (index + 1 > investmentList.size()-1)
                return;
            index++;
            Investment_Type i = Stock_Management.portfolio.getInvestmentList().get(index);
            updateSymbolFld.setText(i.getSymbol());
            updateNameFld.setText(i.getName());
            updatePriceFld.setText("" + i.getPrice());
        });
        
        prevButton.addActionListener((ActionEvent ae) -> {
            ArrayList investmentList = Stock_Management.portfolio.getInvestmentList();
            if (investmentList.isEmpty())
                return;
            if (index == 0)
                return;
            index--;
            Investment_Type i = Stock_Management.portfolio.getInvestmentList().get(index);
            updateSymbolFld.setText(i.getSymbol());
            updateNameFld.setText(i.getName());
            updatePriceFld.setText("" + i.getPrice());
        });
        
        updateSaveButton.addActionListener((ActionEvent ae) -> {
            String symbol = updateSymbolFld.getText();
            double price;
            try {
                price = Double.parseDouble(updatePriceFld.getText());
                int result = Stock_Management.portfolio.updatePrice(symbol, price);
                if (result != Stock_Management.SUCCESS) {
                    updateMessageArea.setText(Stock_Management.getError(result));
                    updateMessageArea.setForeground(Color.red);
                } else {
                    updateMessageArea.setText("Successfully updated price for " + symbol);
                    updateMessageArea.setForeground(Color.green);
                }
            }
            catch (NumberFormatException ex) {
                updateMessageArea.setText("Input Error: NumberFormatException");
                updateMessageArea.setForeground(Color.red);
            }
        });
    }
}

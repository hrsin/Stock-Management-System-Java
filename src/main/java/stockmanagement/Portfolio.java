package stockmanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTextArea;

/**
 *
 * @author rishi
 */
public class Portfolio {
    
    public static final int INVESTMENT_TYPE_STOCK = 0;
    public static final int INVESTMENT_TYPE_FUND = 1;
    
    private ArrayList<Investment_Type> investmentList;
    private HashMap<String, ArrayList<Integer>> keywordMappings;

       /**
     * Get a copy of the investments list
     */
    public ArrayList<Investment_Type> getInvestmentList() {
        return new ArrayList(investmentList);
    }

    /**
     * Set a new investments list
     */
    public void setInvestmentList(ArrayList<Investment_Type> investmentList) {
        this.investmentList = new ArrayList(investmentList);
    }

    /**
     * Create a new, empty Portfolio
     */
    public Portfolio() {
        this.keywordMappings = new HashMap<>();
        this.investmentList = new ArrayList<>();
    }
    
    /**
     * Buys a new investment or adds more to an existing one
     */
    public int buyInvestment(int type,
                             String symbol,
                             String name,
                             int quantity,
                             double price,
                             JTextArea outputArea) {
        try {
            boolean existing = false;
            String output = "";
            switch(type) {
                case INVESTMENT_TYPE_STOCK:
                    Stock stock = new Stock(symbol, name, price);
                    for (Investment_Type i : this.investmentList) {
                        if (i.equals(stock)) {
                            existing = true;
                            if (i instanceof MutualFunds) {
                                return Stock_Management.STOCK_BUY_CONFLICT;
                            }
                            else {
                                stock = (Stock) i;
                            }
                        }
                    }
                    if (!existing) {
                        this.addInvestment(stock);
                    }
                    stock.buyShares(quantity, price);
                    output += String.format("New Book Value: $%.2f\n", stock.getBookValue());
                    output += ("Total Quantity Owned: " + stock.getQuantity() + "\n");
                    break;
                
            }
            output += "Successfully bought " + quantity + " shares of " + symbol;
            outputArea.setText(output);
        }
        catch (Exception e) {
            return exceptionHandler(e);
        }
        return Stock_Management.SUCCESS;
    }
    
    /**
     * Sell an investment by its symbol
     */
    public int sellInvestment(String symbol,
                              int amount,
                              double price,
                              JTextArea outputArea) {
        if (this.isEmpty()) {
            return Stock_Management.ERROR_EMPTY_PORTFOLIO;
        }
        try {
            String output = "";
            int result = -1;
            boolean found = false;
            Investment_Type investment = null;
            for (Investment_Type i : this.investmentList) {
                if (i instanceof Stock && i.getSymbol().equalsIgnoreCase(symbol)) {
                    Stock s = (Stock) i;
                    result = s.sellShares(amount, price);
                    investment = s;
                    break;
                }
                else if (i instanceof MutualFunds && i.getSymbol().equalsIgnoreCase(symbol)) {
                    MutualFunds f = (MutualFunds) i;
                    result = f.sellUnits(amount, price);
                    investment = f;
                    break;
                }
            }
            if (investment == null) {
                return Stock_Management.ERROR_NOT_FOUND;
            }
            else if (result == -1) {
                return Stock_Management.ERROR_INSUFFICIENT_QUANTITY;
            }
            else if (result == 0) {
                output += ("Quantity Remaining: 0\n");
                output += ("Investment removed from portfolio\n");
                this.removeInvestment(investment);
            }
            else {
                output += ("Quantity Remaining: " + investment.getQuantity() + "\n");
                output += String.format("New Book Value: $%.2f\n", investment.getBookValue());
            }
            output += String.format("Payment Received: $%.2f\n", investment.getPaymentReceived(amount));
            output += "Successfully sold " + amount + " units of " + symbol;
            outputArea.setText(output);
        }
        catch (Exception e) {
            return exceptionHandler(e);
        }
        return Stock_Management.SUCCESS;
    }
    
    /**
     * Calculate the total gain of all investments in the portfolio
     */
    public double getGain(JTextArea outputArea) {
        double totalGain = 0;
        String output = "";
        for (Investment_Type i : this.investmentList) {
            totalGain += i.getGain();
            output += String.format("%s: $%.2f\n", i.getSymbol(), i.getGain());
        }
        outputArea.setText(output);
        return totalGain;
    }
    
    /**
     * Updates the price of a specific investment by symbol
     */
    public int updatePrice(String symbol, double price) {
        if (this.isEmpty()) {
            return Stock_Management.ERROR_EMPTY_PORTFOLIO;
        }
        try {
            for (Investment_Type i : this.investmentList) {
                if (symbol.equalsIgnoreCase(i.getSymbol())) {
                    i.setPrice(price);
                }
            }
        }
        catch (Exception e) {
            return exceptionHandler(e);
        }
        return Stock_Management.SUCCESS;
    }
       
    /**
     * Checks if the portfolio is empty
     */
    public boolean isEmpty() {
        return (this.investmentList.isEmpty());
    }
    
    /**
     * Saves all investments to an external file
     */
    public void savePortfolio(String filepath) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(filepath));
            for (Investment_Type i : this.investmentList) {
                if (i instanceof Stock)
                    writer.write("type = \"stock\"\n");
                else if (i instanceof MutualFunds)
                    writer.write("type = \"mutualfund\"\n");
                writer.write("symbol = \"" + i.getSymbol() + "\"\n");
                writer.write("name = \"" + i.getName()+ "\"\n");
                writer.write("quantity = \"" + i.getQuantity()+ "\"\n");
                writer.write("price = \"" + i.getPrice()+ "\"\n");
                writer.write("bookValue = \"" + i.getBookValue()+ "\"\n\n");
            }
            writer.close();
            Stock_Management.showInfoDialog("Data saved to file: " + filepath);
        }
        catch(IOException io) {
            Stock_Management.showErrorDialog("Failed to save portfolio data to file: " + filepath);
        }
    }
    
    /**
     * Loads investment data from an external file
     */
    public void loadPortfolio(String filepath) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filepath));
            String type = "";
            String symbol = "";
            String name = "";
            int quantity = 0;
            double price = 0;
            double bookValue = 0;
            while (true) {
                String line = reader.readLine();
                if (line == null)
                    break;
                else if (line.isEmpty()) {
                    if (type.equals("stock")) {
                        Stock s = new Stock(symbol, name, price);
                        s.setQuantity(quantity);
                        s.setBookValue(bookValue);
                        this.addInvestment(s);
                        
                    }
                    else if (type.equals("mutualfund")) {
                        MutualFunds f = new MutualFunds(symbol, name, price);
                        f.setQuantity(quantity);
                        f.setBookValue(bookValue);
                        this.addInvestment(f);
                    }
                    type = "";
                    symbol = "";
                    name = "";
                    quantity = 0;
                    price = 0;
                    bookValue = 0;
                }
                else {
                    String[] vals = line.split("=");
                    String key = vals[0].trim();
                    String value = vals[1].replace("\"", "").trim();
                    if (key.equals("type")) {
                        type = value;
                    }
                    else if (key.equals("symbol")) {
                        symbol = value;
                    }
                    else if (key.equals("name")) {
                        name = value;
                    }
                    else if (key.equals("quantity")) {
                        quantity = Integer.parseInt(value);
                    }
                    else if (key.equals("price")) {
                        price = Double.parseDouble(value);
                    }
                    else if (key.equals("bookValue")) {
                        bookValue = Double.parseDouble(value);
                    }
                }
            }
            reader.close();
            Stock_Management.showInfoDialog("Portfolio loaded from file: " + filepath);
        }
        catch (Exception io) {
            Stock_Management.showErrorDialog("Failed to load portfolio data from file: " + filepath);
        }
    }
    
    /**
     * Adds keywords for searching the investments
     */
    private void addKeywordMapping(String name, int index) {
        String investmentName = name.toLowerCase();
        String[] keywords = investmentName.split(" ");
        for (String keyword : keywords) {
            if (!this.keywordMappings.containsKey(keyword)) {
                ArrayList<Integer> value = new ArrayList<>();
                value.add(index);
                this.keywordMappings.put(keyword, value);
            }
            else {
                ArrayList<Integer> value = this.keywordMappings.get(keyword);
                value.add(index);
            }
        }
    }
    
    /**
     * Removes keywords from the search index when an investment is removed
     */
    private void removeKeywordMapping(String name, int index) {
        String investmentName = name.toLowerCase();
        String[] keywords = investmentName.split(" ");
        for (String keyword : keywords) {
            if (this.keywordMappings.containsKey(keyword)) {
                ArrayList<Integer> value = this.keywordMappings.get(keyword);
                value.removeIf(v -> (v == index));
                if (value.isEmpty()) {
                    this.keywordMappings.remove(keyword);
                }
            }
        }
    }
    
    /**
     * Adds an investment to the portfolio
     */
    private void addInvestment(Investment_Type i) {
        this.investmentList.add(i);
        int index = this.investmentList.indexOf(i);
        this.addKeywordMapping(i.getName(), index);
    }
    
    /**
     * Removes an investment from the portfolio
     */
    private void removeInvestment(Investment_Type i) {
        int index = this.investmentList.indexOf(i);
        this.investmentList.remove(i);
        this.removeKeywordMapping(i.getName(), index);
    }
    
    /**
     * Handles exceptions by showing an error
     */
    private int exceptionHandler(Exception e) {
        Stock_Management.showErrorDialog(e.getMessage());
        return Stock_Management.ERROR_UNKNOWN;
    }
}


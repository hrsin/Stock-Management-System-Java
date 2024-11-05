/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package stockmanagement;

/**
 * Main class for Stock Management System
 * 
 * @author rishi
 */
import javax.swing.JOptionPane;

public class Stock_Management {

    public static final int ERROR_INSUFFICIENT_QUANTITY = -1; // Not enough shares to sell
    public static final int ERROR_NOT_FOUND = -3; // Investment not found
    public static final int ERROR_EMPTY_PORTFOLIO = -4; // No investments in the portfolio
    public static final int FUND_BUY_CONFLICT = -5; // Duplicate stock symbol
    public static final int STOCK_BUY_CONFLICT = -2; // Duplicate mutual fund symbol
    public static final int INVALID_PRICE = -6; // Price can't be zero or negative
    public static final int INVALID_QUANTITY = -7; // Quantity can't be zero or negative
    public static final int EMPTY_NAME = -8; // Investment name can't be blank
    public static final int EMPTY_SYMBOL = -9; // Investment symbol can't be blank
    public static final int UNKNOWN_ERROR = -10; // Some error happened that we don't know about
    public static final int SUCCESS = 0; // Everything worked fine

    public static Portfolio portfolio; // This is where all the investments are kept

    /**
     * The main method where the program starts
     * @param args command line arguments (not used here)
     */
    public static void main(String[] args) {
        portfolio = new Portfolio(); // Create a new portfolio
        MainGUI gui = new MainGUI(); // Set up the main GUI for the app
        gui.setSize(1200, 650); // Set the size of the window
        gui.setLocationRelativeTo(null); // Center the window on the screen
        gui.setResizable(false); // Make the window not resizable
        gui.setVisible(true); // Show the window
    }

    /**
     * Gets a user-friendly error message based on an error code
     * @param errorCode the code that determines which error message to show
     * @return a friendly error message
     */
    public static String getError(int errorCode) {
        switch (errorCode) {
            case ERROR_INSUFFICIENT_QUANTITY:
                return "You don't have enough quantity to sell.";
            case ERROR_NOT_FOUND:
                return "You can only sell an investment you own.";
            case ERROR_EMPTY_PORTFOLIO:
                return "Your portfolio has no active investments.";
            case FUND_BUY_CONFLICT:
                return "A Stock with the same symbol already exists.";                
            case STOCK_BUY_CONFLICT:
                return "A Mutual Fund with the same symbol already exists.";                
            case INVALID_PRICE:
                return "Price cannot be zero or negative.";                
            case INVALID_QUANTITY:
                return "Quantity cannot be zero or negative.";                
            case EMPTY_NAME:
                return "Investment name cannot be empty.";                
            case EMPTY_SYMBOL:
                return "Investment symbol cannot be empty.";                
            case UNKNOWN_ERROR:
                return "An unknown error occurred.";                
        }
        return null; // If no error code matches, return null
    }

    /**
     * Displays an error message in a dialog box
     * @param msg The message to show
     */
    public static void showErrorDialog(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays an info message in a dialog box
     * @param msg The message to show
     */
    public static void showInfoDialog(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}

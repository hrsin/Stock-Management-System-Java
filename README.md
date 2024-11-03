# Stock-Management-System-Java
### OOPL Java Mini Project for SEM - 3 A2 Batch

## Stock Management System
A Java-based application for managing stock portfolios with features for buying, selling, and visualizing investment data. This system provides a user-friendly graphical interface for tracking stock investments and analyzing portfolio performance.

## Features

### Portfolio Management
  * Buy and sell different types of investments
  * Track multiple stocks simultaneously
  * Real-time portfolio value updates
  * Support for different investment types
### Visual Analytics
  * Interactive pie chart visualization of portfolio distribution
  * Color-coded performance indicators
  * Real-time graph updates on transactions
### User Interface
  * Intuitive graphical interface built with Java Swing
  * Clear message feedback system
  * Input validation for all transactions
  * Easy-to-use form fields for stock operations
### Data Handling
  * Efficient stock quantity tracking
  * Support for batch transactions
  * Automatic percentage calculation for portfolio distribution


## Prerequisites

* Java Development Kit (JDK) 8 or higher
* Java Swing library (included in JDK)
* JFreeChart library for visualization

## Usage

### Buying Stocks

> 1. Select the investment type from the dropdown menu

2. Enter stock symbols (comma-separated for multiple stocks)

3. Input the stock name

4. Enter quantities (comma-separated, matching the number of symbols)

5. Specify the price

6. Click the "Buy" button to execute the transaction

## Monitoring Portfolio
1. The pie chart automatically updates to show the current portfolio distribution

2. Each stock's percentage in the portfolio is calculated and displayed

3. Color-coded messages indicate transaction success or failure

---
## Code Structure

Src Files Overview
## 1. Stock_Management.java

Main Application Controller

  * Initializes the main application window
  * Sets up the GUI components and layout
  * Manages the main application flow
  * Contains the main portfolio instance
  * Handles application-wide event management

## 2. Investment.java

Investment Base Class
  * Defines the basic structure for all investment types
  * Contains common attributes:
    * Symbol  
    * Name
    * Quantity
    * Price
    * Book value calculations
  * Implements base investment validation logic

## 3. Stock.java

Stock Investment Type
  * Extends the Investment class
  * Handles stock-specific calculations
  * Manages stock validation rules
  * Implements stock-specific price and value calculations

## 4. MutualFund.java

Mutual Fund Investment Type
  * Extends the Investment class
  * Implements mutual fund-specific logic
  * Handles redemption fee calculations
  * Manages mutual fund-specific validation rules

## 5. Portfolio.java
   
Portfolio Management
  * Maintains the list of all investments
  * Handles buying and selling operations
  * Manages investment updates and modifications
  * Calculates total portfolio value
  * Implements search and filter functionality
  * Manages transaction history

## 6. MainGUI.java
   
Main Application Window and Interface
  * Creates and manages the primary application window
  * Sets up the tabbed interface for different operations:
    * Buy panel
    * Sell panel
    * Update panel
    * Gain panel
    * Search panel
  * Manages the portfolio visualization components
  * Implements the message area for user feedback
  * Handles menu operations and window events
  * Contains shared UI components:
    * Message display area
    * Type selection dropdown
    * Input fields for stock data
    * Action buttons
    * Pie chart visualization
 * Coordinates communication between different panels
 * Updates the portfolio visualization in real-time
---
## Error Handling

The system includes comprehensive error checking for:
* Invalid input formats
* Number format exceptions
* Mismatched symbol and quantity counts
* Transaction validation
* Portfolio updates
---
## Acknowledgments

1. JFreeChart library for providing visualization capabilities

2. Java Swing for the graphical user interface

3. Contributors and maintainers of the project

## Support
For support, please open an issue in the GitHub repository.

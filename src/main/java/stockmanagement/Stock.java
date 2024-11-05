/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockmanagement;

/**
 *
 * @author rishi
 */
public class Stock extends Investment_Type{
        public static final double COMMISSION = 9.99;

/**
 * Makes a new Stock object
 * @param symbol the symbol for the stock (like a short name)
 * @param name the full name of the stock
 * @param price the price per share of the stock
 */
public Stock(String symbol, String name, double price) throws EmptySymbolError,
                                                              EmptyNameError,
                                                              PriceRangeError {
    super(symbol, name, price);
}

/**
 * Buys a certain number of shares at a given price, and updates the stock price
 * @param amount how many shares to buy
 * @param price the price per share when buying
 * @throws QuantityRangeError if amount is zero or negative
 */
public void buyShares(int amount, double price) throws QuantityRangeError {
    if (amount <= 0)
        throw new QuantityRangeError();
    if (price != this.price) { // Only update price if it's different
        this.price = price;
    }
    this.quantity += amount; // Add the amount to total quantity
    this.bookValue += (amount * this.price + Stock.COMMISSION); // Update book value with commission
}

/**
 * Sells a certain number of shares at a given price
 * @param amount the number of shares to sell
 * @param price the sell price per share
 * @return the new amount of shares owned or -1 if not enough shares
 * @throws QuantityRangeError if amount is zero or negative
 * @throws PriceRangeError if price is invalid
 */
public int sellShares(int amount, double price) throws QuantityRangeError, PriceRangeError {
    if (amount <= 0)
        throw new QuantityRangeError();
        
    if (this.quantity >= amount) { // Check if we have enough shares
        if (this.price != price) {
            this.setPrice(price); // Update price if it's different
        }
        int newAmount = this.quantity - amount;
        this.bookValue = this.bookValue * ((double)newAmount / this.quantity); // Adjust book value
        this.quantity = newAmount;
        return newAmount; // Return new amount after selling
    }
    return -1; // Not enough shares to sell
}

/**
 * Calculates how much gain (profit) we have on this stock
 * @return the gain (profit or loss) on this stock
 */
public double getGain() {
    return (this.quantity * this.price - Stock.COMMISSION) - this.bookValue;
}

/**
 * Calculates the money received after selling a certain number of shares
 * @param amount the number of shares sold
 * @return the amount received after selling
 */
public double getPaymentReceived(int amount) {
    return amount * this.price - Stock.COMMISSION;
}

/**
 * Shows details about this Stock in the console
 */
public void print() {
    System.out.println("Investment Type: Stock");
    System.out.println("Symbol: " + this.symbol);
    System.out.println("Name: " + this.name);
    System.out.printf("Current Price: $%.2f\n", this.price);
    System.out.println("Owned Quantity: " + this.quantity);
    System.out.printf("Book Value: $%.2f\n", this.bookValue);
}

@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true; // Same object, so they are equal
    }
    return super.equals(obj); // Otherwise, check if superclass says they are equal
}

/**
 * Gives a text description of this Stock (like print, but returns a string)
 * @return the details of this Stock as a string
 */
@Override
public String toString() {
    String output = "Investment Type: Stock\n";
    output += "Symbol: " + this.symbol + "\n";
    output += "Name: " + this.name + "\n";
    output += String.format("Current Price: $%.2f\n", this.price);
    output += "Owned Quantity: " + this.quantity + "\n";
    output += String.format("Book Value: $%.2f\n", this.bookValue);
    return output;
}
}

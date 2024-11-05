/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockmanagement;

/**
 *
 * @author rishi
 */
public class MutualFunds extends Investment_Type {
    
    public static final double REDEMPTION = 45;

    public MutualFunds(String symbol, String name, double price) throws EmptySymbolError,
                                                                       EmptyNameError, 
                                                                       PriceRangeError {
        super(symbol, name, price);
    }
    
    /**
     * Buys a certain number of units for the mutual fund at a specific price.
     * Updates the price and quantity of units owned.
     * @param amount Number of units to buy
     * @param price Price per unit to buy at
     */
    public void buyUnits(int amount, double price) throws QuantityRangeError {
        if (amount <= 0)
            throw new QuantityRangeError();
        if (this.price != price) {
            this.price = price;
        }
        this.quantity += amount;
        this.bookValue += amount * this.price;
    }

    /**
     * Sells a specific number of units at a given price and updates the price.
     * @param amount Number of units to sell
     * @param price Price per unit to sell at
     * @return Remaining quantity if sale successful, or -1 if not enough units
     */
    public int sellUnits(int amount, double price) throws QuantityRangeError, PriceRangeError {
        if (amount <= 0)
            throw new QuantityRangeError();
        if (this.quantity >= amount) {
            if (this.price != price) {
                this.setPrice(price);
            }
            int newAmount = this.quantity - amount;
            this.bookValue = this.bookValue * ((double)newAmount / this.quantity);
            this.quantity = newAmount;
            return newAmount;
        }
        return Stock_Management.ERROR_INSUFFICIENT_QUANTITY;
    }

    /**
     * Calculates the gain or profit made on this mutual fund.
     * @return The total gain for the mutual fund
     */
    public double getGain() {
        return (this.quantity * this.price - MutualFunds.REDEMPTION) - this.bookValue;
    }

    /**
     * Calculates payment received after selling a certain number of units.
     * @param amount Number of units sold
     * @return The payment received after selling
     */
    public double getPaymentReceived(int amount) {
        return amount * this.price - MutualFunds.REDEMPTION;
    }

    /**
     * Prints out all the details of this Mutual Fund
     */
    public void print() {
        System.out.println("Investment Type: Mutual Fund");
        System.out.println("Symbol: " + this.symbol);
        System.out.println("Name: " + this.name);
        System.out.printf("Current Price: $%.2f\n", this.price);
        System.out.println("Owned Quantity: " + this.quantity);
        System.out.printf("Book Value: $%.2f\n", this.bookValue);
    }

    /**
     * Checks if this mutual fund has the same symbol as another one.
     * @param obj The mutual fund to compare with
     * @return true if symbols match, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return super.equals(obj);
    }

    /**
     * Returns all the details of this Mutual Fund as a string.
     * @return String representation of this Mutual Fund
     */
    @Override
    public String toString() {
        String output = "Investment Type: Mutual Fund\n";
        output += "Symbol: " + this.symbol + "\n";
        output += "Name: " + this.name + "\n";
        output += String.format("Current Price: $%.2f\n", this.price);
        output += "Owned Quantity: " + this.quantity + "\n";
        output += String.format("Book Value: $%.2f\n", this.bookValue);
        return output;
    }
}

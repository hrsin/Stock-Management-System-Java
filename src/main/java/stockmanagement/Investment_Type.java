package stockmanagement;

/**
 *
 * @author rishi
 */
public abstract class Investment_Type{
    protected String symbol;      // Code representing the investment
    protected String name;        // Name of the investment
    protected int quantity = 0;   // Quantity owned
    protected double price;       // Current price
    protected double bookValue = 0; // Total invested amount
    
    /**
     * Creates a new Investment object with the given details.
     * @param symbol The code for the Investment (e.g., stock or fund)
     * @param name The name of the Investment
     * @param price The price per unit of the Investment
     * @throws stockmanagement.Investment_Type.EmptySymbolError If symbol is empty
     * @throws stockmanagement.Investment_Type.PriceRangeError If price is zero or negative
     * @throws stockmanagement.Investment_Type.EmptyNameError If name is empty
     */
    public Investment_Type(String symbol, String name, double price) throws EmptySymbolError,
                                                                       EmptyNameError,
                                                                       PriceRangeError {
        if (symbol.isEmpty())
            throw new EmptySymbolError();
        if (name.isEmpty())
            throw new EmptyNameError();
        if (price <= 0)
            throw new PriceRangeError();
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the symbol code of the Investment.
     * @return The symbol code
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets a new symbol code for the Investment.
     * @param symbol The new symbol code
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets the name of the Investment.
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a new name for the Investment.
     * @param name The new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the current quantity owned.
     * @return The quantity owned
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Updates the quantity of units owned.
     * @param quantity The new quantity
     * @throws stockmanagement.Investment_Type.QuantityRangeError If quantity is zero or negative
     */
    public void setQuantity(int quantity) throws QuantityRangeError {
        if (quantity <= 0)
            throw new QuantityRangeError();
        this.quantity = quantity;
    }

    /**
     * Gets the current price of the Investment.
     * @return The current price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets a new price for the Investment.
     * @param price The new price
     * @throws stockmanagement.Investment_Type.PriceRangeError If price is zero or negative
     */
    public void setPrice(double price) throws PriceRangeError {
        if (price <= 0)
            throw new PriceRangeError();
        this.price = price;
    }

    /**
     * Gets the book value, which is the total investment cost.
     * @return The book value
     */
    public double getBookValue() {
        return bookValue;
    }

    /**
     * Sets a new book value for the Investment.
     * @param bookValue The new book value
     */
    public void setBookValue(double bookValue) {
        this.bookValue = bookValue;
    }

    /**
     * Checks if this investment is the same as another investment by comparing symbols.
     * @param obj Another investment object to compare
     * @return True if symbols match, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Stock) {
            Stock other = (Stock) obj;
            return symbol.equalsIgnoreCase(other.getSymbol());
        } else if (obj instanceof MutualFunds) {
            MutualFunds other = (MutualFunds) obj;
            return symbol.equalsIgnoreCase(other.getSymbol());
        } else {
            Investment_Type other = (Investment_Type) obj;
            return symbol.equalsIgnoreCase(other.getSymbol());
        }
    }

    /**
     * Returns a string with the main details of the Investment.
     * @return The Investment details as a string
     */
    @Override
    public String toString() {
        return this.symbol + ", " + this.name + ", " + this.price + ", " +
               this.quantity + ", " + this.bookValue;
    }
    
    /**
     * Calculates the gain (profit or loss) on the investment.
     * @return The gain amount
     */
    public abstract double getGain();
    
    /**
     * Calculates payment received from selling part of the investment.
     * @param amount The quantity being sold
     * @return The payment received
     */
    public abstract double getPaymentReceived(int amount);

    // Custom exceptions for various errors
    protected static class EmptySymbolError extends Exception {
        public EmptySymbolError() {}
    }

    protected static class EmptyNameError extends Exception {
        public EmptyNameError() {}
    }

    protected static class PriceRangeError extends Exception {
        public PriceRangeError() {}
    }
    
    protected static class QuantityRangeError extends Exception {
        public QuantityRangeError() {}
    }
}

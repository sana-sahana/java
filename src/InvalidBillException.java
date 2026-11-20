// InvalidBillException.java
// Vehicle Service Management System - Member 3: Spare Parts & Billing

/**
 * Custom checked exception thrown when an invalid bill condition occurs,
 * such as a negative spare part cost, negative labour charge, or negative total bill amount.
 */
public class InvalidBillException extends Exception {
    // Default constructor
    public InvalidBillException() {
        super("Invalid bill amount encountered.");
    }

    // Constructor that accepts a custom error message
    public InvalidBillException(String message) {
        super(message);
    }
}

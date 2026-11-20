// SparePart.java
// Vehicle Service Management System - Member 3: Spare Parts & Billing

/**
 * Represents a spare part used during vehicle servicing.
 * Stores details such as the part name and its cost.
 */
public class SparePart {
    // Member field for spare part name
    private String name;

    // Default constructor
    public SparePart() {
        this.name = "";
    }

    // Constructor with name
    public SparePart(String name) {
        this.name = name;
    }

    // Getter for spare part name
    public String getName() {
        return name;
    }

    // Setter for spare part name
    public void setName(String name) {
        this.name = name;
    }
}

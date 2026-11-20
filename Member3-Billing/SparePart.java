// SparePart.java
// Vehicle Service Management System - Member 3: Spare Parts & Billing

/**
 * Represents a spare part used during vehicle servicing.
 * Stores details such as the part name and its cost.
 */
public class SparePart {
    // Member fields
    private String name;
    private double cost;

    // Default constructor
    public SparePart() {
        this.name = "";
        this.cost = 0.0;
    }

    // Constructor with name and cost
    public SparePart(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    // Getter for spare part name
    public String getName() {
        return name;
    }

    // Setter for spare part name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for spare part cost
    public double getCost() {
        return cost;
    }

    // Setter for spare part cost
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return name + " - Rs. " + (cost == (long) cost ? String.format("%d", (long) cost) : String.format("%.2f", cost));
    }
}

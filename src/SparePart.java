// SparePart.java
// Vehicle Service Management System - Member 3: Spare Parts & Billing

import java.util.Scanner;

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

    // Constructor with name
    public SparePart(String name) {
        this.name = name;
        this.cost = 0.0;
    }

    // Constructor with name and cost, validating that cost is not negative
    public SparePart(String name, double cost) throws InvalidBillException {
        this.name = name;
        setCost(cost);
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

    /**
     * Sets the spare part cost with negative amount validation.
     * 
     * @param cost spare part cost
     * @throws InvalidBillException if cost is negative
     */
    public void setCost(double cost) throws InvalidBillException {
        if (cost < 0) {
            throw new InvalidBillException("Spare part cost cannot be negative: Rs. " + cost);
        }
        this.cost = cost;
    }

    /**
     * Reads spare part details from console input using Scanner with validation.
     * 
     * @param scanner the Scanner object for console input
     * @param partIndex index number of the spare part being entered
     * @return newly created SparePart object with user entered details
     * @throws InvalidBillException if the entered cost is negative
     */
    public static SparePart inputSparePart(Scanner scanner, int partIndex) throws InvalidBillException {
        System.out.println("\n--- Entering Details for Spare Part #" + partIndex + " ---");
        System.out.print("Enter spare part name: ");
        String partName = scanner.nextLine().trim();
        while (partName.isEmpty()) {
            System.out.print("Part name cannot be empty. Enter spare part name: ");
            partName = scanner.nextLine().trim();
        }

        System.out.print("Enter cost for " + partName + ": ");
        double partCost = Double.parseDouble(scanner.nextLine().trim());

        // Validates non-negative amount via constructor
        return new SparePart(partName, partCost);
    }

    @Override
    public String toString() {
        return name + " - Rs. " + (cost == (long) cost ? String.format("%d", (long) cost) : String.format("%.2f", cost));
    }
}

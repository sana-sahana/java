// Billing.java
// Vehicle Service Management System - Member 3: Spare Parts & Billing

import java.util.ArrayList;

/**
 * Manages the billing for vehicle servicing.
 * Handles spare parts collection, labour charges, and bill computations.
 */
public class Billing {
    // List to store multiple spare parts
    private ArrayList<SparePart> spareParts;

    // Default constructor
    public Billing() {
        this.spareParts = new ArrayList<>();
    }

    /**
     * Adds a spare part to the billing record.
     * 
     * @param part the SparePart to add
     */
    public void addSparePart(SparePart part) {
        if (part != null) {
            spareParts.add(part);
        }
    }

    /**
     * Returns the list of spare parts.
     * 
     * @return ArrayList of spare parts
     */
    public ArrayList<SparePart> getSpareParts() {
        return spareParts;
    }
}

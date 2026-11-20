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
    // Labour or service charges
    private double labourCharge;

    // Default constructor
    public Billing() {
        this.spareParts = new ArrayList<>();
        this.labourCharge = 0.0;
    }

    // Parameterized constructor with labour charge
    public Billing(double labourCharge) {
        this.spareParts = new ArrayList<>();
        this.labourCharge = labourCharge;
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

    /**
     * Gets the labour charges.
     * 
     * @return labour charge amount
     */
    public double getLabourCharge() {
        return labourCharge;
    }

    /**
     * Sets the labour charges.
     * 
     * @param labourCharge labour charge amount
     */
    public void setLabourCharge(double labourCharge) {
        this.labourCharge = labourCharge;
    }
}

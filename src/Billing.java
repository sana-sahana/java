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

    // Parameterized constructor with labour charge validation
    public Billing(double labourCharge) throws InvalidBillException {
        this.spareParts = new ArrayList<>();
        setLabourCharge(labourCharge);
    }

    /**
     * Adds a spare part to the billing record.
     * 
     * @param part the SparePart to add
     * @throws InvalidBillException if spare part or its cost is invalid
     */
    public void addSparePart(SparePart part) throws InvalidBillException {
        if (part == null) {
            throw new InvalidBillException("Spare part cannot be null.");
        }
        if (part.getCost() < 0) {
            throw new InvalidBillException("Spare part cost cannot be negative: Rs. " + part.getCost());
        }
        spareParts.add(part);
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
     * Sets the labour charges with negative amount validation.
     * 
     * @param labourCharge labour charge amount
     * @throws InvalidBillException if labour charge is negative
     */
    public void setLabourCharge(double labourCharge) throws InvalidBillException {
        if (labourCharge < 0) {
            throw new InvalidBillException("Labour/service charges cannot be negative: Rs. " + labourCharge);
        }
        this.labourCharge = labourCharge;
    }

    /**
     * Calculates the total cost of all spare parts.
     * 
     * @return total cost of spare parts
     */
    public double calculateTotalSparePartsCost() {
        double totalSpareParts = 0.0;
        for (SparePart part : spareParts) {
            totalSpareParts += part.getCost();
        }
        return totalSpareParts;
    }

    /**
     * Implements basic bill calculation.
     * Computes the sum of all spare parts and adds the labour charge.
     * 
     * @return total bill amount
     * @throws InvalidBillException if the calculated bill amount is negative
     */
    public double calculateBill() throws InvalidBillException {
        return calculateFinalBill();
    }

    /**
     * Calculates the standard final bill amount with negative amount validation.
     * Final Bill = Total Spare Parts Cost + Labour Charges.
     * 
     * @return standard final bill amount
     * @throws InvalidBillException if the final bill amount is negative
     */
    public double calculateFinalBill() throws InvalidBillException {
        double sparePartsTotal = calculateTotalSparePartsCost();
        double finalBill = sparePartsTotal + this.labourCharge;
        if (finalBill < 0) {
            throw new InvalidBillException("Final bill amount cannot be negative: Rs. " + finalBill);
        }
        return finalBill;
    }

    /**
     * Method Overloading (Variant 1):
     * Calculates the final bill with a discount percentage applied.
     * 
     * @param discountPercentage percentage discount to apply (e.g. 10 for 10%)
     * @return discounted final bill amount
     * @throws InvalidBillException if the final bill amount is negative or discount invalid
     */
    public double calculateFinalBill(double discountPercentage) throws InvalidBillException {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new InvalidBillException("Invalid discount percentage: " + discountPercentage + "%. Must be between 0 and 100.");
        }
        double baseBill = calculateFinalBill();
        double discountAmount = (baseBill * discountPercentage) / 100.0;
        double discountedTotal = baseBill - discountAmount;
        if (discountedTotal < 0) {
            throw new InvalidBillException("Final bill after discount cannot be negative: Rs. " + discountedTotal);
        }
        return discountedTotal;
    }

    /**
     * Method Overloading (Variant 2):
     * Calculates the final bill with a flat discount amount and tax percentage.
     * 
     * @param discountAmount flat discount amount in currency
     * @param taxPercentage tax percentage to apply on the discounted amount
     * @return final bill amount after discount and tax
     * @throws InvalidBillException if amounts or percentages are negative
     */
    public double calculateFinalBill(double discountAmount, double taxPercentage) throws InvalidBillException {
        if (discountAmount < 0) {
            throw new InvalidBillException("Discount amount cannot be negative: Rs. " + discountAmount);
        }
        if (taxPercentage < 0) {
            throw new InvalidBillException("Tax percentage cannot be negative: " + taxPercentage + "%");
        }
        double baseBill = calculateFinalBill();
        double afterDiscount = baseBill - discountAmount;
        if (afterDiscount < 0) {
            throw new InvalidBillException("Discount Rs. " + discountAmount + " exceeds total bill Rs. " + baseBill);
        }
        double taxAmount = (afterDiscount * taxPercentage) / 100.0;
        return afterDiscount + taxAmount;
    }
}

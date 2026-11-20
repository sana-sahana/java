// BillReceipt.java
// Vehicle Service Management System - Member 3: Spare Parts & Billing

import java.util.ArrayList;

/**
 * Generates and prints a neatly formatted console receipt
 * for the Vehicle Service Management System.
 */
public class BillReceipt {

    /**
     * Prints the standard bill receipt to the console.
     * 
     * @param billing the Billing object containing parts and labour details
     */
    public static void printReceipt(Billing billing) {
        if (billing == null) {
            System.out.println("No billing data to display.");
            return;
        }

        try {
            double sparePartsTotal = billing.calculateTotalSparePartsCost();
            double labourCharge = billing.getLabourCharge();
            double finalAmount = billing.calculateFinalBill();
            ArrayList<SparePart> parts = billing.getSpareParts();

            System.out.println("\n================================");
            System.out.println("       VEHICLE SERVICE BILL     ");
            System.out.println("================================");
            System.out.println("Spare Parts:");

            if (parts.isEmpty()) {
                System.out.println("  (No spare parts used)");
            } else {
                for (int i = 0; i < parts.size(); i++) {
                    SparePart part = parts.get(i);
                    String itemLine = String.format("%d. %-16s Rs. %s", 
                        (i + 1), 
                        part.getName(), 
                        formatCurrency(part.getCost()));
                    System.out.println(itemLine);
                }
            }

            System.out.println();
            System.out.printf("%-20sRs. %s%n", "Total Spare Parts:", formatCurrency(sparePartsTotal));
            System.out.printf("%-20sRs. %s%n", "Labour Charges:", formatCurrency(labourCharge));
            System.out.println("--------------------------------");
            System.out.printf("%-20sRs. %s%n", "Final Amount:", formatCurrency(finalAmount));
            System.out.println("================================");

        } catch (InvalidBillException e) {
            System.out.println("Error generating receipt: " + e.getMessage());
        }
    }

    /**
     * Prints the bill receipt with an applied discount percentage (demonstrating method overloading).
     * 
     * @param billing the Billing object
     * @param discountPercentage discount percentage to apply
     */
    public static void printReceipt(Billing billing, double discountPercentage) {
        if (billing == null) {
            System.out.println("No billing data to display.");
            return;
        }

        try {
            double sparePartsTotal = billing.calculateTotalSparePartsCost();
            double labourCharge = billing.getLabourCharge();
            double originalBill = billing.calculateFinalBill();
            double finalAmount = billing.calculateFinalBill(discountPercentage);
            double discountValue = originalBill - finalAmount;
            ArrayList<SparePart> parts = billing.getSpareParts();

            System.out.println("\n================================");
            System.out.println("       VEHICLE SERVICE BILL     ");
            System.out.println("================================");
            System.out.println("Spare Parts:");

            if (parts.isEmpty()) {
                System.out.println("  (No spare parts used)");
            } else {
                for (int i = 0; i < parts.size(); i++) {
                    SparePart part = parts.get(i);
                    String itemLine = String.format("%d. %-16s Rs. %s", 
                        (i + 1), 
                        part.getName(), 
                        formatCurrency(part.getCost()));
                    System.out.println(itemLine);
                }
            }

            System.out.println();
            System.out.printf("%-20sRs. %s%n", "Total Spare Parts:", formatCurrency(sparePartsTotal));
            System.out.printf("%-20sRs. %s%n", "Labour Charges:", formatCurrency(labourCharge));
            System.out.printf("%-20sRs. %s%n", "Subtotal:", formatCurrency(originalBill));
            System.out.printf("%-20s-Rs. %s (%.0f%%)%n", "Discount:", formatCurrency(discountValue), discountPercentage);
            System.out.println("--------------------------------");
            System.out.printf("%-20sRs. %s%n", "Final Amount:", formatCurrency(finalAmount));
            System.out.println("================================");

        } catch (InvalidBillException e) {
            System.out.println("Error generating receipt: " + e.getMessage());
        }
    }

    /**
     * Helper to format numbers cleanly (e.g., 500 instead of 500.0).
     */
    private static String formatCurrency(double amount) {
        if (amount == (long) amount) {
            return String.format("%d", (long) amount);
        } else {
            return String.format("%.2f", amount);
        }
    }
}

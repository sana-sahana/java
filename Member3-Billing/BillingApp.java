// BillingApp.java
// Vehicle Service Management System - Member 3: Spare Parts & Billing

import java.util.Scanner;

/**
 * Console-based application for Vehicle Service Billing.
 * Manages user interactions, input collection, validation, and exception handling.
 */
public class BillingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("   VEHICLE SERVICE MANAGEMENT SYSTEM - BILLING    ");
        System.out.println("==================================================");

        boolean continueBilling = true;

        while (continueBilling) {
            try {
                Billing billing = new Billing();

                // Step 1: Input number of spare parts
                System.out.print("\nEnter the number of spare parts used: ");
                String countInput = scanner.nextLine().trim();
                int partCount;
                try {
                    partCount = Integer.parseInt(countInput);
                } catch (NumberFormatException e) {
                    throw new InvalidBillException("Invalid input for number of spare parts. Please enter a valid integer.");
                }

                if (partCount < 0) {
                    throw new InvalidBillException("Number of spare parts cannot be negative: " + partCount);
                }

                // Step 2: Input each spare part
                for (int i = 1; i <= partCount; i++) {
                    boolean partAdded = false;
                    while (!partAdded) {
                        try {
                            SparePart part = SparePart.inputSparePart(scanner, i);
                            billing.addSparePart(part);
                            partAdded = true;
                            System.out.println("✓ Added spare part: " + part.getName() + " (Rs. " + formatAmount(part.getCost()) + ")");
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Please enter a valid numeric value for spare part cost.");
                        } catch (InvalidBillException e) {
                            System.out.println("Validation Error: " + e.getMessage());
                            System.out.println("Please re-enter details for this spare part.");
                        }
                    }
                }

                // Step 3: Input labour/service charges
                boolean labourValid = false;
                while (!labourValid) {
                    try {
                        System.out.print("\nEnter labour / service charges: ");
                        String labourInput = scanner.nextLine().trim();
                        double labour = Double.parseDouble(labourInput);
                        billing.setLabourCharge(labour);
                        labourValid = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Please enter a valid numeric value for labour charges.");
                    } catch (InvalidBillException e) {
                        System.out.println("Validation Error: " + e.getMessage());
                    }
                }

                // Step 4: Calculate totals
                double sparePartsTotal = billing.calculateTotalSparePartsCost();
                double finalBill = billing.calculateFinalBill();

                System.out.println("\n[Billing Calculations Completed Successfully]");
                System.out.println("Total Spare Parts Cost: Rs. " + formatAmount(sparePartsTotal));
                System.out.println("Labour Charges:        Rs. " + formatAmount(billing.getLabourCharge()));
                System.out.println("Final Bill Amount:     Rs. " + formatAmount(finalBill));

            } catch (InvalidBillException e) {
                System.out.println("\n[Bill Processing Error]: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\n[Unexpected Error]: " + e.getMessage());
            }

            System.out.print("\nDo you want to process another bill? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes") && !response.equals("y")) {
                continueBilling = false;
                System.out.println("\nThank you for using Vehicle Service Billing System. Goodbye!");
            }
        }

        scanner.close();
    }

    /**
     * Helper to format double amount without unnecessary decimal places.
     */
    public static String formatAmount(double amount) {
        if (amount == (long) amount) {
            return String.format("%d", (long) amount);
        } else {
            return String.format("%.2f", amount);
        }
    }
}

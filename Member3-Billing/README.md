# Member 3 – Spare Parts & Billing Module
**Vehicle Service Management System**

This module is an independent, console-based Java application designed for **Member 3: Spare Parts & Billing**. It handles recording spare parts used during vehicle servicing, calculating service/labour charges, validating monetary values with custom exceptions, and printing formatted bill receipts.

---

## Features & Responsibilities

1. **`SparePart` Class**:
   - Represents an individual spare part with `name` and `cost`.
   - Encapsulation using `private` fields with getters and setters.
   - Interactive console input helper method (`inputSparePart`) using `java.util.Scanner`.
   - Validates that spare part costs are not negative.

2. **`Billing` Class**:
   - Stores multiple spare parts using `ArrayList<SparePart>`.
   - Stores labour/service charges.
   - Calculates total spare parts cost (`calculateTotalSparePartsCost()`).
   - Calculates the final bill amount (`calculateFinalBill()`).
   - Implements **Method Overloading**:
     - `calculateFinalBill()`: Standard bill (Total Spare Parts + Labour Charges).
     - `calculateFinalBill(double discountPercentage)`: Bill with percentage discount.
     - `calculateFinalBill(double discountAmount, double taxPercentage)`: Bill with flat discount and tax.

3. **`InvalidBillException` Custom Exception**:
   - Custom checked exception extending `java.lang.Exception`.
   - Thrown when negative values are provided for spare part costs, labour charges, or final bills.
   - Handled cleanly using `try-catch` blocks.

4. **`BillReceipt` Class**:
   - Generates a neat, aligned console receipt matching project specifications.
   - Shows spare parts list with individual costs, total spare parts cost, labour charges, and the final bill amount.

5. **`BillingApp` Class**:
   - Main entry point for the console workflow.
   - Welcomes the user, accepts input using `Scanner`, validates amounts, and displays the formatted bill receipt.

---

## Directory Structure

```
Member3-Billing/
├── SparePart.java              # Spare part model and input
├── Billing.java                # Billing logic & calculations
├── InvalidBillException.java   # Custom exception class
├── BillReceipt.java            # Formatted receipt display
├── BillingApp.java             # Console application entry point
└── README.md                   # Documentation
```

---

## Prerequisites

- Java Development Kit (JDK 8 or higher).
- Core Java only (no external libraries or frameworks required).

---

## How to Compile and Run

### 1. Compile all Java files
From inside `Member3-Billing`:
```bash
cd Member3-Billing
javac *.java
```

Or from the root directory:
```bash
javac Member3-Billing/*.java
```

### 2. Run the Billing Console Application
From inside `Member3-Billing`:
```bash
java BillingApp
```

Or from the root directory:
```bash
java -cp Member3-Billing BillingApp
```

---

## Sample Console Workflow & Receipt

```
==================================================
   VEHICLE SERVICE MANAGEMENT SYSTEM - BILLING    
==================================================

Enter the number of spare parts used: 2

--- Entering Details for Spare Part #1 ---
Enter spare part name: Engine Oil
Enter cost for Engine Oil: 500
✓ Added spare part: Engine Oil (Rs. 500)

--- Entering Details for Spare Part #2 ---
Enter spare part name: Air Filter
Enter cost for Air Filter: 300
✓ Added spare part: Air Filter (Rs. 300)

Enter labour / service charges: 400

Generating final bill receipt...

================================
       VEHICLE SERVICE BILL     
================================
Spare Parts:
1. Engine Oil       Rs. 500
2. Air Filter       Rs. 300

Total Spare Parts:  Rs. 800
Labour Charges:     Rs. 400
--------------------------------
Final Amount:       Rs. 1200
================================
```

---

## 15 Incremental Git Milestones

1. `Created SparePart class`
2. `Added spare part name`
3. `Added spare part cost`
4. `Added spare parts input`
5. `Created Billing class`
6. `Added labour charge`
7. `Implemented bill calculation`
8. `Added spare parts total`
9. `Calculated final bill`
10. `Implemented method overloading`
11. `Added InvalidBillException`
12. `Added negative amount validation`
13. `Implemented exception handling`
14. `Created bill receipt display`
15. `Tested billing functionality`

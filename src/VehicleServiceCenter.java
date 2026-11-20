import java.util.Scanner;
import java.util.HashMap;

public class VehicleServiceCenter {

    // Scanner for reading console inputs
    static Scanner sc = new Scanner(System.in);

    // Objects to manage service records and queue
    static ServiceHistory history = new ServiceHistory();
    static ServiceQueue queue = new ServiceQueue(50);

    // HashMaps to associate Job ID with Vehicle, ServiceStatus, and Billing
    static HashMap<Integer, Vehicle> vehicleMap = new HashMap<Integer, Vehicle>();
    static HashMap<Integer, ServiceStatus> statusMap = new HashMap<Integer, ServiceStatus>();
    static HashMap<Integer, Billing> billMap = new HashMap<Integer, Billing>();

    // Creates a new vehicle service job
    public static void createJob() {
        System.out.print("Enter Customer Name: ");
        String customerName = sc.nextLine().trim();

        System.out.print("Enter Vehicle Number: ");
        String vehicleNumber = sc.nextLine().trim();

        System.out.print("Enter Vehicle Model: ");
        String vehicleModel = sc.nextLine().trim();

        System.out.print("Enter Issue / Service Description: ");
        String issueDescription = sc.nextLine().trim();

        if (customerName.isEmpty() || vehicleNumber.isEmpty() || vehicleModel.isEmpty() || issueDescription.isEmpty()) {
            System.out.println("Error: All fields are required to create a job card.");
            return;
        }

        // Create Vehicle and JobCard using Member 1's classes
        Vehicle vehicle = new Vehicle(vehicleNumber, customerName, vehicleModel);
        JobCard job = new JobCard(vehicleNumber, issueDescription);
        int id = job.getJobId();

        // Check whether Job ID already exists in history
        if (history.searchJob(id) != null) {
            System.out.println("Error: Job ID already exists!");
            return;
        }

        // Store vehicle details and service history record
        vehicleMap.put(id, vehicle);
        history.addRecord(job);

        // Associate initial service status (Pending) from Member 2
        statusMap.put(id, ServiceStatus.PENDING);

        // Add Job ID to pending queue
        try {
            queue.enqueue(id);
            System.out.println("Job Card created successfully with Job ID: " + id);
        } catch (ServiceQueue.QueueFullException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Updates the service status of a vehicle
    public static void updateService() {
        int id;
        try {
            System.out.print("Enter Job ID: ");
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: Job ID must be a valid number!");
            return;
        }

        // Check whether Job ID exists
        if (history.searchJob(id) == null || !statusMap.containsKey(id)) {
            System.out.println("Error: Job ID not found!");
            return;
        }

        System.out.println("\nSelect Status:");
        System.out.println("1 - Pending");
        System.out.println("2 - In Progress");
        System.out.println("3 - Completed");
        System.out.print("Enter choice: ");

        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number (1, 2, or 3)!");
            return;
        }

        String statusInput;
        if (choice == 1) {
            statusInput = "PENDING";
        } else if (choice == 2) {
            statusInput = "IN_PROGRESS";
        } else if (choice == 3) {
            statusInput = "COMPLETED";
        } else {
            statusInput = "INVALID";
        }

        try {
            // Update status using Member 2's ServiceStatus enum and exception handling
            ServiceStatus newStatus = ServiceStatus.fromInput(statusInput);
            statusMap.put(id, newStatus);
            System.out.println("Service status updated successfully to: " + newStatus);

            // If status is no longer Pending, remove from pending queue
            if (newStatus != ServiceStatus.PENDING) {
                queue.removeJob(id);
            }
        } catch (InvalidServiceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Generates spare parts and labour bill
    public static void generateBill() {
        int id;
        try {
            System.out.print("Enter Job ID: ");
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: Job ID must be a valid number!");
            return;
        }

        // Check whether job exists
        if (history.searchJob(id) == null) {
            System.out.println("Error: Job ID not found!");
            return;
        }

        double partsCost;
        double labourCharges;
        try {
            System.out.print("Enter Spare Parts Cost: ");
            partsCost = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Enter Labour Charges: ");
            labourCharges = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: Cost and charges must be valid numbers!");
            return;
        }

        Billing bill = new Billing();

        try {
            // Set labour charge and add spare parts using Member 3's methods
            bill.setLabourCharge(labourCharges);
            if (partsCost > 0) {
                bill.addSparePart(new SparePart("Replacement Parts", partsCost));
            }
            billMap.put(id, bill);

            System.out.println("\nBill generated successfully!");
            BillReceipt.printReceipt(bill);
        } catch (InvalidBillException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Displays the complete service details of one vehicle
    public static void searchHistory() {
        int id;
        try {
            System.out.print("Enter Job ID: ");
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: Job ID must be a valid number!");
            return;
        }

        JobCard job = history.searchJob(id);

        // If job does not exist
        if (job == null) {
            System.out.println("Error: Job ID not found!");
            return;
        }

        System.out.println("\n===== VEHICLE & JOB CARD DETAILS =====");
        if (vehicleMap.containsKey(id)) {
            vehicleMap.get(id).displayVehicle();
        }
        job.displayJobCard();

        System.out.println("\n===== SERVICE STATUS =====");
        if (statusMap.containsKey(id)) {
            System.out.println("Current Status: " + statusMap.get(id));
        }

        System.out.println("\n===== BILLING DETAILS =====");
        if (billMap.containsKey(id)) {
            BillReceipt.printReceipt(billMap.get(id));
        } else {
            System.out.println("Bill has not been generated yet.");
        }
    }

    // Displays all pending jobs in the queue
    public static void displayPendingJobs() {
        queue.displayQueue();
    }

    // Processes the next vehicle waiting for service
    public static void processNextJob() {
        try {
            int jobId = queue.dequeue();
            System.out.println("Processing Job ID: " + jobId);

            if (statusMap.containsKey(jobId)) {
                statusMap.put(jobId, ServiceStatus.IN_PROGRESS);
                System.out.println("Job ID " + jobId + " status updated to In progress.");
            }
        } catch (ServiceQueue.QueueEmptyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Displays all service records
    public static void displayAllRecords() {
        history.displayAllRecords();
    }

    public static void main(String[] args) {
        int choice = 0;

        do {
            System.out.println("\n==================================");
            System.out.println("      VEHICLE SERVICE CENTER      ");
            System.out.println("==================================");
            System.out.println("1. Create Job Card");
            System.out.println("2. Update Service Status");
            System.out.println("3. Generate Spare Parts Bill");
            System.out.println("4. View Service History");
            System.out.println("5. View Pending Jobs");
            System.out.println("6. Process Next Job");
            System.out.println("7. View All Service Records");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number (1-8).");
                continue;
            }

            switch (choice) {
                case 1:
                    createJob();
                    break;
                case 2:
                    updateService();
                    break;
                case 3:
                    generateBill();
                    break;
                case 4:
                    searchHistory();
                    break;
                case 5:
                    displayPendingJobs();
                    break;
                case 6:
                    processNextJob();
                    break;
                case 7:
                    displayAllRecords();
                    break;
                case 8:
                    System.out.println("Exiting application. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select between 1 and 8.");
            }
        } while (choice != 8);
    }
}

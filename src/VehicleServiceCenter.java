import java.util.Scanner;
import java.util.HashMap;

public class VehicleServiceCenter {

    // Scanner for reading console inputs
    static Scanner sc = new Scanner(System.in);

    // Objects to manage service records and queue
    static ServiceHistory history = new ServiceHistory();
    static ServiceQueue queue = new ServiceQueue(50);

    // HashMaps to associate Job ID with ServiceStatus and Billing
    static HashMap<Integer, ServiceStatus> statusMap = new HashMap<Integer, ServiceStatus>();
    static HashMap<Integer, Billing> billMap = new HashMap<Integer, Billing>();

    // Creates a new vehicle service job
    public static void createJob() {
        System.out.print("Enter Job ID: ");
        int id = Integer.parseInt(sc.nextLine());

        // Check whether Job ID already exists
        if (history.searchJob(id) != null) {
            System.out.println("Error: Job ID already exists! Please enter a unique ID.");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String customerName = sc.nextLine();

        System.out.print("Enter Vehicle Number: ");
        String vehicleNumber = sc.nextLine();

        System.out.print("Enter Vehicle Model: ");
        String vehicleModel = sc.nextLine();

        // Create JobCard using Member 1's constructor
        JobCard job = new JobCard(id, customerName, vehicleNumber, vehicleModel);
        history.addRecord(job);

        // Create ServiceStatus using Member 2's constructor
        ServiceStatus status = new ServiceStatus(id);
        statusMap.put(id, status);

        // Add Job ID to pending queue
        try {
            queue.enqueue(id);
        } catch (ServiceQueue.QueueFullException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Updates the service status of a vehicle
    public static void updateService() {
        System.out.print("Enter Job ID: ");
        int id = Integer.parseInt(sc.nextLine());

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
        int newStatus = Integer.parseInt(sc.nextLine());

        ServiceStatus status = statusMap.get(id);

        try {
            status.updateStatus(newStatus);

            // If status is no longer Pending, remove from pending queue
            if (newStatus != 1) {
                queue.removeJob(id);
            }
        } catch (InvalidServiceException e) {
            System.out.println("Error: " + e.getMessage());
        }
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
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
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
                    System.out.println("Generate Spare Parts Bill selected.");
                    break;
                case 4:
                    System.out.println("View Service History selected.");
                    break;
                case 5:
                    System.out.println("View Pending Jobs selected.");
                    break;
                case 6:
                    System.out.println("Process Next Job selected.");
                    break;
                case 7:
                    System.out.println("View All Service Records selected.");
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

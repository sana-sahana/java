import java.util.ArrayList;

public class VehicleManager {
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<JobCard> jobCards = new ArrayList<>();

    public boolean registerVehicle(String vehicleNumber, String ownerName, String model) {
        if (vehicleNumber == null || vehicleNumber.trim().isEmpty() ||
            ownerName == null || ownerName.trim().isEmpty() ||
            model == null || model.trim().isEmpty()) {
            System.out.println("Error: Vehicle details cannot be empty.");
            return false;
        }
        if (searchVehicle(vehicleNumber) != null) {
            System.out.println("Error: Vehicle " + vehicleNumber + " is already registered.");
            return false;
        }
        vehicles.add(new Vehicle(vehicleNumber, ownerName, model));
        System.out.println("Vehicle registered successfully: " + vehicleNumber);
        return true;
    }

    public Vehicle searchVehicle(String vehicleNumber) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleNumber().equalsIgnoreCase(vehicleNumber)) {
                return v;
            }
        }
        return null;
    }

    public JobCard createJobCard(String vehicleNumber, String issueDescription) {
        Vehicle v = searchVehicle(vehicleNumber);
        if (v == null) {
            System.out.println("Error: Vehicle " + vehicleNumber + " not registered. Cannot create Job Card.");
            return null;
        }
        if (issueDescription == null || issueDescription.trim().isEmpty()) {
            System.out.println("Error: Issue description cannot be empty.");
            return null;
        }
        JobCard jc = new JobCard(vehicleNumber, issueDescription);
        jobCards.add(jc);
        System.out.println("Job Card created successfully! Assigned Job ID: " + jc.getJobId());
        return jc;
    }

    public void displayAllJobCards() {
        if (jobCards.isEmpty()) {
            System.out.println("No job cards found.");
            return;
        }
        for (JobCard jc : jobCards) {
            jc.displayJobCard();
        }
    }

    public JobCard searchJobCard(int jobId) {
        for (JobCard jc : jobCards) {
            if (jc.getJobId() == jobId) return jc;
        }
        return null;
    }
}

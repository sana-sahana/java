import java.util.ArrayList;

public class VehicleManager {
    private ArrayList<Vehicle> vehicles = new ArrayList<>();

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
}

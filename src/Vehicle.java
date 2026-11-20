public class Vehicle {
    private String vehicleNumber, ownerName, model;

    public Vehicle(String vehicleNumber, String ownerName, String model) {
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
        this.model = model;
    }

    public String getVehicleNumber() { return vehicleNumber; }
    public String getOwnerName() { return ownerName; }
    public String getModel() { return model; }

    public void displayVehicle() {
        System.out.println("Vehicle No: " + vehicleNumber + " | Owner: " + ownerName + " | Model: " + model);
    }
}

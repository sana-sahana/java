public class TestModule1 {
    public static void main(String[] args) {
        VehicleManager manager = new VehicleManager();

        System.out.println("=== 1. Registering Vehicles ===");
        manager.registerVehicle("KA01AB1234", "Rahul Sharma", "Swift");
        manager.registerVehicle("MH02CD5678", "Priya Patel", "City");

        System.out.println("\n=== 2. Testing Constraints ===");
        // Unregistered vehicle test (must be rejected)
        manager.createJobCard("DL03EF9999", "Brake pad replacement");

        System.out.println("\n=== 3. Creating Valid Job Cards ===");
        manager.createJobCard("KA01AB1234", "General Service & Oil Change");
        manager.createJobCard("MH02CD5678", "Wheel Alignment");

        System.out.println("\n=== 4. Displaying All Job Cards ===");
        manager.displayAllJobCards();
    }
}

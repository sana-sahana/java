public class JobCard {
    private static int counter = 1001;
    private int jobId;
    private String vehicleNumber, issueDescription;

    public JobCard(String vehicleNumber, String issueDescription) {
        this.jobId = counter++;
        this.vehicleNumber = vehicleNumber;
        this.issueDescription = issueDescription;
    }

    public int getJobId() { return jobId; }
    public String getVehicleNumber() { return vehicleNumber; }
    public String getIssueDescription() { return issueDescription; }

    public void displayJobCard() {
        System.out.println("Job ID: " + jobId + " | Vehicle: " + vehicleNumber + " | Issue: " + issueDescription);
    }
}

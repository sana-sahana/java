import java.util.ArrayList;

public class ServiceHistory {

    // Stores all vehicle service records
    private ArrayList<JobCard> records;

    // Constructor to initialize the list
    public ServiceHistory() {
        records = new ArrayList<JobCard>();
    }

    // Adds a new job card to service history
    public void addRecord(JobCard job) {
        records.add(job);
        System.out.println("Service record added successfully!");
    }
}

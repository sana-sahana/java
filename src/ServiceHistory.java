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

    // Searches for a job using Job ID (Linear Search)
    public JobCard searchJob(int id) {
        for (int i = 0; i < records.size(); i++) {
            JobCard job = records.get(i);
            if (job.getJobId() == id) {
                return job;
            }
        }
        return null;
    }

    // Displays all service records
    public void displayAllRecords() {
        if (records.isEmpty()) {
            System.out.println("No service records available.");
            return;
        }

        System.out.println("\n===== SERVICE HISTORY =====");
        for (int i = 0; i < records.size(); i++) {
            records.get(i).display();
            System.out.println("----------------------");
        }
    }

    // Displays a particular service record
    public void displayRecord(int id) {
        JobCard job = searchJob(id);

        if (job != null) {
            System.out.println("\n===== SERVICE DETAILS =====");
            job.display();
        } else {
            System.out.println("Job ID not found!");
        }
    }
}

/** Base type for service status records. */
public abstract class Service {
    private final String jobId;
    private final String description;
    private ServiceStatus status;

    protected Service(String jobId, String description) {
        this.jobId = jobId;
        this.description = description;
    }

    public String getJobId() { return jobId; }
    public String getDescription() { return description; }
    public ServiceStatus getStatus() { return status; }
    protected void setStatus(ServiceStatus status) { this.status = status; }

    public abstract String getServiceType();
}

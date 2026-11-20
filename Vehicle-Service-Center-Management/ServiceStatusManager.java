import java.util.HashMap;
import java.util.Map;

/** Stores service records by Job ID for quick lookup. */
public class ServiceStatusManager {
    private final Map<String, Service> services = new HashMap<>();

    public void addService(Service service) throws InvalidServiceException {
        if (service == null) throw new InvalidServiceException("Service record is required.");
        String jobId = service.getJobId();
        if (services.containsKey(jobId)) throw new InvalidServiceException("Duplicate Job ID: " + jobId);
        services.put(jobId, service);
    }

    public Service getService(String jobId) throws InvalidServiceException {
        if (jobId == null || jobId.trim().isEmpty()) throw new InvalidServiceException("Job ID cannot be empty.");
        Service service = services.get(jobId.trim().toUpperCase());
        if (service == null) throw new InvalidServiceException("Job ID not found: " + jobId);
        return service;
    }
}

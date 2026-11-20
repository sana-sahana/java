/** Status values supported by a service job. */
public enum ServiceStatus {
    PENDING("Pending");

    private String displayName;
    ServiceStatus(String displayName) { this.displayName = displayName; }
    @Override public String toString() { return displayName; }
}

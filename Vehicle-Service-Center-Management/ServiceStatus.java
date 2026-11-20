/** Status values supported by a service job. */
public enum ServiceStatus {
    PENDING("Pending"),
    IN_PROGRESS("In progress");

    private String displayName;
    ServiceStatus(String displayName) { this.displayName = displayName; }
    @Override public String toString() { return displayName; }
}

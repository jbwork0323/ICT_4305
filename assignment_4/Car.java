package assignment_4;

import java.time.LocalDate;

public class Car {
    private String permit;
    private LocalDate permitExpiration;
    private String license;
    private CarType type;
    private String owner;

    /* Creates a new Car. owner is the customerId of the Customer who registered the car. */
    public Car(String permit, LocalDate permitExpiration, String license, CarType type, String owner) {
        if (permit == null || permit.isBlank()) {
            throw new IllegalArgumentException("Permit is required.");
        }
        if (permitExpiration == null) {
            throw new IllegalArgumentException("Permit expiration is required.");
        }
        if (license == null || license.isBlank()) {
            throw new IllegalArgumentException("License is required.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Car type is required.");
        }
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("Owner is required.");
        }

        this.permit = permit;
        this.permitExpiration = permitExpiration;
        this.license = license;
        this.type = type;
        this.owner = owner;
    }

    public String getPermit() {
        return permit;
    }

    public LocalDate getPermitExpiration() {
        return permitExpiration;
    }

    public String getLicense() {
        return license;
    }

    public CarType getType() {
        return type;
    }

    public String getOwner() {
        return owner;
    }

    /* Returns true if the permit is still valid on the given date */
    public boolean isPermitValid(LocalDate date) {
        return !date.isAfter(permitExpiration);
    }

    @Override
    public String toString() {
        return "Car [license=" + license
                + ", type=" + type
                + ", permit=" + permit
                + ", permitExpiration=" + permitExpiration
                + ", owner=" + owner + "]";
    }
}

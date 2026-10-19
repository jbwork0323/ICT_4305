package assignment_6;

import java.time.Instant;

public class ParkingCharge {
    private String permitId;
    private String lotId;
    private Instant incurred;
    private Money amount;

    /* Creates a new ParkingCharge for the car with the given permit parking in the given lot */
    public ParkingCharge(String permitId, String lotId, Instant incurred, Money amount) {
        if (permitId == null || permitId.isBlank()) {
            throw new IllegalArgumentException("Permit id is required.");
        }
        if (lotId == null || lotId.isBlank()) {
            throw new IllegalArgumentException("Lot id is required.");
        }
        if (incurred == null) {
            throw new IllegalArgumentException("Incurred time is required.");
        }
        if (amount == null) {
            throw new IllegalArgumentException("Amount is required.");
        }

        this.permitId = permitId;
        this.lotId = lotId;
        this.incurred = incurred;
        this.amount = amount;
    }

    public String getPermitId() {
        return permitId;
    }

    public String getLotId() {
        return lotId;
    }

    public Instant getIncurred() {
        return incurred;
    }

    public Money getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "ParkingCharge [permitId=" + permitId
                + ", lotId=" + lotId
                + ", incurred=" + incurred
                + ", amount=" + amount + "]";
    }
}

package assignment_6;

import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParkingLot {
    /* Compact cars pay 80% of the daily rate */
    private static final double COMPACT_DISCOUNT = 0.8;

    private String lotId;
    private Address address;
    private int capacity;
    private Money dailyRate;
    /* Cars currently in the lot, keyed by permit, with the time each one entered */
    private Map<String, Instant> entryTimes;

    /* Creates a new, empty ParkingLot that charges dailyRate for each car that enters */
    public ParkingLot(String lotId, Address address, int capacity, Money dailyRate) {
        if (lotId == null || lotId.isBlank()) {
            throw new IllegalArgumentException("Lot id is required.");
        }
        if (address == null) {
            throw new IllegalArgumentException("Address is required.");
        }
        if (capacity < 1) {
            throw new IllegalArgumentException("Invalid capacity " + capacity + ". Capacity must be greater than 0.");
        }
        if (dailyRate == null) {
            throw new IllegalArgumentException("Daily rate is required.");
        }

        this.lotId = lotId;
        this.address = address;
        this.capacity = capacity;
        this.dailyRate = dailyRate;
        this.entryTimes = new LinkedHashMap<>();
    }

    public String getLotId() {
        return lotId;
    }

    public Address getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }

    public Money getDailyRate() {
        return dailyRate;
    }

    /* Returns the number of cars currently in the lot */
    public int getOccupancy() {
        return entryTimes.size();
    }

    /* Returns true if the lot has no open spaces */
    public boolean isFull() {
        return getOccupancy() >= capacity;
    }

    /* Returns what the given car pays to park here. Compact cars receive a discount. */
    public Money getRate(Car car) {
        return car.getType() == CarType.COMPACT ? dailyRate.times(COMPACT_DISCOUNT) : dailyRate;
    }

    /* Records a car entering the lot and returns the charge the car incurs. The car must
     * have an unexpired permit, must not already be in the lot, and the lot must have an open space.
     */
    public ParkingCharge entry(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car is required.");
        }
        if (!car.isPermitValid(LocalDate.now())) {
            throw new IllegalStateException("Permit " + car.getPermit() + " expired on " + car.getPermitExpiration() + ".");
        }
        if (entryTimes.containsKey(car.getPermit())) {
            throw new IllegalStateException("Car with permit " + car.getPermit() + " is already in lot " + lotId + ".");
        }
        if (isFull()) {
            throw new IllegalStateException("Lot " + lotId + " is full.");
        }

        Instant entered = Instant.now();
        entryTimes.put(car.getPermit(), entered);
        return new ParkingCharge(car.getPermit(), lotId, entered, getRate(car));
    }

    /* Returns the time the car entered the lot, or null if it is not in the lot */
    public Instant getEntryTime(Car car) {
        return entryTimes.get(car.getPermit());
    }

    @Override
    public String toString() {
        return "ParkingLot [lotId=" + lotId
                + ", address=" + address.getAddressInfo()
                + ", capacity=" + capacity
                + ", dailyRate=" + dailyRate
                + ", occupancy=" + getOccupancy() + "]";
    }
}

package assignment_4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    /* Permits are valid for one year from the date the car is registered */
    private static final int PERMIT_LENGTH_YEARS = 1;
    private static int nextPermitNumber = 1;

    private String customerId;
    private String name;
    private Address address;
    private String phoneNumber;
    private List<Car> cars;

    /* Creates a new Customer with no registered cars */
    public Customer(String customerId, String name, Address address, String phoneNumber) {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("Customer id is required.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (address == null) {
            throw new IllegalArgumentException("Address is required.");
        }

        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.cars = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /* Returns a copy of the cars this customer has registered */
    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }

    /* Registers a car to this customer, issuing a new permit that expires one year from today */
    public Car register(String license, CarType type) {
        String permit = generatePermit();
        LocalDate expiration = LocalDate.now().plusYears(PERMIT_LENGTH_YEARS);
        Car car = new Car(permit, expiration, license, type, customerId);
        cars.add(car);
        return car;
    }

    /* This internal method returns the next unique permit number, e.g. P0001 */
    private static String generatePermit() {
        return String.format("P%04d", nextPermitNumber++);
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId
                + ", name=" + name
                + ", address=" + address.getAddressInfo()
                + ", phoneNumber=" + phoneNumber
                + ", cars=" + cars.size() + "]";
    }
}

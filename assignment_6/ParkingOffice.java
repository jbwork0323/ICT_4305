package assignment_6;

import java.util.ArrayList;
import java.util.List;

public class ParkingOffice {
    private String name;
    private Address address;
    private List<Customer> customers;
    private List<Car> cars;
    private List<ParkingLot> lots;
    private List<ParkingCharge> charges;

    /* Creates a new ParkingOffice with no customers, cars, lots or charges */
    public ParkingOffice(String name, Address address) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (address == null) {
            throw new IllegalArgumentException("Address is required.");
        }

        this.name = name;
        this.address = address;
        this.customers = new ArrayList<>();
        this.cars = new ArrayList<>();
        this.lots = new ArrayList<>();
        this.charges = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    /* Returns a copy of the customers registered with this office */
    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }

    /* Returns a copy of the cars registered with this office */
    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }

    /* Returns a copy of the lots this office manages */
    public List<ParkingLot> getLots() {
        return new ArrayList<>(lots);
    }

    /* Returns a copy of every charge this office has recorded */
    public List<ParkingCharge> getCharges() {
        return new ArrayList<>(charges);
    }

    /* Registers a new customer and assigns them the next customer id, e.g. C001 */
    public Customer register(String name, Address address, String phone) {
        String customerId = String.format("C%03d", customers.size() + 1);
        Customer customer = new Customer(customerId, name, address, phone);
        customers.add(customer);
        return customer;
    }

    /* Registers a car for one of this office's customers. The returned car holds its new parking permit. */
    public Car register(Customer c, String license, CarType t) {
        if (c == null || !customers.contains(c)) {
            throw new IllegalArgumentException("Customer is not registered with " + name + ".");
        }

        Car car = c.register(license, t);
        cars.add(car);
        return car;
    }

    /* Returns the customer with the given name, or null if there is none */
    public Customer getCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    /* Adds a lot for this office to manage */
    public void addLot(ParkingLot lot) {
        if (lot == null) {
            throw new IllegalArgumentException("Lot is required.");
        }

        lots.add(lot);
    }

    /* Records a charge against a registered permit and returns the amount charged */
    public Money addCharge(ParkingCharge charge) {
        if (charge == null) {
            throw new IllegalArgumentException("Charge is required.");
        }
        if (getCar(charge.getPermitId()) == null) {
            throw new IllegalArgumentException("Permit " + charge.getPermitId() + " is not registered with " + name + ".");
        }

        charges.add(charge);
        return charge.getAmount();
    }

    /* Lets a car into one of this office's lots and records the charge it incurs */
    public Money park(Car car, ParkingLot lot) {
        if (lot == null || !lots.contains(lot)) {
            throw new IllegalArgumentException("Lot is not managed by " + name + ".");
        }

        ParkingCharge charge = lot.entry(car);
        return addCharge(charge);
    }

    /* This internal method returns the registered car with the given permit, or null if there is none */
    private Car getCar(String permit) {
        for (Car car : cars) {
            if (car.getPermit().equals(permit)) {
                return car;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ParkingOffice [name=" + name
                + ", address=" + address.getAddressInfo()
                + ", customers=" + customers.size()
                + ", cars=" + cars.size()
                + ", lots=" + lots.size()
                + ", charges=" + charges.size() + "]";
    }
}

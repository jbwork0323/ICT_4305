package assignment_6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkingOfficeTest {
    private Address officeAddress;
    private Address customerAddress;
    private ParkingOffice office;
    private ParkingLot lot;

    @BeforeEach
    void setUp() {
        officeAddress = new Address("2199 S University Blvd", null, "Denver", "CO", "80208");
        customerAddress = new Address("123 Main St", "Apt 4", "Denver", "CO", "80202");
        office = new ParkingOffice("DU Parking Office", officeAddress);
        lot = new ParkingLot("LOT-A", officeAddress, 10, new Money(1000));
        office.addLot(lot);
    }

    @Test
    void storesNameAndAddress() {
        assertEquals("DU Parking Office", office.getName());
        assertEquals(officeAddress, office.getAddress());
    }

    @Test
    void startsWithNoCustomersCarsOrCharges() {
        assertTrue(office.getCustomers().isEmpty());
        assertTrue(office.getCars().isEmpty());
        assertTrue(office.getCharges().isEmpty());
    }

    @Test
    void registerCustomerAddsCustomer() {
        Customer customer = office.register("Joey Work", customerAddress, "303-555-0100");

        assertEquals("Joey Work", customer.getName());
        assertEquals(customerAddress, customer.getAddress());
        assertEquals("303-555-0100", customer.getPhoneNumber());
        assertEquals(List.of(customer), office.getCustomers());
    }

    @Test
    void registerCustomerAssignsSequentialIds() {
        Customer first = office.register("Joey Work", customerAddress, "303-555-0100");
        Customer second = office.register("Mike Prasad", customerAddress, "303-555-0101");

        assertEquals("C001", first.getCustomerId());
        assertEquals("C002", second.getCustomerId());
    }

    @Test
    void registerCarIssuesPermitToCustomer() {
        Customer customer = office.register("Joey Work", customerAddress, "303-555-0100");

        Car car = office.register(customer, "ABC-123", CarType.SUV);

        assertEquals("ABC-123", car.getLicense());
        assertEquals(CarType.SUV, car.getType());
        assertEquals(customer.getCustomerId(), car.getOwner());
        assertTrue(car.getPermit().startsWith("P"));
        assertEquals(List.of(car), office.getCars());
        assertEquals(List.of(car), customer.getCars());
    }

    @Test
    void registerCarRefusedForUnknownCustomer() {
        Customer stranger = new Customer("C999", "Stranger", customerAddress, "303-555-0199");

        assertThrows(IllegalArgumentException.class,
                () -> office.register(stranger, "ABC-123", CarType.SUV));
    }

    @Test
    void getCustomerFindsByName() {
        Customer customer = office.register("Joey Work", customerAddress, "303-555-0100");

        assertSame(customer, office.getCustomer("Joey Work"));
    }

    @Test
    void getCustomerReturnsNullWhenNotFound() {
        assertNull(office.getCustomer("Nobody"));
    }

    @Test
    void addChargeRecordsChargeAndReturnsAmount() {
        Customer customer = office.register("Joey Work", customerAddress, "303-555-0100");
        Car car = office.register(customer, "ABC-123", CarType.SUV);
        ParkingCharge charge = new ParkingCharge(car.getPermit(), "LOT-A", Instant.now(), new Money(1000));

        Money amount = office.addCharge(charge);

        assertEquals(new Money(1000), amount);
        assertEquals(List.of(charge), office.getCharges());
    }

    @Test
    void addChargeRefusedForUnknownPermit() {
        ParkingCharge charge = new ParkingCharge("P9999", "LOT-A", Instant.now(), new Money(1000));

        assertThrows(IllegalArgumentException.class, () -> office.addCharge(charge));
        assertTrue(office.getCharges().isEmpty());
    }

    @Test
    void parkEntersLotAndChargesCustomer() {
        Customer customer = office.register("Joey Work", customerAddress, "303-555-0100");
        Car car = office.register(customer, "ABC-123", CarType.COMPACT);

        Money amount = office.park(car, lot);

        assertEquals(new Money(800), amount);
        assertEquals(1, lot.getOccupancy());
        assertEquals(1, office.getCharges().size());
        assertEquals(car.getPermit(), office.getCharges().get(0).getPermitId());
    }

    @Test
    void parkRefusedForLotNotManagedByOffice() {
        Customer customer = office.register("Joey Work", customerAddress, "303-555-0100");
        Car car = office.register(customer, "ABC-123", CarType.COMPACT);
        ParkingLot otherLot = new ParkingLot("LOT-Z", officeAddress, 10, new Money(1000));

        assertThrows(IllegalArgumentException.class, () -> office.park(car, otherLot));
        assertEquals(0, otherLot.getOccupancy());
    }

    @Test
    void getListsReturnCopies() {
        office.register("Joey Work", customerAddress, "303-555-0100");

        office.getCustomers().clear();
        office.getLots().clear();

        assertEquals(1, office.getCustomers().size());
        assertEquals(1, office.getLots().size());
    }

    @Test
    void requiresName() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingOffice(" ", officeAddress));
    }

    @Test
    void requiresAddress() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingOffice("DU Parking Office", null));
    }
}

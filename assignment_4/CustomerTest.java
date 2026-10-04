package assignment_4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {
    private Address address;
    private Customer customer;

    @BeforeEach
    void setUp() {
        address = new Address("123 Main St", "Apt 4", "Denver", "CO", "80202");
        customer = new Customer("C001", "Joey Work", address, "303-555-0100");
    }

    @Test
    void storesAllFields() {
        assertEquals("C001", customer.getCustomerId());
        assertEquals("Joey Work", customer.getName());
        assertEquals(address, customer.getAddress());
        assertEquals("303-555-0100", customer.getPhoneNumber());
    }

    @Test
    void startsWithNoCars() {
        assertTrue(customer.getCars().isEmpty());
    }

    @Test
    void registerCreatesCarOwnedByCustomer() {
        Car car = customer.register("ABC-123", CarType.SUV);

        assertEquals("ABC-123", car.getLicense());
        assertEquals(CarType.SUV, car.getType());
        assertEquals("C001", car.getOwner());
    }

    @Test
    void registerIssuesPermitExpiringInOneYear() {
        Car car = customer.register("ABC-123", CarType.COMPACT);

        assertEquals(LocalDate.now().plusYears(1), car.getPermitExpiration());
    }

    @Test
    void registerAddsCarToCustomer() {
        Car car = customer.register("ABC-123", CarType.COMPACT);

        assertEquals(List.of(car), customer.getCars());
    }

    @Test
    void registerIssuesUniquePermits() {
        Car first = customer.register("ABC-123", CarType.COMPACT);
        Car second = customer.register("XYZ-789", CarType.SUV);

        assertNotEquals(first.getPermit(), second.getPermit());
    }

    @Test
    void getCarsReturnsCopy() {
        customer.register("ABC-123", CarType.COMPACT);

        customer.getCars().clear();

        assertEquals(1, customer.getCars().size());
    }

    @Test
    void requiresCustomerId() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("", "Joey Work", address, "303-555-0100"));
    }

    @Test
    void requiresName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("C001", null, address, "303-555-0100"));
    }

    @Test
    void requiresAddress() {
        assertThrows(IllegalArgumentException.class,
                () -> new Customer("C001", "Joey Work", null, "303-555-0100"));
    }
}

package assignment_4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AddressTest {

    @Test
    void storesAllFields() {
        Address address = new Address("123 Main St", "Apt 4", "Denver", "CO", "80202");

        assertEquals("123 Main St", address.getStreetAddress1());
        assertEquals("Apt 4", address.getStreetAddress2());
        assertEquals("Denver", address.getCity());
        assertEquals("CO", address.getState());
        assertEquals("80202", address.getZipCode());
    }

    @Test
    void nullStreetAddress2BecomesEmpty() {
        Address address = new Address("123 Main St", null, "Denver", "CO", "80202");

        assertEquals("", address.getStreetAddress2());
    }

    @Test
    void getAddressInfoIncludesStreetAddress2WhenPresent() {
        Address address = new Address("123 Main St", "Apt 4", "Denver", "CO", "80202");

        assertEquals("123 Main St, Apt 4, Denver, CO 80202", address.getAddressInfo());
    }

    @Test
    void getAddressInfoOmitsStreetAddress2WhenBlank() {
        Address address = new Address("123 Main St", "", "Denver", "CO", "80202");

        assertEquals("123 Main St, Denver, CO 80202", address.getAddressInfo());
    }

    @Test
    void requiresStreetAddress1() {
        assertThrows(IllegalArgumentException.class,
                () -> new Address(" ", null, "Denver", "CO", "80202"));
    }

    @Test
    void requiresCity() {
        assertThrows(IllegalArgumentException.class,
                () -> new Address("123 Main St", null, null, "CO", "80202"));
    }

    @Test
    void requiresState() {
        assertThrows(IllegalArgumentException.class,
                () -> new Address("123 Main St", null, "Denver", "", "80202"));
    }

    @Test
    void requiresZipCode() {
        assertThrows(IllegalArgumentException.class,
                () -> new Address("123 Main St", null, "Denver", "CO", null));
    }
}

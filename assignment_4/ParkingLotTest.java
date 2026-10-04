package assignment_4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkingLotTest {
    private Address address;
    private Car compact;
    private Car suv;

    @BeforeEach
    void setUp() {
        address = new Address("2199 S University Blvd", null, "Denver", "CO", "80208");
        LocalDate nextYear = LocalDate.now().plusYears(1);
        compact = new Car("P0001", nextYear, "ABC-123", CarType.COMPACT, "C001");
        suv = new Car("P0002", nextYear, "XYZ-789", CarType.SUV, "C001");
    }

    @Test
    void storesAllFields() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 10);

        assertEquals("LOT-A", lot.getLotId());
        assertEquals(address, lot.getAddress());
        assertEquals(10, lot.getCapacity());
    }

    @Test
    void startsEmpty() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 10);

        assertEquals(0, lot.getOccupancy());
        assertFalse(lot.isFull());
    }

    @Test
    void entryRecordsCarAndTime() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 10);

        lot.entry(compact);

        assertEquals(1, lot.getOccupancy());
        assertNotNull(lot.getEntryTime(compact));
    }

    @Test
    void getEntryTimeIsNullForCarNotInLot() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 10);

        assertNull(lot.getEntryTime(compact));
    }

    @Test
    void isFullWhenOccupancyReachesCapacity() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 1);

        lot.entry(compact);

        assertTrue(lot.isFull());
    }

    @Test
    void entryRefusedWhenLotIsFull() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 1);
        lot.entry(compact);

        assertThrows(IllegalStateException.class, () -> lot.entry(suv));
        assertEquals(1, lot.getOccupancy());
    }

    @Test
    void entryRefusedWhenCarAlreadyInLot() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 10);
        lot.entry(compact);

        assertThrows(IllegalStateException.class, () -> lot.entry(compact));
        assertEquals(1, lot.getOccupancy());
    }

    @Test
    void entryRefusedWhenPermitExpired() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 10);
        Car expired = new Car("P0003", LocalDate.now().minusDays(1), "OLD-001", CarType.COMPACT, "C001");

        assertThrows(IllegalStateException.class, () -> lot.entry(expired));
        assertEquals(0, lot.getOccupancy());
    }

    @Test
    void entryRequiresCar() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 10);

        assertThrows(IllegalArgumentException.class, () -> lot.entry(null));
    }

    @Test
    void requiresLotId() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot("", address, 10));
    }

    @Test
    void requiresAddress() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot("LOT-A", null, 10));
    }

    @Test
    void requiresPositiveCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot("LOT-A", address, 0));
    }
}

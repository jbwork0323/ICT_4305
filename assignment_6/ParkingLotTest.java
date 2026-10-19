package assignment_6;

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
    private static final Money RATE = new Money(1000);

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
        ParkingLot lot = new ParkingLot("LOT-A", address, 10, RATE);

        assertEquals("LOT-A", lot.getLotId());
        assertEquals(address, lot.getAddress());
        assertEquals(10, lot.getCapacity());
        assertEquals(RATE, lot.getDailyRate());
    }

    @Test
    void startsEmpty() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 10, RATE);

        assertEquals(0, lot.getOccupancy());
        assertFalse(lot.isFull());
    }

    @Test
    void entryRecordsCarAndTime() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 10, RATE);

        lot.entry(compact);

        assertEquals(1, lot.getOccupancy());
        assertNotNull(lot.getEntryTime(compact));
    }

    @Test
    void entryReturnsChargeForCarAndLot() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 10, RATE);

        ParkingCharge charge = lot.entry(suv);

        assertEquals("P0002", charge.getPermitId());
        assertEquals("LOT-A", charge.getLotId());
        assertEquals(lot.getEntryTime(suv), charge.getIncurred());
    }

    @Test
    void suvPaysFullDailyRate() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 10, RATE);

        assertEquals(new Money(1000), lot.entry(suv).getAmount());
    }

    @Test
    void compactPaysDiscountedRate() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 10, RATE);

        assertEquals(new Money(800), lot.entry(compact).getAmount());
    }

    @Test
    void getEntryTimeIsNullForCarNotInLot() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 10, RATE);

        assertNull(lot.getEntryTime(compact));
    }

    @Test
    void isFullWhenOccupancyReachesCapacity() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 1, RATE);

        lot.entry(compact);

        assertTrue(lot.isFull());
    }

    @Test
    void entryRefusedWhenLotIsFull() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 1, RATE);
        lot.entry(compact);

        assertThrows(IllegalStateException.class, () -> lot.entry(suv));
        assertEquals(1, lot.getOccupancy());
    }

    @Test
    void entryRefusedWhenCarAlreadyInLot() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 10, RATE);
        lot.entry(compact);

        assertThrows(IllegalStateException.class, () -> lot.entry(compact));
        assertEquals(1, lot.getOccupancy());
    }

    @Test
    void entryRefusedWhenPermitExpired() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 10, RATE);
        Car expired = new Car("P0003", LocalDate.now().minusDays(1), "OLD-001", CarType.COMPACT, "C001");

        assertThrows(IllegalStateException.class, () -> lot.entry(expired));
        assertEquals(0, lot.getOccupancy());
    }

    @Test
    void entryRequiresCar() {
        ParkingLot lot = new ParkingLot("LOT-A", address, 10, RATE);

        assertThrows(IllegalArgumentException.class, () -> lot.entry(null));
    }

    @Test
    void requiresLotId() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot("", address, 10, RATE));
    }

    @Test
    void requiresAddress() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot("LOT-A", null, 10, RATE));
    }

    @Test
    void requiresPositiveCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot("LOT-A", address, 0, RATE));
    }

    @Test
    void requiresDailyRate() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot("LOT-A", address, 10, null));
    }
}

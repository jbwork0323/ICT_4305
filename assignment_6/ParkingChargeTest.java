package assignment_6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;

import org.junit.jupiter.api.Test;

class ParkingChargeTest {
    private static final Instant INCURRED = Instant.parse("2026-10-18T15:30:00Z");
    private static final Money AMOUNT = new Money(1000);

    @Test
    void storesAllFields() {
        ParkingCharge charge = new ParkingCharge("P0001", "LOT-A", INCURRED, AMOUNT);

        assertEquals("P0001", charge.getPermitId());
        assertEquals("LOT-A", charge.getLotId());
        assertEquals(INCURRED, charge.getIncurred());
        assertEquals(AMOUNT, charge.getAmount());
    }

    @Test
    void toStringIncludesAllFields() {
        ParkingCharge charge = new ParkingCharge("P0001", "LOT-A", INCURRED, AMOUNT);

        assertEquals("ParkingCharge [permitId=P0001, lotId=LOT-A, incurred=2026-10-18T15:30:00Z, amount=$10.00]",
                charge.toString());
    }

    @Test
    void requiresPermitId() {
        assertThrows(IllegalArgumentException.class,
                () -> new ParkingCharge(" ", "LOT-A", INCURRED, AMOUNT));
    }

    @Test
    void requiresLotId() {
        assertThrows(IllegalArgumentException.class,
                () -> new ParkingCharge("P0001", null, INCURRED, AMOUNT));
    }

    @Test
    void requiresIncurred() {
        assertThrows(IllegalArgumentException.class,
                () -> new ParkingCharge("P0001", "LOT-A", null, AMOUNT));
    }

    @Test
    void requiresAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new ParkingCharge("P0001", "LOT-A", INCURRED, null));
    }
}

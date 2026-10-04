package assignment_4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class CarTest {
    private static final LocalDate EXPIRATION = LocalDate.of(2026, 12, 31);

    @Test
    void storesAllFields() {
        Car car = new Car("P0001", EXPIRATION, "ABC-123", CarType.COMPACT, "C001");

        assertEquals("P0001", car.getPermit());
        assertEquals(EXPIRATION, car.getPermitExpiration());
        assertEquals("ABC-123", car.getLicense());
        assertEquals(CarType.COMPACT, car.getType());
        assertEquals("C001", car.getOwner());
    }

    @Test
    void permitIsValidBeforeExpiration() {
        Car car = new Car("P0001", EXPIRATION, "ABC-123", CarType.COMPACT, "C001");

        assertTrue(car.isPermitValid(EXPIRATION.minusDays(1)));
    }

    @Test
    void permitIsValidOnExpirationDate() {
        Car car = new Car("P0001", EXPIRATION, "ABC-123", CarType.COMPACT, "C001");

        assertTrue(car.isPermitValid(EXPIRATION));
    }

    @Test
    void permitIsInvalidAfterExpiration() {
        Car car = new Car("P0001", EXPIRATION, "ABC-123", CarType.COMPACT, "C001");

        assertFalse(car.isPermitValid(EXPIRATION.plusDays(1)));
    }

    @Test
    void requiresPermit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Car("", EXPIRATION, "ABC-123", CarType.COMPACT, "C001"));
    }

    @Test
    void requiresPermitExpiration() {
        assertThrows(IllegalArgumentException.class,
                () -> new Car("P0001", null, "ABC-123", CarType.COMPACT, "C001"));
    }

    @Test
    void requiresLicense() {
        assertThrows(IllegalArgumentException.class,
                () -> new Car("P0001", EXPIRATION, null, CarType.COMPACT, "C001"));
    }

    @Test
    void requiresType() {
        assertThrows(IllegalArgumentException.class,
                () -> new Car("P0001", EXPIRATION, "ABC-123", null, "C001"));
    }

    @Test
    void requiresOwner() {
        assertThrows(IllegalArgumentException.class,
                () -> new Car("P0001", EXPIRATION, "ABC-123", CarType.COMPACT, " "));
    }
}

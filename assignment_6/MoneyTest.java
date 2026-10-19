package assignment_6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    void storesCents() {
        Money money = new Money(1250);

        assertEquals(1250, money.getCents());
    }

    @Test
    void getDollarsConvertsCents() {
        Money money = new Money(1250);

        assertEquals(12.5, money.getDollars());
    }

    @Test
    void toStringFormatsAsDollars() {
        assertEquals("$12.50", new Money(1250).toString());
        assertEquals("$0.05", new Money(5).toString());
        assertEquals("$0.00", new Money(0).toString());
    }

    @Test
    void timesRoundsToNearestCent() {
        Money money = new Money(999);

        assertEquals(new Money(799), money.times(0.8));
    }

    @Test
    void equalAmountsAreEqual() {
        assertEquals(new Money(500), new Money(500));
        assertEquals(new Money(500).hashCode(), new Money(500).hashCode());
        assertNotEquals(new Money(500), new Money(501));
    }

    @Test
    void cannotBeNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Money(-1));
    }
}

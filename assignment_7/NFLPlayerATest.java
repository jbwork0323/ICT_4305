package assignment_7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class NFLPlayerATest {

    @Test
    void getNameAIsInheritedFromPlayerA() {
        PlayerA player = new NFLPlayerA("Patrick Mahomes", 41);

        assertEquals("Patrick Mahomes", player.getNameA());
    }

    @Test
    void storesStats() {
        assertEquals(41, new NFLPlayerA("Patrick Mahomes", 41).getStatsA());
    }

    @Test
    void sportIsNFL() {
        assertEquals("NFL", new NFLPlayerA("Patrick Mahomes", 41).getSportA());
    }

    @Test
    void zeroTouchdownsIsAllowed() {
        assertEquals(0, new NFLPlayerA("Rookie", 0).getStatsA());
    }

    @Test
    void nullNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new NFLPlayerA(null, 41));
    }

    @Test
    void blankNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new NFLPlayerA("   ", 41));
    }

    @Test
    void negativeTouchdownsThrows() {
        assertThrows(IllegalArgumentException.class, () -> new NFLPlayerA("Patrick Mahomes", -1));
    }
}

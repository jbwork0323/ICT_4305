package assignment_7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class NBAPlayerATest {

    @Test
    void getNameAIsInheritedFromPlayerA() {
        PlayerA player = new NBAPlayerA("LeBron James", 1708);

        assertEquals("LeBron James", player.getNameA());
    }

    @Test
    void storesStats() {
        assertEquals(1708, new NBAPlayerA("LeBron James", 1708).getStatsA());
    }

    @Test
    void sportIsNBA() {
        assertEquals("NBA", new NBAPlayerA("LeBron James", 1708).getSportA());
    }

    @Test
    void zeroPointsIsAllowed() {
        assertEquals(0, new NBAPlayerA("Rookie", 0).getStatsA());
    }

    @Test
    void nullNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new NBAPlayerA(null, 1708));
    }

    @Test
    void blankNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new NBAPlayerA("   ", 1708));
    }

    @Test
    void negativePointsThrows() {
        assertThrows(IllegalArgumentException.class, () -> new NBAPlayerA("LeBron James", -1));
    }
}

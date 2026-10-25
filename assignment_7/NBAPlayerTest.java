package assignment_7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class NBAPlayerTest {

    @Test
    void storesNameAndStats() {
        NBAPlayer player = new NBAPlayer("LeBron James", 1708);

        assertEquals("LeBron James", player.getName());
        assertEquals(1708, player.getStats());
    }

    @Test
    void sportIsNBA() {
        assertEquals("NBA", new NBAPlayer("LeBron James", 1708).getSport());
    }

    @Test
    void zeroPointsIsAllowed() {
        assertEquals(0, new NBAPlayer("Rookie", 0).getStats());
    }

    @Test
    void nullNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new NBAPlayer(null, 1708));
    }

    @Test
    void blankNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new NBAPlayer("   ", 1708));
    }

    @Test
    void negativePointsThrows() {
        assertThrows(IllegalArgumentException.class, () -> new NBAPlayer("LeBron James", -1));
    }

    @Test
    void worksThroughPlayerInterface() {
        Player player = new NBAPlayer("LeBron James", 1708);

        assertEquals("LeBron James", player.getName());
        assertEquals(1708, player.getStats());
        assertEquals("NBA", player.getSport());
    }
}

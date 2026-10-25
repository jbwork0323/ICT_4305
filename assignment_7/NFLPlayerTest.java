package assignment_7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class NFLPlayerTest {

    @Test
    void storesNameAndStats() {
        NFLPlayer player = new NFLPlayer("Patrick Mahomes", 41);

        assertEquals("Patrick Mahomes", player.getName());
        assertEquals(41, player.getStats());
    }

    @Test
    void sportIsNFL() {
        assertEquals("NFL", new NFLPlayer("Patrick Mahomes", 41).getSport());
    }

    @Test
    void zeroTouchdownsIsAllowed() {
        assertEquals(0, new NFLPlayer("Rookie", 0).getStats());
    }

    @Test
    void nullNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new NFLPlayer(null, 41));
    }

    @Test
    void blankNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new NFLPlayer("   ", 41));
    }

    @Test
    void negativeTouchdownsThrows() {
        assertThrows(IllegalArgumentException.class, () -> new NFLPlayer("Patrick Mahomes", -1));
    }

    @Test
    void worksThroughPlayerInterface() {
        Player player = new NFLPlayer("Patrick Mahomes", 41);

        assertEquals("Patrick Mahomes", player.getName());
        assertEquals(41, player.getStats());
        assertEquals("NFL", player.getSport());
    }
}

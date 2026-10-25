package assignment_7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

/* Checks that a mixed portfolio of players behaves polymorphically for both designs. */
class PlayerPortfolioTest {

    @Test
    void interfacePortfolioReportsEachPlayersOwnSportAndStats() {
        List<Player> portfolio = List.of(new NFLPlayer("Patrick Mahomes", 41), new NBAPlayer("LeBron James", 1708));

        assertEquals("NFL", portfolio.get(0).getSport());
        assertEquals(41, portfolio.get(0).getStats());
        assertEquals("NBA", portfolio.get(1).getSport());
        assertEquals(1708, portfolio.get(1).getStats());
    }

    @Test
    void abstractPortfolioReportsEachPlayersOwnSportAndStats() {
        List<PlayerA> portfolio = List.of(new NFLPlayerA("Patrick Mahomes", 41), new NBAPlayerA("LeBron James", 1708));

        assertEquals("Patrick Mahomes", portfolio.get(0).getNameA());
        assertEquals("NFL", portfolio.get(0).getSportA());
        assertEquals(41, portfolio.get(0).getStatsA());
        assertEquals("LeBron James", portfolio.get(1).getNameA());
        assertEquals("NBA", portfolio.get(1).getSportA());
        assertEquals(1708, portfolio.get(1).getStatsA());
    }
}

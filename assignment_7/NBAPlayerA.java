package assignment_7;

public class NBAPlayerA extends PlayerA {

    /* Creates an NBA player. Stats for an NBA player are total points. */
    public NBAPlayerA(String name, int points) {
        super(name, points, "NBA");
    }

    @Override
    int getStatsA() {
        return stats;
    }

    @Override
    String getSportA() {
        return sport;
    }
}

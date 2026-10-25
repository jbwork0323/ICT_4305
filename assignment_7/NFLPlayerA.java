package assignment_7;

public class NFLPlayerA extends PlayerA {

    /* Creates an NFL player. Stats for an NFL player are total touchdowns. */
    public NFLPlayerA(String name, int touchdowns) {
        super(name, touchdowns, "NFL");
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

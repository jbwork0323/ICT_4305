package assignment_7;

public class NFLPlayer implements Player {
    private static final String SPORT = "NFL";

    private String name;
    private int touchdowns;

    /* Creates an NFL player. Stats for an NFL player are total touchdowns. */
    public NFLPlayer(String name, int touchdowns) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be blank.");
        }
        if (touchdowns < 0) {
            throw new IllegalArgumentException("Invalid touchdowns " + touchdowns + ". Touchdowns cannot be negative.");
        }

        this.name = name;
        this.touchdowns = touchdowns;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStats() {
        return touchdowns;
    }

    @Override
    public String getSport() {
        return SPORT;
    }
}

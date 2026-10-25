package assignment_7;

public class NBAPlayer implements Player {
    private static final String SPORT = "NBA";

    private String name;
    private int points;

    /* Creates an NBA player. Stats for an NBA player are total points. */
    public NBAPlayer(String name, int points) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be blank.");
        }
        if (points < 0) {
            throw new IllegalArgumentException("Invalid points " + points + ". Points cannot be negative.");
        }

        this.name = name;
        this.points = points;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStats() {
        return points;
    }

    @Override
    public String getSport() {
        return SPORT;
    }
}

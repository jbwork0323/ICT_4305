package assignment_7;

/* Base class for players. Holds the shared state and validation; each sport supplies its own stats and sport. */
abstract class PlayerA {
    protected String name;
    protected int stats;
    protected String sport;

    protected PlayerA(String name, int stats, String sport) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be blank.");
        }
        if (stats < 0) {
            throw new IllegalArgumentException("Invalid stats " + stats + ". Stats cannot be negative.");
        }

        this.name = name;
        this.stats = stats;
        this.sport = sport;
    }

    public String getNameA() {
        return name;
    }

    abstract int getStatsA();

    abstract String getSportA();
}

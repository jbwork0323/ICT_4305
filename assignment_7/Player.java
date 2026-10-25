package assignment_7;

/* Common contract for any player in the portfolio. Each sport decides what its stats mean. */
public interface Player {
    String getName();

    int getStats();

    String getSport();
}

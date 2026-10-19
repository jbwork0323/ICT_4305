package assignment_6;

public class Money {
    private long cents;

    /* Creates a new amount of Money. Amounts are stored in whole cents to avoid rounding errors. */
    public Money(long cents) {
        if (cents < 0) {
            throw new IllegalArgumentException("Invalid amount " + cents + ". Money cannot be negative.");
        }

        this.cents = cents;
    }

    public long getCents() {
        return cents;
    }

    /* Returns the amount in dollars, e.g. 1250 cents is 12.5 dollars */
    public double getDollars() {
        return cents / 100.0;
    }

    /* Returns a new Money that is this amount multiplied by the factor, rounded to the nearest cent */
    public Money times(double factor) {
        return new Money(Math.round(cents * factor));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Money)) {
            return false;
        }
        return cents == ((Money) other).cents;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(cents);
    }

    /* Returns the amount formatted as dollars, e.g. $12.50 */
    @Override
    public String toString() {
        return String.format("$%d.%02d", cents / 100, cents % 100);
    }
}

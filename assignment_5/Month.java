package assignment_5;

/* Generic wrapper that holds a single month value. T is chosen at runtime, e.g. Integer for the month index or String for the month name. */
public class Month<T> {
    private T month;

    // getter and setter
    public void setMonth(T month) {
        this.month = month;
    }

    public T getMonth() {
        return month;
    }

    // constructors
    public Month() { }

    public Month(T month) {
        this.month = month;
    }
}

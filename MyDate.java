 
public class MyDate {
    private int day;
    private int month;
    private int year;
    private int julianNumber;

    /* If no arguments were provided then default the date January 1st, 1970 (epoch time). */
    public MyDate() {
        this(1, 1, 1970);
    }

    /* Creates a new MyDate from a day, month, and year */
    public MyDate(int day, int month, int year) {
        if (year < 1) {
            throw new IllegalArgumentException("Invalid year " + year + ". Year must be greater than 0.");
        }
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month " + month + ". Month must be between 1 and 12.");
        }
        int lastDay = getLastDayOfMonth(month, year);
        if (day < 1 || day > lastDay) {
            throw new IllegalArgumentException("Invalid day " + day + " for month " + month + " and year " + year + ".");
        }

        this.day = day;
        this.month = month;
        this.year = year;
        this.julianNumber = toJulianNumber(day, month, year);
    }

    /* Creates a new MyDate from an existing MyDate */
    public MyDate(MyDate date) {
        this(date.getDay(), date.getMonth(), date.getYear());
    }

    /* Returns the day of the month for this MyDate */
    public int getDay() {
        return day;
    }

    /* Returns the month of the year for this MyDate */
    public int getMonth() {
        return month;
    }

    /* Returns the year for this MyDate */
    public int getYear() {
        return year;
    }

    /* Returns true if this MyDate represents a date in a leap year */
    public static boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    public static int getLastDayOfMonth(int month, int year) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                throw new IllegalArgumentException("Month must be between 1 and 12.");
        }
    }

    /* This internal method returns the calculated Julian number for the provided day, month, year
     * This method is static, as it does not require a MyDate object to perform its computation
     */
    private static int toJulianNumber(int day, int month, int year) {
        int totalDays = 0;

        for (int y = 1; y < year; y++) {
            totalDays += isLeapYear(y) ? 366 : 365;
        }

        for (int m = 1; m < month; m++) {
            totalDays += getLastDayOfMonth(m, year);
        }

        totalDays += day;
        return totalDays;
    }

    /* This internal method returns a 3-integer array
    * containing the day, month, and year of this MyDate
     */
    private int[] fromJulianNumber() {
        return new int[] {day, month, year};
    }
}  
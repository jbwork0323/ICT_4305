package assignment_5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthApp {
    private static final String[] MONTH_NAMES = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };

    @SuppressWarnings({"unchecked", "rawtypes"}) // generic array creation, as noted in the assignment
    public static void main(String[] args) {
        // Array: one array of each Month type
        Month<Integer>[] monthNumberArray = new Month[12];
        Month<String>[] monthNameArray = new Month[12];

        // ArrayList: one list of each Month type
        List<Month<Integer>> monthNumberList = new ArrayList<>();
        List<Month<String>> monthNameList = new ArrayList<>();

        // HashMap: month index object as key, month name object as value
        Map<Month<Integer>, Month<String>> monthMap = new HashMap<>();

        /* Create the 24 Month instances (12 Integer, 12 String) and add the same objects to every collection. */
        for (int i = 0; i < MONTH_NAMES.length; i++) {
            Month<Integer> monthNumber = new Month<>();
            Month<String> monthName = new Month<>();
            monthNumber.setMonth(i + 1);
            monthName.setMonth(MONTH_NAMES[i]);

            monthNumberArray[i] = monthNumber;
            monthNameArray[i] = monthName;

            monthNumberList.add(monthNumber);
            monthNameList.add(monthName);

            monthMap.put(monthNumber, monthName);
        }

        System.out.println("Array:");
        for (int i = 0; i < monthNumberArray.length; i++) {
            System.out.println(monthNumberArray[i].getMonth() + " = " + monthNameArray[i].getMonth());
        }

        System.out.println();
        System.out.println("ArrayList:");
        for (int i = 0; i < monthNumberList.size(); i++) {
            System.out.println(monthNumberList.get(i).getMonth() + " = " + monthNameList.get(i).getMonth());
        }

        /* HashMap iteration order is not guaranteed, so walk the keys in list order. Month does not override equals/hashCode,
           so lookups work because we reuse the exact same key objects that were put into the map. */
        System.out.println();
        System.out.println("HashMap:");
        for (Month<Integer> key : monthNumberList) {
            System.out.println(key.getMonth() + " = " + monthMap.get(key).getMonth());
        }
    }
}

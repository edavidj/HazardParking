/**
 *  @author- Steven Luu
 *  This class calls binary search to locate a match in the Entry array, it then finds all other entries that have the same match
 *  returning a subarray of a specified filter
 *  Possible inputs for filter parameter:
 *  day- "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"
 *  violationCode- "Pxxx" where x is a natural number (include leading 0's if the code is less than 100)
 *  date- Any whole number from 1-31
 *  hour- Any whole number from 0-23 where 0 is midnight (12AM)
 */
package hazardparking;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Filter {
    private static int top;
    private static int bottom;
    private static boolean floor = false;
    private static boolean ceiling = false;

    /**
     * Filter by day of the week
     * Finds the lowest and highest index with the same filter criteria and returns a subarray
     * (from the dataset) of all entries that fulfill the filter criteria
     *
     * @param data   dataset
     * @param filter day of the week(e.g. MONDAY, TUESDAY, etc.)
     * @return array of entries that are recorded on that day
     */
    public static Entry[] day(Entry[] data, String filter) {
        floor = false;
        ceiling = false;
        Sort.sort(data, 4);
        top = BinarySearch.search(data, 0, 132850, filter.toUpperCase(), 1);
        bottom = top;
        if (top == -1)
            return data;
        while (!ceiling || !floor) {
            if (!ceiling) {
                if (top == 132850)
                    ceiling = true;
                else if (!data[top + 1].getDay().equals(filter))
                    ceiling = true;
                else
                    top++;
            }
            if (!floor) {
                if (bottom == 0)
                    floor = true;
                else if (!data[bottom - 1].getDay().equals(filter))
                    floor = true;
                else
                    bottom--;
            }
        }
        return Arrays.copyOfRange(data, bottom, top);
    }

    /**
     * Filter by violation code
     * Finds the lowest and highest index with the same filter criteria and returns a subarray
     * (from the dataset) of all entries that fulfill the filter criteria
     *
     * @param data   dataset
     * @param filter violation code (e.g. "P003")
     * @return array of entries that are recorded with the violation code specified
     */
    public static Entry[] violationCode(Entry[] data, String filter) {
        floor = false;
        ceiling = false;
        Sort.sort(data, 3);
        top = BinarySearch.search(data, 0, 132850, filter, 2);
        bottom = top;
        if (top == -1)
            return data;
        while (!ceiling || !floor) {
            if (!ceiling) {
                if (top == 132850)
                    ceiling = true;
                else if (!data[top + 1].getViolationCode().equals(filter))
                    ceiling = true;
                else
                    top++;
            }
            if (!floor) {
                if (bottom == 0)
                    floor = true;
                else if (!data[bottom - 1].getViolationCode().equals(filter))
                    floor = true;
                else
                    bottom--;
            }
        }
        return Arrays.copyOfRange(data, bottom, top);
    }

    /**
     * Filter by day of the month
     * Finds the lowest and highest index with the same filter criteria and returns a subarray
     * (from the dataset) of all entries that fulfill the filter criteria
     *
     * @param data   dataset
     * @param filter day of the month (e.g. 17, 25, etc.)
     * @return array of entries that are recorded on the specified day
     */
    public static Entry[] date(Entry[] data, int filter) {
        floor = false;
        ceiling = false;
        LocalDateTime temp = LocalDateTime.of(2015, 12, filter, 0, 0);
        Sort.sort(data, 1);
        top = BinarySearch.search(data, 0, 132850, temp, 1);
        bottom = top;
        if (top == -1)
            return data;
        while (!ceiling || !floor) {
            if (!ceiling) {
                if (top == 132850)
                    ceiling = true;
                else if (data[top + 1].getDate().getDayOfMonth() != filter)
                    ceiling = true;
                else
                    top++;
            }
            if (!floor) {
                if (bottom == 0)
                    floor = true;
                else if (data[bottom - 1].getDate().getDayOfMonth() != filter)
                    floor = true;
                else
                    bottom--;
            }
        }
        return Arrays.copyOfRange(data, bottom, top);
    }

    /**
     * Filter by hour of the day
     * Finds the lowest and highest index with the same filter criteria and returns a subarray
     * (from the dataset) of all entries that fulfill the filter criteria
     *
     * @param data   dataset
     * @param filter hour of the day (e.g. 10, 15, etc.)
     * @return array of entries that are recorded within the hour specified
     */
    public static Entry[] hour(Entry[] data, int filter) {
        floor = false;
        ceiling = false;
        LocalDateTime temp = LocalDateTime.of(2015, 12, 1, filter, 0);
        Sort.sort(data, 2);
        top = BinarySearch.search(data, 0, 132850, temp, 2);
        bottom = top;
        if (top == -1)
            return data;
        while (!ceiling || !floor) {
            if (!ceiling) {
                if (top == 132850)
                    ceiling = true;
                else if (data[top + 1].getDate().getHour() != filter)
                    ceiling = true;
                else
                    top++;
            }
            if (!floor) {
                if (bottom == 0)
                    floor = true;
                else if (data[bottom - 1].getDate().getHour() != filter)
                    floor = true;
                else
                    bottom--;
            }
        }
        return Arrays.copyOfRange(data, bottom, top);
    }
}

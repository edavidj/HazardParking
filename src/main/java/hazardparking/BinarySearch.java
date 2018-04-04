/**
 *  @author- Steven Luu
 *  This class uses binary search to search through the Entry array looking for either a specific day, violation code, date, or hour
 *  When a search is found, it will return the index of the matched Entry.
 */
package hazardparking;

import java.time.LocalDateTime;

public class BinarySearch {
    private static int mid = 0;

    /**
     * Binary search for strings, this covers the following options (which should be specified in the last parameter):
     * Option 1- Day of the Week
     * Option 2- Violation Code
     *
     * @param data   dataset
     * @param min    minimum index to search
     * @param max    maximum index to search
     * @param target what we are trying to search for, in this case it is a string
     * @param option are we searching for the day of the week or violation code? Only possible options are 1 or 2
     * @return index that matches the search criteria (returns -1 if it search failed or resulted to an error)
     */
    public static int search(Entry[] data, int min, int max, String target, int option) {
        if (max >= min) {
            mid = min + (max - min) / 2;
            if (option == 1) {
                if (compare(target, data[mid].getDay()) == 0)
                    return mid;
                else if (compare(target, data[mid].getDay()) > 0)
                    return search(data, mid + 1, max, target, option);
                else
                    return search(data, min, mid - 1, target, option);
            } else if (option == 2) {
                if (target.equals(data[mid].getViolationCode())) {
                    return mid;
                } else if (target.compareTo(data[mid].getViolationCode()) > 0) {
                    return search(data, mid + 1, max, target, option);
                } else {
                    return search(data, min, mid - 1, target, option);
                }
            }
        }
        return -1;
    }

    /**
     * Binary search for dates, this covers the following options (which should be specified in the last parameter):
     * Option 1- Local Date
     * Option 2- Hour
     *
     * @param data   dataset
     * @param min    minimum index to search
     * @param max    maximum index to search
     * @param target what we are trying to search for, in this case it is a date
     * @param option are we searching for the day of the month or hour of the day?
     * @return index that matches the search criteria (returns -1 if search failed or resulted in an error)
     */
    public static int search(Entry[] data, int min, int max, LocalDateTime target, int option) {
        if (max >= min) {
            mid = min + (max - min) / 2;
            if (option == 1) {
                if (target.getDayOfMonth() == data[mid].getDate().getDayOfMonth()) {
                    return mid;
                } else if (target.getDayOfMonth() > data[mid].getDate().getDayOfMonth()) {
                    return search(data, mid + 1, max, target, option);
                } else {
                    return search(data, min, mid - 1, target, option);
                }
            } else if (option == 2) {
                if (target.getHour() == data[mid].getDate().getHour()) {
                    return mid;
                } else if (target.getHour() > data[mid].getDate().getHour()) {
                    return search(data, mid + 1, max, target, option);
                } else {
                    return search(data, min, mid - 1, target, option);
                }
            }
        }
        return -1;
    }

    /**
     * Special method when searching days as you cannot use the compareTo method to determine if a day was before or after another
     * This method compares two days
     *
     * @param s1 day being compared
     * @param s2 day comparing s1
     * @return 1- s1 is after s2, 0- s1 and s2 are the same day, -1- s1 is before s2
     */
    private static int compare(String s1, String s2) {
        if (convert(s1) == convert(s2))
            return 0;
        else if (convert(s1) > convert(s2))
            return 1;
        else
            return -1;
    }

    /**
     * This method is used to convert the days of the week into integers
     *
     * @param s Day to convert
     * @return Integer representing the day of the week where Monday is 1, Tuesday is 2, ... , Sunday is 7
     */
    private static int convert(String s) {
        switch (s) {
            case "MONDAY":
                return 1;
            case "TUESDAY":
                return 2;
            case "WEDNESDAY":
                return 3;
            case "THURSDAY":
                return 4;
            case "FRIDAY":
                return 5;
            case "SATURDAY":
                return 6;
            case "SUNDAY":
                return 7;
            default:
                return -1;
        }
    }
}

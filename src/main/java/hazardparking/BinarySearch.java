/**
    Author- Steven Luu
    This class uses binary search to search through the Entry array looking for either a specific day, violation code, date, or hour
    When a search is found, it will return the index of the matched Entry.
 */
package hazardparking;

import java.time.LocalDateTime;

public class BinarySearch {
    private static int mid = 0;
    /**
        Option 1- Day of the Week
        Option 2- Violation Code
     */
    public static int search(Entry[] data, int min, int max, String target, int option){
        if (max >= min) {
            mid = min + (max - min) / 2;
            if (option == 1){
                if (compare(target, data[mid].getDay()) == 0)
                    return mid;
                else if (compare(target, data[mid].getDay()) > 0)
                    return search(data, mid + 1, max, target, option);
                else
                    return search(data, min, mid - 1, target, option);
            }
            else{
                if (target.equals(data[mid].getViolationCode())){
                    return mid;
                }
                else if (target.compareTo(data[mid].getViolationCode()) > 0){
                    return search(data, mid + 1, max, target, option);
                }
                else{
                    return search(data, min, mid - 1, target, option);
                }
            }
        }
        return -1;
    }

    /**
        Option 1- Local Date
        Option 2- Hour
     */
    public static int search(Entry[] data, int min, int max, LocalDateTime target, int option){
        if (max >= min) {
            mid = min + (max - min) / 2;
            if (option == 1){
                if (target.getDayOfMonth() == data[mid].getDate().getDayOfMonth()){
                    return mid;
                }
                else if (target.getDayOfMonth() > data[mid].getDate().getDayOfMonth()){
                    return search(data, mid + 1, max, target, option);
                }
                else{
                    return search(data, min, mid - 1, target, option);
                }
            }
            else{
                if (target.getHour() == data[mid].getDate().getHour()){
                    return mid;
                }
                else if (target.getHour() > data[mid].getDate().getHour()){
                    return search(data, mid + 1, max, target, option);
                }
                else{
                    return search(data, min, mid - 1, target, option);
                }

            }
        }
        return -1;
    }

    private static int compare(String s1, String s2){
        if (convert(s1) == convert(s2))
            return 0;
        else if (convert(s1) > convert(s2))
            return 1;
        else
            return -1;
    }

    private static int convert(String s){
        switch (s){
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

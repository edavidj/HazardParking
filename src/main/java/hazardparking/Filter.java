/**
    Author- Steven Luu
    This class uses binary search to locate a match in the Entry array, it then finds all other entries that have the same match
    returning a subarray of a specified filter
    Possible inputs for filter parameter:
    day- "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"
    violationCode- "Pxxx" where x is a natural number (include leading 0's if the code is less than 100)
    date- Any whole number from 1-31
    hour- Any whole number from 0-23 where 0 is midnight (12AM)
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

    public static Entry[] day(Entry[] data, String filter){
        Sort.sort(data, 4);
        top = BinarySearch.search(data, 0, 132850, filter, 1);
        bottom = top;
        while (!ceiling || !floor){
            if (!ceiling){
                if (top == 132850)
                    ceiling = true;
                else if (!data[top+1].getDay().equals(filter))
                    ceiling = true;
                else
                    top++;
            }
            if (!floor){
                if (bottom == 0)
                    floor = true;
                else if (!data[bottom-1].getDay().equals(filter))
                    floor = true;
                else
                    bottom--;
            }
        }
        return Arrays.copyOfRange(data, bottom, top);
    }

    public static Entry[] violationCode(Entry[] data, String filter){
        Sort.sort(data, 3);
        top = BinarySearch.search(data, 0, 132850, filter, 2);
        bottom = top;
        while (!ceiling || !floor){
            if (!ceiling){
                if (top == 132850)
                    ceiling = true;
                else if (!data[top+1].getViolationCode().equals(filter))
                    ceiling = true;
                else
                    top++;
            }
            if (!floor){
                if (bottom == 0)
                    floor = true;
                else if (!data[bottom-1].getViolationCode().equals(filter))
                    floor = true;
                else
                    bottom--;
            }
        }
        return Arrays.copyOfRange(data, bottom, top);

    }

    public static Entry[] date(Entry[] data, int filter){
        LocalDateTime temp = LocalDateTime.of(2015,12,filter,0,0);
        Sort.sort(data, 1);
        top = BinarySearch.search(data, 0, 132850,temp,1);
        bottom = top;
        while (!ceiling || !floor){
            if (!ceiling){
                if (top == 132850)
                    ceiling = true;
                else if (data[top+1].getDate().getDayOfMonth() != filter)
                    ceiling = true;
                else
                    top++;
            }
            if (!floor){
                if (bottom == 0)
                    floor = true;
                else if (data[bottom-1].getDate().getDayOfMonth() != filter)
                    floor = true;
                else
                    bottom--;
            }
        }
        return Arrays.copyOfRange(data, bottom, top);
    }

    public static Entry[] hour(Entry[] data, int filter){
        LocalDateTime temp = LocalDateTime.of(2015,12,1,filter,0);
        Sort.sort(data, 2);
        top = BinarySearch.search(data, 0, 132850, temp, 2);
        bottom = top;
        while (!ceiling || !floor){
            if (!ceiling){
                if (top == 132850)
                    ceiling = true;
                else if (data[top+1].getDate().getHour() != filter)
                    ceiling = true;
                else
                    top++;
            }
            if (!floor){
                if (bottom == 0)
                    floor = true;
                else if (data[bottom-1].getDate().getHour() != filter)
                    floor = true;
                else
                    bottom--;
            }
        }
        return Arrays.copyOfRange(data, bottom, top);
    }
}

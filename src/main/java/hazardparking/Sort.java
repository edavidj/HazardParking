package hazardparking;

import java.time.*;
import java.util.ArrayList;
/**
 * Implementation of merge sort using different interfaces
 * @author Ethan Johnston, Rico Florentino
 *
 */
public class Sort {
	public static Entry[] aux;
	/**
	 * exported sorting function calls on recursive version
	 * @param a Entry[] array to be sorted
	 * @param option interface being sorted
	 */
	public static void sort(Entry[] a, int option){
		aux = new Entry[a.length];
		sort(a, 0, a.length-1, option);
	}
	/**
	 * recursive merge sort function
	 * @param a Entry[] array to be sorted
	 * @param low int lower end of bounds
	 * @param high int upper end of bounds
	 * @param option interface being compared
	 */
	private static void sort(Entry[] a, int low, int high, int option){
		if(high <= low) return; //base case for finished sorting
		int mid = low +(high-low)/2;
		//divide into parts and sort
		sort(a, low, mid, option);
		sort(a, mid+1, high, option);
		//merge divided parts after sort
		merge(a, low, mid, high, option);
	}
	/**
	 * merge sorted section of the array
	 * @param a LocalDateTime[] array having sections merged
	 * @param low lower end of bounds
	 * @param mid divide between two sections being merged
	 * @param high upper end of the bounds
	 * @param option interface being compared
	 */
	public static void merge(Entry[] a, int low, int mid, int high, int option){
		int i = low; int j = mid+1;
		//clone array into temp
		for(int k = low; k <= high; k++){
			aux[k] = a[k];
		}
		//modify array to merge sections
		for(int k = low; k <= high; k++){
			if (i > mid) {
				a[k] = aux[j++];
			}
			else if(j > high) {
				a[k] = aux[i++];
			}
			else if (less(aux[j], aux[i], option)) {
				a[k] = aux[j++];
			}
			else {
				a[k] = aux[i++];
			}

		}
	}
	/**
	 * utility method for comparing two interfaces
	 * @param a interface being compared from
	 * @param b interface being compared to
	 * @param option how it is being compared
	 * @return true if less false if not
	 */
	public static boolean less(Entry a, Entry b, int option) {

		if (option == 1)	// option 1 is comparing LocalDateTime of both objects
		{
			return a.getDate().compareTo(b.getDate()) < 0;

		}
		else if (option == 2)		// option 2 is comparing it by HOUR
		{
			Integer hourA = a.getDate().getHour();
			Integer hourB = b.getDate().getHour();
			return hourA.compareTo(hourB) < 0;
		}
		else if (option == 3)		// option 3 is comparing it by violationCode
		{
			String stringCodeA = a.getViolationCode();
			String stringCodeB = b.getViolationCode();

			return stringCodeA.compareTo(stringCodeB) < 0;
		}
		else if (option == 4)		//option 4 is comparing it by day of week
		{
			DayOfWeek DayA = a.getDate().getDayOfWeek();
			DayOfWeek DayB = b.getDate().getDayOfWeek();

			return DayA.compareTo(DayB) < 0;
		}
		return true;
	}
	/**
	 * utility method for checking if the array is sorted
	 * @param a Entry[] array being checked
	 * @param option how it is compared
	 * @return true if sorted and false if not
	 */
	public static boolean isSorted(Entry[] a, int option) {
		for (int i = 1; i <= a.length-1; i++) {
			if (less(a[i],a[i-1], option)) {
				return false;
			}
		}
		return true;
	}
}

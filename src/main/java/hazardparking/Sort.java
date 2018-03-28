package hazardparking;

import java.time.*;
import java.util.ArrayList;
/**
 * Implementation of merge sort using LocalDate interface
 * @author Ethan Johnston
 * 
 */
public class Sort {
		public static Entry[] aux;
		/**
		 * exported sorting function calls on recursive version
		 * @param a LocalDateTime[] array to be sorted
		 */
		public static void sort(Entry[] a){
			aux = new Entry[a.length];
			sort(a, 0, a.length-1);
		}
		/**
		 * recursive merge sort function
		 * @param a LocalDateTime[] array to be sorted
		 * @param low int lower end of bounds
		 * @param high int upper end of bounds
		 */
		private static void sort(Entry[] a, int low, int high){
			if(high <= low) return; //base case for finished sorting
			int mid = low +(high-low)/2;
			//divide into parts and sort
			sort(a, low, mid);
			sort(a, mid+1, high);
			//merge divided parts after sort
			merge(a, low, mid, high);
		}
		/**
		 * merge sorted section of the array
		 * @param a LocalDateTime[] array having sections merged
		 * @param low lower end of bounds
		 * @param mid divide between two sections being merged
		 * @param high upper end of the bounds
		 */
		public static void merge(Entry[] a, int low, int mid, int high){
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
				else if (less(aux[j], aux[i])) {
					a[k] = aux[j++];
				}
				else {
					a[k] = aux[i++];
				}
			}
		}
		/**
		 * utility method for comparing two dates
		 * @param a date being compared from
		 * @param b date being compared to
		 * @return true if less false if not
		 */
		private static boolean less(Entry a, Entry b) {
			return a.getDate().compareTo(b.getDate()) < 0;
			//return a.compareTo(b) < 0;
		}
		
		public static boolean isSorted(Entry[] a) {
			for (int i = 1; i <= a.length-1; i++) {
				if (less(a[i],a[i-1])) {
					return false;
				}
			}
			return true;
		}
}

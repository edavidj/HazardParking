package hazardparking;

import java.time.*;
import java.util.ArrayList;
/**
 * Implementation of merge sort using LocalDate interface
 * @author Ethan Johnston
 * 
 */
public class Sort {
		public static ArrayList<Entry> aux;
		/**
		 * exported sorting function calls on recursive version
		 * @param a LocalDateTime[] array to be sorted
		 */
		public static void sort(ArrayList<Entry> a){
			aux = new ArrayList<Entry>();
			sort(a, 0, a.size() - 1);
		}
		/**
		 * recursive merge sort function
		 * @param a LocalDateTime[] array to be sorted
		 * @param low int lower end of bounds
		 * @param high int upper end of bounds
		 */
		private static void sort(ArrayList<Entry> a, int low, int high){
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
		private static void merge(ArrayList<Entry> a, int low, int mid, int high){
			int i = low; int j = mid+1;
			//clone array into temp
			for(int k = low; k <= high; k++){
				aux.set(k, a.get(k));
			}
			//modify array to merge sections
			for(int k = low; k <= high; k++){
				if (i > mid) {
					a.set(k, aux.get(j++));
				}
				else if(j > high) {
					a.set(k, aux.get(i++));
				}
				else if (less(aux.get(j), aux.get(i))) {
					a.set(k, aux.get(j++));
				}
				else {
					a.set(k, aux.get(i++));
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
		
		public static boolean isSorted(ArrayList<Entry> a) {
			for (int i = 1; i <= a.size(); i++) {
				if (less(a.get(i),a.get(i-1))) {
					return false;
				}
			}
			return true;
		}
}

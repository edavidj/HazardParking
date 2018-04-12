package hazardparking;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestApp {

	Entry[] testdata;
	


	@Before
	public void setUp() throws Exception {
		
		ExtractData.init();
		testdata = ExtractData.getData();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSortLDT() {
		
		// Calls upon sorting the data on option 1 - Local Date Time
        Sort.sort(testdata, 1);
        assertTrue(Sort.isSorted(testdata, 1));
	}
	
	@Test
	public void testSortHour() {
		
		// Calls upon sorting the data on option 2 - Hour
		Sort.sort(testdata, 2);
        assertTrue(Sort.isSorted(testdata, 2));
	}
	
	@Test
	public void testSortVC() {
		
		// Calls upon sorting the data on option 3 - Violation Code
		Sort.sort(testdata, 3);
        assertTrue(Sort.isSorted(testdata, 3));
	}
	
	@Test
	public void testSortDOW() {
		
		// Calls upon sorting the data on option 3 - Day of Week
		Sort.sort(testdata, 4);
        assertTrue(Sort.isSorted(testdata, 4));
	}

	@Test
	public void testFilterDay(){
		Entry[] filtered = Filter.day(testdata, "MONDAY");
		boolean test = true;
		for (int i = 0; i < filtered.length; i++) {
			if (!filtered[i].getDay().equals("MONDAY")) {
				test = false;
			}
		}
		assertTrue(test);
	}

	@Test
	public void testFilterViolationCode(){
		Entry[] filtered = Filter.violationCode(testdata, "P003");
		boolean test = true;
		for (int i = 0; i < filtered.length; i++){
			if (!filtered[i].getViolationCode().equals("P003"))
				test = false;
		}
		assertTrue(test);
	}

	@Test
	public void testFilterDate(){
		Entry[] filtered = Filter.date(testdata, 23);
		boolean test = true;
		for (int i = 0; i < filtered.length; i++){
			if (filtered[i].getDate().getDayOfMonth() != 23)
				test = false;
		}
		assertTrue(test);
	}

	@Test
	public void testFilterHour(){
		Entry[] filtered = Filter.hour(testdata, 9);
		boolean test = true;
		for (int i = 0; i < filtered.length; i++){
			if (filtered[i].getDate().getHour() != 9)
				test = false;
		}
		assertTrue(test);
	}
}

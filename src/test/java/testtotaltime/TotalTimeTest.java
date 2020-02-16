package testtotaltime;

import static org.junit.Assert.*;

import org.junit.Test;

import datastructure.EnduroTime;

public class TotalTimeTest {

	@Test
	public void correctTimeTest() {
		String strTotal = "01.23.34";
		EnduroTime start = EnduroTime.parse("12:00:00"); 
		EnduroTime stop = EnduroTime.parse("13:23:34");
		//EnduroTime total = EnduroTime.parse(strTotal);
		EnduroTime time = EnduroTime.between(start, stop);
		
		assertEquals(strTotal, time.toString()); 
	}
	
//	@Test
//	public void refactorTest() {
//		String start = "12.00.00"; 
//		String stop = "13.23.34";
//		TotalTime time = new TotalTime(start,stop);
//		String testTime = "12.00.00"; 
//
//	}

}

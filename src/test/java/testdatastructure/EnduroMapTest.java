package testdatastructure;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import datastructure.EnduroMap;
import datastructure.EnduroTime;
import datastructure.Person;

public class EnduroMapTest {
	EnduroMap em = new EnduroMap();

	@Before
	public void setUp() {
		em.addParticipant(new Person(1));
	}
	
	@Test
	public void testCorrectCreation() {
		for (int i = 0; i < 10; i++) {
			em.addParticipant(new Person(i));
		}
		assertEquals(10, em.participants());
	}
	
	@Test
	public void testLoadStart() {
		em.putStartTime(1,EnduroTime.parse("00:01:00"));
		assertEquals(em.getStartTimes(1).get(0).toString(), EnduroTime.parse("00:01:00").toString());
	}
	
	@Test
	public void testLoadEnd() {
		EnduroTime et = EnduroTime.parse("00:01:00");
		em.putEndTime(1, et);
		assertEquals(em.getEndTimes(1).get(0).toString(), et.toString());
	}

	@Test
	public void testMapForLaps() {
		em.putStartTime(1, EnduroTime.parse("10:00:00"));
		em.putStartTime(1, EnduroTime.parse("11:00:00"));
		em.putEndTime(1, EnduroTime.parse("12:00:00"));
		assertEquals(em.getStartTimes(1).get(0), EnduroTime.parse("10:00:00"));
		assertEquals(em.getStartTimes(1).get(1), EnduroTime.parse("11:00:00"));

	}
}

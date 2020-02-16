package testparse;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import parse.TimeParser;

public class TimeParserTest {

	@Test //TODO fixa testet
	public void testTimeParse() {
		
		assertEquals(TimeParser.parseTime("00.00.01", "\\.", ":"), "00:00:01");
		assertEquals(TimeParser.parseTime("00.00", "\\.", ":"), "00:00:00");
	}

}

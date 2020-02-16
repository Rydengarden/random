package testparse;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import datastructure.EnduroMap;
import datastructure.EnduroTime;
import datastructure.Person;
import parse.ParseInRegister;
import parse.Register;

public class ParseInTest {
	private EnduroMap em;

	@Before
	public void setup() {
		em = new EnduroMap();
		em.addParticipant(new Person(1));
		em.addParticipant(new Person(2));
	}

	@Test
	public void parseFileOneParticipantTest() throws IOException {
		em.putStartTime(1, EnduroTime.parse("12:00:00"));
		em.putEndTime(1, EnduroTime.parse("12:00:01"));
		ParseInRegister parse = new ParseInRegister(em);
		(new Register(new File("mockstart.txt"))).addEntry(1, "12.00.00");
		(new Register(new File("mockend.txt"))).addEntry(1, "12.00.01");
		parse.parseStartFile(new File("mockstart.txt"));
		parse.parseEndFile(new File("mockend.txt"));
		assertEquals(parse.getEnduroMap().getRow(1), em.getRow(1));
	}

	public void parseFileManyParticipantsTest() throws IOException {
		ParseInRegister parse = new ParseInRegister();
		em = new EnduroMap();

		Register startReg = new Register(new File("mockstart.txt"));
		for (int i = 0; i < 5; i++) {
			startReg.addEntry(i + 1, "00:00:0" + i);
			em.putStartTime(i + 1, EnduroTime.parse("00:00:0" + i));
		}

		Register endReg = new Register(new File("mockend.txt"));
		for (int i = 5; i < 10; i++) {
			endReg.addEntry(i + 1, "00:00:0" + i);
			em.putStartTime(i + 1, EnduroTime.parse("00:00:0" + i));
		}

		parse.parseStartFile(new File("mockstart.txt"));
		parse.parseEndFile(new File("mockend.txt"));
		String parseResult = "";
		String emResult = "";
		for (int i = 1; i < parse.getEnduroMap().participants(); i++) {
			parseResult = parseResult + parse.getEnduroMap().getRow(i);
			emResult = emResult + em.getRow(i);
		}
		assertEquals(parseResult, emResult);
	}

	@Test
	public void missingStartOrEndTimeTest() throws FileNotFoundException {
		ParseInRegister parse = new ParseInRegister(em);
		(new Register(new File("mockstart.txt"))).addEntry(1, "12.00.00");
		(new Register(new File("mockend.txt"))).addEntry(2, "12.00.01");
		parse.parseStartFile(new File("mockstart.txt"));
		parse.parseEndFile(new File("mockend.txt"));
		assertEquals(em.getStartTimes(2).get(0).getTimeOrDefault("start"), "start");
		assertEquals(em.getEndTimes(1).get(0).getTimeOrDefault("end"), "end");
	}

	public void moreThanOneStartOrEndTimeTest() throws FileNotFoundException {
		
	}

	@After
	public void clear() {
		new File("mockstart.txt").delete();
		new File("mockend.txt").delete();
	}
}

package testparse;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import analyseresult.Result;
import datastructure.*;

public class ResultTest {
	EnduroMap em = new EnduroMap();
	
	@Before
	public void setUp() {
		em.addParticipant(new Person(1));
		em.addParticipant(new Person(2));
	}
	
	@Test
	public void generateResultFileTest() throws IOException {
		em.putStartTime(1, EnduroTime.parse("00:00:00"));
		em.putEndTime(1, EnduroTime.parse("01:00:01"));
		em.putStartTime(2, EnduroTime.parse("00:00:02"));
		em.putEndTime(2, EnduroTime.parse("01:00:03"));
		(new Result(em)).generateMinimalOneLapResult("mockresult.txt");
		Scanner scan = new Scanner(new File("mockresult.txt"));
		String threeFirstLines = scan.nextLine() + "\n" + scan.nextLine() + "\n" + scan.nextLine();
		assertEquals(threeFirstLines, "StartNr; Totaltid; Start; Mål\n" + 
									"1; 01.00.01; 00.00.00; 01.00.01\n" +
									"2; 01.00.01; 00.00.02; 01.00.03");
		scan.close();
		
	}
	
	@Test
	public void missingStartOrEndTimeTest() throws IOException {
		em.putStartTime(1, new EnduroTime());
		em.putEndTime(1, EnduroTime.parse("00:00:01"));
		em.putStartTime(2, EnduroTime.parse("00:00:02"));
		em.putEndTime(2, new EnduroTime());
		(new Result(em)).generateMinimalOneLapResult("mockresult.txt");
		Scanner scan = new Scanner(new File("mockresult.txt"));
		String threeFirstLines = scan.nextLine() + "\n" + scan.nextLine() + "\n" + scan.nextLine();
		assertEquals(threeFirstLines, "StartNr; Totaltid; Start; Mål\n" + 
									"1; --.--.--; Start?; 00.00.01\n" +
									"2; --.--.--; 00.00.02; Slut?");
		scan.close();
	}

	@Test
	public void moreThanOneStartOrEndTimeTest() throws IOException {
		em.putStartTime(1, EnduroTime.parse("00:00:01"));
		em.putStartTime(1, EnduroTime.parse("00:00:02"));
		em.putEndTime(1, EnduroTime.parse("01:00:03"));
		em.putStartTime(2, EnduroTime.parse("00:00:00"));
		em.putEndTime(2, EnduroTime.parse("01:00:05"));
		em.putEndTime(2, EnduroTime.parse("01:00:04"));
		(new Result(em)).generateMinimalOneLapResult("mockresult.txt");
		Scanner scan = new Scanner(new File("mockresult.txt"));
		String threeFirstLines = scan.nextLine() + "\n" + scan.nextLine() + "\n" + scan.nextLine();
		assertEquals(threeFirstLines, "StartNr; Totaltid; Start; Mål\n" + 
									"1; 01.00.02; 00.00.01; 01.00.03; Flera starttider?; 00.00.02\n" +
									"2; 01.00.05; 00.00.00; 01.00.05; Flera måltider?; 01.00.04");
		scan.close();
	}

	@Test
	public void unreasonableTotalTimeTest() throws IOException {
		em.putStartTime(1, EnduroTime.parse("00:00:00"));
		em.putEndTime(1, EnduroTime.parse("00:00:01"));
		em.putStartTime(2, EnduroTime.parse("00:00:00"));
		em.putEndTime(2, EnduroTime.parse("01:00:00"));
		(new Result(em)).generateMinimalOneLapResult("mockresult.txt");
		Scanner scan = new Scanner(new File("mockresult.txt"));
		String threeFirstLines = scan.nextLine() + "\n" + scan.nextLine() + "\n" + scan.nextLine();
		assertEquals(threeFirstLines, "StartNr; Totaltid; Start; Mål\n" + 
									"1; 00.00.01; 00.00.00; 00.00.01; Omöjlig totaltid?\n" +
									"2; 01.00.00; 00.00.00; 01.00.00");
		scan.close();
	}

	@After
	public void clear() {
		new File("mockresult.txt").delete();
	}
}

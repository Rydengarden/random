package testparse;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Test;

import datastructure.EnduroTime;
import parse.Register;

public class RegisterTest {
	
	@Test
	public void createRegFileTest() throws IOException {
		Register reg = new Register(new File("mockreg.txt"));
		reg.deleteFile("mockreg.txt");
		reg.addEntry(1, "00.00.00");
		reg.addEntry(2, "00.00.01");
		Scanner scan = new Scanner(new File("mockreg.txt"));
		assertEquals(scan.nextLine(), "1; 00.00.00");
		assertEquals(scan.nextLine(), "2; 00.00.01");
		scan.close();
	}
	
	
	@Test
	public void createRegFileTimeTest() throws IOException {
		Register reg = new Register(new File("time.txt"));
		reg.deleteFile("time.txt");
		reg.addEntry(1);
		reg.addEntry(2);
		Scanner scan = new Scanner(new File("time.txt"));
		assertEquals(scan.nextLine(), "1; "+ EnduroTime.now().toString());
		assertEquals(scan.nextLine(), "2; "+ EnduroTime.now().toString());
		scan.close();
	}

	@After
	public void clear() {
		new File("time.txt").delete();
		new File("mockreg.txt").delete();
	}
}

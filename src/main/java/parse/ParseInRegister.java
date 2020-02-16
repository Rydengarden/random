package parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import datastructure.EnduroMap;
import datastructure.EnduroTime;

public class ParseInRegister extends TimeParser{
	private EnduroMap em;
	
	public ParseInRegister(EnduroMap em) {
		this.em = em;
	}
	
	public ParseInRegister() {
		this.em = new EnduroMap();
	}
	
	public EnduroMap getEnduroMap() {
		return em;
	}

	public void parseStartFile(File file) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		String[] line;
		while (scan.hasNextLine()) {
			line = scan.nextLine().split("; ");
			if(line.length < 2){
				em.putStartTime(Integer.parseInt(line[0]), null);
			}
			em.putStartTime(Integer.parseInt(line[0]), EnduroTime.parse(parseTime(line[1], "\\.", ":")));
		}
		scan.close();
		
	}
	public void parseEndFile(File file) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		String[] line;
		while (scan.hasNextLine()) {
			line = scan.nextLine().split("; ");
			em.putEndTime(Integer.parseInt(line[0]), EnduroTime.parse(parseTime(line[1], "\\.", ":")));
		}
		scan.close();
	}
}

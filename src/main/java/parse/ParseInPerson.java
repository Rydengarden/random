package parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import datastructure.*;

public class ParseInPerson {
	EnduroMap em;
	
	public ParseInPerson() {
		em = new EnduroMap();
	}
	
	public ParseInPerson(EnduroMap em) {
		this.em = em;
	}
	
	public void parseNameFile(File nameFile) throws FileNotFoundException {
		Scanner scan = new Scanner(nameFile);
		String[] identifiers;
		String[] line;
		int nbr;
		identifiers = scan.nextLine().split("; "); //gets column names
		
		while (scan.hasNextLine()) {
			line = scan.nextLine().split("; ");
			nbr = Integer.parseInt(line[0]);
			
			if (!em.containsNbr(nbr)) {
				em.addParticipant(new Person(nbr));
			}
			
			for (int i = 0; i < identifiers.length; i++) {	
				em.getPerson(nbr).setDetail(identifiers[i], line[i]);
			} 
		}
		
		scan.close();
	}
	
	public List<String> updateIdentifiers(String[] identifiers) {
		List<String> list = new ArrayList<String>(Arrays.asList(identifiers));
		return list;
	}
}

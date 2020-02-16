package parse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import datastructure.EnduroTime;

 public class Register {
 	private File file;

 	public Register(File file) {
 		this.file = file;
 	}

	
 	private void addToRegFile(int startnbr, String time) throws IOException {
 		FileWriter fw = new FileWriter(file, true);
 		fw.write(startnbr + "; " + time + "\n");
 		fw.close();
 		// TODO write to gui
 	}
	
	public boolean addEntry(int startnbr, String time) {
		try {
			addToRegFile(startnbr, time);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
 	public boolean addEntry(int startnbr) {
 		try {
 			addToRegFile(startnbr, EnduroTime.now().toString());
 			return true;
 		} catch (IOException e) {
 			return false;
 		}
 	}
	
 	public void deleteFile(String fileName) {
 		File file = new File(fileName);
 		file.delete();
 	}
 }

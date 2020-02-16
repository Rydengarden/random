package parse;

public class TimeParser {
	
	public static String parseTime(String time, String parseFromSep, String parseToSep) {
		
		String s = time.replaceAll(parseFromSep, parseToSep);
	
		
		if (s.length() == 5) { 

			return s + parseToSep + "00";
		} else {
			return s;
		}
	}
}

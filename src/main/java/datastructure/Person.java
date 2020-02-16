package datastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {
	int participantNbr;
	Map<String, String> details;
	List<EnduroTime> endTimes;
	List<EnduroTime> startTimes;

	public Person(Integer participantNbr) {
		this.participantNbr = participantNbr;
		details = new HashMap<String, String>();
		details.put("StartNr", participantNbr.toString());
		startTimes = new ArrayList<EnduroTime>();
		endTimes = new ArrayList<EnduroTime>();
	}

	public void putStartTime(EnduroTime time) {
		startTimes.add(time);
	}

	public void putEndTime(EnduroTime time) {
		endTimes.add(time);
	}
	public void putName(String name){
		details.put("name", name);
	}
	public String getName(){
		return details.get("name");
	}

	public List<EnduroTime> getStartTimes() {
		if (startTimes.isEmpty()) {
			ArrayList<EnduroTime> list = new ArrayList<EnduroTime>();
			list.add(new EnduroTime());
			return list;
		} else {
			return startTimes;   //placeholder
		}
	}

	public List<EnduroTime> getEndTimes() {
		if (endTimes.isEmpty()) {
			ArrayList<EnduroTime> list = new ArrayList<EnduroTime>();
			list.add(new EnduroTime());
			return list;
		} else {
			return endTimes;   //placeholder
		}
	}

	public String details() {
		StringBuilder string = new StringBuilder();
		details.forEach((id, d) -> {
			string.append(d + "; ");
		});
		return string.toString();
	}

	public int getNbr() {
		return participantNbr;
	}

	public void setDetail(String identifier, String detail) {
		details.put(identifier, detail);
	}
	
}
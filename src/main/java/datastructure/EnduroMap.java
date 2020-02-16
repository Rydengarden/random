package datastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnduroMap {
	private Map<Integer, Person> enduroMap;
	private List<String> identifiers;
	
	public EnduroMap() {
		enduroMap = new HashMap<Integer, Person>();
	}
	
	public int participants() {
		return enduroMap.size();
	}

	public void putStartTime(int participantNbr, EnduroTime startTime) {
		enduroMap.get(participantNbr).putStartTime(startTime);
	}

	public void putEndTime(int participantNbr, EnduroTime endTime) {
		enduroMap.get(participantNbr).putEndTime(endTime);
	}
	
	public List<EnduroTime> getStartTimes(int participantNbr) {
		return enduroMap.get(participantNbr).getStartTimes();
	}
	
	public List<EnduroTime> getEndTimes(int participantNbr) {
		return enduroMap.get(participantNbr).getEndTimes();
	}

	public String getRow(int participantNbr) {
		Person p = enduroMap.get(participantNbr);
		return p.details() + p.getStartTimes() + p.getEndTimes();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof EnduroMap) {
			return enduroMap.equals(o);
		}
		return false;
	}

	public void addParticipant(Person person) {
		enduroMap.put(person.getNbr(),person);
		
	}
	
	public boolean containsNbr(int nbr) {
		return enduroMap.containsKey(nbr);
	}

	public Person getPerson(int nbr) {
		return enduroMap.get(nbr);
	}
	
	public void setIdentifiers(List<String> list) {
		list.forEach((i) -> {
			if (!identifiers.contains(i)) {
				identifiers.add(i);
			}
		});
	}
	
	public String formattedFirstColumn(){
		StringBuilder s = new StringBuilder();
		identifiers.forEach((i) -> s.append(i + "; "));
		return s.toString();
	}
}

package analyseresult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import datastructure.EnduroMap;
import datastructure.EnduroTime;

public class Result {
	private EnduroMap em;
	
	public Result(EnduroMap em) {
		this.em = em;
	}

	public void generateMinimalOneLapResult(String fileName) throws IOException {
		File file = new File(fileName);
		file.delete();
		StringBuilder sb = new StringBuilder();
		sb.append("StartNr; Totaltid; Start; Mål\n");
		String startTime;
		String endTime;	
		EnduroTime totalTime;
		for (int i = 1; i <= em.participants(); i++) {
			startTime = em.getStartTimes(i).get(0).getTimeOrDefault("Start?");
			endTime = em.getEndTimes(i).get(0).getTimeOrDefault("Slut?");
			totalTime = EnduroTime.between(em.getStartTimes(i).get(0), em.getEndTimes(i).get(0));
			sb.append(em.getPerson(i).details() + totalTime.getTimeOrDefault("--.--.--") + "; " + startTime + "; " + endTime); 
			sb.append(errorHandling(1, i));
		}
		FileWriter fw = new FileWriter(fileName, true);
		fw.write(sb.toString());
		fw.close();
	}

	private String errorHandling(int maxEndTimes, int participantNbr) {
		StringBuilder sb = new StringBuilder();
		if (em.getStartTimes(participantNbr).size() > 1) {
			sb.append("; Flera starttider?" + redundantTimes(em.getStartTimes(participantNbr)));
		}
		if (em.getEndTimes(participantNbr).size() > 1) {
			sb.append("; Flera måltider?" + redundantTimes(em.getEndTimes(participantNbr)));
		}
		if (!EnduroTime.between(em.getStartTimes(participantNbr).get(0), em.getEndTimes(participantNbr).get(0)).isReasonableTotalTime()) {
			sb.append("; Omöjlig totaltid?");
		}
		sb.append("\n");
		return sb.toString();
	}

	private String redundantTimes(List<EnduroTime> list) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < list.size(); i++) {
			sb.append("; " + list.get(i));
		}
		return sb.toString();
	}
}

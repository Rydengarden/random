package datastructure;

import java.time.Duration;
import java.time.LocalTime;

import parse.TimeParser;

public class EnduroTime {
    private LocalTime localtime;

    public EnduroTime() {
        //if time unknown (intentionally left blank)
    }

    public EnduroTime(LocalTime localtime) {
        this.localtime = localtime;
    }

	public static EnduroTime now() {
        return new EnduroTime(LocalTime.now().withNano(0));
    }

    public static EnduroTime parse(String text){
        return new EnduroTime(LocalTime.parse(text));
    }

    public static EnduroTime between(EnduroTime start, EnduroTime end) {
        if (start.localtime != null && end.localtime != null) {
            Duration diff = Duration.between(start.localtime, end.localtime);
            long seconds = diff.getSeconds();
            long absSeconds = Math.abs(seconds);
            String positive = String.format(
                "%02d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
            return EnduroTime.parse(seconds < 0 ? "-" + positive : positive);
        } else {
            return new EnduroTime();
        }
    }

    @Override
    public boolean equals(Object obj) {
        return this.localtime.equals(((EnduroTime) obj).localtime);
    }

    @Override
    public String toString() {
        return TimeParser.parseTime(localtime.toString(), ":", ".");
    }

    public String getTimeOrDefault(String defaultString) {
        if (localtime != null) {
            return TimeParser.parseTime(localtime.toString(), ":", ".");
        } else {
            return defaultString;
        }
    }

    public boolean isReasonableTotalTime() {
        if (localtime == null) {
            return true;
        }
        return localtime.compareTo(LocalTime.parse("00:15:00")) >= 0;
    }
}
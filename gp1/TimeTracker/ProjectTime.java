package gp1.TimeTracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectTime {
    private String startTime;
    private String endTime;
    private float hoursLogged;

    // Constructor: initialize with start and end times
    public ProjectTime(String start, String end) {
        this.startTime = start;
        this.endTime = end;
        calculateHoursLogged();
    }

    // Setter for startTime - updates startTime and recalculates hoursLogged
    public void setStartTime(String newStartTime) {
        this.startTime = newStartTime;
        calculateHoursLogged();
    }

    // Setter for endTime - updates endTime and recalculates hoursLogged
    public void setEndTime(String newEndTime) {
        this.endTime = newEndTime;
        calculateHoursLogged();
    }

    // Getter for startTime
    public String getStartTime() {
        return startTime;
    }

    // Getter for endTime
    public String getEndTime() {
        return endTime;
    }

    // Getter for hoursLogged - returns formatted string
    public String getHoursLogged() {
        // If there was an error, return -1
        if (hoursLogged == -1) {
            return "-1";
        }

        // Convert hoursLogged (in minutes) to appropriate format
        float minutes = hoursLogged;

        // Less than 120 minutes: show in minutes
        if (minutes < 120) {
            return (int) minutes + " m";
        }

        // Convert to hours
        float hours = minutes / 60;

        // Less than 120 hours: show in hours
        if (hours < 120) {
            return (int) hours + " h";
        }

        // Convert to days
        float days = hours / 24;

        // Less than 120 days: show in days
        if (days < 120) {
            return (int) days + " d";
        }

        // 120 days or more: show in months (30 days per month)
        float months = days / 30;
        return (int) months + " mo";
    }

    // Helper method to calculate hoursLogged from startTime and endTime
    private void calculateHoursLogged() {
        try {
            // Create date formatter for the pattern "yyyy-MM-dd HH:mm"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            // Parse start and end times into Date objects
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            // Get time in milliseconds
            long startMs = start.getTime();
            long endMs = end.getTime();

            // Calculate difference in milliseconds
            long differenceMs = endMs - startMs;

            // Convert milliseconds to minutes
            // 1 minute = 60,000 milliseconds
            hoursLogged = differenceMs / 60000.0f;

            // Ensure hoursLogged is not negative (except -1 for errors)
            if (hoursLogged < 0) {
                hoursLogged = -1;
            }

        } catch (ParseException e) {
            // If parsing fails, set hoursLogged to -1
            hoursLogged = -1;
        }
    }
}

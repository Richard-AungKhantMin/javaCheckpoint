package DayOfWeek;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DayOfWeekFinder {
    public String findNextDayOfWeek(String startDate, String dayOfWeek) {
        try {
            LocalDate start = LocalDate.parse(startDate);
            DayOfWeek targetDay = DayOfWeek.valueOf(dayOfWeek.toUpperCase());
            DayOfWeek currentDay = start.getDayOfWeek();
            
            int currentDayValue = currentDay.getValue();
            int targetDayValue = targetDay.getValue();
            int daysToAdd = targetDayValue - currentDayValue;
            
            // If target day is today or already passed, go to next week
            if (daysToAdd <= 0) {
                daysToAdd += 7;
            }
            
            LocalDate nextDate = start.plusDays(daysToAdd);
            return nextDate.toString();
        } catch (Exception e) {
            return "Error";
        }
    }
}

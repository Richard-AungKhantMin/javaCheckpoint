package gp1.DayOfWeek;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DayOfWeekFinder {
    public String findNextDayOfWeek(String startDate, String dayOfWeek) {
        
        try{
            
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, format);
        
        DayOfWeek targetDay = DayOfWeek.valueOf(dayOfWeek.toUpperCase());
        DayOfWeek startDay = start.getDayOfWeek();
        
        int diff = targetDay.getValue() - startDay.getValue();
        
        if (diff <= 0){
            diff = diff + 7;
        }
        
        start = start.plusDays(diff);
        
        return start.toString();
        
        }catch (Exception e){
            return "Error";
        }
        
        
    }
}

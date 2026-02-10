package MonthlyPeriod;

import java.time.LocalDate;
import java.time.Period;

public class MonthlyPeriod {
    public String calculatePeriod(String startDate, String endDate) {
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            Period period = Period.between(start, end);
            
            int years = Math.abs(period.getYears());
            int months = Math.abs(period.getMonths());
            
            // Handle same date case
            if (years == 0 && months == 0) {
                return "";
            }
            
            String yearPart = years == 1 ? "1 year" : years + " years";
            String monthPart = months == 1 ? "1 month" : months + " months";
            
            if (years > 0 && months > 0) {
                return yearPart + " and " + monthPart;
            } else if (years > 0) {
                return yearPart;
            } else {
                return monthPart;
            }
        } catch (Exception e) {
            return "Error";
        }
    }
}
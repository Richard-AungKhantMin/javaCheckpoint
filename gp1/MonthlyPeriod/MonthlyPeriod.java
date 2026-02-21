package gp1.MonthlyPeriod;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class MonthlyPeriod {
    public String calculatePeriod(String startDate, String endDate) {
        // Implementation to calculate the period between two dates in months and years
        try{
            
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(startDate, format);
            LocalDate end = LocalDate.parse(endDate, format);
            Period diff = Period.between(start, end);
            
            int yInt = Math.abs(diff.getYears());
            int mInt = Math.abs(diff.getMonths());
            
           String ans = "";
           
           String y = (yInt == 1) ? "1 year" : yInt + " years" ; 
           String m = (mInt == 1) ? "1 month" : mInt + " months" ; 
           
           if (yInt == 0 && mInt == 0) return ans;
           if (yInt == 0) return m;
           if (mInt == 0) return y;
           
           ans = y + " and " + m;
           
           return ans;
            
            
        }catch (Exception e){
            return "Error";
        }
        
        
        
    }
}
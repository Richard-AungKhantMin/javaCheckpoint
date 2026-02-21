package gp1.AgeFinder;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class AgeFinder {
    public int calculateAge(String date) {
        
        try{
        // Implementation to calculate the age from given date
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate bd = LocalDate.parse(date, format);
        Period diff = Period.between(bd, LocalDate.now());
        int ans = diff.getYears();
        
       if (ans < 0){
           return -1;
       } 
        
        return ans;
         
        }catch (Exception e){
            return -1;
        }
    }
}

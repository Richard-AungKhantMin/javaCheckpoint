package gp1.AgeFinder;

import java.time.LocalDate;
import java.time.Period;

public class AgeFinder {
    public int calculateAge(String date) {
        try {
            LocalDate birthDate = LocalDate.parse(date);
            LocalDate today = LocalDate.now();
            Period period = Period.between(birthDate, today);
            int age = period.getYears();
            
            // Return -1 if age is negative (birth date in the future)
            return age < 0 ? -1 : age;
        } catch (Exception e) {
            return -1;
        }
    }
}
package gp1.TimeTracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectTime {
    private String startTime;
    private String endTime;
    private float hoursLogged;

    public ProjectTime(String start, String end){
        this.startTime = start;
        this.endTime = end;
        minDiff();
    }

    public void setStartTime(String start){
         this.startTime = start;
         minDiff();
    }
    
    public void setEndTime(String end){
        this.endTime = end;
        minDiff();
    }

    public String getStartTime(){
        return this.startTime;
    }
    
    public String getEndTime(){
        return this.endTime;
    }

public void minDiff(){
        
        try{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date start = format.parse(this.startTime);
        Date end = format.parse(this.endTime);
        
        Long milliS = start.getTime();
        Long milliE = end.getTime();
        
        Long diff = milliE - milliS;
        
        if (diff < 0) hoursLogged = -1;
        
        hoursLogged = diff / 60000f;
        
        }catch (ParseException e){
            hoursLogged = -1;
        }
        
    }


    public String getHoursLogged(){
        
        if (hoursLogged < 0) return "-1";
        
        if (hoursLogged < 120) return (int) hoursLogged + " m";
        
        float hours = hoursLogged / 60;
        if (hours < 120) return (int) hours + " h";
        
        float days = hours / 24;
        if (days < 120) return (int) days + " d";
        
        float months = days / 30;
        return (int) months + " mo";
        
    }
    
    
}
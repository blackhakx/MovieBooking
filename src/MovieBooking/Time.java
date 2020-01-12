/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// File name : Time.java

package MovieBooking;

import java.security.InvalidParameterException;


/**
 *
 * @author 91ken
 */
public class Time implements Comparable<Time>{
    private int hours;
    private int mins;
    private int secs;
    
    public Time(){
        
    }
    
    public Time(int hours) throws InvalidParameterException{
        if((hours < 0) || (hours > 23)){
            throw new InvalidParameterException("Invalid Hour Input!");
        }
        else{
            this.hours = hours;
        }
        this.mins = 0;
        this.secs = 0;
    }
    
    public Time(int hours, int mins){
        if((hours < 0) || (hours > 23)){
            throw new InvalidParameterException("Invalid Hour Input!");
        }
        else{
            this.hours = hours;
        }
        if((mins < 0) || (mins > 59)){
            throw new InvalidParameterException("Invalid Minute Input!");
        }
        else{
            this.mins = mins;
        }
        this.secs = 0;
    }
    
    public Time(int hours, int mins, int secs){
        if((hours < 0) || (hours > 23)){
            throw new InvalidParameterException("Invalid Hour Input!");
        }
        else{
            this.hours = hours;
        }
        if((mins < 0) || (mins > 59)){
            throw new InvalidParameterException("Invalid Minute Input!");
        }
        else{
            this.mins = mins;
        }
        if((secs < 0) || (secs > 59)){
            throw new InvalidParameterException("Invalid Seconds Input!");
        }
        else{
            this.secs = secs;
        }
    }

    public int getHours() {
        return hours;
    }
    
    public int getMins() {
        return mins;
    }
    
    public int getSecs() {
        return secs;
    }

    public void setHours(int hours) throws InvalidParameterException{
        if((hours < 0) || (hours > 23)){
            throw new InvalidParameterException("Invalid Hour Input!");
        }
        else{
            this.hours = hours;
        }
    }

    public void setMins(int mins) throws InvalidParameterException{
        if((mins < 0) || (mins > 59)){
            throw new InvalidParameterException("Invalid Minute Input!");
        }
        else{
            this.mins = mins;
        }
    }

    public void setSecs(int secs) throws InvalidParameterException{
        if((secs < 0) || (secs > 59)){
            throw new InvalidParameterException("Invalid Seconds Input!");
        }
        else{
            this.secs = secs;
        }
    }
    
    public boolean equals(Time otherTime){
        return ((this.hours ==otherTime.getHours()) && (this.mins == otherTime.getMins()) 
                && (this.secs == otherTime.getSecs()));
    }

    @Override
    public String toString() {
        return (String.format("%02d", hours)+ ":" + String.format("%02d", mins) + ":" + String.format("%02d", secs));
    }
    
    @Override
    public int compareTo(Time t) {
        int totalTime1 = (this.hours*60*60) + (this.mins*60) + this.secs;
        int totalTime2 = (t.getHours()*60*60) + (t.getMins()*60) + t.getSecs();
                
        if(this.equals(t)){return 0;}
        else if(totalTime1 < totalTime2){
           return -1;
        }
        else{
            return 1;
        }

            //else{return 0;}  if(totalTime1 > totalTime2)
        
    }
    
    /**
    // * Test Time methods
    public static void main(String[] args){
        Time time1 = new Time(10);
        Time time2 = new Time(10);
        Time time3 = new Time(9);
        Time time4 = new Time(11);
        //Time time5 = new Time(-1);
        //Time time6 = new Time(30);
        System.out.println(time1.compareTo(time2));
        System.out.println(time1.compareTo(time3));
        System.out.println(time1.compareTo(time4));
    }
    //* */
}

package com.mayukh.project.oop;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.Serializable;
import java.time.LocalTime;

public class Appointment implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 9169750245884617L;
	private String notes;
    public LocalDate date;
    public LocalTime time;
    public String appointmentType;
    public Appointment(){
    }
    
    public Appointment(String appointmentType, String date, String time, String notes) throws DateTimeParseException{
    	
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate givenDate = LocalDate.parse(date,formatDate);
    	this.date = givenDate;
    	
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH-mm-ss");
        LocalTime givenTime = LocalTime.parse(time,formatTime);
    	this.time = givenTime;   
        if(appointmentType.equals("Vet Visit") ||appointmentType.equals("Grooming")||appointmentType.equals("Vaccination")){
            this.appointmentType = appointmentType;
        }
        else{
            System.out.println("Select between Vet Visit/Vaccination/Grooming");
            System.out.println("restart appointment process");
        }
        this.notes = notes;
    }
    
    public void setAppointmentType(String appointmentType){
    	if(appointmentType.equals("Vet Visit") ||appointmentType.equals("Grooming")||appointmentType.equals("Vaccination")){
            this.appointmentType = appointmentType;
        }
        else{
            System.out.println("Select between Vet Visit/Vaccination/Grooming");
            System.out.println("restart appointment process");
        }
    }
    public void setDate(String date) throws DateTimeParseException{
    	
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate givenDate = LocalDate.parse(date,formatDate);
    	this.date = givenDate;
        
    }
    public void setTime(String time) throws DateTimeParseException{
    	
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH-mm-ss");
        LocalTime givenTime = LocalTime.parse(time,formatTime);
    	this.time = givenTime;
        
    }
    public void setNotes(String notes){
        this.notes = notes;
    }
    public LocalDate getDate(){
        return this.date;
    }
    public LocalTime getTime(){
        return this.time;
    } 
    public String getAppointmentType(){
        return this.appointmentType;
    }
    public String getNotes(){
        return this.notes;

    }
    public String toString(){
        return "[ Appointment Type:"+this.appointmentType+", Date:"+this.date+", Time:"+this.time+", Notes:"+this.notes+"]";
    }
}
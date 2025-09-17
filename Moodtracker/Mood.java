package com.mayukh.project.oop2;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.Serializable;


public class Mood implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9220303188222024023L;
	public String name;
	public LocalDateTime date;
	public LocalDateTime time; 
	public String notes;
	public Mood() {	
	}
	public Mood(String name, String date,String time,String notes) {
		this.name=name;
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		this.date = LocalDateTime.parse(date,format1);
		DateTimeFormatter format2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		this.time = LocalDateTime.parse(time,format2) ;
		this.notes = notes;
		
		
		
	}
	
	public void setName(String name) {
		this.name=name; 
	}
	public void setDate(String date) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		this.date = LocalDateTime.parse(date,format);
	}
	public void setTime(String time) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
		this.time = LocalDateTime.parse(time,format) ;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public String getName() {
		return this.name;
	}
	public LocalDateTime getDate() {
		return this.date;
	}
	public LocalDateTime getTime() {
		return this.time;
	}
	public String getNotes() {
		return this.notes;
	}
	public String toString() {
		return "[Mood Name:"+this.name+", Date:"+this.date+", "+this.time+", "+this.notes+"]";
	}
	
	

}

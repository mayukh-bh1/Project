package com.mayukh.project.oop;
import java.io.Serializable;
import java.time.LocalDate;


import java.util.ArrayList;
public class Pet implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2650345190049665281L;
	private int petID;
    public String name;
    public String breed;
    public int age;
    public String ownerName;
    public String contactInfo;
    public LocalDate registrationDate;
    public ArrayList <Appointment> listOfAppointments;
    public Pet() {
    	this.registrationDate = LocalDate.now();
    	this.listOfAppointments = new ArrayList<>();
    }

    public Pet(int petID, String name,String breed,int age,String ownerName,String contactInfo){
        this.petID=petID;
        this.name = name;
        this.breed = breed;
        this.age = age;
        if (contactInfo.matches("\\d{10}")) this.contactInfo= contactInfo;
        else {
            System.out.println("Invalid! Please enter exactly 10 digits.");
            System.out.println("Start registration process again");
        }
        this.ownerName = ownerName;
        this.contactInfo = contactInfo;
        this.registrationDate= LocalDate.now();    
        this.listOfAppointments = new ArrayList<>();
    }

    public void setPetID(int petID){
        this.petID = petID;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setBreed(String breed){
        this.breed = breed;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setOwnerName(String ownerName){
        this.ownerName = ownerName;
    }
    public void setContactInfo(String contactInfo){
    	if (contactInfo.matches("\\d{10}")) this.contactInfo= contactInfo;
        else {
            System.out.println("Invalid! Please enter exactly 10 digits.");
            System.out.println("Start registration process again");
        }
        
    }
    public void addAppointment(Appointment a){
        this.listOfAppointments.add(a);
    }
    public void setListOfAppointments(ArrayList<Appointment> listOfAppointments){
        this.listOfAppointments = listOfAppointments;
    }
    public int getPetID(){
        return this.petID;
    }
    public String getName(){
        return this.name;
    }
    public String getBreed(){
        return this.breed;
    }
    public int getAge(){
        return this.age;
    }
    public String getOwnerName(){
        return this.ownerName;
    }
    public String getContactInfo(){
        return this.contactInfo;
    }
    public LocalDate getRegistrationDate(){
        return this.registrationDate;
    }
    public ArrayList<Appointment> getListOfAppointments(){
        return this.listOfAppointments;
    }
    public String toString() {
    	return "[ Pet ID:"+this.petID+", Name:"+this.name+", Breed:"+this.breed+", Age:"+this.age+",Owner Name:"+
    this.ownerName+",Contact:"+this.contactInfo+",Registration date:"+this.registrationDate+
    ",No. of Appointments:"+((this.listOfAppointments==null)?0:this.listOfAppointments.size())+"]";
    }
}

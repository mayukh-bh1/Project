package com.mayukh.project.oop;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.io.File;
public class PetCareSchedular {

	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) {
		Scanner scanner =new Scanner(System.in);
		ArrayList<Pet> pets = new ArrayList<>();
		ArrayList<Appointment> appointments = new ArrayList<>();
		HashMap <Pet,ArrayList<Appointment>> table = new HashMap<>();
		boolean t= true;
		
		while(t==true) {
		
			try {
				
				File file = new File("Pet.ser");
				if(file.exists() && file.length()>0) {
					FileInputStream fileIn = new FileInputStream("Pet.ser");
					ObjectInputStream in = new ObjectInputStream(fileIn); 
					pets = (ArrayList<Pet>)in.readObject();
					in.close();
				}
				File file2 = new File("Appointment.ser");
				if(file2.exists() && file2.length()>0) {
					FileInputStream fileIn2 = new FileInputStream("Appointment.ser");
					ObjectInputStream in2 = new ObjectInputStream(fileIn2); 
					appointments =(ArrayList<Appointment>) in2.readObject();
					in2.close();
				}
				File file3 = new File("Appointment+Pet.ser");
				if(file3.exists() && file3.length()>0) {
					FileInputStream fileIn3 = new FileInputStream("Appointment+Pet.ser");
					ObjectInputStream in3 = new ObjectInputStream(fileIn3);
					table = (HashMap <Pet,ArrayList<Appointment>>) in3.readObject();
					in3.close();
				}
				
				System.out.println("Welcome,Pet owners often forget important tasks such as vet visits, vaccinations, and grooming appointments. Paws & Whiskers wants to provide a simple console-based app to help them organize all their pet care needs in one place.");
				System.out.println("To register a pet, press 1");
				System.out.println("To schedule an appointer press 2");
				System.out.println("To store data in file press 3");
				System.out.println("To see details of pets and appointments press 4");
				System.out.println("get reports press 5");
				System.out.println("to exit press 6");
				int x = scanner.nextInt();
				switch(x) {
				case 1 :
					pets.add(registerPet(pets,scanner));
					break;
				case 2:
					Appointment a = scheduleAppointment(scanner,pets);
					appointments.add(a);
					storeLocal(a,scanner,pets,appointments,table);	
					break;
				case 3:
					storeData(pets,appointments,table);
					break;
				case 4:
					seeDetails(scanner,pets,appointments,table);
					break;
				
				case 5:
					getReports(scanner,pets,appointments,table);
					break;
				case 6:
					t=false;
					break;
				default:
					System.out.println("Enter valid input");
				}
				
				
				
			}
			catch(NumberFormatException e) {
				System.out.println(e.getMessage());
			}
			catch(DuplicateIDException|PetIDNotRegisteredException dide) {
				System.out.println(dide.getMessage());
			}
			catch(FileNotFoundException s) {
				System.out.println(s.getMessage());
			}
			catch(DateTimeException dte) {
				System.out.println(dte.getMessage());
			}
			catch(IOException|ClassNotFoundException e) {
				System.out.println(e.getMessage());	
			}
			
		}
		scanner.close();
	
		
	}
	public static Pet registerPet(ArrayList<Pet> pets,Scanner scanner) throws DuplicateIDException,NumberFormatException,DateTimeException{
		System.out.println("Enter Unique Pet ID (in the Format '100001'):");
		int y = scanner.nextInt();
		int k = -1;
		for (int i = 0 ; i<pets.size();i++) {
			if (y==pets.get(i).getPetID()) {
				k=i;	
			}
		}
		if (k==-1) ;
		else {
			throw new DuplicateIDException("Enter Unique pet ID(Previous one already exist)");
		}
		Pet m = new Pet();
		m.setPetID(y);
		scanner.nextLine();
		System.out.println("Enter pet Name:");
		m.setName(scanner.nextLine());
		System.out.println("Enter pet age:");
		m.setAge(scanner.nextInt());
		scanner.nextLine();
		System.out.println("Enter pet breed:");
		m.setBreed(scanner.nextLine());
		System.out.println("Enter Owner Name:");
		m.setOwnerName(scanner.nextLine());
		System.out.println("Enter Owner Contact Info:");
		m.setContactInfo(scanner.nextLine());
		System.out.println("registration done");
		return m;
		
	}
	public static Appointment scheduleAppointment(Scanner scanner,ArrayList<Pet> pets) throws DateTimeException,PetIDNotRegisteredException{
		System.out.println("Enter Pet ID for appointment");
		int inputID =scanner.nextInt();
		int yu =1;
		for (Pet p :pets) {
			if (p.getPetID()==inputID) {
				yu=-1;
				break;
			}
		}
		if(yu==1) {
			throw new PetIDNotRegisteredException("pet ID not registered. Enter correct pet ID register first");
		}
		Appointment m = new Appointment();
		scanner.nextLine();
		System.out.println("Select Appointment Type.Select between Vet Visit/Vaccination/Grooming");
		m.setAppointmentType(scanner.nextLine());
		System.out.println("Write date in this format:dd-MM-yyyy:");
		m.setDate(scanner.nextLine());
		System.out.println("Write time in this format:HH-mm-ss:");
		m.setTime(scanner.nextLine());
		System.out.println("Write notes");
		m.setNotes(scanner.nextLine());
		System.out.println("appointment fixed");
		return m;	
	}
	public static void storeData(ArrayList<Pet> pets, ArrayList<Appointment> appointments,HashMap<Pet,ArrayList<Appointment>> table ) throws IOException{
		FileOutputStream fileIn = new FileOutputStream("Pet.ser");
		ObjectOutputStream in = new ObjectOutputStream(fileIn); 
		in.writeObject(pets);
		FileOutputStream fileIn2 = new FileOutputStream("Appointment.ser");
		ObjectOutputStream in2 = new ObjectOutputStream(fileIn2); 
		in2.writeObject(appointments);
		FileOutputStream fileIn3 = new FileOutputStream("Appointment+Pet.ser");
		ObjectOutputStream in3 = new ObjectOutputStream(fileIn3);
		in3.writeObject(table);
		in.close();
		in2.close();
		in3.close();
		System.out.println("Data stored successfully");
		
	}
	public static void seeDetails(Scanner scanner,ArrayList<Pet> pets, ArrayList<Appointment> appointments,HashMap<Pet,ArrayList<Appointment>> table)  {
		System.out.println("We Show the following information based on the option chosen\n"+
				"Press 1 for All registered pets\n"+
				"Press 2 for All appointments for a specific pet\n"+
				"Press 3 for Upcoming appointments for all pets\n"+
				"Press 4 for Past appointment history for each pet");
		int i = scanner.nextInt();
		LocalDate d1 = LocalDate.now();
		LocalTime t1 = LocalTime.now();
		if(i==1) {
			for (Pet y :pets) {
				System.out.println(y.toString());
			}
			System.out.println("All registered pets");	
		}
		else if (i==2) {
			System.out.println("Enter petID:");
			int e = -1;
			int inputID = scanner.nextInt();
			Pet w =null;
			for(Pet p:pets) {
				if (p.getPetID()==inputID) {
					e =1;
					w= p;
					break;
				}
			}
			if(e==1) {
				for(Appointment l:w.getListOfAppointments()) {
					System.out.println(l.toString());
				}
				System.out.println("Appointments for petID:"+inputID);
				
			}
			else {
				System.out.println("pet ID not registered . register first.");
			}
			
		}
		else if(i==3) {
			for(Pet key: table.keySet()) {
				System.out.println(key.toString());
				for(Appointment q: table.get(key)) {
					if(ChronoUnit.DAYS.between(d1, q.getDate())>=0 && ChronoUnit.MINUTES.between(t1, q.getTime())<0 ) {
						System.out.println(q.toString());
					}
				}
			}	
		}
		else if(i==4) {
			for(Pet key: table.keySet()) {
				System.out.println(key.toString());
				for(Appointment q: table.get(key)) {
					if(ChronoUnit.DAYS.between(d1, q.getDate())<=0 && ChronoUnit.MINUTES.between(t1, q.getTime())>0 ) {
						System.out.println(q.toString());
					}
				}
			}
		}
		else {
			System.out.println("invalid input");
		}
		
	}
	public static void getReports(Scanner scanner,ArrayList<Pet> pets, ArrayList<Appointment> appointments,HashMap<Pet,ArrayList<Appointment>> table) {
		System.out.println("Produce simple reports including:"+
				"Press 1 for Pets with upcoming appointments in the next week"+
				"Press 2 for Pets overdue for a vet visit (For example: No vet visit in the last 6 months");
		int i = scanner.nextInt();
		LocalDate d1 = LocalDate.now();
		LocalTime t1 = LocalTime.now();
		if(i==1) {
			for(Pet key: table.keySet()) {
				System.out.println(key.toString());
				for(Appointment q: table.get(key)) {
					if(ChronoUnit.DAYS.between(d1, q.getDate())>=0 && ChronoUnit.MINUTES.between(t1, q.getTime())<0 && ChronoUnit.DAYS.between(d1, q.getDate())<7) {
						
						System.out.println(q.toString());
					}
				}
			}
			
		}
		else if(i==2) {
			int k =-1;
			for(Pet key:table.keySet()) {
				for(Appointment q: table.get(key)) {
					if(q.getAppointmentType().equals("Vet Visit") && ChronoUnit.MONTHS.between(q.getDate(),d1)<=6) {
						k=1;	
					}
				}
				
			}
			if(k==-1) {
				System.out.println("overdue- no vet visit for 6 months . Please make an appointment");
				for(Pet key:table.keySet()) {
					System.out.println(key.toString());
				}
			}
			
			
		}
		else {
			System.out.println("invalid input");
		}
		
		
		
	}
	public static void storeLocal(Appointment a,Scanner scanner,ArrayList<Pet> pets, ArrayList<Appointment> appointments,HashMap<Pet,ArrayList<Appointment>> table) throws PetIDNotRegisteredException {
		System.out.println("Enter that pet ID again");
		int inputID = scanner.nextInt();
		int yu =1;
		for (Pet p :pets) {
			if (p.getPetID()==inputID) {
				p.addAppointment(a);
				if (table.containsKey(p)) {
				    table.get(p).add(a);
				} else {
				    ArrayList<Appointment> list = new ArrayList<>();
				    list.add(a);
				    table.put(p, list);
				}
				yu=-1;
				System.out.println("Appointment done");
				break;
			}
		}
		if(yu==1) {
			throw new PetIDNotRegisteredException("pet ID not registered. Enter correct pet ID register first");
		}
		
		
	}

}

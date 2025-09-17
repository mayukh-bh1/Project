package com.mayukh.project.oop2;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MoodTracker {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		boolean t = true;
		ArrayList<Mood> moods = new ArrayList<>(); 
		while(t==true) {
			try {
				File file = new File("Mood.ser");
				if(file.exists() && file.length()>0) {
					FileInputStream fileIn = new FileInputStream("Mood.ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					moods = (ArrayList<Mood>)in.readObject();
					in.close();
				}
				System.out.println("Menu for the user to (Press a)Add Mood,(Press d)Delete Mood,(Press e)Edit Mood (Press s)Search Mood (Press w)Write mood to a file.(Press E)Exit the program");
				char input = scanner.next().charAt(0);
				switch(input) {
				case 'a':
					addMood(scanner,moods);
					break;
				case 'd':
					deleteMood(scanner,moods);
					break;
				case 'e':
					editMood(scanner,moods);
					break;
				case 's':
					searchMood(scanner,moods);
					break;
				case 'w':
					writeMoods(moods);
					break;
				case 'E':
					t=false;
					break;
				default:
					System.out.println("Invalid input");
				}
			}
			catch(MoodInvalidException | DateTimeParseException | NumberFormatException | IOException |ClassNotFoundException e) {
				System.out.println(e.getMessage());
				
			}
		}
		scanner.close();

	}
	public static void addMood(Scanner scanner,ArrayList<Mood> moods) throws MoodInvalidException,DateTimeParseException{
		Mood newMood = new Mood();
		System.out.println("Write mood name");
		String moodName = scanner.nextLine();
		newMood.setName(moodName);
		System.out.println("Write date in the format 'dd-MM-yyyy'");
		String moodDate = scanner.nextLine();
		newMood.setDate(moodDate);
		System.out.println("Write time in the format 'HH:mm:ss'");
		String moodTime = scanner.nextLine();
		newMood.setTime(moodTime);
		for(Mood mood:moods) {
			if(mood.getTime().equals(newMood.getTime()) && mood.getDate().equals(newMood.getDate())) {
				if(mood.getName().equals(newMood.getName())) {
					throw new MoodInvalidException("Same Mood is available at the same time and date. it cannot exist.Restart mood addition process.");
				}
			}
		}
		System.out.println("Write Mood description");
		String moodNotes = scanner.nextLine();
		newMood.setNotes(moodNotes);
		moods.add(newMood);
		System.out.println("Mood added successfully");	
	}
	public static Mood searchMoodByDetails(Scanner scanner, ArrayList<Mood> moods) throws DateTimeParseException {
		Mood searchMood = null;
		scanner.nextLine();
		System.out.println("Write mood name");
		String moodName = scanner.nextLine();
		System.out.println("Write date in the format 'dd-MM-yyyy'");
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String moodDate = scanner.nextLine();
		LocalDateTime date = LocalDateTime.parse(moodDate,format1);
		System.out.println("Write time in the format 'HH:mm:ss'");
		DateTimeFormatter format2 = DateTimeFormatter.ofPattern("HH-mm-ss");
		String moodTime = scanner.nextLine();
		LocalDateTime time = LocalDateTime.parse(moodTime,format2);
		for(Mood mood:moods) {
			if(mood.getDate().equals(date) && mood.getTime().equals(time) && mood.getName().equals(moodName)) {
				searchMood = mood;
			}
		}
		return searchMood;
		
		
	}
	public static ArrayList<Mood> searchMoodByDate(Scanner scanner,ArrayList<Mood> moods) throws DateTimeParseException{
		ArrayList<Mood> searchMoods = new ArrayList<>();
		scanner.nextLine();
		System.out.println("Write date in the format 'dd-MM-yyyy'");
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String moodDate = scanner.nextLine();
		LocalDateTime date = LocalDateTime.parse(moodDate,format1);
		for (Mood mood:moods) {
			if(mood.getDate().equals(date)) {
				searchMoods.add(mood);
			}
		}
		return searchMoods;
		
		
		
	}
	public static void searchMood(Scanner scanner, ArrayList<Mood> moods) throws NumberFormatException,DateTimeParseException{
		System.out.println("Press 1 to search by name,mood and time.");
		System.out.println("Press 2 to search by date. It will show each mood in that date.");
		Mood afterSearchMood = null;
		ArrayList<Mood> afterSearchMoods = new ArrayList<>();
		int input= scanner.nextInt();
		switch(input) {
		case 1:
			afterSearchMood = searchMoodByDetails(scanner,moods);
			break;
		case 2:
			afterSearchMoods = searchMoodByDate(scanner,moods);
			break;
		default:
			System.out.println("Invalid input");
		}
		if (afterSearchMood == null && afterSearchMoods.size()==0) {
			System.out.println("Mood not found");
		}
		else if(afterSearchMood != null) {
			System.out.println(afterSearchMood.toString());
		}
		else if(afterSearchMoods.size()!=0) {
			for(Mood mood:afterSearchMoods) {
				System.out.println(mood.toString());
			}
		}
		System.out.println("search operation complete");
		
	}
	public static void deleteMood(Scanner scanner, ArrayList<Mood> moods) throws NumberFormatException,DateTimeParseException{
		System.out.println("Press 1 to search by name,mood and time.It will delete that mood");
		System.out.println("Press 2 to search by date. It will delete every mood in that date. ");
		Mood afterSearchMood = null;
		ArrayList<Mood> afterSearchMoods = new ArrayList<>();
		int input= scanner.nextInt();
		switch(input) {
		case 1:
			afterSearchMood = searchMoodByDetails(scanner,moods);
			break;
		case 2:
			afterSearchMoods = searchMoodByDate(scanner,moods);
			break;
		default:
			System.out.println("Invalid input");
		}
		if (afterSearchMood == null && afterSearchMoods.size()==0) {
			System.out.println("Mood not found");
		}
		else if(afterSearchMood != null) {
			for (Mood mood: moods) {
				if(afterSearchMood.equals(mood)) moods.remove(mood);
			}
		}
		else if(afterSearchMoods.size()!=0) {
			for(Mood mood1:afterSearchMoods) {
				for (Mood mood2: moods) {
					if(mood1.equals(mood2)) moods.remove(mood2);
				}
				
			}
		}
		System.out.println("Delete operation complete");
		
	}
	public static void editMood(Scanner scanner,ArrayList<Mood> moods) throws DateTimeParseException{
		Mood afterSearchMood = null;
		scanner.nextLine();
		System.out.println("Which mood do you want to edit");
		afterSearchMood = searchMoodByDetails(scanner,moods);
		if (afterSearchMood == null) System.out.println("Mood not found");
		else {
			for (Mood mood: moods) {
				if (afterSearchMood.equals(mood)) moods.remove(mood);
			}
			System.out.println("Mood found. You can edit its description/notes");
			String notes = scanner.nextLine();
			afterSearchMood.setNotes(notes);
			moods.add(afterSearchMood);
			System.out.println("Edit process done");
		}	
	}
	public static void writeMoods(ArrayList<Mood> moods) throws IOException{
		FileOutputStream fileIn = new FileOutputStream("Mood.ser");
		ObjectOutputStream in = new ObjectOutputStream(fileIn);
		in.writeObject(moods);
		in.close();	
	}

}

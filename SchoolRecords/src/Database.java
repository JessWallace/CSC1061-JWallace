import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
//Data members
	private static final String PERSON_DATABASE_FILE = "PersonDatabase.txt";

	public Database() {
		try {
			File myFile = new File(PERSON_DATABASE_FILE);
			if (myFile.createNewFile()) {
				System.out.println("File created: " + myFile.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

//Methods
	public void writePerson(Person p) {
		
			try {
				FileWriter pWrite = new FileWriter(PERSON_DATABASE_FILE, true);
				pWrite.write(p.toString());
				System.out.println("Written to database: " + p.toString());
				pWrite.flush();
				pWrite.close();
			} catch (IOException e) {
				System.out.println("An error occured in writing.");
				e.printStackTrace();
			}
		
	}

	public ArrayList<String> readDatabase() {
		ArrayList<String> people = new ArrayList<>();
		File readFile = new File(PERSON_DATABASE_FILE);
		Scanner scnr = null;
		try {
			scnr = new Scanner(readFile);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(-1);
		}

		while (scnr.hasNextLine()) {
			String line = scnr.nextLine();
			people.add(line);
		}
		return people;
	}
	
	public void clearDatabase() {
		try (FileWriter writer = new FileWriter(PERSON_DATABASE_FILE, false)){
			System.out.println("Database cleared manually.");
		} catch (IOException e) {
			System.out.println("Error clearing database.");
			e.printStackTrace();
		}
	}
}

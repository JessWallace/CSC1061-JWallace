import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
//Data members
	static final String PERSON_DATABASE_FILE = "PersonDatabase.txt";
	static File outFile = new File(PERSON_DATABASE_FILE);
	static PrintWriter pWrite;
	static Scanner scnr;

	public Database() throws IOException {
		if (outFile.createNewFile()) {
			System.out.println("File created: " + outFile.getName());
		} else {
			System.out.println("File already exists.");
		}
	}

//Methods
	public void writePerson(Person p) throws PersonObjectDBException {
		if (pWrite == null) {
			try {
				pWrite = new PrintWriter((outFile));
			} catch (FileNotFoundException e) {
				throw new PersonObjectDBException("Database Creation Error.");
			}
		}
		pWrite.write(p.toString());
		System.out.println("Written to database: " + p.toString());
		pWrite.flush();

	}

	public static Person readPerson() throws FileNotFoundException {
		if (scnr == null) {
			scnr = new Scanner(outFile);
		}
		if (scnr.hasNextLine()) {
			String line = scnr.nextLine();
			String[] token = line.split(",");
			// removes extra spaces
			for (int i = 0; i < token.length; i++) {
				token[i] = token[i].trim();
			}
			if (token[0].equals("Faculty")) {
				Faculty faculty = new Faculty(token[1], token[2], Long.parseLong(token[3]), token[4], token[5],
						Double.parseDouble(token[6]), token[7], token[8], token[9]);
				return faculty;
			}
			if (token[0].equals("Staff")) {
				Staff staff = new Staff(token[1], token[2], Long.parseLong(token[3]), token[4], token[5],
						Double.parseDouble(token[6]), token[7], token[8]);
				return staff;
			}
			if (token[0].equals("Student")) {
				Student student = new Student(token[1], token[2], Long.parseLong(token[3]), token[4], token[5]);
				return student;
			}
		}
		return null;
	}

	public List<Person> readDatabase() throws FileNotFoundException {
		List<Person> peopleList = new ArrayList<>();
		Person people = null;
		try {
			while ((people = readPerson()) != null) { // calling read method and if not empty, put the object into the
														// list
				peopleList.add(people);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return peopleList;

	}

	public void clearDatabase() {
		try (FileWriter writer = new FileWriter(PERSON_DATABASE_FILE, false)) {
			System.out.println("Database cleared manually.");
		} catch (IOException e) {
			System.out.println("Error clearing database.");
			e.printStackTrace();
		}
	}
}

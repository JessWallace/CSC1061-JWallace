/**
 * A program to write in employees to a database, then read them out. Jessika
 * Wallace - CSC1061
 */

public class TestSchoolRecords {

	public static void main(String[] args) {
		/**
		 * New Database to hold employees
		 */
		Database database = new Database();
		/**
		 * Create 2 student objects and write into database.
		 */
		Student st1 = new Student("Abby Apple", "123 Abraham Avenue", 1111111, "AApple@student.edu", "Freshman");
		database.writePerson(st1);
		Student st2 = new Student("Billy Banana", "234 Billings Boulevard", 2222222, "BBanana@student.edu",
				"Sophomore");
		database.writePerson(st2);
		/**
		 * Create 2 Faculty objects and write into database.
		 */
		Faculty f1 = new Faculty("Candice Carrot", "345 Collins Court", 3333333, "CandiceCarrot@employee.edu", "C0123",
				60000, "June 1, 2023", "M-W-F 12-1PM", "Junior");
		database.writePerson(f1);
		Faculty f2 = new Faculty("Daniel Durian", "456 Dunham Drive", 4444444, "DanielDurian@employee.edu", "D0234",
				95000, "June 1, 2018", "M 9-10AM", "Senior");
		database.writePerson(f2);
		/**
		 * Create 2 Staff objects and write into database.
		 */
		Staff s1 = new Staff("Elsie Eggplant", "567 Emerson Street", 5555555, "ElsieEggplant@employee.edu", "E0345",
				75000, "August 15, 2020", "Human Resources Administrator");
		database.writePerson(s1);
		Staff s2 = new Staff("Fanny Fig", "578 Fredrick Way", 6666666, "FannyFig@employee.edu", "F0456", 198000,
				"November 15, 2015", "Chairman");
		database.writePerson(s2);
		/**
		 * Now read back all the entries from the database.
		 */
		System.out.println("Reading back Database.\n");
		for (String person : database.readDatabase()) {
			System.out.println(person);
		}
		/**
		 * Clear out the database and close the program so no duplicate entries.
		 */
		System.out.println("\nClearing Database.");
		database.clearDatabase();
	}

}

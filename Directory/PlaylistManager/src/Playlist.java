import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Playlist {

	public static void main(String[] args) {
		// Data Members
		MyDoubleLinkedList<Song> playlist = new MyDoubleLinkedList<Song>();
		Scanner scnr = new Scanner(System.in);
		System.out.println("Hello and Welcome to your Playlist.");
		// Methods

		boolean again = true;

		while (again) {

			System.out.println("Please enter a number to select from the options below.\nMain Menu:\n1. Add a song\n"
					+ "2. Remove a song\n3. Total number of songs\n4. Play playlist\n5. Shuffle playlist\n6. Reverse playlist\n"
					+ "7. Save Playlist\n8. Load a previously saved playlist\n9. Quit");

			int input = scnr.nextInt();
			scnr.nextLine();
			switch (input) {
			case 1:
				System.out.print("Input the Title: \n");
				String title = scnr.nextLine();
				System.out.print("Input the Artist: \n");
				String artist = scnr.nextLine();

				Song s1 = new Song(artist, title);
				playlist.add(s1);
				break;
			case 2:
				System.out.println("Remove most recently added song: " + playlist.getLast() + "?");
				String ans = scnr.next();
				while (!ans.equalsIgnoreCase("yes") && !ans.equalsIgnoreCase("no")) {
					System.out.println("You must input yes or no.");
					ans = scnr.next();
				}
				if (ans.equalsIgnoreCase("yes")) {
					playlist.removeLast();
					System.out.println("Song removed.");
					break;
				}
				System.out.println("Input the Song's number you wish to remove: ");
				int index = scnr.nextInt();
				playlist.remove(index);
				break;
			case 3:
				System.out.println("Total songs: " + playlist.count());
				break;
			case 4:
				if (playlist.isEmpty()) {
					System.out.println("Playlist is empty.");
				} else {
					playlist.assignIndexes();
					for (int i = 0; i < playlist.size(); i++) {
						System.out.println(playlist.get(i));
					}
				}
				break;
			case 5:
				shuffle(playlist);
				System.out.println("Playlist shuffled!");
				again = true;
				break;
			case 6:
				if (playlist.isEmpty()) {
					System.out.println("Playlist is empty. Nothing to reverse.");
				} else {
					playlist.reverse(); // Assuming your list class has this method
					System.out.println("Playlist reversed!");
				}
				break;
			case 7:
				System.out.print("Enter filename to save (e.g., myplaylist.txt): ");
				String saveFile = scnr.nextLine();

				try (FileWriter writer = new FileWriter(saveFile)) {
					for (int i = 0; i < playlist.size(); i++) {
						Song song = playlist.get(i);
						writer.write(song.getArtist() + "|" + song.getTitle() + "\n");
					}
					System.out.println("Playlist saved to " + saveFile);
				} catch (IOException e) {
					System.out.println("Error saving playlist: " + e.getMessage());
				}
				break;
			case 8:
				System.out.print("Enter filename to load from (e.g., myplaylist.txt): ");
				String loadFile = scnr.nextLine();

				try (BufferedReader reader = new BufferedReader(new FileReader(loadFile))) {
					playlist.clear();
					String line;

					while ((line = reader.readLine()) != null) {
						String[] parts = line.split("\\|");
						if (parts.length == 2) {
							artist = parts[0].trim();
							title = parts[1].trim();
							playlist.add(new Song(artist, title));
						}
					}

					System.out.println("Playlist loaded from " + loadFile);
				} catch (IOException e) {
					System.out.println("Error loading playlist: " + e.getMessage());
				}
				break;
			case 9:
			    System.out.println("Goodbye!");
			    again = false;
			    break;
			default:
				System.out.println("Invalid input. Try again.");
				break;
			}
		}

	}

	public static void shuffle(MyDoubleLinkedList<Song> playlist) {
		if (playlist.size() <= 1) {
			return;
		}
		Random rand = new Random();
		ArrayList<Song> tempList = new ArrayList<>();

		for (int i = 0; i < playlist.size(); i++) {
			tempList.add(playlist.get(i));
		}
		for (int i = tempList.size() - 1; i > 0; i--) {
			int j = rand.nextInt(i + 1);
			Song temp = tempList.get(i);
			tempList.set(i, tempList.get(j));
			tempList.set(j, temp);
		}
		playlist.clear();
		for (Song song : tempList) {
			playlist.add(song);
		}
	}

}

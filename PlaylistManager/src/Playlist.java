import java.util.Random;

public class Playlist {

	public static void main(String[] args) {
		//Data Members
		MyDoubleLinkedList playlist = new MyDoubleLinkedList();
		
		//Methods
		System.out.println("Hello and Welcome to your Playlist.");
		System.out.println("Please enter a number to select from the options below.\nMain Menu:\n1. Add a song\n"
				+ "2. Remove a song\n3. Total number of songs\n4. Play playlist\n5. ");

	}
	public int shuffle() {	
		Random rand = new Random();
		return rand.nextInt();
	}

}

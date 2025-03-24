
public class Song {
	private String artist;
	private String title;
	
	//Getters and Setters
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
// Constructor
	public Song(String artist, String title) {
		this.artist = artist;
		this.title = title;
	}
	//Methods
	@Override
	public String toString() {
		return "Song [artist=" + artist + ", title=" + title + "]";
	}
	
}

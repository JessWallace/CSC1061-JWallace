package test;

public class BookCreator {

	public static void main(String[] args) {
		
		BookTree book = new BookTree("Trees for Dummies");
		
		book.addBookNode("Chapter 5", 5, 0, 0);	
		book.addBookNode("Chapter 1", 1, 0, 0);
		book.addBookNode("Chapter 4", 4, 0, 0);
		book.addBookNode("Chapter 2", 2, 0, 0);
		book.addBookNode("Chapter 3", 3, 0, 0);
		
		book.addBookNode("Section 5.5", 5, 5, 0);
		book.addBookNode("Section 4.4", 4, 4, 0);
		book.addBookNode("Section 3.3", 3, 3, 0);
		book.addBookNode("Section 1.1", 1, 1, 0);
		book.addBookNode("Section 2.2", 2, 2, 0);
		
		book.addBookNode("SubSection 2.2.5", 2, 2, 5);
		book.addBookNode("SubSection 5.5.3", 5, 5, 3);
		book.addBookNode("SubSection 1.1.5", 1, 1, 5);
		book.addBookNode("SubSection 4.4.6", 4, 4, 6);
		book.addBookNode("SubSection 3.3.4", 3, 3, 4);

		for(BookNode node: book) {
			System.out.println(node);
		}
	}

}

package library.service;

import java.util.List;

import library.entity.Authors;
import library.entity.BookAuthor;
import library.entity.Books;

public interface BookAuthorService {

    void save(BookAuthor bookAuthor);
	
    BookAuthor findById(Integer id);
	
    List<BookAuthor> findBooksByAuthor(String name);
	
	List<BookAuthor> getAll();
	
	void deleteById(Integer id);
	
	void updateBookAuthor(BookAuthor bookAuthor);
	
	BookAuthor bookAuthorByNames(String authorName, String bookName);
	
	List<Books> booksOfActiveAuthors(); 
	
	List<Authors> activeAuthor();
	
}

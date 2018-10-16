package library.service;

import java.util.List;

import library.entity.Books;

public interface BookService {

	void save(Books book);
	
	Books findById(Integer id);
	
	List<Books> findAll();
	
	void deleteById(Integer id);
	
	void updateBook(Books book);
	
	Books findByName(String name);
	
	Integer numberBooksByGenre(String genre);
	
	
}

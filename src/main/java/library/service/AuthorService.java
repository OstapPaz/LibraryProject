package library.service;

import java.util.List;

import library.entity.Authors;

public interface AuthorService {

	void save(Authors author);
	
	Authors findById(Integer id);
	
	Authors findByName(String name);
	
	List<Authors> getAll();
	
	void deleteById(Integer id);
	
	void updateAuthor(Authors author);
	
	List<Authors> findOlderAuthors();
	
	
}

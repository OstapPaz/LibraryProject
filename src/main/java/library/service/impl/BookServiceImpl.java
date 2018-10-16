package library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import library.entity.Books;
import library.repository.BookRepository;
import library.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	
	@Autowired BookRepository br;
	
	@Override
	public void save(Books book) {
		br.save(book);
		
	}

	@Override
	public Books findById(Integer id) {
		return br.getOne(id);
	}

	@Override
	public List<Books> findAll() {
		return br.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		br.deleteById(id);
	}

	@Override
	public void updateBook(Books book) {
		br.save(book);
		
	}

	@Override
	public Books findByName(String name) {
		return br.findByName(name);
	}

	@Override
	public Integer numberBooksByGenre(String genre) {
		return br.numderBooksByGenre(genre);
	}

	
	

	
	
	
}

package library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import library.entity.Authors;
import library.entity.BookAuthor;
import library.entity.Books;
import library.repository.BookAuthorRepository;
import library.service.BookAuthorService;

@Service
@Transactional
public class BookAuthorServiceImpl implements BookAuthorService {

	@Autowired BookAuthorRepository bar;
	
	@Override
	public void save(BookAuthor bookAuthor) {
		bar.save(bookAuthor);
		
	}

	@Override
	public BookAuthor findById(Integer id) {
		return bar.getOne(id);
	}

	@Override
	public List<BookAuthor> findBooksByAuthor(String authorName) {
		return bar.findAuthorBooks(authorName);
	}

	@Override
	public List<BookAuthor> getAll() {
		return bar.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		bar.deleteById(id);
		
	}

	@Override
	public void updateBookAuthor(BookAuthor bookAuthor) {
		bar.save(bookAuthor);	
	}

	@Override
	public BookAuthor bookAuthorByNames(String authorName, String bookName) {
		return bar.bookAuthorByNames(authorName, bookName);
	}

	@Override
	public List<Books> booksOfActiveAuthors() {
		return bar.findBookThatAuthorHaveMoreBook();
	}

	@Override
	public List<Authors> activeAuthor() {
		return bar.findAuthorWithMostBook();
	}
	
	

	
	
}

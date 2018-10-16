package library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import library.entity.Authors;
import library.entity.BookAuthor;
import library.entity.Books;
import library.service.BookAuthorService;

@RestController
public class BookAuthorController {

@Autowired BookAuthorService bas;
	
	@GetMapping("/bookauthor/")
	public ResponseEntity<List<BookAuthor>> listAllBooksAuthors() {
		List<BookAuthor> bookAuthor = bas.getAll();
		if (bookAuthor.isEmpty()) {
			return new ResponseEntity<List<BookAuthor>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<BookAuthor>>(bookAuthor, HttpStatus.OK);
	}
	
	@GetMapping(value = "/bookauthor/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, "application/json; charset=UTF-8"})
	public ResponseEntity<BookAuthor> getBookAuthor(@PathVariable("id") Integer id) {
		System.out.println("Searching book author with id " + id);
		BookAuthor bookAuthor = bas.findById(id);
		if (bookAuthor == null) {
			System.out.println("BookAuthor with id " + id + " not found");
			return new ResponseEntity<BookAuthor>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookAuthor>(bookAuthor, HttpStatus.OK);
	}
	
	@PostMapping("/bookauthor/")
	public ResponseEntity<Void> createBookAuthor(@RequestBody BookAuthor bookAuthor, 
			UriComponentsBuilder ucBuilder) {
		System.out.println("Creating bookAuthor " + bookAuthor.getBook().getName() + 
				" with author " + bookAuthor.getAuthor().getName() );
		
		if(bas.bookAuthorByNames(bookAuthor.getAuthor().getName(),
				bookAuthor.getBook().getName()) != null) {
			System.out.println("This BookAuthor already exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		bas.save(bookAuthor);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/bookauthor/{id}").buildAndExpand(bookAuthor.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/bookauthor/{id}")
	public ResponseEntity<BookAuthor> updateBookAuthor(@PathVariable("id") Integer id, 
			@RequestBody BookAuthor bookAuthor) {
		if (bas.findById(id) == null) {
			System.out.println("BookAuthor with id " + id + " not found");
			return new ResponseEntity<BookAuthor>(HttpStatus.NOT_FOUND);
		}
		
		bas.save(bookAuthor);
		return new ResponseEntity<BookAuthor>(bookAuthor, HttpStatus.OK);
	}
	
	@DeleteMapping("/bookauthor/{id}")
	 public ResponseEntity<BookAuthor> deleteBookAuthor(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting bookAuthor with id " + id);
 
        BookAuthor bookAuthor = bas.findById(id);
        if (bookAuthor == null) {
            System.out.println("Unable to delete. BookAuthor with id " + id + " not found");
            return new ResponseEntity<BookAuthor>(HttpStatus.NOT_FOUND);
        }
 
        bas.deleteById(id);
        return new ResponseEntity<BookAuthor>(HttpStatus.NO_CONTENT);
    }
	
	@GetMapping("/activeauthorsbook/")
	public ResponseEntity<List<Books>> booksOfActiveAuthors() {
		List<Books> books = bas.booksOfActiveAuthors();
		
		if(books.isEmpty()) {
			return new ResponseEntity<List<Books>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Books>>(books, HttpStatus.OK);
	}
	
	@GetMapping("/activeauthor/")
	public ResponseEntity<Authors> activeAuthor() {
		Authors author = bas.activeAuthor().get(0);
		
		if(author == null) {
			return new ResponseEntity<Authors>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Authors>(author, HttpStatus.OK);
	}
}

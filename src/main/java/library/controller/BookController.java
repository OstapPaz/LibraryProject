package library.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import library.dto.BookDto;
import library.entity.Books;
import library.mapper.BookMapper;
import library.service.BookService;

@RestController
public class BookController {

	@Autowired BookService bookService;
	
	@GetMapping("/book/")
	public ResponseEntity<List<Books>> listAllBooks() {
		List<Books> books = bookService.findAll();
		if (books.isEmpty()) {
			return new ResponseEntity<List<Books>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Books>>(books, HttpStatus.OK);
	}
	
	@GetMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Books> getBook(@PathVariable("id") Integer id) {
		System.out.println("Searching book with id " + id);
		Books book = bookService.findById(id);
		if (book == null) {
			System.out.println("Book with id " + id + " not found");
			return new ResponseEntity<Books>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Books>(book, HttpStatus.OK);
	}
	
	@PostMapping("/book/")
	public ResponseEntity<Void> createBook(@RequestBody Books book, 
			UriComponentsBuilder ucBuilder) {
		System.out.println("Crearing book " + book.getName() );
		
		if(bookService.findByName(book.getName()) != null) {
			System.out.println("Book with name " + book.getName() + " already exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		bookService.save(book);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/book/{id}").buildAndExpand(book.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/book/{id}")
	public ResponseEntity<Books> updateBook(@PathVariable("id") Integer id, 
			@RequestBody Books book) {
		BookDto currentBookDto =  BookMapper.toDtoBook(bookService.findById(id));
		if (currentBookDto == null) {
			System.out.println("Book with id " + id + " not found");
			return new ResponseEntity<Books>(HttpStatus.NOT_FOUND);
		}
		
		Books currentBook = BookMapper.toBook(currentBookDto);
		bookService.save(currentBook);
		return new ResponseEntity<Books>(currentBook, HttpStatus.OK);
	}
	
	@DeleteMapping("/book/{id}")
	 public ResponseEntity<Books> deleteBook(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting book with id " + id);
 
        Books book = bookService.findById(id);
        if (book == null) {
            System.out.println("Unable to delete. Book with id " + id + " not found");
            return new ResponseEntity<Books>(HttpStatus.NOT_FOUND);
        }
 
        bookService.deleteById(id);
        return new ResponseEntity<Books>(HttpStatus.NO_CONTENT);
    }
	
	@GetMapping("/book/{genre}")
	public ResponseEntity<Map<String,Integer>> booksByGenre(@PathVariable("genre") String genre) {
		 Integer num = bookService.numberBooksByGenre(genre);
		 Map<String,Integer> map = new HashMap<>();
		 map.put(genre, num);
		 if (map.isEmpty()) {
			 System.out.println("Genre " + genre + " not found");
			 return new ResponseEntity<Map<String,Integer>>(HttpStatus.NO_CONTENT);
		 }
		 
		 return new ResponseEntity<Map<String,Integer>>(map, HttpStatus.OK);
	}
	
}

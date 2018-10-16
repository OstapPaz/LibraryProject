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

import library.dto.AuthorDto;
import library.entity.Authors;
import library.mapper.AuthorMapper;
import library.service.AuthorService;

@RestController
public class AuthorController {

@Autowired AuthorService authorService;
	
	@GetMapping("/author/")
	public ResponseEntity<List<Authors>> listAllAuthors() {
		List<Authors> authors = authorService.getAll();
		if (authors.isEmpty()) {
			return new ResponseEntity<List<Authors>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Authors>>(authors, HttpStatus.OK);
	}
	
	@GetMapping(value = "/author/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, "application/json; charset=UTF-8"})
	public ResponseEntity<Authors> getAuthor(@PathVariable("id") Integer id) {
		System.out.println("Searching author with id " + id);
		Authors author = authorService.findById(id);
		if (author == null) {
			System.out.println("Author with id " + id + " not found");
			return new ResponseEntity<Authors>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Authors>(author, HttpStatus.OK);
	}
	
	@PostMapping("/author/")
	public ResponseEntity<Void> createAuthor(@RequestBody Authors author, 
			UriComponentsBuilder ucBuilder) {
		System.out.println("Crearing book " + author.getName() );
		
		if(authorService.findByName(author.getName()) != null) {
			System.out.println("Author with name " + author.getName() + " already exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		authorService.save(author);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/author/{id}").buildAndExpand(author.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/author/{id}")
	public ResponseEntity<Authors> updateAuthor(@PathVariable("id") Integer id, 
			@RequestBody Authors author) {
		AuthorDto currentAuthorDto =  AuthorMapper.toDtoAuthor(authorService.findById(id));
		if (currentAuthorDto == null) {
			System.out.println("Author with id " + id + " not found");
			return new ResponseEntity<Authors>(HttpStatus.NOT_FOUND);
		}
		
		Authors currentAuthor = AuthorMapper.toAuthor(currentAuthorDto);
		authorService.save(currentAuthor);
		return new ResponseEntity<Authors>(currentAuthor, HttpStatus.OK);
	}
	
	@DeleteMapping("/author/{id}")
	 public ResponseEntity<Authors> deleteAuthor(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting author with id " + id);
 
        Authors author = authorService.findById(id);
        if (author == null) {
            System.out.println("Unable to delete. Author with id " + id + " not found");
            return new ResponseEntity<Authors>(HttpStatus.NOT_FOUND);
        }
 
        authorService.deleteById(id);
        return new ResponseEntity<Authors>(HttpStatus.NO_CONTENT);
    }
	
	@GetMapping("/authorold/")
	public ResponseEntity<List<Authors>> listOlderAuthors() {
		List<Authors> authors = authorService.findOlderAuthors();
		if (authors.isEmpty()) {
			return new ResponseEntity<List<Authors>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Authors>>(authors, HttpStatus.OK);
	}
	
}

package library.mapper;

import org.modelmapper.ModelMapper;

import library.dto.BookDto;
import library.entity.Books;

public class BookMapper {

	public static Books toBook(BookDto bookDto) {
		return new ModelMapper().map(bookDto, Books.class);
	}
	
	public static BookDto toDtoBook(Books book) {
		return new ModelMapper().map(book, BookDto.class);
	}
	
}

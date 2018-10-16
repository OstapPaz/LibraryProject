package library.mapper;

import org.modelmapper.ModelMapper;

import library.dto.AuthorDto;
import library.entity.Authors;

public class AuthorMapper {

	public static Authors toAuthor(AuthorDto authorDto) {
		return new ModelMapper().map(authorDto, Authors.class);
	}
	
	public static AuthorDto toDtoAuthor(Authors author) {
		return new ModelMapper().map(author, AuthorDto.class);
	}
	
}

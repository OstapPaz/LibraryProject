package library.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BookDto {

	private Integer id;
	
	private String name;
	
	private Date published;
	
	private String genre;
	
	private int rating;
	
}

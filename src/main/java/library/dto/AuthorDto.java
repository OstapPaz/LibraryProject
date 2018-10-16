package library.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class AuthorDto {

	private Integer id;
	
	private String name;
	
	private String gender;
	
	private Date born;
	
}

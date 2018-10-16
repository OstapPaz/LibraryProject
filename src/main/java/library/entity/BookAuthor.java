package library.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book_author")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookAuthor extends BaseEntity {


	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id")
	private Books book;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
	private Authors author;
	
	
	
	
}

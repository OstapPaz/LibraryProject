package library.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes={@Index(name="name_idx", columnList= "name")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Books extends BaseEntity {
	
//	@Nationalized
	@Column
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(columnDefinition = "DATETIME")
	private Date published;
	
	private String genre;
	
	private int rating;
	
	
	
}

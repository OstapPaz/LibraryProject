package library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import library.entity.Authors;
import library.entity.BookAuthor;
import library.entity.Books;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer>, CrudRepository<BookAuthor, Integer> {

	@Query("SELECT b FROM BookAuthor b WHERE b.author.name = :authorName")
	List<BookAuthor> findAuthorBooks(@Param("authorName")String authorName);
	
	@Query("SELECT ba.book FROM BookAuthor ba "
			+ "GROUP BY ba.author "
			+ "HAVING COUNT(ba.book) > 1")	
	List<Books> findBookThatAuthorHaveMoreBook();
	
	@Query("	SELECT a, COUNT(a)" + 
			"FROM Authors a " + 
			"JOIN BookAuthor ba ON a.id = ba.author " +
			" GROUP BY a.name " +
			" ORDER BY 2 DESC")
	List<Authors> findAuthorWithMostBook();
	
	@Query("SELECT ba FROM BookAuthor ba WHERE ba.author.name = :authorName"
			+ " AND ba.book.name = :bookName ")
	BookAuthor bookAuthorByNames(@Param("authorName")String authorName,
								 @Param("bookName")String bookName);
	
}

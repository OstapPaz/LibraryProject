package library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import library.entity.Books;

@Repository
public interface BookRepository extends JpaRepository<Books, Integer>, CrudRepository<Books, Integer> {

	@Query("SELECT b FROM Books b WHERE b.name = :name")
	Books findByName(@Param("name") String name);
	
    @Query("SELECT COUNT(b) FROM Books b WHERE b.genre=:genre")
    Integer numderBooksByGenre(@Param("genre") String genre);
}

package library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import library.entity.Authors;

public interface AuthorRepository extends JpaRepository<Authors, Integer> {

	@Query("SELECT a FROM Authors a WHERE a.born < '1963-01-01' GROUP BY a.born ")
	List<Authors> findOlderAuthors();

	@Query("SELECT a FROM Authors a WHERE a.name = :name")
	Authors findByName(@Param("name") String name);
	
}

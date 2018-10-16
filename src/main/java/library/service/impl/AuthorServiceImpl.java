package library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import library.entity.Authors;
import library.repository.AuthorRepository;
import library.service.AuthorService;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

	@Autowired AuthorRepository ar;
	
	@Override
	public void save(Authors author) {
		ar.save(author);
		
	}

	@Override
	public Authors findById(Integer id) {
		return ar.getOne(id);
	}

	@Override
	public List<Authors> getAll() {
		return ar.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		ar.deleteById(id);
	}

	@Override
	public void updateAuthor(Authors author) {
		ar.save(author);
		
	}

	@Override
	public Authors findByName(String name) {
		return ar.findByName(name);
	}

	@Override
	public List<Authors> findOlderAuthors() {
		return ar.findOlderAuthors();
	}

	
	
}

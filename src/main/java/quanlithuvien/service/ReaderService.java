package quanlithuvien.service;

import java.util.List;

import quanlithuvien.entity.Book;
import quanlithuvien.entity.Category;
import quanlithuvien.entity.Reader;
import quanlithuvien.repository.ReaderRepository;

public class ReaderService {
	private ReaderRepository readerRepository;
	
	public ReaderService() {
		readerRepository = new ReaderRepository();
	}
	public List<Reader> findAll() {
		// TODO Auto-generated method stub
		List<Reader> readers = readerRepository.findAll();
		return readers;
	}

	public Reader findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteById(Long id1) {
		// TODO Auto-generated method stub
		
	}

}

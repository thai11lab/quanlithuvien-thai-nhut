package quanlithuvien.service;

import java.util.List;

import quanlithuvien.entity.BookReader;
import quanlithuvien.repository.BookReaderRepository;

public class BookReaderService {
	
	private BookReaderRepository bookReaderRepository;
	
	public BookReaderService() {
		// TODO Auto-generated constructor stub
		bookReaderRepository = new BookReaderRepository();
	}
	public List<BookReader> findByReaderId(Long id) {
		// TODO Auto-generated method stub
		
		List<BookReader> bookReaders = bookReaderRepository.findByProductId(id);
		return bookReaders;
	}
	
}

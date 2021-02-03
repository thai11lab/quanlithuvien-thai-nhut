package quanlithuvien.service;

import java.util.ArrayList;
import java.util.List;

import quanlithuvien.datasoucre.HibernateConfig;
import quanlithuvien.entity.Book;
import quanlithuvien.entity.Category;
import quanlithuvien.repository.BookRepository;

public class BookService {
	private BookRepository bookRepository;
	
	public BookService() {
		// TODO Auto-generated constructor stub
		bookRepository = new BookRepository();
	}
	
	public List<Book> findAll() {
		List<Book> listBook = bookRepository.findAll();
		return listBook;
	}
	
	public void save(Book book) {
		bookRepository.save(book);
	}
	
	public void update(Book book,Long id) {
		bookRepository.update(book, id);
	}
	
	public Book findById(Long id) {
		Book book = bookRepository.findById(id);
		return book;
	}

	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		bookRepository.deleteById(id);
	}

	public List<Book> findBySearch(String key) {
		// TODO Auto-generated method stub
		return bookRepository.findBySearch(key);
	}
}

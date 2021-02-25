package quanlithuvien.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "book_reader")
public class BookReader {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	
	
	@ManyToOne
	@JoinColumn(name = "reader_id")
	private Reader reader;


	public BookReader(Long id, Book book, Reader reader) {
		super();
		this.id = id;
		this.book = book;
		this.reader = reader;
	}


	public BookReader() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	public Reader getReader() {
		return reader;
	}


	public void setReader(Reader reader) {
		this.reader = reader;
	}
		
	
	
}

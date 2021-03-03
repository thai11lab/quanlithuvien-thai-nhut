package quanlithuvien.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name = "reader")
public class Reader {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "code" ,nullable = false,length = 30)
	private String code;
	
	@Column(name = "full_name",nullable = false,length = 255)
	private String name;
	
	@Column(name = "address")
	private String address;
	
	

	@Column(name = "age")
	private int age;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_by")
	private String modifiedBy;

	@OneToMany(mappedBy = "reader",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
	private Set<BookReader> bookReaders;
	
	public Reader() {
		super();
	}
	
	

	public Reader(Long id, String code, String name, String address, int age, Date createdDate, Date modifiedDate,
			String createdBy, String modifiedBy, Set<BookReader> bookReaders) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.address = address;
		this.age = age;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.bookReaders = bookReaders;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	public Set<BookReader> getBookReaders() {
		return bookReaders;
	}

	public void setBookReaders(Set<BookReader> bookReaders) {
		this.bookReaders = bookReaders;
	}
}

package quanlithuvien.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", length = 255)
	private String name;

	@Column(name = "code", length = 255)
	private String code;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	public Role(Long id, String name, String code) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
	}
		
	public Role() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}

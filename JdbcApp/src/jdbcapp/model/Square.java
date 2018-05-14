package jdbcapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Square {

	@Id
	private int id;
	private String name;
	
	public Square() {}
	
	public Square(int id, String name) {
		setId(id);
		setName(name);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

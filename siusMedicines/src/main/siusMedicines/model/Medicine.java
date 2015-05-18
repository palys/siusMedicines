package siusMedicines.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "medicines")
public class Medicine {
	
	@Column(name = "name")
	private String name;
	
	public Medicine() {
		
	}

	public Medicine(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Medicine [ name=" + name + "]";
	}
	
	

}

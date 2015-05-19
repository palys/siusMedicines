package siusMedicines.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "medicines")
public class Medicine {
	
	@Id
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "medicines")
	private Set<Prescription> prescriptions;
	
	public Medicine() {
		
	}

	public Medicine(String name, Set<Prescription> prescriptions) {
		super();
		this.name = name;
		this.prescriptions = prescriptions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	@Override
	public String toString() {
		return "Medicine [ name=" + name + "]";
	}
	
	

}

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
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "meal_info")
	private String mealInfo;
	
	@OneToMany(mappedBy = "medicine")
	private Set<Prescription> prescriptions;
	
	public Medicine() {
		
	}

	public Medicine(String name, String description, String mealInfo,
			Set<Prescription> prescriptions) {
		super();
		this.name = name;
		this.description = description;
		this.mealInfo = mealInfo;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMealInfo() {
		return mealInfo;
	}

	public void setMealInfo(String mealInfo) {
		this.mealInfo = mealInfo;
	}

	@Override
	public String toString() {
		return "Medicine [name=" + name + ", description=" + description
				+ ", mealInfo=" + mealInfo + "]";
	}

}

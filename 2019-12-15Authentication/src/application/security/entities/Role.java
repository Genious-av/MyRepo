package application.security.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter

@Entity
@Table(name="role")

public class Role {
	
	@Id
	@Column(length=100) // for string id
	private String roleString;
	
	
	// cross table field
	@ManyToMany(mappedBy="roles") // main entity
	private Set<Account> accounts = new HashSet<>();
	
	
	public Role(String role) 
	{
		super();
		this.roleString = role; // requariments for Spring Security
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleString == null) ? 0 : roleString.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (roleString == null) {
			if (other.roleString != null)
				return false;
		} else if (!roleString.equals(other.roleString))
			return false;
		return true;
	}

}

package application.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import dto.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name="teacher")
public class TeacherEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idteacher;
	
	
	public int passportTeacherE;
	private String firstnameTeacherE;
	private String lastnameTeacherE;
	
	
	@OneToMany(mappedBy="teacherE", cascade=CascadeType.ALL)
	//@JsonManagedReference 
	@JsonIgnore
	private Set<GroupEntity> groups = new HashSet<>();


	public TeacherEntity(int passportTeacherE, String firstnameTeacherE, String lastnameTeacherE) {
		super();
		this.passportTeacherE = passportTeacherE;
		this.firstnameTeacherE = firstnameTeacherE;
		this.lastnameTeacherE = lastnameTeacherE;
	}
	
	public Teacher getTeacher() {
		return new Teacher(passportTeacherE, firstnameTeacherE, lastnameTeacherE);
	}
	
	public TeacherEntity(Teacher teacher) {
		this(teacher.getPassportTeacher(), teacher.getFirstnameTeacher(), teacher.getLastnameTeacher());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeacherEntity other = (TeacherEntity) obj;
		if (firstnameTeacherE == null) {
			if (other.firstnameTeacherE != null)
				return false;
		} else if (!firstnameTeacherE.equals(other.firstnameTeacherE))
			return false;
		if (lastnameTeacherE == null) {
			if (other.lastnameTeacherE != null)
				return false;
		} else if (!lastnameTeacherE.equals(other.lastnameTeacherE))
			return false;
		if (passportTeacherE != other.passportTeacherE)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstnameTeacherE == null) ? 0 : firstnameTeacherE.hashCode());
		result = prime * result + ((lastnameTeacherE == null) ? 0 : lastnameTeacherE.hashCode());
		result = prime * result + passportTeacherE;
		return result;
	}
	
	
	
}

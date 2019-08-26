package application.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private int id_teacher;
	
	public int passportTeacherEntity;
	private String firstnameTeacherEntity;
	private String lastnameTeacherEntity;
	
	@OneToMany(mappedBy="teacherEntity", cascade=CascadeType.ALL)
	@JsonManagedReference
	
	private Set<GroupEntity> groups= new HashSet<>();
	
	
	public TeacherEntity(int passportTeacherEntity, String firstnameTeacherEntity, String lastnameTeacherEntity) {
		super();
		this.passportTeacherEntity = passportTeacherEntity;
		this.firstnameTeacherEntity = firstnameTeacherEntity;
		this.lastnameTeacherEntity = lastnameTeacherEntity;
	}
	
	
	
	public TeacherEntity(Teacher teacher) {
		this(teacher.getPassportTeacher(), teacher.getLastnameTeacher(),teacher.getLastnameTeacher());
	}
	
	public Teacher getTeacher() {
		return new Teacher(passportTeacherEntity, firstnameTeacherEntity, lastnameTeacherEntity);
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
		if (firstnameTeacherEntity == null) {
			if (other.firstnameTeacherEntity != null)
				return false;
		} else if (!firstnameTeacherEntity.equals(other.firstnameTeacherEntity))
			return false;
		if (groups == null) {
			if (other.groups != null)
				return false;
		} else if (!groups.equals(other.groups))
			return false;
		if (lastnameTeacherEntity == null) {
			if (other.lastnameTeacherEntity != null)
				return false;
		} else if (!lastnameTeacherEntity.equals(other.lastnameTeacherEntity))
			return false;
		if (passportTeacherEntity != other.passportTeacherEntity)
			return false;
		return true;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstnameTeacherEntity == null) ? 0 : firstnameTeacherEntity.hashCode());
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result + ((lastnameTeacherEntity == null) ? 0 : lastnameTeacherEntity.hashCode());
		result = prime * result + passportTeacherEntity;
		return result;
	}




	
	
	

	
	
	
}

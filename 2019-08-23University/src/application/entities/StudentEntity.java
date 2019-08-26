package application.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import dto.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name="student")
public class StudentEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_student;
	
	private int passportStudentEntity;
	private String nameStudentEntity;
	private int ageEntity;
	private String nameGroupEntity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	
	private GroupEntity groupEntity;

	public StudentEntity(int passportStudentEntity, String nameStudentEntity, int ageEntity, String nameGroupEntity) {
		super();
		this.passportStudentEntity = passportStudentEntity;
		this.nameStudentEntity = nameStudentEntity;
		this.ageEntity = ageEntity;
		this.nameGroupEntity=nameGroupEntity;
	}
	
	public Student getStudent() {
		return new Student(passportStudentEntity,nameStudentEntity, ageEntity,nameGroupEntity);
	}
	
	public StudentEntity(Student student) {
		this(student.getPassportStudent(),student.getNameStudent(), student.getAge(), student.getNameGroup());
	}
	
	
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentEntity other = (StudentEntity) obj;
		if (ageEntity != other.ageEntity)
			return false;
		if (groupEntity == null) {
			if (other.groupEntity != null)
				return false;
		} else if (!groupEntity.equals(other.groupEntity))
			return false;
		if (nameGroupEntity == null) {
			if (other.nameGroupEntity != null)
				return false;
		} else if (!nameGroupEntity.equals(other.nameGroupEntity))
			return false;
		if (nameStudentEntity == null) {
			if (other.nameStudentEntity != null)
				return false;
		} else if (!nameStudentEntity.equals(other.nameStudentEntity))
			return false;
		if (passportStudentEntity != other.passportStudentEntity)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ageEntity;
		result = prime * result + ((groupEntity == null) ? 0 : groupEntity.hashCode());
		result = prime * result + ((nameGroupEntity == null) ? 0 : nameGroupEntity.hashCode());
		result = prime * result + ((nameStudentEntity == null) ? 0 : nameStudentEntity.hashCode());
		result = prime * result + passportStudentEntity;
		return result;
	}

	
	
}

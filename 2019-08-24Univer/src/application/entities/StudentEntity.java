package application.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	private int passportStudentE;
	private String nameStudentE;
	private int ageE;
	
	@ManyToOne 
	//@JsonBackReference
	@JsonIgnore
	public GroupEntity groupEntity;

	public StudentEntity(int passportStudentE, String nameStudentE, int ageE, GroupEntity groupEntity) {
		super();
		this.passportStudentE = passportStudentE;
		this.nameStudentE = nameStudentE;
		this.ageE = ageE;
		this.groupEntity = groupEntity;
	}
	
	public Student getStudent() {
		return new Student(passportStudentE, nameStudentE, ageE, groupEntity.getGroup());
		
	}
	
	public StudentEntity (Student student) {
		this(student.getPassportStudent(), student.getNameStudent(), student.getAge(), new GroupEntity(student.getGroup()));
	}
	
	
	public GroupEntity getGroup() {
		return groupEntity;
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
		if (ageE != other.ageE)
			return false;
		if (nameStudentE == null) {
			if (other.nameStudentE != null)
				return false;
		} else if (!nameStudentE.equals(other.nameStudentE))
			return false;
		if (passportStudentE != other.passportStudentE)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ageE;
		result = prime * result + ((nameStudentE == null) ? 0 : nameStudentE.hashCode());
		result = prime * result + passportStudentE;
		return result;
	}


	
}

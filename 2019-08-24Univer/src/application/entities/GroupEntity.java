package application.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import dto.Group;
import dto.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name="groupUniversity")
public class GroupEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_group;
	
	private String nameGroupE;
	private String courseNameE;
	
	@ManyToOne 
	//@JsonBackReference
	@JsonIgnore
	private TeacherEntity teacherE;
				
	@OneToMany(mappedBy="groupEntity", cascade=CascadeType.ALL)
	//@JsonManagedReference 
	@JsonIgnore
	private Set<StudentEntity> students = new HashSet<>();

	public GroupEntity(String nameGroupE, String courseNameE, TeacherEntity teacherE) {
		super();
		this.nameGroupE = nameGroupE;
		this.courseNameE = courseNameE;
		this.teacherE = teacherE;
	}
	
	public Group getGroup() {
		return new Group(nameGroupE, courseNameE, teacherE.getTeacher());
	}
	
	public GroupEntity (Group group) {
		this(group.getNameGroup(), group.getCourseName(),new TeacherEntity(group.getTeacher()));
	}
	
	
	
	public TeacherEntity getTeacher() {
		return teacherE;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupEntity other = (GroupEntity) obj;
		if (courseNameE == null) {
			if (other.courseNameE != null)
				return false;
		} else if (!courseNameE.equals(other.courseNameE))
			return false;
		if (nameGroupE == null) {
			if (other.nameGroupE != null)
				return false;
		} else if (!nameGroupE.equals(other.nameGroupE))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseNameE == null) ? 0 : courseNameE.hashCode());
		result = prime * result + ((nameGroupE == null) ? 0 : nameGroupE.hashCode());
		return result;
	}
	

	
	
	
	
	
	
	
}

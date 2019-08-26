package application.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import dto.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="groupUniversity")
public class GroupEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_groupUniversity;
	
	private String nameGroupEntity;
	private String nameCourseEntity;
	private int passportTeacherEntity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonBackReference
	
	private TeacherEntity teacherEntity;
	
	
	@OneToMany(mappedBy="groupEntity", cascade=CascadeType.ALL)
	@JsonManagedReference
	
	private Set<StudentEntity> students=new HashSet<>();

	public GroupEntity(String nameGroupEntity, String nameCourseEntity, int passportTeacherEntity) {
		super();
		this.nameGroupEntity = nameGroupEntity;
		this.nameCourseEntity = nameCourseEntity;
		this.passportTeacherEntity=passportTeacherEntity;
	}

	
	public GroupEntity(Group group) {
		this(group.getNameGroup(), group.getCourseName(), group.getPassportTeacher());
		
	}


	public GroupEntity(String nameGroupEntity, String nameCourseEntity, int passportTeacherEntity,
			TeacherEntity teacherEntity) {
		super();
		this.nameGroupEntity = nameGroupEntity;
		this.nameCourseEntity = nameCourseEntity;
		this.passportTeacherEntity = passportTeacherEntity;
		this.teacherEntity = teacherEntity;
	}
	
	
	
	
	

}

package io.home.hibernateapp.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table (name="TEACHER")
public class Teacher {

	@Id 
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="TEACHER_ID")
	private long teacherId;
	
	@Column (name="TEACHER_NAME")
	private String teacherName;
	
	/* @ManyToMany(mappedBy="teachers") is not creating the mapping table
	 * because mapping is done by other class 
	 */
	@ManyToMany(mappedBy="teachers")
	private Collection<Subject> subjects;

	/****************************************************************************/
	public Teacher() {}

	public Teacher(String teacherName) {
		super();
		setTeacherName(teacherName);
		subjects = new ArrayList<Subject>();
	}

	/****************************************************************************/
	public long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Collection<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(Collection<Subject> subjects) {
		this.subjects = subjects;
	}
	
	/****************************************************************************/
}

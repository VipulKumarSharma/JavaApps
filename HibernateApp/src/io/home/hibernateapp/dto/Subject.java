package io.home.hibernateapp.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table (name="SUBJECT")
public class Subject {

	@Id 
	@GeneratedValue (strategy=GenerationType.AUTO)
	@Column (name="SUBJECT_ID")
	private int subjectId;
	
	@Column (name="SUBJECT_NAME")
	private String subjectName;
	
	/* By default hibernate will create 2 mapping tables 
	 * to avoid this 
	 */
	@ManyToMany
	@JoinTable(name			= "SUBJECT_TEACHER_MAPPING",
		joinColumns			= @JoinColumn(name="SUBJECT_ID"),
		inverseJoinColumns	= @JoinColumn(name="TEACHER_ID"))
	private Collection<Teacher> teachers;
	
	/****************************************************************************/
	public Subject() {}
	
	public Subject(String subjectName) {
		super();
		setSubjectName(subjectName);
		teachers = new ArrayList<Teacher>();
	}

	/****************************************************************************/
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Collection<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(Collection<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	/****************************************************************************/
}

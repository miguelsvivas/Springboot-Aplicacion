package com.aplicacion.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1330124534723526291L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native" )
	@GenericGenerator(name="native", strategy ="native")
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String Description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", Description=" + Description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(Description, id, name);
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
		return Objects.equals(Description, other.Description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}
	
	
	
}

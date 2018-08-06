package com.atos.hibernate;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "roles")
public class Roles {
	private Integer codRol;
	private String descRol;
	private Set<Tareas> tareas;
	private Set<Usuarios> usuarios;
	public Roles() {
		super();
	}
	public Roles(Integer codRol) {
		super();
		this.codRol = codRol;
	}
	public Roles(Integer codRol, Set<Tareas> tareas) {
		super();
		this.codRol = codRol;
		this.tareas = tareas;
	}
	public Roles(Integer codRol, String descRol, Set<Tareas> tareas) {
		super();
		this.codRol = codRol;
		this.descRol = descRol;
		this.tareas = tareas;
	}
	
	@Id
	@Column(name = "codigo_rol", unique = true, nullable = false, precision = 11, scale = 0)
	public Integer getCodRol() {
		return codRol;
	}
	public void setCodRol(Integer codRol) {
		this.codRol = codRol;
	}
	@Column(name = "rol", length = 100)
	public String getDescRol() {
		return descRol;
	}
	public void setDescRol(String descRol) {
		this.descRol = descRol;
	}
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<Tareas> getTareas() {
		return tareas;
	}
	public void setTareas(Set<Tareas> tareas) {
		this.tareas = tareas;
	}
	
	

}

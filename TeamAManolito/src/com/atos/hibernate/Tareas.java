package com.atos.hibernate;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

public class Tareas {
	private Set<Roles> roles;
	private Integer codigo_tarea;
	private String desc;

	
	
	public Tareas() {
		super();
	}
	
	

	public Tareas(Set<Roles> roles, Integer codigo_tarea, String desc) {
		super();
		this.roles = roles;
		this.codigo_tarea = codigo_tarea;
		this.desc = desc;
	}


	@Id
	@Column ( name = "codigo_tarea" ,unique = true, nullable = false, precision = 200, scale = 0)
	public Integer getCodigo_tarea() {
		return codigo_tarea;
	}



	public void setCodigo_tarea(Integer codigo_tarea) {
		this.codigo_tarea = codigo_tarea;
	}


	@Column(name = "tarea", length = 100)
	public String getDesc() {
		return desc;
	}



	public void setDesc(String desc) {
		this.desc = desc;
	}



	// ojito aqui hay que revisar esta mierda campo schema dentro a la anotacion
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "rol_tarea", joinColumns = { @JoinColumn(name = "codigo_tarea", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "codigo_rol", nullable = false, updatable = false) })
	public Set<Roles> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}








}

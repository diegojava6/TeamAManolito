package com.atos.hibernate;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

public class Tareas {

	private Integer codigoTarea;
	private String tarea;
	private Set<Roles> roles;

	public Tareas() {
		super();
	}

	public Tareas(Integer codigoTarea) {
		super();
		this.codigoTarea = codigoTarea;
	}

	public Tareas(Integer codigoTarea, String tarea, Set<Roles> roles) {
		super();
		this.codigoTarea = codigoTarea;
		this.tarea = tarea;
		this.roles = roles;
	
	// ojito aqui hay que revisar esta mierda
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "rol_tarea", schema = "", joinColumns = { @JoinColumn(name = "codigo_tarea", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "codigo_rol", nullable = false, updatable = false) })
	public Set<Roles> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	@id 
	@column (name="codigo_tarea"  unique = true, nullable = false, precision = 200, scale = 0)
	public Integer getCodigoTarea() {
		return codigoTarea;
	}

	public void setCodigoTarea(Integer codigoTarea) {
		this.codigoTarea = codigoTarea;
	}
	@column (name="tarea" nullable = false, length = 100)
	public String getTarea() {
		return tarea;
	}

	public void setTarea(String tarea) {
		this.tarea = tarea;
	}

}

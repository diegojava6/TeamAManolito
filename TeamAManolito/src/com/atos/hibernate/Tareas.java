package com.atos.hibernate;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

public class Tareas {
	private Set<Roles> roles;
	
	// ojito aqui hay que revisar esta mierda
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "rol_tarea", schema = "", joinColumns = { @JoinColumn(name = "codigo_tarea", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "codigo_rol", nullable = false, updatable = false) })
	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	
}

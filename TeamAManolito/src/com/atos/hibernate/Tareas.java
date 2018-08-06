package com.atos.hibernate;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Tareas entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TAREAS")
public class Tareas implements java.io.Serializable {

	// Fields

	private Byte codigoTarea;
	private String descripcionTarea;
	private String vinculo;
	private Set<Roles> roleses = new HashSet<Roles>(0);

	// Constructors

	/** default constructor */
	public Tareas() {
	}

	/** minimal constructor */
	public Tareas(Byte codigoTarea) {
		this.codigoTarea = codigoTarea;
	}

	/** full constructor */
	public Tareas(Byte codigoTarea, String descripcionTarea, String vinculo,
			Set<Roles> roleses) {
		this.codigoTarea = codigoTarea;
		this.descripcionTarea = descripcionTarea;
		this.vinculo = vinculo;
		this.roleses = roleses;
	}

	// Property accessors
	@Id
	@Column(name = "CODIGO_TAREA", unique = true, nullable = false, precision = 2, scale = 0)
	public Byte getCodigoTarea() {
		return this.codigoTarea;
	}

	public void setCodigoTarea(Byte codigoTarea) {
		this.codigoTarea = codigoTarea;
	}

	@Column(name = "DESCRIPCION_TAREA", length = 2000)
	public String getDescripcionTarea() {
		return this.descripcionTarea;
	}

	public void setDescripcionTarea(String descripcionTarea) {
		this.descripcionTarea = descripcionTarea;
	}

	@Column(name = "VINCULO", length = 50)
	public String getVinculo() {
		return this.vinculo;
	}

	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "ROLES_TAREAS", schema = "J172", joinColumns = { @JoinColumn(name = "CODIGO_TAREA", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "CODIGO_ROL", nullable = false, updatable = false) })
	public Set<Roles> getRoleses() {
		return this.roleses;
	}

	public void setRoleses(Set<Roles> roleses) {
		this.roleses = roleses;
	}

}
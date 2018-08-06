package com.atos.hibernate;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Roles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ROLES")
public class Roles implements java.io.Serializable {

	// Fields

	private Byte codigoRol;
	private String descripcionRol;
	private Set<Tareas> tareases = new HashSet<Tareas>(0);
	private Set<Usuarios> usuarioses = new HashSet<Usuarios>(0);

	// Constructors

	/** default constructor */
	public Roles() {
	}

	/** minimal constructor */
	public Roles(Byte codigoRol) {
		this.codigoRol = codigoRol;
	}

	/** full constructor */
	public Roles(Byte codigoRol, String descripcionRol, Set<Tareas> tareases,
			Set<Usuarios> usuarioses) {
		this.codigoRol = codigoRol;
		this.descripcionRol = descripcionRol;
		this.tareases = tareases;
		this.usuarioses = usuarioses;
	}

	// Property accessors
	@Id
	@Column(name = "CODIGO_ROL", unique = true, nullable = false, precision = 2, scale = 0)
	public Byte getCodigoRol() {
		return this.codigoRol;
	}

	public void setCodigoRol(Byte codigoRol) {
		this.codigoRol = codigoRol;
	}

	@Column(name = "DESCRIPCION_ROL", length = 100)
	public String getDescripcionRol() {
		return this.descripcionRol;
	}

	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roleses")
	public Set<Tareas> getTareases() {
		return this.tareases;
	}

	public void setTareases(Set<Tareas> tareases) {
		this.tareases = tareases;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roles")
	public Set<Usuarios> getUsuarioses() {
		return this.usuarioses;
	}

	public void setUsuarioses(Set<Usuarios> usuarioses) {
		this.usuarioses = usuarioses;
	}

}
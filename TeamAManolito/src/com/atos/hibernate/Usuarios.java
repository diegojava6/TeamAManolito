package com.atos.hibernate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Usuarios entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USUARIOS")
public class Usuarios implements java.io.Serializable {

	// Fields

	private String nombreUsuario;
	private Roles roles;
	private String password;
	private Date fechaAlta;
	private Date fechaBaja;
	private String carpetaDocumentacion;
	private String idioma;

	// Constructors

	/** default constructor */
	public Usuarios() {
	}

	/** minimal constructor */
	public Usuarios(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/** full constructor */
	public Usuarios(String nombreUsuario, Roles roles, String password,
			Date fechaAlta, Date fechaBaja, String carpetaDocumentacion,
			String idioma) {
		this.nombreUsuario = nombreUsuario;
		this.roles = roles;
		this.password = password;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
		this.carpetaDocumentacion = carpetaDocumentacion;
		this.idioma = idioma;
	}

	// Property accessors
	@Id
	@Column(name = "NOMBRE_USUARIO", unique = true, nullable = false, length = 20)
	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODIGO_ROL")
	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	@Column(name = "PASSWORD", length = 10)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_ALTA", length = 7)
	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_BAJA", length = 7)
	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	@Column(name = "CARPETA_DOCUMENTACION", length = 250)
	public String getCarpetaDocumentacion() {
		return this.carpetaDocumentacion;
	}

	public void setCarpetaDocumentacion(String carpetaDocumentacion) {
		this.carpetaDocumentacion = carpetaDocumentacion;
	}

	@Column(name = "IDIOMA", length = 2)
	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

}
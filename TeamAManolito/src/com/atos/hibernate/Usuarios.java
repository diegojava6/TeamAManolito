package com.atos.hibernate;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIOS_A")
public class Usuarios {
	private String nombre;
	private String apellidos;
	private String correo;
	private String password;
	private Integer primerLogin;
	private Integer accesoAplicacion;
	private Roles roles;

	public Usuarios() {
		super();
	}

	public Usuarios(String correo, String password) {
		super();
		this.correo = correo;
		this.password = password;
	}
	public Usuarios(String correo) {
		super();
		this.correo = correo;
	}

	public Usuarios(String nombre, String apellidos, String correo, String password, Integer primerLogin,
			Integer accesoAplicacion) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.password = password;
		this.primerLogin = primerLogin;
		this.accesoAplicacion = accesoAplicacion;

	}

	@Column(name = "nombre", nullable = false, length = 10)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "apellido", nullable = false, length = 10)
	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Id
	@Column(name = "correo", unique = true, nullable = false, length = 50)
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Column(name = "password", nullable = false, length = 10)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "primer_registro", nullable = false, precision = 1, scale = 0)
	public Integer getPrimerLogin() {
		return primerLogin;
	}

	public void setPrimerLogin(Integer primerLogin) {
		this.primerLogin = primerLogin;
	}

	@Column(name = "acceso", nullable = false, precision = 1, scale = 0)
	public Integer getAccesoAplicacion() {
		return accesoAplicacion;
	}

	public void setAccesoAplicacion(Integer accesoAplicacion) {
		this.accesoAplicacion = accesoAplicacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_rol")
	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

}

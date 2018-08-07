package com.atos.hibernate;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuarios {
	private String nombre;
	private String apellidos;
	private String correo;
	private String password;
	private boolean primerLogin;
	private boolean accesoAplicacion;
	private Set<Roles> roles;
	
	

	
	public Usuarios() {
		super();
	}
	
	
	public Usuarios(String correo, String password) {
		super();
		this.correo = correo;
		this.password = password;
	}
	
	


	public Usuarios(String nombre, String apellidos, String correo, String password, boolean primerLogin,
			boolean accesoAplicacion, Set<Roles> roles) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.password = password;
		this.primerLogin = primerLogin;
		this.accesoAplicacion = accesoAplicacion;
		this.roles = roles;
	}

	@Column(name = "nombre", nullable = false, length = 100)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	@Column(name = "apellido", nullable = false, length = 50)
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
	
	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "primer_registro", nullable = false, columnDefinition="boolean default true")
	public boolean isPrimerLogin() {
		return primerLogin;
	}
	public void setPrimerLogin(boolean primerLogin) {
		this.primerLogin = primerLogin;
	}
	@Column(name = "acceso", nullable = false, columnDefinition="boolean default false")
	public boolean isAccesoAplicacion() {
		return accesoAplicacion;
	}
	public void setAccesoAplicacion(boolean accesoAplicacion) {
		this.accesoAplicacion = accesoAplicacion;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_rol")
	public Set<Roles> getRoles() {
		return roles;
	}
	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	
	
}
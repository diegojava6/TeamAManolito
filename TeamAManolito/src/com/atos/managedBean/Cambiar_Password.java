package com.atos.managedBean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.atos.hibernate.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;
import com.atos.util.IAcceso_Contextos;

@ViewScoped
@ManagedBean(name = "cambiar_password")
public class Cambiar_Password implements Serializable {

	@ManagedProperty("#{gestion_usuarios}")
	private IGestion_Usuarios gestion_usuarios;

	@ManagedProperty("#{accesos_contextos}")
	private IAcceso_Contextos accesos_contextos;

	private Usuarios usuario;
	private String oldpassword;
	private String pass;
	private String passagain;

	@PostConstruct
	public void metodo_Inicio() {

		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 

		usuario = gestion_usuarios.consultar_conRol(auth.getName());

	}

	public void comprobar_pass(ActionEvent actionevent) {

		if(oldpassword=="" || pass=="" || passagain=="") {
			
			System.out.println("Todos los campos son obligatorios");
			
		}else if (oldpassword.equals(usuario.getPassword())) {
			if (pass.equals(passagain)) {
				
				System.out.println("seguir con el cambio de pass");

				usuario.setPassword(pass);
				usuario.setPrimerLogin(1);
				gestion_usuarios.modificacion_Usuario(usuario);

			} else {
				System.out.println("las contraseñas no son identicas");
			}

		} else {
			System.out.println("no es tu contraseña");
		}
	}

	public void salir(ActionEvent actionevent) {		
		
		
	}
	
	public IGestion_Usuarios getGestion_usuarios() {
		return gestion_usuarios;
	}

	public void setGestion_usuarios(IGestion_Usuarios gestion_usuarios) {
		this.gestion_usuarios = gestion_usuarios;
	}

	public IAcceso_Contextos getAccesos_contextos() {
		return accesos_contextos;
	}

	public void setAccesos_contextos(IAcceso_Contextos accesos_contextos) {
		this.accesos_contextos = accesos_contextos;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPassagain() {
		return passagain;
	}

	public void setPassagain(String passagain) {
		this.passagain = passagain;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	
	
}
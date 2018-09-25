package com.atos.managedBean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.atos.hibernate.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;
import com.atos.util.IAcceso_Contextos;

@ManagedBean(name = "cambiar_password")
@SessionScoped
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

		// El usuario deberia de cogerlo por sesion
		usuario = new Usuarios();

	}

	public void comprobar_pass() {

		//if (oldpassword.equals(usuario.getPassword())) {
			if (pass.equals(passagain)) {
				System.out.println("seguir con el cambio de pass");

				// usuario.setPassword(pass);
				// gestion_usuarios.modificacion_Usuario(usuario);

			} else {
				FacesContext.getCurrentInstance().addMessage("mensaje",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Las contraseñas nuevas no son iguales","mensaje"));
			}

		//} else {
		//	FacesContext.getCurrentInstance().addMessage("La contraseña original no es la correcta.",
		//			new FacesMessage(FacesMessage.SEVERITY_ERROR, "La contraseña original no es la correcta.", "mensaje"));
		//}
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
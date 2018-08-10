package com.atos.managedBean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.atos.hibernate.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;
import com.atos.util.IAcceso_Contextos;

@ManagedBean(name = "login_bean")
@ViewScoped
public class Login_bean {
	@ManagedProperty("#{gestion_usuarios}")
	private IGestion_Usuarios gestion_usuarios;

	@ManagedProperty("#{accesos_contextos}")
	private IAcceso_Contextos accesos_contextos;
	/*
	 * private String correo_usuario; private String clave_usuario;
	 */
	private Usuarios usuario_login;

	// ********** METODOS DEL CICLO DE VIDA (CDI)
	@PostConstruct
	public void metodo_Inicio() {
		System.out.println("SOY EL METODO DE INICIO DE LOGIN_BEAN");
		usuario_login = new Usuarios();

	}

	@PreDestroy
	public void metodo_Final() {
		System.out.println("SOY EL METODO DE FINALIZACION DE LOGIN_BEAN");
	}
	// ********** FIN METODOS DEL CICLO DE VIDA (CDI)

	public void metodo_Evento(ActionEvent evento) {

	}

	public void metodo_Accion(ActionEvent evento) throws IOException {

		boolean resultado = gestion_usuarios.consultar_Login(usuario_login.getCorreo(), usuario_login.getPassword());

		if (resultado == false) {
			accesos_contextos.addMensaje("Usuario y/o contraseña incorrectos", "mensaje");
		} else {
			Usuarios usuario_navegacion = gestion_usuarios.consultar_Correo(usuario_login.getCorreo());
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("Administrador2.xhtml");
			/*
			 * if((usuario_navegacion.getRoles().getDescRol()).equals("SUPERADMIN")) {
			 * 
			 * 
			 * }else if((usuario_navegacion.getRoles().getDescRol()).equals("ADMIN")){
			 * 
			 * 
			 * }
			 */

		}

	}

	public void metodo_EventoCambioClave(ValueChangeEvent evento) {
		System.out.println("SOY EL EVENTO DEL CAMBIO DE CLAVE");
	}

	public void metodo_EventoCambioCorreo(ValueChangeEvent evento) {
		System.out.println("SOY EL EVENTO DEL CAMBIO DE CORREO");

		// this.usuario_login=this.InterfazGestionUsuarios.consultar_correo(this.correo_usuario)

		// this.usuario_Login.setCorreo(correo_usuario);
		// if(usuario_Login.consultarCorreo())
	}

	public IGestion_Usuarios getGestion_usuarios() {
		return gestion_usuarios;
	}

	public void setGestion_usuarios(IGestion_Usuarios gestion_usuarios) {
		this.gestion_usuarios = gestion_usuarios;
	}

	public Usuarios getUsuario_login() {
		return usuario_login;
	}

	public void setUsuario_login(Usuarios usuario_login) {
		this.usuario_login = usuario_login;
	}

	public IAcceso_Contextos getAccesos_contextos() {
		return accesos_contextos;
	}

	public void setAccesos_contextos(IAcceso_Contextos accesos_contextos) {
		this.accesos_contextos = accesos_contextos;
	}

}
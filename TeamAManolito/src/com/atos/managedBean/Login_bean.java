package com.atos.managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.atos.hibernate.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;
import com.atos.util.IAcceso_Contextos;

@ManagedBean(name = "login_bean")
@Component("login_bean")
@Scope("prototype")
@SessionScoped
public class Login_bean implements Serializable, Validator {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@ManagedProperty("#{gestion_usuarios}")
	private IGestion_Usuarios gestion_usuarios;

	@ManagedProperty("#{accesos_contextos}")
	private IAcceso_Contextos accesos_contextos;

	@ManagedProperty("#{navegacionBean}")
	private Navegacion_Bean navegacion_Bean;

	/*
	 * private String correo_usuario; private String clave_usuario;
	 */
	private Usuarios usuario_login;

	private boolean loggedin;

	// ********** METODOS DEL CICLO DE VIDA (CDI)
	@PostConstruct
	public void metodo_Inicio() {
		System.out.println("SOY EL METODO DE INICIO DE LOGIN_BEAN");
		usuario_login = new Usuarios();
		loggedin = false;

	}

	@PreDestroy
	public void metodo_Final() {
		System.out.println("SOY EL METODO DE FINALIZACION DE LOGIN_BEAN");
	}
	// ********** FIN METODOS DEL CICLO DE VIDA (CDI)

	public void metodo_Evento(ActionEvent evento) {

	}

	public String metodo_Accion(ActionEvent evento) throws IOException {

		Usuarios resultado = gestion_usuarios.consultar_Login(usuario_login.getDas(), usuario_login.getPassword());

		if (resultado == null) {

			FacesMessage msg = new FacesMessage("Usuario y/o contraseña incorrectos", "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);

			return navegacion_Bean.redirectToLogin();

		} else {

			if (resultado.getAccesoAplicacion() == 0) {
				accesos_contextos.addMensaje("No tienes permisos de acceso!!", "mensaje");
			} else if (resultado.getPrimerLogin() == 1) {
				Usuarios usu= gestion_usuarios.consultar_conRol(resultado.getDas());
				accesos_contextos.addMensaje(
						"Welcome!! " + usu.getRoles().getDescRol(),
						"mensaje");
			} else {
				accesos_contextos.addMensaje("Reedireccionar a cambiar la pass!!", "mensaje");
				loggedin = true;
			}

		}
		return navegacion_Bean.redirectToAdministrador2();

	}
	
	
	

	
	

	public void metodo_EventoCambioClave(ValueChangeEvent evento) {
		System.out.println("SOY EL EVENTO DEL CAMBIO DE CLAVE");
	}

	public void metodo_EventoCambioCorreo(ValueChangeEvent evento) {
		System.out.println("SOY EL EVENTO DEL CAMBIO DE CORREO");

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

	public boolean isLoggedin() {
		return loggedin;
	}

	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}

	public Navegacion_Bean getNavegacion_Bean() {
		return navegacion_Bean;
	}

	public void setNavegacion_Bean(Navegacion_Bean navegacion_Bean) {
		this.navegacion_Bean = navegacion_Bean;
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
		// TODO Auto-generated method stub

	}

	

}
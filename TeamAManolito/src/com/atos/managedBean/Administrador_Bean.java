package com.atos.managedBean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atos.hibernate.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;
import com.atos.util.Generar_Pass;
import com.atos.util.IAcceso_Contextos;

@ManagedBean(name = "administrador_bean")
@ViewScoped
public class Administrador_Bean implements Serializable {
	private Usuarios usuario;

	@ManagedProperty("#{gestion_usuarios}")
	private IGestion_Usuarios gestion_usuarios;

	private String correo;

	@ManagedProperty("#{generar_pass}")
	private Generar_Pass generar_pass;

	private boolean bot_bm;
	private boolean bot_alt;
	private boolean codigo_art;
	private String desc_rol;

	// UTILIDAD GENERAL PARA LA GESTION DE MENSAJES EN MANAGEDBEAN
	@ManagedProperty("#{accesos_contextos}")
	private IAcceso_Contextos accesos_contextos;

	private FacesMessage mensaje;

	@PostConstruct
	public void valores_Iniciales() {
		usuario = new Usuarios();
		generar_pass = new Generar_Pass();

		// ESTADO INICIAL DE LOS BOTONES DEL FORMULARIO
		bot_bm = true;
		bot_alt = false;
		codigo_art = false;
	}

	// opcion de alta
	public void alta(ActionEvent evento) {
		System.out.println("soy el alta");
		try {
			
			usuario.setPassword(generar_pass.generar_Pass());
			// llama al metodo de alta de usuario
			gestion_usuarios.alta_Usuario(usuario);
			// mensaje = new FacesMessage("Alta correcta", "mensaje");
		} catch (Exception e) {
			// mensaje = new FacesMessage("Alta incorrecta", "mensaje");
		}
		// FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
		// ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("");
	}

	public void baja(ActionEvent evento) {
		System.out.println("soy el alta");
		try {
			// llama al metodo de alta de usuario
			gestion_usuarios.baja_Usuario(usuario);
			// accesos_contextos.addMensaje("baja correcta", "mensaje");
		} catch (Exception e) {
			// accesos_contextos.addMensaje("baja incorrecta", "mensaje");
		}
	}

	public void modificacion(ActionEvent evento) {
		System.out.println("soy el alta");
		try {
			// llama al metodo de alta de usuario
			gestion_usuarios.modificacion_Usuario(usuario);

			// accesos_contextos.addMensaje("modificaicon correcta", "mensaje");
		} catch (Exception e) {
			// accesos_contextos.addMensaje("modificaicon incorrecta", "mensaje");
		}
	}

	public void consulta(ActionEvent evento) {
		System.out.println("soy el alta");
		try {
			// llama al metodo de alta de usuario

			//  usuario = gestion_usuarios.consultar_Correo(usuario.getCorreo());
			usuario = gestion_usuarios.consultar_conRol(usuario.getCorreo());
			 desc_rol =usuario.getRoles().getDescRol();
			System.out.println("ok");
			// mensaje = new FacesMessage("consulta correcta", "mensaje");
		} catch (Exception e) {
			// mensaje = new FacesMessage("consulta incorrecta", "mensaje");
			System.out.println("no ok");
		}
		// FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
		// ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("");
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Generar_Pass getGenerar_pass() {
		return generar_pass;
	}

	public void setGenerar_pass(Generar_Pass generar_pass) {
		this.generar_pass = generar_pass;
	}

	public boolean isBot_bm() {
		return bot_bm;
	}

	public void setBot_bm(boolean bot_bm) {
		this.bot_bm = bot_bm;
	}

	public boolean isBot_alt() {
		return bot_alt;
	}

	public void setBot_alt(boolean bot_alt) {
		this.bot_alt = bot_alt;
	}

	public boolean isCodigo_art() {
		return codigo_art;
	}

	public void setCodigo_art(boolean codigo_art) {
		this.codigo_art = codigo_art;
	}

	public IAcceso_Contextos getAccesos_contextos() {
		return accesos_contextos;
	}

	public void setAccesos_contextos(IAcceso_Contextos accesos_contextos) {
		this.accesos_contextos = accesos_contextos;
	}

	/*
	 * public IGestion_Usuarios getGestion_usuarios() { return gestion_usuarios; }
	 */
	public void setGestion_usuarios(IGestion_Usuarios gestion_usuarios) {
		this.gestion_usuarios = gestion_usuarios;
	}

	public String getDesc_rol() {
		return desc_rol;
	}

	public void setDesc_rol(String desc_rol) {
		this.desc_rol = desc_rol;
	}

	public IGestion_Usuarios getGestion_usuarios() {
		return gestion_usuarios;
	}

}

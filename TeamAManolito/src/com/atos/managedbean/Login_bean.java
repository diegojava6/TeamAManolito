package com.atos.managedbean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.bean.ManagedProperty;
import com.atos.dao.UsuariosDAO;
import com.atos.hibernate.Usuarios;
import com.atos.hibernate.modelo.Gestion_Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;

@ManagedBean(name = "login_bean")
@ViewScoped
public class Login_bean {
	@ManagedProperty("#{gestion_usuarios}")
	private IGestion_Usuarios gestion_usuarios;

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

	public void metodo_Accion(ActionEvent evento) {

		boolean resultado = gestion_usuarios.consultar_login(usuario_login.getCorreo(),
				usuario_login.getPassword());
		System.out.println(resultado);
		/*
		 * if(resultado!=false) { System.out.println("el correo existe"); } else {
		 * System.out.println("el correo no existe"); }
		 * 
		 * 
		 * return "";
		 */
	}

	public void metodo_EventoCambioClave(ValueChangeEvent evento) {
		System.out.println("SOY EL EVENTO DEL CAMBIO DE CLAVE");
	}

	public void metodo_EventoCambioCorreo(ValueChangeEvent evento) {
		System.out.println("SOY EL EVENTO DEL CAMBIO DE CORREO");

		// this.usuario_login=this.InterfazGestionUsuarios.consultar_correo(this.correo_usuario);

		if (this.usuario_login != null) {
			System.out.println("el correo existe");
		} else {
			System.out.println("el correo no existe");
		}

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

}

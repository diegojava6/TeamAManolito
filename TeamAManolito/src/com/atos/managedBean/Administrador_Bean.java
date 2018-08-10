package com.atos.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atos.hibernate.Roles;
import com.atos.hibernate.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;
import com.atos.util.Generar_Pass;
import com.atos.util.IAcceso_Contextos;

@ManagedBean(name = "administrador_bean")
@ViewScoped
public class Administrador_Bean implements Serializable {
	private Usuarios usuario;
	
	//porpiedades para datatable
	private List<Usuarios> lista_usuarios;
	private int numero_filas;
	private int modo_seleccion;
	private String filtrado_usuario;
	private String filtrado_apellido;
	//-----------------------------------
	
	@ManagedProperty("#{gestion_usuarios}")
	private IGestion_Usuarios gestion_usuarios;

	//propiedad auxialar/test
	private String correo;

	@ManagedProperty("#{generar_pass}")
	private Generar_Pass generar_pass;

	// propiedades auxiliares test
	private boolean bot_bm;
	private boolean bot_alt;
	private String desc_rol;
	private Roles roles;

	// UTILIDAD GENERAL PARA LA GESTION DE MENSAJES EN MANAGEDBEAN
	@ManagedProperty("#{accesos_contextos}")
	private IAcceso_Contextos accesos_contextos;

	private FacesMessage mensaje;

	@PostConstruct
	public void valores_Iniciales() {
		usuario = new Usuarios();
		roles = new Roles();
		// generar pass no funciona por el momento
		generar_pass = new Generar_Pass();
		lista_usuarios = gestion_usuarios.consultar_Todos();
		modo_seleccion = 1;

		// ESTADO INICIAL DE LOS BOTONES DEL FORMULARIO
		bot_bm = true;
		bot_alt = false;
		
	}

	// opcion de alta
	public void alta(ActionEvent evento) {
		System.out.println("soy el alta");
		try {
			
			usuario.setPassword(generar_pass.generar_Pass());
			usuario.setAccesoAplicacion(1);
			// hacer consulta de codigo rol para descripcion del rol
			roles.setCodRol(1);
			roles.setDescRol("ADMIN");
			usuario.setPrimerLogin(1);
			usuario.setRoles(roles);
			// llama al metodo de alta de usuario
			gestion_usuarios.alta_Usuario(usuario);
			System.out.println("correcta alta");
			bot_alt = true;
			bot_bm = false;
			
		} catch (Exception e) {
			System.out.println("error alta");
		
		}
		
	}

	public void baja(ActionEvent evento) {
		System.out.println("soy el alta");
		if (usuario.getAccesoAplicacion() == 0) {
			System.out.println("usuario ya está dado de baja");
		} else {
		try {
			
			
			// llama al metodo de alta de usuario
			usuario.setAccesoAplicacion(0);
			gestion_usuarios.baja_Usuario(usuario);
			System.out.println("Alta correcta");
			// accesos_contextos.addMensaje("baja correcta", "mensaje");
		} catch (Exception e) {
			System.out.println("alta incorrecta");
			// accesos_contextos.addMensaje("baja incorrecta", "mensaje");
		}
	}
		
	
	}

	public void modificacion(ActionEvent evento) {
		System.out.println("soy el alta");
		try {
			// llama al metodo de alta de usuario
			usuario.setRoles(roles);
			gestion_usuarios.modificacion_Usuario(usuario);
			System.out.println("correcta modificaicon");
			
		} catch (Exception e) {
			
			System.out.println("error modificaicon");
		}
	}

	public void consulta(ActionEvent evento) {
		System.out.println("soy el alta");
		try {
			// llama al metodo de alta de usuario

			//  usuario = gestion_usuarios.consultar_Correo(usuario.getCorreo());
			usuario = gestion_usuarios.consultar_conRol(usuario.getCorreo());
			roles = usuario.getRoles();
			 desc_rol =usuario.getRoles().getDescRol();
			System.out.println("ok");
			bot_bm = false;
			bot_alt = true;
			// mensaje = new FacesMessage("consulta correcta", "mensaje");
		} catch (Exception e) {
			// mensaje = new FacesMessage("consulta incorrecta", "mensaje");
			System.out.println("no ok");
		}
		// FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
		// ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("");
	}
	
	// limapia el formulario
	public void clear (ActionEvent evento) {
		System.out.println("limpiar formulario");
		try {
			usuario = new Usuarios();
			roles = new Roles();
			
			bot_bm = true;
			bot_alt = false;
			
			System.out.println("limpiado completado");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("como puede haber un erorr aquí...");
		}
	}
	
	// metodo tipo filtrado adtatable
	public void cambio_ModoFiltrado(ValueChangeEvent evento) {
		modo_seleccion = (int) evento.getNewValue();
		if(modo_seleccion==1) {
			filtrado_usuario="startsWith";
			filtrado_apellido="startsWith";
		}
		if(modo_seleccion==2) {
			filtrado_usuario="endsWith";
			filtrado_apellido="endsWith";
		}
		if(modo_seleccion==3) {
			filtrado_usuario="contains";
			filtrado_apellido="contains";
		}
		if(modo_seleccion==4) {
			filtrado_usuario="exact";
			filtrado_apellido="exact";
		}
	}
	
	// boton refrescar tabla
	public void refresh_tabla ( ActionEvent event) {
		lista_usuarios = gestion_usuarios.consultar_Todos();
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

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	// accesores para  el datatable
	public List<Usuarios> getLista_usuarios() {
		return lista_usuarios;
	}

	public void setLista_usuarios(List<Usuarios> lista_usuarios) {
		this.lista_usuarios = lista_usuarios;
	}

	public int getNumero_filas() {
		return numero_filas;
	}

	public void setNumero_filas(int numero_filas) {
		this.numero_filas = numero_filas;
	}

	public int getModo_seleccion() {
		return modo_seleccion;
	}

	public void setModo_seleccion(int modo_seleccion) {
		this.modo_seleccion = modo_seleccion;
	}

	public String getFiltrado_usuario() {
		return filtrado_usuario;
	}

	public void setFiltrado_usuario(String filtrado_usuario) {
		this.filtrado_usuario = filtrado_usuario;
	}

	public String getFiltrado_apellido() {
		return filtrado_apellido;
	}

	public void setFiltrado_apellido(String filtrado_apellido) {
		this.filtrado_apellido = filtrado_apellido;
	}

	

	
}

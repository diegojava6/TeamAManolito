package com.atos.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.atos.hibernate.Roles;
import com.atos.hibernate.Usuarios;
import com.atos.hibernate.modelo.IGestion_Roles;
import com.atos.hibernate.modelo.IGestion_Usuarios;
import com.atos.util.Generar_Pass;
import com.atos.util.IAcceso_Contextos;

@ManagedBean(name = "administrador_bean")
@ViewScoped
public class Administrador_Bean implements Serializable {
	private Usuarios usuario;
	private Roles roles;

	// porpiedades para datatable
	private List<Usuarios> lista_usuarios;
	private List<Roles> lista_roles;
	private int numero_filas;
	private int modo_seleccion;
	private String filtrado_das;
	private String filtrado_correo;
	private String filtrado_usuario;
	private String filtrado_apellido;
	private String filtrado_rol;
	private Integer seleccionRol;
	private String correo;
	// -----------------------------------

	@ManagedProperty("#{gestion_usuarios}")
	private IGestion_Usuarios gestion_usuarios;

	@ManagedProperty("#{gestion_roles}")
	private IGestion_Roles gestion_roles;

	@ManagedProperty("#{generar_pass}")
	private Generar_Pass generar_pass;

	// propiedades auxiliares test
	private boolean bot_bm;
	private boolean bot_alt;
	private boolean campo_das;
	private List<Roles> lista_r;
	private List<String> lista_rvista;

	// UTILIDAD GENERAL PARA LA GESTION DE MENSAJES EN MANAGEDBEAN
	@ManagedProperty("#{accesos_contextos}")
	private IAcceso_Contextos accesos_contextos;

	@PostConstruct
	public void valores_Iniciales() {
		usuario = new Usuarios();
		roles = new Roles();

		lista_usuarios = gestion_usuarios.consultar_Todos();
		lista_roles = gestion_roles.consultar_Roles();
		modo_seleccion = 2;
		correo = "";
		// ESTADO INICIAL DE LOS BOTONES DEL FORMULARIO
		bot_bm = true;
		bot_alt = false;
		campo_das = false;

		
	}

	// opcion de alta
	public void alta(ActionEvent evento) {

		
		//comprobar si el campo correo tiene algo escrito o no
		if (correo.hashCode() != 0) {

			try {

				usuario.setPassword(generar_pass.generar_Pass());
				usuario.setPrimerLogin(0);
				usuario.setRoles(roles);
				// llama al metodo de alta de usuario
				gestion_usuarios.alta_Usuario(usuario);
				System.out.println("correcta alta");
				bot_alt = true;
				bot_bm = false;

			} catch (Exception e) {
				System.out.println("error alta");

			}
		} else {
			FacesContext.getCurrentInstance().addMessage("correo necesario", 
			        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correo necesario", correo));
		}

	}

	public void baja(ActionEvent evento) {

		try {

			// llama al metodo de alta de usuario
			usuario.setAccesoAplicacion(0);
			gestion_usuarios.baja_Usuario(usuario);
			System.out.println("Baja correcta");

		} catch (Exception e) {
			System.out.println("Baja incorrecta");

		}

	}

	public void actualizacion_correo(ValueChangeEvent evento) {

		// SE RECIBE EL VALOR SELECCIONADO POR EL EVENTO
		correo = (String) evento.getNewValue();
		
	}
	
	public void carga_Roles(ValueChangeEvent evento) {

		// SE RECIBE EL VALOR SELECCIONADO POR EL EVENTO
		seleccionRol = (Integer) evento.getNewValue();
		roles = gestion_roles.consultar_ID(seleccionRol);

	}
	

	public void modificacion(ActionEvent evento) {

		try {
			// llama al metodo de alta de usuario

			usuario.setRoles(roles);

			gestion_usuarios.modificacion_Usuario(usuario);
			System.out.println("correcta modificacion");

		} catch (Exception e) {

			System.out.println("error modificacion");
		}
	}

	public void consulta(ActionEvent evento) {

		try {

			usuario = gestion_usuarios.consultar_conRol(usuario.getDas());
			roles = usuario.getRoles();
			seleccionRol = usuario.getRoles().getCodRol();
			System.out.println("ok");
			bot_bm = false;
			bot_alt = true;
			setCampo_das(true);

			// mensaje = new FacesMessage("consulta correcta", "mensaje");
		} catch (Exception e) {
			// mensaje = new FacesMessage("consulta incorrecta", "mensaje");
			System.out.println("no ok");
		}
		// FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
		// ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("");
	}

	// limapia el formulario
	public void clear(ActionEvent evento) {
		System.out.println("limpiar formulario");
		try {
			usuario = new Usuarios();
			roles = new Roles();
			seleccionRol = 0;
			bot_bm = true;
			bot_alt = false;
			campo_das = false;

			System.out.println("limpiado completado");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("como puede haber un erorr aqu�...");
		}
	}

	// metodo tipo filtrado datatable
	public void cambio_ModoFiltrado(ValueChangeEvent evento) {
		modo_seleccion = (int) evento.getNewValue();
		if (modo_seleccion == 1) {
			filtrado_das = "startsWith";
			filtrado_correo = "startsWith";
			filtrado_usuario = "startsWith";
			filtrado_apellido = "startsWith";
			filtrado_rol = "startsWith";
		}
		if (modo_seleccion == 2) {
			filtrado_das = "endsWith";
			filtrado_correo = "endsWith";
			filtrado_usuario = "endsWith";
			filtrado_apellido = "endsWith";
			filtrado_rol = "endsWith";
		}
		if (modo_seleccion == 3) {
			filtrado_das = "contains";
			filtrado_correo = "contains";
			filtrado_usuario = "contains";
			filtrado_apellido = "contains";
			filtrado_rol = "contains";
		}
		if (modo_seleccion == 4) {
			filtrado_das = "exact";
			filtrado_correo = "exact";
			filtrado_usuario = "exact";
			filtrado_apellido = "exact";
			filtrado_rol = "exact";
		}
	}
	
	public List<Roles> carga_roles (){
		
		
		return lista_r;
		
	}

	// boton refrescar tabla
	public void refresh_tabla(ActionEvent event) {
		lista_usuarios = gestion_usuarios.consultar_Todos();
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Generar_Pass getGenerar_pass() {
		return generar_pass;
	}

	public boolean isCampo_das() {
		return campo_das;
	}

	public void setCampo_das(boolean campo_das) {
		this.campo_das = campo_das;
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

	public void setGestion_usuarios(IGestion_Usuarios gestion_usuarios) {
		this.gestion_usuarios = gestion_usuarios;
	}

	public IGestion_Usuarios getGestion_usuarios() {
		return gestion_usuarios;
	}

	// accesores para el datatable
	public List<Usuarios> getLista_usuarios() {
		return lista_usuarios;
	}

	public void setLista_usuarios(List<Usuarios> lista_usuarios) {
		this.lista_usuarios = lista_usuarios;
	}

	public List<Roles> getLista_roles() {
		return lista_roles;
	}

	public void setLista_roles(List<Roles> lista_roles) {
		this.lista_roles = lista_roles;
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

	public String getFiltrado_das() {
		return filtrado_das;
	}

	public void setFiltrado_das(String filtrado_das) {
		this.filtrado_das = filtrado_das;
	}

	public String getFiltrado_correo() {
		return filtrado_correo;
	}

	public void setFiltrado_correo(String filtrado_correo) {
		this.filtrado_correo = filtrado_correo;
	}

	public String getFiltrado_rol() {
		return filtrado_rol;
	}

	public void setFiltrado_rol(String filtrado_rol) {
		this.filtrado_rol = filtrado_rol;
	}

	public IGestion_Roles getGestion_roles() {
		return gestion_roles;
	}

	public void setGestion_roles(IGestion_Roles gestion_roles) {
		this.gestion_roles = gestion_roles;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public Integer getSeleccionRol() {
		return seleccionRol;
	}

	public void setSeleccionRol(Integer seleccionRol) {
		this.seleccionRol = seleccionRol;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public List<Roles> getLista_r() {
		return lista_r;
	}

	public void setLista_r(List<Roles> lista_r) {
		this.lista_r = lista_r;
	}

	public List<String> getLista_rvista() {
		return lista_rvista;
	}

	public void setLista_rvista(List<String> lista_rvista) {
		this.lista_rvista = lista_rvista;
	}

}

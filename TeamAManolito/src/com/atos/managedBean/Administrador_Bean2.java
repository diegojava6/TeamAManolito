package com.atos.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.atos.hibernate.Tareas;
import com.atos.hibernate.modelo.IGestion_Tareas;
import com.atos.util.IAcceso_Contextos;

@ManagedBean(name = "administrador_bean2")
@ViewScoped
public class Administrador_Bean2 implements Serializable {
	private Tareas tarea;
	private Tareas tarea_nueva;
	private List<Tareas> lista_tareas;

	@ManagedProperty("#{gestion_tareas}")
	private IGestion_Tareas gestion_tareas;

	// propiedades auxiliares test
	private boolean bot_bm;
	private boolean bot_alt;
	private boolean campo_cod;
	private int numero_filas;

	// UTILIDAD GENERAL PARA LA GESTION DE MENSAJES EN MANAGEDBEAN
	@ManagedProperty("#{accesos_contextos}")
	private IAcceso_Contextos accesos_contextos;

	@PostConstruct
	public void valores_Iniciales() {
		tarea = new Tareas();
		tarea_nueva = new Tareas();
		lista_tareas = gestion_tareas.consultar_Todos();
		// ESTADO INICIAL DE LOS BOTONES DEL FORMULARIO
		bot_bm = true;
		bot_alt = false;
		setCampo_cod(false);

	}

	// opcion de alta
	public void alta(ActionEvent evento) {

		try {

			if (tarea.getCodigo_tarea() != null) {

				System.out.println("El c�digo no se puede elegir, es autom�tico");

				tarea_nueva.setNombre_tarea(tarea.getNombre_tarea());
				tarea_nueva.setDesc(tarea.getDesc());
				tarea_nueva.setEstado(tarea.getEstado());

				gestion_tareas.alta_Tarea(tarea_nueva);
				System.out.println("correcta alta");
				tarea = tarea_nueva;

			} else {

				// llama al metodo de alta de usuario
				gestion_tareas.alta_Tarea(tarea);
				System.out.println("correcta alta");
			}
			bot_alt = true;
			bot_bm = false;
		} catch (Exception e) {
			System.out.println("error alta");

		}

	}

	public void baja(ActionEvent evento) {

		try {

			tarea.setEstado(0);
			gestion_tareas.baja_Tarea(tarea);
			System.out.println("Baja correcta");

		} catch (Exception e) {
			System.out.println("Baja incorrecta");

		}

	}

	public void modificacion(ActionEvent evento) {

		try {
			// llama al metodo de alta de usuario
			gestion_tareas.modificacion_Tarea(tarea);
			System.out.println("correcta modificacion");

		} catch (Exception e) {

			System.out.println("error modificacion");
		}
	}

	public void consulta(ActionEvent evento) {

		try {

			tarea = gestion_tareas.consultar_Codigo(tarea.getCodigo_tarea());

			System.out.println("ok");
			bot_bm = false;
			bot_alt = true;
			setCampo_cod(true);

		} catch (Exception e) {

			System.out.println("no ok");
		}
	}

	// limapia el formulario
	public void clear(ActionEvent evento) {
		System.out.println("limpiar formulario");
		try {
			tarea = new Tareas();
			bot_bm = true;
			bot_alt = false;
			setCampo_cod(false);

			System.out.println("limpiado completado");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("como puede haber un error aqu�...");
		}
	}

	// boton refrescar tabla
	public void refresh_tabla(ActionEvent event) {
		lista_tareas = gestion_tareas.consultar_Todos();
	}

	public Tareas getTarea() {
		return tarea;
	}

	public void setTarea(Tareas tarea) {
		this.tarea = tarea;
	}

	public IGestion_Tareas getGestion_tareas() {
		return gestion_tareas;
	}

	public void setGestion_tareas(IGestion_Tareas gestion_tareas) {
		this.gestion_tareas = gestion_tareas;
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

	public boolean isCampo_cod() {
		return campo_cod;
	}

	public void setCampo_cod(boolean campo_cod) {
		this.campo_cod = campo_cod;
	}

	public IAcceso_Contextos getAccesos_contextos() {
		return accesos_contextos;
	}

	public void setAccesos_contextos(IAcceso_Contextos accesos_contextos) {
		this.accesos_contextos = accesos_contextos;
	}

	public List<Tareas> getLista_tareas() {
		return lista_tareas;
	}

	public void setLista_tareas(List<Tareas> lista_tareas) {
		this.lista_tareas = lista_tareas;
	}

	public int getNumero_filas() {
		return numero_filas;
	}

	public void setNumero_filas(int numero_filas) {
		this.numero_filas = numero_filas;
	}

}

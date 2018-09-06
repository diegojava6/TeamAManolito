package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.atos.dao.TareasDAO;
import com.atos.hibernate.Tareas;

@Component("gestion_tareas")
@Scope("prototype")
public class Gestion_Tareas implements IGestion_Tareas {

	private TareasDAO tareasdao;

	@Override
	@Transactional(readOnly = true)
	public List<Tareas> consultar_Todos() {
		// TODO Auto-generated method stub
		return tareasdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	// Metodo que recibe un objeto usuario en base a un string recibido (PK-correo)
	public Tareas consultar_Codigo(Integer codigo) {
		// TODO Auto-generated method stub

		return tareasdao.findById(codigo);

	}

	@Override
	@Transactional
	public void alta_Tarea(Tareas tarea) {
		tareasdao.save(tarea);
	}

	@Override
	@Transactional
	public void baja_Tarea(Tareas tarea) {
		tareasdao.attachDirty(tarea);
	}

	@Override
	@Transactional
	public void modificacion_Tarea(Tareas tarea) {
		tareasdao.attachDirty(tarea);
	}

	public TareasDAO getTareasdao() {
		return tareasdao;
	}

	public void setTareasdao(TareasDAO tareasdao) {
		this.tareasdao = tareasdao;
	}

}

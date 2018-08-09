package com.atos.dao_ext;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.Usuarios;
import com.atos.dao.UsuariosDAO;


public class UsuarioDAO_EXT extends UsuariosDAO {

	
	@Transactional(readOnly = true)
	public Usuarios consultar_ConRol(String correo_usuario) {
		// APERTURA DE CRITERIA PARA LA CONSULTA
		Criteria consulta = getCurrentSession().createCriteria(Usuarios.class);
		// MODO DE RESOLUCION DE CARGA VAGA
		consulta.setFetchMode("roles", FetchMode.JOIN);
		// CONDICIONES DE LA CONSULTA
		consulta.add(Restrictions.idEq(correo_usuario));
		// SE EJECUTA LA CONSULTA
		Usuarios usuario = (Usuarios) consulta.uniqueResult();
		return usuario;
	}
	/*
	 * @Override
	 * 
	 * @Transactional(readOnly = true) 
	 * public Usuarios consultar_Tareas(String
	 * correo_usuario) { // APERTURA DE CRITERIA PARA LA CONSULTA Criteria consulta
	 * = getCurrentSession().createCriteria(Usuarios.class); // MODO DE RESOLUCION
	 * DE CARGA VAGA consulta.setFetchMode("roles", FetchMode.JOIN);
	 * consulta.setFetchMode("roles.tareases", FetchMode.JOIN); // CONDICIONES DE LA
	 * CONSULTA consulta.add(Restrictions.idEq(correo_usuario)); // TRATAMIENTO DEL
	 * PRODUCTO CARTESIANO DE LA CONSULTA
	 * consulta.setResultTransformer(consulta.DISTINCT_ROOT_ENTITY); // SE EJECUTA
	 * LA CONSULTA Usuarios usuario = (Usuarios) consulta.uniqueResult(); return
	 * usuario; }
	 */

}

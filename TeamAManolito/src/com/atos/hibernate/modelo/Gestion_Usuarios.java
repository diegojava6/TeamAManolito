package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.atos.dao.UsuariosDAO;
import com.atos.hibernate.Usuarios;

public class Gestion_Usuarios implements IGestion_Usuarios {
	private UsuariosDAO usuariosdao;

	@Override
	@Transactional(readOnly = true)
	public List<Usuarios> consultar_Todos() {
		// TODO Auto-generated method stub
		return usuariosdao.findAll();
	}

	
}

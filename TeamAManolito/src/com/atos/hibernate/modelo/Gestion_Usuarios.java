package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.atos.dao.UsuariosDAO;
import com.atos.hibernate.Usuarios;

@Component("gestion_usuarios")
@Scope("prototype")
public class Gestion_Usuarios implements IGestion_Usuarios {
	private UsuariosDAO usuariosdao;

	@Override
	@Transactional(readOnly = true)
	public List<Usuarios> consultar_Todos() {
		System.out.println("todos");
		// TODO Auto-generated method stub
		return usuariosdao.findAll();
	}

	@Override
	public Usuarios consultar_correo(String correo) {
		// TODO Auto-generated method stub
		return usuariosdao.findById(correo);
	}

	public void setUsuariosdao(UsuariosDAO usuariosdao) {
		this.usuariosdao = usuariosdao;
	}
	

	
}

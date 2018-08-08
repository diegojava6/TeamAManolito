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
	
	private boolean credenciales;

	@Override
	@Transactional(readOnly = true)
	public List<Usuarios> consultar_Todos() {
		// TODO Auto-generated method stub
		return usuariosdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	// Metodo que recibe un objeto usuario en base a un string recibido (PK-correo)
	public Usuarios consultar_correo(String correo) {
		// TODO Auto-generated method stub
		
		return usuariosdao.findById(correo);
		
	}

	@Override
	@Transactional(readOnly = true)
	// Metodo para comprobar si el correo y la password existe en la BBDD y devolver
	// un true o false en su comprobacion
	public boolean consultar_login(String correo, String password) {
		// TODO Auto-generated method stub
		Usuarios usu = usuariosdao.findById(correo);
		
		credenciales = false;

		if (usu != null) {

			if (usu.getCorreo().equals(correo) && usu.getPassword().equals(password)) {

				credenciales = true;

			}
		} 
		
		return credenciales;
	}

	// ACCESOR PARA SPRING
	public void setUsuariosdao(UsuariosDAO usuariosdao) {
		this.usuariosdao = usuariosdao;
	}


}

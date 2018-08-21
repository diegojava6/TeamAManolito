package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.dao.DataAccessException;

import com.atos.hibernate.Usuarios;

public interface IGestion_Usuarios {

	public List<Usuarios> consultar_Todos();
	public Usuarios consultar_Correo(String correo);
	public boolean[] consultar_Login(String correo, String password);
	public void alta_Usuario(Usuarios usuario) throws DataAccessException;
	public void baja_Usuario(Usuarios usuario) throws DataAccessException;
	public void modificacion_Usuario(Usuarios usuario);
	public Usuarios consultar_conRol(String correo);
	
}

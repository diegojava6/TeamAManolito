package com.atos.hibernate;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atos.hibernate.modelo.Gestion_Usuarios;
import com.atos.hibernate.modelo.IGestion_Roles;
import com.atos.hibernate.modelo.IGestion_Usuarios;
import com.atos.util.Generar_Pass;

public class Test {

	public static void main(String[] args) {

		ApplicationContext contexto = new ClassPathXmlApplicationContext("com/spring/modelo.xml");
		
		IGestion_Usuarios gestion_cliente = contexto.getBean(IGestion_Usuarios.class);
		
		Generar_Pass generar_pass = new Generar_Pass();
	
		IGestion_Roles gestion_roles = contexto.getBean(IGestion_Roles.class);
		
		
		//COMPROBAR METODO CONSULTAR ROLES
		List<Roles> rol = gestion_roles.consultar_Roles();
		System.out.println(rol);
		
		List<Usuarios> usuarios = gestion_cliente.consultar_Todos();
		System.out.println(usuarios);

		//COMPROBAR METODO CONSULTAR DAS
		Usuarios cliente = gestion_cliente.consultar_Das("M000001");
		 		
		//COMPROBAR METODO CONSULTAR LOGIN
		Usuarios usuario = gestion_cliente.consultar_Login("M000001","admin");
		System.out.println(usuario);

		//COMPROBAR GENERACION DE PASS AUTO
		String pass = generar_pass.generar_Pass(); 
		System.out.println(pass);

		
		
		
		
	}

}

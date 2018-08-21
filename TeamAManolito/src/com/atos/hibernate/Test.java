package com.atos.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atos.hibernate.modelo.IGestion_Usuarios;
import com.atos.util.Generar_Pass;

public class Test {

	public static void main(String[] args) {

		ApplicationContext contexto = new ClassPathXmlApplicationContext("com/spring/modelo.xml");
		
		IGestion_Usuarios gestion_cliente = contexto.getBean(IGestion_Usuarios.class);
		
		Generar_Pass generar_pass = new Generar_Pass();
	
		
		
		//COMPROBAR METODO CONSULTAR CORREO 
		Usuarios cliente = gestion_cliente.consultar_Correo("admin@admin.com");
		 		
		//COMPROBAR METODO CONSULTAR LOGIN
		/*boolean credencial = gestion_cliente.consultar_Login("admin@admin.com","admin");
		System.out.println(credencial);
		*/
		boolean[] array = gestion_cliente.consultar_Login("admin@admin.com","admin");
		/*
		//COMPROBAR GENERACION DE PASS AUTO
		String pass = generar_pass.generar_Pass(); 
		System.out.println(pass);
*/
		
		
		
		
	}

}

package com.atos.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atos.hibernate.modelo.IGestion_Usuarios;

public class Test {

	public static void main(String[] args) {

		ApplicationContext contexto = new ClassPathXmlApplicationContext("com/spring/modelo.xml");
		
		IGestion_Usuarios gestion_cliente = contexto.getBean(IGestion_Usuarios.class);
		
		Usuarios cliente = gestion_cliente.consultar_correo("admin@admin.");

		System.out.println("*********************** SIGUIENTE CONSULTA ****************************");
		
		boolean credencial = gestion_cliente.consultar_login("admin@admin.com","amin");
		
		System.out.println(credencial);


	}

}

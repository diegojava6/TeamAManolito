package com.atos.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atos.hibernate.modelo.IGestion_Usuarios;

public class inicio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext contexto = new ClassPathXmlApplicationContext(
				"com/spring/modelo.xml");

		// ***** PRUEBAS DE USUARIO
		IGestion_Usuarios gestion = contexto.getBean(IGestion_Usuarios.class);
		Usuarios usuario1 = gestion.consultar_correo("admin@admin");
	}

}

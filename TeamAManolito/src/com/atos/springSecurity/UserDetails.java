package com.atos.springSecurity;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.atos.hibernate.Roles;
import com.atos.hibernate.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;


@Service("userDetailsService")
public class UserDetails implements UserDetailsService, Serializable {


	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{gestion_usuarios}")
	private IGestion_Usuarios gestion_usuarios;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Usuarios usu = gestion_usuarios.consultar_conRol(username);

		if(usu==null) {
			System.out.println("Usario inexistente!");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Roles rol = usu.getRoles();
		
		GrantedAuthority lista = new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return rol.getDescRol();
			}
		};
		authorities.add(lista);

		User userDetails = new User(usu.getDas(),encoder.encode(usu.getPassword()), authorities);

		return userDetails;

	}

	public org.springframework.security.core.userdetails.UserDetails loadUserByPassword(
			org.springframework.security.core.userdetails.UserDetails password) {

		System.out.println(password);

		return password;

	}

//	private User buildUserForAuthentication(Usuarios user, List<GrantedAuthority> authorities) {
//		return new User(user.getDas(), user.getPassword(), true, true, true, true, authorities);
//	}
//
//	private List<GrantedAuthority> buildUserAuthority(Roles roles) {
//		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
//
//		setAuths.add(new SimpleGrantedAuthority("Adminisitrador"));
//
//		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
//		return Result;
//
//	}

//	public void verificacion_usuario(String das, String pass) {
//		// optimizar consulta apra traer roles de una vez malditasea**criteria
//		System.out.println("el das de verificaicon usuario " + das);
//		System.out.println("elpass de verificaicon usuario " + pass);
//		// usus = gestion_usuarios.consultar_Login(das,pass);
//		Usuarios usus = gestion_usuarios.consultar_Das(das);
//		Integer primerLogin = usus.getPrimerLogin();
//		Integer acceso_app = usus.getAccesoAplicacion();
//		if (usus != null) {
//			switch (acceso_app) {
//			case 0:
//				// Statements
//				// no tiene acceso
//				System.out.println("sacar mensaje no tieen acceos a la app");
//				break; // optional
//
//			case 1:
//				// Statements
//				// si tiene acceso
//				System.out.println("tiene acceso");
//				if (primerLogin == 1) {
//					System.out.println("se debe redireccionar a cambio de password");
//				} else {
//
//					Usuarios usu = gestion_usuarios.consultar_Das(usus.getDas());
//					usu = gestion_usuarios.consultar_conRol(usus.getDas());
//					System.out.println("correcto");
//				}
//				break; // optional
//
//			// You can have any number of case statements.
//			default: // Optional
//				System.out.println("no deberia tener este valor , ergo no tiene acceso");
//
//			}
//		}
//	}
	

	public IGestion_Usuarios getGestion_usuarios() {
		return gestion_usuarios;
	}

	public void setGestion_usuarios(IGestion_Usuarios gestion_usuarios) {
		this.gestion_usuarios = gestion_usuarios;
	}

	
	
}

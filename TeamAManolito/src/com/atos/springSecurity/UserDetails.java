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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.atos.hibernate.Roles;
import com.atos.hibernate.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;



@ManagedBean(name = "login_bean")
@SessionScoped
@Service("userDetailsService")
public class UserDetails implements UserDetailsService,Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{gestion_usuarios}")
	private IGestion_Usuarios gestion_usuarios;

	
	private Usuarios usus;
	private Usuarios usuarioConLogin;
	private String dasusu;
	private String passwordusu;
	private Usuarios usuario_login;

	
	
	@PostConstruct
	public void init() {
		
		usuario_login = new Usuarios();
		usuario_login.setDas("m000004");
		usuario_login.setPassword("admin");

		 
		 System.out.println("test");
		System.out.println(dasusu);
		
		//System.out.println(loginb.getUsuario_login().getDas());
		System.out.println("test");
	}

	

	
	
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("das"+ usuario_login.getDas());
		System.out.println("pass"+ usuario_login.getPassword());
		verificacion_usuario(usuario_login.getDas(), usuario_login.getPassword());
		
			
			List<GrantedAuthority> authorities =   buildUserAuthority(usuario_login.getRoles());
			return buildUserForAuthentication(usuario_login, authorities);
		
		//SecurityConfig sec = new SecurityConfig().configureGlobal(auth);
		
		
			
	}
	
	private User buildUserForAuthentication ( Usuarios user, List<GrantedAuthority> authorities) {
		return new User(user.getDas(),user.getPassword(),true,true,true,true,authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Roles roles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		
			setAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
		
	}
	
	public void transpaso(String das, String password) {
		// TODO Auto-generated method stub
		System.out.println(das);
		System.out.println(password);
		dasusu = das;
		passwordusu = password;
		verificacion_usuario(das, password);
	}

public void verificacion_usuario (String das, String pass) {
	//optimizar consulta apra traer roles de una vez malditasea**criteria
	System.out.println("el das de verificaicon usuario " + das);
	System.out.println("elpass de verificaicon usuario " + pass);
	// usus = gestion_usuarios.consultar_Login(das,pass);
	 usus = gestion_usuarios.consultar_Das(das);
	Integer primerLogin = usus.getPrimerLogin();
	Integer acceso_app = usus.getAccesoAplicacion();
	if ( usus !=null) {
		switch(acceso_app){
		   case 0 :
		      // Statements
			   // no tiene acceso
			   System.out.println("sacar mensaje no tieen acceos a la app");
		      break; // optional
		   
		   case 1 :
		      // Statements
			   // si tiene acceso 
			   System.out.println("tiene acceso");
			   if(primerLogin == 1) {
				   System.out.println("se debe redireccionar a cambio de password");
			   } else {
				  // usuarioConLogin = gestion_usuarios.consultar_conRol(dasusu);
				   usuario_login = gestion_usuarios.consultar_Das(usuario_login.getDas());
				   usuario_login = gestion_usuarios.consultar_conRol(usuario_login.getDas());
				   System.out.println("correcto");
			   }
		      break; // optional
		   
		   // You can have any number of case statements.
		   default : // Optional
		      System.out.println("no deberia tener este valor , ergo no tiene acceso");
			   
		}
	}
}



public void metodo_Accion(ActionEvent evento) throws IOException {
	System.out.println("metodo submit");
}


	public Usuarios getUsus() {
		return usus;
	}


	public void setUsus(Usuarios usus) {
		this.usus = usus;
	}

	

	public String getDasusu() {
		return dasusu;
	}





	public void setDasusu(String dasusu) {
		this.dasusu = dasusu;
	}





	public String getPasswordusu() {
		return passwordusu;
	}





	public void setPasswordusu(String passwordusu) {
		this.passwordusu = passwordusu;
	}





	public IGestion_Usuarios getGestion_usuarios() {
		return gestion_usuarios;
	}





	public void setGestion_usuarios(IGestion_Usuarios gestion_usuarios) {
		this.gestion_usuarios = gestion_usuarios;
	}





	public Usuarios getUsuarioConLogin() {
		return usuarioConLogin;
	}





	public void setUsuarioConLogin(Usuarios usuarioConLogin) {
		this.usuarioConLogin = usuarioConLogin;
	}





	public Usuarios getUsuario_login() {
		return usuario_login;
	}





	public void setUsuario_login(Usuarios usuario_login) {
		this.usuario_login = usuario_login;
	}






	
	
}

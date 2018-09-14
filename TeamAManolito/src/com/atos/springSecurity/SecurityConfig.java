package com.atos.springSecurity;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import com.atos.hibernate.modelo.IGestion_Usuarios;
import com.atos.managedBean.Login_bean;

@EnableWebSecurity
@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@ManagedProperty("#{login_bean}")
	private Login_bean authProvider;
	 
	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	            .inMemoryAuthentication()
	                .withUser("user").password("password").roles("USER");
	        
	    }
	 // with default password encoder
	/* @Bean
		public UserDetailsService userDetailsService() {
			InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
			manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
			return manager;
		}*/
	 
	 

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http
		.authorizeRequests()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/xhtml/Login.xhtml").permitAll();
		http.formLogin().failureUrl("/xhtml/Login.xhtml");
		http.formLogin().defaultSuccessUrl("/xhtml/Administrador2.xhtml",true);
	}



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
	//if(authProvider.isLoggedin()==true)
		//System.out.println("entra generador usuario builder");
	// auth.inMemoryAuthentication().withUser(authProvider.getUsuario_login().getDas()).password(authProvider.getUsuario_login().getPassword()).roles(authProvider.getUsuario_login().getRoles().getDescRol());
	}

	
	
	
	

}

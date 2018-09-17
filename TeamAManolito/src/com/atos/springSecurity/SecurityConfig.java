package com.atos.springSecurity;

import java.io.Serializable;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import com.atos.hibernate.modelo.IGestion_Usuarios;
import com.atos.managedBean.Login_bean;

@EnableWebSecurity
@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

	
	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;
	
	
	 
	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	      auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	        
	    }
	
	 

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



	

	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}



	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}



	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}



	




	
	
	
	

}

package com.atos.springSecurity;

import java.io.Serializable;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
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

@EnableGlobalAuthentication
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable();
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/Login.jsp").permitAll();
		http.formLogin().failureUrl("/Login.jsp?error");
		http.formLogin().usernameParameter("username").passwordParameter("password");
		http.authorizeRequests().antMatchers("/admin/**").hasRole("Administrador").anyRequest().authenticated().and()
				.formLogin().defaultSuccessUrl("/xhtml/admin/Administrador2.xhtml");
		http.authorizeRequests().antMatchers("/usuario/**").hasAnyRole("Usuario").anyRequest().authenticated().and()
				.formLogin().defaultSuccessUrl("/xhtml/usuario/inicio.xhtml");
//		http.logout().logoutUrl("/Login.jsp");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}

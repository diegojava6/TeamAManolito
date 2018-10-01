package com.atos.springSecurity;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedProperty;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.atos.hibernate.modelo.IGestion_Usuarios;

@EnableGlobalAuthentication
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/Login.jsp").permitAll()
				.antMatchers("/xhtml/admin/**").hasAuthority("administrador")
				.antMatchers("/xhtml/usuario/**").hasAnyAuthority("administrador", "usuario").anyRequest()
				.authenticated()
				.and()
			.formLogin()
				.loginPage("/Login.jsp")
				.successHandler(customizeAuthenticationSuccessHandler())
				.failureUrl("/Login.jsp?error")
				.usernameParameter("username").passwordParameter("password")
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/Login.jsp?logout")
				.invalidateHttpSession(true)
				.and()
			.csrf().disable();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationSuccessHandler customizeAuthenticationSuccessHandler() {
		return new CustomizeAuthenticationSuccessHandler();
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}

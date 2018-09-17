package com.atos.springSecurity;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.Roles;
import com.atos.hibernate.Usuarios;
import com.atos.managedBean.Login_bean;

import net.bytebuddy.asm.Advice.OffsetMapping.Target.ForArray.ReadOnly;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service("userDetailsService")
public class UserDetails implements UserDetailsService {
	@Autowired
	private Login_bean login_bean;

	@Transactional(readOnly = true)
	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if(login_bean.isLoggedin()==true) {
			Usuarios user = login_bean.getUsuario_login();
			List<GrantedAuthority> authorities = 
                    buildUserAuthority(login_bean.getUsuario_login().getRoles());
			return buildUserForAuthentication(user, authorities);
		}
		return null;
	}
	private User buildUserForAuthentication ( Usuarios user, List<GrantedAuthority> authorities) {
		return new User(user.getDas(),user.getPassword(),authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Roles roles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		
			setAuths.add(new SimpleGrantedAuthority(roles.getDescRol()));
		
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
		
	}
	
}

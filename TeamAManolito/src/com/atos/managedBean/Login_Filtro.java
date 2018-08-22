package com.atos.managedBean;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atos.managedBean.Login_bean;

public class Login_Filtro implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		  Login_bean loginBean = (Login_bean)((HttpServletRequest)request).getSession().getAttribute("loginBean");
	         
	        // For the first application request there is no loginBean in the session so user needs to log in
	        // For other requests loginBean is present but we need to check if user has logged in successfully
	        if (loginBean == null || !loginBean.isLoggedin()) {
	            String contextPath = ((HttpServletRequest)request).getContextPath();
	            ((HttpServletResponse)response).sendRedirect(contextPath + "/Login.xhtml");
	        }
	         
	        chain.doFilter(request, response);
	             
	    
		
	}

	
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

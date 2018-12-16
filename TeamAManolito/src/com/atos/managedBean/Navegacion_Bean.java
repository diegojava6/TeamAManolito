package com.atos.managedBean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
@ManagedBean(name = "navegacionBean")
@SessionScoped
public class Navegacion_Bean implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -1320559301796412852L;
	/**
	 * 
	 */


	/**
	 * 
	 */
	

	public String redirectToLogin() {
	        return "/Login.xhtml?faces-redirect=true";
	    }
	 
	   public String toLogin() {
	        return "/Login.xhtml";
	    }
	   
	   public String redirectToAdministrador2() {
	        return "/Secured/Administrador2.xhtml?faces-redirect=true";
	    }
	     
	    /**
	     * Go to info page.
	     * @return Info page name.
	     */
	    public String toAdrministrador2() {
	        return "/Secured/Administrador2.xhtml";
	    }

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

}

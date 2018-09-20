package com.atos.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import javax.faces.validator.Validator;

@ManagedBean(name = "validador_Email")
public class Validador_Email implements Validator {
	 private final static String EMAIL_CARACTERES= "^[_a-zA-Z0-9-]+(.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(.[a-zA-Z0-9-]+)*(.[a-zA-Z]{2,4})$";
     
	    private final static Pattern EMAIL_PATRON_COMPILADO= Pattern.compile(EMAIL_CARACTERES);
	    
	    
	    public void validate(FacesContext fc, UIComponent c, Object objeto) throws ValidatorException {
	        // vacio no vale
	        if (objeto == null || "".equals((String)objeto)) {
	            FacesMessage msg = new FacesMessage("No hay valor!", " Error");
	            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	            throw new ValidatorException(msg);
	        }
	         
	        // The email matcher
	        Matcher matcher = EMAIL_PATRON_COMPILADO.matcher((String)objeto);
	         
	        if (!matcher.matches()) {   // no coincide
	            FacesMessage msg = new FacesMessage("valor invalido en el email!", "Error");
	            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	            throw new ValidatorException(msg);
	        }
	         
	    }


		public static String getEmailCaracteres() {
			return EMAIL_CARACTERES;
		}


		public static Pattern getEmailPatronCompilado() {
			return EMAIL_PATRON_COMPILADO;
		}
	    
	    
	
}

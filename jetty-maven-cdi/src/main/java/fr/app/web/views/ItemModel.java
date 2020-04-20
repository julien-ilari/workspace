package fr.app.web.views;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import fr.app.web.faces.interceptors.annotations.ElementTrace;
import fr.app.web.faces.validators.UpperCase;




public class ItemModel implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@NotNull
	@UpperCase
	@ElementTrace(libelle="Identifiant")
	private String id;
	
	private String value;
	
	
	private String label;
	
	private Boolean require;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Boolean getRequire() {
		return require;
	}

	public void setRequire(Boolean require) {
		this.require = require;
	}





}

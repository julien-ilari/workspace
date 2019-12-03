package fr.app.web.views;

import javax.validation.constraints.NotNull;

import fr.app.web.faces.abstracts.AbstractModel;
import fr.app.web.faces.generator.InputText;
import fr.app.web.faces.interceptors.annotations.ElementTrace;
import fr.app.web.faces.validators.UpperCase;



public class ExempleModel extends AbstractModel<String> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@NotNull
	@UpperCase
	@ElementTrace(libelle="Identifiant")
	@InputText("Identifiant")
	private String id;
	
	private String value;
	
	private String label;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
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





}

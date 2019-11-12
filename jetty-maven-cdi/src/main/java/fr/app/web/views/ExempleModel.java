package fr.app.web.views;

import javax.validation.constraints.NotNull;

import fr.app.web.faces.abstracts.AbstractModel;
import fr.app.web.faces.validators.UpperCase;


public class ExempleModel extends AbstractModel<String> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@UpperCase
	private String id;
	
	
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
		
	}

}

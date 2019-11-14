package fr.app.web.views;

import javax.validation.constraints.NotNull;

import fr.app.web.faces.abstracts.AbstractModel;
import fr.app.web.faces.interceptors.annotations.ElementTrace;
import fr.app.web.faces.validators.UpperCase;



public class ExempleModel extends AbstractModel<String> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@ElementTrace(libelle="Identifiant")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExempleModel other = (ExempleModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}

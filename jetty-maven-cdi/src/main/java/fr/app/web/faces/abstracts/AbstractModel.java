package fr.app.web.faces.abstracts;

import java.io.Serializable;



public abstract class AbstractModel<ID extends Serializable> extends AbstractBean  {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public abstract ID getId();

	public abstract void setId(ID id);

	public void clear() {
		setId(null);
	}
	
	public String getUniqueValue() {
		return String.valueOf(getId());
	}
		
	
}

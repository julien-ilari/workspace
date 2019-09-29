package fr.app.web.views;

import fr.app.web.faces.abstracts.AbstractModel;


public class ExempleModel extends AbstractModel<String> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
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

package fr.app.web.views;

import java.io.IOException;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import fr.app.web.faces.abstracts.GenericViewCRUD;
import fr.app.web.faces.interceptors.annotations.Log;

@ViewScoped
@Named
@Log 
public class ExempleViewAdmin extends GenericViewCRUD<ExempleModel>{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void loadBean() throws IOException {
		// TODO Auto-generated method stub
		super.loadBean();
		ExempleModel model = new ExempleModel();
		model.setId("TEST");
		getListModels().add(model);
	}
	
	

	
	
}

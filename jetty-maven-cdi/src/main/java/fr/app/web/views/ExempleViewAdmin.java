package fr.app.web.views;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.beanutils.BeanUtils;

import fr.app.web.faces.abstracts.GenericViewCRUD;

@ViewScoped
@Named
//@Log 
public class ExempleViewAdmin extends GenericViewCRUD<ExempleModel> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private UIForm form;

	private UIInput input;

	
	private Formulaire formulaire;

	public void createHtmlInputText(ActionEvent event) throws JAXBException {
		
	
	}
	
	@Override
	public void insertValueListener(ActionEvent event) throws IOException {	
		for (ItemModel item : formulaire.getItems()) {
			try {
				BeanUtils.copyProperty(getModel(), item.getId(), item.getValue());
			} catch (IllegalAccessException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.insertValueListener(event);
	}

	@PostConstruct
	public void init() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Formulaire.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();

		
		formulaire = (Formulaire) unmarshaller.unmarshal(new File("C:/Users/33671/Desktop/travail julien/repository/workspace/jetty-maven-cdi/src/main/resources/page.xml"));
		
	}

	@Override
	public void loadBean() throws IOException {
		// TODO Auto-generated method stub
		super.loadBean();

	}

	public UIInput getInput() {
		return input;
	}

	public void setInput(UIInput input) {
		this.input = input;
	}



	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
	}

	public List<ItemModel> getItems() {
		return formulaire.getItems();
	}

	

}

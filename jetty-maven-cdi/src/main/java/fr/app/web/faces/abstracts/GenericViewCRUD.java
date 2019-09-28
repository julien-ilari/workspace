package fr.app.web.faces.abstracts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIForm;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;

import fr.app.web.faces.exceptions.MetierException;
import fr.app.web.faces.utils.JSFUtil;



public abstract class GenericViewCRUD extends AbstractBean {

	private static final long serialVersionUID = 1L;
	

	/**
	 * models : liste de plusieurs entité.
	 */
	private List<Object> listModels;

	/**
	 * model : Représente une seul entité.
	 */
	@Inject
	private Object model;

	/**
	 * Liste des IEntity filtrés.
	 */
	private List<Object> filteredValues;

	/**
	 * IEntity selectionné.
	 */
	private Object selectedValue;

	/**
	 * Composant Formulaire pour l'ajout et la modification.
	 */
	private UIForm formAjout;

	/**
	 * Formulaire contenant pour la dataTable.
	 */
	private UIForm formList;

	private HtmlInputText inputUniqueText;

	/**
	 * La dataTable contenant la liste des données.
	 */
	private DataTable dataTable;
	
	/**
	 * Mode d'affichage de la vue de gestion
	 * 0 : Supression 
	 * 1 : Insertion 
	 * 2 : Mise à jour
	 * 3 : Liste (defaut)
	 */
	private Integer mode = 3;
	
	@PostConstruct
	public void postConstructGenericViewCRUD() {
		
	}
	
	
	public void loadBean()throws IOException {
		if (!FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) {
			
        }
		chargerListe();
	}
	
	/**
	 * Appel du métier pour effectuer une modification.
	 * @param entity
	 * @throws MetierException 
	 */
	public void chargerListe() throws IOException { 
		// Initialisation de la liste
		if(null == listModels) {
			listModels = new ArrayList<>();
		}else {
			listModels.clear();
		}
		
		
	}
	

	
	/**
	 * Appel du métier pour effectuer une modification.
	 * @param entity
	 * @throws MetierException 
	 */
	public void modifier() throws IOException { 
	
		
	}

	
	/**
	 * Action Listener : Modifier value.
	 * 
	 * @param event
	 * @throws MetierException
	 * @throws IOException
	 */
	public void modifierValueListener(ActionEvent event) throws IOException {
		modifier();
		
		PrimeFaces.current().executeScript("updateSuccess()");
	}
	
	/**
	 * Action : Message de confirmation pour la modification.
	 */
	public void updateSuccess() {
		if (!JSFUtil.facesContext().isValidationFailed()) {
			FacesMessage message = new FacesMessage("La valeur a bien été modifiée.");
			FacesContext.getCurrentInstance().addMessage(formList.getClientId(), message);
		}
	}
	


	/**
	 * Action Listener : Remove value.
	 * 
	 * @param event
	 * @throws MetierException
	 * @throws IOException 
	 */
	public void removeValueListener(ActionEvent event) throws IOException {
		if(!JSFUtil.facesContext().isPostback())
			return;
		
		PrimeFaces.current().executeScript("deleteSuccess()");
	}
	
	/**
	 * Action : Message de confirmation pour la suppression.
	 */
	public void deleteSuccess() {
		if (!JSFUtil.facesContext().isValidationFailed()) {
			FacesMessage message = new FacesMessage("La valeur a bien été supprimée.");
			FacesContext.getCurrentInstance().addMessage(formList.getClientId(), message);
		}

	}


	/**
	 * Action Listener : Ajout d'une nouvealle valeur
	 * 
	 */
	public void insertValueListener(ActionEvent event) throws IOException {	
		if(!JSFUtil.facesContext().isPostback())
			return;
		
		
		
		//loadBean();
		PrimeFaces.current().executeScript("insertSuccess()");
	}

	


	/**
	 * Action : Message de confirmation et remise à zéro du formulaire de saisie.
	 */
	public void insertSuccess() {
		if (!JSFUtil.facesContext().isValidationFailed()) {
			FacesMessage message = new FacesMessage("La valeur a bien été ajoutée.");
			FacesContext.getCurrentInstance().addMessage(formList.getClientId(), message);
		}
	}


	/**
	 * Validation : Valide la valeur unique 
	 * @param event
	 */
	public void validateUniqueValue(ComponentSystemEvent event) {
		if(!JSFUtil.facesContext().isPostback())
			return;

	}
	
	
	public List<Object> getListModels() {
		return listModels;
	}


	public void setListModels(List<Object> listModels) {
		this.listModels = listModels;
	}


	public Object getModel() {
		return model;
	}


	public void setModel(Object model) {
		this.model = model;
	}


	public List<Object> getFilteredValues() {
		return filteredValues;
	}


	public void setFilteredValues(List<Object> filteredValues) {
		this.filteredValues = filteredValues;
	}


	public Object getSelectedValue() {
		return selectedValue;
	}


	public void setSelectedValue(Object selectedValue) {
		this.selectedValue = selectedValue;
	}


	public UIForm getFormAjout() {
		return formAjout;
	}


	public void setFormAjout(UIForm formAjout) {
		this.formAjout = formAjout;
	}


	public UIForm getFormList() {
		return formList;
	}


	public void setFormList(UIForm formList) {
		this.formList = formList;
	}


	public HtmlInputText getInputUniqueText() {
		return inputUniqueText;
	}


	public void setInputUniqueText(HtmlInputText inputUniqueText) {
		this.inputUniqueText = inputUniqueText;
	}


	public DataTable getDataTable() {
		return dataTable;
	}


	public void setDataTable(DataTable dataTable) {
		this.dataTable = dataTable;
	}


	public Integer getMode() {
		return mode;
	}


	public void setMode(Integer mode) {
		this.mode = mode;
	}

	
}

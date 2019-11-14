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
import fr.app.web.faces.interceptors.annotations.Trace;
import fr.app.web.faces.utils.JSFUtil;



public abstract class GenericViewCRUD<M extends AbstractModel<?>> extends AbstractBean {

	private static final long serialVersionUID = 1L;
	

	/**
	 * models : liste de plusieurs entité.
	 */
	private List<M> listModels;

	/**
	 * model : Représente une seul entité.
	 */
	@Inject
	private M model;

	/**
	 * Liste des IEntity filtrés.
	 */
	private List<M> filteredValues;

	/**
	 * IEntity selectionné.
	 */
	private Object selectedValue;

	/**
	 * Composant Formulaire pour l'ajout et la modification.
	 */
	private UIForm formModel;

	/**
	 * Formulaire contenant pour la dataTable.
	 */
	private UIForm formModels;

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
			FacesContext.getCurrentInstance().addMessage(formModels.getClientId(), message);
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
			FacesContext.getCurrentInstance().addMessage(formModels.getClientId(), message);
		}

	}


	/**
	 * Action Listener : Ajout d'une nouvealle valeur
	 * 
	 */
	@Trace
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
			FacesContext.getCurrentInstance().addMessage(formModels.getClientId(), message);
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
	
	
	public List<M> getListModels() {
		return listModels;
	}


	public void setListModels(List<M> listModels) {
		this.listModels = listModels;
	}


	public Object getModel() {
		return model;
	}


	public void setModel(M model) {
		this.model = model;
	}


	public List<M> getFilteredValues() {
		return filteredValues;
	}


	public void setFilteredValues(List<M> filteredValues) {
		this.filteredValues = filteredValues;
	}


	public Object getSelectedValue() {
		return selectedValue;
	}


	public void setSelectedValue(Object selectedValue) {
		this.selectedValue = selectedValue;
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


	public UIForm getFormModel() {
		return formModel;
	}


	public void setFormModel(UIForm formModel) {
		this.formModel = formModel;
	}


	public UIForm getFormModels() {
		return formModels;
	}


	public void setFormModels(UIForm formModels) {
		this.formModels = formModels;
	}

	
}

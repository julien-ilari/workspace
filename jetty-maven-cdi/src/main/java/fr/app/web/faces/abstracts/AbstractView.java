package fr.app.web.faces.abstracts;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import fr.app.web.faces.exceptions.MetierException;

/**
 * 
 * @author Julien ILARI
 *
 */
public abstract class AbstractView extends AbstractBean {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Indicateur de chargement du bean.
	 */
	protected boolean loaded = false;

	/**
	 * Post Construct
	 * 
	 * @throws MetierException
	 */
	@PostConstruct
	public void postConstructAbstractController() {

	}

	/**
	 * Chargement complémentaire du backend-bean.
	 *
	 * <p>
	 * Cette méthode doit être employée pour le listener <code>PreRenderView</code>
	 * </p>
	 * <p>
	 * <code>
	 *      &lt;f:event listener="#{ccontroller.loadBean}" type="preRenderView" /&gt
	 * </code>
	 * </p>
	 *
	 * @param event Composant d'évènements système
	 */
	public void loadBean(ComponentSystemEvent event) {
		beanLoaded();
	}


	/**
	 * Active l'indicateur de chargement.
	 */
	public void beanLoaded() {
		loaded = true;
	}

	/**
	 * Désactive l'indicateur de chargement.
	 */
	public void beanUnloaded() {
		loaded = false;
	}
	
	/**
	 * Getter de l'indicateur de chargement.
	 * @return
	 */
	public boolean isLoaded() {
		return loaded;
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addErrorMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	
	
	

}

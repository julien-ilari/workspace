package fr.app.web.faces.interceptors;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlForm;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.commons.lang3.StringUtils;

import fr.app.commons.base.exceptions.TechniqueException;
import fr.app.web.faces.exceptions.MetierException;
import fr.app.web.faces.interceptors.annotations.CatchException;
import fr.app.web.faces.utils.JSFUtil;

/**
 * The abstract Class AbstractExceptionInterceptor.
 */
@CatchException
@Interceptor
public class ExceptionInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final char SEPARATOR = ':';

	@Inject
	private ResourceBundle bundle;

	@Inject
	private ExceptionHelper exceptionHelper;

	/**
	 * Traitement erreur Métier
	 */
	private void traiterExceptionMetier(final MetierException exception) {
		if (!exceptionHelper.isErreurTechniqueExistante()) {
			this.addErrorMessage(exception.getMessage(), exception.getIdComposantIhm(),
					exception.getParams());
			exceptionHelper.setErreurMetierExistante(true);
		}
	}
	
	/**
	 * Traitement erreur Technique
	 */
	private void traiterExceptionTechnique(final TechniqueException exception) {
		if (!exceptionHelper.isErreurTechniqueExistante()) {
			this.addErrorMessage(exception.getMessage(), null, null);
			exceptionHelper.setErreurTechniqueExistante(true);
		}
	}

	/**
	 * Capture exception @PostConstruct
	 * @param ic
	 * @throws Exception
	 */
	@PostConstruct
	public void catchInitException(final InvocationContext ic) throws Exception {
		
		
		try {
			ic.proceed();
		} catch (TechniqueException exception) {		
			traiterExceptionTechnique((TechniqueException) exception);
		} catch (MetierException exception) {		
			traiterExceptionMetier((MetierException) exception);
		}catch (Exception exception) {		
			throw exception;
		}
		
		
	}
	
	/**
	 * Catch exception .
	 *
	 * @param ic the ic
	 * @return object
	 * @author
	 * @throws Throwable 
	 */
	@AroundInvoke
	public Object catchException(final InvocationContext ic) throws Throwable  {
		try {
			return ic.proceed();
		} catch (TechniqueException exception) {		
			traiterExceptionTechnique((TechniqueException) exception);
		} catch (MetierException exception) {		
			traiterExceptionMetier((MetierException) exception);
		}catch (Exception exception) {		
//			if (exception.getCause() instanceof RollbackException) {
//				RollbackException rollbackException = (RollbackException) exception.getCause();
//				if (null != rollbackException.getCause() && rollbackException.getCause() instanceof PersistenceException) {
//					PersistenceException persistenceException = (PersistenceException) rollbackException.getCause();
//					if (null != persistenceException.getCause() && persistenceException.getCause() instanceof DatabaseException) {
//						DatabaseException databaseException = (DatabaseException) persistenceException.getCause();
//						// Violation contrainte unique
//						this.addErrorMessage(databaseException.getMessage(), null, null);
//						return null;
//						
//					} 
//				} 
//			}
			
			throw exception;
		}

		return null;
	}
	

	/**
	 * Ajout d'un méssage type erreur.
	 *
	 * @param key le code message
	 * @param componentId
	 * @param parameters
	 */
	protected void addErrorMessage(final String key, final String componentId, List<String> parameters) {

		String message;

		// Récupération du message présent dans le bundle
		if (key.equals(ExceptionHelper.KEY_ERREUR_TECHNIQUE)) {
			try {
				message = bundle.getString(key);
			} catch (MissingResourceException e) {
				message = ExceptionHelper.MESSAGE_ERREUR_TECHNIQUE;
			}
		} else {
			try {
				message = bundle.getString(key);
			} catch (MissingResourceException e) {
				message = key;
			}
		}

		// Ajout des paramètres
		if (parameters != null && !parameters.isEmpty()) {
			message = MessageFormat.format(message, parameters.toArray());
		}

		String idComposant = retrieveComponentId(componentId);
		JSFUtil.facesContext().validationFailed();
		JSFUtil.facesContext().addMessage(idComposant, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}

	/**
	 * Récupérer l'identifiant complet du composant.
	 * 
	 * @param componentId L'identifiant partiel
	 * @return L'identifiant complet du composant
	 */
	private String retrieveComponentId(String componentId) {

		if (componentId == null || componentId.isEmpty()) {
			return null;
		}

		UIViewRoot viewRoot = JSFUtil.getViewRoot();

		// L'identifiant ne contient le séparateur
		// Concaténation du formId et componentId
		if (!StringUtils.contains(componentId, SEPARATOR)) {
			String formId = retrieveFormId(viewRoot);
			if (formId != null) {
				componentId = formId + SEPARATOR + componentId;
			}
		}

		// Recherche du composant en fonction de l'identifiant
		UIComponent component = viewRoot.findComponent(componentId);
		if (component == null) {
			return null;
		}

		if (component instanceof UIInput) {
			((UIInput) component).setValid(false);
		}

		return component.getClientId();
	}

	/**
	 * Récupérer l'identifiant du formulaire fonction d'un composant.
	 *
	 * @param component Un composant.
	 * @return L'identifiant du formulaire.
	 */
	private String retrieveFormId(UIComponent component) {

		String formId = null;

		for (UIComponent child : component.getChildren()) {
			if (child instanceof HtmlForm) {
				return child.getId();

			} else {
				formId = retrieveFormId(child);

				if (formId != null) {
					return formId;
				}
			}
		}

		return formId;
	}

}

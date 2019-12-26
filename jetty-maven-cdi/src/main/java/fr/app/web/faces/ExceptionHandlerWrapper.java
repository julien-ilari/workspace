package fr.app.web.faces;


import static javax.faces.application.FacesMessage.SEVERITY_FATAL;
import static org.omnifaces.util.Messages.addGlobal;

import java.util.Iterator;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.exceptionhandler.FacesMessageExceptionHandler;

public class ExceptionHandlerWrapper extends FacesMessageExceptionHandler {

	public ExceptionHandlerWrapper(ExceptionHandler wrapped) {
		super(wrapped);
	}
	
	@Override
	public void handle() {
		for (Iterator<ExceptionQueuedEvent> iter = getUnhandledExceptionQueuedEvents().iterator(); iter.hasNext();) {
			Throwable exception = iter.next().getContext().getException();
			if(exception.getCause().getCause() instanceof ConstraintViolationException) {
				Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) exception.getCause().getCause()).getConstraintViolations();
				for (Iterator<ConstraintViolation<?>> iterViolations = violations.iterator(); iterViolations.hasNext();) {
					ConstraintViolation<?> violation = iterViolations.next();
					addGlobal(new FacesMessage(SEVERITY_FATAL, createFatalMessageViolation(violation), null));
					iterViolations.remove();
				}
			}else {
				addGlobal(new FacesMessage(SEVERITY_FATAL, createFatalMessage(exception), null));
			}
			
			iter.remove();
		}

		getWrapped().handle();
	}
	
	/**
	 * Créer un message fatal basé sur une exception donnée qui sera à son tour transmis à
	* {@link FacesContext #addMessage (String, javax.faces.application.FacesMessage)}.
	* L'implémentation par défaut renvoie {@link Throwable # toString ()}.
	* @param exception L'exception pour laquelle créer un message fatal.
	* @return Le message fatal créé en fonction de l'exception donnée.
	 */
	@Override
	protected String createFatalMessage(Throwable exception) {
		// Si ViewExpiredException alors detailMessage viewId 
		return exception.toString();
	}
	
	private String createFatalMessageViolation(ConstraintViolation<?> violation) {
		// Si ViewExpiredException alors detailMessage viewId 
		return StringUtils.replace("Erreur, violation de contrainte " 
				+  violation.getRootBeanClass().getSimpleName()  
				+ " " + violation.getPropertyPath()
				+ " : " + violation.getMessage(), "$Proxy$_$$_WeldSubclass", "");
	}

}

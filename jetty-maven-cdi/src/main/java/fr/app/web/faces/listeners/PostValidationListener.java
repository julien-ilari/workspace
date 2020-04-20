package fr.app.web.faces.listeners;

import javax.faces.component.UIInput;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

/**
 * PostValidationListener
 * 
 * @author Julien ILARI
 *
 */
public class PostValidationListener implements SystemEventListener {

	@Override
	public boolean isListenerForSource(Object source) {
		
		return source instanceof UIInput;
	}
	
	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {

		
	}

}

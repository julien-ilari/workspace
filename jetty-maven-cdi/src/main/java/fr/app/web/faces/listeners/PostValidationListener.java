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
		
		return true;
	}
	
	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {

		
		if (event.getSource() instanceof UIInput) {
			UIInput source = (UIInput) event.getSource();

			System.out.println(event.getSource());
			if (!source.isValid()) {
				source.getAttributes().put("styleClass", "form-control is-invalid");
				System.out.println("Champ valide.");
			} else if(source.isValid()) {
				source.getAttributes().put("styleClass", "form-control is-valid");
				System.out.println("Champ invalide.");
			}
			
		} else {
			//System.out.println(event.getSource());
		}
		

	}

}

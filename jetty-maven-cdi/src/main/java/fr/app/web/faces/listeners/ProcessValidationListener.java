package fr.app.web.faces.listeners;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class ProcessValidationListener implements PhaseListener {

	private static final long serialVersionUID = -7607159318721947672L;

	// The phase where the listener is going to be called
	private PhaseId phaseId = PhaseId.PROCESS_VALIDATIONS;

	/**
	 * Recursively walk through the view tree,
	 */
	public void beforePhase(PhaseEvent event) {
		clearInitialState(event.getFacesContext().getViewRoot());
	}
	
	public void clearInitialState(UIComponent component) {
		for (UIComponent child : component.getChildren()) {
			// Initial State HtmlInputText
			if (child instanceof HtmlInputText) {
				((HtmlInputText) child).getAttributes().replace("styleClass", "form-control");
				((HtmlInputText) child).clearInitialState();
			} 


			// Process next node
			clearInitialState(child);
		}
	}

	public void afterPhase(PhaseEvent event) {
		processViewTree(event.getFacesContext().getViewRoot());
	}

	private void processViewTree(UIComponent component) {
		// Go to every child
		for (UIComponent child : component.getChildren()) {

			if (child instanceof HtmlInputText) {
				HtmlInputText htmlInputText = (HtmlInputText) child;
				if (htmlInputText.isValid()) {
					String styleClass  = (String) htmlInputText.getAttributes().get("styleClass");
					htmlInputText.getAttributes().replace("styleClass", styleClass + " is-valid");
					System.out.println("Champ valide.");
				} else if(! htmlInputText.isValid()) {
					String styleClass  = (String) htmlInputText.getAttributes().get("styleClass");
					htmlInputText.getAttributes().replace("styleClass", styleClass + " is-invalid");
					
					System.out.println("Champ invalide.");
					
				}

			} 

			// Process next node
			processViewTree(child);
		}
	}
	
	public PhaseId getPhaseId() {
		return phaseId;
	}

}

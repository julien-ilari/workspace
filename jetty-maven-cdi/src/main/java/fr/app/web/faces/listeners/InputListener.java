package fr.app.web.faces.listeners;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class InputListener implements PhaseListener {

	private static final long serialVersionUID = -7607159318721947672L;

	// The phase where the listener is going to be called
	private PhaseId phaseId = PhaseId.PROCESS_VALIDATIONS;

	/**
	 * Recursively walk through the view tree,
	 */
	public void beforePhase(PhaseEvent event) {
		processViewTree(event.getFacesContext().getViewRoot());
	}

	public void afterPhase(PhaseEvent event) {
		
	}

	public PhaseId getPhaseId() {
		return phaseId;
	}

	private void processViewTree(UIComponent component) {
		// Go to every child
		for (UIComponent child : component.getChildren()) {
			// Display component ID and its type
			System.out.println("+ " + child.getId() + " [" + child.getClass() + "]");
			
			if (child instanceof HtmlInputText) {
				System.out.println(child.getId());
				System.out.println(((HtmlInputText) child).isValid());
				((HtmlInputText) child).getAttributes().replace("styleClass", "form-control");
				((HtmlInputText) child).clearInitialState();
				System.out.println(child.getId());
				System.out.println("Champ réinialisé.");

			} 
			


			// Process next node
			processViewTree(child);
		}
	}

}

package fr.app.web.faces.listeners;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.commons.lang3.StringUtils;

public class ProcessValidationListener implements PhaseListener {

	private static final long serialVersionUID = -7607159318721947672L;

	// The phase where the listener is going to be called
	private PhaseId phaseId = PhaseId.PROCESS_VALIDATIONS;
	
	private Map<UIComponent, String> classCompoent = new HashMap<UIComponent, String>();
	
	private Map<UIInput, HtmlOutputLabel> inputLabel = new HashMap<UIInput, HtmlOutputLabel>();
	

	/**
	 * Recursively walk through the view tree,
	 */
	public void beforePhase(PhaseEvent event) {
		clearInitialState(event.getFacesContext().getViewRoot());
	}
	
	public void clearInitialState(UIComponent component) {
		for (UIComponent child : component.getChildren()) {
			
			
			// Initial State HtmlInputText
			if (child instanceof UIInput) {
				UIInput htmlInputText = (UIInput) child;
				
				// Si non existant dans la map (savegarde le styleClass)
				if(!classCompoent.containsKey(htmlInputText)) {
					String styleClass = (String) htmlInputText.getAttributes().get("styleClass");
					if(null != styleClass) {
						styleClass = styleClass.replace(" is-valid", "");
						styleClass = styleClass.replace(" is-invalid", "");
					}
						classCompoent.put(htmlInputText,styleClass);
					
					
				}

				
			} else if(child instanceof HtmlOutputLabel) {
				HtmlOutputLabel htmlOutputLabel = (HtmlOutputLabel) child;
				for (UIComponent uiChildren : htmlOutputLabel.getParent().getChildren()) {
					
					String forLabel = (String) htmlOutputLabel.getAttributes().get("for");
					if(uiChildren instanceof UIInput) {
						UIInput htmlInputText = (UIInput) uiChildren;
						String idInput = (String) htmlInputText.getAttributes().get("id");
						
						
						if(forLabel.equals(idInput)) {
							if(!inputLabel.containsKey(htmlInputText)) {
							inputLabel.put(htmlInputText, htmlOutputLabel);
							}
							
						}
						
					}
				}
				
		

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

			if (child instanceof UIInput) {
				UIInput uIInput = (UIInput) child;
				//HtmlOutputLabel htmlOutputLabel = inputLabel.get( (UIInput) child);
				
				String styleClassInput  = classCompoent.get(child);
				//String styleClassLabel  = (String) htmlOutputLabel.getAttributes().get("styleClass");
				
				if (uIInput.isValid()) {
					uIInput.getAttributes().replace("styleClass", StringUtils.defaultString(styleClassInput) + " is-valid");
				}else {
					uIInput.getAttributes().replace("styleClass", StringUtils.defaultString(styleClassInput) + " is-invalid");
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

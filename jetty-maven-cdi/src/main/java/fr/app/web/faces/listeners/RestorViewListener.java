package fr.app.web.faces.listeners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class RestorViewListener implements PhaseListener {

	private static final long serialVersionUID = -7607159318721947672L;

	// The phase where the listener is going to be called
	private PhaseId phaseId = PhaseId.RESTORE_VIEW;

	/**
	 * Recursively walk through the view tree,
	 */
	public void beforePhase(PhaseEvent event) {
		return;
	}
	
	

	public void afterPhase(PhaseEvent event) {
		return;
	}


	
	public PhaseId getPhaseId() {
		return phaseId;
	}

}

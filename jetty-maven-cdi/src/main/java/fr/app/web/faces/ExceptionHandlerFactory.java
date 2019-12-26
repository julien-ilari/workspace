package fr.app.web.faces;

import javax.faces.context.ExceptionHandler;

import org.omnifaces.exceptionhandler.FacesMessageExceptionHandlerFactory;

public class ExceptionHandlerFactory extends FacesMessageExceptionHandlerFactory {

	// Constructors ---------------------------------------------------------------------------------------------------

	public ExceptionHandlerFactory(javax.faces.context.ExceptionHandlerFactory wrapped) {
		super(wrapped);
	}

	// Actions --------------------------------------------------------------------------------------------------------
	
	@Override
	public ExceptionHandler getExceptionHandler() {
		return new ExceptionHandlerWrapper(getWrapped().getExceptionHandler());
	}

}

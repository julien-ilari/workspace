package fr.app.web.faces.converters;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@ApplicationScoped
@Named("converter.upperCase")
public class ToUpperCaseConverter implements Converter<Object> {


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
    	return (null != submittedValue) ? submittedValue.toUpperCase() : null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
    	return (String) modelValue;
    }

}

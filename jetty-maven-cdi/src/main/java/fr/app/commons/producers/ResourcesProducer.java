package fr.app.commons.producers;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import fr.app.web.faces.utils.JSFUtil;

/**
 * Producteur ResourceBundle
 * @author Julien ILARI
 *
 */
public class ResourcesProducer {


    /**
     * Produire un bundle de ressources.
     *
     * @return Le bundle de ressources
     */
	@Named(value="bundle")
    @Produces
    public ResourceBundle produceResourceBundle() {
    	String expression = "#{msg}";
        return JSFUtil.getApplication().evaluateExpressionGet(JSFUtil.facesContext(), expression, PropertyResourceBundle.class); 
    }

    /**
     * Produire la locale courante.
     *
     * @return La locale courante
     */
    @Produces
    public Locale produceLocale() {
        return JSFUtil.getLocale();
    }

   

}

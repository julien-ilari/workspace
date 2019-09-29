package fr.app.web.faces.utils;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.enterprise.context.ContextNotActiveException;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * JSF utilities.
 *
 */
public class JSFUtil {

    /**
     * Retourner le contexte Faces.
     *
     * @return Le contexte Faces.
     */
    public static FacesContext facesContext() {

        if (FacesContext.getCurrentInstance() == null) {
            throw new ContextNotActiveException("Le contexte « Faces » n'est pas disponible");
        }

        return FacesContext.getCurrentInstance();
    }

    /**
     * Retourner la vue racine.
     *
     * @return La vue racine.
     */
    public static UIViewRoot getViewRoot() {

        return facesContext().getViewRoot();
    }

    /**
     * Retourner la vue racine.
     *
     * @return La vue racine.
     */
    public static String getViewId() {

        return getViewRoot().getViewId();
    }

    /**
     * Retourner la locale courante.
     *
     * @return La locale courante.
     */
    public static Locale getLocale() {

        if (getViewRoot() == null) {
            return Locale.getDefault();
        }

        return getViewRoot().getLocale();
    }

    /**
     * Retourner le contexte applicatif.
     *
     * @return Le contexte applicatif.
     */
    public static Application getApplication() {

        return facesContext().getApplication();
    }
    
    /**
     * Retourner le manipulateur de navigation.
     *
     * @return Le manipulateur de navigation.
     */
    public static NavigationHandler getNavigationHandler() {

        return getApplication().getNavigationHandler();
    }

    /**
     * Manipule la navigation.
     *
     * @param outcome Action d'arrivée
     */
    public static void handleNavigation(String outcome) {

        handleNavigation(null, outcome);
    }

    /**
     * Manipule la navigation fonction d'une action de départ.
     *
     * @param fromAction Action de départ
     * @param outcome Action d'arrivée
     */
    public static void handleNavigation(String fromAction, String outcome) {

        getNavigationHandler().handleNavigation(facesContext(), fromAction, outcome);
    }

    /**
     * Retourner le bundle de messages.
     *
     * @return Le bundle de messages
     */
    public static String getMessageBundle() {

        return getApplication().getMessageBundle();
    }

    /**
     * Retourner le « resolver » EL.
     *
     * @return Le « resolver » EL.
     */
    public static ELResolver getELResolver() {

        return getApplication().getELResolver();
    }

    public static Object getELValue(String expression) {

        return getELResolver().getValue(getELContext(), null, expression);
    }

    /**
     * Retourner le contexte externe.
     *
     * @return Le contexte externe.
     */
    public static ExternalContext externalContext() {

        return facesContext().getExternalContext();
    }
    
    /**
     * Suivre vers une page.
     *
     * @param page La page d'arrivée
     * @throws java.io.IOException
     */
    public static void forward(String page) throws IOException {

        externalContext().dispatch(page);
    }

    /**
     * Rediriger vers une page.
     *
     * @param page La page d'arrivée
     * @throws java.io.IOException
     */
    public static void redirect(String page) throws IOException {

        externalContext().redirect(page);
    }

    /**
     * Retourner le HttpServletRequest.
     *
     * @return Le HttpServletRequest.
     */
    public static HttpServletRequest getRequest() {

        return (HttpServletRequest) externalContext().getRequest();
    }

    /**
     * Retourner le remote user.
     *
     * @return Le remote user.
     */
    public static String remoteUser() {

        return externalContext().getRemoteUser();
    }
    
    /**
     * Retourner le bundle de ressources.
     *
     * @return Le bundle de ressources.
     */
    public static ResourceBundle getResourceBundle() {

        return getApplication().getResourceBundle(facesContext(), "msg");
    }

    /**
     * Retourner un message du bundle de ressources fonction d'une clef.
     *
     * @param key La clef à extraire
     * @return Le bundle de ressources.
     */
    public static String bundleString(final String key) {

        try {
//            return getResourceBundle().getString(key);
return key;
        } catch (MissingResourceException e) {
            return key;
        }
    }

    /**
     * Retourner les attributs de session.
     *
     * @return Retourne les attributs de session
     */
    public static Map<String, Object> getSessionMap() {

        return externalContext().getSessionMap();
    }

    /**
     * Retourner un attribut de session.
     *
     * @param <T>
     * @param key Nom de l'attribut de session
     * @return La valeur de l'attribut de session
     */
    @SuppressWarnings("unchecked")
	public static <T> T getSessionAttribute(final String key) {
        return (T) getSessionMap().get(key);
    }

    /**
     * Retourner le contexte EL.
     *
     * @return Le contexte EL.
     */
    public static ELContext getELContext() {

        return facesContext().getELContext();
    }

    /**
     * Ajouter un message au contexte Faces.
     *
     * @param message Le message.
     */
    public static void addFacesMessage(String message) {

        facesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    /**
     * Ajouter un message au contexte Faces.
     *
     * @param message Le message.
     */
    public static void addInfoMessage(String message) {

        facesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    /**
     * Ajouter un message au contexte Faces.
     *
     * @param message Le message.
     */
    public static void addWarningMessage(String message) {

        facesContext().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
    }

    /**
     * Ajouter un message au contexte Faces.
     *
     * @param message Le message.
     */
    public static void addErrorMessage(String message) {
        
        facesContext().validationFailed();
        facesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    /**
     * Ajouter un message au contexte Faces.
     *
     * @param message Le message.
     */
    public static void addFatalMessage(String message) {

        facesContext().addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, message, ""));
    }

    /**
     * Ajouter un message au contexte Faces.
     *
     * @param title Le titre du message.
     * @param message Le message.
     */
    public static void addFacesMessage(String title, String message) {

        facesContext().addMessage(title, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
    }

    /**
     * Ajouter un message au contexte Faces.
     *
     * @param title Le titre du message.
     * @param message Le message.
     */
    public static void addInfoMessage(String title, String message) {

        facesContext().addMessage(title, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
    }

    /**
     * Ajouter un message au contexte Faces.
     *
     * @param title Le titre du message.
     * @param message Le message.
     */
    public static void addWarningMessage(String title, String message) {

        facesContext().addMessage(title, new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
    }

    /**
     * Ajouter un message au contexte Faces.
     *
     * @param title Le titre du message.
     * @param message Le message.
     */
    public static void addErrorMessage(String title, String message) {

        facesContext().validationFailed();
        facesContext().addMessage(title, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
    }

    /**
     * Ajouter un message au contexte Faces.
     *
     * @param title Le titre du message.
     * @param message Le message.
     */
    public static void addFatalMessage(String title, String message) {

        facesContext().addMessage(title, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, ""));
    }

    /**
     * Ajouter un message au contexte Faces.
     *
     * @param title Le titre du message.
     * @param message Le message.
     * @param summary Le résumé de l'erreur.
     */
    public static void addFacesMessage(String title, String message, String summary) {

        facesContext().addMessage(title, new FacesMessage(FacesMessage.SEVERITY_INFO, message, summary));
    }

    /**
     * Ajouter un message au contexte Faces.
     *
     * @param title Le titre du message.
     * @param message Le message.
     * @param summary Le résumé de l'erreur.
     */
    public static void addInfoMessage(String title, String message, String summary) {

        facesContext().addMessage(title, new FacesMessage(FacesMessage.SEVERITY_INFO, message, summary));
    }

    /**
     * Ajouter un message au contexte Faces.
     *
     * @param title Le titre du message.
     * @param message Le message.
     * @param summary Le résumé de l'erreur.
     */
    public static void addWarningMessage(String title, String message, String summary) {

        facesContext().addMessage(title, new FacesMessage(FacesMessage.SEVERITY_WARN, message, summary));
    }

    /**
     * Ajouter un message au contexte Faces.
     *
     * @param title Le titre du message.
     * @param message Le message.
     * @param summary Le résumé de l'erreur.
     */
    public static void addErrorMessage(String title, String message, String summary) {

        facesContext().validationFailed();
        facesContext().addMessage(title, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, summary));
    }

    /**
     * Ajouter un message au contexte Faces.
     *
     * @param title Le titre du message.
     * @param message Le message.
     * @param summary Le résumé de l'erreur.
     */
    public static void addFatalMessage(String title, String message, String summary) {

        facesContext().addMessage(title, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, summary));
    }

}

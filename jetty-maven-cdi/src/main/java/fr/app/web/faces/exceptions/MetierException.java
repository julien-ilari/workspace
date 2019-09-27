package fr.app.web.faces.exceptions;

import java.io.Serializable;
import java.util.List;


public class MetierException extends Exception implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 8721036879596389375L;

    /**
     * Permet d'identifier le composant IHM lié à l'exception métier.
     */
    private String idComposantIhm;

    /**
     * Liste de paramètres
     */
    private List<String> params;

    /**
     *
     */
    public MetierException() {
        super();
    }

    /**
     * @param message the detail message (which is saved for later retrieval by
     * the Throwable.getMessage() method).
     */
    public MetierException(final String message) {
        super(message);
    }

    /**
     * @param message the detail message (which is saved for later retrieval by
     * the Throwable.getMessage() method).
     * @param idComposantIhm l'id du composant IHM en lien avec l'exception
     */
    public MetierException(final String message, final String idComposantIhm) {
        super(message);
        this.setIdComposantIhm(idComposantIhm);
    }

    /**
     * @param message the detail message (which is saved for later retrieval by
     * the Throwable.getMessage() method).
     * @param cause the cause (which is saved for later retrieval by the
     * Throwable.getCause() method). (A null value is permitted, and indicates
     * that the cause is nonexistent or unknown.)
     */
    public MetierException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message the detail message (which is saved for later retrieval by
     * the Throwable.getMessage() method).
     * @param cause the cause (which is saved for later retrieval by the
     * Throwable.getCause() method). (A null value is permitted, and indicates
     * that the cause is nonexistent or unknown.)
     * @param idComposantIhm l'id du composant IHM en lien avec l'exception
     */
    public MetierException(final String message, final Throwable cause, final String idComposantIhm) {
        super(message, cause);
        this.setIdComposantIhm(idComposantIhm);
    }

    /**
     * @param cause the cause (which is saved for later retrieval by the
     * Throwable.getCause() method). (A null value is permitted, and indicates
     * that the cause is nonexistent or unknown.)
     */
    public MetierException(final Throwable cause) {
        super(cause);
    }

    /**
     * @param cause the cause (which is saved for later retrieval by the
     * Throwable.getCause() method). (A null value is permitted, and indicates
     * that the cause is nonexistent or unknown.)
     * @param idComposantIhm l'id du composant IHM en lien avec l'exception
     */
    public MetierException(final Throwable cause, final String idComposantIhm) {
        super(cause);
        this.setIdComposantIhm(idComposantIhm);
    }

    /**
     *
     * @param message the detail message (which is saved for later retrieval by
     * the Throwable.getMessage() method).
     * @param params Liste des paramètres pour les messages paramétrés
     */
    public MetierException(final String message, List<String> params) {
        super(message);
        this.params = params;
    }

    /**
     * @param message the detail message (which is saved for later retrieval by
     * the Throwable.getMessage() method).
     * @param idComposantIhm l'id du composant IHM en lien avec l'exception
     * @param params Liste des paramètres pour les messages paramétrés
     */
    public MetierException(final String message, final String idComposantIhm, List<String> params) {
        super(message);
        this.setIdComposantIhm(idComposantIhm);
        this.params = params;
    }

    /**
     * @param message the detail message (which is saved for later retrieval by
     * the Throwable.getMessage() method).
     * @param cause the cause (which is saved for later retrieval by the
     * Throwable.getCause() method). (A null value is permitted, and indicates
     * that the cause is nonexistent or unknown.)
     * @param params Liste des paramètres pour les messages paramétrés
     */
    public MetierException(final String message, final Throwable cause, List<String> params) {
        super(message, cause);
        this.params = params;
    }

    /**
     * @param message the detail message (which is saved for later retrieval by
     * the Throwable.getMessage() method).
     * @param cause the cause (which is saved for later retrieval by the
     * Throwable.getCause() method). (A null value is permitted, and indicates
     * that the cause is nonexistent or unknown.)
     * @param idComposantIhm l'id du composant IHM en lien avec l'exception
     * @param params Liste des paramètres pour les messages paramétrés
     */
    public MetierException(final String message, final Throwable cause, final String idComposantIhm, List<String> params) {
        super(message, cause);
        this.setIdComposantIhm(idComposantIhm);
        this.params = params;
    }

    /**
     * @param cause the cause (which is saved for later retrieval by the
     * Throwable.getCause() method). (A null value is permitted, and indicates
     * that the cause is nonexistent or unknown.)
     * @param params Liste des paramètres pour les messages paramétrés
     */
    public MetierException(final Throwable cause, List<String> params) {
        super(cause);
        this.params = params;
    }

    /**
     * @param cause the cause (which is saved for later retrieval by the
     * Throwable.getCause() method). (A null value is permitted, and indicates
     * that the cause is nonexistent or unknown.)
     * @param idComposantIhm l'id du composant IHM en lien avec l'exception
     * @param params Liste des paramètres pour les messages paramétrés
     */
    public MetierException(final Throwable cause, final String idComposantIhm, List<String> params) {
        super(cause);
        this.setIdComposantIhm(idComposantIhm);
        this.params = params;
    }

    public String getIdComposantIhm() {
        return idComposantIhm;
    }

    public void setIdComposantIhm(String idComposantIhm) {
        this.idComposantIhm = idComposantIhm;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}

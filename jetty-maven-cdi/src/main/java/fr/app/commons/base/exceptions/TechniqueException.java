/**
 *
 */
package fr.app.commons.base.exceptions;

import java.io.Serializable;


public class TechniqueException extends Exception implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1117875240598324461L;
	
	/**
	 * Message d'aide pour le débug (renseigné par le développeur)
	 */
	private String messageFonctionnel = "Aucune indication complémentaire pour l'erreur (cf. exception)."; 
	
	
    /**
     *
     */
    public TechniqueException() {
        super("Une erreur est survenue.");
    }

    /**
     * @param message the detail message (which is saved for later retrieval by
     * the Throwable.getMessage() method).
     */
    public TechniqueException(final String message) {
        super(message);
    }
    

    public TechniqueException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public TechniqueException(final String messageFonctionnel, final String message, final Throwable cause) {
        super(message, cause);
        this.messageFonctionnel = messageFonctionnel;
    }


    public TechniqueException(final Throwable cause) {
        super(cause);
    }


    

}

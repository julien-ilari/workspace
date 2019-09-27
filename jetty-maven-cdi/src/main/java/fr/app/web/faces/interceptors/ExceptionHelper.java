package fr.app.web.faces.interceptors;

import java.io.Serializable;

import javax.enterprise.inject.Model;

@Model
public class ExceptionHelper implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	// CLE DU MESSAGES PRESENT DANS LE BUNDLE POUR LES EXCEPTIONS TECHNIQUES
	public static final String KEY_ERREUR_TECHNIQUE = "erreur.technique";
	public static final String MESSAGE_ERREUR_TECHNIQUE = "Une erreur technique est survenue.";
	
	 // CLE DU MESSAGES PRESENT DANS LE BUNDLE POUR LES EXCEPTIONS FONCTIONNEL
	public static final String KEY_ERREUR_FONCTIONNELLE = "erreur.fonctionnelle";
	public static final String MESSAGE_ERREUR_FONCTIONNELLE = "Une erreur fonctionnelle est survenue.";

    Boolean erreurTechniqueExistante;

    Boolean erreurMetierExistante;

    public ExceptionHelper() {
        erreurTechniqueExistante = false;
        erreurMetierExistante = false;
    }

    public Boolean isErreurTechniqueExistante() {
        return erreurTechniqueExistante;
    }

    public void setErreurTechniqueExistante(Boolean erreurTechniqueExistante) {
        this.erreurTechniqueExistante = erreurTechniqueExistante;
    }

    public Boolean isErreurMetierExistante() {
        return erreurMetierExistante;
    }

    public void setErreurMetierExistante(Boolean erreurMetierExistante) {
        this.erreurMetierExistante = erreurMetierExistante;
    }

    public Boolean getErreurTechniqueExistante() {
        return erreurTechniqueExistante;
    }

    public Boolean getErreurMetierExistante() {
        return erreurMetierExistante;
    }

}

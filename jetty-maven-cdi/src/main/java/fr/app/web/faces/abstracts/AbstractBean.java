package fr.app.web.faces.abstracts;

import java.io.Serializable;

import fr.app.web.faces.utils.JSFUtil;

public abstract class AbstractBean implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Dépôt de resource.
	 */
	protected final String ERREUR_TECHNIQUE = JSFUtil.bundleString("erreur.technique");
}

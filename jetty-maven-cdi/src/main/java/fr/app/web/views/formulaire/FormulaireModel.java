package fr.app.web.views.formulaire;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

/**
 * Représentation des informations du formulaire à transmettre.
 * @Dependent Une nouvelle instance pour l'injection en cours.
 * 
 * @author Julien ILARI
 *
 */
@Dependent
public class FormulaireModel implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Sujet selectionné.
	 */
	private String sujet;
	
	/**
	 * Objet selectionné.
	 */
	private String objet;
	
	/**
	 * Zone de texte générique pour la saisie du formulaire.
	 */
	private String zoneTexte;
	
	
	
	
	
	public String getSujet() {
		return sujet;
	}
	public void setSujet(String sujet) {
		this.sujet = sujet;
	}
	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}
	public String getZoneTexte() {
		return zoneTexte;
	}
	public void setZoneTexte(String zoneTexte) {
		this.zoneTexte = zoneTexte;
	}
	
	


}

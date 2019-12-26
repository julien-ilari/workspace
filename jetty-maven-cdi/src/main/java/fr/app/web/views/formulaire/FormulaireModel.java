package fr.app.web.views.formulaire;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.validation.constraints.NotNull;

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
	@NotNull
	private SujetModel sujet;
	
	/**
	 * Objet selectionné.
	 */
	@NotNull
	private ObjetModel objet;
	
	/**
	 * Zone de texte générique pour la saisie du formulaire.
	 */
	@NotNull
	private String zoneTexte;
	

	public SujetModel getSujet() {
		return sujet;
	}
	public void setSujet(SujetModel sujet) {
		this.sujet = sujet;
	}
	public ObjetModel getObjet() {
		return objet;
	}
	public void setObjet(ObjetModel objet) {
		this.objet = objet;
	}
	public String getZoneTexte() {
		return zoneTexte;
	}
	public void setZoneTexte(String zoneTexte) {
		this.zoneTexte = zoneTexte;
	}
	
	


}

package fr.app.web.faces.interceptors;

public class TraceElement {
	
	
	
	private String libelle;
	
	private String ancienneValeur;
	
	private String nouvelleValeur;
	
	

	public TraceElement(String libelle) {
		super();
		this.libelle = libelle;
	}
	
	


	public TraceElement(String libelle, String ancienneValeur, String nouvelleValeur) {
		super();
		this.libelle = libelle;
		this.ancienneValeur = ancienneValeur;
		this.nouvelleValeur = nouvelleValeur;
	}




	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getAncienneValeur() {
		return ancienneValeur;
	}

	public void setAncienneValeur(String ancienneValeur) {
		this.ancienneValeur = ancienneValeur;
	}

	public String getNouvelleValeur() {
		return nouvelleValeur;
	}

	public void setNouvelleValeur(String nouvelleValeur) {
		this.nouvelleValeur = nouvelleValeur;
	}
	

}
